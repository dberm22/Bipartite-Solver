package com.dberm22.bruteforce;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.math.util.MathUtils;

public class Permutations<T> {
    
    public static Set<List<Byte>> getAllSubsetPermutations(byte n) {
        Permutations<Byte> obj = new Permutations<Byte>();
        Collection<Byte> input = new ArrayList<Byte>();
        
        for(byte i = 0; i<n; i++)
        {
            input.add(i);
        }

        Collection<List<Byte>> output = obj.permute(input, n, n);
        //int k = 0;
        Set<List<Byte>> pnr = null;
        for (byte i = 0; i <= input.size(); i++) {
            pnr = new HashSet<List<Byte>>();
            for(List<Byte> perm : output){
                pnr.add(perm.subList(i, perm.size()));
            }
            //k = input.size()- i;
            //System.out.println("P("+input.size()+","+k+") :"+"Count ("+pnr.size()+") :- "+pnr);
        }
        
        return pnr;
    }
    
    public static Set<List<Byte>> nPr(byte n, byte r) {
        Permutations<Byte> obj = new Permutations<Byte>();
        Collection<Byte> input = new ArrayList<Byte>();
        
        for(byte i = 0; i<n; i++)
        {
            input.add(i);
        }
        
        Collection<List<Byte>> output = obj.permute(input, n, r);
        Set<List<Byte>> pnr = new HashSet<List<Byte>>();
        for(List<Byte> perm : output)
        {
            pnr.add(perm.subList(0, r));
        }
        //System.out.println("P("+input.size()+","+r+") :"+"Count ("+pnr.size()+") :- "+pnr);
        
        return pnr;
    }
    
    public Collection<List<T>> permute(Collection<T> input, byte n, byte r) {
        Collection<List<T>> output = new ArrayList<List<T>>();
        if (input.isEmpty()) {
            output.add(new ArrayList<T>());
            return output;
        }
        List<T> list = new ArrayList<T>(input);
        T head = list.get(0);
        List<T> rest = list.subList(1, list.size());    
        for (List<T> permutations : permute(rest, n , r)) {
            List<List<T>> subLists = new ArrayList<List<T>>();
            for (int i = 0; i <= permutations.size(); i++) {
                if(permutations.size() > r && i>r)
                {}
                else
                {
                    List<T> subList = new ArrayList<T>();
                    subList.addAll(permutations);
                    subList.add(i, head);
                    subLists.add(subList);
                }
            }
            output.addAll(subLists);
        }
        return output;
    }
    
    public static void main(String[] args)
    {
        byte n = 5;
        byte r = 3;
        long tic = System.currentTimeMillis();
        Set<List<Byte>>  output = nPr(n, r);
        long executionTime= System.currentTimeMillis() - tic;
        System.out.println("No. of Permutations for "+ n +"P"+ r + ": "+ output.size() +"    ->    n!/(n-r)! = "+ MathUtils.factorial(n)/MathUtils.factorial(n-r));
        System.out.println("Run time: "+ executionTime +" ms");
    }
}
