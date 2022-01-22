package prereqchecker;

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
 * ValidPreReqInputFile name is passed through the command line as args[1]
 * Read from ValidPreReqInputFile with the format:
 * 1. 1 line containing the proposed advanced course
 * 2. 1 line containing the proposed prereq to the advanced course
 * 
 * Step 3:
 * ValidPreReqOutputFile name is passed through the command line as args[2]
 * Output to ValidPreReqOutputFile with the format:
 * 1. 1 line, containing either the word "YES" or "NO"
 */
public class ValidPrereq {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
            return;
        }
	// WRITE YOUR CODE HERE

    Graph map=new Graph(args[0]);
    StdIn.setFile(args[1]);
    String t1=StdIn.readLine();
    String t2=StdIn.readLine();
    map.addp(t1,t2);
    String temp=map.gp(t1).get(0);
    String out=course(temp,t1,map);
    StdOut.setFile(args[2]);
    StdOut.println(out);
    }
    private static String course(String a, String t1, Graph map){
        if(a.compareTo(t1)==0){
            String out2="NO";
            return out2;
        }
        else{
            String out2="YES";
            int sizem = map.gp(a).size();
            for(int i=0;i<sizem;i++){
                String f=map.gp(a).get(i);
                if(course(f,t1,map).compareTo("NO")==0){
                    out2="NO";
                    return out2;
                }
            }
            return out2;
        }
    }
}
