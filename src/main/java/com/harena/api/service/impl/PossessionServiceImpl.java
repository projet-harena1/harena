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
import com.harena.api.service.PossessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        if (patrimoine == null){
            throw new ResourceNotFoundException(patrimoineNom + " does not exist");
        }
        possessionAvecTypes.forEach(possessionAvecType -> {
            switch (possessionAvecType.getType()) {
                case ARGENT -> this.saveArgent(possessionAvecType.getArgent(),patrimoineNom);
                case FLUXARGENT -> this.saveFluxArgent(possessionAvecType.getFluxArgent(), patrimoineNom);
                case MATERIEL -> this.saveMateriel(possessionAvecType.getMateriel(), patrimoineNom);
                default -> throw new IllegalArgumentException("Unknown possession type: " + possessionAvecType.getType());
            }
        });
        return possessionAvecTypes;
    }

    @Override
    public List<PossessionAvecType> findPatrimoinePossessions(String patrimoineNom, Long page, Long pageSize) {
        List<PossessionAvecType> possessionAvecTypes = new ArrayList<>();
        Patrimoine patrimoine = patrimoineRepository.findPatrimoineByNom(patrimoineNom);
        if (patrimoine != null && patrimoine.possessions() != null) {
            possessionAvecTypes = patrimoine.possessions().stream()
                    .map(this::mapToPossessionAvecType)
                    .skip((page - 1) * pageSize)
                    .limit(pageSize)
                    .collect(Collectors.toList());
        }

        return possessionAvecTypes;
    }

    private PossessionAvecType mapToPossessionAvecType(Object possession) {
        PossessionAvecType possessionAvecType = new PossessionAvecType();
        if (possession instanceof Argent argent) {
            possessionAvecType.setType(PossessionType.ARGENT);
            possessionAvecType.setArgent(argent);
        } else if (possession instanceof Materiel materiel) {
            possessionAvecType.setType(PossessionType.MATERIEL);
            possessionAvecType.setMateriel(materiel);
        } else if (possession instanceof FluxArgent fluxArgent) {
            possessionAvecType.setType(PossessionType.FLUXARGENT);
            possessionAvecType.setFluxArgent(fluxArgent);
        }

        return possessionAvecType;
    }

    private void saveMateriel(Materiel materiel, String patrimoineNom) {
        var patrimoine = patrimoineRepository.findPatrimoineByNom(patrimoineNom);
        if (patrimoine == null){
            throw new ResourceNotFoundException("Patrimoine not found");
        }
        materiel.setPatrimoine(patrimoine);
        var foundMateriel = materielRepository.findMaterielByNom(materiel.getNom());
        if (foundMateriel == null){
            materielRepository.create(materiel);
        }
        materielRepository.update(materiel, foundMateriel);
    }

    private void saveFluxArgent(FluxArgent fluxArgent,  String patrimoineNom) {
        var patrimoine = patrimoineRepository.findPatrimoineByNom(patrimoineNom);
        if (patrimoine == null){
            throw new ResourceNotFoundException("Patrimoine not found");
        }
        fluxArgent.setPatrimoine(patrimoine);
        var foundFlux = fluxArgentRepository.findFluxArgentByNom(fluxArgent.getNom());
        if (foundFlux == null){
            fluxArgentRepository.create(fluxArgent);
        }
        fluxArgentRepository.update(fluxArgent, foundFlux);
    }

    private void saveArgent(Argent argent, String patrimoineNom) {
        var patrimoine = patrimoineRepository.findPatrimoineByNom(patrimoineNom);
        if (patrimoine == null){
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
