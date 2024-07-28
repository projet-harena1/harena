package com.harena.api.endpoint.rest.controller;

import com.harena.api.dto.responses.PossessionAvecType;
import com.harena.api.dto.responses.RestPatrimoine;
import com.harena.api.service.PossessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/patrimoines")
@RequiredArgsConstructor
public class PossessionController {
    private final PossessionService possessionService;

    @PutMapping("/{nom_patrimoine}/possessions")
    public List<PossessionAvecType> crupdatePatrimoinePossessions(
            @PathVariable(name = "nom_patrimoine", required = false) String patrimoineNom,
            @RequestBody List<PossessionAvecType> possessionAvecTypes
            ){
        return possessionService.savePatrimoinePossessions(patrimoineNom, possessionAvecTypes);
    }
    @GetMapping("/{nom_patrimoine}/possessions")
    public List<PossessionAvecType> getPatrimoinePossessions(
            @RequestParam(name = "page") Long page,
            @RequestParam(name = "page_size") Long pageSize,
            @PathVariable (name = "nom_patrimoine")String patrimoineNom) {
        return possessionService.getPatrimoinePossessions(patrimoineNom, page, pageSize);
    }

}