package pl.edu.pw.fizyka.voter;

import java.util.ArrayList;
import java.util.Random;

public class NodeList {

	ArrayList<Node> list;

	int mi, N;
	double p;
	int rhoP;
	int rhoM;
	double M;

	public NodeList(int Mi, int NN, double P) {
		mi = Mi;
		N = NN;
		p = P;
		list = new ArrayList<Node>(N);

		ArrayList<Integer> tmp = new ArrayList<Integer>();
		for (int i = 0; i < N; ++i)
			tmp.add(i);

		// System.out.println(tmp.get(N - 1));
		for (int i = 0; i < N; ++i)
			list.add(new Node());
		// while (tmp.size() > 0) {
		for (int x = 0; x < 4; ++x) {
			for (int j = 0; j < N; ++j) {
				if (list.get(j).connections.size() < mi) {
					int rIndex = j;
					while (rIndex == j || list.get(j).connections.contains(rIndex))
						rIndex = tmp.get((new Random()).nextInt(tmp.size()));
					// System.out.println(j+" "+rIndex+" "+list.get(j).connections.size()+" "+tmp );

					list.get(j).connections.add(rIndex);
					list.get(rIndex).connections.add(j);

					if (list.get(rIndex).connections.size() >= mi)
						tmp.remove(tmp.indexOf(rIndex));
					// possible optimization
					if (list.get(j).connections.size() >= mi)
						tmp.remove(tmp.indexOf(j));
				}
				// tmp.remove(tmp.indexOf(j));
			}
		}
		// }

	}

	public void nextTimeStep() {
//		System.out.println();
		Random rgen = new Random();
		int i=0;
		for (Node n : list) {
			int j = rgen.nextInt(mi);
			int jIndex = n.connections.get(j);
			if (n.s != list.get(jIndex).s) {
				if (rgen.nextDouble() < p) {
					int aIndex = rgen.nextInt(N);
					
					while (n.s != list.get(aIndex).s || n.connections.contains(aIndex)||aIndex==i){
						aIndex = rgen.nextInt(N);
						//System.out.println("test");
					}
//					System.out.println(i+" "+aIndex+" "+j);
					n.connections.set(j, aIndex);
					list.get(aIndex).connections.add(i);
				} else {
//					System.out.println("rev");
					n.s *= -1;
				}
				
			}
			++i;
		}
//		System.out.println();
	}

	public void calcM() {
		double sum = 0;
		for (int ii = 0; ii < N; ii++)
			sum += list.get(ii).s;
		M = sum / N;
	}

	public void printM() {
		System.out.println(M);
	}

	public double getM() {
		return M;
	}

	public void calcRho() {
		int tempRhoP = 0;
		int tempRhoM = 0;
		for (int ii = 0; ii < N; ii++) {
			for (int n : list.get(ii).connections) {
				if (list.get(ii).s == 1 && list.get(n).s == 1) {
					tempRhoP++;
				} else if (list.get(ii).s == -1 && list.get(n).s == -1) {
					tempRhoM++;
				} else {
				}
			}
		}
		rhoM = tempRhoM / 2;
		rhoP = tempRhoP / 2;
	}

	public int getRhoM() {
		return rhoM;
	}

	public int getRhoP() {
		return rhoP;
	}

	public void printRhoP() {
		System.out.println(rhoP);
	}

	public void printRhoM() {
		System.out.println(rhoM);
	}

	public int getNumberOfConnections() {
		return N * mi/2;
	}

	public void printNumberOfConnections() {
		System.out.println(N *mi/2);
	}

	public void printAllConnections() {
		for (int ii = 0; ii < N; ii++) {
			System.out.println(ii+" "+list.get(ii).s+" "+list.get(ii).connections);
		}
	}
	
	
	public void printParm(){
		this.calcRho();
		this.calcM();
		this.printRhoM();
		this.printRhoP();
		this.printM();
	}
}
