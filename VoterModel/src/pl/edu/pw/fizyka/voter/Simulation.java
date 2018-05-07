package pl.edu.pw.fizyka.voter;

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
		test.printAllConnections();
		test.nextTimeStep();
		test.printAllConnections();
		
	}
	
	
	public void evolutionTest1(){
		NodeList test=new NodeList(4,10000,0.5);
		System.out.println("OK");
		test.printParm();
		for(int i=0;i<2000;++i){
			test.nextTimeStep();
			
			if(i%100==0){System.out.println("*****************");test.printParm();}
		}
	}
	
	public Simulation(){
		//this.parameterTest();
		//this.nextStepTest();
		this.evolutionTest1();
	}

}
