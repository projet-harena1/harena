package com.harena.api.service;

import com.harena.api.dto.responses.PossessionAvecType;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface PossessionService {
    List<PossessionAvecType> savePatrimoinePossessions(String patrimoineNom, List<PossessionAvecType> possessionAvecTypes);
    List<PossessionAvecType> getPatrimoinePossessions(String patrimoineNom, Long page, Long pageSize);
}