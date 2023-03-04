package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;


public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	
	
	public Village(String nom, int nbVillageoisMaximum,int nbEtalMax) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche=new Marche(nbEtalMax);
		
	}
	
	private static class Marche {
		private   Etal[] etal;
		private  int nbEtal;
		
		//uwu//
		private Marche(int nbEtalMax) {
			this.nbEtal=nbEtalMax;
			etal= new Etal[nbEtalMax];
			for (int i=0; i<nbEtalMax;i++) {
				etal[i]= new Etal();}
			}
		
		
		public  void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit, int nbProduit) {
			etal[indiceEtal].occuperEtal(vendeur,produit,nbProduit);
			}
		
		public   int trouverEtalLibre() {
			for (int i=0; i<nbEtal;i++) {
				if (etal[i].isEtalOccupe()==false) {
					return (i);}	
				}
			return -1;
			}
			
		public  Etal[] trouverEtals(String produit) {
			int nb=0;
			for (int i=0; i<nbEtal;i++) {
				if (etal[i].isEtalOccupe()==true) { 
					if (etal[i].contientProduit(produit)==true) {
						nb++;}
					}
				}
			Etal[] tabEtal=new Etal[nb];
			nb=0;
			for (int i=0; i<nbEtal;i++) {
				if (etal[i].isEtalOccupe()==true) {
					if (etal[i].contientProduit(produit)==true) {
						tabEtal[nb]=etal[i];
						nb++;}}
				}
			return tabEtal;
			}
		
		public Etal trouverVendeur(Gaulois gaulois) {
			for (int i=0; i<nbEtal;i++) {
				if (etal[i].getVendeur()==gaulois) {
					return etal[i];}
			}
			return null;}
		
		public String afficherMarche() {
			int vide=0;
			for (int i=0; i<nbEtal;i++) {
				if (etal[i].isEtalOccupe()) {
					etal[i].afficherEtal();}
				else {
					vide++;
					}
				}
			return ("Il reste " + vide + "�tals non utilis�s dans le march�.\n");
		}
			
		
		
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom()+"cherche un endroit pour vendre"+nbProduit+produit+".\n");
		int i=marche.trouverEtalLibre();
		marche.utiliserEtal(i, vendeur, produit, nbProduit);
		chaine.append("Le vendeur"+vendeur.getNom()+"vend des"+produit+"à l'étal n°"+i);
		return(chaine.toString());
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Les vendeurs qui proposent des"+produit+"sont:\n");
		Etal[] etal=marche.trouverEtals(produit);
		for (int i=0; i<etal.length;i++) {
			chaine.append("-"+etal[i].getVendeur().getNom());
		}
		return(chaine.toString());}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return(marche.trouverVendeur(vendeur));
	}
	 public String partirVendeur(Gaulois vendeur) {
		 marche.trouverVendeur(vendeur).libererEtal();
		 return("le vendeur"+vendeur.getNom()+"quitte son étal , il a vendu 20 fleur parmis les 20 qu'il voulait vendre");
	 }


	 public String afficherMarche() {
		 return (marche.afficherMarche());
	 }

}
	
	
