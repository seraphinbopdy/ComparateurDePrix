package com.comparateur.esiea.comparateurprix.Entity;

public class AfficheTrajet {


    private String villeD;
    private String villeA;
    private String dateDepart;
    private String dateArrive;
    private double Prix;

    public AfficheTrajet(String villeD, String villeA, String dateDepart, String dateArrive, double prix) {
        this.villeD = villeD;
        this.villeA = villeA;
        this.dateDepart = dateDepart;
        this.dateArrive = dateArrive;
        Prix = prix;
    }

    public String getVilleD() {
        return villeD;
    }

    public String getVilleA() {
        return villeA;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public String getDateArrive() {
        return dateArrive;
    }

    public double getPrix() {
        return Prix;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    public void setDateArrive(String dateArrive) {
        this.dateArrive = dateArrive;
    }

    @Override
    public String toString() {
        return "AfficheTrajet{" +
                "villeD='" + villeD + '\'' +
                ", villeA='" + villeA + '\'' +
                ", dateDepart='" + dateDepart + '\'' +
                ", dateArrive='" + dateArrive + '\'' +
                ", Prix=" + Prix + "€" +
                '}';
    }
}
