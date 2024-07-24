package com.harena.api.service.impl;

import com.harena.api.model.Patrimoine;
import com.harena.api.repository.PatrimoineRepository;
import com.harena.api.service.PatrimoineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class PatrimoineServiceImpl implements PatrimoineService {
    private final PatrimoineRepository patrimoineRepository;

    @Override
    public List<Patrimoine> findAllPatrimoines(int page, int pageSize) {
        log.info("pageSize: {}, pages: {}", pageSize, page);
        return patrimoineRepository.findAllPatrimoines();
    }
}
