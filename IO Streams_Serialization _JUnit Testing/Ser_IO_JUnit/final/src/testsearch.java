import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.junit.Test;

public class testsearch {

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
		 3
		 b
		 3
		 a
		 3
		 c
		 3
		 e
		 6
		 */
		//temp.txt-->3-->b-->3-->a-->3-->c-->3-->e-->6
		assertEquals(s1.NoS,e.gb.get(e.gb.size()-4).NoS);
		assertEquals(s2.NoS,e.gb.get(e.gb.size()-3).NoS);
		assertEquals(s3.NoS,e.gb.get(e.gb.size()-2).NoS);
		assertEquals(null,e.gb.get(e.gb.size()-1));
		}

}
