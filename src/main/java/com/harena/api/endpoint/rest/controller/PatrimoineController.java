package com.harena.api.endpoint.rest.controller;

import com.harena.api.dto.responses.RestPatrimoine;
import com.harena.api.service.PatrimoineService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/patrimoines")
@RequiredArgsConstructor
public class PatrimoineController {
  private final PatrimoineService patrimoineService;

  @GetMapping
  public List<RestPatrimoine> getPatrimoines(
      @RequestParam(name = "page", required = false) Long page,
      @RequestParam(name = "page_size", required = false) Long pageSize) {
    return patrimoineService.findAllPatrimoines(page, pageSize);
  }

  @PutMapping
  public List<RestPatrimoine> crupdatePatrimoines(
      @RequestBody List<RestPatrimoine> restPatrimoines) {
    return patrimoineService.savePatrimoines(restPatrimoines);
  }

  @GetMapping("/{nom_patrimoine}")
  public RestPatrimoine getPatrimoineByNom(
      @PathVariable(name = "nom_patrimoine") String patrimoineNom) {
    return patrimoineService.findPatrimoineByNom(patrimoineNom);
  }

  @GetMapping("/projection")
  public List<RestPatrimoine> getProjectionFuture(
      @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          LocalDate futureDate) {
    return patrimoineService.projectionFuture(futureDate);
  }
}
