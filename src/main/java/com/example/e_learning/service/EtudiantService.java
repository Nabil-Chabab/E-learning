package com.example.e_learning.service;

import com.example.e_learning.model.Etudiant;
import com.example.e_learning.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    // Récupérer tous les étudiants
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    // Récupérer un étudiant par ID
    public Etudiant getEtudiantById(String id) {
        return etudiantRepository.findById(id).orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
    }

    // Ajouter un nouvel étudiant
    public Etudiant addEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    // Modifier un étudiant existant
    public Etudiant updateEtudiant(String id, Etudiant etudiant) {
        Optional<Etudiant> existingEtudiant = etudiantRepository.findById(id);
        if (existingEtudiant.isPresent()) {
            Etudiant updatedEtudiant = existingEtudiant.get();
            // Mettre à jour tous les champs, y compris le mot de passe
            updatedEtudiant.setCin(etudiant.getCin());
            updatedEtudiant.setCne(etudiant.getCne());
            updatedEtudiant.setImageURL(etudiant.getImageURL());
            updatedEtudiant.setModules(etudiant.getModules());
            updatedEtudiant.setApogee(etudiant.getApogee());
            updatedEtudiant.setEmail(etudiant.getEmail());
            updatedEtudiant.setNom(etudiant.getNom());
            updatedEtudiant.setPrenom(etudiant.getPrenom());
            updatedEtudiant.setMotDePasse(etudiant.getMotDePasse()); // IMPORTANT
            return etudiantRepository.save(updatedEtudiant);
        } else {
            throw new RuntimeException("Étudiant non trouvé");
        }
    }


    // Supprimer un étudiant
    public String deleteEtudiant(String id) {
        etudiantRepository.deleteById(id);
        return "Étudiant supprimé avec succès";
    }
}