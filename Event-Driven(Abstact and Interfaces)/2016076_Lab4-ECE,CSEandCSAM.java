//https://github.com/Raj-Kamal/lab4
import java.util.Comparator;
import java.awt.geom.Area;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.ShutdownChannelGroupException;
import java.util.ArrayList;
import java.util.StringTokenizer;
/** Class for buffered reading int and double values */
class Reader {
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

class maxHeap implements Comparator<Animal>
{

    public Animal[] Heap;
    public int size;
    private int maxsize;
    int h_count;
    int c_count;
    int maxtimestrap;
 
    private static final int FRONT = 1;
 
    public maxHeap(int maxsize)
    {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new Animal[this.maxsize + 1];
        Grassland no=new Grassland(Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE);
        Grassland need=new Grassland(Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE);
        h_count=0;
        c_count=0;
        Heap[0] = new Herbivore(Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,-100,"fantom",no,need,Integer.MAX_VALUE);
        this.maxtimestrap=0;
    }
 
    private int parent(int pos)
    {
        return pos / 2;
    }
 
    private int leftChild(int pos)
    {
        return (2 * pos);
    }
 
    private int rightChild(int pos)
    {
        return (2 * pos) + 1;
    }
 
    private boolean isLeaf(int pos)
    {
        if (pos >=  (size / 2)  &&  pos <= size)
        {
            return true;
        }
        return false;
    }
 
    private void swap(int fpos,int spos)
    {
        Animal tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }
 
//    private void maxHeapify(int pos)
//    {
//    	//System.out.println(!isLeaf(pos));
//        if (!isLeaf(pos))
//        { 
//        	System.out.println("ddsasa");
//            if ( compare(Heap[pos],Heap[leftChild(pos)])==0  || compare(Heap[pos],Heap[rightChild(pos)])==0)
//            {
//                if (compare(Heap[leftChild(pos)],Heap[rightChild(pos)])==1)
//                {
//                    swap(pos, leftChild(pos));
//                    maxHeapify(leftChild(pos));
//                }else
//                {
//                    swap(pos, rightChild(pos));
//                    maxHeapify(rightChild(pos));
//                }
//            }
//        }}
 
    public void insert(Animal element)
    {
    	if(element.timestrap>this.maxtimestrap){
    		this.maxtimestrap=element.timestrap;
    	}
        Heap[++size] = element;
        int current = size;
 
        while(compare(Heap[current],Heap[parent(current)])==1)
        {
            swap(current,parent(current));
            current = parent(current);
        }	
    }
 
    public void print()
    {
        for (int i = 1; i <= size / 2; i++ )
        {
            System.out.println("    PARENT : " + Heap[i].name);
            System.out.println("LEFT CHILD : " + Heap[2*i].name);
            System.out.println("RIGHT CHILD :" + Heap[2 * i  + 1].name);
            System.out.println();
        }
    }
    public void print2(){
    for (int i = 1; i <=size; i++){    	
        System.out.print(this.Heap[i].name +" "+this.Heap[i].timestrap +" ");
        
    }
    System.out.println();
    }
    public void maxHeap()
    {
        for (int pos=(size/2);pos>=1;pos--)
        {
            maxHeapify(pos);
        }
    }
    public void maxHeapify(int pos){    	 
    	if (pos <=  (size / 2)){
        if ( compare(Heap[pos],Heap[leftChild(pos)])==0  || compare(Heap[pos],Heap[rightChild(pos)])==0)
        {
            if (compare(Heap[leftChild(pos)],Heap[rightChild(pos)])==1)
            {
                swap(pos, leftChild(pos));
                maxHeapify(leftChild(pos));
            }else
            {
                swap(pos, rightChild(pos));
                maxHeapify(rightChild(pos));
            }
        }
    }
    }
    public Animal remove()
    {	                 
        if(size>=1){
        	Animal popped = Heap[1];
        	Heap[1] = Heap[size--];
        	maxHeapify(1);
        	return popped;
        }
        else{
        	size=0;
        	return Heap[0];
        }
        
    }
 	@Override
	public int compare(Animal a, Animal b) {
		
		if(a.timestrap<b.timestrap){
			return 1;
			
		}
		else if(a.timestrap==b.timestrap){
			
			if(a.health>b.health){
				return 1;
			}
			else if(a.health==b.health){
				if(a.typ>b.typ){
					return 1;
				}
				else if(a.typ==b.typ){
					double temp1=Math.sqrt((a.x*a.x)+(a.y*a.y));
					double temp2=Math.sqrt((b.x*b.x)+(b.y*b.y));
					if(temp1>temp2){
						return 1;
					}
						
				}
			}
		}
		return 0;
	}  
   
}






class Grassland{
	int x;
	int y;
	int r;
	int grass_capacity;
	
	public Grassland(int a,int b,int c,int d){
		this.x=a;
		this.y=b;
		this.r=c;
		this.grass_capacity=d;
		
		
	}
}
abstract class Animal{
	int status,health,x,y,timestrap,typ;
	String name;
	Grassland fst;
	Grassland sec;
	int no_of_w_move;
	public Animal(int a,int b,int c,int d,int e,int f,String n,Grassland i,Grassland g){
		this.status=a;
		this.health=b;
		this.x=c;
		this.y=d;
		this.timestrap=e;
		this.typ=f;
		this.name=n;
		this.fst=i;
		this.sec=g;
		this.no_of_w_move=0;
		
	}
	public 	abstract void turn(int othsts,ArrayList<Animal> c);
	public double getdist(double x1,double x2,double y1,double y2){
		double temp3=x2-x1;
		double temp4=y2-y1;
		return Math.sqrt((temp3*temp3)+(temp4*temp4));
	}
	public double getRandomInt(double max) {		  
		  max = Math.floor(max);
		  return Math.floor(Math.random() * (max - 0)) +0; 
		}
	public void updat_health(int k){
		if(k==0){
			this.health=0;
		}
		else{
		this.health=(int) (this.health+this.health*(k/100));
		}
	}
	public void updat_timestrap(int maxtimestrap, int clock){
		int yu=getRandomIntspcl(maxtimestrap,clock);
		if(yu>=clock-1){
			this.health=0;
		}
		else{
			this.timestrap=yu;
			}
	}
	public int  getRandomIntspcl(int maxtimestrap, int clock) {		
		return (int) (Math.floor(Math.random() * (clock - maxtimestrap)) +maxtimestrap); 
	}	
}

//herbivore
class Herbivore extends Animal{
	//additional requirement of the herbivore
	
	int grass_need;
	
	public Herbivore(int a, int b, int c, int d, int e,String i,Grassland f,Grassland g,int h) {
		super(a, b, c, d, e,1,i,f,g);		
		this.grass_need=h;
	}
	public int is_close_grassland(){
		if(super.getdist(this.x,super.fst.x,this.y,super.fst.y)<super.fst.r){
			return 1;
		}
		else if(super.getdist(this.x,super.sec.x,this.y,super.sec.y)<super.sec.r){
			return 2;
		}
		else if(super.getdist(this.x,super.fst.x,this.y,super.fst.y)<super.getdist(this.x,super.sec.x,this.y,super.sec.y)){
			return 3;
		}
		else{
			return 4;
		}
	}
	@Override
	public void turn(int othsts, ArrayList<Animal> c) {
		int t1=is_close_grassland();//tells if h is in or out and near to whic gl
		if(othsts<1){
			//if there is no carnivore left
			double temp5=super.getRandomInt(100);			
			if(temp5<50){
				//automatically goes to the next or nearest gl accordingly since its certain to move
				go_to_th_nearest_grassfield(5);
			}
			else{
				//certain not to move and hence eat if in any gl else do nothing
				if(t1==1){
					eat_grass(1);
				}
				else if(t1==2){
					eat_grass(2);
				}
			}
		}
		else{
			//carnivore left 
			if(t1==3 || t1==4){
				///if the h is near one of the gl but not inside it
				double temp12=super.getRandomInt(100);
				if(temp12<5){
					// decided not to move
					
				}
				else{
					//decided to move
					double temp13=super.getRandomInt(100);
					if(temp13<65){
						//to nearest field
						this.go_to_th_nearest_grassfield(5);
					}
					else{
						//away from animal
						this.run_away(c,4);
					}
				}

			}
			else if(t1==1 || t1==2){
				//if h is in the gl
					if(this.grass_need>super.fst.grass_capacity)
					{
						//the one which he is in has lower grass
						double temp10=super.getRandomInt(100);
						if(temp10<20){
							//decide to stay and hence eat
							if(t1==1)this.eat_partial(1);
							else{this.eat_partial(2);}
						}
						else{
							//decide to move
							this.updat_health(-25);
							double temp11=super.getRandomInt(100);
							if(temp11<70){
								//run from the animal
								this.run_away(c,4);
							}
							else{
								//run to the next gl
								go_to_th_nearest_grassfield(2);
							}					
						}
					}
					else{
						//the one which he is in is sufficient
						double temp8=super.getRandomInt(100);
						if(temp8<90){
							//stay and eat
							//chose the one he is staying to eat from
							
							if(t1==1)this.eat_full(1);
							else{this.eat_full(2);;}
						}
						else{
							this.updat_health(-25);
							//decide to move
							double temp9=super.getRandomInt(100);
							if(temp9<50){
								//run  from carnivore
								this.run_away(c,2);
							}
							else{
								//run to next field
								go_to_th_nearest_grassfield(3);
							}

						}
				}
				
				
				}				
			}
		if(super.getdist(this.x,super.fst.x,this.y,super.fst.y)<super.fst.r || super.getdist(this.x,super.sec.x,this.y,super.sec.y)<super.sec.r){
			this.no_of_w_move=0;
		}
		else{
			this.no_of_w_move=this.no_of_w_move+1;
		}
		}
	public void eat_full(int k){
		Grassland t;
		if(k==1){t=super.fst;}
		else{t=super.sec;}
		t.grass_capacity=t.grass_capacity-this.grass_need;
		this.updat_health(50);
	}
	public void eat_partial(int k){
		Grassland t;
		if(k==1){t=super.fst;}
		else{t=super.sec;}
		t.grass_capacity=0;
		this.updat_health(20);
	}
	public void eat_grass(int k) {	
		if(this.grass_need>super.fst.grass_capacity)
		{
			eat_partial(k);
		}
		else{
			eat_full(k);
		}
		}
	public void go_to_th_nearest_grassfield(int k) {
		if(is_close_grassland()==1 || is_close_grassland()==4){
			if(super.getdist(this.x,super.sec.x,this.y,super.sec.y)<=k){
				this.x=super.sec.x;
				this.y=super.sec.y;
			}
			else{
				int d=(int) super.getdist(this.x,super.sec.x,this.y,super.sec.y);
				int e=k/d;
				this.x=((1-e)*this.x)+(e*super.sec.x);
				this.y=((1-e)*this.y)+(e*super.sec.y);
			}
		}
		else if(is_close_grassland()==2 || is_close_grassland()==3){
			if(super.getdist(this.x,super.fst.x,this.y,super.fst.y)<=k){
				this.x=super.fst.x;
				this.y=super.fst.y;
			}
			else{
				int d=(int) super.getdist(this.x,super.fst.x,this.y,super.fst.y);
				int e=k/d;
				this.x=((1-e)*this.x)+(e*super.fst.x);
				this.y=((1-e)*this.y)+(e*super.fst.y);
			}		
		}
	}
	public void run_away(ArrayList<Animal> whole,int unit){	
		ArrayList<Animal> tryy=new ArrayList<Animal>();
		for(int i=0;i<whole.size();i++){
			if(whole.get(i).typ==0){
				tryy.add(whole.get(i));
				 
			}
		}
		if(tryy.size()==1){
			int y=(int) (unit/(unit+super.getdist(this.x,tryy.get(0).x,this.y,tryy.get(0).y)));
			this.x=(this.x-(y*tryy.get(0).x))/(1-y);
			this.y=(this.y-(y*tryy.get(0).y))/(1-y);
		}
		else{
			if(super.getdist(this.x,tryy.get(0).x,this.y,tryy.get(0).y)<super.getdist(this.x,tryy.get(1).x,this.y,tryy.get(1).y)){
				int y=(int) (unit/(unit+super.getdist(this.x,tryy.get(0).x,this.y,tryy.get(0).y)));
				this.x=(this.x-(y*tryy.get(0).x))/(1-y);
				this.y=(this.y-(y*tryy.get(0).y))/(1-y);
			}
			else{
				int y=(int) (unit/(unit+super.getdist(this.x,tryy.get(1).x,this.y,tryy.get(1).y)));
				this.x=(this.x-(y*tryy.get(1).x))/(1-y);
				this.y=(this.y-(y*tryy.get(1).y))/(1-y);
			}
		}
	}
}
//carnivore
class Carnivore extends Animal{
	//additional requirement of the carnivore	
	int no_of_wated_round;
	public Carnivore(int a, int b, int c, int d, int e,String h,Grassland f,Grassland g) {
		super(a, b, c, d, e,0,h,f,g);
		this.no_of_wated_round=0;
	}
	public int is_close_grassland(){
		if(super.getdist(this.x,super.fst.x,this.y,super.fst.y)<super.fst.r){
			return 1;
		}
		else if(super.getdist(this.x,super.sec.x,this.y,super.sec.y)<super.sec.r){
			return 2;
		}
		else if(super.getdist(this.x,super.fst.x,this.y,super.fst.y)<super.getdist(this.x,super.sec.x,this.y,super.sec.y)){
			return 3;
		}
		else{
			return 4;
		}
	}
	public void run_to(ArrayList<Animal> whole,int unit){	
		ArrayList<Animal> tryy=new ArrayList<Animal>();
		for(int i=0;i<whole.size();i++){
			if(whole.get(i).typ==0){
				tryy.add(whole.get(i));				 
			}
		}
		if(tryy.size()==1){
			if(super.getdist(this.x,tryy.get(0).x,this.y,tryy.get(0).y)<1){
				eat_herb(tryy.get(0));
			}
			else{
			int d=(int) super.getdist(this.x,tryy.get(0).x,this.y,tryy.get(0).y);
			int e=unit/d;
			this.x=((1-e)*this.x)+(e*tryy.get(0).x);
			this.y=((1-e)*this.y)+(e*tryy.get(0).y);}
		}
		else{
			if(super.getdist(this.x,tryy.get(0).x,this.y,tryy.get(0).y)<super.getdist(this.x,tryy.get(1).x,this.y,tryy.get(1).y)){
				if(super.getdist(this.x,tryy.get(0).x,this.y,tryy.get(0).y)<1){
					eat_herb(tryy.get(0));
				}
				else{
				int d=(int) super.getdist(this.x,tryy.get(0).x,this.y,tryy.get(0).y);
				int e=unit/d;
				this.x=((1-e)*this.x)+(e*tryy.get(0).x);
				this.y=((1-e)*this.y)+(e*tryy.get(0).y);}
			}
			else{
				if(super.getdist(this.x,tryy.get(1).x,this.y,tryy.get(1).y)<1){
					eat_herb(tryy.get(1));
				}
				else{
				int d=(int) super.getdist(this.x,tryy.get(1).x,this.y,tryy.get(1).y);
				int e=unit/d;
				this.x=((1-e)*this.x)+(e*tryy.get(1).x);
				this.y=((1-e)*this.y)+(e*tryy.get(1).y);}
			}
		}
	}
	public int givupdt(ArrayList<Animal> whole){
		int t=0;
	
	ArrayList<Animal> tryy=new ArrayList<Animal>();
	for(int i=0;i<whole.size();i++){
		if(whole.get(i).typ==0){
			tryy.add(whole.get(i));				 
		}
	}
	if(tryy.size()==1){
		if(super.getdist(this.x,tryy.get(0).x,this.y,tryy.get(0).y)<7){
			t=0;
		}
		else{t=1;}
	}
	else{
		if(super.getdist(this.x,tryy.get(0).x,this.y,tryy.get(0).y)<super.getdist(this.x,tryy.get(1).x,this.y,tryy.get(1).y)){
			if(super.getdist(this.x,tryy.get(0).x,this.y,tryy.get(0).y)<7){
				t=0;
			}
			else{t=1;}
		}
		else{
			if(super.getdist(this.x,tryy.get(1).x,this.y,tryy.get(1).y)<7){
				t=0;
			}
			else{t=1;}
		}
	}
	return t;
}
	
	public void eat_herb(Animal animal) {
		this.health=this.health+((2/3)*animal.health);
		animal.health=0;
		
	}
	@Override
	public void turn(int othsts, ArrayList<Animal> c) {
		int t2=is_close_grassland();  
		if(othsts<1){
			if(t2==1 || t2==2){
				this.updat_health(-30);
			}
			else{
				this.updat_health(-60);
			}
		}
		
		else{
			if(t2==1 || t2==2){
				double tr1=super.getRandomInt(100);
				if(tr1<25){
					this.updat_health(-30);
				}
				else{
					run_to(c,2);
				}
			}
			else{
				double tr2=super.getRandomInt(100);
				if(t2<92){
					run_to(c,4);
				}
				else{
					this.updat_health(-60); 
				}
			}
			//if hb are atleast 1
			
		}
		if(this.givupdt(c)==1){
			this.no_of_w_move=this.no_of_w_move+1;
		}
		else if(this.givupdt(c)==0){
			this.no_of_w_move=0;   
		}

	}

}

class Simulation{
	int clock;
	Grassland one;
	Grassland two;
	maxHeap wildlife;
	ArrayList<Animal> temp;	
	public Simulation(int a,Grassland[] b,Animal[] c,int mzx){
		this.clock=a; 
		wildlife=new maxHeap(5);
		temp=new ArrayList<Animal>();
		for(int i=0;i<c.length;i++){
			if(c[i].status==1){
				if(c[i].typ==1){
					wildlife.h_count=wildlife.h_count+1;
				} 
				else if(c[i].typ==0){
					wildlife.c_count=wildlife.c_count+1;
				}
			temp.add(c[i]);	
			wildlife.insert(c[i]);
			}
		}
		wildlife.maxtimestrap=mzx;
		this.one=b[0];
		this.two=b[1];		
	}
	public void initiatemotion(){
		while(this.clock>0 && wildlife.size!=0){
		//	wildlife.print2();	
			if(wildlife.size==1){
				boolean v=false;
				while(v!=true){
					Animal sd=wildlife.remove();
					
					sd.turn(wildlife.c_count,temp);                  
					sd.updat_timestrap(wildlife.maxtimestrap,this.clock);
					if(sd.health>0){
                    
                    if(sd.name.equals("FC")){
                        System.out.println("It is First Carnivore.");
                    }
                    else if(sd.name.equals("SC")){
                        System.out.println("It is Second Carnivore.");
                    }
                    else if(sd.name.equals("FH")){
                        System.out.println("It is First Herbivore.");
                    }
                    else if(sd.name.equals("SH")){
                        System.out.println("It is Second Herbivore.");
                    }
                    System.out.println("It’s health after taking turn is "+ sd.health+".");
                }
                
                else if(sd.health==0){
                    
                    if(sd.name.equals("FC")){
                        System.out.println("It is First Carnivore.");
                    }
                    else if(sd.name.equals("SC")){
                        System.out.println("It is Second Carnivore.");
                    }
                    else if(sd.name.equals("FH")){
                        System.out.println("It is First Herbivore.");
                    }
                    else if(sd.name.equals("SH")){
                        System.out.println("It is Second Herbivore.");
                    }
                    System.out.println("It is dead.");
                    v=true;
                    return;
                }}}		
			ArrayList<Animal> tl=new ArrayList<Animal>();
			boolean mc=false;
			int compar=wildlife.Heap[1].timestrap;
			tl.add(wildlife.remove());
	
			
			
			while(mc!=true && wildlife.size!=0){
				Animal check=wildlife.remove();				
				if(check.timestrap==compar){
					
					tl.add(check);										
				}
				else{
					wildlife.insert(check);					
					mc=true;
				}			
			}	
			
			while(tl.size()!=0){
				
				make_a_move(tl.get(0));
				tl.remove(tl.get(0));
				
			}
			}
		  }
		
	public void make_a_move(Animal ofintrest){ 
		if(ofintrest.typ==1){
		ofintrest.turn(wildlife.c_count,temp);
		ofintrest.updat_timestrap(wildlife.maxtimestrap,this.clock);
		updat_the_availabestock(ofintrest);

		}
		else if(ofintrest.typ==0){
			ofintrest.turn(wildlife.h_count,temp);
			ofintrest.updat_timestrap(wildlife.maxtimestrap,this.clock);

			updat_the_availabestock(ofintrest);}
		
		this.clock--;
	}
	public void updat_the_availabestock(Animal e){
		while(wildlife.size!=0){			
			wildlife.remove();			
			}
		ArrayList<Animal> temp2=new ArrayList<Animal>();	
		
		 
		if(e.health>0){
			temp2.add(e);
			if(e.name.equals("FC")){
				System.out.println("It is First Carnivore.");
			}
			else if(e.name.equals("SC")){
				System.out.println("It is Second Carnivore.");
			}
			else if(e.name.equals("FH")){
				System.out.println("It is First Herbivore.");
			}
			else if(e.name.equals("SH")){
				System.out.println("It is Second Herbivore.");
			}
			System.out.println("It’s health after taking turn is "+ e.health+".");
			System.out.println();
		}
		
		else if(e.health==0){
			temp.remove(e);
			if(e.name.equals("FC")){
				System.out.println("It is First Carnivore.");
			}
			else if(e.name.equals("SC")){
				System.out.println("It is Second Carnivore.");
			}
			else if(e.name.equals("FH")){
				System.out.println("It is First Herbivore.");
			}
			else if(e.name.equals("SH")){
				System.out.println("It is Second Herbivore.");
			}
			System.out.println("It is dead.");
			System.out.println();
		}
		for(int v=0;v<temp.size();v++){
			
			if(temp2.contains(temp.get(v))==false){
				temp2.add(temp.get(v));
			}
			
		}
		while(temp2.size()!=0){
			Animal tempor2=temp2.get(0);
			wildlife.h_count=0;wildlife.c_count=0;
			if(tempor2.typ==1){wildlife.h_count=wildlife.h_count+1;}
			else if(tempor2.typ==0){wildlife.c_count=wildlife.c_count+1;}
			if(tempor2.health>0){
			wildlife.insert(tempor2);}
			temp2.remove(tempor2);
		}		
		
		
	}
		
	


}



public class world {
	
		public void open() throws IOException{	
		int findmax=0;
		System.out.println("Enter Total Final Time for Simulation:");
		int temp14=Reader.nextInt();
		System.out.println("Enter x, y centre, radius and Grass Available for First Grassland:");
		int temp15=Reader.nextInt();
		int temp16=Reader.nextInt();
		int temp17=Reader.nextInt();
		int temp18=Reader.nextInt();
		Grassland grass1=new Grassland(temp15, temp16,temp17,temp18);
		System.out.println("Enter x, y centre, radius and Grass Available for Second Grassland:");
		int temp19=Reader.nextInt();
		int temp20=Reader.nextInt();
		int temp21=Reader.nextInt();
		int temp22=Reader.nextInt();
		Grassland grass2=new Grassland(temp19,temp20,temp21,temp22);
		Grassland[] input1={grass1,grass2};
		Animal giraffe;
		Animal bison;
		Animal tiger;
		Animal wolf;
		System.out.println("Enter Health and Grass Capacity for Herbivores:");
		int temp23=Reader.nextInt();
		int temp24=Reader.nextInt();
		
		System.out.println("Enter x, y position and timestamp for First Herbivore:");
		int temp25=Reader.nextInt();
		int temp26=Reader.nextInt();
		int temp27=Reader.nextInt();
		
		if(temp27>findmax){findmax=temp27;}
		
		int status1;
		if(temp23>0){
			status1=1;
		}
		else{
			status1=0;
		}
		giraffe=new Herbivore(status1,temp23,temp25,temp26,temp27,"FH",grass1,grass2,temp24);
		
		System.out.println("Enter x, y position and timestamp for Second Herbivore:");
		int temp28=Reader.nextInt();
		int temp29=Reader.nextInt();
		int temp37=Reader.nextInt();
		
		if(temp37>findmax){findmax=temp37;}
		
		bison=new Herbivore(status1,temp23,temp28,temp29,temp37,"SH",grass1,grass2,temp24);
		
		System.out.println("Enter Health for Carnivores:");
		int temp30=Reader.nextInt();
		int status2; 
		if(temp30>0){
			status2=1;
		}
		else{
			status2=0;
		}
		System.out.println("Enter x,y position and timestamp for First Carnivore:");
		int temp31=Reader.nextInt();
		int temp32=Reader.nextInt();
		int temp33=Reader.nextInt();
		
		if(temp33>findmax){findmax=temp33;}

		tiger=new Carnivore(status2,temp30,temp31,temp32,temp33,"FC",grass1,grass2);
		System.out.println("Enter x, y position and timestamp for Second Carnivore:");
		int temp34=Reader.nextInt();
		int temp35=Reader.nextInt();
		int temp36=Reader.nextInt();
		
		if(temp36>findmax){findmax=temp36;}
		
		wolf=new Carnivore(status2,temp30,temp34,temp35,temp36,"SC",grass1,grass2);
		Animal[] input2={giraffe,bison,tiger,wolf};
		
		
		System.out.println("The Simulation Begins - ");
		Simulation lifeline=new Simulation(temp14,input1,input2,findmax);
		lifeline.initiatemotion();
		}
		
		public static void main(String[] args) throws IOException{
			Reader.init(System.in);
			world forest=new world();
			forest.open();
		}

}
