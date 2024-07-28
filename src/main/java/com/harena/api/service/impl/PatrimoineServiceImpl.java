package com.harena.api.service.impl;

import com.harena.api.dto.responses.RestPatrimoine;
import com.harena.api.exceptions.InternalServerException;
import com.harena.api.exceptions.ResourceNotFoundException;
import com.harena.api.repository.PatrimoineRepository;
import com.harena.api.repository.PersonRepository;
import com.harena.api.repository.model.Patrimoine;
import com.harena.api.service.PatrimoineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PatrimoineServiceImpl implements PatrimoineService {
    private final PatrimoineRepository patrimoineRepository;
    private final PersonRepository personRepository;

    @Override
    public List<RestPatrimoine> findAllPatrimoines(Long page, Long pageSize) {
        List<Patrimoine> allPatrimoines = patrimoineRepository.loadAllData();
        return allPatrimoines.stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .map(this::toRestPatrimoine)
                .collect(Collectors.toList());
    }

    @Override
    public List<RestPatrimoine> savePatrimoines(List<RestPatrimoine> restPatrimoines) {
        var patrimoines = new ArrayList<RestPatrimoine>();
        restPatrimoines.forEach(restPatrimoine -> {
           var personne = personRepository.findPersonByNom(restPatrimoine.getPossesseur().nom());
           if (personne != null){
               personRepository.update(restPatrimoine.getPossesseur(), restPatrimoine.getPossesseur());
           } else {
               personRepository.create(restPatrimoine.getPossesseur());
           }
           var patrimoine = patrimoineRepository.findPatrimoineByNom(restPatrimoine.getNom());
           if (patrimoine != null){
               patrimoines.add(toRestPatrimoine(
                       patrimoineRepository.update(toPatrimoine(restPatrimoine), patrimoine)
                       .orElseThrow(() -> new InternalServerException("Error creating patrimoine ")))
               );
           } else {
              patrimoines.add(toRestPatrimoine(patrimoineRepository.create(toPatrimoine(restPatrimoine))
                      .orElseThrow(() -> new InternalServerException("Error creating patrimoine ")))
              );
           }
        });
        return patrimoines;
    }

    @Override
    public RestPatrimoine findPatrimoineByNom(String patrimoineNom) {
        var patrimoine = patrimoineRepository.findPatrimoineByNom(patrimoineNom);
        if (patrimoine == null){
            throw new ResourceNotFoundException(patrimoineNom + " n'existe pas");
        }
        return this.toRestPatrimoine(patrimoine);
    }

    private RestPatrimoine toRestPatrimoine(Patrimoine patrimoine){
        return new RestPatrimoine(
                patrimoine.nom(),
                patrimoine.t(),
                patrimoine.possesseur(),
                patrimoine.getValeurComptable()
        );
    }


    private Patrimoine toPatrimoine(RestPatrimoine patrimoine){
        return new Patrimoine(
                patrimoine.getNom(),
                patrimoine.getPossesseur(),
                patrimoine.getT(),
                Set.of()
        );
    }
}
