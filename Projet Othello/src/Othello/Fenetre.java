package Othello;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.*;
import javax.swing.border.LineBorder;



public class Fenetre extends JFrame implements ActionListener,Serializable{

	private static final long serialVersionUID = 1L;
	Othello Oth = new Othello(); // import du jeu
	Othello OthAnul[] = new Othello[64];
	IntelArt IntelligenceA = new IntelArt(); // Intelligence Artificiel
	Jeu Jeux = new Jeu();
	
			// Pour jouer
	int JoueurActuelTab []= new int [61];
	int Joueur1 = 200;
	int JoueurActuel=200;
	int JoueurIA1 = 1; // IA1 inactif si = 0
	int JoueurIA2  = 10; //IA2 inactif si = 10
	int SelectJeu = 0;
	int TypePartie = 1;   //{0,1,2}; /* deux Humain / Solo / IA_IA
	int NbCoupJouer = 0;
	boolean DebutJeu;
	boolean FinJeu;
	boolean Auto = false;
	boolean Manuel = true;
	boolean FinChrono = false;
	String NomJoueur1 = "nom 1 ?";
	String NomJoueur2 = "nom 2 ?";
	String TextCaseAffiche;
	
	 //Gestion de la sauvegarde
	 boolean Sauvegarde;
	
	boolean TypeJoueurHumain;
	boolean JoueurAdverseIA  = true;
	boolean ChangementJoueur = true;
	int QuiCommence = 0;
	//Dimension fenetre
	int HauteurFenetre = 532;
	
	// import image pour test
		Icon imageCoupPossible = new ImageIcon("images/coupPossible.gif");
		Icon ImagePionNoir = new ImageIcon("images/pionNoir.gif");
		Icon ImagePionBlanc = new ImageIcon("images/pionBlanc.gif");
		Icon ImageDebut = new ImageIcon("images/couleurDenut.gif");
	// texte affichage
	private String TextJoueursEnCours ;
		
	//Barre de menu
	private JMenuBar MenuJeu;
	private JMenu IconeMenuJeu = new JMenu("Jeu");
	private JMenu IconeMenu = new JMenu("Menu");
	private JMenu IconeAutoManu = new JMenu("Type");
	private JMenu IconeCouleur = new JMenu("Sélectionner une couleur ?");
	private JMenu IconeAide = new JMenu("Aide?");
	//separateur
	private JSeparator Separateur;
	// Bouton annuler
	private JButton ButAnnulerCoup = new JButton("Annuler");
	private JButton ButtonJouer = new JButton("Jouer");
	// Icone du menue Aide
	private JMenuItem IconeAideAide = new JMenuItem("Aide");
	private JMenuItem IconeAPropos = new JMenuItem("A propos ?");
	// Icone du menu principale
	private JMenuItem IconeNouveauJeu = new JMenuItem("Nouvelle Partie");
	private JMenuItem IconeChargerJeu = new JMenuItem("Charger Partie");
	private JMenuItem IconSauvegarder = new JMenuItem("Sauvegarder Partie");
	private JMenuItem IconStatique = new JMenuItem("Statistique");
	private JMenuItem IconeQuitterJeu = new JMenuItem("Quitter");
			//Niveau du jeu
	private JMenu IconeSolo = new JMenu("Solo");
	 JRadioButtonMenuItem IconeSoloDebutant = new JRadioButtonMenuItem("Débutant");
	 JRadioButtonMenuItem IconeSoloIntermediaire = new JRadioButtonMenuItem("Intermédiaire");
	 JRadioButtonMenuItem IconeSoloProfessionel = new JRadioButtonMenuItem("Professionel");
	 JRadioButtonMenuItem IconeMultiHumain = new JRadioButtonMenuItem("Deux Joueurs");
	 private JMenu IconeMultiIA = new JMenu("Deux IA");
	 JRadioButtonMenuItem IconeIADebu_Debut = new JRadioButtonMenuItem("Débutant - Débutant");
	 JRadioButtonMenuItem IconeIAInter_Inter = new JRadioButtonMenuItem("Intermédiaire - Intermédiaire "); 
	 JRadioButtonMenuItem IconeIAPro_Pro = new JRadioButtonMenuItem("Professionel - Professionel");
	 JRadioButtonMenuItem IconeIADebu_Inter = new JRadioButtonMenuItem("Débutant - Intermédiaire");
	 JRadioButtonMenuItem IconeIAInter_Pro = new JRadioButtonMenuItem("Intermédiaire - Professionel");
	 ButtonGroup buttonGroupSolo = new ButtonGroup();
	 ButtonGroup buttonGroupCouleur = new ButtonGroup();
	 ButtonGroup buttonGroupAutoManu= new ButtonGroup();
	 //// Menue type partie automatique ou Manuel
	 JRadioButtonMenuItem IconeAuto = new JRadioButtonMenuItem("Automatique");
	 JRadioButtonMenuItem IconeManuel = new JRadioButtonMenuItem("Manuel");
		// Couleur commencer
	 JRadioButtonMenuItem IconCouleurDebutBlanc = new JRadioButtonMenuItem("Blanc");
	private JRadioButtonMenuItem IconCouleurDebutNoir = new JRadioButtonMenuItem("Noir");
		//// différents JPanel de la fenêtre
	private JPanel FondPrincipale;
	private JPanel PanelHaut;
	private JPanel PanelHautHaut;
	private JPanel PanelDroite;
	private JPanel PanelDroiteHaut;
	private JPanel PanelGauche;
	private JPanel PanelGaucheHaut;

	
	private JPanel PanelBas;
	JPanel PanelBasBas;
	private JPanel GrilleCases;
	// cases du plateau
	private JButton CasesPlateau[] = new JButton[64]; 
	// Element de texte
	JLabel ChronoIncrem = new JLabel();;
	JLabel GereTour;
	private JLabel GereCoupSN;
	private JLabel GereCoupSN1;
	private JLabel GereCoupSB;
	private JLabel GereCoupSB1;
	private JLabel GereCaseB;
	private JLabel GereCaseN;
	private JLabel TitreCaseB;
	private JLabel TitreCaseN;
	private JLabel AfficheLettre;
	private JLabel TextCase[] = new JLabel[8];
	 JLabel JoueurCaseBlanc;
	 JLabel JoueurCaseNoir;
	
	public Fenetre() {
		
		super();
		InitialiserFenetre();
		Statistique Stat = new Statistique(this,Oth);
		OthAnul[Oth.IncremJeu] = Oth.CopieOthello();
		Stat.StatParJoueur();
		/*System.out.println(Stat.HTable.keySet());
		Enumeration G = Stat.HTable.keys();
		
		Stat.StatParJoueur();
		
		int  ti[] = null;
		//int tt[][] = (int[][]) Stat.HTable.get("Débutant");
		
		try {
		int tt[][] = (int[][]) Stat.HTable.get("Débutant");
		 for (int i =0;i<tt.length;i++) {
			 System.out.println("");
		 for(int j = 0;j<tt[0].length;j++) {
			    System.out.print(" "+tt[i][j]);}}}
		catch(java.lang.ClassCastException e) {
			ti = (int[]) Stat.HTable.get("Débutant");}
			/* for (int i =0;i<ti.length;i++)
				    System.out.println(ti[i]);*/
		
	   /* while(G.hasMoreElements())
	      System.out.println(G.nextElement());*/
	 
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void InitialiserFenetre() {
		//Ajout Listener Menu
		IconeNouveauJeu.addActionListener(this);
		IconeQuitterJeu.addActionListener(this);
		IconSauvegarder.addActionListener(this);
		IconStatique.addActionListener(this);
		IconeChargerJeu.addActionListener(this);
		//Bouton annuler
		ButAnnulerCoup.addActionListener(this);
		ButtonJouer.addActionListener(this);

				
		try 
		{
				// ferme le programme grâce à la croix
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				MenuJeu = new JMenuBar();
				FondPrincipale = new JPanel();
				PanelHaut = new JPanel();
				PanelDroite = new JPanel();	
				PanelGauche = new JPanel();	
				PanelHaut = new JPanel();
				PanelBas = new JPanel();
				
				BoxLayout PanelGaucheLayout = new BoxLayout(PanelGauche, javax.swing.BoxLayout.Y_AXIS);
				PanelGauche.setLayout(PanelGaucheLayout);
				// accueille les boutons de la grille
				GrilleCases = new JPanel(); 
				// Création barre de menue
				setJMenuBar(MenuJeu);
		
				// bouton Menu
				MenuJeu.add(IconeMenu);
				IconeMenu.setBorder(BorderFactory.createTitledBorder(""));
				IconeMenu.setFont(new java.awt.Font("Arial",1,12));
				{
					IconeMenu.add(IconeNouveauJeu); 
					IconeNouveauJeu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_MASK));
					Separateur = new JSeparator();
					IconeMenu.add(Separateur);
					IconeMenu.add(IconeChargerJeu);
					IconeMenu.add(Separateur);	
					IconeMenu.add(IconSauvegarder);
					IconeMenu.add(Separateur);
					IconeMenu.add(IconStatique);
					IconeMenu.add(Separateur);
					IconeMenu.add(IconeQuitterJeu);
					IconeQuitterJeu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_MASK));
					}
				
				// ajout les différents menu de type de jeu	
				MenuJeu.add(IconeMenuJeu);
				IconeMenuJeu.setBorder(BorderFactory.createTitledBorder(""));
				IconeMenuJeu.setFont(new java.awt.Font("Arial",1,12));
				{
					 								
					IconeSolo.add(IconeSoloDebutant);
					IconeSolo.add(IconeSoloIntermediaire);
					IconeSolo.add(IconeSoloProfessionel);
					
					IconeSoloIntermediaire.setSelected( true );
			        buttonGroupSolo.add( IconeSoloIntermediaire );
			        IconeSoloIntermediaire.addActionListener( this::actionPerformed );
			        
			        IconeSoloDebutant.setSelected( false );
			        buttonGroupSolo.add( IconeSoloDebutant );
			        IconeSoloDebutant.addActionListener( this::actionPerformed );
			        
			        IconeSoloProfessionel.setSelected( false );
			        buttonGroupSolo.add( IconeSoloProfessionel );
			        IconeSoloProfessionel.addActionListener( this::actionPerformed );
					
					
					IconeMultiIA.add(IconeIADebu_Debut);
					IconeMultiIA.add(IconeIADebu_Inter);
					IconeMultiIA.add(IconeIAInter_Inter);
					IconeMultiIA.add(IconeIAInter_Pro);
					IconeMultiIA.add(IconeIAPro_Pro);
					
					IconeIAPro_Pro.setSelected(false );
			        buttonGroupSolo.add( IconeIAPro_Pro );
			        IconeIAPro_Pro.addActionListener( this::actionPerformed );
					
					IconeIAInter_Pro.setSelected( false );
			        buttonGroupSolo.add( IconeIAInter_Pro );
			        IconeIAInter_Pro.addActionListener( this::actionPerformed );
			        
					IconeIAInter_Inter.setSelected( false );
				        buttonGroupSolo.add( IconeIAInter_Inter );
				        IconeIAInter_Inter.addActionListener( this::actionPerformed );
				        
					IconeIADebu_Inter.setSelected( false );
				        buttonGroupSolo.add( IconeIADebu_Inter );
				        IconeIADebu_Inter.addActionListener( this::actionPerformed );
				        
						IconeIADebu_Debut.setSelected( false );
				        buttonGroupSolo.add( IconeIADebu_Debut );
				        IconeIADebu_Debut.addActionListener( this::actionPerformed );
					
					IconeMenuJeu.add(IconeMultiHumain);
					IconeMenuJeu.add(Separateur);	
					IconeMenuJeu.add(IconeSolo);
					IconeMenuJeu.add(Separateur);	
					IconeMenuJeu.add(IconeMultiIA);
					
					IconeMultiHumain.setSelected( false );
			        buttonGroupSolo.add( IconeMultiHumain );
			        IconeMultiHumain.addActionListener( this::actionPerformed );
					
					}
		        // Selection type de partie
		    	MenuJeu.add(IconeAutoManu);
		    	IconeAutoManu.setBorder(BorderFactory.createTitledBorder(""));
		    	IconeAutoManu.setFont(new java.awt.Font("Arial",1,12));
				
				{IconeAutoManu.add(IconeAuto); 								
				IconeAutoManu.add(Separateur);
				IconeAutoManu.add(IconeManuel);
				
				buttonGroupAutoManu.add(IconeAuto);
				IconeAuto.addActionListener( this::actionPerformed );
				buttonGroupAutoManu.add(IconeManuel);
				IconeManuel.setSelected( true );
				IconeManuel.addActionListener( this::actionPerformed );
				
				}


				// Selectionner une couleur
				MenuJeu.add(IconeCouleur);
				IconeCouleur.setBorder(BorderFactory.createTitledBorder(""));
				IconeCouleur.setFont(new java.awt.Font("Arial",1,12));
				{
					IconeCouleur.add(IconCouleurDebutBlanc); 								
					IconeCouleur.add(Separateur);
					IconeCouleur.add(IconCouleurDebutNoir);}
				
				IconCouleurDebutBlanc.setSelected( true );
		        buttonGroupCouleur.add( IconCouleurDebutBlanc );
		        IconCouleurDebutBlanc.addActionListener( this::actionPerformed );
		        
		        IconCouleurDebutNoir.setSelected( false );
		        buttonGroupCouleur.add( IconCouleurDebutNoir );
		        IconCouleurDebutNoir.addActionListener( this::actionPerformed );
		        // Création menu Aide
		        MenuJeu.add(IconeAide);
		        IconeAide.setBorder(BorderFactory.createTitledBorder(""));
		        IconeAide.setFont(new java.awt.Font("Arial",1,12));
				{IconeAide.add(IconeAideAide);
		        MenuJeu.add(Separateur);
		        IconeAide.add(IconeAPropos);}
		        
		        IconeAideAide.addActionListener( this::actionPerformed );
		        IconeAPropos.addActionListener( this::actionPerformed );

		        
				// crée une disposition et on l'applique au panel fond Principale
			BorderLayout jPanel1Layout = new BorderLayout();
			FondPrincipale.setLayout(jPanel1Layout);
			
			getContentPane().add(FondPrincipale, BorderLayout.CENTER);
			{
				//Panel du bas
				FondPrincipale.add(PanelBas,BorderLayout.SOUTH);
				PanelBas.setPreferredSize(new java.awt.Dimension(HauteurFenetre,80));
				{
					PanelBasBas = new JPanel();
					GridBagLayout PanelBasBasLayout = new GridBagLayout();
					PanelBas.add(PanelBasBas);
					PanelBas.setBackground( Color.LIGHT_GRAY);
					PanelBasBas.setBackground(Color.LIGHT_GRAY);
					PanelBasBas.setPreferredSize(new java.awt.Dimension(HauteurFenetre,70));
					PanelBasBasLayout.rowWeights = new double[] {0.0,0.0};
					PanelBasBasLayout.rowHeights = new int[] {35,35};
					PanelBasBasLayout.columnWeights = new double[] {0.0, 0.5, 0.0, 0.1,0.0, 0.0, 0.0, 0.0 ,0.0, 0.0, 0.0, 
							0.0, 0.0, 0.0, 0.0,0.0, 0.0, 0.0, 0.0 ,0.0, 0.0, 0.0, 0.0, 0.0 };
					PanelBasBasLayout.columnWidths = new int[] {HauteurFenetre/24,HauteurFenetre/24,HauteurFenetre/24
							,HauteurFenetre/24,HauteurFenetre/24,HauteurFenetre/24,HauteurFenetre/24,HauteurFenetre/24,
							HauteurFenetre/24,HauteurFenetre/24,HauteurFenetre/24
							,HauteurFenetre/24,HauteurFenetre/24,HauteurFenetre/24,HauteurFenetre/24,HauteurFenetre/24
							,HauteurFenetre/24,HauteurFenetre/24,HauteurFenetre/24,HauteurFenetre/24
							,HauteurFenetre/24,HauteurFenetre/24,HauteurFenetre/24,HauteurFenetre/24};
					PanelBasBas.setLayout(PanelBasBasLayout);
					
					{	
					    AfficheLettre  = new JLabel();
						GridBagConstraints C = new GridBagConstraints(0, 0, 24, 2, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
						PanelBasBas.add(AfficheLettre,C);
						String Lettre[] = {"A","B","C","D","E","F","G","H"};
						int ll=1;
						for (int i =0; i< 8; i++) {
						TextCase[i] = new JLabel();
						C.gridx = ll;
						ll+=3;
						PanelBasBas.add(TextCase[i],C);
						TextCase[i].setLocation(50,50);
						TextCase[i].setFont(new java.awt.Font("Arial",1,14));
						TextCase[i].setForeground(Color.black);
						TextCase[i].setText(""+Lettre[i]);}
								
						   C.gridx = 8;
						    C.gridy = 1;
						    //La taille en hauteur et en largeur
						ButAnnulerCoup.setSize(100,20);		
						PanelBasBas.add(ButAnnulerCoup, C);
						   C.gridx = 12;
						    C.gridy = 1;
						    //La taille en hauteur et en largeur
						ButtonJouer.setSize(20,20);	
						PanelBasBas.add(ButtonJouer, C);
						
						//Chronomètre
						   C.gridx = 16;
						    C.gridy = 1;
						PanelBasBas.add(ChronoIncrem, C);
						ChronoIncrem.setFont(new java.awt.Font("Arial",1,16));
						ChronoIncrem.setForeground(Color.red);
						ChronoIncrem.setText("0 S");
						
						
					}
					
					
				}
				
			
				
		
				//Panel du haut
				FondPrincipale.add(PanelHaut,BorderLayout.NORTH);
				PanelHaut.setPreferredSize(new java.awt.Dimension(HauteurFenetre,50));
				{
					PanelHautHaut = new JPanel();
					GridBagLayout PanelHautHautLayout = new GridBagLayout();
					PanelHaut.add(PanelHautHaut);
					PanelHaut.setBackground( Color.LIGHT_GRAY);
					PanelHautHaut.setBackground(Color.LIGHT_GRAY);
					PanelHautHaut.setPreferredSize(new java.awt.Dimension(HauteurFenetre,80));
					PanelHautHautLayout.rowWeights = new double[] {0.0, 0.0, 0.1, 0.0, 0.0 };
					PanelHautHautLayout.rowHeights = new int[] {40, 40, 20, 20, 30};
					PanelHautHautLayout.columnWeights = new double[] {0.1};
					PanelHautHautLayout.columnWidths = new int[] {7};
					PanelHautHaut.setLayout(PanelHautHautLayout);
					
					{
						GereTour = new JLabel();
						PanelHautHaut.add(GereTour, new GridBagConstraints(0, 0, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						GereTour.setFont(new java.awt.Font("Arial",1,14));
						GereTour.setForeground(Color.white);
						GereTour.setText(" Sélectionner une couleur ?");
						
						AfficheLettre  = new JLabel();
						PanelHautHaut.add(AfficheLettre, new GridBagConstraints(0, 1, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						AfficheLettre.setFont(new java.awt.Font("Arial",1,14));
						AfficheLettre.setForeground(Color.black);
						AfficheLettre.setText("A                B              C              D              E              F              G              H");
						
					}
					
				}
				
				// Panel de droite
				FondPrincipale.add(PanelDroite, BorderLayout.EAST);
				PanelDroite.setPreferredSize(new java.awt.Dimension(120, HauteurFenetre));
				{
						// panel de droite avec la disposition  GridBagLayout
					PanelDroiteHaut = new JPanel();
					GridBagLayout PanelDroiteHautLayout = new GridBagLayout();
					PanelDroite.add(PanelDroiteHaut);
					PanelDroite.setBackground( Color.LIGHT_GRAY);
					PanelDroiteHaut.setBackground(Color.LIGHT_GRAY);
					PanelDroiteHaut.setPreferredSize(new java.awt.Dimension(120, HauteurFenetre));
					PanelDroiteHautLayout.rowWeights = new double[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
					PanelDroiteHautLayout.rowHeights = new int[] {HauteurFenetre/16,HauteurFenetre/16,HauteurFenetre/16
							,HauteurFenetre/16,HauteurFenetre/16,HauteurFenetre/16,HauteurFenetre/16,HauteurFenetre/16,
							HauteurFenetre/16,HauteurFenetre/16,HauteurFenetre/16
							,HauteurFenetre/16,HauteurFenetre/16,HauteurFenetre/16,HauteurFenetre/16,HauteurFenetre/16};
					PanelDroiteHautLayout.columnWeights = new double[] {0.1};
					PanelDroiteHautLayout.columnWidths = new int[] {7};
					PanelDroiteHaut.setLayout(PanelDroiteHautLayout);
					
					{
						GereCoupSB1 = new JLabel();
						PanelDroiteHaut.add(GereCoupSB1, new GridBagConstraints(0, 4, 1, 5, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						GereCoupSB1.setFont(new java.awt.Font("Arial",1,16));
						GereCoupSB1.setForeground(Color.white);
						GereCoupSB1.setText(" Score Blanc");
						
						GereCoupSB = new JLabel();
						PanelDroiteHaut.add(GereCoupSB, new GridBagConstraints(0, 5, 1, 5, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						GereCoupSB.setFont(new java.awt.Font("Arial",1,20));
						GereCoupSB.setForeground(Color.white);
						GereCoupSB.setText("0");
						
						TitreCaseB = new JLabel();
						PanelDroiteHaut.add(TitreCaseB, new GridBagConstraints(0, 6, 1, 5, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						TitreCaseB.setFont(new java.awt.Font("Arial",1,16));
						TitreCaseB.setForeground(Color.white);
						TitreCaseB.setText("Case jouée");
						
						GereCaseB = new JLabel();
						PanelDroiteHaut.add(GereCaseB, new GridBagConstraints(0, 7, 1, 5, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						GereCaseB.setFont(new java.awt.Font("Arial",1,16));
						GereCaseB.setForeground(Color.white);
						GereCaseB.setText(" ? ");
						
						JoueurCaseBlanc = new JLabel();
						PanelDroiteHaut.add(JoueurCaseBlanc, new GridBagConstraints(0, 0, 1, 5, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						JoueurCaseBlanc.setFont(new java.awt.Font("Arial",1,16));
						JoueurCaseBlanc.setForeground(Color.white);
						JoueurCaseBlanc.setText(" ? ");
						
						
						
						GridBagConstraints C = new GridBagConstraints(0, 0, 1, 16, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
						for (int i =0; i< 8; i++) {
						TextCase[i] = new JLabel();
						C.gridy = 2*i+1;
						PanelDroiteHaut.add(TextCase[i],C);
						TextCase[i].setLocation(50,50);
						TextCase[i].setFont(new java.awt.Font("Arial",1,16));
						TextCase[i].setForeground(Color.black);
						TextCase[i].setText(""+(i+1));}
						
					}
				}
				
				
						//Paramétrage panel de gauche
				FondPrincipale.add(PanelGauche, BorderLayout.WEST);
				PanelGauche.setPreferredSize(new java.awt.Dimension(120, HauteurFenetre));
				{					
					// panel de gauche ( haut ) avec la disposition  GridBagLayout
					PanelGaucheHaut = new JPanel();
					GridBagLayout PanelGaucheHautLayout = new GridBagLayout();
					PanelGauche.add(PanelGaucheHaut);
					PanelGauche.setBackground(Color.LIGHT_GRAY);
					PanelGaucheHaut.setBackground(Color.LIGHT_GRAY);
					PanelGaucheHaut.setPreferredSize(new java.awt.Dimension(120, HauteurFenetre));
					PanelGaucheHautLayout.rowWeights = new double[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
					PanelGaucheHautLayout.rowHeights = new int[] {HauteurFenetre/16,HauteurFenetre/16,HauteurFenetre/16
							,HauteurFenetre/16,HauteurFenetre/16,HauteurFenetre/16,HauteurFenetre/16,HauteurFenetre/16,
							HauteurFenetre/16,HauteurFenetre/16,HauteurFenetre/16
							,HauteurFenetre/16,HauteurFenetre/16,HauteurFenetre/16,HauteurFenetre/16,HauteurFenetre/16};
					PanelGaucheHautLayout.columnWeights = new double[] {10};
					PanelGaucheHautLayout.columnWidths = new int[] {7};
					PanelGaucheHaut.setLayout(PanelGaucheHautLayout);				
					{
							
						GereCoupSN1 = new JLabel();
						PanelGaucheHaut.add(GereCoupSN1, new GridBagConstraints(0, 4, 1, 5, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						GereCoupSN1.setFont(new java.awt.Font("Arial",1,16));
						GereCoupSN1.setForeground(Color.black);
						GereCoupSN1.setText(" Score Noir");
						
						GereCoupSN = new JLabel();
						PanelGaucheHaut.add(GereCoupSN, new GridBagConstraints(0, 5, 1, 5, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						GereCoupSN.setFont(new java.awt.Font("Arial",1,20));
						GereCoupSN.setForeground(Color.black);
						GereCoupSN.setText("0");
						
						TitreCaseN = new JLabel();
						PanelGaucheHaut.add(TitreCaseN, new GridBagConstraints(0, 6, 1, 5, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						TitreCaseN.setFont(new java.awt.Font("Arial",1,16));
						TitreCaseN.setForeground(Color.black);
						TitreCaseN.setText("Case jouée");
						
						GereCaseN = new JLabel();
						PanelGaucheHaut.add(GereCaseN, new GridBagConstraints(0, 7, 1, 5, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						GereCaseN.setFont(new java.awt.Font("Arial",1,16));
						GereCaseN.setForeground(Color.black);
						GereCaseN.setText(" ? ");
						
						JoueurCaseNoir = new JLabel();
						PanelGaucheHaut.add(JoueurCaseNoir, new GridBagConstraints(0, 0, 1, 5, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						JoueurCaseNoir.setFont(new java.awt.Font("Arial",1,16));
						JoueurCaseNoir.setForeground(Color.black);
						JoueurCaseNoir.setText(" ? ");
						
						GridBagConstraints C = new GridBagConstraints(0, 0, 1, 16, 0.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
						for (int i =0; i< 8; i++) {
						TextCase[i] = new JLabel();
						C.gridy = 2*i+1;
						PanelGaucheHaut.add(TextCase[i],C);
						TextCase[i].setLocation(50,50);
						TextCase[i].setFont(new java.awt.Font("Arial",1,16));
						TextCase[i].setForeground(Color.black);
						TextCase[i].setText(""+(i+1));}
						
						
							
						}
					
					}
				

				}
			
			
			
			{
				// grilles principale du jeux GridLayout avec les 64 cases
			GrilleCases.setLayout(new GridLayout(8,8));
			FondPrincipale.add(GrilleCases, BorderLayout.CENTER);
			GrilleCases.setPreferredSize(new java.awt.Dimension(HauteurFenetre, HauteurFenetre));	
				// Génération des cases
			{
				
				
				for (int i = 0; i < 64; i++)	
				{
					CasesPlateau[i] = new JButton(); // création nouveau bouton
					CasesPlateau[i].setBackground(new Color(20,204,163)); // couleur vert/bleu
					CasesPlateau[i].setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false)); // les bords en Noir			
					// ajout les actions aux Cases
					CasesPlateau[i].addActionListener(this);
					GrilleCases.add(CasesPlateau[i]);
				}
			}
			
		
		}
			
			
			

			
			// Appliquer le tableau initial ne sert à rien
			//PionDepart(); // afficher les pion du début de jeu 
						
			pack();
			setTitle("Bienvenue au jeu Othello"); // Titre de la fenêtre
			setVisible(true); // Visualiser la fenêtre
			setResizable(false); // Faire ne sorte que la fenêtre ne soit pas modifiable en dimension
			// centrage de la fenêtre à l'écran
			java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();  
			pack(); 
			/* la fenêtre prend sa taille... */ 
			setLocation( (screenSize.width-getWidth())/2,(screenSize.height-getHeight())/2 
			        );
			}} catch (Exception e) {
	e.printStackTrace();
	}} // fin création de la fenêtre
	
	//Affichage des coups possible
	//afficher coups possible
	public void RecherCoupPossible(int JoueurSel)
	{
		ArrayList<Integer> Coup_Possible = Oth.RecherPossible(-JoueurSel);
		
		for (int i = 0; i< Coup_Possible.size(); i+=3){
			switch(Coup_Possible.get(i)/10) {
			case 1:
			CasesPlateau[Coup_Possible.get(i)%10-1].setIcon(imageCoupPossible);
			break;
			case 2:
			CasesPlateau[Coup_Possible.get(i)%10+8-1].setIcon(imageCoupPossible);
			break;
			case 3:
			CasesPlateau[Coup_Possible.get(i)%10+16-1].setIcon(imageCoupPossible);
			break;
			case 4:
			CasesPlateau[Coup_Possible.get(i)%10+24-1].setIcon(imageCoupPossible);
			break;
			case 5:
			CasesPlateau[Coup_Possible.get(i)%10+32-1].setIcon(imageCoupPossible);
			break;
			case 6:
			CasesPlateau[Coup_Possible.get(i)%10+40-1].setIcon(imageCoupPossible);
			break;
			case 7:
			CasesPlateau[Coup_Possible.get(i)%10+48-1].setIcon(imageCoupPossible);
			break;
			case 8:
			CasesPlateau[Coup_Possible.get(i)%10+56-1].setIcon(imageCoupPossible);
			break;
			
			}
		}
	}
	// Mise à jour affichage après coup jouer
	public void MiseAJourAffichage() {
		AfficheCaseJouer();
		GereCoupSB.setText(""+Oth.ScoreBlanc);
		GereCoupSN.setText(""+Oth.ScoreNoir);
	}
	// Repère le joueur pour slectionner coup possible
public void JoueurEnCours()
{
	if (Oth.JoueurEnCours == 1) {
		GereTour.setForeground(Color.white);
		TextJoueursEnCours = "Blanc";}
	else {
		TextJoueursEnCours = "Noir";
		GereTour.setForeground(Color.black);}
	GereTour.setText("Joueur " +TextJoueursEnCours +" à vous de  jouer");
}
// Mise à jour interface
public void CoupJouer() {
	int IncremCase = 0;
	for (int i = 1; i < 9; i++)	{
		for (int j = 1; j < 9; j++)
		{		// remet les cases à la bonne couleur
			switch(Oth.damier[i][j]) {
			case 1:
				CasesPlateau[IncremCase].setIcon(ImagePionBlanc);
			break;
			case -1:
				CasesPlateau[IncremCase].setIcon(ImagePionNoir);
			break;
			case 2:
				CasesPlateau[IncremCase].setIcon(ImageDebut);
			}		
			IncremCase++;}	
			}
}
//Routine bouton jouer
public void BoutonJouer() {

	
	if(FinJeu == false) {		
			if(JoueurActuelTab[Oth.IncremJeu] == JoueurIA1 ) {
	FinChrono = false;
	ChronoPanel Chrono = new ChronoPanel(this);
		Chrono.start();
		SelectJeu = IntelligenceA.ChoixIa(JoueurIA1,Oth);Jouer();FinChrono = true;
		
		}
	
	else if(JoueurActuelTab[Oth.IncremJeu] == JoueurIA2) {
		FinChrono = false;
		ChronoPanel Chrono = new ChronoPanel(this);
		Chrono.start();
		SelectJeu = IntelligenceA.ChoixIa(JoueurIA2,Oth);
		FinChrono = true;
		Jouer();}
	}
	else {
		MessageErreuJeu();
	}
	
}
//choix du jeux selon selection 
public void Jouer() {
	
	
if(Oth.TestCoup(SelectJeu) == true) {//Si le coup est valide on prend le jeu

	//Sauvegarde pour bouton annuler ou sauchegarder une partie
	OthAnul[Oth.IncremJeu] = Oth.CopieOthello();	
	
	
	if (TypePartie == 0) {	
		
		Jeux.MultiJoueur(Oth, this);	
	}
	
	if(TypePartie == 1) {
		Jeux.JeuSolo(Oth, this);
	}
	
	if(TypePartie == 2) {


	
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	update(getGraphics()); // mise à jour du graphique

	
		//paint(getGraphics());	
		Jeux.JeuIAIA(Oth, this);
	
	}
}
}//fin jouer
 

//Choix aléatoire pour couleur qui va commencer
//Choisir qui commence
public void AfficheNomJoueurIA2() {
	
	if (JoueurIA2 == 10 && Oth.JoueurEnCours == -1) {JoueurCaseNoir.setText("Débutant");}
	if (JoueurIA2 == 20 && Oth.JoueurEnCours == -1) {JoueurCaseNoir.setText("Intermédiaire");}
	if (JoueurIA2 == 30 && Oth.JoueurEnCours == -1) {JoueurCaseNoir.setText("Professionel");}
	if (JoueurIA2 == 10 && Oth.JoueurEnCours == 1) {JoueurCaseBlanc.setText("Débutant");}
	if (JoueurIA2 == 20 && Oth.JoueurEnCours == 1) {JoueurCaseBlanc.setText("Intermédiaire");}
	if (JoueurIA2 == 30 && Oth.JoueurEnCours == 1) {JoueurCaseBlanc.setText("Professionel");}
}

public void AfficheNomJoueurIA1() {
	
	if (JoueurIA1 == 0 && Oth.JoueurEnCours == -1) {JoueurCaseNoir.setText("Débutant");}
	if (JoueurIA1 == 1 && Oth.JoueurEnCours == -1) {JoueurCaseNoir.setText("Intermédiaire");}
	if (JoueurIA1 == 2 && Oth.JoueurEnCours == -1) {JoueurCaseNoir.setText("Professionel");}
	if (JoueurIA1 == 0 && Oth.JoueurEnCours == 1) {JoueurCaseBlanc.setText("Débutant");}
	if (JoueurIA1 == 1 && Oth.JoueurEnCours == 1) {JoueurCaseBlanc.setText("Intermédiaire");}
	if (JoueurIA1 == 2 && Oth.JoueurEnCours == 1) {JoueurCaseBlanc.setText("Professionel");}
}


public void QuiCommenceJeu()
{
	CoupJouer(); // Mise à jour de l'affichage du damier
	
Random rand = new Random();
QuiCommence = rand.nextInt(50);
QuiCommence = 2;
if(QuiCommence%2 == 0) {
	
	switch(TypePartie) {
	case 0:
		if (Oth.JoueurEnCours ==1) {
			JoueurCaseNoir.setText(NomJoueur2);
			JoueurCaseBlanc.setText(NomJoueur1);}
			else {
				JoueurCaseNoir.setText(NomJoueur1);
				JoueurCaseBlanc.setText(NomJoueur2);
			}
		JoueurActuelTab[Oth.IncremJeu] = Joueur1;
		
		AfficherJoueur();
		RecherCoupPossible(Oth.JoueurEnCours);
		break;
	case 1:
		if (Oth.JoueurEnCours ==1) {JoueurCaseBlanc.setText(NomJoueur1);}
			else {JoueurCaseNoir.setText(NomJoueur1);}
		Oth.JoueurEnCours = Oth.JoueurEnCours*(-1); // changement de joueur pour afficher le niveau
		AfficheNomJoueurIA1();
		Oth.JoueurEnCours = Oth.JoueurEnCours*(-1); // Redonne la main au bon joueur
		AfficherJoueur();
		RecherCoupPossible(Oth.JoueurEnCours);
		JoueurActuelTab[Oth.IncremJeu] = Joueur1;
		break;
	case 2:
		AfficheNomJoueurIA1();
		Oth.JoueurEnCours = Oth.JoueurEnCours*(-1); // changement de joueur pour afficher le niveau 
		AfficheNomJoueurIA2();
		Oth.JoueurEnCours = Oth.JoueurEnCours*(-1); // Redonne la main au bon joueur
		AfficherJoueur();
		JoueurActuelTab[Oth.IncremJeu] = JoueurIA1;
		SelectJeu = IntelligenceA.ChoixIa(JoueurIA1 , Oth);	// IA1 joue
		RecherCoupPossible(Oth.JoueurEnCours);
		Jouer();
		break;} //fin switch


} // fin cas 1


else if (QuiCommence%2 != 0){
	
	switch(TypePartie) {
	case 0:
		if (Oth.JoueurEnCours ==1) {
			JoueurCaseNoir.setText(NomJoueur2);
			JoueurCaseBlanc.setText(NomJoueur1);}
			else {
				JoueurCaseNoir.setText(NomJoueur1);
				JoueurCaseBlanc.setText(NomJoueur2);
			}
		JoueurActuelTab[Oth.IncremJeu] = Joueur1;
		Oth.JoueurEnCours = Oth.JoueurEnCours*(-1); // changement de joueur car c'est l'autre qui commence
		AfficherJoueur();
		RecherCoupPossible(Oth.JoueurEnCours);
		break;
	case 1:
		if (Oth.JoueurEnCours ==1) {JoueurCaseBlanc.setText(NomJoueur1);}
			else {JoueurCaseNoir.setText(NomJoueur1);}
		Oth.JoueurEnCours = Oth.JoueurEnCours*(-1); // changement de joueur car c'est l'autre qui commence
		AfficheNomJoueurIA1();
		JoueurActuelTab[Oth.IncremJeu] = JoueurIA1;
		SelectJeu = IntelligenceA.ChoixIa(JoueurIA1 , Oth);	// IA1 joue
		RecherCoupPossible(Oth.JoueurEnCours);
		Jouer();
		break;
	case 2:
		AfficheNomJoueurIA1();
		Oth.JoueurEnCours = Oth.JoueurEnCours*(-1); // changement de joueur car c'est l'autre qui commence
		AfficheNomJoueurIA2();
		AfficherJoueur();
		JoueurActuelTab[Oth.IncremJeu] = JoueurIA2;
		SelectJeu = IntelligenceA.ChoixIa(JoueurIA2 , Oth);	// IA2 joue
		Jouer();
		break;} //fin switch
}//fin Cas 2


}

//Routine fin  de partie
//Gestion fin ce partie
public void FinDePartie() {
	String messageType;
	//Gestion des message de fin de partie
	if (Oth.ScoreBlanc < Oth.ScoreNoir) {
		messageType  = "Bravo le joueur Noir a gagné";
	GereTour.setForeground(Color.black);
	GereTour.setText("Le joueur Noir a gnagné ... !!");
	Oth.CaseJouer[60]=-1;
	}
	else if (Oth.ScoreBlanc > Oth.ScoreNoir) {
		messageType  = "Bravo le joueur Blanc a gagné";
	GereTour.setForeground(Color.white);
	GereTour.setText("Le joueur Blanc a gnagné ... !!");
	Oth.CaseJouer[60]=1;}
	else {
		messageType  = "Egalité";
	GereTour.setForeground(Color.red);
	GereTour.setText("!!... Égalité ... !!");
	Oth.CaseJouer[60]=0;}
Message(messageType);

}

//Message feneêtre fin de partie
public void Message(String messageType) {
	JOptionPane Message = new JOptionPane();
	JOptionPane.showMessageDialog( Message, messageType, 
		      "Bravo", JOptionPane.INFORMATION_MESSAGE);
}
//Routine nouveau jeu
public void NouveuJeu() {
Oth = new Othello(); // intialisation du damier
CoupJouer(); // Mettre à jour l'affichage de départ
DebutJeu = false;
FinJeu = false;
IconCouleurDebutBlanc.setEnabled(true);
IconCouleurDebutNoir.setEnabled(true);
MiseAJourAffichage();
// remettre à zéro case jouer
TextCaseAffiche = " ";
Oth.JoueurEnCours = Oth.JoueurEnCours*(-1); 
AfficheCaseJouer(); 
Oth.JoueurEnCours = Oth.JoueurEnCours*(-1);
AfficheCaseJouer(); 

// Message choix de couleur
GereTour.setForeground(Color.white);
GereTour.setText("Sélectionner une couleur ?");
}
//Mise à jour message joueur en cours
//Mise à jour affichage joueur en cours
public void AfficherJoueur() {
	if (Oth.JoueurEnCours == 1) {
	GereTour.setForeground(Color.white);
	GereTour.setText("Joueur Blanc à vous de jouer");}
	else {
	GereTour.setForeground(Color.black);
	GereTour.setText("Joueur Noir à vous de jouer");}
}
public void MessageErreuJeu() {
	
	if(FinJeu == false && DebutJeu == true) {
		if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
	if (Oth.JoueurEnCours == 1) {
		GereTour.setForeground(Color.red);
		TextJoueursEnCours = "Blanc";}
	else {
		TextJoueursEnCours = "Noir";
		GereTour.setForeground(Color.red);}
	GereTour.setText("Joueur " +TextJoueursEnCours +" selctionnez une case valide");}
	else if(FinJeu == false && DebutJeu == false || FinJeu == false && DebutJeu == true) {
	JOptionPane.showMessageDialog(this, " C'est L'IA qui doit jouer, appuyer sur Jouer ! ","Erreur", JOptionPane.ERROR_MESSAGE);
	}}
	
	if (FinJeu == true) {
		JOptionPane.showMessageDialog(this, " Veuillez démarrer une nouvelle partie ! ","Erreur", JOptionPane.ERROR_MESSAGE);}
}
//Convertie case
public void ConvertCaseJouer(int CaseJouerLocal) {

	
	if (CaseJouerLocal%10 == 1) {TextCaseAffiche = "A"+CaseJouerLocal/10;}
	if (CaseJouerLocal%10 == 2) {TextCaseAffiche = "B"+CaseJouerLocal/10;}
	if (CaseJouerLocal%10 == 3) {TextCaseAffiche = "C"+CaseJouerLocal/10;}
	if (CaseJouerLocal%10 == 4) {TextCaseAffiche = "D"+CaseJouerLocal/10;}
	if (CaseJouerLocal%10 == 5) {TextCaseAffiche = "E"+CaseJouerLocal/10;}
	if (CaseJouerLocal%10 == 6) {TextCaseAffiche = "F"+CaseJouerLocal/10;}
	if (CaseJouerLocal%10 == 7) {TextCaseAffiche = "G"+CaseJouerLocal/10;}
	if (CaseJouerLocal%10 == 8) {TextCaseAffiche = "H"+CaseJouerLocal/10;}
	
}
//Affiche case
public void AfficheCaseJouer() {
	
	if(Oth.JoueurEnCours == 1) {
		GereCaseB.setText(TextCaseAffiche);
	}
	else {
		GereCaseN.setText(TextCaseAffiche);
	}
 
}
// Action des Listeners
// gestion des groupe de bouton
public void ActiverDesactiverBouton(boolean ActivDesac) {
	if (ActivDesac == false) {		
		//IconeSolo.setEnabled(false);
		IconeSoloDebutant.setEnabled(false);
		IconeSoloIntermediaire.setEnabled(false);
		IconeSoloProfessionel.setEnabled(false);
		IconeMultiHumain.setEnabled(false);
		IconeIADebu_Debut.setEnabled(false);
		IconeIAInter_Inter.setEnabled(false);
		IconeIAPro_Pro.setEnabled(false);
		IconeIADebu_Inter.setEnabled(false);
		IconeIAInter_Pro.setEnabled(false);
		
	}
	
	if (ActivDesac == true) {	
		
		IconeSoloDebutant.setEnabled(true);
		IconeSoloIntermediaire.setEnabled(true);
		IconeSoloProfessionel.setEnabled(true);
		IconeMultiHumain.setEnabled(true);
		IconeIADebu_Debut.setEnabled(true);
		IconeIAInter_Inter.setEnabled(true);
		IconeIAPro_Pro.setEnabled(true);
		IconeIADebu_Inter.setEnabled(true);
		IconeIAInter_Pro.setEnabled(true);
		
	}
	
}
//Gestion de la sauvegarde 
public Othello chargerUnModele()
{
	Othello Sauv = new Othello(); // initialiser un damier
	try 
	{			
		// ouverture d'un flux d'entrée depuis le fichier "personne.serial"
		FileInputStream fluxEntreeFichier = new FileInputStream("Sauvegarde/Partie.ser");
		// création d'un "flux objet" avec le flux fichier
		ObjectInputStream fluxEntreeObjet= new ObjectInputStream(fluxEntreeFichier);
		try 
		{						//Chargement du modèle
		Othello SS = (Othello) fluxEntreeObjet.readObject();	
		Sauv = SS.CopieSauvOthello();
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
				
			return Sauv;
			}	

public void PreparationSauvegarde() {

Oth.TypePartieSauv = TypePartie;
Oth.NomJoueurSauv1 = JoueurCaseBlanc.getText();
Oth.NomJoueurSauv2 = JoueurCaseNoir.getText();
for (int i = 0; i< Oth.JoueurActuelTabSauvegarde.length; i++) {
	Oth.JoueurActuelTabSauvegarde[i] = JoueurActuelTab[i];
}

for (int i = 0; i< Oth.IncremJeu; i++) {
	Oth.OthSauvAnul[i] = OthAnul[i].CopieOthello();
}

}

public void SauverUnePartie() {	
	
	// Affecter des valeurs aux variables de gestion de sauvegarde spécifique
	PreparationSauvegarde();
	Othello Sauv = Oth.CopieSauvOthello(); // création d'une copie du damier
	
	try {	
		
		// ouverture d'un flux de sortie vers un fichier
		FileOutputStream fluxSortieFichier = new FileOutputStream("Sauvegarde/Partie.ser");
		// création d'un "flux objet" avec le flux fichier
		ObjectOutputStream fluxSortieObjet= new ObjectOutputStream(fluxSortieFichier);
		try {
			// sérialisation : écriture de l'objet dans le flux de sortie
				fluxSortieObjet.writeObject(Sauv); 		
				fluxSortieObjet.writeObject(TypePartie); 	
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
	Sauvegarde = true;}

@Override

	public void actionPerformed(ActionEvent e) {
	
	// Création objet SourceDuClic
	Object SourceDuClic = e.getSource();
	

	// Choix type de jeu
	if (SourceDuClic == IconeMultiHumain) {TypePartie = 0;
	TypeJoueurHumain = true;
	JoueurAdverseIA = false;

		NomJoueur1 = JOptionPane.showInputDialog(this, "Entrer le nom du joueur ", " ");	
		NomJoueur2 = JOptionPane.showInputDialog(this, "Entrer le nom du deuxième joueur", " ");	
		JoueurCaseBlanc.setText("?");
		JoueurCaseNoir.setText("?");
	}
	
	//type automatique ou manuel
	if (SourceDuClic == IconeManuel) {Auto = false; Manuel = true;}
	if (SourceDuClic == IconeAuto) {Auto = true; Manuel = false;}
	//Choix couleur 
	if (SourceDuClic == IconCouleurDebutBlanc) {
	Oth.JoueurEnCours= 1;
	IconCouleurDebutBlanc.setEnabled(false);
	IconCouleurDebutNoir.setEnabled(false);
	ActiverDesactiverBouton(false);
	DebutJeu = true;
	QuiCommenceJeu();}
	if (SourceDuClic == IconCouleurDebutNoir) {	
	Oth.JoueurEnCours = -1;
	IconCouleurDebutNoir.setEnabled(false);
	IconCouleurDebutBlanc.setEnabled(false);
	ActiverDesactiverBouton(false);
	DebutJeu = true;
	QuiCommenceJeu();}
	//Initialiser jeu (nouvelle partie)
	if(SourceDuClic == IconeNouveauJeu) {NouveuJeu();ActiverDesactiverBouton(true);}
	// joue deux humain
	if(SourceDuClic == IconeSoloDebutant) {
		TypePartie = 1;
		JoueurAdverseIA = true;
		TypeJoueurHumain = true;
		NomJoueur1 = JOptionPane.showInputDialog(this, "Entrer le nom du joueur ", " ");	
		JoueurIA1 = 0 ;}
	// Jeu Solo intermédiaire
	if(SourceDuClic == IconeSoloIntermediaire) {
		TypePartie = 1;
		JoueurAdverseIA = true;
		TypeJoueurHumain = true;
		NomJoueur1 = JOptionPane.showInputDialog(this, "Entrer le nom du joueur ", " ");	
		JoueurIA1 = 1 ;}
	if(SourceDuClic == IconeSoloProfessionel) {
		TypePartie = 1;
		JoueurAdverseIA = true;
		TypeJoueurHumain = true;
		NomJoueur1 = JOptionPane.showInputDialog(this, "Entrer le nom du joueur ", " ");	
		JoueurIA1 = 2 ;}
		if(SourceDuClic == IconeIADebu_Debut) {
		TypePartie = 2;
		JoueurAdverseIA = true;
		TypeJoueurHumain = false;
		JoueurIA1 = 0 ;
		JoueurIA2= 10 ;}
	if(SourceDuClic == IconeIAInter_Inter) {
		TypePartie = 2;
		JoueurAdverseIA = true;
		TypeJoueurHumain = false;
		JoueurIA1 = 1 ;
		JoueurIA2 = 20 ;}	
	if(SourceDuClic == IconeIADebu_Inter) {
		TypePartie = 2;
		JoueurAdverseIA = true;
		TypeJoueurHumain = false;
		JoueurIA1 = 0 ;
		JoueurIA2 = 20 ;}	
	if(SourceDuClic == IconeIAInter_Pro) {
		TypePartie = 2;
		JoueurAdverseIA = true;
		TypeJoueurHumain = false;
		JoueurIA1 = 1 ;
		JoueurIA2 = 30 ;}	
	if(SourceDuClic == IconeIAPro_Pro) {
		TypePartie = 2;
		JoueurAdverseIA = true;
		TypeJoueurHumain = false;
		JoueurIA1 = 2 ;
		JoueurIA2 = 30 ;}	
	//Gestion bouton Aide
	if (SourceDuClic == IconeAideAide){	Desktop d = Desktop.getDesktop();
	File AideFile = new File("Aide_Othello.pdf");
	try {
		d.open(AideFile);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} }
	//Bouton statistique
	if (SourceDuClic == IconStatique) {
		Statistique Stat = new Statistique(this,Oth);
		Stat.StatParJoueur();
	Stat.AfficheStat();
	
	}
	//Gestion bouton à propos
	if (SourceDuClic == IconeAPropos){  
	
		  try {
			try {
				Desktop.getDesktop().browse(new URI("http://www.ffothello.org/othello/"));
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}}
    

	
	// Gestion charger une partie
	if (SourceDuClic == IconeChargerJeu){
		Othello SS = new Othello();
		SS = chargerUnModele();
		Oth = SS.CopieSauvOthello();
		JoueurCaseBlanc.setText(Oth.NomJoueurSauv1);
		JoueurCaseNoir.setText(Oth.NomJoueurSauv2);
		TypePartie = Oth.TypePartieSauv;
		for (int i = 0; i< Oth.JoueurActuelTabSauvegarde.length; i++) {
			JoueurActuelTab[i] = Oth.JoueurActuelTabSauvegarde[i];
		}
		for (int i =0; i<Oth.IncremJeu;i++) {
			OthAnul[i]=Oth.OthSauvAnul[i].CopieOthello();
		}
		CoupJouer();
		RecherCoupPossible(Oth.JoueurEnCours);
		MiseAJourAffichage(); // Mise a jour score
		AfficherJoueur(); //Mise à jour qui va jouer
		switch(TypePartie) {
		case 0:
			IconeMultiHumain.setSelected( true );
		case 1:
			if(Oth.NomJoueurSauv2 == "Intermédiaire") {IconeSoloIntermediaire.setSelected( true );}
			if(Oth.NomJoueurSauv2 == "Débutant") {IconeSoloDebutant.setSelected( true );}
			if(Oth.NomJoueurSauv2 == "Professionel") {IconeSoloProfessionel.setSelected( true );}		
		case 2:
			if(Oth.NomJoueurSauv1 == "Débutant" && Oth.NomJoueurSauv2 == "Débutant") {IconeIADebu_Debut.setSelected( true );}
			if(Oth.NomJoueurSauv1 == "Débutant" && Oth.NomJoueurSauv2 == "Intermédiaire") {IconeIADebu_Inter.setSelected( true );}
			if(Oth.NomJoueurSauv1 == "Intermédiaire" && Oth.NomJoueurSauv2 == "Intermédiaire") {IconeIAInter_Inter.setSelected( true );}
			if(Oth.NomJoueurSauv1 == "Intermédiaire" && Oth.NomJoueurSauv2 == "Professionel") {IconeIAInter_Pro.setSelected( true );}
			if(Oth.NomJoueurSauv1 == "Professionel" && Oth.NomJoueurSauv2 == "Professionel") {IconeIAPro_Pro.setSelected( true );}			
		}
		ActiverDesactiverBouton(false);
		IconCouleurDebutBlanc.setEnabled(false);
		IconCouleurDebutNoir.setEnabled(false);
		
		
		
		 // Routine pour afficher les case jouer
		// Routine pour afficher les cases jouer
				 if (Oth.IncremJeu >=2) {
					 ConvertCaseJouer(Oth.CaseJouer[Oth.IncremJeu-2]) ;
					 AfficheCaseJouer(); 
					 ConvertCaseJouer(Oth.CaseJouer[Oth.IncremJeu-1]) ;
					 Oth.JoueurEnCours = Oth.JoueurEnCours*(-1); 
					 AfficheCaseJouer(); 
					 Oth.JoueurEnCours = Oth.JoueurEnCours*(-1);}
				 else {	TextCaseAffiche = "?";
//				 Oth.JoueurEnCours = Oth.JoueurEnCours*(-1); 
				 AfficheCaseJouer(); 
			//	 Oth.JoueurEnCours = Oth.JoueurEnCours*(-1); 
				 }
				 // Annuler la fin de la partie
				 FinJeu = false;
		
	
	}
	
	
	//Gestion bouton annuler
	if (SourceDuClic == ButAnnulerCoup) {
		if(Oth.IncremJeu >= 1) {Oth.ChargeModel(OthAnul[Oth.IncremJeu-1]);}
		 CoupJouer(); // Mise à jour damier afficher
		 MiseAJourAffichage(); // Mise a jour score
		 RecherCoupPossible(Oth.JoueurEnCours); //Afficher le spossibilité de jeu
		 AfficherJoueur(); //Mise à jour qui va jouer
		 // Routine pour afficher les cases jouer
		 if (Oth.IncremJeu >=2) {
			 ConvertCaseJouer(Oth.CaseJouer[Oth.IncremJeu-2]) ;
			 AfficheCaseJouer(); 
			 ConvertCaseJouer(Oth.CaseJouer[Oth.IncremJeu-1]) ;
			 Oth.JoueurEnCours = Oth.JoueurEnCours*(-1); 
			 AfficheCaseJouer(); 
			 Oth.JoueurEnCours = Oth.JoueurEnCours*(-1);}
		 else {	TextCaseAffiche = "?";
//		 Oth.JoueurEnCours = Oth.JoueurEnCours*(-1); 
		 AfficheCaseJouer(); 
	//	 Oth.JoueurEnCours = Oth.JoueurEnCours*(-1); 
		 }
		 // Annuler la fin de la partie
		 FinJeu = false;
	}
	
	//Gestion bouton Jouer
		
	if(SourceDuClic == ButtonJouer) {BoutonJouer();}
	

	
	//Gestion bouton suavegarde
	if(SourceDuClic == IconSauvegarder) {
	
		SauverUnePartie();
	}
	//Gestion bouton Quitter
	if( SourceDuClic== IconeQuitterJeu) {
		System.exit(0);}

	// Action après selection de chaque case
	if (SourceDuClic == CasesPlateau[0]){SelectJeu = 11;MessageErreuJeu();
	if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[1]){SelectJeu = 12; MessageErreuJeu();
	if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[2]){SelectJeu = 13; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[3]){SelectJeu = 14; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[4]){SelectJeu = 15; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[5]){SelectJeu = 16; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[6]){SelectJeu = 17; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[7]){SelectJeu = 18; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[8]){SelectJeu = 21; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[9]){SelectJeu = 22; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[10]){SelectJeu = 23; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[11]){SelectJeu = 24; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[12]){SelectJeu = 25; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[13]){SelectJeu = 26; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[14]){SelectJeu = 27; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[15]){SelectJeu = 28; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[16]){SelectJeu = 31; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[17]){SelectJeu = 32;MessageErreuJeu(); if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[18]){SelectJeu = 33; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[19]){SelectJeu = 34; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[20]){SelectJeu = 35;MessageErreuJeu(); if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[21]){SelectJeu = 36; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[22]){SelectJeu = 37; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[23]){SelectJeu = 38;MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[24]){SelectJeu = 41; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[25]){SelectJeu = 42;MessageErreuJeu(); if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[26]){SelectJeu = 43; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[27]){SelectJeu = 44;MessageErreuJeu();}
	if (SourceDuClic == CasesPlateau[28]){SelectJeu = 45;MessageErreuJeu();}
	if (SourceDuClic == CasesPlateau[29]){SelectJeu = 46; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[30]){SelectJeu = 47; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[31]){SelectJeu = 48; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[32]){SelectJeu = 51; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[33]){SelectJeu = 52;MessageErreuJeu(); if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[34]){SelectJeu = 53; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[35]){SelectJeu = 54;MessageErreuJeu();}
	if (SourceDuClic == CasesPlateau[36]){SelectJeu = 55;MessageErreuJeu();}
	if (SourceDuClic == CasesPlateau[37]){SelectJeu = 56;MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[38]){SelectJeu = 57; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[39]){SelectJeu = 58;MessageErreuJeu(); if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[40]){SelectJeu = 61;MessageErreuJeu(); if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[41]){SelectJeu = 62;MessageErreuJeu(); if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[42]){SelectJeu = 63; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[43]){SelectJeu = 64; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[44]){SelectJeu = 65; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[45]){SelectJeu = 66;MessageErreuJeu(); if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[46]){SelectJeu = 67; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[47]){SelectJeu = 68; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[48]){SelectJeu = 71; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[49]){SelectJeu = 72; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[50]){SelectJeu = 73;MessageErreuJeu(); if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[51]){SelectJeu = 74; MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[52]){SelectJeu = 75;MessageErreuJeu(); if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[53]){SelectJeu = 76;MessageErreuJeu(); if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[54]){SelectJeu = 77;MessageErreuJeu(); if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[55]){SelectJeu = 78;MessageErreuJeu(); if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[56]){SelectJeu = 81;MessageErreuJeu(); if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[57]){SelectJeu = 82;MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[58]){SelectJeu = 83;MessageErreuJeu(); if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[59]){SelectJeu = 84;MessageErreuJeu(); if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[60]){SelectJeu = 85;MessageErreuJeu(); if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[61]){SelectJeu = 86;MessageErreuJeu(); if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[62]){SelectJeu = 87;MessageErreuJeu();if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}
	if (SourceDuClic == CasesPlateau[63]){SelectJeu = 88;MessageErreuJeu(); if(JoueurActuelTab[Oth.IncremJeu] == Joueur1) {
		Jouer();}
	}




		
	}
	}
