//Raj Kamal Yadav 2016076 
import static org.junit.Assert.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.junit.Test;

public class testadd {

	@Test
	public void test() throws ClassNotFoundException, IOException {
	Playlist c=new Playlist("temp.txt");
	Song s1=new Song("b","d","3");
	Song s2=new Song("a","s","2");
	Song s3=new Song("c","v","7");	
	c.Pl.add(s1);
	c.Pl.add(s2);
	c.Pl.add(s3);
	ObjectOutputStream outobj = null;	
	outobj = new ObjectOutputStream (new FileOutputStream("temp.txt"));
	outobj.writeObject(c);
	MobileApp e=new MobileApp();
	ArrayList<String> cc=new ArrayList<String>();
	cc.add("temp.txt");
	e.perform(cc);

	assertEquals("Redd",e.Pl.get(0).Pl.get(3).NoS);
	assertEquals("TS",e.Pl.get(0).Pl.get(3).NoSi);
	assertEquals("3",e.Pl.get(0).Pl.get(3).D);
	
	assertEquals("Loveyou",e.Pl.get(0).Pl.get(4).NoS);
	assertEquals("SG",e.Pl.get(0).Pl.get(4).NoSi);
	assertEquals("3",e.Pl.get(0).Pl.get(4).D);
	
	assertEquals("Love",e.Pl.get(0).Pl.get(5).NoS);
	assertEquals("E",e.Pl.get(0).Pl.get(5).NoSi);
	assertEquals("3",e.Pl.get(0).Pl.get(5).D);
	assertEquals(6,e.Pl.get(0).Pl.size());

	


	
	
	

	

	
	}

}
