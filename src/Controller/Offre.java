package Controller;


public class Offre {
    private int idOffre;
    private String nomOffre;
    private String prixOffre;
    private int dureeOffre;
    private Categorie categorie;

    public Offre(int idOffre, String nomOffre, String prixOffre, int dureeOffre, Categorie categorie) {
        this.idOffre = idOffre;
        this.nomOffre = nomOffre;
        this.prixOffre = prixOffre;
        this.dureeOffre = dureeOffre;
        this.categorie = categorie;
    }
    public Offre(String nomOffre, String prixOffre, int dureeOffre, Categorie categorie) {
        this.nomOffre = nomOffre;
        this.prixOffre = prixOffre;
        this.dureeOffre = dureeOffre;
        this.categorie = categorie;
    }
    public Offre(Offre offre) {
        this.idOffre = offre.getIdOffre();
        this.nomOffre = offre.getNomOffre();
        this.prixOffre = offre.getPrixOffre();
        this.dureeOffre = offre.getDureeOffre();
        this.categorie = offre.getCategorie();
    }


    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public String getNomOffre() {
        return nomOffre;
    }

    public void setNomOffre(String nomOffre) {
        this.nomOffre = nomOffre;
    }

    public String getPrixOffre() {
        return prixOffre;
    }

    public void setPrixOffre(String prixOffre) {
        this.prixOffre = prixOffre;
    }

    public int getDureeOffre() {
        return dureeOffre;
    }

    public void setDureeOffre(int dureeOffre) {
        this.dureeOffre = dureeOffre;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}
