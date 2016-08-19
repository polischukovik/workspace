package algorythm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

class WaveAlgorytm {
	Map<Integer,Integer> labels = new HashMap<Integer, Integer>();
	
	public WaveAlgorytm(Integer startNode) {		
		labels.put(startNode, 0);
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
             System.err.println("NODE "+ k + ": " + showNode(k));
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

			
			
			
			System.out.println(0 + " " + 0);
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // Example: 0 1 are the indices of the nodes you wish to sever the link between
            //System.out.println("0 1");
        }
	
	static String showNode(Integer k){
		return " node: " + k + ": " + net.get(k).stream().map(n -> n.toString()).collect(Collectors.joining(","));
	}    

}
