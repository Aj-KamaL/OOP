//Raj Kamal Yadav 2016076 
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.junit.Test;

public class testdelete {

	@Test
	public void test() throws FileNotFoundException, IOException, ClassNotFoundException {
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
		/*
		 temp.txt
		 2
		 c
		 2
		 b
		 6
		  
		  
		 
		 * 	 */
		assertEquals(1,e.Pl.get(0).Pl.size());
		assertEquals("a",e.Pl.get(0).Pl.get(0).NoS);
		assertEquals("s",e.Pl.get(0).Pl.get(0).NoSi);
		assertEquals("2",e.Pl.get(0).Pl.get(0).D);
		
		
	}

}
