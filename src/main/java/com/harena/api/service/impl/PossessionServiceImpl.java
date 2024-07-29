package com.harena.api.service.impl;

import com.harena.api.dto.responses.PossessionAvecType;
import com.harena.api.enums.PossessionType;
import com.harena.api.exceptions.ResourceNotFoundException;
import com.harena.api.repository.ArgentRepository;
import com.harena.api.repository.FluxArgentRepository;
import com.harena.api.repository.MaterielRepository;
import com.harena.api.repository.PatrimoineRepository;
import com.harena.api.repository.model.Patrimoine;
import com.harena.api.repository.model.possession.Argent;
import com.harena.api.repository.model.possession.FluxArgent;
import com.harena.api.repository.model.possession.Materiel;
import com.harena.api.repository.model.possession.Possession;
import com.harena.api.service.PossessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PossessionServiceImpl implements PossessionService {
    private final PatrimoineRepository patrimoineRepository;
    private final ArgentRepository argentRepository;
    private final FluxArgentRepository fluxArgentRepository;
    private final MaterielRepository materielRepository;

    @Override
    public List<PossessionAvecType> savePatrimoinePossessions(
            String patrimoineNom,
            List<PossessionAvecType> possessionAvecTypes
    ) {
        var patrimoine = patrimoineRepository.findPatrimoineByNom(patrimoineNom);
        if (patrimoine == null) {
            throw new ResourceNotFoundException(patrimoineNom + " does not exist");
        }
        possessionAvecTypes.forEach(possessionAvecType -> {
            switch (possessionAvecType.getType()) {
                case ARGENT -> this.saveArgent(possessionAvecType.getArgent(), patrimoineNom);
                case FLUXARGENT -> this.saveFluxArgent(possessionAvecType.getFluxArgent(), patrimoineNom);
                case MATERIEL -> this.saveMateriel(possessionAvecType.getMateriel(), patrimoineNom);
                default ->
                        throw new IllegalArgumentException("Unknown possession type: " + possessionAvecType.getType());
            }
        });
        return possessionAvecTypes;
    }

    @Override
    public List<PossessionAvecType> findPatrimoinePossessions(String patrimoineNom, Long page, Long pageSize) {
        var selectedPatrimoines = getSelectedPatrimoines(patrimoineNom);
        var possessionsAvecTypes = new ArrayList<PossessionAvecType>();
        possessionsAvecTypes.addAll(getPossessionsAvecType(materielRepository.loadAllData(), selectedPatrimoines, PossessionType.MATERIEL));
        possessionsAvecTypes.addAll(getPossessionsAvecType(argentRepository.loadAllData(), selectedPatrimoines, PossessionType.ARGENT));
        possessionsAvecTypes.addAll(getPossessionsAvecType(fluxArgentRepository.loadAllData(), selectedPatrimoines, PossessionType.FLUXARGENT));
        var start = Math.toIntExact((page - 1) * pageSize);
        var end = Math.min(start + Math.toIntExact(pageSize), possessionsAvecTypes.size());
        return possessionsAvecTypes.subList(start, end);
    }

    @Override
    public PossessionAvecType findPatrimoinePossessionByNom(String patrimoineNom, String possessionNom) {
        var selectedPatrimoines = getSelectedPatrimoines(patrimoineNom);
        var foundPossession = findPossessionByName(materielRepository.loadAllData(), selectedPatrimoines, possessionNom, PossessionType.MATERIEL)
                .or(() -> findPossessionByName(argentRepository.loadAllData(), selectedPatrimoines, possessionNom, PossessionType.ARGENT))
                .or(() -> findPossessionByName(fluxArgentRepository.loadAllData(), selectedPatrimoines, possessionNom, PossessionType.FLUXARGENT));
        return foundPossession.orElse(null);
    }

    @Override
    public void deletePatrimoinePossessionByNom(String patrimoineNom, String possessionNom) {
        var patrimoine = patrimoineRepository.findPatrimoineByNom(patrimoineNom);
        if (patrimoine == null) {
            throw new ResourceNotFoundException("Patrimoine not found with name: " + patrimoineNom);
        }

        var deleted = deletePossession(materielRepository.loadAllData(), patrimoine, possessionNom, PossessionType.MATERIEL)
                || deletePossession(argentRepository.loadAllData(), patrimoine, possessionNom, PossessionType.ARGENT)
                || deletePossession(fluxArgentRepository.loadAllData(), patrimoine, possessionNom, PossessionType.FLUXARGENT);

        if (!deleted) {
            throw new ResourceNotFoundException("Possession not found with name: " + possessionNom);
        }
    }

    private <T extends Possession> boolean deletePossession(
            List<T> possessions,
            Patrimoine patrimoine,
            String possessionNom,
            PossessionType type
    ) {
        var possessionToDelete = possessions.stream()
                .filter(possession -> patrimoine.equals(possession.getPatrimoine()) &&
                        possessionNom.equals(possession.getNom()))
                .findFirst();

        if (possessionToDelete.isPresent()) {
            switch (type) {
                case MATERIEL:
                    materielRepository.delete((Materiel) possessionToDelete.get());
                    break;
                case ARGENT:
                    argentRepository.delete((Argent) possessionToDelete.get());
                    break;
                case FLUXARGENT:
                    fluxArgentRepository.delete((FluxArgent) possessionToDelete.get());
                    break;
            }
            return true;
        }
        return false;
    }


    private Set<Patrimoine> getSelectedPatrimoines(String patrimoineNom) {
        return patrimoineRepository.loadAllData().stream()
                .filter(patrimoine -> patrimoineNom.equals(patrimoine.nom()))
                .collect(Collectors.toSet());
    }

    private <T extends Possession> List<PossessionAvecType> getPossessionsAvecType(
            List<T> possessions,
            Set<Patrimoine> selectedPatrimoines,
            PossessionType type
    ) {
        return possessions.stream()
                .filter(possession -> selectedPatrimoines.contains(possession.getPatrimoine()))
                .map(possession -> createPossessionAvecType(possession, type))
                .collect(Collectors.toList());
    }

    private <T extends Possession> Optional<PossessionAvecType> findPossessionByName(
            List<T> possessions,
            Set<Patrimoine> selectedPatrimoines,
            String possessionNom,
            PossessionType type
    ) {
        return possessions.stream()
                .filter(possession -> selectedPatrimoines.contains(possession.getPatrimoine()) &&
                        possessionNom.equals(possession.getNom()))
                .findFirst()
                .map(possession -> createPossessionAvecType(possession, type));
    }

    private PossessionAvecType createPossessionAvecType(Possession possession, PossessionType type) {
        var possessionAvecType = new PossessionAvecType();
        possessionAvecType.setType(type);
        switch (type) {
            case MATERIEL:
                possessionAvecType.setMateriel((Materiel) possession);
                break;
            case ARGENT:
                possessionAvecType.setArgent((Argent) possession);
                break;
            case FLUXARGENT:
                possessionAvecType.setFluxArgent((FluxArgent) possession);
                break;
        }
        return possessionAvecType;
    }

    private void saveMateriel(Materiel materiel, String patrimoineNom) {
        var patrimoine = patrimoineRepository.findPatrimoineByNom(patrimoineNom);
        if (patrimoine == null) {
            throw new ResourceNotFoundException("Patrimoine not found");
        }
        materiel.setPatrimoine(patrimoine);
        var foundMateriel = materielRepository.findMaterielByNom(materiel.getNom());
        if (foundMateriel == null) {
            materielRepository.create(materiel);
        } else {
            materielRepository.update(materiel, foundMateriel);
        }
    }

    private void saveFluxArgent(FluxArgent fluxArgent, String patrimoineNom) {
        var patrimoine = patrimoineRepository.findPatrimoineByNom(patrimoineNom);
        if (patrimoine == null) {
            throw new ResourceNotFoundException("Patrimoine not found");
        }
        fluxArgent.setPatrimoine(patrimoine);
        var foundFlux = fluxArgentRepository.findFluxArgentByNom(fluxArgent.getNom());
        if (foundFlux == null) {
            fluxArgentRepository.create(fluxArgent);
        } else {
            fluxArgentRepository.update(fluxArgent, foundFlux);
        }
        patrimoine.possessions().add(fluxArgent);
        patrimoineRepository.update(patrimoine, patrimoine);
    }

    private void saveArgent(Argent argent, String patrimoineNom) {
        var patrimoine = patrimoineRepository.findPatrimoineByNom(patrimoineNom);
        if (patrimoine == null) {
            throw new ResourceNotFoundException("Patrimoine not found");
        }
        argent.setPatrimoine(patrimoine);
        var foundArgent = argentRepository.findArgentByNom(argent.getNom());
        if (foundArgent == null) {
            argentRepository.create(argent);
        } else {
            argentRepository.update(argent, foundArgent);
        }
    }
}
