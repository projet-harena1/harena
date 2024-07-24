package com.harena.api.service;

import com.harena.api.model.Patrimoine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatrimoineService {
    List<Patrimoine> findAllPatrimoines(int page, int pageSize);
}
