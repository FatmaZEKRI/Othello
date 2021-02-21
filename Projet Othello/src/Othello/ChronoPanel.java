package Othello;


public class ChronoPanel extends Thread{

	int time = 0;
	
	Fenetre Fe;
	
	public ChronoPanel(Fenetre Fe) {
		this.Fe = Fe;}

	public void run() {

		long chrono0 = java.lang.System.currentTimeMillis() /1000;
		
		while(Fe.FinChrono == false) {
			
		
		long chrono1 = java.lang.System.currentTimeMillis()/1000 - chrono0;
		
		if (chrono1 -time >1) {
			time ++;
			Fe.ChronoIncrem.setText(""+time);
			Fe.PanelBasBas.repaint();
			//System.out.println(""+time + " S");
		}
		
		if (time >= 60) {Fe.FinChrono = true;}
		
	}

	}
	

}
