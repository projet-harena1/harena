package com.harena.api.service;

import com.harena.api.dto.PatrimoineSummarized;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface PatrimoineService {
  List<PatrimoineSummarized> findAllPatrimoines(Integer page, Integer pageSize);

  List<PatrimoineSummarized> crupdatePatrimoines(List<PatrimoineSummarized> patrimoines);

  PatrimoineSummarized findPatrimoineByNom(String patrimoineName);
}
