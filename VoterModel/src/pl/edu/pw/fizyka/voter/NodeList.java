package pl.edu.pw.fizyka.voter;

import java.util.ArrayList;
import java.util.Random;

public class NodeList {

	ArrayList<Node> list;

	int mi, N;
	double p;

	public NodeList(int Mi, int NN, double P) {
		mi = Mi;
		N = NN;
		p = P;
		list = new ArrayList<Node>(N);

		ArrayList<Integer> tmp = new ArrayList<Integer>();
		for (int i = 0; i < N; ++i)
			tmp.add(i);

		//System.out.println(tmp.get(N - 1));
		for (int i = 0; i < N; ++i)
			list.add(new Node());
		//while (tmp.size() > 0) {
		for(int x=0;x<4;++x){
			for (int j = 0; j < N; ++j) {
				if(list.get(j).connections.size() < mi) {
					int rIndex = j;
					while (rIndex == j || list.get(j).connections.contains(rIndex))
						rIndex=tmp.get((new Random()).nextInt(tmp.size()));
					//System.out.println(j+" "+rIndex+" "+list.get(j).connections.size()+" "+tmp );
					
					list.get(j).connections.add(rIndex);
					list.get(rIndex).connections.add(j);
					

					if (list.get(rIndex).connections.size() >= mi)
						tmp.remove(tmp.indexOf(rIndex));
					// possible optimization
					if (list.get(j).connections.size() >= mi)
						tmp.remove(tmp.indexOf(j));
				}
				//tmp.remove(tmp.indexOf(j));
			}
	}
		//}

	}

}
