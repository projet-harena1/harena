package com.harena.api.endpoint.rest.controller;

import com.harena.api.model.Patrimoine;
import com.harena.api.service.PatrimoineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/patrimoines")
@RequiredArgsConstructor
public class PatrimoineController {

    private final PatrimoineService patrimoineService;

    @GetMapping
    public List<Patrimoine> getPatrimoines(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "page_size", defaultValue = "10") int pageSize
    ) {
        return patrimoineService.findAllPatrimoines(page, pageSize);
    }
}
