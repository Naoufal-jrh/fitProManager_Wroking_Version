package Controller;



public class Membre {
    private int idPersonne;
    private String cin;
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private String tel;
    private String sexe;
    private Date dateNaissance;
    private Date dateAdherence;

    public Membre(String nom, String cin, String prenom, String email, String adresse, String tel, String sexe, Date dateNaissance,Date dateAdherence) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.tel = tel;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.dateAdherence = dateAdherence;
    }
    public Membre(int id ,String nom,String cin , String prenom, String email, String adresse, String tel, String sexe, Date dateNaissance,Date dateAdherence) {
        this.idPersonne = id;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.tel = tel;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.dateAdherence = dateAdherence;
    }

    public Membre(Membre mb){
        this.idPersonne = mb.idPersonne;
        this.cin = mb.cin;
        this.nom = mb.nom;
        this.prenom = mb.prenom;
        this.email = mb.email;
        this.adresse = mb.adresse;
        this.tel = mb.tel;
        this.sexe = mb.sexe;
        this.dateNaissance = mb.dateNaissance;
        this.dateAdherence = mb.dateAdherence;

    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTel() {
        return tel;
    }

    public String getSexe() {
        return sexe;
    }

    public void setIdPersonne(int idPerssone) {
        this.idPersonne = idPerssone;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public Date getDateAdherence() {
        return dateAdherence;
    }

    public void setDateAdherence(Date dateAdherence) {
        this.dateAdherence = dateAdherence;
    }

}
