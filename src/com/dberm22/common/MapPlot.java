package com.dberm22.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JComponent;

public class MapPlot extends JComponent
{

  private static final long serialVersionUID = -8207257551841900166L;
  private  int windowWidth=400;
  private  int windowHeight=400;
  private  HashMap<Integer, Point2D> A;
  private  HashMap<Integer, Point2D> B;
  public  ArrayList<Integer> bestPerm = new ArrayList<Integer>();
  public boolean swap = false;

  private static Font bigFont = new Font("Monospaced", Font.BOLD, 36);
  private static Font smallFont = new Font("Monospaced", Font.PLAIN, 10);

  public MapPlot(HashMap<Integer, Point2D> A, HashMap<Integer, Point2D> B, int width, int height)
  {
	  this.A = A;
	  this.B = B;
	  this.windowWidth=width;
	  this.windowHeight=height;
  }
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // draw entire component white
    g.setColor(Color.white);
    g.fillRect(0, 0, getWidth(), getHeight());

    g.setColor(Color.RED);
    for (Entry<Integer, Point2D> entry : A.entrySet()) 
    {
    	int id = entry.getKey();
    	Point2D pos = entry.getValue();
    	
    	g.setFont(bigFont);
        FontMetrics fm = g.getFontMetrics();
    	int w = fm.stringWidth("x");
    	int h = fm.getAscent();
    	g.drawString("x", (int)(pos.getX() - (w / 2.0)), (int)(pos.getY() + (h / 4.0)));
    	g.setFont(smallFont);
    	g.drawString(String.valueOf(id), (int)(pos.getX()+5), (int)(pos.getY()-9));
    }
    
    g.setColor(Color.BLUE);
    for (Entry<Integer, Point2D> entry : B.entrySet()) 
    {
    	int id = entry.getKey();
    	Point2D pos = entry.getValue();
    	
    	g.setFont(bigFont);
        FontMetrics fm = g.getFontMetrics();
    	int w = fm.stringWidth("o");
    	int h = fm.getAscent();
    	g.drawString("o", (int)(pos.getX() - (w / 2.0)), (int)(pos.getY() + (h / 4.0)));
    	g.setFont(smallFont);
    	g.drawString(String.valueOf(id), (int)(pos.getX()+5), (int)(pos.getY()-9));
    }
    
	g.setColor(Color.BLACK);
	for(int i = 0;i<bestPerm.size();i++)
	{
		Point2D Apos;
		Point2D Bpos;

		Apos = A.get(i);
		Bpos = B.get(bestPerm.get(i));
		
		try{g.drawLine((int)(Apos.getX()), (int)(Apos.getY()), (int)(Bpos.getX()), (int)(Bpos.getY()));}
		catch(Exception e){}
	}
  }

  public Dimension getPreferredSize() {
    return new Dimension(windowWidth, windowHeight);
  }

  public Dimension getMinimumSize() {
    return getPreferredSize();
  }

}
