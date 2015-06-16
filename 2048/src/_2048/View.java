package _2048;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class View
  extends JPanel
{

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Settings settings;
  
  public View() {
	  settings = new Settings();
	  //maak een array aan met waarden 0
	  for (int i = 0; i <settings.rijen * settings.kolommen; i++) {
		  settings.waarden.add(0);
    }
	  //voeg een getal toe op een willekeurige plek
    int beginwaarde1 = (int)(Math.random() * settings.rijen * settings.rijen);
    settings.waarden.set(beginwaarde1, 2);
    	//voeg nog een getal toe op een willekeurige plek, anders dan de vorige plek
    int beginwaarde2 = (int)(Math.random() * settings.rijen * settings.kolommen);
    while (beginwaarde1 == beginwaarde2) {
      beginwaarde2 = (int)(Math.random() * settings.rijen * settings.kolommen);
    }
    settings.waarden.set(beginwaarde2, 2);
  }
  
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    
    teken(g);
    tekenhokjes(g);
  }
  
  public void teken(Graphics g)
  {
	  g.setColor(settings.background);
	  g.fillRect(settings.x, settings.y, settings.breedte, settings.hoogte);
	  g.setColor(settings.foreground);
	  
	  for (int i = 0; i <= settings.rijen; i++) {
      g.drawLine(settings.x, settings.y + i * settings.hoogte / settings.rijen, settings.x + settings.breedte, settings.y + i * settings.hoogte / settings.rijen);
    }
    for (int i = 0; i <= settings.kolommen; i++) {
      g.drawLine(settings.x + i * settings.breedte / settings.kolommen, settings.y, settings.x + i * settings.breedte / settings.kolommen, settings.y + settings.hoogte);
    }
  }
  
  public void tekenhokjes(Graphics g)
  {
	//voor de gradients (niet uitgetest of dit echt nodig is)  
	Graphics2D g2 = (Graphics2D) g;
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	RenderingHints.VALUE_ANTIALIAS_ON);
	//---

    int aantalhokjes = settings.rijen * settings.kolommen;
    int startx = settings.x;
    int starty = settings.y;
    Font font1 = new Addfont().createFont();
    g.setFont(font1);
    settings.fm = g.getFontMetrics();
    for (int i = 0; i < aantalhokjes; i++)
    {
     g2.setPaint(settings.redtowhite);
     //if (i%2==0){
    //	 g2.setPaint(settings.bluetogreen);
     //}
     g2.drawRect(startx, starty, settings.breedte / settings.kolommen, settings.hoogte / settings.rijen);
     g2.fillRect(startx, starty, (settings.breedte / settings.kolommen)-2, (settings.hoogte / settings.rijen)-2); 
      if (((Integer)settings.waarden.get(i)).intValue() != 0)
      {
        String word = settings.waarden.get(i)+"";
        
        int woordlengte = settings.fm.stringWidth(word);
        int regelhoogte = settings.fm.getHeight();
        int startpositiex = startx + (settings.breedte / settings.kolommen - woordlengte) / 2;
        int startpositiey = starty + (settings.hoogte / settings.rijen + regelhoogte) / 2;
        g2.setPaint(settings.fontcolor);
        g2.drawString(word, startpositiex, startpositiey);
      }
      startx += settings.breedte / settings.kolommen;
      if (i % settings.kolommen == settings.kolommen - 1)
      {
        startx = settings.x;
        starty += settings.hoogte / settings.rijen;
      }
    }
  }
  

// **** NIEUWE SETUP ******
  public void swipe(int richting){
		if (checkFull()){
			System.out.print(settings.waarden+" -> ");
			System.out.println("Geen zetten meer.");
		}
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
			else if (richting == 3){
			rij = sorteerRechts(rij);
			}
			returnLijst.add(rij);
		}
		if (richting > 1){
			returnLijst = transponeer(returnLijst);
		}
		settings.waarden = convertToList(returnLijst);
		voegNieuwGetalToe();
		repaint();
		
	}
  
  protected boolean checkFull(){
	  boolean full = true;
	  //check horizontaal of er nog naast elkaar liggende settings.waarden zijn
	  int counter = 0;
	  int getal1 = 0;
	  int getal2 = 0;
	  //check of er nog nullen zijn --> return false;
	  for (int i =0; i < settings.waarden.size(); i++){
		  if (settings.waarden.get(i)==0){
			  return false;
		  }
	  }
	  
	  
	  for (int i =0; i < settings.rijen; i++){
		  for (int j = 0 ; j < settings.kolommen-1; j++){
			  getal1= settings.waarden.get(counter);
			  counter++;
			  getal2= settings.waarden.get(counter);
			  if (getal1 == getal2){
				  full= false;
			  } 
			  
		  }
		counter++;
	  }
	  //check verticaal of er nog naast elkaar liggende waarden zijn
	  int pos1 = 0;
	  int pos2= settings.kolommen;
	  for (int i=0; i < settings.waarden.size()-settings.kolommen;i++){
		  getal1 = settings.waarden.get(pos1);
		  getal2 = settings.waarden.get(pos2);
		  if (getal1==getal2){
			  full=false;
		  }
		  pos1++;
		  pos2++;
	  }
	return full;
	
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
  
protected ArrayList<ArrayList<Integer>> maakRijen(){
		  ArrayList<ArrayList<Integer>> rijenLijst = new ArrayList<ArrayList<Integer>>();
		  int counter = 0;
		  for (int i = 0; i< settings.rijen; i++){
			  ArrayList<Integer> rij = new ArrayList<Integer>();
			  for (int j = 0; j < settings.kolommen; j++){
				  rij.add(settings.waarden.get(counter));
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
	  	for (int j = 0; j< settings.kolommen; j++){
		  ArrayList<Integer> rij = new ArrayList<Integer>();
		  rij.add(settings.waarden.get(count));
		  rijenLijst.add(rij);
		  count++;
		}  
	  
	  	//voor de resterende rijen, haal de bijbehorende array op en vul deze aan met 1 waarde
	  for (int i = 1; i < settings.rijen; i++){
	  		for (int j = 0; j< settings.kolommen; j++){
	  		ArrayList<Integer>rij = rijenLijst.get(j);
			  rij.add(settings.waarden.get(count));
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

protected ArrayList<Integer> sorteerRechts( ArrayList<Integer> rij){
	ArrayList<Integer> newRij = new ArrayList<Integer>();
	rij = telOpRechts(rij);
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
	return newRij;
}

private void voegNieuwGetalToe(){
	//check of waarden vol is
	boolean vol = true;
	for (int i = 0; i < settings.waarden.size(); i++){
		if (settings.waarden.get(i)== 0){
			vol = false;
		}
	}
	while (!vol){
		int newPos = (int)(Math.random()*settings.waarden.size());
		if (settings.waarden.get(newPos)==0){
			settings.waarden.set(newPos, 2);
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
		// we hebben nu de omgekeerde rij van wat we willen, maar de nullen zijn nog niet apart
		// nullen achteraan zetten (is nog steeds de omgekeerde rij)
		ArrayList<Integer> bufferRij = new ArrayList<Integer>();
		//nullen
		for (int j=0;j<newRij.size();j++){
			if (newRij.get(j)==0){
				bufferRij.add(0);
			}
		}
		//niet-nullen
		for (int j=newRij.size()-1;j>=0;j--){
			if (newRij.get(j)!=0){
				bufferRij.add(newRij.get(j));
			}
		}
		newRij= bufferRij;
	return newRij;
}
}