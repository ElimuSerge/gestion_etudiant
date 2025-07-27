package com.gestion_etudiant.repository;

import com.gestion_etudiant.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    boolean existsByEmail(String email);
}