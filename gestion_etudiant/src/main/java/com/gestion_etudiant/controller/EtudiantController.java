package com.gestion_etudiant.controller;

import com.gestion_etudiant.dto.EtudiantDTO;
import com.gestion_etudiant.entity.Etudiant;
import com.gestion_etudiant.service.EtudiantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/etudiants")
public class EtudiantController {

    private final EtudiantService service;

    public EtudiantController(EtudiantService service) {
        this.service = service;
    }

    @Operation(summary = "Ajouter un nouvel étudiant", description = "Crée un nouvel étudiant dans la base de données.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Étudiant créé avec succès"),
        @ApiResponse(responseCode = "400", description = "Données d'entrée invalides")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Etudiant ajouterEtudiant(@Valid @RequestBody EtudiantDTO dto) {
        Etudiant etudiant = new Etudiant();
        BeanUtils.copyProperties(dto, etudiant);
        return service.enregistrer(etudiant);
    }

    @Operation(summary = "Lister tous les étudiants", description = "Récupère la liste de tous les étudiants.")
    @ApiResponse(responseCode = "200", description = "Liste des étudiants récupérée avec succès")
    @GetMapping
    public List<Etudiant> getTous() {
        return service.lister();
    }

    @Operation(summary = "Obtenir un étudiant par ID", description = "Récupère les détails d'un étudiant spécifique par son ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Étudiant trouvé"),
        @ApiResponse(responseCode = "404", description = "Étudiant non trouvé")
    })
    @GetMapping("/{id}")
    public Etudiant getParId(@PathVariable Long id) {
        Etudiant etudiant = service.obtenir(id);
        if (etudiant == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Étudiant non trouvé");
        }
        return etudiant;
    }

    @Operation(summary = "Modifier un étudiant", description = "Met à jour les informations d'un étudiant existant.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Étudiant modifié avec succès"),
        @ApiResponse(responseCode = "404", description = "Étudiant non trouvé"),
        @ApiResponse(responseCode = "400", description = "Données d'entrée invalides")
    })
    @PutMapping("/{id}")
    public Etudiant modifierEtudiant(@PathVariable Long id, @Valid @RequestBody EtudiantDTO dto) {
        Etudiant etudiant = service.obtenir(id);
        if (etudiant == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Étudiant non trouvé");
        }
        BeanUtils.copyProperties(dto, etudiant);
        return service.modifier(id, etudiant);
    }

    @Operation(summary = "Supprimer un étudiant", description = "Supprime un étudiant par son ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Étudiant supprimé avec succès"),
        @ApiResponse(responseCode = "404", description = "Étudiant non trouvé")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimerEtudiant(@PathVariable Long id) {
        Etudiant etudiant = service.obtenir(id);
        if (etudiant == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Étudiant non trouvé");
        }
        service.supprimer(id);
    }
}