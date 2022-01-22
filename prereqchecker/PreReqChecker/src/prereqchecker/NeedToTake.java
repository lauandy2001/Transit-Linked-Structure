package prereqchecker;

import java.util.*;

/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * NeedToTakeInputFile name is passed through the command line as args[1]
 * Read from NeedToTakeInputFile with the format:
 * 1. One line, containing a course ID
 * 2. c (int): Number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * NeedToTakeOutputFile name is passed through the command line as args[2]
 * Output to NeedToTakeOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class NeedToTake {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
            return;
        }

	// WRITE YOUR CODE HERE
    Graph map=new Graph(args[0]);
    StdIn.setFile(args[1]);
    String out=" ";
    ArrayList<String> t=new ArrayList<String>();
    ArrayList<String> t2= new ArrayList<String>();
    ArrayList<String> t3= new ArrayList<String>();
    ArrayList<String> t4= new ArrayList<String>();
    while(!StdIn.isEmpty()){
        out=StdIn.readLine();
        int temp = StdIn.readInt();
        StdIn.readLine();
        for(int i=0;i<temp;i++){
            String tempc=StdIn.readLine();
            t.add(tempc);
        }
    }
    t2.addAll(t);
    for(int a=0;a<t.size();a++){
        int sizem=map.gp(t.get(a)).size();
        if(sizem!=0){
            for(int b=0;b<sizem;b++){
                String at=t.get(a);
                String ce=map.gp(at).get(b);
                find(t2,ce,map);
            }       
        }
    }
    int sizec=map.gp(out).size();
    for(int c=0;c<sizec;c++){
        String ce=map.gp(out).get(c);
        find(t3,ce,map);
    }
    for(int d=0;d<t3.size();d++){
        if(!t2.contains(t3.get(d))){
            String temp_out=t3.get(d);
            t4.add(temp_out);
        }
    }
    StdOut.setFile(args[2]);
    for(int e=0;e<t4.size();e++){
        String get_out=t4.get(e);
        StdOut.println(get_out);
    }
    
    }
    private static void find(ArrayList<String> t2, String ce, Graph map){
        if(!t2.contains(ce)){
            t2.add(ce);
        }
        int sizet=map.gp(ce).size();
        if(sizet!=0){
            for(int i=0;i<sizet;i++){
                String temp_fc=map.gp(ce).get(i);
                find(t2,temp_fc,map);
            }
        }



    }
}
