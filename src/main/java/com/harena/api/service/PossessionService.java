package com.harena.api.service;

import com.harena.api.dto.responses.PossessionAvecType;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface PossessionService {
  List<PossessionAvecType> savePatrimoinePossessions(
      String patrimoineNom, List<PossessionAvecType> possessionAvecTypes);

  List<PossessionAvecType> findPatrimoinePossessions(
      String patrimoineNom, Long page, Long pageSize);

  PossessionAvecType findPatrimoinePossessionByNom(String patrimoineNom, String possessionNom);

  void deletePatrimoinePossessionByNom(String patrimoineNom, String possessionNom);
}
