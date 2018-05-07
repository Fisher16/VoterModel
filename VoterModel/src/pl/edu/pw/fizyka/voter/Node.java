package pl.edu.pw.fizyka.voter;

import java.util.ArrayList;
import java.util.Random;

public class Node {
	
	//state {-1,+1}
	public int s;
	public ArrayList<Integer> connections;
	
	public Node(){
		s=(new Random()).nextInt(2)*2-1;
		connections=new ArrayList<Integer>();
	//	System.out.println(s);
	}
	
}
