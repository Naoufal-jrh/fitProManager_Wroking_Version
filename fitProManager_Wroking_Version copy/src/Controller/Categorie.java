package Controller;

public class Categorie {
    private int idCategorie;
    private String nomCategorie;
    private  int nbrMembres;

    public Categorie(int idCategorie, String nomCategorie,int nbrMembres) {
        this.idCategorie = idCategorie;
        this.nomCategorie = nomCategorie;
        this.nbrMembres=nbrMembres;
    }
    public Categorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public int getNbrMembres() {
        return nbrMembres;
    }

    public void setNbrMembres(int nbrMembres) {
        this.nbrMembres = nbrMembres;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }
}
