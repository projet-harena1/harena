package com.harena.api.endpoint.rest.controller;

import com.harena.api.dto.PatrimoineSummarized;
import com.harena.api.service.PatrimoineService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/patrimoines")
public class PatrimoineController {

  private final PatrimoineService patrimoineService;

  public PatrimoineController(PatrimoineService patrimoineService) {
    this.patrimoineService = patrimoineService;
  }

  @GetMapping
  private List<PatrimoineSummarized> getPatrimoines(
      @RequestParam(name = "page") Integer page,
      @RequestParam(name = "page_size") Integer pageSize) {
    return patrimoineService.findAllPatrimoines(page, pageSize);
  }

  @PutMapping
  public List<PatrimoineSummarized> crupdatePatrimoines(
      @RequestBody List<PatrimoineSummarized> patrimoines) {
    return patrimoineService.crupdatePatrimoines(patrimoines);
  }

  @GetMapping("/{nom_patrimoine}:")
  public PatrimoineSummarized getPatrimoineByNom(
      @PathVariable(name = "nom_patrimoine") String patrimoineName) {
    return patrimoineService.findPatrimoineByNom(patrimoineName);
  }
}
