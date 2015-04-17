package com.dberm22.greedy;

import java.util.ArrayList;
import java.util.HashMap;

import com.dberm22.common.EvalFunction;
import com.dberm22.common.MatrixUtils;
import com.dberm22.common.ValueLocationStruct;

public class GreedySearch
{
	public static <U,V> HashMap<U,V> greedySearch(ArrayList<U> u, ArrayList<V> v, EvalFunction evalFunc)  
	{
	    HashMap<U,V> matches = new HashMap<U,V>();
	    
	    if(u==null || u.size()==0 || v==null || v.size()==0)
	    {
	        return matches;
	    } 
	
	    //Form Matrix    
	    double[][] costMatrix = new double[v.size()][u.size()];
	    for (int i = 0; i<v.size();i++) 
	    {
	        for (int j = 0; j<u.size();j++) 
	        {
	            costMatrix[i][j] = evalFunc.execute(u.get(j), v.get(i));
	        } 
	    }
	
	    //Perform Assignments in Greedy Fashion
	    //need +Math.ulp in case of rounding errors
	    while(MatrixUtils.min(costMatrix).value + Math.ulp(Double.MAX_VALUE) < Double.POSITIVE_INFINITY)
	    {  
	        ValueLocationStruct minValueAndPosition = MatrixUtils.min(costMatrix);

	        V match_v = v.get(minValueAndPosition.row);
	        U match_u = u.get(minValueAndPosition.col);
	        matches.put(match_u,match_v);
	        
	        //Remove paired items from matrix so they don't get reassigned
	        for(int j = 0; j<u.size();j++) 
	        {
	            costMatrix[minValueAndPosition.row][j] = Double.POSITIVE_INFINITY;
	        }
	        for(int i = 0; i<v.size();i++) 
	        {
	            costMatrix[i][minValueAndPosition.col] = Double.POSITIVE_INFINITY;
	        } 
	        
	    }
	    
	    return matches;
	
	}


}