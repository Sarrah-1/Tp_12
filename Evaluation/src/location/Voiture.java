package location;
public class Voiture extends Vehicule {
    private int nbPlaces;

    public Voiture(String immatriculation, String marque, double tarifJournalier, int nbPlaces) {
        super(immatriculation, marque, tarifJournalier);
        this.nbPlaces = nbPlaces;
    }

    @Override
    public double calculerCout(int nbJours) {
        if (nbPlaces <= 5) {
            return tarifJournalier * nbJours;
        }
        return (tarifJournalier + 50) * nbJours;
    }

    @Override
    public void afficherDetails() {
        System.out.println("Type : Voiture");
        super.afficherDetails();
        System.out.println("Nombre de places : " + nbPlaces);
    }
}
