package pl.edu.pw.fizyka.voter;

import java.util.ArrayList;
import java.util.Random;

public class NodeList {

	ArrayList<Node> list;

	int mi, N;
	double p;
	double rhoP;
	double rhoM;
	double rho;
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
		for (int x = 0; x < mi; ++x) {
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
		// System.out.println("Gen OK");
	}

	public void nextTimeStep() {

		Random rgen = new Random();
		int iIndex = rgen.nextInt(N);
		Node n = list.get(iIndex);
		// System.out.println("mój Index- " + iIndex);
		int conn = n.connections.size();
		// System.out.println("conn- " + conn);
		if (conn > 0) {
			int j = rgen.nextInt(conn > 0 ? conn : mi);
			int jIndex = n.connections.get(j);
			// System.out.println("j- " + j + " jIndex- " + jIndex);
			if (n.s != list.get(jIndex).s) {

				double por = rgen.nextDouble();
				// System.out.println(por + ", p - " + p);
				if (por < p) {
					int aIndex = rgen.nextInt(N);

					//// error find better way//probably fixed
					int switcher = 0;
					ArrayList<Integer> aList = new ArrayList<Integer>();
					// limit 100
					while (switcher < 100
							&& (n.s != list.get(aIndex).s || n.connections.contains(aIndex) || aIndex == iIndex)) {
						switcher++;
						aIndex = rgen.nextInt(N);
						// System.out.println("jestem");
					}
					if (!((n.s != list.get(aIndex).s || n.connections.contains(aIndex) || aIndex == iIndex)))
						aList.add(aIndex);
					if (switcher == 100) {
						for (Node nd : list)
							if (n.s == nd.s && list.indexOf(n) != list.indexOf(nd) && !n.connections.contains(aIndex))
								aList.add(list.indexOf(nd));
						if (aList.size() > 0)
							aIndex = aList.get(rgen.nextInt(aList.size()));
						// System.out.println("Switcheru"+aList);
					}
					// System.out.println(list.indexOf(n)+" "+aIndex+" "+jIndex);
					if (aList.size() > 0) {
						n.connections.set(j, aIndex);
						list.get(aIndex).connections.add(iIndex);
						list.get(jIndex).connections.remove(list.get(jIndex).connections.indexOf(iIndex));
						// System.out.println("foch");
					}
				} else {
					n.s *= -1;
					// System.out.println("zdrada");
				}

			}
			// else {System.out.println("nic");}
		}
	}

	public double calcM() {
		double sum = 0;
		for (int ii = 0; ii < N; ii++)
			sum += list.get(ii).s;
		M = sum / N;
		return M;
	}

	public void printM() {
		System.out.println(M);
	}

	public double getM() {
		return M;
	}

	public double calcRho() {
		double tempRhoP = 0;
		double tempRhoM = 0;
		double tmpRho = 0;
		for (int ii = 0; ii < N; ii++) {
			for (int n : list.get(ii).connections) {
				if (list.get(ii).s == 1 && list.get(n).s == 1) {
					tempRhoP++;
				} else if (list.get(ii).s == -1 && list.get(n).s == -1) {
					tempRhoM++;
				} else {
					tmpRho++;
				}
			}
		}
		rhoM = tempRhoM / mi /N ;
		rhoP = tempRhoP / mi /N;
		rho = tmpRho / mi /N;
		return rho;
	}

	public double getRhoM() {
		return rhoM;
	}

	public double getRhoP() {
		return rhoP;
	}

	public void printRhoP() {
		System.out.println(rhoP);
	}

	public void printRhoM() {
		System.out.println(rhoM);
	}

	public int getNumberOfConnections() {
		return N * mi / 2;
	}

	public void printNumberOfConnections() {
		System.out.println(N * mi / 2);
	}

	public void printAllConnections() {
		for (int ii = 0; ii < N; ii++) {
			System.out.println(ii + " " + list.get(ii).s + " " + list.get(ii).connections);
		}
	}

	public void printParm() {
		this.calcRho();
		this.calcM();

		this.printRhoM();
		this.printRhoP();
		this.printM();
	}

}
