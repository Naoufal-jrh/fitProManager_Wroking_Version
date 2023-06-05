package Controller;

public class Date {
    private int jour;
    private int mois;
    private int année;

    public Date(int jour, int mois, int année) {
        this.jour = jour;
        this.mois = mois;
        this.année = année;
    }


    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnée() {
        return année;
    }

    public void setAnnée(int année) {
        this.année = année;
    }

    @Override
    public String toString() {
        return jour+"/"+mois+"/"+année;
    }
}
