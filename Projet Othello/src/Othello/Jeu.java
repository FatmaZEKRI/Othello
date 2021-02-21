package Othello;



public class Jeu extends Thread {
	
	IntelArt IntelA = new IntelArt(); // Intelligence Artificiel
	int kkk;
	int iii;
	
	public void MultiJoueur(Othello OtJ, Fenetre FeJ) {
		/// déclaration variable local
		int situationJeu = -1;
		OtJ.JouerCoup(FeJ.SelectJeu/10,FeJ.SelectJeu%10,OtJ.JoueurEnCours); // Mise à jour de la grille avec le coup selctionner
		//Mise à jour diamier visuellement
		FeJ.CoupJouer();
		// Mise à jour du score
		FeJ.MiseAJourAffichage() ;
		// Mise à jour case jouer
		FeJ.ConvertCaseJouer(OtJ.CaseJouer[OtJ.IncremJeu-1]);
		FeJ.AfficheCaseJouer();
		FeJ.JoueurActuelTab[OtJ.IncremJeu] = FeJ.Joueur1;
		//Donner la main ou pas au joueur suivant
				situationJeu = JeuCond(OtJ, FeJ);
		switch (situationJeu) {
		case 1:
			FeJ.RecherCoupPossible(OtJ.JoueurEnCours);
			FeJ.AfficherJoueur();
		break;
		case 2:
		break;
		case 3:
			OtJ.JoueurEnCours = OtJ.JoueurEnCours*(-1);
			FeJ.RecherCoupPossible(OtJ.JoueurEnCours);
			FeJ.AfficherJoueur();
		break;
		
		}
		
		
	}
	
	
	public void JeuSolo(Othello OtJ, Fenetre FeJ) {
		          
      
		int situationJeu = -1;
		OtJ.JouerCoup(FeJ.SelectJeu/10,FeJ.SelectJeu%10,OtJ.JoueurEnCours); // Mise à jour de la grille avec le coup selctionner	
		//Mise à jour diamier visuellement
		FeJ.CoupJouer();
		// Mise à jour du score
		FeJ.MiseAJourAffichage() ;
		// Mise à jour case jouer
		FeJ.ConvertCaseJouer(OtJ.CaseJouer[OtJ.IncremJeu-1]);
		FeJ.AfficheCaseJouer();
		//Donner la main ou pas au joueur suivant
		situationJeu = JeuCond(OtJ, FeJ);
		
		switch (situationJeu) {
		case 1:
			{
				if( FeJ.JoueurActuelTab[OtJ.IncremJeu-1] == FeJ.JoueurIA1) {
				
				if(FeJ.Auto == true) {
					FeJ.SelectJeu = IntelA.ChoixIa(FeJ.JoueurIA1 , OtJ);
					FeJ.JoueurActuelTab[OtJ.IncremJeu] = FeJ.JoueurIA1;
					FeJ.Jouer();}}			
			else if (FeJ.JoueurActuelTab[OtJ.IncremJeu-1] == FeJ.Joueur1) {
				FeJ.JoueurActuelTab[OtJ.IncremJeu] = FeJ.Joueur1;
				FeJ.RecherCoupPossible(OtJ.JoueurEnCours);}
				FeJ.AfficherJoueur();
				}
		break;
		case 2:
		
		break;
		case 3:	
			//changer de couleur
			OtJ.JoueurEnCours = OtJ.JoueurEnCours*(-1);
			// Selon Type de joueur actuel faire un choix
			{
			if( FeJ.JoueurActuelTab[OtJ.IncremJeu-1] == FeJ.Joueur1 ) {
				FeJ.JoueurActuelTab[OtJ.IncremJeu] = FeJ.JoueurIA1;
				FeJ.RecherCoupPossible(OtJ.JoueurEnCours);
				FeJ.AfficherJoueur();
				if(FeJ.Auto == true) {
					FeJ.SelectJeu = IntelA.ChoixIa(FeJ.JoueurIA1 , OtJ);
					FeJ.Jouer();}
				}
			else if(FeJ.JoueurActuelTab[OtJ.IncremJeu-1] == FeJ.JoueurIA1) {
				FeJ.JoueurActuelTab[OtJ.IncremJeu] = FeJ.Joueur1;
				//Afficher à qui de joueur
				FeJ.AfficherJoueur();
				// Afficher les cas de jeu possible
				FeJ.RecherCoupPossible(OtJ.JoueurEnCours);
				
				}
			}
		break;
		
		}
		
		
		
	}
public void JeuIAIA(Othello OtJ, Fenetre FeJ) {

	int situationJeu = -1;
	OtJ.JouerCoup(FeJ.SelectJeu/10,FeJ.SelectJeu%10,OtJ.JoueurEnCours); // Mise à jour de la grille avec le coup selctionner	
	// Petite pause
	try {
		Thread.sleep(10);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//Mise à jour diamier visuellement
	FeJ.CoupJouer();
	// Mise à jour du score
	FeJ.MiseAJourAffichage();
	//Mise à jour de la fenetre
	FeJ.paint(FeJ.getGraphics());
	// Mise à jour case jouer
	FeJ.ConvertCaseJouer(OtJ.CaseJouer[OtJ.IncremJeu-1]);
	FeJ.AfficheCaseJouer();
	//Donner la main ou pas au joueur suivant
	situationJeu = JeuCond(OtJ, FeJ);
	switch (situationJeu) {
	case 1:
		{
			if(FeJ.JoueurActuelTab[OtJ.IncremJeu-1] == FeJ.JoueurIA1) {
				FeJ.JoueurActuelTab[OtJ.IncremJeu] = FeJ.JoueurIA1;
			// Afficher les cas de jeu possible
			FeJ.RecherCoupPossible(OtJ.JoueurEnCours);
			if(FeJ.Auto == true) {
				FeJ.SelectJeu = IntelA.ChoixIa(FeJ.JoueurIA1 , OtJ);
				FeJ.Jouer();}}			
		else if (FeJ.JoueurActuelTab[OtJ.IncremJeu-1] == FeJ.JoueurIA2 ) {
			FeJ.JoueurActuelTab[OtJ.IncremJeu] = FeJ.JoueurIA2;
			// Afficher les cas de jeu possible
			FeJ.RecherCoupPossible(OtJ.JoueurEnCours);
			//long debut = System.currentTimeMillis();
			if(FeJ.Auto == true) {
				FeJ.SelectJeu = IntelA.ChoixIa(FeJ.JoueurIA2 , OtJ);
				FeJ.Jouer();}
			//System.out.println(System.currentTimeMillis()-debut);
			}
				}
	break;
	case 2:
	
	break;
	case 3:	
		//changer de couleur
		OtJ.JoueurEnCours = OtJ.JoueurEnCours*(-1);
		// Selon Type de joueur actuel faire un choix
		{
		if(FeJ.JoueurActuelTab[OtJ.IncremJeu-1] == FeJ.JoueurIA2) {
			FeJ.JoueurActuelTab[OtJ.IncremJeu] = FeJ.JoueurIA1;
			FeJ.AfficherJoueur();
			// Afficher les cas de jeu possible
			FeJ.RecherCoupPossible(OtJ.JoueurEnCours);
			if(FeJ.Auto == true) {
				FeJ.SelectJeu = IntelA.ChoixIa(FeJ.JoueurIA1 , OtJ);
				FeJ.Jouer();}
			}
		else if(FeJ.JoueurActuelTab[OtJ.IncremJeu-1] == FeJ.JoueurIA1 ) {
			FeJ.JoueurActuelTab[OtJ.IncremJeu] = FeJ.JoueurIA2;
			FeJ.AfficherJoueur();
			// Afficher les cas de jeu possible
			FeJ.RecherCoupPossible(OtJ.JoueurEnCours);
			if(FeJ.Auto == true) {
				FeJ.SelectJeu = IntelA.ChoixIa(FeJ.JoueurIA2 , OtJ);
				FeJ.Jouer();}
			}
		}
	break;
	
	}
	
}

public int JeuCond(Othello Othe, Fenetre Fee){
	int CasDeJeu = 0;
	int CaseOccupe = Othe.ScoreBlanc + Othe.ScoreNoir;
	//cas 1 : Le joueur adverse ne peut pas jouer donc on demande au joueur actuel de rejouer
	{
	if (Othe.JeuPossible(Othe.JoueurEnCours*(-1)) == false && Othe.JeuPossible(Othe.JoueurEnCours) == true) { 
		CasDeJeu = 1;}
	//cas 2 : Pas de jeux pour l'adversaire ni pour le joueur actuelle
	else if( (Othe.JeuPossible(Othe.JoueurEnCours*(-1)) == false &&  Othe.JeuPossible(Othe.JoueurEnCours) == false) || CaseOccupe >63) {
		
// Faire statut fin de partie
		Fee.FinJeu = true;
		Fee.FinChrono = true;
		Fee.FinDePartie();
		Statistique St = new Statistique(Fee,Othe);
		St.AjoutHtable(Othe.CaseJouer);
		St.SauvHTable(); //sauvegarder les données
		St.StatParJoueur();
	
		
	CasDeJeu = 2;}
	
	//cas 3 : Jeu possible pour l'adversaire donner la main
	else if(Othe.JeuPossible(Othe.JoueurEnCours*(-1)) == true) {CasDeJeu = 3;}
	}


	return CasDeJeu;
}



}//fin classe JEu
