package tn.esprit.atelierrest1.services;

import tn.esprit.atelierrest1.entities.Etudiant;
import tn.esprit.atelierrest1.entities.Option;

import java.util.ArrayList;
import java.util.List;

public class EtudiantService {

    private static final List<Etudiant> etudiants = new ArrayList<>();

    private final OptionService optionService = new OptionService();

    static {
        Option informatique = new Option(
                1,
                "Informatique",
                "Informatique",
                "M. Responsable Informatique",
                30,
                1,
                30
        );

        Option mathematiques = new Option(
                2,
                "Mathématiques",
                "Mathématiques",
                "Mme Responsable Mathématiques",
                25,
                1,
                25
        );

        etudiants.add(new Etudiant(
                "I001",
                "Doe",
                "Jean",
                informatique,
                2023,
                "jean.doe@example.com"
        ));

        etudiants.add(new Etudiant(
                "I002",
                "Smith",
                "Alice",
                informatique,
                2022,
                "alice.smith@example.com"
        ));

        etudiants.add(new Etudiant(
                "I003",
                "Durand",
                "Pierre",
                mathematiques,
                2023,
                "pierre.durand@example.com"
        ));
    }

    public List<Etudiant> getAllEtudiants() {
        return etudiants;
    }

    public Etudiant getEtudiantById(String identifiant) {
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getIdentifiant().equals(identifiant)) {
                return etudiant;
            }
        }

        return null;
    }

    public boolean addEtudiant(Etudiant etudiant) {
        if (getEtudiantById(etudiant.getIdentifiant()) != null) {
            return false;
        }

        if (etudiant.getOption() == null) {
            return false;
        }

        Option option = optionService.getOptionById(
                etudiant.getOption().getCodeOption()
        );

        if (option == null) {
            return false;
        }

        etudiant.setOption(option);
        etudiants.add(etudiant);

        return true;
    }

public boolean updateEtudiant(String identifiant, Etudiant newEtudiant) {
    Etudiant existingEtudiant = getEtudiantById(identifiant);

    if (existingEtudiant == null) {
        return false;
    }

    if (newEtudiant.getOption() == null) {
        return false;
    }

    Option option = optionService.getOptionById(
            newEtudiant.getOption().getCodeOption()
    );

    if (option == null) {
        return false;
    }

    existingEtudiant.setNom(newEtudiant.getNom());
    existingEtudiant.setPrenom(newEtudiant.getPrenom());
    existingEtudiant.setOption(option);
    existingEtudiant.setAnneeEtude(newEtudiant.getAnneeEtude());
    existingEtudiant.setEmail(newEtudiant.getEmail());

    return true;
}

    public boolean deleteEtudiant(String identifiant) {
        Etudiant etudiant = getEtudiantById(identifiant);

        if (etudiant == null) {
            return false;
        }

        etudiants.remove(etudiant);
        return true;
    }

    public List<Etudiant> getEtudiantsByOption(int codeOption) {
        Option option = optionService.getOptionById(codeOption);

        if (option == null) {
            return null;
        }

        List<Etudiant> result = new ArrayList<>();

        for (Etudiant etudiant : etudiants) {
            if (etudiant.getOption() != null &&
                    etudiant.getOption().getCodeOption() == codeOption) {
                result.add(etudiant);
            }
        }

        return result;
    }
}