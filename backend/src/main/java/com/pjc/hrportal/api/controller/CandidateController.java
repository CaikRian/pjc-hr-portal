package com.pjc.hrportal.api.controller;

import com.pjc.hrportal.application.dto.CandidateDTO;
import com.pjc.hrportal.application.service.CandidateService;
import com.pjc.hrportal.domain.model.Candidate;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CandidateController {

    private final CandidateService service;

    public CandidateController(CandidateService service) {
        this.service = service;
    }

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok().body(java.util.Map.of("status", "ok"));
    }

    @GetMapping("/candidates")
    public List<Candidate> list() {
        return service.list();
    }

    @PostMapping("/candidates")
    public ResponseEntity<Candidate> create(@RequestBody @Valid CandidateDTO dto) {
        Candidate created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/candidates/" + created.getId())).body(created);
    }

    @GetMapping("/candidates/{id}")
    public ResponseEntity<Candidate> get(@PathVariable Long id) {
        Candidate c = service.get(id);
        return (c == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(c);
    }

    @PutMapping("/candidates/{id}")
    public ResponseEntity<Candidate> update(@PathVariable Long id, @RequestBody @Valid CandidateDTO dto) {
        Candidate c = service.update(id, dto);
        return (c == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(c);
    }

    @DeleteMapping("/candidates/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean ok = service.delete(id);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
