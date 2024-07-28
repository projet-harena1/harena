package com.harena.api.service;

import com.harena.api.dto.responses.RestPatrimoine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatrimoineService {
    List<RestPatrimoine> findAllPatrimoines(Long page, Long pageSize);
    List<RestPatrimoine> savePatrimoines(List<RestPatrimoine> restPatrimoines);
    RestPatrimoine findPatrimoineByNom(String patrimoineNom);
}
