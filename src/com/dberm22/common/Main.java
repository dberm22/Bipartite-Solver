package com.dberm22.common;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import com.dberm22.alphabeta.Minimin;
import com.dberm22.bruteforce.BruteForceSearch;
import com.dberm22.common.EvalFunction;
import com.dberm22.greedy.GreedySearch;
import com.dberm22.lapjv4j.lapjv;

public class Main implements EvalFunction
{
	private enum Algorithm
	{
		GREEDY_SEARCH,
		BRUTE_FORCE_SEARCH,
		ALPHA_BETA_PRUNING,
		LAPJV;
	}

    private static HashMap<Integer, Point2D> A = new HashMap<Integer, Point2D>();
    private static HashMap<Integer, Point2D> B = new HashMap<Integer, Point2D>();
    
    public static void main(String args[])
    {
    	
      int maxElements = 300;
      int maxIterations = 6;
      int distScale = 800;
      
      PrintWriter writer = null;
      try 
      {
  		writer = new PrintWriter("SpeedTestResults.csv", "UTF-8");
  	  } catch (Exception e){}
      writer.println("Element Count, Iteration Number, Greedy, Brute Force, AlphaBeta, LAPJV");
      //writer.println("Element Count, Iteration Number, Greedy, LAPJV");
      
      for(int elementCount=1; elementCount<=maxElements; ++elementCount)
      {
    	  for(int iterNum=1; iterNum<=maxIterations; ++iterNum)
    	  {
    		    writer.print(elementCount+", "+iterNum);
    		  
			    ArrayList<Integer> u_elements = new ArrayList<Integer>();
			    ArrayList<Integer> v_elements = new ArrayList<Integer>();
			        
			    for(int i=0;i<elementCount;i++)
			    {
			      	Point2D u_pos = new Point((int)(distScale*Math.random()),(int)(distScale*Math.random()));
			      	A.put(i,u_pos);
			    	u_elements.add(i);
			    	
			    	Point2D v_pos = new Point((int)(distScale*Math.random()),(int)(distScale*Math.random()));
			      	B.put(i,v_pos);
			    	v_elements.add(i);
			    }
			      
			      
			    Main eval = new Main();   
			    for(Algorithm algo : Algorithm.values())
			    {
			    	HashMap<Integer, Integer> permMap = null;
			    	long tic = System.currentTimeMillis();
			    	
			    	switch(algo)
			    	{
					    case GREEDY_SEARCH:
					    	System.out.print(iterNum + " - Greedy: ");
					    	if(elementCount<=2000)
					    	{
					    		permMap = GreedySearch.greedySearch(u_elements, v_elements, eval);
					    	}
					    	break;
					    case BRUTE_FORCE_SEARCH:
					    	if(elementCount<=10)
					    	{
					    		System.out.print(iterNum + " - Brute Force: ");
					    		permMap = BruteForceSearch.bruteForce(u_elements, v_elements, eval);
					    	}
					    	break;
					    case ALPHA_BETA_PRUNING:
					    	if(elementCount<=11)
					    	{
					    		System.out.print(iterNum + " - Alpha Beta: ");
					    		Minimin tester = new Minimin();
					    		permMap = tester.minimin(u_elements, v_elements, eval);
					    	}
							break;
					    case LAPJV:
					    	if(elementCount<=500)
					    	{
					    		System.out.print(iterNum + " - LAPJV: ");
					    		permMap = lapjv.lap(u_elements, v_elements, eval);
					    	}
					    	break;
			    	}
			    	writer.print(", "+(System.currentTimeMillis()-tic));
			    	if(permMap != null)
			    	{
			    		System.out.println(permMap.toString());
			    	}
			    }
			    writer.println(""); 
    	  }
      }
      writer.close();
	
  }

    @Override
	public <U,V> double execute(U u_element, V v_element)
	{
		Point2D u_elementPos = A.get((Integer)u_element);
		Point2D v_elementPos = B.get((Integer)v_element);
		return u_elementPos.distance(v_elementPos);
	}

}
