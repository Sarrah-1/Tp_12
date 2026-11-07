package location;
public class Main {
    public static void main(String[] args) {
        Client client1 = new Client(1, "Ouaday", "Sara");
        Client client2 = new Client(2, "Hamdi", "Amine");

        Voiture voiture1 = new Voiture("11111-A-1", "Renault", 300, 5);
        Voiture voiture2 = new Voiture("22222-B-2", "Peugeot", 400, 7);
        Moto moto1 = new Moto("33333-C-3", "Yamaha", 150, 125);

        Vehicule[] vehicules = new Vehicule[3];
        vehicules[0] = voiture1;
        vehicules[1] = voiture2;
        vehicules[2] = moto1;

        System.out.println("LISTE DES VEHICULES");
        for (int i = 0; i < vehicules.length; i++) {
            vehicules[i].afficherDetails();
            System.out.println();
        }

        Location[] locations = new Location[3];
        locations[0] = new Location(client1, voiture1, 3);
        locations[1] = new Location(client2, voiture2, 2);
        locations[2] = new Location(client1, moto1, 6);

        System.out.println("LISTE DES LOCATIONS");
        double total = 0;
        for (int i = 0; i < locations.length; i++) {
            locations[i].afficherRecapitulatif();
            total += locations[i].calculerMontant();
        }

        System.out.println();
        System.out.println("Montant total de toutes les locations : " + total + " DH");
    }
}
