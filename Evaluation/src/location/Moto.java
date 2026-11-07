package location;
public class Moto extends Vehicule {
    private int cylindree;

    public Moto(String immatriculation, String marque, double tarifJournalier, int cylindree) {
        super(immatriculation, marque, tarifJournalier);
        this.cylindree = cylindree;
    }

    @Override
    public double calculerCout(int nbJours) {
        double total = tarifJournalier * nbJours;
        if (nbJours >= 5) {
            total = total * 0.9;
        }
        return total;
    }

    @Override
    public void afficherDetails() {
        System.out.println("Type : Moto");
        super.afficherDetails();
        System.out.println("Cylindrée : " + cylindree + " cm³");
    }
}
