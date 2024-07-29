package com.harena.api.endpoint.rest.controller;

import com.harena.api.dto.responses.PossessionAvecType;
import com.harena.api.service.PossessionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/patrimoines")
@RequiredArgsConstructor
public class PossessionController {
  private final PossessionService possessionService;

  @PutMapping("/{nom_patrimoine}/possessions")
  public List<PossessionAvecType> crupdatePatrimoinePossessions(
      @PathVariable(name = "nom_patrimoine", required = false) String patrimoineNom,
      @RequestBody List<PossessionAvecType> possessionAvecTypes) {
    return possessionService.savePatrimoinePossessions(patrimoineNom, possessionAvecTypes);
  }

  @GetMapping("/{nom_patrimoine}/possessions")
  public List<PossessionAvecType> getPatrimoinePossessions(
      @RequestParam(name = "page") Long page,
      @RequestParam(name = "page_size") Long pageSize,
      @PathVariable(name = "nom_patrimoine") String patrimoineNom) {
    return possessionService.findPatrimoinePossessions(patrimoineNom, page, pageSize);
  }

  @GetMapping("/{nom_patrimoine}/possessions/{nom_possession}")
  public PossessionAvecType getPatrimoinePossessionByNom(
      @PathVariable(name = "nom_patrimoine") String patrimoineNom,
      @PathVariable(name = "nom_possession") String possessionNom) {
    return possessionService.findPatrimoinePossessionByNom(patrimoineNom, possessionNom);
  }

  @DeleteMapping("/{nom_patrimoine}/possessions/{nom_possession}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePatrimoinePossessionByNom(
      @PathVariable(name = "nom_patrimoine") String patrimoineNom,
      @PathVariable(name = "nom_possession") String possessionNom) {
    possessionService.deletePatrimoinePossessionByNom(patrimoineNom, possessionNom);
  }
}
