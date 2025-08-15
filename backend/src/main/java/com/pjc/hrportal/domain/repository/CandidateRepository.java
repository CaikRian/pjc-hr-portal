package com.pjc.hrportal.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pjc.hrportal.domain.model.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> { }
