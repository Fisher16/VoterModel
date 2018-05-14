package pl.edu.pw.fizyka.voter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

public class Simulation {
	
	public void NodeListGenerationTest(){
		NodeList test=new NodeList(3,1000,0.5);
		int i=0;
		for(Node n:test.list){System.out.println(i+" "+n.s+"  "+n.connections);++i;}

	}
	public void parameterTest(){
		NodeList test=new NodeList(3,1000,0.5);
		test.calcRho();
		test.calcM();
		test.printAllConnections();
		test.printRhoM();
		test.printRhoP();
		test.printM();
		test.printNumberOfConnections();
	}
	
	public void nextStepTest(){
		NodeList test=new NodeList(3,10,0.5);
		System.out.println("OK");
		test.printAllConnections();
		test.nextTimeStep();
		System.out.println("*****");
		test.printAllConnections();
		test.nextTimeStep();
		System.out.println("*****");
		test.printAllConnections();
		
	}
	
	
	public void evolutionTest1(){
		NodeList test=new NodeList(4,10000,0);
		System.out.println("OK");
		test.printParm();
		for(int i=0;i<200000&&test.M*test.M!=1;++i){
			test.nextTimeStep();
			
			if(i%1000==0){System.out.println("*****************");test.printParm();}
		}
		int conn=0;
		for(Node n:test.list){
			conn+=n.connections.size();
		}
		System.out.println("Total num of conn: "+conn/2);
	}
	
	public void dataGen(){
		
		PrintWriter writer;
		DecimalFormat df = new DecimalFormat("0.000");  
		try {
			writer = new PrintWriter("11_dataBig_N1k_Mi4_ttofrozen.txt", "UTF-8");
			writer.println("p rhoP rhoM M");
			for(double i=0.10;i<=0.71;i+=0.05){
				NodeList test=new NodeList(4,1000,i);
				System.out.println("ok");
				
				while(test.calcRho()>0&&test.M*test.M!=1){
	//				System.out.println(test.calcRho()+" "+test.M);
					test.nextTimeStep();
					test.calcM();
				}

				test.calcRho();
				test.calcM();
				String str=df.format(i)+" "+test.getRhoP()+" "+test.getRhoM()+" "+test.getM()+"   "+test.rho;
				writer.println(str.replaceAll("\\.", ","));
				writer.flush();
				System.out.println(i);
			}
			writer.close();
			
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		}
		
		
	
	
	public Simulation(){
		//this.parameterTest();
		//this.nextStepTest();
		//this.evolutionTest1();
		this.dataGen();
	}

}
