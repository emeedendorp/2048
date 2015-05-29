package _2048;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.JPanel;

public class View
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
  ArrayList<Integer> waarden = new ArrayList(this.rijen * this.kolommen);
  private static final long serialVersionUID = 1L;
  int newx;
  int newy;
  FontMetrics fm;
  
  public View()
  {
    for (int i = 0; i < this.rijen * this.kolommen; i++) {
      this.waarden.add(Integer.valueOf(0));
    }
    int beginwaarde1 = (int)(Math.random() * this.rijen * this.kolommen);
    this.waarden.set(beginwaarde1, Integer.valueOf(2));
    
    int beginwaarde2 = (int)(Math.random() * this.rijen * this.kolommen);
    while (beginwaarde1 == beginwaarde2) {
      beginwaarde2 = (int)(Math.random() * this.rijen * this.kolommen);
    }
    this.waarden.set(beginwaarde2, Integer.valueOf(2));
  }
  
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    
    teken(g);
    tekenhokjes(g);
  }
  
  public void teken(Graphics g)
  {
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
    ArrayList<Integer> nieuw = new ArrayList(this.rijen * this.kolommen);
    for (int j = 0; j < this.rijen; j++)
    {
      ArrayList<Integer> rij1 = new ArrayList(this.kolommen);
      for (int i = 0; i < this.kolommen; i++) {
        rij1.add((Integer)this.waarden.get(j * this.kolommen + i));
      }
      ArrayList<Integer> rij2 = checkmatches(swipeLinks(rij1));
      for (int i = 0; i < this.kolommen; i++) {
        nieuw.add((Integer)rij2.get(i));
      }
    }
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
    ArrayList<Integer> rij2 = new ArrayList(this.rijen * this.kolommen);
    
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
    ArrayList<Integer> rij2 = new ArrayList(this.kolommen * this.rijen);
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
    ArrayList<Integer> rij2 = new ArrayList(this.kolommen * this.rijen);
    
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
}
