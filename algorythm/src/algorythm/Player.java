package algorythm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

class WaveAlgorytm {
	HashMap<Integer, List<Integer>> net;
	Map<Integer,Integer> labels;
	int nodeCnt;
	int startNode, endNode;
	List<Integer> path;
	
	public WaveAlgorytm(int startNode, int targetNode, HashMap<Integer, List<Integer>> net, int nodeCnt) {		
		this.net = net;
		this.nodeCnt = nodeCnt;
		this.labels = new HashMap<>();
		this.path = new ArrayList<>();
		this.startNode = startNode;
		this.endNode = targetNode;
//		for(Integer n : net.keySet()){
//			labels.put(n, null);
//		}
		labels.put(startNode, 0);
		evalWave();
		recPath(targetNode);
	}
	
	private void evalWave(){
		int lblCnt = 0;
		for(; lblCnt < nodeCnt - 1; lblCnt++){
			//get nodes with label N
			for(int n : getLabeled(lblCnt)){
				//get neighbors and mark them N+1
				for(int x : net.get(n)){
					if(!labels.containsKey(x)){
						labels.put(x, lblCnt + 1);
						//finish when riches target
						if(x == endNode) return;
					}
				}
			}
		}
	}
	
	private void recPath(int c){
		path.add(c);
		if(c == startNode) return;
		for(int x : net.get(c)){
			if(labels.get(x).equals(labels.get(c) - 1)){
				recPath(x);
				break;
			}
		}
	}
	
	private List<Integer> getLabeled(int N){
		List<Integer> list = new ArrayList<>();
		for(int i : labels.keySet()){
			if(labels.get(i).equals(N)) list.add(i);
		}
		return list;
	}
	
	public List<Integer> getPath(){
		return path;
	}

}

public class Player {
	
	static HashMap<Integer, List<Integer>> net;
	static int SI;
	
	public static void main(String args[])  {
        Scanner in = new Scanner(System.in);
        int N =12;// in.nextInt(); // the total number of nodes in the level, including the gateways
        System.err.println("N="+N);
        int L =23;// in.nextInt(); // the number of links
        System.err.println("L="+L);
        int E =1;// in.nextInt(); // the number of exit gateways
        System.err.println("E="+E);
        net = new HashMap<>();
		int[] gateway = new int[E];
        
		int[] nodes1 = {11,0,1,0,10,11,2,4,8,6,7,0,3,0,11,0,0,9,0,0,0,0,5};
		int[] nodes2 = {6,9,2,1,1,5,3,5,9,7,8,6,4,2,7,8,4,10,5,7,3,10,6};
		
        for (int i = 0; i < L; i++) {
            int N1 = nodes1[i];//in.nextInt(); // N1 and N2 defines a link between these nodes            
            int N2 = nodes2[i];//in.nextInt();
            System.err.println("N1="+N1+" N2="+N2);
			
			List<Integer> l = net.get(N1);
			if(l == null) l = new ArrayList<Integer>();
			l.add(N2);
			net.put(N1,l);
			
			l = net.get(N2);
			if(l == null) l = new ArrayList<Integer>();
			l.add(N1);
			net.put(N2,l);
        }
        
        for(Integer k : net.keySet()){
             System.err.println("NODE "+ k + ": " + " node: " + k + ": " + net.get(k).stream().map(n -> n.toString()).collect(Collectors.joining(",")));
        }
        
        for (int i = 0; i < E; i++) {
            int EI =0;// in.nextInt(); // the index of a gateway node
            System.err.println("EI"+EI);
			gateway[i] = EI;
        }

        // game loop
        while (true) {

            int curN,curE;
            SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn
            System.err.println("SI="+SI);

			WaveAlgorytm w = new WaveAlgorytm(gateway[0], SI, net, L);
			System.err.println("Path: " + w.getPath().stream().map(s -> s.toString()).collect(Collectors.joining(",")));
			
			
			System.out.println(0 + " " + 0);
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // Example: 0 1 are the indices of the nodes you wish to sever the link between
            //System.out.println("0 1");
        }
	}
}
