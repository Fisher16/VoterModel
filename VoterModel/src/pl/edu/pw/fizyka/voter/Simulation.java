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
	
	public Simulation(){
		this.parameterTest();
	}

}
