//Raj Kamal Yadav 2016076 
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
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
class Song implements Serializable
{
	String NoS;	
	String NoSi;
	String D;
	public Song(	String NoS,	String NoSi,String D)
	{
		this.NoS=NoS;
		this.NoSi=NoSi;
		this.D=D;
	}
	public void printing()
	{
		System.out.println("Name of the Song : "+" "+NoS);
		System.out.println("Name of the Singer : "+" "+ NoSi);
		System.out.println("Duration of the Song : "+" "+D);
	}
}
class Playlist implements Serializable{
	ArrayList<Song> Pl;
	String Name;
	public Playlist(String h){
		Pl=new ArrayList<Song>();
		Name=h;
	}
}
public class MobileApp implements Serializable
{
	ArrayList<Playlist> Pl;
	ArrayList<Song> gb;
	int exit;
	public MobileApp(){
		 Pl=new ArrayList<Playlist>();
		 gb=new ArrayList<Song>();

		 exit=0;
	}
	public void menu(Playlist playlist) throws IOException 
	{
		int exitting=0;
		System.out.println(playlist.Pl.size());
		while(exitting!=1){
			System.out.println("1. Add");
			System.out.println("2. Delete");
			System.out.println("3. Search");
			System.out.println("4. Show");
			System.out.println("5. Back to menu");
			System.out.println("6. Exit");
			int option =Reader.nextInt();
			if(option==1){
				String name=Reader.reader.readLine();
				String singer=Reader.reader.readLine();
				String duration=Reader.reader.readLine();	
				Song TRP=new Song(name,singer,duration);
				playlist.Pl.add(TRP);
				System.out.println(playlist.Pl.size());			

			}
			else if(option==2){
				String h=Reader.reader.readLine();
				int yes=0;
				for(int i=0;i<playlist.Pl.size();i++)
				{
					if(playlist.Pl.get(i).NoS.equals(h)){
						playlist.Pl.remove(i);
						yes=1;
						System.out.println(playlist.Pl.size());
					}
				}
				if(yes==0){
					System.out.println("No song with the given name exist");
				}

		}
			else if(option==3)
			{
				String h=Reader.reader.readLine();
				int yes=0;
				for(int i=0;i<playlist.Pl.size();i++)
				{
					if(playlist.Pl.get(i).NoS.equals(h)){						
						yes=1;
						playlist.Pl.get(i).printing();
						this.gb.add(playlist.Pl.get(i));
					}
				}
				if(yes==0){
					System.out.println("No song with the given name exist");
					this.gb.add(null);

				}
			}
			else if(option==4){
				for(int i=0;i<playlist.Pl.size();i++)
				{
					playlist.Pl.get(i).printing();
				}
			}
			else if(option==5){
				exitting=1;
			}
			else if(option==6){			
				this.exit=1;
				return;
			}
	}

		
	}	
	public static Playlist deserialize(String s) throws IOException, ClassNotFoundException {
	 ObjectInputStream in = null;
	 Playlist s1=new Playlist(s);
	 try {
	 DataInputStream inn = new DataInputStream(new BufferedInputStream(new FileInputStream(s)));
	 if(inn.available()>0){
	 in = new ObjectInputStream (new FileInputStream(s));
	 s1= (Playlist)in.readObject();
	 s1.Name=s;
	 }
	 } finally {	
		 if(in!=null){
	 in.close();}
	 }
	return s1; 
	 }
	public void perform(ArrayList<String> s) throws FileNotFoundException, IOException, ClassNotFoundException{
		Reader.init(System.in);
		ObjectOutputStream outobj = null;	
		
		for(int y=0;y<s.size();y++){
		this.Pl.add(this.deserialize(s.get(y)));		
		}
		
		while(this.exit!=1)
		{
			int nof=0;
			try 			
			{	
				System.out.println("Available PlayList");
				for(int i=0;i<this.Pl.size();i++) 
				{				
					System.out.println(this.Pl.get(i).Name);
				}		
				System.out.println("Enter the name of the Music Playlist");
				String plylstname=Reader.next();
				int no_of_file=0;
				for(int i=0;i<this.Pl.size();i++) 
				{
					if(this.Pl.get(i).Name.equals(plylstname))
					{
						break;
					}
					else
					{
						no_of_file=no_of_file+1;
					}
				}
				if(no_of_file<this.Pl.size())
				{
					nof=no_of_file;
					this.menu(this.Pl.get(no_of_file));			
				}

				}
			finally 
			{						
				
					outobj = new ObjectOutputStream (new FileOutputStream(s.get(nof)));
					outobj.writeObject(this.Pl.get(nof));
				
				
			
			}	
		}

	}
	public static void main(String[] args)throws IOException, ClassNotFoundException
	{
		MobileApp App=new MobileApp();
		ArrayList<String> c=new ArrayList<String>();
		c.add("1.txt");
		c.add("2.txt");
		c.add("3.txt");
		App.perform(c);
		


	
	
	}
	
}

