package pl.edu.pw.fizyka.voter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Simulation {

	public void NodeListGenerationTest() {
		NodeList test = new NodeList(3, 1000, 0.5);
		int i = 0;
		for (Node n : test.list) {
			System.out.println(i + " " + n.s + "  " + n.connections);
			++i;
		}

	}

	public void parameterTest() {
		NodeList test = new NodeList(3, 1000, 0.5);
		test.calcRho();
		test.calcM();
		test.printAllConnections();
		test.printRhoM();
		test.printRhoP();
		test.printM();
		test.printNumberOfConnections();
	}

	public void nextStepTest() {
		NodeList test = new NodeList(3, 10, 0.5);
		System.out.println("OK");
		test.printAllConnections();
		test.calcRho();
		test.printRhoM();
		test.nextTimeStep();
		System.out.println("*****");
		test.calcRho();
		test.printRhoM();
		test.printAllConnections();
		test.nextTimeStep();
		System.out.println("*****");
		test.calcRho();
		test.printRhoM();
		test.printAllConnections();
		test.nextTimeStep();
		System.out.println("*****");
		test.calcRho();
		test.printRhoM();
		test.printAllConnections();
		test.nextTimeStep();
		System.out.println("*****");
		test.calcRho();
		test.printRhoM();
		test.printAllConnections();


	}

	public void evolutionTest1() {
		NodeList test = new NodeList(4, 1000, 0);
		System.out.println("OK");
		test.printParm();
		for (int i = 0; i < 200000 && test.M * test.M != 1; ++i) {
			test.nextTimeStep();

			if (i % 1000 == 0) {
				System.out.println("*****************");
				test.printParm();
			}
		}
		int conn = 0;
		for (Node n : test.list) {
			conn += n.connections.size();
		}
		System.out.println("Total num of conn: " + conn / 2);
	}

	public void dataGen() {

		PrintWriter writer;
		PrintWriter writer2;
		int N = 500;
		int mi = 4;
		DecimalFormat df = new DecimalFormat("0.000");
		DecimalFormat df2 = new DecimalFormat("0.00");
		try {
			writer = new PrintWriter("13_data_N" + N + "_Mi" + mi + ".txt", "UTF-8");

			writer.println("p rhoP rhoM M rho");
			for (double i = 0.2; i <= 0.61; i += 0.1) {
				NodeList test = new NodeList(mi, N, i);
				System.out.println("ok");

				writer2 = new PrintWriter("rho/dataAVG_N" + N + "_p" + df2.format(i));

				double rho = test.calcRho();

				ArrayList<Double> rhoList = new ArrayList<Double>();
				int j = 0;
				int avg = 0;
				for (; avg < 10; ++avg) {
					test = new NodeList(mi, N, i);

					j = 0;
					rho = test.calcRho();

					System.out.println("ok " + avg);
					while (rho > 0) {
						// writer2.println(j+" "+rho);
						// System.out.println(test.calcRho()+" "+test.M);
						if (rhoList.size() == j)
							rhoList.add(rho);
						else
							rhoList.set(j, rhoList.get(j) + rho);
						test.nextTimeStep();
						rho = test.calcRho();
						j++;
					}
					System.out.println("j " + j);
				}
				test.calcRho();
				test.calcM();

				for (int x = 0; x < rhoList.size(); x++)
					writer2.println(x + " " + rhoList.get(x) / avg);
				writer2.flush();
				writer2.close();

				String str = df.format(i) + " " + test.getRhoP() + " " + test.getRhoM() + " " + test.getM() + "   "
						+ test.rho;
				writer.println(str.replaceAll("\\.", ","));
				writer.flush();
				System.out.println("Iteracja p=" + i);
			}
			writer.close();

		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public Simulation() {
		// this.parameterTest();
		//this.nextStepTest();
		// this.evolutionTest1();
		 this.dataGen();
	}

}
