package Othello;

import java.io.Serializable;
import java.util.ArrayList;




public class Othello extends Thread implements Serializable{
	
// déclaration des règles 

	private static final long serialVersionUID = 1L;
private int noir = -1;
private int blanc = 1;
private int vide = 2;
private int bord = 0;
//Variable pour sauvegarde
int JoueurActuelTabSauvegarde[] = new int [61];
String NomJoueurSauv1;
String NomJoueurSauv2;
Othello OthSauvAnul[] = new Othello[64];
int TypePartieSauv;
// Déclaration des scores 
int ScoreCoins = 500;
int ScoreC = -150;
int ScoreX = -250;
int ScoreBord1 = 30;
int ScoreBord2 = 10;
int ScoreCentre = 16;
int ScoreCentre1 = 2;
int ScoreCentre2 = 1;
int ScoreNeutre = 0;
//Déclaration joueur en cours
int JoueurEnCours = blanc;
//Déclaration du damier principale du jeu
int damier[][]=  new int[10][10];
// Déclaration du tableau des cases jouer
int CaseJouer[] = new int[61];
int IncremJeu = 0;
// Déclaration du damier des scores d'évaluation
int DamierEvaluation [][] = new int[10][10];
//Déclaration des scores
 int ScoreBlanc = 0;
 int ScoreNoir = 0;
// Initialisation du jeu avec damier à zéro
 public Othello() {
		 this.CreatDamier();
		 this.CreatDamierEvaluation();	 	 
 }
//initialisation du damier
public void CreatDamier() {
//Création des cases vides
for (int i = 1; i < 9; i++) {
	for (int j = 1; j < 9; j++) {
		damier[i][j] = vide;}
	}
// Création des bords
for (int i = 0; i<10;i++) {
	damier[0][i] = bord;
	damier[i][0] = bord;
	damier[9][i] = bord;
	damier[i][9] = bord; }

// initialisation des 4 points
damier[4][4] = blanc;
damier[5][5] = blanc;
damier[5][4] = noir;
damier[4][5] = noir;
}
// Initialisation damier Evaluation
public void CreatDamierEvaluation() {
	for (int i = 1; i < 9; i++) {
		for (int j = 1; j < 9; j++) {
			DamierEvaluation[i][j] = vide;}}
	
	// Score Case Coins
	DamierEvaluation[1][1] = ScoreCoins;
	DamierEvaluation[1][8] = ScoreCoins;
	DamierEvaluation[8][1] = ScoreCoins;
	DamierEvaluation[8][8] = ScoreCoins;
	//Score Case C et X
	DamierEvaluation[1][2] = ScoreC;
	DamierEvaluation[2][1] = ScoreC;
	DamierEvaluation[2][2] = ScoreX;
	
	DamierEvaluation[1][7] = ScoreC;
	DamierEvaluation[2][8] = ScoreC;
	DamierEvaluation[2][7] = ScoreX;
	
	DamierEvaluation[7][1] = ScoreC;
	DamierEvaluation[7][2] = ScoreX;
	DamierEvaluation[8][2] = ScoreC;
	
	DamierEvaluation[7][7] = ScoreX;
	DamierEvaluation[7][8] = ScoreC;
	DamierEvaluation[8][7] = ScoreC;
	// Score des bord
	DamierEvaluation[1][3] = ScoreBord1;
	DamierEvaluation[1][4] = ScoreBord2;
	DamierEvaluation[1][5] = ScoreBord2;
	DamierEvaluation[1][6] = ScoreBord1;
	
	DamierEvaluation[8][3] = ScoreBord1;
	DamierEvaluation[8][4] = ScoreBord2;
	DamierEvaluation[8][5] = ScoreBord2;
	DamierEvaluation[8][6] = ScoreBord1;
	
	DamierEvaluation[3][1] = ScoreBord1;
	DamierEvaluation[4][1] = ScoreBord2;
	DamierEvaluation[5][1] = ScoreBord2;
	DamierEvaluation[6][1] = ScoreBord1;
	
	DamierEvaluation[3][8] = ScoreBord1;
	DamierEvaluation[4][8] = ScoreBord2;
	DamierEvaluation[5][8] = ScoreBord2;
	DamierEvaluation[6][8] = ScoreBord1;
	
	//Score zone centrale
	DamierEvaluation[4][4] = ScoreCentre;
	DamierEvaluation[4][5] = ScoreCentre;
	DamierEvaluation[5][5] = ScoreCentre;
	DamierEvaluation[5][5] = ScoreCentre;
	//Score bord du centre
	DamierEvaluation[3][3] = ScoreCentre2;
	DamierEvaluation[3][4] = ScoreCentre1;
	DamierEvaluation[3][5] = ScoreCentre1;
	DamierEvaluation[3][6] = ScoreCentre2;
	DamierEvaluation[4][6] = ScoreCentre1;
	DamierEvaluation[5][6] = ScoreCentre1;
	DamierEvaluation[6][6] = ScoreCentre2;
	DamierEvaluation[6][5] = ScoreCentre1;
	DamierEvaluation[6][4] = ScoreCentre1;
	DamierEvaluation[6][3] = ScoreCentre2;
	//Score zone neutre
	DamierEvaluation[2][3] = ScoreNeutre;
	DamierEvaluation[2][4] = ScoreNeutre;
	DamierEvaluation[2][5] = ScoreNeutre;
	DamierEvaluation[2][6] = ScoreNeutre;
	DamierEvaluation[7][3] = ScoreNeutre;
	DamierEvaluation[7][4] = ScoreNeutre;
	DamierEvaluation[7][5] = ScoreNeutre;
	DamierEvaluation[7][6] = ScoreNeutre;
	DamierEvaluation[3][2] = ScoreNeutre;
	DamierEvaluation[4][2] = ScoreNeutre;
	DamierEvaluation[5][2] = ScoreNeutre;
	DamierEvaluation[6][2] = ScoreNeutre;
	DamierEvaluation[3][7] = ScoreNeutre;
	DamierEvaluation[4][7] = ScoreNeutre;
	DamierEvaluation[5][7] = ScoreNeutre;
	DamierEvaluation[6][7] = ScoreNeutre;
	
	
	
}
//Mise à jour score selon insertion et selon si les cases sont défintitifs
public void MiseaJourScoreDef(int CouleurPion, int AvAp) {
	//Av = 0 : pour mettre à zéro les cases déjà définitif au précédents jeu
	//Ap = 1 : Pour évaluer le jeu après une profondeur X
	ArrayList<Integer> ListeDef = new ArrayList<Integer>();
	ArrayList<Integer> ListInser = new ArrayList<Integer>();

	if(CouleurPion == blanc){
	ListeDef = RecherchePionDefinitif(1);
	ListInser = RechercheInsertion(1);
	
	for (int i = 0; i<ListeDef.size();i++) {

		DamierEvaluation[ListeDef.get(i)/10][ListeDef.get(i)%10] = AvAp*ScoreCoins/2;}
		
								
		for (int i = 0; i<ListInser.size();i++) {
			if(DamierEvaluation[ListeDef.get(i)/10][ListeDef.get(i)%10] == 1) {
			DamierEvaluation[ListeDef.get(i)/10][ListeDef.get(i)%10] = AvAp*ScoreCoins/2;}
			if(DamierEvaluation[ListeDef.get(i)/10][ListeDef.get(i)%10] == -1) {
			DamierEvaluation[ListeDef.get(i)/10][ListeDef.get(i)%10] = AvAp*ScoreCoins/2;}
			}
	}

	if(CouleurPion == noir){
		ListeDef = RecherchePionDefinitif(-1);
		ListInser = RechercheInsertion(-1);		
		for (int i = 0; i<ListeDef.size();i++) {
				DamierEvaluation[ListeDef.get(i)/10][ListeDef.get(i)%10] = AvAp*ScoreCoins/2;}
		for (int i = 0; i<ListInser.size();i++) {
			if(DamierEvaluation[ListeDef.get(i)/10][ListeDef.get(i)%10] == 1) {
				DamierEvaluation[ListeDef.get(i)/10][ListeDef.get(i)%10] = AvAp*ScoreCoins/2;}
				if(DamierEvaluation[ListeDef.get(i)/10][ListeDef.get(i)%10] == -1) {
				DamierEvaluation[ListeDef.get(i)/10][ListeDef.get(i)%10] = AvAp*ScoreCoins/2;}
		}
	}
	
	// Score Case Coins
	DamierEvaluation[1][1] = ScoreCoins;
	DamierEvaluation[1][8] = ScoreCoins;
	DamierEvaluation[8][1] = ScoreCoins;
	DamierEvaluation[8][8] = ScoreCoins;
}

///Filtrage des solutions
public ArrayList<Integer> FiltreRecherPossible (int CouleurAdverse){
	ArrayList<Integer> Sol = new ArrayList<Integer>();
	ArrayList<Integer> SolFiltre = new ArrayList<Integer>();
	Sol = RecherPossible(CouleurAdverse);
	int k=2;
	if(Sol.size()!=0) {
	SolFiltre.add(Sol.get(0)); // initialisation du tableau
	SolFiltre.add(Sol.get(1)); // initialisation du tableau
	}
	for (int i = 0; i< Sol.size()-3; i+=3) {
		
		if(Sol.get(i)!=Sol.get(i+3)) {
			SolFiltre.add(Sol.get(i+3));
			SolFiltre.add(Sol.get(i+4)); //a mettre à jour avec la bonne valeur
			if(i!=0) {k+=2;};
		}
		else {
			SolFiltre.set(k-1,Sol.get(i+1)+Sol.get(i+4));
		}
	
	}

	
	return SolFiltre;
	
}
// Rechercher les jeux possibles
public ArrayList<Integer> RecherPossible (int CouleurAdverse) {
int k1 = 1;
ArrayList<Integer> CasePossible = new ArrayList<Integer>();
for (int i = 1; i<9;i++) {
	for( int j=1;j<9;j++) {
		if (damier[i][j] == vide) {
			k1 = 1;//recherche vers SUD
			while( (damier[i+k1][j] == CouleurAdverse) && (damier[i+k1][j]!=bord) ) {	
				k1+=1;
				if (damier[i+k1][j] == (CouleurAdverse*(-1))) {
					CasePossible.add(i*10+j);
					CasePossible.add(k1-1);
					CasePossible.add(0); // 0 pour direction SUD			
				}
			}
			k1=1;//recherche vers le NORD
			while( (damier[i-k1][j] == CouleurAdverse) && (damier[i-k1][j]!=bord) ) {	
				k1+=1;
				if (damier[i-k1][j] == (CouleurAdverse*(-1))) {
					CasePossible.add(i*10+j);
					CasePossible.add(k1-1); // nombre de point à renverser
					CasePossible.add(1); // 1 pour direction NORD
				}
	}
		
			//recherche vers l'EST
			k1=1;
			while( (damier[i][j+k1] == CouleurAdverse) && (damier[i][j+k1]!=bord) ) {	
				k1+=1;
				if (damier[i][j+k1] == (CouleurAdverse*(-1))) {
					CasePossible.add(i*10+j);
					CasePossible.add(k1-1); // nombre de point à renverser
					CasePossible.add(2); // 2 pour direction EST			
				}
			}
			k1=1; // Recherche vers l"OUEST
			while( (damier[i][j-k1] == CouleurAdverse) && (damier[i][j-k1]!=bord) ) {	
				k1+=1;
				if (damier[i][j-k1] == (CouleurAdverse*(-1))) {
					CasePossible.add(i*10+j);
					CasePossible.add(k1-1); // nombre de point à renverser
					CasePossible.add(3);// 3 pour direction OUEST			
				}
			}
			k1=1; // Recherche vers le SUD OUEST
			while( (damier[i+k1][j-k1] == CouleurAdverse) && (damier[i+k1][j-k1]!=bord) ) {	
				k1+=1;
				if (damier[i+k1][j-k1] == (CouleurAdverse*(-1))) {
					CasePossible.add(i*10+j);
					CasePossible.add(k1-1); // nombre de point à renverser
					CasePossible.add(4); // 4 pour direction SUD OUEST			
				}
			}
			k1=1; // Recherche vers le NORD OUEST
			while( (damier[i-k1][j-k1] == CouleurAdverse) && (damier[i-k1][j-k1]!=bord) ) {	
				k1+=1;
				if (damier[i-k1][j-k1] == (CouleurAdverse*(-1))) {
					CasePossible.add(i*10+j);
					CasePossible.add(k1-1); // nombre de point à renverser
					CasePossible.add(5); // 5 pour direction NORD OUEST			
				}
			}
			k1=1; // Recherche vers le SUD EST
			while( (damier[i+k1][j+k1] == CouleurAdverse) && (damier[i+k1][j+k1]!=bord) ) {	
				k1+=1;
				if (damier[i+k1][j+k1] == (CouleurAdverse*(-1))) {
					CasePossible.add(i*10+j);
					CasePossible.add(k1-1); // nombre de point à renverser
					CasePossible.add(6); // 6 pour direction SUD EST			
				}
			}
			k1=1; // Recherche vers le NORD EST
			while( (damier[i-k1][j+k1] == CouleurAdverse) && (damier[i-k1][j+k1]!=bord) ) {	
				k1+=1;
				if (damier[i-k1][j+k1] == (CouleurAdverse*(-1))) {
					CasePossible.add(i*10+j);
					CasePossible.add(k1-1); // nombre de point à renverser
					CasePossible.add(7); // 7 pour direction NORD EST			
				}
			}
		} // fin if damier
	}} // fin boucle for i et j
return (CasePossible);	
}
//
// Chercher les pions définitifs en partant des coins
public ArrayList<Integer> RecherchePionDefinitif( int CouleurPion) {
	
	ArrayList<Integer> PionDefintif = new ArrayList<Integer>();

	int k1 = 1;
	int k2 = 1;
	int k3;
	int k4;
	int kk = 0;
	// A partir de la case 11
		if(damier[1][1]== CouleurPion) {
			PionDefintif.add(kk,10+k1);kk++;k1++;k2++;
		// vers l'EST
		while (damier[1][k1]== CouleurPion) {
			PionDefintif.add(kk,10+k1);
			kk++;k1++;
		}
		// vers le sud
		while (damier[k2][1]== CouleurPion) {
			PionDefintif.add(kk,k2*10+1);
			kk++;k2++;
		}
		k1--;k2--;k3=k1;k4=k2;
		// voir tous les cases adjacentes 1 
		for (int i = 2; i<=k2;i++) {
			for(int j = 2; j<=k1-1;j++) {
		if (damier[i][j] == CouleurPion)	{
			PionDefintif.add(kk,i*10+j);kk++;}
		if (damier[i][j] != CouleurPion || j ==k1-1) {k1=j;j=10;}
		
			}
		}
		// voir tous les cases adjacentes 2
		k1=k3;k2=k4;
				for (int j = 2; j<=k1;j++) {
					for(int i = 2; i<=k2-1;i++) {
				if (damier[i][j] == CouleurPion && TestExist(PionDefintif, (i*10+j)))	{
					PionDefintif.add(kk,i*10+j);kk++;}
				if (damier[i][j] != CouleurPion || i ==k2-1) {k2=i;i=10;}}				
					}
		}//fin coin 11
		
		 k1=1;k2=1;
		// A partir de la case 18
			if(damier[1][8]== CouleurPion) {
				if(TestExist(PionDefintif,18)){PionDefintif.add(kk,10+(9-k1));kk++;} // Attention à ne pas enregistrer la case une deuxième fois
				k1++;k2++;
			// vers l'OUEST
			while (damier[1][9-k1]== CouleurPion) {
				if(TestExist(PionDefintif,10+9-k1)) {PionDefintif.add(kk,10+(9-k1));kk++;}
				k1++;
			}
			// vers le sud
			while (damier[k2][8]== CouleurPion) {
				PionDefintif.add(kk,k2*10+8);// Pas besoins de condutions (elle sera mise sur le coins 88
				kk++;k2++;
			}
			k1--;k2--;k3=k1;k4=k2;
			// voir tous les cases adjacentes 1 
			for (int i = 2; i<=k2;i++) {
				for(int j = 2; j<=k1-1;j++) {
			if (damier[i][9-j] == CouleurPion && TestExist(PionDefintif, (i*10+(9-j))))	{
				PionDefintif.add(kk,i*10+9-j);kk++;}
			if (damier[i][9-j] != CouleurPion || j ==k1-1) {k1=j;j=10;}
			
				}
			}
			// voir tous les cases adjacentes 2
			k1=k3;k2=k4;
					for (int j = 2; j<=k1;j++) {
						for(int i = 2; i<=k2-1;i++) {
					if (damier[i][9-j] == CouleurPion && TestExist(PionDefintif, (i*10+9-j)))	{
						PionDefintif.add(kk,i*10+9-j);kk++;}
					if (damier[i][9-j] != CouleurPion || i ==k2-1) {k2=i;i=10;}}				
						}
			}//fin coin 18
	
			 k1=1;k2=1;
				// A partir de la case 81
					if(damier[8][1]== CouleurPion) {
						if(TestExist(PionDefintif,81)){PionDefintif.add(kk,81);kk++;} // Attention à ne pas enregistrer la case une deuxième fois
						k1++;k2++;
					// vers L'EST
					while (damier[8][k1]== CouleurPion) {
						if(TestExist(PionDefintif,80+k1)) {PionDefintif.add(kk,80+k1);kk++;}
						k1++;
					}
					// vers le NORD
					while (damier[9-k2][1]== CouleurPion) {
						if(TestExist(PionDefintif,(9-k2)*10+1)) {PionDefintif.add(kk,(9-k2)*10+1);kk++;}
						k2++;
					}
					k1--;k2--;k3=k1;k4=k2;
					// voir tous les cases adjacentes 1
					for (int i = 2; i<=k2;i++) {
						for(int j = 2; j<=k1-1;j++) {
					if (damier[9-i][j] == CouleurPion && TestExist(PionDefintif, ((9-i)*10+j)))	{
						PionDefintif.add(kk,(9-i)*10+j);kk++;}
					if (damier[9-i][j] != CouleurPion || j ==k1-1) {k1=j-1;j=10;}
					
						}
					}
					// voir tous les cases adjacentes 2
					k1=k3;k2=k4;
							for (int j = 2; j<=k1;j++) {
								for(int i = 2; i<=k2-1;i++) {
							if (damier[9-i][j] == CouleurPion && TestExist(PionDefintif, ((9-i)*10+j)))	{
								PionDefintif.add(kk,(9-i)*10+j);kk++;}
							if (damier[9-i][j] != CouleurPion || i ==k2-1) {k2=i-1;i=10;}}				
								}
					}//fin coin 81
					
					// A partir de la case 88
					k1=1;k2=1;
					if(damier[8][8]== CouleurPion) {
						if(TestExist(PionDefintif,88)){PionDefintif.add(kk,88);kk++;} // Attention à ne pas enregistrer la case une deuxième fois
						k1++;k2++;
					// vers L'OUEST
					while (damier[8][9-k1]== CouleurPion) {
						if(TestExist(PionDefintif,80+9-k1)) {PionDefintif.add(kk,80+9-k1);kk++;}
						k1++;
					}
					// vers le NORD
					while (damier[9-k2][8]== CouleurPion) {
						if(TestExist(PionDefintif,(9-k2)*10+8)) {PionDefintif.add(kk,(9-k2)*10+8);kk++;}
						k2++;
					}
					k1--;k2--;k3=k1;k4=k2;
					// voir tous les cases adjacentes 1
					for (int i = 2; i<=k2;i++) {
						for(int j = 2; j<=k1-1;j++) {
					if (damier[9-i][9-j] == CouleurPion && TestExist(PionDefintif, ((9-i)*10+9-j)))	{
						PionDefintif.add(kk,(9-i)*10+9-j);kk++;}
					if (damier[9-i][9-j] != CouleurPion || j ==k1-1) {k1=j-1;j=10;}
					
						}
					}
					// voir tous les cases adjacentes 2
					k1=k3;k2=k4;
							for (int j = 2; j<=k1;j++) {
								for(int i = 2; i<=k2-1;i++) {
							if (damier[9-i][9-j] == CouleurPion && TestExist(PionDefintif, ((9-i)*10+9-j)))	{
								PionDefintif.add(kk,(9-i)*10+9-j);kk++;}
							if (damier[9-i][9-j] != CouleurPion || i ==k2-1) {k2=i-1;i=10;}}				
								}
					}//fin coin 88
	return PionDefintif;
	
}
// Filtrage du tableau lors de la recherche deinitif
public boolean TestExist(ArrayList<Integer> TabList, int CaseTest) {
	
	for (int i=0;i<TabList.size();i++) {
	if (TabList.get(i) == CaseTest) {return false;}}
	
	return true;
	
}
// Recherche des cas d'insertion
public ArrayList<Integer> RechercheInsertion(int CouleurPion){	
	ArrayList<Integer> TabInsertion = new ArrayList<Integer>();	
	int CouleurAdverse = CouleurPion*(-1);
	
// Cas par rapport coin 11
	int k1 = 3;
	//Vers l'EST
	if(damier[1][1] == vide && damier[1][2] == CouleurAdverse) {
		while(k1<8) {		
			if (damier[1][k1]==CouleurAdverse) {k1++;}
			else if(damier[1][k1]==vide || damier[1][k1+1]==vide || damier[1][k1]==CouleurPion || damier[1][k1+1]==CouleurPion) {k1=9;}
			else if (damier[1][k1]==CouleurPion && damier[1][k1+1]==CouleurAdverse ) {
				TabInsertion.add(10+k1);k1=9;}}}
	// Vers le SUD
	k1=3;
	if(damier[1][1] == vide && damier[2][1] == CouleurAdverse) {
		while(k1<8) {		
			if (damier[k1][1]==CouleurAdverse) {k1++;}
			else if(damier[k1][1]==vide || damier[k1+1][1]==vide || damier[k1][1]==CouleurPion || damier[k1+1][1]==CouleurPion) {k1=9;}
			else if (damier[k1][1]==CouleurPion && damier[k1+1][1]==CouleurAdverse ) {
				TabInsertion.add(10*k1+1);k1=9;}}} // fin coin 11
// Cas par rapport coin 18
		k1 = 3;
		//Vers l'OUEST
		if(damier[1][8] == vide && damier[1][7] == CouleurAdverse) {
			while(k1<8) {		
				if (damier[1][9-k1]==CouleurAdverse) {k1++;}
				else if(damier[1][9-k1]==vide || damier[1][9-k1-1]==vide || damier[1][9-k1]==CouleurPion || damier[1][9-k1-1]==CouleurPion) {k1=9;}
				else if (damier[1][9-k1]==CouleurPion && damier[1][9-k1-1]==CouleurAdverse ) {
					if(TestExist(TabInsertion,(10+9-k1))) {TabInsertion.add(10+9-k1);}k1=9;}
				}
			}		
		// Vers le SUD
		k1=3;
		if(damier[1][8] == vide && damier[2][8] == CouleurAdverse) {
			while(k1<8) {		
				if (damier[k1][8]==CouleurAdverse) {k1++;}
				else if(damier[k1][8]==vide || damier[k1+1][8]==vide || damier[k1][8]==CouleurPion || damier[k1+1][8]==CouleurPion) {k1=9;}
				else if (damier[k1][8]==CouleurPion && damier[k1+1][8]==CouleurAdverse ) {
					TabInsertion.add(10*k1+8);k1=9;}}} // fin coin 18	
// Cas par rapport coin 81
				k1 = 3;
				//Vers L'EST
				if(damier[8][1] == vide && damier[7][1] == CouleurAdverse) {
					while(k1<8) {		
						if (damier[8][k1]==CouleurAdverse) {k1++;}
						else if(damier[8][k1]==vide || damier[8][k1+1]==vide || damier[8][k1]==CouleurPion || damier[8][k1+1]==CouleurPion) {k1=9;}
						else if (damier[8][k1]==CouleurPion && damier[8][k1+1]==CouleurAdverse ) {
							if(TestExist(TabInsertion,(80+k1))) {TabInsertion.add(80+k1);}k1=9;}
						}
					}		
				// Vers le NORD
				k1=3;
				if(damier[8][1] == vide && damier[7][1] == CouleurAdverse) {
					while(k1<8) {		
						if (damier[9-k1][1]==CouleurAdverse) {k1++;}
						else if(damier[9-k1][1]==vide || damier[9-k1-1][1]==vide || damier[9-k1][1]==CouleurPion || damier[9-k1-1][1]==CouleurPion) {k1=9;}
						else if (damier[9-k1][1]==CouleurPion && damier[9-k1-1][1]==CouleurAdverse ) {
							TabInsertion.add(10*(9-k1)+1);k1=9;}}} // fin coin 18	
// Cas par rapport coin 88
				k1 = 3;
				//Vers L'OUEST
				if(damier[8][8] == vide && damier[8][7] == CouleurAdverse) {
					while(k1<8) {		
						if (damier[8][9-k1]==CouleurAdverse) {k1++;}
						else if(damier[8][9-k1]==vide || damier[8][9-k1-1]==vide || damier[8][9-k1-1]==CouleurPion
								|| damier[8][9-k1]==CouleurPion ) {k1=9;}
						else if (damier[8][9-k1]==CouleurPion && damier[8][9-k1-1]==CouleurAdverse ) {
							if(TestExist(TabInsertion,(80+9-k1))) {TabInsertion.add(80+9-k1);}k1=9;}
						}
					}		
				// Vers le NORD
				k1=3;
				if(damier[8][8] == vide && damier[7][8] == CouleurAdverse) {
					while(k1<8) {		
						if (damier[9-k1][8]==CouleurAdverse) {k1++;}
						else if(damier[9-k1][8]==vide || damier[9-k1-1][8]==vide || damier[9-k1][8]==CouleurPion || damier[9-k1-1][8]==CouleurPion) {k1=9;}
						else if (damier[9-k1][8]==CouleurPion && damier[9-k1-1][8]==CouleurAdverse ) {
							TabInsertion.add(10*(9-k1)+8);k1=9;}}} // fin coin 18
	return TabInsertion;
	
}


// Jouer un coup
public void JouerCoup(int Ligne, int Colonne, int CouleurJoueur) {
	int Choix = Ligne * 10 + Colonne;
	ArrayList<Integer> PossibleChoix = new ArrayList<Integer>();
	PossibleChoix = RecherPossible(CouleurJoueur*(-1));
	for (int i = 0; i < PossibleChoix.size(); i+=3) {
	if (Choix == PossibleChoix.get(i)) {
		damier[Ligne][Colonne] = CouleurJoueur;
	switch(PossibleChoix.get(i+2)) {
	case 0: // Vers le sud
	for (int j = 1 ; j< PossibleChoix.get(i+1)+1;j++) {
		damier[Ligne+j][Colonne] = CouleurJoueur;
		}
	break;
	case 1: // vers le nord
	for (int j =1; j< PossibleChoix.get(i+1)+1;j++) {
		damier[Ligne-j][Colonne] = CouleurJoueur;
		}
	break;
	case 2: // vers l'EST
		for (int j =1; j< PossibleChoix.get(i+1)+1;j++) {
			damier[Ligne][Colonne+j] = CouleurJoueur;
			}
		break;
	case 3: // vers l'OUEST
		for (int j =1; j< PossibleChoix.get(i+1)+1;j++) {
			damier[Ligne][Colonne-j] = CouleurJoueur;
			}
		break;
	case 4: // vers Sud Ouest
		for (int j =1; j< PossibleChoix.get(i+1)+1;j++) {
			damier[Ligne+j][Colonne-j] = CouleurJoueur;
			}
		break;
	case 5: // vers Nord Ouest
		for (int j =1; j< PossibleChoix.get(i+1)+1;j++) {
			damier[Ligne-j][Colonne-j] = CouleurJoueur;     
			}
		break;
	case 6: // vers SUD EST
		for (int j =1; j< PossibleChoix.get(i+1)+1;j++) {
			damier[Ligne+j][Colonne+j] = CouleurJoueur;
			}
		break;
	case 7: // vers Nord Est
		for (int j =1; j< PossibleChoix.get(i+1)+1;j++) {///////// Mise à jour
			damier[Ligne-j][Colonne+j] = CouleurJoueur;
			}
		break;
	}
			
		}
	}
	
	
// Mise à jour des score;
	ScoreNoir = 0;
	ScoreBlanc = 0;
	for (int i = 1;i<9;i++) {
		for(int j =1;j<9;j++) {
			if (damier[i][j]== -1)
			ScoreNoir +=1;
			else if (damier[i][j]==1)
			ScoreBlanc +=1;
		}
	}
	
	CaseJouer[IncremJeu] = Choix;
	IncremJeu++;
	
}
// Retourne si le coup jouer est valide
public boolean TestCoup(int CoupJouer) {
	boolean Test = false;
	int Increm = 0;
	ArrayList<Integer> TestTab = RecherPossible(-JoueurEnCours);	
	while(TestTab.size()!=0 && Increm < TestTab.size() && Test == false) {
			if ( (CoupJouer) == TestTab.get(Increm)) {Test = true;}
			Increm+=3;}
	return(Test);
}
// Vérifier si le joueur selectionner à des jeux possible
public boolean JeuPossible(int CouleurJoueur) {
boolean JeuPos = true;
ArrayList<Integer> TabTest = RecherPossible(-CouleurJoueur);
if (TabTest.size() == 0 ) {JeuPos = false;}
	return JeuPos;
}


public int getScore(int Couleur) {
int Scor = 0;	
	for (int i=0; i<9;i++) {
		for(int j=0;j<9;j++) {
			if (damier[i][j] == Couleur) { Scor +=1;}
		}}
return Scor;}

// Calcul somme des score
public int SommeMatrice() {
	int Score=0;
	
	
		for(int i =1;i<9;i++) {
			for(int j = 1;j<9;j++) {
				if(damier[i][j]==blanc) {Score  = Score + DamierEvaluation[i][j];}
				else if (damier[i][j]==noir) {Score  = Score - DamierEvaluation[i][j];}
			}
		}

return Score;

}


public Othello CopieOthello() {
// crée un nouveau modèle
Othello mod = new Othello();

// récupère le statut des  cases
for (int i = 1; i < 9; i++) {
for (int j = 1; j < 9; j++) {
	mod.damier[i][j] = damier[i][j];
	mod.DamierEvaluation[i][j] = DamierEvaluation[i][j];}
}
// récupère le joueurs en cours
mod.JoueurEnCours = JoueurEnCours;
// récupère les points
mod.ScoreBlanc = getScore(blanc);
mod.ScoreNoir = getScore(noir);
// récupère la case joué
for (int i =0; i<60;i++) {
	mod.CaseJouer[i] = CaseJouer[i];	
}
// récupéère increm jeu
mod.IncremJeu = IncremJeu;
return mod;}

public Othello CopieSauvOthello() {
	// crée un nouveau modèle
	Othello mod = new Othello();

	// récupère le statut des  cases
	for (int i = 1; i < 9; i++) {
	for (int j = 1; j < 9; j++) {
		mod.damier[i][j] = damier[i][j];
		mod.DamierEvaluation[i][j] = DamierEvaluation[i][j];}
	}
	// récupère le joueurs en cours
	mod.JoueurEnCours = JoueurEnCours;
	// récupère les points
	mod.ScoreBlanc = getScore(blanc);
	mod.ScoreNoir = getScore(noir);
	// récupère la case joué
	for (int i =0; i<CaseJouer.length;i++) {
		mod.CaseJouer[i] = CaseJouer[i];	
		mod.JoueurActuelTabSauvegarde[i] = JoueurActuelTabSauvegarde[i];
	}
	// récupéère increm jeu
	mod.IncremJeu = IncremJeu;
	//Copie des joueurs
	mod.NomJoueurSauv1 = NomJoueurSauv1;
	mod.NomJoueurSauv2 = NomJoueurSauv2;
	mod.TypePartieSauv = TypePartieSauv;
	//copie des étapes de jeu
	for(int i = 0; i<IncremJeu;i++) {
	mod.OthSauvAnul[i] = OthSauvAnul[i].CopieOthello();
	}
	
	return mod;}


public void ChargeModel (Othello modele)
{
	// récupère les statuts des  cases
	for (int i = 1; i < 9; i++) {
		for (int j = 1; j < 9; j++) {
			damier[i][j] = modele.damier[i][j];
			DamierEvaluation[i][j] = modele.DamierEvaluation[i][j];
		}}
		// récupère le joueurs en cours
	JoueurEnCours = modele.JoueurEnCours;
		// récupère les points
	ScoreBlanc = modele.ScoreBlanc;
	ScoreNoir = modele.ScoreNoir;
		// récupère la case joué
	for (int i =0; i<60;i++) {
	CaseJouer[i] = modele.CaseJouer[i];}
	// Récupère l'incrémenet
	IncremJeu = modele.IncremJeu;
	
}



}//fin Othello

