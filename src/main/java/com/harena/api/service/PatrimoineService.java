package com.harena.api.service;

import com.harena.api.dto.responses.RestPatrimoine;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface PatrimoineService {
    List<RestPatrimoine> savePatrimoines(List<RestPatrimoine> restPatrimoines);

    RestPatrimoine findPatrimoineByNom(String patrimoineNom);

    List<RestPatrimoine> projectionFuture(LocalDate futureDate);
}
