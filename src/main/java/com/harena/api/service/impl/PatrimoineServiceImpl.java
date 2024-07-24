package com.harena.api.service.impl;

import com.harena.api.dto.PatrimoineSummarized;
import com.harena.api.dto.json.PatrimoineDataJsonFile;
import com.harena.api.dto.json.PersonneDataJsonFile;
import com.harena.api.model.Personne;
import com.harena.api.repository.PatrimoineRepository;
import com.harena.api.repository.PersonRepository;
import com.harena.api.service.PatrimoineService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PatrimoineServiceImpl implements PatrimoineService {
    private final PatrimoineRepository patrimoineRepository;
    private final PersonRepository personRepository;

    public PatrimoineServiceImpl(PatrimoineRepository patrimoineRepository, PersonRepository personRepository) {
        this.patrimoineRepository = patrimoineRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<PatrimoineSummarized> findAllPatrimoines(Integer page, Integer pageSize) {
        var persons = personRepository.loadAllData();
        var personMap = persons.stream()
                .collect(Collectors.toMap(PersonneDataJsonFile::getNom, Function.identity()));
        var patrimoines = patrimoineRepository.loadAllData().stream()
                .map(data -> convertToSummarized(data, personMap))
                .collect(Collectors.toList());
        return getPagedResult(patrimoines, page, pageSize);
    }

    @Override
    public List<PatrimoineSummarized> crupdatePatrimoines(List<PatrimoineSummarized> patrimoines) {
        var persons = personRepository.loadAllData();
        var personMap = persons.stream()
                .collect(Collectors.toMap(PersonneDataJsonFile::getNom, Function.identity()));
        var patrimoinesSaved = new ArrayList<PatrimoineSummarized>();
        patrimoines.forEach(patrimoine -> {
            var possesseur = getOrCreatePerson(personMap, patrimoine.getPossesseur().nom());
            var dataJsonFile = convertToDataJsonFile(patrimoine);
            dataJsonFile.setPossesseurNom(possesseur.getNom());
            var savedData = patrimoineRepository.update(dataJsonFile)
                    .orElseGet(() -> patrimoineRepository.create(dataJsonFile).orElse(null));
            if (savedData != null) {
                patrimoinesSaved.add(convertToSummarized(savedData, personMap));
            }
        });

        return patrimoinesSaved;
    }

    @Override
    public PatrimoineSummarized findPatrimoineByNom(String patrimoineName) {
        var patrimoineDataList = patrimoineRepository.loadAllData();
        var personMap = personRepository.loadAllData().stream()
                .collect(Collectors.toMap(PersonneDataJsonFile::getNom, Function.identity()));
        var patrimoineData = patrimoineDataList.stream()
                .filter(data -> data.getNom().equals(patrimoineName))
                .findFirst()
                .orElse(null);
        return convertToSummarized(patrimoineData, personMap);
    }


    private PersonneDataJsonFile getOrCreatePerson(Map<String, PersonneDataJsonFile> personMap, String personName) {
        return personMap.computeIfAbsent(personName, name -> {
            var newPerson = new PersonneDataJsonFile();
            newPerson.setNom(name);
            personRepository.create(newPerson);
            return newPerson;
        });
    }

    private List<PatrimoineSummarized> getPagedResult(List<PatrimoineSummarized> patrimoines, Integer page, Integer pageSize) {
        var start = (page - 1) * pageSize;
        var end = Math.min(start + pageSize, patrimoines.size());
        if (start >= patrimoines.size() || start < 0) {
            return Collections.emptyList();
        }
        return patrimoines.subList(start, end);
    }

    private PatrimoineSummarized convertToSummarized(PatrimoineDataJsonFile patrimoineData, Map<String, PersonneDataJsonFile> personMap) {
        if (patrimoineData == null) {
            return null;
        }
        var possesseur = Optional.ofNullable(personMap.get(patrimoineData.getPossesseurNom()))
                .map(p -> new Personne(p.getNom()))
                .orElse(null);
        return new PatrimoineSummarized(
                patrimoineData.getNom(),
                patrimoineData.getT(),
                possesseur,
                patrimoineData.getValeurComptable()
        );
    }

    private PatrimoineDataJsonFile convertToDataJsonFile(PatrimoineSummarized patrimoineSummarized) {
        if (patrimoineSummarized == null) {
            return null;
        }
        return new PatrimoineDataJsonFile(
                patrimoineSummarized.getNom(),
                patrimoineSummarized.getPossesseur().nom(),
                patrimoineSummarized.getT(),
                patrimoineSummarized.getValeurComptable()
        );
    }
}
