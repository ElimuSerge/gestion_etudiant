package com.gestion_etudiant.service;

import org.springframework.stereotype.Service;
import com.gestion_etudiant.entity.Etudiant;
import com.gestion_etudiant.repository.EtudiantRepository;

import java.util.List;

@Service
public class EtudiantService {

    private final EtudiantRepository repository;

    public EtudiantService(EtudiantRepository repository) {
        this.repository = repository;
    }

    public Etudiant enregistrer(Etudiant e) {
        return repository.save(e);
    }

    public List<Etudiant> lister() {
        return repository.findAll();
    }

    public Etudiant obtenir(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Etudiant modifier(Long id, Etudiant nouveau) {
        return repository.findById(id)
            .map(e -> {
                e.setNom(nouveau.getNom());
                e.setPrenom(nouveau.getPrenom());
                e.setEmail(nouveau.getEmail());
                return repository.save(e);
            })
            .orElse(null);
    }

    public void supprimer(Long id) {
        repository.deleteById(id);
    }
}

