package Controller;



public class Inscription {
    private int idInscription;
    private Offre offre;
    private Membre membre;
    private Date dateInscription;

    public Inscription(int idInscription, Offre offre, Membre membre, Date dateInscription) {
        this.idInscription = idInscription;
        this.offre = offre;
        this.membre = membre;
        this.dateInscription = dateInscription;
    }
    public Inscription(Offre offre, Membre membre, Date dateInscription) {
        this.offre = offre;
        this.membre = membre;
        this.dateInscription = dateInscription;
    }

    public int getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(int idInscription) {
        this.idInscription = idInscription;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }
}
