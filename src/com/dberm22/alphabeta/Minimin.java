package com.dberm22.alphabeta;

import java.util.ArrayList;
import java.util.HashMap;

import com.dberm22.common.EvalFunction;

public class Minimin
{
	private double bestscore = Double.MAX_VALUE;
	private ArrayList<Integer> bestPerm;
	private int maxdepth;
	
	public Minimin(){}
	
	public Minimin(int maxdepth)
	{
		this.maxdepth = maxdepth;
	}
	
	private double getMinScore(PermutationNode node, EvalFunction evalfunc)   
	{
		double nodeScore = 0;
		if(!node.isRoot())
		{
			double parentScore = node.getParent().getScore();
			nodeScore = parentScore + evalfunc.execute(node.data.size()-1,node.data.get(node.data.size()-1));
		}
		node.setScore(nodeScore);
		
		//System.out.println(node.data + "     " + node.getScore() + "                 " + bestscore + "     " + bestPerm);
		
	    if(node.getLevel() == this.maxdepth)
	        return nodeScore;
	    
	    if (node.getScore() < bestscore)
	    {
	    	node.populateChildren();
	        for(Object child : node.getChildren())
	        {
	        	double childScore = getMinScore((PermutationNode)child, evalfunc);
	        	if(childScore < bestscore)
	        	{
	        		bestscore = childScore;
	        		bestPerm = ((PermutationNode)child).data;
	        	}            
	        }
	    }
	    node=null;
	    
	    return bestscore;

	}
	
	public <U, V> HashMap<U,V> minimin(ArrayList<U> u, ArrayList<V> v, EvalFunction evalFunc) 
	{
		
		HashMap<U,V> matches = new HashMap<U,V>();
		
	    if(v.size()>=u.size())
	    {
	    	maxdepth = u.size();
			PermutationNode root = new PermutationNode(v.size());
			
			getMinScore(root,evalFunc);
	    	
			for(int matchID=0; matchID<u.size();matchID++)
	        {
	    		matches.put(u.get(matchID),v.get(this.bestPerm.get(matchID)));
	        }
	    }
	    else
	    {
	    	maxdepth = v.size();
			PermutationNode root = new PermutationNode(u.size());
			
			getMinScore(root,evalFunc);
			
	    	for(int matchID=0; matchID<v.size();matchID++)
	        {
	    		matches.put(u.get(this.bestPerm.get(matchID)),v.get(matchID));
	        }
	    }
		
		return matches;
	}

}
