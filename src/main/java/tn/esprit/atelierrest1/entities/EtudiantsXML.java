package tn.esprit.atelierrest1.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "etudiants")
public class EtudiantsXML {

    private List<Etudiant> etudiants;

    public EtudiantsXML() {
    }

    public EtudiantsXML(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    @XmlElement(name = "etudiant")
    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }
}