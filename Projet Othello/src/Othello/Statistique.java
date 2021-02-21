package Othello;

import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class Statistique extends JFrame implements Serializable{

	private static final long serialVersionUID = 22052020;
	public int MatCaseJouer[][] = new int[1][61];
	Fenetre Fe;
	Othello Oth;
	boolean ExistHtable = false;
	String TextNoir;
	String TextBlanc;
	int MatNoir[][];
	Hashtable HTable = new Hashtable();
	
	//Variable interface statistique
	private JPanel FondPanel;
	private JPanel FondStat;
	private JPanel PanNom;
	private JPanel PanNbPartie;
	private JPanel PanNbNull;
	private JPanel PanNBGain;
	private JPanel PanNBPerdue;
	private JPanel PanPourcent;
	private JPanel PanReset;
	int NbAffiche = 10;
	int Nbinfo = 6;
	private JLabel LabTitreNom;
	private JLabel LabtitreNbPar;
	private JLabel LabtitreNbGain;
	private JLabel LabtitreNBPerdue;
	private JLabel LabtitreNBNulle;
	private JLabel LabtitrePourcent;
	private JLabel LabNomTab[] = new JLabel[NbAffiche];
	private JLabel LabNbPartie[] = new JLabel[NbAffiche];
	private JLabel LabNbGain[] = new JLabel[NbAffiche];
	private JLabel LabNbPerdue[] = new JLabel[NbAffiche];
	private JLabel LabNbNulle[] = new JLabel[NbAffiche];
	private JLabel LabPourcent[] = new JLabel[NbAffiche];
	private JButton ResetButton = new JButton("Reset");
	
	String TabNom[] = new String[NbAffiche];
	double TabPourcent[] = new double[NbAffiche];
	int TabNbTot[] = new int[NbAffiche];
	int TabGain[] = new int[NbAffiche];
	int TabNull[] = new int[NbAffiche];
	int TabPerdu[] = new int[NbAffiche];

	
	
	public Statistique(Fenetre Fe, Othello Oth) {
		this.Fe = Fe;
		this.Oth = Oth;
		
		File f = new File("Sauvegarde/HTABLE.ser"); 		
		if(f.exists()) {
			HTable = chargerHTable();
		}
		else {
			Hashtable HTable = new Hashtable();
		}
		
		}
	
	
public void StatParJoueur() {
	
ArrayList<String> ListeJoueur = new ArrayList<String>();
ArrayList<Integer> ListeNbTot = new ArrayList<Integer>();
ArrayList<Integer> ListeGain = new ArrayList<Integer>();
ArrayList<Integer> ListeNull = new ArrayList<Integer>();
ArrayList<Integer> ListePerdu = new ArrayList<Integer>();
ArrayList<Double> ListePourcent = new ArrayList<Double>();
int nb_joueur = NbAffiche;


	Enumeration G = HTable.keys();
	while(G.hasMoreElements()) {
		
		int NombrePartieGa =0;
		int NombrePartieNull=0;
		int NombrePartiePer=0;
		int NombrePartie=0;
		
		String Nom = ""+G.nextElement();
		try {
		int Mat[][] = (int[][]) HTable.get(Nom);
		NombrePartie = Mat.length;
		
		for(int i = 0; i<NombrePartie ; i++) {
			if(Mat[i][60] == 1) {NombrePartieGa++;}
			if(Mat[i][60] == 0) {NombrePartieNull++;}
		}
		NombrePartiePer = NombrePartie - NombrePartieGa - NombrePartieNull;
		}
		catch(java.lang.ClassCastException e) {
		int tab[] = (int[]) HTable.get(Nom);
		NombrePartie = 1;
		if(tab[60] == 1) {NombrePartieGa++;}
		if(tab[60] == 0) {NombrePartieNull++;}
		if(tab[60] == -1) {NombrePartiePer++;}}
		double Poucentage = ((double)NombrePartieGa)/NombrePartie*100;
		ListeJoueur.add(Nom);
		ListePourcent.add(Poucentage);
		ListeNbTot.add(NombrePartie);
		ListeGain.add(NombrePartieGa);
		ListeNull.add(NombrePartieNull);
		ListePerdu.add(NombrePartiePer);}
	int JJ = 0;
int increm = 0;
int TailleMat = ListeJoueur.size();
	while(ListeJoueur.size()>0 && increm<nb_joueur && increm<TailleMat) {
		JJ = Max(ListePourcent);
		TabNom[increm] = ListeJoueur.get(JJ);
		TabPourcent[increm] = Math.round(ListePourcent.get(JJ));
		//TabPourcent[increm] = (ListePourcent.get(JJ));
		TabNbTot[increm] = ListeNbTot.get(JJ);
		TabGain[increm] = ListeGain.get(JJ);
		TabNull[increm] = ListeNull.get(JJ);
		TabPerdu[increm] = ListePerdu.get(JJ);
		
		
		
		
		ListeJoueur.remove(JJ);
		ListePourcent.remove(JJ);
		ListeNbTot.remove(JJ);
		ListeGain.remove(JJ);
		ListeNull.remove(JJ);
		ListePerdu.remove(JJ);
		increm++;	
	}    
	

}

public void AfficheStat() {
	int hauteur = 500;
	int largeur = 750;
	int hauteurBouton = 50;
	
	try {
			// ferme le programme grâce à la croix
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			
			FondPanel = new JPanel();
			
			// crée une disposition et on l'applique au panel fond Principale
			BorderLayout jPanel1Layout = new BorderLayout();
			FondPanel.setLayout(jPanel1Layout);
			getContentPane().add(FondPanel, BorderLayout.CENTER);
			FondPanel.setPreferredSize(new java.awt.Dimension(largeur,hauteur+hauteurBouton));
			GridBagLayout PanPrince = new GridBagLayout();
			int tablar[] = new int[Nbinfo];
			for(int i =0;i<Nbinfo;i++) {tablar[i] = largeur/Nbinfo;}
			PanPrince.columnWidths = tablar;
			FondPanel.setLayout(PanPrince);
			FondPanel.setBackground( Color.gray);
			GridBagConstraints Princ = new GridBagConstraints(0, 0, 0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
			
					//Panel des noms
			PanNom = new JPanel();
			FondPanel.add(PanNom,Princ);
			PanNom.setPreferredSize(new java.awt.Dimension(largeur/Nbinfo,hauteur));
			GridBagLayout PanNomLayout = new GridBagLayout();	
			PanNom.setBackground( Color.GRAY);
			//PanNomLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0 };
			int tabrow[] = new int[NbAffiche];
			for(int i = 0; i<NbAffiche;i++) {tabrow[i] = hauteur/(NbAffiche+1);}
			PanNomLayout.rowHeights = tabrow;
			PanNomLayout.columnWeights = new double[] {0.0};
			PanNomLayout.columnWidths = new int[] {largeur/Nbinfo};
			PanNom.setLayout(PanNomLayout);
			
				LabTitreNom = new JLabel();
				GridBagConstraints C = new GridBagConstraints(0, 0, 1, 0, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
				PanNom.add(LabTitreNom, C);
				LabTitreNom.setFont(new java.awt.Font("Arial",1,14));
				LabTitreNom.setForeground(Color.white);
				LabTitreNom.setText("Joueurs");
				C.anchor = GridBagConstraints.NORTHWEST;
				for(int i =0;i<NbAffiche;i++) {
					LabNomTab[i] = new JLabel();
					C.gridy = i+1;
					PanNom.add(LabNomTab[i],C);
					LabNomTab[i].setFont(new java.awt.Font("Arial",1,14));
					LabNomTab[i].setForeground(Color.white);
					LabNomTab[i].setText(" "+(i+1)+" - "+TabNom[i]);
					if(TabNom[i] == null) {LabNomTab[i].setText("");}	
						}
				
				//Panel des nombres de partie
				PanNbPartie = new JPanel();
				Princ.gridx = 1;
				FondPanel.add(PanNbPartie,Princ);
				PanNbPartie.setPreferredSize(new java.awt.Dimension(largeur/Nbinfo,hauteur));
				GridBagLayout PanNBPartieLayout = new GridBagLayout();	
				PanNbPartie.setBackground( Color.GRAY);
				for(int i = 0; i<NbAffiche;i++) {tabrow[i] = hauteur/(NbAffiche+1);}
				PanNBPartieLayout.rowHeights = tabrow;
				PanNBPartieLayout.columnWeights = new double[] {0.0};
				PanNBPartieLayout.columnWidths = new int[] {largeur/Nbinfo};
				PanNbPartie.setLayout(PanNBPartieLayout);
				
				    LabtitreNbPar = new JLabel();
					GridBagConstraints C1 = new GridBagConstraints(0, 0, 1, 0, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
					PanNbPartie.add(LabtitreNbPar, C1);
					LabtitreNbPar.setFont(new java.awt.Font("Arial",1,14));
					LabtitreNbPar.setForeground(Color.white);
					LabtitreNbPar.setText("Nb Partie");
					for(int i =0;i<NbAffiche;i++) {
						LabNbPartie[i] = new JLabel();
						C1.gridy = i+1;
						PanNbPartie.add(LabNbPartie[i],C1);
						LabNbPartie[i].setFont(new java.awt.Font("Arial",1,14));
						LabNbPartie[i].setForeground(Color.white);
						LabNbPartie[i].setText(""+TabNbTot[i]);
						if(TabNbTot[i] == 0) {LabNbPartie[i].setText("");}
					}
					
					
					//Panel des nombres de partie gagnées
					PanNBGain = new JPanel();
					Princ.gridx = 2;
					FondPanel.add(PanNBGain,Princ);
					PanNBGain.setPreferredSize(new java.awt.Dimension(largeur/Nbinfo,hauteur));
					GridBagLayout PanNBGainLayout = new GridBagLayout();	
					PanNBGain.setBackground( Color.GRAY);
					for(int i = 0; i<NbAffiche;i++) {tabrow[i] = hauteur/(NbAffiche+1);}
					PanNBGainLayout.rowHeights = tabrow;
					PanNBGainLayout.columnWeights = new double[] {0.0};
					PanNBGainLayout.columnWidths = new int[] {largeur/Nbinfo};
					PanNBGain.setLayout(PanNBGainLayout);
					
					    LabtitreNbGain = new JLabel();
						GridBagConstraints C2 = new GridBagConstraints(0, 0, 1, 0, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
						PanNBGain.add(LabtitreNbGain, C2);
						LabtitreNbGain.setFont(new java.awt.Font("Arial",1,14));
						LabtitreNbGain.setForeground(Color.white);
						LabtitreNbGain.setText("Nb Gain");
						for(int i =0;i<NbAffiche;i++) {
							LabNbGain[i] = new JLabel();
							C2.gridy = i+1;
							PanNBGain.add(LabNbGain[i],C2);
							LabNbGain[i].setFont(new java.awt.Font("Arial",1,14));
							LabNbGain[i].setForeground(Color.white);
							LabNbGain[i].setText(""+TabGain[i]);
							if(TabNbTot[i] == 0) {LabNbGain[i].setText("");}
						}
						//Panel des nombres de partie perdues
						PanNBPerdue = new JPanel();
						Princ.gridx = 3;
						FondPanel.add(PanNBPerdue,Princ);
						PanNBPerdue.setPreferredSize(new java.awt.Dimension(largeur/Nbinfo,hauteur));
						GridBagLayout PanNBPerdLayout = new GridBagLayout();	
						PanNBPerdue.setBackground( Color.GRAY);
						for(int i = 0; i<NbAffiche;i++) {tabrow[i] = hauteur/(NbAffiche+1);}
						PanNBGainLayout.rowHeights = tabrow;
						PanNBGainLayout.columnWeights = new double[] {0.0};
						PanNBGainLayout.columnWidths = new int[] {largeur/Nbinfo};
						PanNBPerdue.setLayout(PanNBGainLayout);
						
						
						    LabtitreNBPerdue = new JLabel();
							GridBagConstraints C3 = new GridBagConstraints(0, 0, 1, 0, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
							PanNBPerdue.add(LabtitreNBPerdue, C3);
							LabtitreNBPerdue.setFont(new java.awt.Font("Arial",1,14));
							LabtitreNBPerdue.setForeground(Color.white);
							LabtitreNBPerdue.setText("Nb Perdue");
							for(int i =0;i<NbAffiche;i++) {
								LabNbPerdue[i] = new JLabel();
								C3.gridy = i+1;
								PanNBPerdue.add(LabNbPerdue[i],C3);
								LabNbPerdue[i].setFont(new java.awt.Font("Arial",1,14));
								LabNbPerdue[i].setForeground(Color.white);
								LabNbPerdue[i].setText(""+TabPerdu[i]);
								if(TabNbTot[i] == 0) {LabNbPerdue[i].setText("");}
							}
							//Panel des nombres de partie nulle
							PanNbNull = new JPanel();
							Princ.gridx = 4;
							FondPanel.add(PanNbNull,Princ);
							PanNbNull.setPreferredSize(new java.awt.Dimension(largeur/Nbinfo,hauteur));
							GridBagLayout PanNBNullLayout = new GridBagLayout();	
							PanNbNull.setBackground( Color.gray);
							for(int i = 0; i<NbAffiche;i++) {tabrow[i] = hauteur/(NbAffiche+1);}
							PanNBNullLayout.rowHeights = tabrow;
							PanNBNullLayout.columnWeights = new double[] {0.0};
							PanNBNullLayout.columnWidths = new int[] {largeur/Nbinfo};
							PanNbNull.setLayout(PanNBNullLayout);
							
							    LabtitreNBNulle = new JLabel();
								GridBagConstraints C4 = new GridBagConstraints(0, 0, 1, 0, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
								PanNbNull.add(LabtitreNBNulle, C4);
								LabtitreNBNulle.setFont(new java.awt.Font("Arial",1,14));
								LabtitreNBNulle.setForeground(Color.white);
								LabtitreNBNulle.setText("Nb Nulle");
								for(int i =0;i<NbAffiche;i++) {
									LabNbNulle[i] = new JLabel();
									C4.gridy = i+1;
									PanNbNull.add(LabNbNulle[i],C4);
									LabNbNulle[i].setFont(new java.awt.Font("Arial",1,14));
									LabNbNulle[i].setForeground(Color.white);
									LabNbNulle[i].setText(""+TabNull[i]);
									if(TabNbTot[i] == 0) {LabNbNulle[i].setText("");}
								}
								//Panel des nombres % gain
								PanPourcent = new JPanel();
								Princ.gridx++;
								FondPanel.add(PanPourcent,Princ);
								PanPourcent.setPreferredSize(new java.awt.Dimension(largeur/Nbinfo,hauteur));
								GridBagLayout PanPourcentLayout = new GridBagLayout();	
								PanPourcent.setBackground( Color.gray);
								for(int i = 0; i<NbAffiche;i++) {tabrow[i] = hauteur/(NbAffiche+1);}
								PanPourcentLayout.rowHeights = tabrow;
								PanPourcentLayout.columnWeights = new double[] {0.0};
								PanPourcentLayout.columnWidths = new int[] {largeur/Nbinfo};
								PanPourcent.setLayout(PanPourcentLayout);
								
								    LabtitrePourcent = new JLabel();
									GridBagConstraints C5 = new GridBagConstraints(0, 0, 1, 0, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
									PanPourcent.add(LabtitrePourcent, C5);
									LabtitrePourcent.setFont(new java.awt.Font("Arial",1,14));
									LabtitrePourcent.setForeground(Color.white);
									LabtitrePourcent.setText("% Gain");
									for(int i =0;i<NbAffiche;i++) {
										LabPourcent[i] = new JLabel();
										C5.gridy = i+1;
										PanPourcent.add(LabPourcent[i],C5);
										LabPourcent[i].setFont(new java.awt.Font("Arial",1,14));
										LabPourcent[i].setForeground(Color.white);
										LabPourcent[i].setText(""+TabPourcent[i]);
										if(TabNbTot[i] == 0) {LabPourcent[i].setText("");}
									}
									
						// Panel bouton RESET
						PanReset = new JPanel();
						GridBagLayout Panbut = new GridBagLayout();	
						Panbut.rowHeights = new int[]{hauteur/3,hauteur/3,hauteur/3};
						PanReset.setPreferredSize(new java.awt.Dimension(largeur,hauteurBouton));
						PanReset.setLayout(Panbut);
						FondPanel.add(PanReset,new GridBagConstraints(0, 0, 0, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						PanReset.setBackground( Color.gray);
						PanReset.add(ResetButton,new GridBagConstraints(0, 0, 0, 0, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						ResetButton.addActionListener(this::actionPerformed);
		
			
			
			pack();
			setTitle("Statistique du jeu"); // Titre de la fenêtre
			setVisible(true); // Visualiser la fenêtre
			setResizable(false); // Faire ne sorte que la fenêtre ne soit pas modifiable en dimension
			// centrage de la fenêtre à l'écran
			java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
			setLocation( (screenSize.width-getWidth())/2,(screenSize.height-getHeight())/2);}
			catch (Exception e) {
				e.printStackTrace();
				}
					
}

public void actionPerformed(ActionEvent e) {
	// Création objet SourceDuClic
	Object SourceDuClic = e.getSource();
	if(SourceDuClic == ResetButton) {
	HTable = new Hashtable();
	 SauvHTable();
	 StatParJoueur();
	 
	 for (int i = 0; i<TabNom.length;i++) {
		TabNom[i] = "";
		LabNomTab[i].setText(""+TabNom[i]);
		LabNbGain[i].setText(""+TabNom[i]);
		LabNbNulle[i].setText(""+TabNom[i]);
		LabNbPartie[i].setText(""+TabNom[i]);
		LabPourcent[i].setText(""+TabNom[i]);
		LabNbGain[i].setText(""+TabNom[i]);
		LabNbPerdue[i].setText(""+TabNom[i]);}
	 update(getGraphics()); // mise à jour du graphique
	
		
	}
}

public int Max(ArrayList<Double> tab) {
double MaxValue = -200.00;
int MaxI = 0;

for(int i = 0; i<tab.size();i++) {
	if(tab.get(i)>MaxValue) {
		MaxValue = tab.get(i);
		MaxI = i;
	}
}

return MaxI;
}
public void SauvHTable() {	

	
	try {	
		
		// ouverture d'un flux de sortie vers un fichier
		FileOutputStream fluxSortieFichier = new FileOutputStream("Sauvegarde/HTABLE.ser");
		// création d'un "flux objet" avec le flux fichier
		ObjectOutputStream fluxSortieObjet= new ObjectOutputStream(fluxSortieFichier);
		try {
			// sérialisation : écriture de l'objet dans le flux de sortie
				fluxSortieObjet.writeObject(HTable); 			
				// on vider le tampon
			fluxSortieObjet.flush();
			} 
		finally {
				//fermeture des flux
			try {
				fluxSortieObjet.close();
			} 
			finally {
				fluxSortieFichier.close();
			}
		}
	} 
	catch(IOException ioe)
	{
		ioe.printStackTrace();}
	ExistHtable = true;}
	

public Hashtable chargerHTable()
{
	Hashtable HTab = new Hashtable();
	try 
	{			
		// ouverture d'un flux d'entrée depuis le fichier "personne.serial"
		FileInputStream fluxEntreeFichier = new FileInputStream("Sauvegarde/HTABLE.ser");
		// création d'un "flux objet" avec le flux fichier
		ObjectInputStream fluxEntreeObjet= new ObjectInputStream(fluxEntreeFichier);
		try 
		{						//Chargement du modèle
			 HTab = (Hashtable) fluxEntreeObjet.readObject();	
		} 
		
		finally 
			{
					// on ferme les flux
				try {
					fluxEntreeObjet.close();
				} finally {
					fluxEntreeFichier.close();
				}
			}
	} catch(IOException ioe) 
		{		} catch(ClassNotFoundException cnfe)
			{
				cnfe.printStackTrace();};
				
			return HTab;
			}

public void AjoutHtable(int[] Tab) {
	TextNoir = Fe.JoueurCaseNoir.getText();
	TextBlanc = Fe.JoueurCaseBlanc.getText();
	int tabTest[] = null;
	if(HTable.containsKey(TextNoir)) {
		try {
		int	Mat[][] = (int[][]) HTable.get(TextNoir);
		MatNoir = MiseAJourMat(Mat, Tab);
		int Long = MatNoir.length;
		int Lar = MatNoir[0].length;
		MatNoir[Long-1][Lar-1] = MatNoir[Long-1][Lar-1]*(-1);
	HTable.put(TextNoir,MatNoir);}
		catch(java.lang.ClassCastException e){
			int Mat[][] = new int[2][61];
			tabTest = (int[]) HTable.get(TextNoir);
		for (int i=0;i<tabTest.length;i++){
			Mat[1][i] = Tab[i];
			Mat[0][i] = tabTest[i];
			}
		
		Mat[1][tabTest.length-1] = Mat[1][tabTest.length-1]*(-1);
		HTable.put(TextNoir,Mat);
		}
		}
	
	else if(HTable.containsKey(TextNoir) == false){
		Tab[Tab.length-1] = Tab[Tab.length-1]*(-1);
		int Mat1[][] = new int[1][Tab.length];
		for (int i = 0; i<Tab.length;i++) {
			Mat1[0][i] = Tab[i];
		}
		Tab[Tab.length-1] = Tab[Tab.length-1]*(-1);
		HTable.put(TextNoir,Mat1);}


	if(HTable.containsKey(TextBlanc)) {
		try {
		int Mat[][] = (int[][]) HTable.get(TextBlanc);
		MatNoir = MiseAJourMat(Mat, Tab);
		HTable.put(TextBlanc,MatNoir);}
		catch(java.lang.ClassCastException e){
			int Mat[][] = new int[2][61];
			tabTest = (int[]) HTable.get(TextBlanc);
		for (int i=0;i<tabTest.length;i++){
			Mat[1][i] = Tab[i];
			Mat[0][i] = tabTest[i];}
		HTable.put(TextBlanc,Mat);}
		}
	else if(HTable.containsKey(TextBlanc) == false){HTable.put(TextBlanc,Tab);}
	
}

public int[][] MiseAJourMat(int[][] mat, int[] tab) {
	
	int Long = mat.length;
	int Lar = mat[0].length;
	int MatAJour[][] = new int [Long+1][Lar];
	
	for(int i=0;i<Long;i++) {
		for(int j =0; j<Lar;j++) {
			MatAJour[i][j] = mat[i][j]; 	
		}
	}
	for(int i =0; i<Lar;i++) {
		MatAJour[Long][i] = tab[i];
	}
	
return MatAJour;
	}
	
	
	

}
