package com.gestion_etudiant.controller;

import com.gestion_etudiant.entity.Etudiant;
import com.gestion_etudiant.service.EtudiantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    private final EtudiantService service;

    public EtudiantController(EtudiantService service) {
        this.service = service;
    }

    @PostMapping
    public Etudiant ajouterEtudiant(@RequestBody Etudiant e) {
        return service.enregistrer(e);
    }

    @GetMapping
    public List<Etudiant> getTous() {
        return service.lister();
    }

    @GetMapping("/{id}")
    public Etudiant getParId(@PathVariable Long id) {
        return service.obtenir(id);
    }

    @PutMapping("/{id}")
    public Etudiant modifierEtudiant(@PathVariable Long id, @RequestBody Etudiant e) {
        return service.modifier(id, e);
    }

    @DeleteMapping("/{id}")
    public void supprimerEtudiant(@PathVariable Long id) {
        service.supprimer(id);
    }
}

