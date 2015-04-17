package com.dberm22.bruteforce;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;

import com.dberm22.common.EvalFunction;
import com.dberm22.common.MapPlot;

public class RunBruteForce implements EvalFunction
{

  private static HashMap<Integer, Point2D> A = new HashMap<Integer, Point2D>();
  private static HashMap<Integer, Point2D> B = new HashMap<Integer, Point2D>();

  
  public static void main(String args[])
  {
	int numXs = 6;
    int numOs = 6;
    
    if(args.length==2)
    {
  	  numXs=Integer.valueOf(args[0]);
  	  numOs=Integer.valueOf(args[1]);
    }
    else if(args.length==1)
    {
  	  numXs=Integer.valueOf(args[0]);
  	  numOs=numXs;
    }
    
    int windowWidth=400;
    int windowHeight=400;
      
    ArrayList<Integer> u_elements = new ArrayList<Integer>();
    ArrayList<Integer> v_elements = new ArrayList<Integer>();
    
    for(int i=0;i<numXs;i++)
    {
    	Point2D pos = new Point((int)(windowWidth*Math.random()),(int)(windowHeight*Math.random()));
    	A.put(i,pos);
    	u_elements.add(i);
    }

    for(int i=0;i<numOs;i++)
    {
    	Point2D pos = new Point((int)(windowWidth*Math.random()),(int)(windowHeight*Math.random()));
    	B.put(i,pos);
    	v_elements.add(i);
    }
    
    JFrame mainFrame = new JFrame("BruteForce Tester");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    MapPlot map = new MapPlot(A, B, windowWidth, windowHeight);
    mainFrame.getContentPane().add(map);
    mainFrame.pack();
    mainFrame.setVisible(true);
    
    long tic = System.currentTimeMillis();
    
    RunBruteForce eval = new RunBruteForce();
	HashMap<Integer, Integer> permMap = BruteForceSearch.bruteForce(u_elements, v_elements, eval);
	System.out.println(System.currentTimeMillis()-tic+"ms");
	
    ArrayList<Integer> bestPerm = new ArrayList<Integer>();
    for(int i=0;i<Math.max(numXs,numOs);i++){bestPerm.add(permMap.get(i));}
	
	map.bestPerm = bestPerm;
	mainFrame.repaint();
  }

@Override
public <U, V> double execute(U u_element, V v_element) {
	
	Point2D u_elementPos = A.get((Integer)u_element);
	Point2D v_elementPos = B.get((Integer)v_element);
	return u_elementPos.distance(v_elementPos);
}
}
