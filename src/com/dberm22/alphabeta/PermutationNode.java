package com.dberm22.alphabeta;
/*
 * PermutationNode is a modification of Grzegorz's "Yet Another Tree Structure" TreeNode
 * https://code.google.com/p/yet-another-tree-structure/source/browse/trunk/java/src/com/tree/TreeNode.java
 * Grzegorz (gt4dev@gmail.com)
 * 
 * Modified by David Berman (dberm22@gmail.com) for specific purpose of using this node to construct 
 * a permutation tree. 2014
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PermutationNode{

	private int factor;
	public ArrayList<Integer> data;
	private PermutationNode parent;
	private List<PermutationNode> children;
	private List<PermutationNode> elementsIndex;
	private double score = 0.0;

	public boolean isRoot() {
		return parent == null;
	}

	public boolean isLeaf() {
		return children.size() == 0;
	}

	public PermutationNode(int factor, ArrayList<Integer> data) {
		this.factor = factor;
		this.data = data;
		this.children = new LinkedList<PermutationNode>();
		this.elementsIndex = new LinkedList<PermutationNode>();
		this.elementsIndex.add(this);
	}
	
	public PermutationNode(int factor) {
		this.factor = factor;
		this.data = new ArrayList<Integer>();
		this.children = new LinkedList<PermutationNode>();
		this.elementsIndex = new LinkedList<PermutationNode>();
		this.elementsIndex.add(this);
	}
	

	private PermutationNode addChild(ArrayList<Integer> data) {
		PermutationNode childNode = new PermutationNode(this.factor, data);
		childNode.parent = this;
		this.children.add(childNode);
		this.registerChildForSearch(childNode);
		return childNode;
	}
	
	public List<PermutationNode> getChildren(){
		return children;
	}
	
	public PermutationNode getParent(){
		return parent;
	}

	public int getLevel() {
		if (this.isRoot())
			return 0;
		else
			return parent.getLevel() + 1;
	}

	private void registerChildForSearch(PermutationNode node) {
		elementsIndex.add(node);
		if (parent != null)
			parent.registerChildForSearch(node);
	}
	
	public void setScore(double score)
	{
		this.score = score;
	}
	
	public double getScore()
	{
		return this.score;
	}
	
	public int getFactor()
	{
		return this.factor;
	}
	
	public void populateChildren()
	{
		for(int i=0;i<this.factor;i++)
		{
			@SuppressWarnings("unchecked")
			ArrayList<Integer> clone = (ArrayList<Integer>) this.data.clone();
			if(!clone.contains(i)) 
			{
				clone.add(i);
				this.addChild(clone);
			}
		}
	}

	@Override
	public String toString() {
		return data != null ? data.toString() : "[data null]";
	}

}