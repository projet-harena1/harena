package com.harena.api.endpoint.rest.controller;

import com.harena.api.dto.responses.RestPatrimoine;
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
    public List<RestPatrimoine> getPatrimoines(
            @RequestParam(name = "page") Long page,
            @RequestParam(name = "page_size") Long pageSize
    ) {
        return patrimoineService.findAllPatrimoines(page, pageSize);
    }

    @PutMapping
    public List<RestPatrimoine> crupdatePatrimoines(
            @RequestBody List<RestPatrimoine> restPatrimoines
    ) {
        return patrimoineService.savePatrimoines(restPatrimoines);
    }


    @GetMapping("/{nom_patrimoine}")
    public RestPatrimoine getPatrimoineByNom(@PathVariable(name = "nom_patrimoine") String patrimoineNom){
        return patrimoineService.findPatrimoineByNom(patrimoineNom);
    }

}
