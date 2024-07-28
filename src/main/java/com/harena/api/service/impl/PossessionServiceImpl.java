package com.harena.api.service.impl;

import com.harena.api.dto.json.ArgentDataJsonFile;
import com.harena.api.dto.json.DeviseDataJsonFile;
import com.harena.api.dto.json.FluxArgentDataJsonFile;
import com.harena.api.dto.json.PossessionDataJsonFile;
import com.harena.api.model.Devise;
import com.harena.api.model.possession.Argent;
import com.harena.api.model.possession.FluxArgent;
import com.harena.api.model.possession.Materiel;
import com.harena.api.model.possession.Possession;
import com.harena.api.repository.PossessionRepository;
import com.harena.api.service.PossessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PossessionServiceImpl extends PossessionService {
    private final PossessionRepository possessionRepository;

    @Autowired
    public PossessionServiceImpl(PossessionRepository possessionRepository) {
        this.possessionRepository = possessionRepository;
    }

    @Override
    public List<PossessionDataJsonFile> findAllPossessions() {
        return possessionRepository.loadAllData();
    }

    @Override
    public Optional<PossessionDataJsonFile> createPossession(PossessionDataJsonFile possession) {
        return possessionRepository.create(possession);
    }

    @Override
    public Optional<PossessionDataJsonFile> updatePossession(PossessionDataJsonFile possession) {
        return possessionRepository.update(possession);
    }

    @Override
    public boolean deletePossession(Long id) {
        List<PossessionDataJsonFile> allPossessions = possessionRepository.loadAllData();
        Optional<PossessionDataJsonFile> possessionToDelete = allPossessions.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (possessionToDelete.isPresent()) {
            allPossessions.remove(possessionToDelete.get());
            possessionRepository.writeDataToFile(allPossessions);
            return true;
        }

        return false;
    }

    @Override
    public int calculateFutureValue(Long id, LocalDate futureDate) {
        Optional<PossessionDataJsonFile> possession = getPossessionById(id);
        if (possession.isPresent()) {
            Possession actualPossession = convertToDomain(possession.get());
            return actualPossession.valeurComptableFuture(futureDate);
        }
        throw new IllegalArgumentException("Possession not found for id: " + id);
    }

    private Optional<PossessionDataJsonFile> getPossessionById(Long id) {
        return possessionRepository.loadAllData().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    private Possession convertToDomain(PossessionDataJsonFile possessionData, ArgentDataJsonFile argentData) {
        if (possessionData == null) {
            return null;
        }

        // Identifiez le type de possession à partir des données JSON
        String type = String.valueOf(possessionData.getType());

        // Créez une instance de la classe correspondante
        switch (type) {
            case "ARGENT":
                return convertToArgent(argentData.ty);
            case "MATERIEL":
                return convertToMateriel(possessionData.getType());
            case "FLUX_ARGENT":
                return convertToFluxArgent(possessionData.getFluxArgent());
            default:
                throw new IllegalArgumentException("Type de possession non supporté: " + type);
        }
    }

    private Argent convertToArgent(PossessionDataJsonFile argentData) {
        if (argentData == null) {
            return null;
        }

        return new Argent(
                argentData.getType().name(),
                argentData.,
                argentData.getT(),
                argentData.getValeurComptable(),
                convertToDevise(argentData.getDevise())
        );
    }

    private Materiel convertToMateriel(MaterielDataJsonFile materielData) {
        if (materielData == null) {
            return null;
        }

        return new Materiel(
                materielData.getNom(),
                materielData.getT(),
                materielData.getValeurComptable(),
                materielData.getDateDAcquisition(),
                materielData.getTauxDAppreciationAnnuel(),
                convertToDevise(materielData.getDevise())
        );
    }

    private FluxArgent convertToFluxArgent(FluxArgentDataJsonFile fluxArgentData) {
        if (fluxArgentData == null) {
            return null;
        }

        return new FluxArgent(
                fluxArgentData.getNom(),
                convertToArgent(fluxArgentData.getArgent()),
                fluxArgentData.getDebut(),
                fluxArgentData.getFin(),
                fluxArgentData.getFluxMensuel(),
                fluxArgentData.getDateDOperation(),
                convertToDevise(fluxArgentData.getDevise())
        );
    }

    private Devise convertToDevise(DeviseDataJsonFile deviseData) {
        if (deviseData == null) {
            return null;
        }

        return new Devise(
                deviseData.getNom(),
                deviseData.getCode()
        );
    }

}
