package prereqchecker;
import java.util.*;
public class Graph{
    private final int vertex;
    private HashMap<String,ArrayList<String>> map;

    public Graph(String inputFileName){
        StdIn.setFile(inputFileName);
        int out=1;
        while(!StdIn.isEmpty()){
            out=StdIn.readInt();
            StdIn.readLine();
            this.map=new HashMap<>();
            for(int i=0;i<out;i++){
                String temp=StdIn.readLine();
                map.put(temp,new ArrayList<String>());
            }
            int t=StdIn.readInt();
            StdIn.readLine();
            for(int j=0;j<t;j++){
                String temp2=StdIn.readLine();
                String[] temp23=temp2.split(" ");
                ArrayList<String> out2=map.get(temp23[0]);
                out2.add(temp23[1]);
            }  
        }
        this.vertex=out;   
    }


    public int gv(){
        return vertex;
    }

    public HashMap<String, ArrayList<String>> gm(){
        return map;
    }

    public ArrayList<String> gp(String e){
        return map.get(e);
    }
    
    public void addp(String c,String p){
        map.get(c).add(0,p);
    }




















}