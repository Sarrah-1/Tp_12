package location;
public class Location {
    private int nbJours;
    private Client client;
    private Vehicule vehicule;

    public Location(Client client, Vehicule vehicule, int nbJours) {
        this.client = client;
        this.vehicule = vehicule;
        this.nbJours = nbJours;
    }

    public double calculerMontant() {
        return vehicule.calculerCout(nbJours);
    }

    public void afficherRecapitulatif() {
        System.out.println("----- Récapitulatif de la location -----");
        System.out.println(client);
        vehicule.afficherDetails();
        System.out.println("Durée : " + nbJours + " jour(s)");
        System.out.println("Montant total : " + calculerMontant() + " DH");
        System.out.println("----------------------------------------");
    }
}
