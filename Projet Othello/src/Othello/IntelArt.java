package Othello;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;

public class IntelArt extends Thread {
	
	Random rand = new Random();
	Othello OthIA = new Othello(); // Pour boucle de AlphaBeta
	Othello testOth = new Othello();
	ArrayList<Othello> OthList = new ArrayList<Othello>();
	int Joueur;
	int Jou;
	long Debut;
	private int BestMove;
	private int BestMoveAlphaBeta;
	private int BestMoveNegaMax;
	private int BestMoveSoft;
	private int Position;
	//private int TabScore[] = new int [64]; 
	int ProfondeurIA_1 = 4;	 // profondeur intermédiaire
	int ProfondeurIA_2 = 6;	 // profondeur professionnel
	int TempsParBranche;
	int G;
	Hashtable RacineSave = new Hashtable();
	boolean IAPro;
	public int ChoixIa(int JoueurIA, Othello Othe) {
		int ChoixIA = 0;

		if(JoueurIA == 0 || JoueurIA == 10) {	        
			ChoixIA = Debutant(Othe); // débutant
		}
		
		if(JoueurIA == 1 || JoueurIA == 20) {
			IAPro = false;
			ChoixIA = CalculIA(ProfondeurIA_1, Othe);	 // IA intermédiaire	
			
			
			}
		if(JoueurIA == 2 || JoueurIA == 30) {
			IAPro = true;
			ChoixIA = CalculIA(ProfondeurIA_2, Othe);// IA Professionel
		}		
		
		return ChoixIA;
	}
	
	public void ScoreTabAjour() {
	
	}
	

	public synchronized int Debutant(Othello Othe) {
		
		int ChoixDebutant;
		int CaseChoisie = 0;
		ArrayList<Integer> Resultat = new ArrayList<Integer>();
		Resultat = Othe.RecherPossible(-Othe.JoueurEnCours);
		int ChoixPossible[] = new int [Resultat.size()/3];
		int k = 0;
		for (int i = 0; i< Resultat.size(); i+=3) {
			ChoixPossible[k] = Resultat.get(i);	
			k++;
		}		
		// choix aléatoir de l'indice de la case
		ChoixDebutant = ChoixPossible[CaseChoisie]; // Selection du choix.
		return ChoixDebutant;
	}
public synchronized int CalculIA(int Profondeur, Othello Othe) {
	int Alpha =-100000; 
	int Beta = 100000;
	int CaseChoisie;
	
	Joueur = Othe.JoueurEnCours;
	
	//Othe.MiseaJourScoreDef(Othe.JoueurEnCours,0); // Mise à jour matrice des scores et mettre à zéro les pions défintif occupé
	//Othe.MiseaJourScoreDef(-Othe.JoueurEnCours,0); // Mise à jour matrice des scores et mettre à zéro les pions défintif occupé
	OthIA= Othe.CopieOthello(); // Faire une copie du modèle du jeu actuelle
	testOth = Othe;
	//BestMove = 0;
	ArrayList<Integer> SolutionPo = Othe.FiltreRecherPossible(-Othe.JoueurEnCours);
	//TempsParBranche = 20000 / (SolutionPo.size()/2);
	if(SolutionPo.size() >2) {
		//Debut = System.currentTimeMillis();
		if(IAPro == true) {
			CaseChoisie = AlphaBeta(Profondeur, Alpha,Beta,Profondeur);
			BestMove = BestMoveAlphaBeta;}
		if(IAPro == false) {
		//	long chrono0 =  java.lang.System.currentTimeMillis();
			CaseChoisie = AlphaBeta(Profondeur, Alpha,Beta,Profondeur);
			BestMove = BestMoveAlphaBeta;
		/*	long chronoAlpha =  java.lang.System.currentTimeMillis() - chrono0;
		//	chrono0 =  java.lang.System.currentTimeMillis();
		CaseChoisie = NegaMax(Profondeur, Alpha,Beta,Profondeur);
		long chronoNegaMax =  java.lang.System.currentTimeMillis() -chrono0;
		chrono0 =  java.lang.System.currentTimeMillis();
		CaseChoisie = MinMax(Profondeur,Profondeur);
			long chronoMinMax = java.lang.System.currentTimeMillis() - chrono0;
			chrono0 =  java.lang.System.currentTimeMillis();
		CaseChoisie = SoftAlphaBeta(Profondeur, Alpha,Beta,Profondeur);
		long chronoSoft = java.lang.System.currentTimeMillis() - chrono0;*/
		Position = 0;//Othe.CaseJouer[Othe.IncremJeu-1];
		//G = MTDF(Profondeur, 0);	
//		System.out.println(" AlphaBeta : "+BestMoveAlphaBeta +" NegaMax : "+ BestMoveNegaMax);//+" SoftAlpha :"+BestMoveSoft);
//		System.out.println("MinMax :"+chronoMinMax+" AlphaBeta : "+chronoAlpha +" NegaMax : "+ chronoNegaMax+" SoftAlpha :"+chronoSoft);
		
	    
		}
		
		
	}
	else {BestMove = SolutionPo.get(0);
	}
	CaseChoisie = BestMove;	
	return CaseChoisie;
}

public int AlphaBeta(int Pro, int Alpha, int Beta, int Profondeur) {
		
	int ScoreTMP=0;
	ArrayList<Integer> ChoiPossible = new ArrayList<Integer>();
	
	if((Pro ==0) || (OthIA.ScoreBlanc + OthIA.ScoreNoir)>64  ||
			((OthIA.JeuPossible(OthIA.JoueurEnCours*(-1)) == false && OthIA.JeuPossible(OthIA.JoueurEnCours*(1)) == false)) ) 
	{return Eval();}
	
	if(OthIA.JoueurEnCours == Joueur){
	ChoiPossible = OthIA.FiltreRecherPossible(-OthIA.JoueurEnCours); // - joueur car recherche possible a été faite à l'envers 
		
	for (int i =0; i< ChoiPossible.size() ;i+=2 ) {
		Othello OthSauve = OthIA.CopieOthello(); // Sauvegarder le damier
		OthIA.JouerCoup(ChoiPossible.get(i)/10, ChoiPossible.get(i)%10, OthIA.JoueurEnCours); //Jouer un jeu
		OthIA.JoueurEnCours = OthIA.JoueurEnCours*(-1);
		ArrayList<Integer> Test = OthIA.FiltreRecherPossible(-OthIA.JoueurEnCours); // recherche solution pout prochain joueur
		if(Test.size() == 0) {OthIA.JoueurEnCours = OthIA.JoueurEnCours *(-1);} // Revenir au joueur précédent si pas de solut
		ScoreTMP = AlphaBeta(Pro-1,Alpha,Beta, Profondeur);
		OthIA.ChargeModel(OthSauve); // récupération damier enregistrer
		if(ScoreTMP > Alpha) {
			Alpha = ScoreTMP;
			if(Pro == Profondeur) {BestMoveAlphaBeta = ChoiPossible.get(i);}
		if (Alpha >= Beta) {break;} }
	}
	
	return(Alpha);
	}
	
	else {
		
		ChoiPossible = OthIA.FiltreRecherPossible(-OthIA.JoueurEnCours); // - joueur car recherche possible a été faite à l'envers 
		for (int i = 0; i<ChoiPossible.size(); i+=2) {
			Othello OthSauve = OthIA.CopieOthello(); // Sauvegarder le damier
			OthIA.JouerCoup(ChoiPossible.get(i)/10, ChoiPossible.get(i)%10, OthIA.JoueurEnCours); //Jouer un jeu
			OthIA.JoueurEnCours = OthIA.JoueurEnCours*(-1);
			ArrayList<Integer> Test = OthIA.FiltreRecherPossible(-OthIA.JoueurEnCours); // recherche solution pout prochain joueur
			if(Test.size() == 0) {OthIA.JoueurEnCours = OthIA.JoueurEnCours *(-1);} // Revenir au joueur précédent si pas de solut
			ScoreTMP = AlphaBeta(Pro-1,Alpha,Beta,Profondeur);
			OthIA.ChargeModel(OthSauve); // récupération damier enregistrer
			if(ScoreTMP < Beta) {
				Beta = ScoreTMP;
				//BestMove = ChoiPossible.get(i);
				if(Alpha >= Beta) {
					break;}
			}
			
		}
		return Beta;
	}
	

}

public int Eval() {
	int Score = 0; // intialisation du score
	int CoefScore = 1; // coef sur la différence de score à adapter en fonction de l'avancement de la partie
	int CoefMobilite = 1; // Coef mobilité à adapter en fonction de l'avancement de la partie
	int CoefPositionnel = 1; // Coef à adapter en fonction de l'avancement de la partie
	int ScorePostionnel = 0; // la somme du score issue de la matrice de postion
	int ScoreJoueur;
	int ScoreAdverse;
	int ScoreJeu =0; // différence des deux score	


// Calcul Score de jeu
	ScoreJoueur=OthIA.getScore(Joueur);
	ScoreAdverse = OthIA.getScore(-Joueur);
	ScoreJeu = ScoreJoueur-ScoreAdverse;
// Mise à jour des scores en fonction de l'avancement du jeu
	/*if(ScoreJoueur+ScoreAdverse > 15 ) {
		CoefMobilite = 5000;
		CoefPositionnel = 40;}
	if(ScoreJoueur+ScoreAdverse > 25 ) {
		CoefMobilite = 3000;
		CoefPositionnel= 60;}*/
	if(ScoreJoueur+ScoreAdverse ==64 ) {
		CoefScore = 100;
		CoefMobilite = 0;
		CoefPositionnel = 1;}
// Calcul du score pour le critère de mobilité
	ArrayList<Integer> SolJoueur = new ArrayList<Integer>();
	ArrayList<Integer> SolJoueurAdverse = new ArrayList<Integer>();
	SolJoueur = OthIA.FiltreRecherPossible(Joueur);
	SolJoueurAdverse = OthIA.FiltreRecherPossible(-Joueur);
// Calcul du score positionnel	
	OthIA.MiseaJourScoreDef(1,1);
	OthIA.MiseaJourScoreDef(-1,1);
	ScorePostionnel = OthIA.SommeMatrice();

	/*System.out.println("Score Score Jeu : "+(ScoreJeu)*CoefScore);
	System.out.println("Score Mobilité : "+(SolJoueur.size()-SolJoueurAdverse.size())/2 * CoefMobilite);
	System.out.println("Score Position : "+ScorePostionnel*CoefPositionnel);*/
// Déduction du score global pour blanc
	if(OthIA.JoueurEnCours == 1) {
	Score = (ScoreJeu)*CoefScore +
			(SolJoueur.size()-SolJoueurAdverse.size())/2 * CoefMobilite+
			(ScorePostionnel)*CoefPositionnel;
	/*for (int i = 1;i<9;i++) {
	System.out.println("");
	for(int j =1;j<9;j++) {
		System.out.print(" "+OthIA.DamierEvaluation[i][j]);
	}
}
	System.out.println("");
	System.out.println("Score "+1 +" "+Score);*/
	}
	//Déduction du score global pour noir
	if(OthIA.JoueurEnCours == -1)
	{Score = (-ScoreJeu)*CoefScore +
	(SolJoueurAdverse.size()-SolJoueur.size())/2 * CoefMobilite+
	(-ScorePostionnel)*CoefPositionnel;
	/*for (int i = 1;i<9;i++) {
	System.out.println("");
	for(int j =1;j<9;j++) {
		System.out.print(" "+OthIA.DamierEvaluation[i][j]);
	}
}*/
	//System.out.println("");
	//System.out.println("Score "+(-1) +" "+Score);
	}
	return Score;
	
}



public int NegaMax(int Pro,int Alpha, int Beta, int Profondeur) {
	ArrayList<Integer> ChoiPossible = new ArrayList<Integer>();
	int ScoreTMP=0;
	
	if((Pro ==0) || (OthIA.ScoreBlanc + OthIA.ScoreNoir)>64  ||
			((OthIA.JeuPossible(OthIA.JoueurEnCours*(-1)) == false && OthIA.JeuPossible(OthIA.JoueurEnCours*(1)) == false)) ) 
	{return Eval();}
	
	ChoiPossible = OthIA.FiltreRecherPossible(-OthIA.JoueurEnCours); // - joueur car recherche possible a été faite à l'envers 
	
	for (int i =0; i< ChoiPossible.size() ;i+=2 ) {
		Othello OthSauve = OthIA.CopieOthello(); // Sauvegarder le damier
		OthIA.JouerCoup(ChoiPossible.get(i)/10, ChoiPossible.get(i)%10, OthIA.JoueurEnCours); //Jouer un jeu
		OthIA.JoueurEnCours = OthIA.JoueurEnCours*(-1);
		ArrayList<Integer> Test = OthIA.FiltreRecherPossible(-OthIA.JoueurEnCours); // recherche solution pour prochain joueur
		if(Test.size() == 0) {OthIA.JoueurEnCours = OthIA.JoueurEnCours *(-1);} // Revenir au joueur précédent si pas de solut
		ScoreTMP = -NegaMax(Pro-1,-Beta,-Alpha, Profondeur);
		OthIA.ChargeModel(OthSauve); // récupération damier enregistrer
		if(ScoreTMP>= Alpha) {
			Alpha = ScoreTMP;
			if(Pro == Profondeur) {BestMoveNegaMax = ChoiPossible.get(i);}
			}
		if(Alpha>=Beta) {break;}
	}
	
	return Alpha;	
}// fin NegaMax

public int SoftAlphaBeta(int Pro,int Alpha, int Beta, int Profondeur) {
	ArrayList<Integer> ChoiPossible = new ArrayList<Integer>();
	int ScoreTMP=0;
	int Current = -100000;
	
	if((Pro ==0) || (OthIA.ScoreBlanc + OthIA.ScoreNoir)>64  ||
			((OthIA.JeuPossible(OthIA.JoueurEnCours*(-1)) == false && OthIA.JeuPossible(OthIA.JoueurEnCours*(1)) == false)) ) 
	{return Eval();}
	
	ChoiPossible = OthIA.FiltreRecherPossible(-OthIA.JoueurEnCours); // - joueur car recherche possible a été faite à l'envers 
	
	for (int i =0; i< ChoiPossible.size() ;i+=2 ) {
		Othello OthSauve = OthIA.CopieOthello(); // Sauvegarder le damier
		OthIA.JouerCoup(ChoiPossible.get(i)/10, ChoiPossible.get(i)%10, OthIA.JoueurEnCours); //Jouer un jeu
		OthIA.JoueurEnCours = OthIA.JoueurEnCours*(-1);
		ArrayList<Integer> Test = OthIA.FiltreRecherPossible(-OthIA.JoueurEnCours); // recherche solution pour prochain joueur
		if(Test.size() == 0) {OthIA.JoueurEnCours = OthIA.JoueurEnCours *(-1);} // Revenir au joueur précédent si pas de solut
		ScoreTMP = -SoftAlphaBeta(Pro-1,-Beta,-Alpha, Profondeur);
		OthIA.ChargeModel(OthSauve); // récupération damier enregistrer
		if(ScoreTMP>= Current) {
			Current = ScoreTMP;
			if(Pro == Profondeur) {BestMoveSoft = ChoiPossible.get(i);}
			if(ScoreTMP>= Alpha) {
				Alpha = ScoreTMP;
				if(Pro == Profondeur) {BestMoveSoft = ChoiPossible.get(i);}
				if(Alpha>=Beta) {break;}
				}
			}
			}
	return Current;	
}
public int MTDF(int Profondeur, int Init_G) {
	
	G = Init_G;
	int Beta;
	int UpperBound = +100000;
	int LowerBound = -100000;
	do {
		if (G ==LowerBound) {Beta = G+1;}
		else {Beta = G;}
		G = AlphaBetaMemory(Profondeur,Beta-1,Beta,Profondeur);
		if(G<Beta) {UpperBound = G;}
		else{LowerBound = G;}
			}while(LowerBound !=UpperBound);
	
	return G;	
}

public int AlphaBetaMemory(int Pro,int Alpha,int Beta, int Profondeur) {
	
	int TabPosition [] = new int [2];
	ArrayList<Integer> ChoiPossible = new ArrayList<Integer>();
	int ScoreTMP=0;
	Position = 0;
	if(RacineSave.containsKey(Position)) {
	TabPosition = (int[]) RacineSave.get(Position);
	if(TabPosition[0]>= Beta) {return TabPosition[0];}
	if(TabPosition[1]<= Alpha) {return TabPosition[1];}
	Alpha = Math.max(Alpha,TabPosition[0]);
	Beta = Math.min(Beta,TabPosition[1]);}
	
	if((Pro ==0) || (OthIA.ScoreBlanc + OthIA.ScoreNoir)>64  ||
			((OthIA.JeuPossible(OthIA.JoueurEnCours*(-1)) == false && OthIA.JeuPossible(OthIA.JoueurEnCours*(1)) == false)) ) 
	{return Eval();}
	
	int A_int = Alpha;
	int current=-100000;
	
	
ChoiPossible = OthIA.FiltreRecherPossible(-OthIA.JoueurEnCours); // - joueur car recherche possible a été faite à l'envers 
	
	for (int i =0; i< ChoiPossible.size() ;i+=2 ) {
		Othello OthSauve = OthIA.CopieOthello(); // Sauvegarder le damier
		OthIA.JouerCoup(ChoiPossible.get(i)/10, ChoiPossible.get(i)%10, OthIA.JoueurEnCours); //Jouer un jeu
		OthIA.JoueurEnCours = OthIA.JoueurEnCours*(-1);
		ArrayList<Integer> Test = OthIA.FiltreRecherPossible(-OthIA.JoueurEnCours); // recherche solution pour prochain joueur
		if(Test.size() == 0) {OthIA.JoueurEnCours = OthIA.JoueurEnCours *(-1);} // Revenir au joueur précédent si pas de solut
		ScoreTMP = -SoftAlphaBeta(Profondeur-1,-Beta,-A_int, Profondeur);
		OthIA.ChargeModel(OthSauve); // récupération damier enregistrer
		
		if(ScoreTMP>= current) {
			current = ScoreTMP;
			Position = ChoiPossible.get(i);
			if(Pro == Profondeur) {BestMoveSoft = ChoiPossible.get(i);}
			if(ScoreTMP>= A_int) {
				A_int = ScoreTMP;
				if(ScoreTMP>= Beta) {break;}}
				}
		}
	
		
	if(G<= Alpha) {
		TabPosition[1]=current;
		RacineSave.put(Position,TabPosition);}
	if(G> Alpha && G <Beta) {
		TabPosition[0]=current;
		TabPosition[1]=current;
		RacineSave.put(Position,TabPosition);}
	if(G>= Beta) {
		TabPosition[0]=current;
		RacineSave.put(Position,TabPosition);
	}
	return current;
}




public int MinMax(int Pro, int Profondeur) {
	
	if((Pro ==0) || (OthIA.ScoreBlanc + OthIA.ScoreNoir)>64 ||
			((OthIA.JeuPossible(OthIA.JoueurEnCours*(-1)) == false && OthIA.JeuPossible(OthIA.JoueurEnCours*(1)) == false)) ) 
	{return Eval();}	//Limite avec profondeur ou partie finie
	int BestSco;
		
	   if (OthIA.JoueurEnCours == Joueur) { //=Programme
		   BestSco = -100000;
		   ArrayList<Integer>   ChoiPossible = OthIA.FiltreRecherPossible(-OthIA.JoueurEnCours); // - joueur car recherche possible a été faite à l'envers
		   for (int i =0; i< ChoiPossible.size() ;i+=2 ) {
				Othello OthSauve = OthIA.CopieOthello(); // Sauvegarder le damier
				OthIA.JouerCoup(ChoiPossible.get(i)/10, ChoiPossible.get(i)%10, OthIA.JoueurEnCours); //Jouer un jeu
				OthIA.JoueurEnCours = OthIA.JoueurEnCours *(-1); // changement de joueur
			ArrayList<Integer> Test = OthIA.FiltreRecherPossible(-OthIA.JoueurEnCours); // recherche solution pout prochain joueur
			if(Test.size() == 0) {OthIA.JoueurEnCours = OthIA.JoueurEnCours *(-1);} // Revenir au joueur précédent si pas de solution	
		int	ScoreTMP = MinMax(Pro-1, Profondeur);
				OthIA.ChargeModel(OthSauve); // récupération damier enregistrer
				if(ScoreTMP >BestSco) {
					BestSco = ScoreTMP;
					if(Pro == Profondeur) {BestMove = ChoiPossible.get(i);}
					//System.out.println("Max : "+ChoiPossible.size()/2 + " " + BestMove + " Score : "+BestSco);
				}			
	         }
	      }
	   else { //type MIN = adversaire
		      BestSco = +10000;
		      ArrayList<Integer>  ChoiPossible = OthIA.FiltreRecherPossible(-OthIA.JoueurEnCours); // - joueur car recherche possible a été faite à l'envers
		      for (int i =0; i< ChoiPossible.size();i+=2 ) {
		    	  Othello OthSauve = OthIA.CopieOthello();
					OthIA.JouerCoup(ChoiPossible.get(i)/10, ChoiPossible.get(i)%10, OthIA.JoueurEnCours); //Jouer un jeu
					OthIA.JoueurEnCours = OthIA.JoueurEnCours *(-1);
					ArrayList<Integer> Test = OthIA.FiltreRecherPossible(-OthIA.JoueurEnCours); // recherche solution pout prochain joueur
					if(Test.size() == 0) {OthIA.JoueurEnCours = OthIA.JoueurEnCours *(-1);} // Revenir au joueur précédent si pas de solution	
					int	 ScoreTMP = MinMax(Pro-1,Profondeur);	
					OthIA.ChargeModel(OthSauve); // récupérer damier enregistrer	  
		         if(ScoreTMP <BestSco) {
						BestSco = ScoreTMP;
				//	System.out.println("Min : "+ChoiPossible.size()/2+ " " +BestMove+ " Score : "+BestSco);  
						}
		      }
		   }
		   return BestSco ; 	   	
} // fin MinMax



}//fin Classe
