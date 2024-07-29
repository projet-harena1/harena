package com.harena.api.service;

import com.harena.api.dto.responses.RestPatrimoine;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface PatrimoineService {
  List<RestPatrimoine> findAllPatrimoines(Long page, Long pageSize);

  List<RestPatrimoine> savePatrimoines(List<RestPatrimoine> restPatrimoines);

  RestPatrimoine findPatrimoineByNom(String patrimoineNom);

  List<RestPatrimoine> projectionFuture(LocalDate futureDate);
}
