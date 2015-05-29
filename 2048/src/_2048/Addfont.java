package _2048;

import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class Addfont
{
  private static Font ttfBase = null;
  private static Font VarySharky = null;
  private static InputStream myStream = null;
  private static final String FONT_PATH_VarySharky = "BADABB__.ttf";
  
  public Font createFont()
  {
    try
    {
      myStream = new BufferedInputStream(
        new FileInputStream("BADABB__.ttf"));
      ttfBase = Font.createFont(0, myStream);
      VarySharky = ttfBase.deriveFont(0, 24.0F);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      System.err.println("Font not loaded.");
    }
    return VarySharky;
  }
}
