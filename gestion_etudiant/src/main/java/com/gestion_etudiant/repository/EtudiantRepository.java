package com.gestion_etudiant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion_etudiant.entity.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

}