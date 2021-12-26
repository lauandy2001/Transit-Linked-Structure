import java.util.ArrayList;

/**
 * This class contains methods which perform various operations on a layered linked
 * list to simulate transit
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class Transit {
	/**
	 * Makes a layered linked list representing the given arrays of train stations, bus
	 * stops, and walking locations. Each layer begins with a location of 0, even though
	 * the arrays don't contain the value 0.
	 * 
	 * @param trainStations Int array listing all the train stations
	 * @param busStops Int array listing all the bus stops
	 * @param locations Int array listing all the walking locations (always increments by 1)
	 * @return The zero node in the train layer of the final layered linked list
	 */
	public static TNode makeList(int[] trainStations, int[] busStops, int[] locations) {
		// WRITE YOUR CODE HERE	
		ArrayList<Integer> link1 = new ArrayList<Integer>(); //1=ts, 2=buss, 3=loc
		link1.add(0);
		for(int i = 0; i<locations.length; i++){
			link1.add(new Integer(locations[i]));
		}
		TNode l1h = null;
		int size1 = link1.size();
		for(int j = size1-1;j>=0;j--){
			TNode temp = new TNode();
			temp.location=link1.get(j);
			temp.down=null;
			temp.next=l1h;
			l1h=temp;
		}

		ArrayList<Integer> link2 = new ArrayList<Integer>();
		link2.add(0);
		for(int i = 0; i<busStops.length; i++){
			link2.add(new Integer(busStops[i]));
		}
		TNode l2h = null;
		int size2 = link2.size();
		for(int j = size2-1;j>=0;j--){
			TNode temp = new TNode();
			temp.location=link2.get(j);
			temp.down=null;
			temp.next=l2h;
			l2h=temp;
		}

		ArrayList<Integer> link3 = new ArrayList<Integer>();
		link3.add(0);
		for(int i = 0; i<trainStations.length; i++){
			link3.add(new Integer(trainStations[i]));
		}
		TNode l3h = null;
		int size3 = link3.size();
		for(int j = size3-1;j>=0;j--){
			TNode temp = new TNode();
			temp.location=link3.get(j);
			temp.down=null;
			temp.next=l3h;
			l3h=temp;
		}

		TNode ptr1 = l1h; 
		TNode ptr2 = l2h; 
		while((ptr1!=null)&&(ptr2!=null)){
			if(ptr1.location==ptr2.location){
				ptr2.down=ptr1;
				ptr2=ptr2.next; //swiiit
			}
			else{
				ptr1=ptr1.next;
			}

		}
		TNode temp2 = l2h; 
		TNode temp3 = l3h; 
		while((temp2!=null)&&(temp3!=null)){
			if(temp2.location==temp3.location){
				temp3.down=temp2;
				temp3=temp3.next;
			} 
			else{
				temp2=temp2.next;
			}
		}
		return l3h;
	}
	




	/**
	 * Modifies the given layered list to remove the given train station but NOT its associated
	 * bus stop or walking location. Do nothing if the train station doesn't exist
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param station The location of the train station to remove
	 */
	public static void removeTrainStation(TNode trainZero, int station) {
		// WRITE YOUR CODE HERE
		TNode ptr=trainZero;
		while(ptr.next!=null){
			if(ptr.next.location==station){
				ptr.next=ptr.next.next;
				break; //break from loop
			}
			ptr=ptr.next;
		}

	}

	/**
	 * Modifies the given layered list to add a new bus stop at the specified location. Do nothing
	 * if there is no corresponding walking location.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param busStop The location of the bus stop to add
	 */
	public static void addBusStop(TNode trainZero, int busStop) {
		// WRITE YOUR CODE HERE
		TNode ptr1=trainZero.down.down; //change 1 to 2, from down to no down
		TNode ptr2=trainZero.down; 
		while(ptr1!=null){
			if((ptr2.next==null)||(ptr1.location<ptr2.next.location)){
				ptr1=ptr1.next;
				StdOut.println(ptr1.location);
			} 
			else{
				ptr2=ptr2.next;		
				StdOut.println(ptr2.location);
			}


			if((ptr2.next!=null)&&(ptr1.location==ptr2.next.location)&&(ptr1.location==busStop)){
				break;
			}
			else if(ptr1.location==busStop){ //if to elseif
				TNode temp = new TNode(busStop,ptr2.next,ptr1);
				ptr2.next=temp;
				break;
			}
		}
	}
	
	/**
	 * Determines the optimal path to get to a given destination in the walking layer, and 
	 * collects all the nodes which are visited in this path into an arraylist. 
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param destination An int representing the destination
	 * @return
	 */
	public static ArrayList<TNode> bestPath(TNode trainZero, int destination) {
		// WRITE YOUR CODE HERE
	ArrayList<TNode> out= new ArrayList<TNode>();
		TNode ptr= trainZero; 
		while(ptr.location<=destination){
			out.add(ptr);
			if((ptr.next!=null)&&(ptr.next.location<=destination)){
				ptr=ptr.next;
			}
			else{
				ptr=ptr.down;
			}
			if(ptr==null){
				break;
			}
		}
		return out;

	}

	/**
	 * Returns a deep copy of the given layered list, which contains exactly the same
	 * locations and connections, but every node is a NEW node.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @return
	 */
	public static TNode duplicate(TNode trainZero) {
		// WRITE YOUR CODE HERE
		ArrayList<Integer> link1 = new ArrayList<Integer>();
		TNode ptr1=trainZero.down.down;
		while(ptr1!=null){
			link1.add(ptr1.location);
			ptr1=ptr1.next;
		}
		TNode l1h=null;
		int sizeA = link1.size();
		for(int i = sizeA-1; i>=0; i--){
			int a = link1.get(i);
			TNode nodeA = new TNode(a,l1h,null);
			l1h=nodeA;
		}

		ArrayList<Integer> link2 = new ArrayList<Integer>();
		TNode ptr2=trainZero.down;
		while(ptr2!=null){
			link2.add(ptr2.location);
			ptr2=ptr2.next;
		}
		TNode l2h=null;
		int sizeB = link2.size();
		for(int j = sizeB-1; j>=0; j--){
			int b = link2.get(j);
			TNode nodeB = new TNode(b,l2h,null);
			l2h=nodeB;
		}

		ArrayList<Integer> link3 = new ArrayList<Integer>();
		TNode ptr3=trainZero;
		while(ptr3!=null){
			link3.add(ptr3.location);
			ptr3=ptr3.next;
		}
		TNode l3h=null;
		int sizeC=link3.size();
		for(int k = sizeC-1; k>=0; k--){
			int c = link3.get(k);
			TNode nodeC = new TNode(c,l3h,null);
			l3h=nodeC;
		}
																

		TNode temp1 = l1h; 
		TNode temp2 = l2h; 
		while((temp1!=null)&&(temp2!=null)){
			if(temp1.location==temp2.location){
				temp2.down=temp1;
				temp2=temp2.next;
			}
			else{
				temp1=temp1.next;
			}
		}
		TNode temp3 = l2h; 
		TNode temp4 = l3h; 
		while((temp3!=null)&&(temp4!=null)){
			if(temp3.location==temp4.location){
				temp4.down=temp3;
				temp4=temp4.next;
			}
			else{
				temp3=temp3.next;
			}
		}
		return l3h;
	}

	/**
	 * Modifies the given layered list to add a scooter layer in between the bus and
	 * walking layer.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param scooterStops An int array representing where the scooter stops are located
	 */
	public static void addScooter(TNode trainZero, int[] scooterStops) {
		// WRITE YOUR CODE HERE
		ArrayList<Integer> s = new ArrayList<Integer>();
		s.add(0);

		for(int i = 0; i<scooterStops.length; i++){
			s.add(scooterStops[i]);
		}

		TNode head=null;
		int sizeS = s.size();
		for(int j = sizeS-1;j>=0;j--){
			TNode temp = new TNode(s.get(j),head,null);
			head=temp;
		}

		TNode ptr1= trainZero.down.down; 
		TNode ptr2= head; 
		while((ptr1!=null)&&(ptr2!=null)){
			if(ptr1.location==ptr2.location){
				ptr2.down=ptr1;
				ptr2=ptr2.next;
			}
			else{
				ptr1=ptr1.next;
			}
		}
	
		TNode temp2=head; 
		TNode temp3=trainZero.down; 
		while((temp2!=null)&&(temp3!=null)){
			if(temp2.location==temp3.location){
				temp3.down=temp2;
				temp3=temp3.next;
			}
			else{
				temp2=temp2.next;
			}
		}

	}
}