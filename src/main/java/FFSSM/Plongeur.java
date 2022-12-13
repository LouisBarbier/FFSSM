package FFSSM;

import java.time.LocalDate;

public class Plongeur extends Personne{

    public Licence licence;
    public  int niveau;

    public Plongeur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance,int niveau) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance);
        this.niveau=niveau;
    }

    public Plongeur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance, int niveau, Licence licence) {
        this(numeroINSEE, nom, prenom, adresse, telephone, naissance,niveau);
        this.licence=licence;
    }

    public Licence getLicence() {
        return licence;
    }

    public void setLicence (Licence lic){
        licence=lic;
    }
}
