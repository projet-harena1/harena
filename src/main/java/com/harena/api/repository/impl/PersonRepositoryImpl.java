package com.harena.api.repository.impl;

import com.harena.api.dto.PersonneDTO;
import com.harena.api.repository.PersonRepository;
import com.harena.api.repository.model.Personne;
import com.harena.api.repository.utils.BaseRepository;
import com.harena.api.repository.utils.ReadDataFromJsonFile;
import com.harena.api.repository.utils.WriteDataToJsonFile;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepositoryImpl extends BaseRepository<PersonneDTO> implements PersonRepository {

  public PersonRepositoryImpl(
      ReadDataFromJsonFile<PersonneDTO> readDataFromJsonFile,
      WriteDataToJsonFile<PersonneDTO> writeDataToJsonFile,
      @Value("${path.to.file.person}") String filePath) {
    super(readDataFromJsonFile, writeDataToJsonFile, filePath);
  }

  @Override
  public List<Personne> loadAllData() {
    return super.loadAllData(PersonneDTO.class).stream()
        .map(this::toPersonne)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Personne> create(Personne toCreate) {
    var personneDTO = toPersonneDTO(toCreate);
    return super.create(PersonneDTO.class, personneDTO).map(this::toPersonne);
  }

  @Override
  public Optional<Personne> update(Personne toCreate, Personne toUpdated) {
    var createDTO = toPersonneDTO(toCreate);
    var updateDTO = toPersonneDTO(toUpdated);
    return super.update(PersonneDTO.class, createDTO, updateDTO).map(this::toPersonne);
  }

  @Override
  public Personne findPersonByNom(String nom) {
    return loadAllData().stream()
        .filter(personne -> personne.nom().equals(nom))
        .findFirst()
        .orElse(null);
  }

  private Personne toPersonne(PersonneDTO personneDTO) {
    return new Personne(personneDTO.getNom());
  }

  private PersonneDTO toPersonneDTO(Personne personne) {
    return new PersonneDTO(personne.nom());
  }
}
