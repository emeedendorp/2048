package _2048;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Addfont
{
  private static Font ttfBase = null;
  private static Font VarySharky = null;
  private static InputStream myStream = null;
  public Font createFont()
  {
    try
    {
      myStream = new BufferedInputStream(
        new FileInputStream("BADABB__.ttf"));
      ttfBase = Font.createFont(0, myStream);
      VarySharky = ttfBase.deriveFont(0, 24.0F);
    }
    catch (FileNotFoundException ex)
    {
      ex.printStackTrace();
      System.err.println("Font not loaded.");
    } catch (FontFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return VarySharky;
  }
}
