package Controller;


public class Expiration {
    private int idExpiration;
    private Membre membre;
    private Categorie categorie;
    private Date dateExpiration;

    public Expiration(int idExpiration, Membre membre, Categorie categorie, Date dateExpiration) {
        this.idExpiration = idExpiration;
        this.membre = membre;
        this.categorie = categorie;
        this.dateExpiration = dateExpiration;
    }
    public Expiration(Membre membre, Categorie categorie) {
        this.membre = membre;
        this.categorie = categorie;
    }

    public int getIdExpiration() {
        return idExpiration;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
}
