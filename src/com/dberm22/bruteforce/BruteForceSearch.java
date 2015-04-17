package com.dberm22.bruteforce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dberm22.common.EvalFunction;

public class BruteForceSearch
{

	public static <U, V> HashMap<U,V> bruteForce(ArrayList<U> u, ArrayList<V> v, EvalFunction evalFunc) 
	{
		HashMap<U,V> matches = new HashMap<U,V>();
		
	    if(u==null || u.size()==0 || v==null || v.size()==0)
	    {
	        return matches;
	    }
	    
	    List<Byte> bestperm;
	    if(v.size()>u.size())
	    {
	    	bestperm = execute(u,v,evalFunc, false);
	    	for(int matchID=0; matchID<u.size();matchID++)
	        {
	            V match_v = v.get(bestperm.get(matchID));
	            U match_u = u.get(matchID);
	            matches.put(match_u,match_v);
	        }
	    }
	    else
	    {
	    	bestperm = execute(v,u,evalFunc, true);
	    	for(int matchID=0; matchID<v.size();matchID++)
	        {
	            V match_v = v.get(matchID);
	            U match_u = u.get(bestperm.get(matchID));
	            matches.put(match_u,match_v);
	        }
	    }
	    
        return matches;
	}
	
	private static <U, V> List<Byte> execute(ArrayList<U> u, ArrayList<V> v, EvalFunction evalFunc, boolean swap)
	{
		double minCost = Double.POSITIVE_INFINITY;
	    List<Byte> bestperm = new ArrayList<Byte>();
	    ArrayList<List<Byte>> permutations = new ArrayList<List<Byte>>();
	    
        permutations.addAll(Permutations.nPr((byte)v.size(), (byte)u.size()));
        for(int perm = 0; perm<permutations.size(); perm++)
        {
            double totalCost = 0;
            for(int matchID = 0; matchID<permutations.get(perm).size(); matchID++)
            {
            	if(swap){ totalCost += evalFunc.execute(v.get(permutations.get(perm).get(matchID)), u.get(matchID));}
            	else{totalCost += evalFunc.execute(u.get(matchID), v.get(permutations.get(perm).get(matchID)));}
            }
            
            if(totalCost<minCost)
            {
                minCost=totalCost;
                bestperm = permutations.get(perm);
            }
        }
        
        return bestperm;
	}

}