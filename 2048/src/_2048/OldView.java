package _2048;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class OldView
  extends JPanel
{
  int x = 20;
  int y = 20;
  int breedte = 400;
  int hoogte = 400;
  int rijen = 4;
  int kolommen = 4;
  Color background = Color.GRAY;
  Color foreground = Color.BLACK;
  ArrayList<Integer> waarden = new ArrayList<Integer>(this.rijen * this.kolommen);
  private static final long serialVersionUID = 1L;
  int newx;
  int newy;
  FontMetrics fm;
  
  public OldView() {
    
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
  
  public void links()
  {
	  //nieuwe Arraylist met de grootte van het veld
	  ArrayList<Integer> nieuw = new ArrayList<Integer>(this.rijen * this.kolommen);
	  //voor elke rij:
	  for (int j = 0; j < this.rijen; j++) {
      ArrayList<Integer> rij1 = new ArrayList<Integer>(this.kolommen);
      //voor elk getal in de rij:
      for (int i = 0; i < this.kolommen; i++) {
    	  //voeg de waarde uit de array waarden(huidige speelveld) toe aan de tijdelijke array rij1
    	  rij1.add((Integer)this.waarden.get(j * this.kolommen + i));
      }
      //voer checkmatches uit op de tijdelijke doelarray, sla deze op in een nieuwe doelarray
      ArrayList<Integer> rij2 = checkmatches(swipeLinks(rij1));
      // voeg vervolgens de tijdelijke array op in de doelarray
      for (int i = 0; i < this.kolommen; i++) {
        nieuw.add((Integer)rij2.get(i));
      }
    }
	//zet de waarden van de doelarray naar de waarden-array
    for (int i = 0; i < this.rijen * this.kolommen; i++) {
      this.waarden.set(i, (Integer)nieuw.get(i));
    }
    repaint();
    System.out.println(this.waarden);
    this.waarden = voegwaardetoe(this.waarden);
    System.out.println(this.waarden);
  }

  
  public ArrayList<Integer> swipeLinks(ArrayList<Integer> rij1)
  {
    ArrayList<Integer> rij2 = new ArrayList<Integer>(this.rijen * this.kolommen);
    
    int rij2count = 0;
    for (int i = 0; i < this.kolommen; i++) {
      if (((Integer)rij1.get(i)).intValue() != 0)
      {
        rij2.add((Integer)rij1.get(i));
        rij2count++;
      }
    }
    while (rij2count < this.kolommen)
    {
      rij2.add(Integer.valueOf(0));
      rij2count++;
    }
    return rij2;
  }
  
  public ArrayList<Integer> checkmatches(ArrayList<Integer> rij1)
  {
    boolean checked = false;
    ArrayList<Integer> rij2 = new ArrayList<Integer>(this.kolommen * this.rijen);
    if (rij1.get(0) == rij1.get(1))
    {
      rij2.add(Integer.valueOf(((Integer)rij1.get(0)).intValue() * 2));
      rij2.add(Integer.valueOf(0));
      checked = true;
    }
    else
    {
      rij2.add((Integer)rij1.get(0));
    }
    for (int i = 1; i < this.kolommen - 1; i++) {
      if ((rij1.get(i) == rij1.get(i + 1)) && (!checked))
      {
        rij2.add(Integer.valueOf(((Integer)rij1.get(i)).intValue() * 2));
        rij2.add(Integer.valueOf(0));
        checked = true;
      }
      else if (checked)
      {
        checked = false;
      }
      else
      {
        rij2.add((Integer)rij1.get(1));
      }
    }
    if (checked) {
      checked = false;
    } else {
      rij2.add((Integer)rij1.get(this.kolommen - 1));
    }
    return rij2;
  }
  
  public ArrayList<Integer> voegwaardetoe(ArrayList<Integer> rij1)
  {
    int telnullen = 0;
    for (int i = 0; i < this.rijen * this.kolommen; i++) {
      if (((Integer)rij1.get(i)).intValue() != 0) {
        telnullen++;
      }
    }
    int spawnpositie = (int)(Math.random() * telnullen);
    int teller = 0;
    for (int i = 0; i < this.rijen * this.kolommen; i++) {
      if (((Integer)rij1.get(i)).intValue() == 0) {
        if (teller == spawnpositie)
        {
          rij1.set(i, Integer.valueOf(2));
          System.out.println("2 toegoevoegd aan " + i);
          teller++;
        }
        else
        {
          teller++;
        }
      }
    }
    return rij1;
  }

// **** NIEUWE SETUP ******
  public void gaNaarLinks(){
		ArrayList<ArrayList<Integer>> rijenLijst = maakRijen();
		ArrayList<ArrayList<Integer>> returnLijst = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> rij = new ArrayList<Integer>();
		for (int i = 0; i < rijenLijst.size();i++){
			rij = sorteerLinks(rijenLijst.get(i));
			rij = telOp(rij);
			returnLijst.add(rij);
		}
		System.out.println("returnLijst: "+returnLijst);
		for (int i = 0; i< returnLijst.size(); i++){
			int counter= 0;
			rij = returnLijst.get(i);
			for (int j=0; j<rij.size();j++){			
				waarden.set(j+counter, rij.get(j));
				counter++;
			}
		}
		waarden = convertToList(returnLijst);
		System.out.println(waarden);
		voegNieuwGetalToe();
		repaint();
		
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

private ArrayList<Integer> sorteerLinks( ArrayList<Integer> rij){
	ArrayList<Integer> newRij = new ArrayList<Integer>();
	for (int i = 0; i < rij.size(); i ++){
		if (rij.get(i)!=0){
			newRij.add(rij.get(i));
		}
	}
	for (int i = 0; i < rij.size(); i ++){
		if (rij.get(i)==0){
			newRij.add(0);
		}
	}
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
		System.out.println("newPos is nu: "+newPos+" ----->");
		if (waarden.get(newPos)==0){
			waarden.set(newPos, 2);
			System.out.println("2 neergezet op positie "+newPos);
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
	return newRij;
}

}