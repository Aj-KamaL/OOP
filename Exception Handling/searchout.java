//Raj Kamal Yadav 2016076 Group -3
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;
 
class	NoException	extends	Exception	{	
	public	NoException(String	message)	{	
		super(message);	
	}	
}
class	NonCoordinateException	extends	Exception	{	
	public	NonCoordinateException(String	message)	{	
		super(message);	
	}	
}
class	StackEmptyException	extends	Exception	{	
	public	StackEmptyException(String	message)	{	
		super(message);	
	}	
}
class	OverlapException	extends	Exception	{	
	public	OverlapException(String	message)	{	
		super(message);	
	}	
}
class	QueenFoundExcep	extends	Exception	{	
	public	QueenFoundExcep(String	message)	{	
		super(message);	
	}	
}

class Reader{
    static BufferedReader reader;
    static StringTokenizer tokenizer;
    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }
    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                    reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }

    static float nextFloat() throws IOException {
        return Float.parseFloat( next() );
    }
}
//self created Stack data stucture to avoid index confusion and other method's 
class Stack<T>
{
    private ArrayList<T> stack ;
    private int front_pointr, length;    
    public Stack()
    {       
        length = 0; 
        stack=new ArrayList<T>();
        front_pointr = -1;
    }
    public boolean isEmpty()
    {
        return front_pointr == -1;
    }   
    public void push(T i)
    {        
        stack.add(i);
        length++ ;
        front_pointr=front_pointr+1;
    }

    public T pop()
    {
    	
        if( !isEmpty() )
        {       
        length--;
        T y=stack.get(front_pointr);
        stack.remove(front_pointr--); 
        return y;
		}
        return null;
    }        
    //need to change
    public void display()
    {      
        if (length!= 0)
        {     
        for (int i = 0; i <=front_pointr ; i++){
            System.out.println(stack.get(i));
        }
        System.out.println();
    	}
    }    
}
//Knight class 
class Knight{
	private String Name;
	public Stack<String> getMagic_box() {
		return magic_box;
	}
	public void setMagic_box(Stack<String> magic_box) {
		this.magic_box = magic_box;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getItr() {
		return itr;
	}
	public void setItr(int itr) {
		this.itr = itr;
	}
	public String getName() {
		return Name;
	}
	private Stack<String> magic_box;
	private int x,y;
	private int itr;
	public Knight(String nam,int a,int b){
		this.Name=nam;
		this.x=a;
		this.y=b;
		magic_box=new Stack<String>();
		itr=1;
	
	}
	//method to print the details of each of the Knight if needed
	public void givdetails(){
		System.out.println(Name);
		magic_box.display();		
	}
	
}
//main class to searh the queen
public class searchout {
	static int fg=0;//flag to stop trying in either case queen found or no knights left to rescue her
	static ArrayList<String> towrite; //a list where all the exceptions are stored to be printed later
	private int No_of_knights;
	private int No_of_iteration; 
	private int Target_queen_X;
	private int Target_queen_Y;
	private ArrayList<Knight> Knights;	
	
	public searchout(int no_of_knights, int no_of_iteration, int target_queen_X, int target_queen_Y,ArrayList<Knight> knights) {
	this.No_of_knights=no_of_knights;
	this.No_of_iteration=no_of_iteration;
	this.Target_queen_X=target_queen_X;
	this.Target_queen_Y=target_queen_Y;
	this.Knights=knights;
	this.towrite=new ArrayList<String>();
	
	}
	//here is the knight's new about locations are compared with other's to move previous or just allocate the coming one 
	public  void updt_the_cord(Knight a, String string, String string2) throws OverlapException, NoException  {
		for(int i=0;i<Knights.size();i++){
			if(Knights.get(i).getX()==Integer.parseInt(string)&& Knights.get(i).getY()==Integer.parseInt(string2)){				
				a.setX(Knights.get(i).getX());
				a.setY(Knights.get(i).getY());
				String message2="OverlapException: Knights Overlap Exception "+Knights.get(i).getName();
				Knights.remove(i);				
				throw new OverlapException(message2);
			}
		}				
		//if everything happens as supposed we let it continue informing there is no exception
				a.setX(Integer.parseInt(string));
				a.setY(Integer.parseInt(string2));
				String message114="No Exception "+Knights.get(0).getX()+" "+Knights.get(0).getY();
				throw new NoException(message114);
	}
	// here is checked if the knight is succeded in the mission if yes tha the flag we discussed earlier is turned 1 else new locations are provided above
	public  void check_the_cord(Knight a, String string, String string2) throws OverlapException, NoException {
		try{
			if(Target_queen_X==Integer.parseInt(string)&& Target_queen_Y==Integer.parseInt(string2)){
				String message6="QueenFoundException: Queen has been Found. Abort!";
				throw new QueenFoundExcep(message6);
			}
			else{
				updt_the_cord(a, string, string2);
			}
		}
		catch(QueenFoundExcep e){
			fg=1;
			//System.out.println(e.getMessage());
			towrite.add(e.getMessage());
		}
		
			
	}
	//after beginning search move is made after assuring that the knight magic box is not empty and new Cordinates are looked for and than coordinated are observed above
	public  void make_a_move(Knight a) throws OverlapException, NonCoordinateException, StackEmptyException, NoException {		
		if(a.getMagic_box().isEmpty()!=true){
			String item=a.getMagic_box().pop();
			String[] temp2=item.split(" ");
			
			if(temp2[0].equals("Coordinate")){
				check_the_cord(a,temp2[1],temp2[2]);
			}		
			else{
				String message="NonCoordinateException: Not a coordinate Exception "+ temp2[1] ;
				throw new NonCoordinateException(message);
			}
		}
		else{
			String message3="StackEmptyException:Stack Empty exception";
			Knights.remove(a);	
			throw new StackEmptyException(message3);
		}
	}	
	//search begins here after assuring that there is atleast one knight its iteration is checked than the move is made accordingly above followed by incrementing its iteration
	public void pursuit() throws NonCoordinateException, OverlapException, StackEmptyException, NoException{
				if(Knights.size()!=0){
					if(Knights.get(0).getItr()<=No_of_iteration){
						String tp=Knights.get(0).getItr()+" "+Knights.get(0).getName()+" "+Knights.get(0).getX()+" "+Knights.get(0).getY();
						//System.out.println(tp);
						towrite.add(tp);
						Knights.get(0).setItr(Knights.get(0).getItr()+1);
						make_a_move(Knights.get(0));						
					}
					else{						
						Knights.remove(0);					
					}
				}
				else{
					fg=1;
				}	
	}	
	public static  void main(String[] args) throws IOException,FileNotFoundException,NoException,QueenFoundExcep,StackEmptyException,OverlapException, NonCoordinateException{
		Reader.init(System.in);
		File dir = new File("C:/Test Case/Input");
		ArrayList<Knight> knights=new ArrayList<Knight>();		
		for (File file : dir.listFiles()) {
			Scanner s = new Scanner(file);
			ArrayList<String> ehdetail=new ArrayList<String>();
			while(s.hasNextLine()!=false){				
				String readline =s.nextLine();
				ehdetail.add(readline);					
			}
			String[] temp1=ehdetail.get(1).split(" ");
			Knight A =new Knight(ehdetail.get(0), Integer.parseInt(temp1[0]),Integer.parseInt(temp1[1]));
			for(int i=3;i<3+Integer.parseInt(ehdetail.get(2));i++){		
				A.getMagic_box().push(ehdetail.get(i));
			}			
			knights.add(A);
		}		
//		for(int i=0;i<knights.size();i++){
//		knights.get(i).givdetails();
//		}		
		bubbleSort(knights);		
//		System.out.println();
//		System.out.println("aftersort");
//		System.out.println();
//		System.out.println();		
//		for(int i=0;i<knights.size();i++){
//			knights.get(i).givdetails();
//			}		
		int no_of_knights=Reader.nextInt();
		int no_of_iteration=Reader.nextInt();
		int target_queen_X=Reader.nextInt();
		int target_queen_Y=Reader.nextInt();
		searchout quest =new searchout(no_of_knights,no_of_iteration,target_queen_X,target_queen_Y,knights);		
		while(fg==0){
		try {
			quest.pursuit();
		}catch (NonCoordinateException e) {				
			//System.out.println(e.getMessage());
			towrite.add(e.getMessage());
			Knight tem=quest.Knights.get(0);
			quest.Knights.remove(0);
			quest.Knights.add(tem);
		}catch (OverlapException e) {			
			//System.out.println(e.  getMessage());
			towrite.add(e.getMessage());
			Knight tem=quest.Knights.get(0);
			quest.Knights.remove(0);
			quest.Knights.add(tem);			
		}catch (StackEmptyException e) {			
			//System.out.println(e.getMessage());	
			towrite.add(e.getMessage());
		}catch (NoException e) {			
			//System.out.println(e.getMessage());
			towrite.add(e.getMessage());
			Knight tem=quest.Knights.get(0);
			quest.Knights.remove(0);
			quest.Knights.add(tem);
		}		
		}
		quest.write(towrite);
	}
	//to sort the knights lexicographically initially right after their addition 
	public static void bubbleSort(ArrayList<Knight> knights2) {  
        int n = knights2.size();  
        Knight temp=new Knight("isld",3,3);  
         for(int i=0; i < n; i++){  
                 for(int j=1; j < (n-i); j++){  
                          if(knights2.get(j-1).getName().compareTo(knights2.get(j).getName()) > 0){                              
                                 temp = knights2.get(j-1);  
                                 knights2.set(j-1, knights2.get(j));                         
                                 knights2.set(j, temp);  
                         }}}}
    //finally the write method to write the desired outpt in the defined location
	private void write(ArrayList<String> hike) throws IOException 
	{
		File file = new File("C:/Test Case/Output/Hellotry1.txt");
    	FileWriter writer;    
	      file.createNewFile();
	      writer = new FileWriter(file); 
		for(int i=0;i<hike.size();i++){
			writer.write(hike.get(i));
			writer.write("\n");
		}	
	      writer.flush();        
	      writer.close();
	}
}