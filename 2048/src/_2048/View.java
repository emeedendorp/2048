package _2048;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class View
  extends JPanel
{
  int x = 20;
  int y = 20;
  int breedte = 400;
  int hoogte = 400;
  int rijen = 5;
  int kolommen = 4;
  Color background = Color.GRAY;
  Color foreground = Color.BLACK;
  ArrayList<Integer> waarden = new ArrayList<Integer>(this.rijen * this.kolommen);
  private static final long serialVersionUID = 1L;
  int newx;
  int newy;
  FontMetrics fm;
  
  public View() {
    
	  //maak een array aan met waarden 0
	  for (int i = 0; i < this.rijen * this.kolommen; i++) {
      this.waarden.add(0);
    }
	  //voeg een getal toe op een willekeurige plek
    int beginwaarde1 = (int)(Math.random() * this.rijen * this.kolommen);
    this.waarden.set(beginwaarde1, 2);
    	//voeg nog een getal toe op een willekeurige plek, anders dan de vorige plek
    int beginwaarde2 = (int)(Math.random() * this.rijen * this.kolommen);
    while (beginwaarde1 == beginwaarde2) {
      beginwaarde2 = (int)(Math.random() * this.rijen * this.kolommen);
    }
    this.waarden.set(beginwaarde2, 2);
  }
  
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    
    teken(g);
    tekenhokjes(g);
  }
  
  public void teken(Graphics g)
  {
	  // instellingen hard gecodeerd in de class
	  g.setColor(this.background);
	  g.fillRect(this.x, this.y, this.breedte, this.hoogte);
	  g.setColor(this.foreground);
	  
	  for (int i = 0; i <= this.rijen; i++) {
      g.drawLine(this.x, this.y + i * this.hoogte / this.rijen, this.x + this.breedte, this.y + i * this.hoogte / this.rijen);
    }
    for (int i = 0; i <= this.kolommen; i++) {
      g.drawLine(this.x + i * this.breedte / this.kolommen, this.y, this.x + i * this.breedte / this.kolommen, this.y + this.hoogte);
    }
  }
  
  public void tekenhokjes(Graphics g)
  {
    int aantalhokjes = this.rijen * this.kolommen;
    int startx = this.x;int starty = this.y;
    Font font1 = new Addfont().createFont();
    g.setFont(font1);
    this.fm = g.getFontMetrics();
    for (int i = 0; i < aantalhokjes; i++)
    {
      g.setColor(Color.YELLOW);
      g.drawRect(startx, starty, this.breedte / this.kolommen, this.hoogte / this.rijen);
      if (((Integer)this.waarden.get(i)).intValue() != 0)
      {
        String word = this.waarden.get(i)+"";
        
        int woordlengte = this.fm.stringWidth(word);
        int regelhoogte = this.fm.getHeight();
        int startpositiex = startx + (this.breedte / this.kolommen - woordlengte) / 2;
        int startpositiey = starty + (this.hoogte / this.rijen + regelhoogte) / 2;
        g.drawString(word, startpositiex, startpositiey);
      }
      startx += this.breedte / this.kolommen;
      if (i % this.kolommen == this.kolommen - 1)
      {
        startx = this.x;
        starty += this.hoogte / this.rijen;
      }
    }
  }
  

// **** NIEUWE SETUP ******
  public void swipe(int richting){
		
	  	ArrayList<ArrayList<Integer>> rijenLijst = maakRijen();
		if (richting < 2){
			rijenLijst = maakRijen();
		}
		else{
			rijenLijst = maakVertRijen();
		}
		ArrayList<ArrayList<Integer>> returnLijst = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> rij = new ArrayList<Integer>();
		for (int i = 0; i < rijenLijst.size();i++){
			rij = rijenLijst.get(i);
			if (richting == 0){
			rij = sorteerLinks(rij);
			}
			else if (richting == 1){
			rij = sorteerRechts(rij);	
			}
			else if (richting == 2){	
			rij = sorteerLinks(rij);
			}
			returnLijst.add(rij);
		}
		if (richting > 1){
			returnLijst = transponeer(returnLijst);
		}
		waarden = convertToList(returnLijst);
		voegNieuwGetalToe();
		repaint();
		
	}
  private ArrayList<ArrayList<Integer>> transponeer(ArrayList<ArrayList<Integer>>  lijst){
	  ArrayList<ArrayList<Integer>>  bufferLijst = new ArrayList<ArrayList<Integer>>();
	  ArrayList<Integer> rij = new ArrayList<Integer>();
		  // arrays aanmaken en 1e waarde wegschrijven
		  rij = lijst.get(0);
		  for (int j = 0; j<rij.size(); j++){
			  ArrayList<Integer> bufferRij = new ArrayList<Integer>();
			  bufferRij.add(rij.get(j));
			  bufferLijst.add(bufferRij);
		  }
		  // volgende waarden wegschrijven
		 for (int i = 1; i< lijst.size();i++){ 
			 rij = lijst.get(i);
			  for (int j = 0; j<rij.size(); j++){
				  ArrayList<Integer> bufferRij = bufferLijst.get(j);
				  bufferRij.add(rij.get(j));
				  bufferLijst.set(j, bufferRij);
			  }
	  }
	return bufferLijst;
	  
  }
  private ArrayList<Integer> convertToList(ArrayList<ArrayList<Integer>> list){
	  	ArrayList<Integer> rij = new ArrayList<Integer>();
	  	ArrayList<Integer> newList = new ArrayList<Integer>();
	  	for (int i = 0; i< list.size();i++){
	  		rij = list.get(i);
	  		for (int j=0; j < rij.size(); j++){
	  			newList.add(rij.get(j));
	  		}
	  	}  	
	  	return newList;
  }
  
private ArrayList<ArrayList<Integer>> maakRijen(){
		  ArrayList<ArrayList<Integer>> rijenLijst = new ArrayList<ArrayList<Integer>>();
		  int counter = 0;
		  for (int i = 0; i< rijen; i++){
			  ArrayList<Integer> rij = new ArrayList<Integer>();
			  for (int j = 0; j < kolommen; j++){
				  rij.add(waarden.get(counter));
				  counter++;
			  }
			  rijenLijst.add(rij);
		  }
		  return rijenLijst;
	}
private ArrayList<ArrayList<Integer>> maakVertRijen(){
	  ArrayList<ArrayList<Integer>> rijenLijst = new ArrayList<ArrayList<Integer>>();
		//maak aantal arrays gelijk aan kolommen en vul deze met 1 waarde
	  	int count = 0;
	  	for (int j = 0; j< kolommen; j++){
		  ArrayList<Integer> rij = new ArrayList<Integer>();
		  rij.add(waarden.get(count));
		  rijenLijst.add(rij);
		  count++;
		}  
	  
	  	//voor de resterende rijen, haal de bijbehorende array op en vul deze aan met 1 waarde
	  for (int i = 1; i < rijen; i++){
	  		for (int j = 0; j< kolommen; j++){
	  		ArrayList<Integer>rij = rijenLijst.get(j);
			  rij.add(waarden.get(count));
			  count++;
			  rijenLijst.set(j, rij);
	  		}
		  }
	  return rijenLijst;
}

private ArrayList<Integer> sorteerLinks( ArrayList<Integer> rij){
	ArrayList<Integer> newRij = new ArrayList<Integer>();
	// alle getallen neerzetten
	for (int i = 0; i < rij.size(); i ++){
		if (rij.get(i)!=0){
			newRij.add(rij.get(i));
		}
	}
	//alle nullen neerzetten
	for (int i = 0; i < rij.size(); i ++){
		if (rij.get(i)==0){
			newRij.add(0);
		}
	}
	// gelijke getallen bijelkaar optellen
	// lege plekken opvullen met nullen
	rij = telOp(newRij);
	//array resetten
	newRij = new ArrayList<Integer>();
	// alle getallen neerzetten
	// getallen nog een keer vooraan zetten
	for (int i = 0; i < rij.size(); i ++){
		if (rij.get(i)!=0){
			newRij.add(rij.get(i));
		}
	}
	//alle nullen neerzetten
	for (int i = 0; i < rij.size(); i ++){
		if (rij.get(i)==0){
			newRij.add(0);
		}
	}
	return newRij;
}

private ArrayList<Integer> sorteerRechts( ArrayList<Integer> rij){
	ArrayList<Integer> newRij = new ArrayList<Integer>();
	// alle nullen neerzetten
	for (int i = 0; i < rij.size(); i ++){
		if (rij.get(i)==0){
			newRij.add(0);
		}
	}
	// alle getallen neerzetten
	for (int i = 0; i < rij.size(); i ++){
		if (rij.get(i)!=0){
			newRij.add(rij.get(i));
		}
	}
	
	newRij = telOpRechts(newRij);
	
	return newRij;
}

private void voegNieuwGetalToe(){
	//check of waarden vol is
	boolean vol = true;
	for (int i = 0; i < waarden.size(); i++){
		if (waarden.get(i)== 0){
			vol = false;
		}
	}
	while (!vol){
		int newPos = (int)(Math.random()*waarden.size());
		if (waarden.get(newPos)==0){
			waarden.set(newPos, 2);
			vol = true;
		}
	}
}
private ArrayList<Integer> telOp(ArrayList<Integer> rij){
	ArrayList<Integer> newRij = new ArrayList<Integer>();
		int getal1 = rij.get(0);
		int getal2 = rij.get(1);
		//getal 1
		if (getal1 == getal2){
			newRij.add(getal1*2);
			getal1 = 0;	
		}
		else{
			newRij.add(getal1);			
			getal1 = getal2;
		}
		//getal 2 t/m voorlaatst		
		for (int i = 2; i < rij.size(); i++){
			getal2 = rij.get(i);
			//als het volgende getal gelijk is verdubbelen
			if (getal1 == getal2){
				newRij.add(getal1*2);
				getal1= 0;
			}
			//anders gewoon het eerste getal wegschrijven 
			else {
				newRij.add(getal1);
				getal1 = getal2;
			}
		}
		//laatste getal
		newRij.add(getal1);
	return newRij;
}
private ArrayList<Integer> telOpRechts(ArrayList<Integer> rij){
	ArrayList<Integer> newRij = new ArrayList<Integer>();
		int size = rij.size()-1;
		int getal1 = rij.get(size);
		int getal2 = rij.get(size-1);
		// --- getallen in omgekeerde volgorde opslaan 
		//getal 1
		if (getal1 == getal2){
			newRij.add(getal1*2);
			getal1 = 0;	
		}
		else{
			newRij.add(getal1);			
			getal1 = getal2;
		}
		//getal 2 t/m voorlaatst
		for (int i = size-2; i >= 0; i--){
			getal2 = rij.get(i);
			if (getal1 == getal2){
				newRij.add(getal1*2);
				getal1= 0;
			}
			else {
				newRij.add(getal1);
				getal1 = getal2;
			}
		}
		//laatste getal
		newRij.add(getal1);
		// we hebben nu de omgekeerde rij van wat we willen
		// rij inverteren
		rij = newRij;
		//array resetten
		newRij = new ArrayList<Integer>();
		for (int i = rij.size()-1; i>=0;i--){
			newRij.add(rij.get(i));
		}
	return newRij;
}
}