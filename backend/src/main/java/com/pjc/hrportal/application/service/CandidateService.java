package com.pjc.hrportal.application.service;

import com.pjc.hrportal.application.dto.CandidateDTO;
import com.pjc.hrportal.domain.model.Candidate;
import com.pjc.hrportal.domain.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    private final CandidateRepository repo;

    public CandidateService(CandidateRepository repo) {
        this.repo = repo;
    }

    public List<Candidate> list() {
        return repo.findAll();
    }

    public Candidate create(CandidateDTO dto) {
        Candidate c = new Candidate();
        c.setName(dto.getName());
        c.setEmail(dto.getEmail());
        c.setRole(dto.getRole());
        c.setSeniority(dto.getSeniority());
        return repo.save(c);
    }

    public Candidate get(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Candidate update(Long id, CandidateDTO dto) {
        Candidate c = repo.findById(id).orElse(null);
        if (c == null) return null;
        c.setName(dto.getName());
        c.setEmail(dto.getEmail());
        c.setRole(dto.getRole());
        c.setSeniority(dto.getSeniority());
        return repo.save(c);
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
