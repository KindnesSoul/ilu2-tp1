package histoire;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;
import villagegaulois.VillageSansChefException;
public class ScénarioTest {
	public static void main(String[] args) throws VillageSansChefException {
		Village village = new Village("le village des irréductibles", 10, 5);
		Gaulois bonemine = new Gaulois("Bonemine", 7);
		village.ajouterHabitant(bonemine);
		village.afficherVillageois();
		System.out.println("Fin du test");
}}
