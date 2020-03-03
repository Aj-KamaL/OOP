//Raj Kamal Yadav 2016076 Group -3
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import javax.swing.event.AncestorEvent;
//this is used to store the last stage info per student that is his roll no. and his christmas tree
import java.io.PrintWriter;
import java.util.Random;

class BSTFilesBuilder {

	public void createBSTFiles(int numStudents, int numTrees) {
		Random rand = new Random();
		for (int i = 1; i <= numTrees; i++) {
		    try {
				PrintWriter w = new PrintWriter("./src/" + i + ".txt", "UTF-8");
				int type = rand.nextInt(3) + 1;
				if(type == 1) {
					w.println("Integer");
					w.println(numStudents);
					for (int j = 1; j <= numStudents; j++) {
						w.print(rand.nextInt(1000));
						w.print(" ");
					}
				}
				else if(type == 2) {
					w.println("Float");
					w.println(numStudents);
					for (int j = 1; j <= numStudents; j++) {
						w.print(rand.nextFloat()*1000);
						w.print(" ");
					}
				}
				else {
					w.println("String");
					w.println(numStudents);
					String alphabet = "abcdefghijklmnopqrstuvwxyz";
					for (int j = 1; j <= numStudents; j++) {
						int len = rand.nextInt(10)+1;
						for (int k = 0; k < len; k++)
							w.print(alphabet.charAt(rand.nextInt(alphabet.length())));
						w.print(" ");
					}
				}
				w.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class student<T>{
	int rollNo;
	ArrayList<T> items;
	public student(int a,ArrayList<T> items){
		this.rollNo=a;
		this.items=items;
	}
}

interface Comparable<T> {
    public int compareTo(T o);
}
class Node<T> implements Comparable<Node>{
	T val;
    Node left, right;

    public Node(T val2){
        val = val2;
        left = right = null;
    }
	@Override
	public int compareTo(Node o) {		
		if(o !=	null	&&	o.val.getClass()=="hh".getClass())	{
			String	sec	=	(String) o.val;	
			int comparison = 0;
		    int c1, c2;
		    for(int i = 0; i <((String)val).length() && i < sec.length(); i++) {
		        c1 = (int) ((String)val).charAt(i);   // See note 1
		        c2 = (int) sec.charAt(i);   // See note 1 if case insensitive tha put lowecase
		        comparison = c1 - c2;   // See note 2

		        if(comparison != 0)     // See note 3
		            return comparison;
		    }
		    if(((String)val).length() > sec.length())    // See note 4
		        return 1;
		    else if (((String)val).length() < sec.length())
		        return -1;
		    else
		        return 0;
		}	
		else if(o !=null	&& o.val.getClass()==Integer.class){
			
			int sec=(int) o.val;
		
			if((int)val>sec){
				return 1;
			}
			else if((int)val<sec){
				return -1;
			}
			else{
				return 0;
			}
		}
		else{
			float sec=(float) (o.val);
			if((float)val>sec){
				return 1;
			}
			else if((float)val<sec){
				return -1;
			}
			else{
				return 0;
			}
		}
	}
		
}
//a generic bst class which can create bst for string int float(float) as well
class BST<T> {
	int sum;float total;String all;
	Node root;  
    public BST() { 
        root = null;        
    }     
    public <T> void ins(T odd) {
       root = putin(root, odd);
    }     
     <T> Node putin(Node root,T val) { //ALERT
        if (root == null) {
            root = new Node(val);
            return root;
        }        
        Node<T> r=new Node(val);
         if ( root.compareTo(r)>0)
            root.left = putin(root.left, val);
        else if (root.compareTo(root)<=0)//assuming that equal values can be provided
            root.right = putin(root.right, val); 
        return root;        
    }
	public void print(Node root) {
		 if (root != null)
	        {
	        	print(root.left);
	        	System.out.println(root.val);	        
	            print(root.right);		
	        } }
	//below function are used to derive the position of the root node in the inorder traversal
	public <T> int position(T val) {
		   return positionHelper(val, root, 1);
		}
	public <T> int positionHelper(T val, Node currentNode, int steps) {
		   if(currentNode.left != null) {
		       steps = positionHelper(val, currentNode.left, steps++);}
		   if(currentNode.val.equals(val)) {
		       return steps;
		   }
		   steps++;
		   if(currentNode.right != null) {
		       steps = positionHelper(val, currentNode.right, steps++);
		   }
		   return steps;
		}
	//below are three overloaded methods that are used to sum all the encarvings
	public void gettotals(Node hit) {	
	        if (hit == null){
	            return;}	 
	        gettotals(hit.left);	        
	        this.all=this.all+(String)hit.val;
	        gettotals(hit.right);
	    }
	public void gettotali(Node hit) {	
        if (hit == null){
            return;}	 
        gettotali(hit.left);	        
        	this.sum=this.sum+(int)hit.val;	        
        gettotali(hit.right);
    }
	public void gettotald(Node hit) {	
        if (hit == null){
            return;}	 
        gettotald(hit.left);	       
        this.total=this.total+(float)hit.val;	        
        gettotald(hit.right);
    }

}
public class Gift_distribution{	

	ArrayList<BST> list;
	int ss;
	ArrayList<Integer> whattodo;
	ArrayList<Object> ans ;	
	public Gift_distribution(ArrayList<BST> collectn,int tnos, ArrayList<Integer> flag, ArrayList<Object> answers){
		this.list=collectn;
		this.ss=tnos;
		this.whattodo=flag;
		this.ans=answers;
	} 
	//a  generic method that distributes the gifts to different students based on their inorder position
	public  ArrayList<?> distriute(){	
		ArrayList<Object> result=new ArrayList<Object>();
		for(int y=0;y<list.size();y++){	
			BST temp =list.get(y);
			String s="";int ss=0;float sss=0;
			Node hit =temp.root;	
			result.add(temp.position(hit.val));	
			result.add(ans.get(y));			
		}		
		return result;
	}	
	public static <T> void main(String[] args) throws IOException{
		//to read the file from given location
		BSTFilesBuilder lnm=new BSTFilesBuilder();
		Scanner ic=new Scanner(System.in);
		int aa=ic.nextInt();
		int bb=ic.nextInt();
		lnm.createBSTFiles(aa, bb);
		
		
		
		File dir = new File("C:/TEST_CASES/T3/input");
		ArrayList list =new ArrayList<BST>() ;
		ArrayList<Integer> flag=new ArrayList<Integer>();
		ArrayList<Object> answers=new ArrayList<>();
		int nos=0;
		for (File file : dir.listFiles()) {
			//taking the files simultaneously and extracting and filling information based on string integer float(float)
		    Scanner s = new Scanner(file);
		    while(s.hasNextLine()!=false){
		    	String readline =s.nextLine();
		    	if(readline.equals("String")){
		    		flag .add(0);
		    		BST imp2=new BST();
		    		nos=Integer.parseInt(s.nextLine());
		    		String[] t=s.nextLine().split(" ");
		    		
		    		for(int i=0;i<t.length;i++){
		    			imp2.ins((String) t[i]);
		    			
		    		}
		    		imp2.gettotals(imp2.root);
		    		answers.add(imp2.all.substring(4,imp2.all.length()));
		    		list.add(imp2);
		    	}
		    	else if(readline.equals("Integer")){
		    		flag.add(1);
		    		BST imp3=new BST();
		    		nos=Integer.parseInt(s.nextLine());
		    		String t[]=s.nextLine().split(" ");
		    
		    		for(int i=0;i<t.length;i++){		    	
		    			imp3.ins(Integer.parseInt(t[i]));
		    		}		 
		    		imp3.gettotali(imp3.root);
		    		answers.add(imp3.sum);
		    		list.add(imp3);		    	
		    	}
		    	else if(readline.equals("Float")){
		    		flag.add(2);		    		
		    		BST imp4=new BST();
		    		nos=Integer.parseInt(s.nextLine());
		    		String[] t=s.nextLine().split(" ");
		    		float temp=0;
		    		for(int i=0;i<t.length;i++){
		    			imp4.ins(Float.parseFloat(t[i]));
		    		}		
		    		imp4.gettotald(imp4.root);
		    		answers.add(imp4.total);
		    		list.add(imp4);   	
		    		
		    	}
		    }
		    s.close();
		    }
		
		Gift_distribution Christoval_School=new Gift_distribution(list,nos,flag,answers);
		
		ArrayList<?> ans=Christoval_School.distriute();	
		for(int p=0;p<ans.size();p++){
			System.out.println(ans.get(p));
		}
		
		ArrayList<student> hike=new ArrayList<student>();	
		//ans contains unordered information about the studen and its gift
		int c=1;
		while(c<=nos){			
			boolean g=false;
			ArrayList<Object> axx=new ArrayList<>();
		for(int i=0;i<ans.size();i=i+2){		
			if((int)ans.get(i)==c){			
				axx.add(ans.get(i+1));	
				g=true;				
			}}
			if(g==true){
			student ty=new student(c,axx);
			hike.add(ty);}
			c=c+1;
		}		
//		
	//	method to print the details in the console
//		for(int i=0;i<hike.size();i++){
//			System.out.print(hike.get(i).rollNo+" ");
//			for(int j=0;j<hike.get(i).items.size();j++){
//				System.out.print(hike.get(i).items.get(j)+" ");
//			}
//			System.out.println();
//		}	
		//write in the file
		Christoval_School.write(hike,nos);		
		
	}
	
	private void write(ArrayList<student> hike,int nos) throws IOException 
	{
		int k;	
		File file = new File("C:/TEST_CASES/T2/output/Hello1.txt");
    	FileWriter writer;
	      k=nos-hike.size();
	     // System.out.println(k);
	      file.createNewFile();
	      writer = new FileWriter(file); 
			for(int i=0;i<hike.size();i++){
			writer.write(hike.get(i).rollNo+"  ");
			for(int j=0;j<hike.get(i).items.size();j++){
				writer.write(hike.get(i).items.get(j)+"  ");
			}
			writer.write("\n");
		}
		writer.write( String.valueOf(k) );
	      writer.flush();        
	      writer.close();
	}
	


}
