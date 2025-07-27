package com.gestion_etudiant.service;

import com.gestion_etudiant.entity.Etudiant;
import com.gestion_etudiant.repository.EtudiantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EtudiantService {

    private final EtudiantRepository repository;

    public EtudiantService(EtudiantRepository repository) {
        this.repository = repository;
    }

    public Etudiant enregistrer(Etudiant e) {
        // Vérifier si l'email existe déjà
        if (repository.existsByEmail(e.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'email est déjà utilisé");
        }
        return repository.save(e);
    }

    public List<Etudiant> lister() {
        return repository.findAll();
    }

    public Etudiant obtenir(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Étudiant non trouvé"));
    }

    public Etudiant modifier(Long id, Etudiant nouveau) {
        return repository.findById(id)
                .map(e -> {
                    // Vérifier si l'email est modifié et s'il existe déjà
                    if (!e.getEmail().equals(nouveau.getEmail()) && repository.existsByEmail(nouveau.getEmail())) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'email est déjà utilisé");
                    }
                    e.setNom(nouveau.getNom());
                    e.setPrenom(nouveau.getPrenom());
                    e.setEmail(nouveau.getEmail());
                    return repository.save(e);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Étudiant non trouvé"));
    }

    public void supprimer(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Étudiant non trouvé");
        }
        repository.deleteById(id);
    }
}