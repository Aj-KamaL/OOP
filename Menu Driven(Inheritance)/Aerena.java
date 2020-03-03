//Raj Kamal Yadav 2016076

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
class TEAMS{
	private double money;
	private boolean can_i_buy;
	private double[] arr;
	private ArrayList<Creature> team;
	public TEAMS(double k, double[] ii){
		this.money=k;
		this.arr=ii;
		//if any one tries to start a game without the least amount required
		for(int i=0;i<ii.length;i++){
			if(this.money>=ii[i]){
				this.can_i_buy=true;
				break;}
			else{
				this.can_i_buy=false;
			}
			
		}		
		team=new ArrayList<Creature>();
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double w) {
		money=w;
	}
	public boolean isCan_i_buy() {
		return can_i_buy;
	}
	public double[] getArr() {
		return arr;
	}
	public ArrayList<Creature> getTeam() {
		return team;
	}
	//to check weather the team is able to buy the cheapest warrior or not
	public void update(){
		for(int i=0;i<this.arr.length;i++){
			if(this.money>=this.arr[i]){
				this.can_i_buy=true;
				break;}
			else{
				this.can_i_buy=false;
			}
			
		}
	}
	public void add(Creature C){		
		if(this.can_i_buy==true && this.money-C.cost>=0){
		this.team.add(C);
		this.money=this.money-C.cost;
		}
		update();
		}

	}
class Creature{
	protected String Name;
	protected double Power;
	protected double Health;
	protected double cost;
	protected double asset;	
	protected int status;
	public Creature(String n,double p,double h,double c,double a){
		this.cost=p;
		this.asset=h;
		this.Power=c;
		this.Health=a;
		this.Name=n;
		this.status=0;	
	}
	protected double  getRandomInt(double max) {		  
		  max = Math.floor(max);
		  return Math.floor(Math.random() * (max - 0)) +0; 
		}
	protected double attack(Creature c){
		double punch =getRandomInt(c.Power);
		c.Health=c.Health-punch;
		return punch;
	}
}
class Humans extends Creature{
	public Humans(String n, double p, double h, double c, double a) {
		super(n, p, h, c, a);	
	}
}
class  Dragons extends Creature{
	public Dragons(String n, double p, double h, double c, double a) {
		super(n, p, h, c, a);
	}
	//update the dragon's attack
	@Override
	protected double attack(Creature c){
		double a=super.attack(c);
		double flag=super.getRandomInt(100);
		if(flag<=15){
			c.Health=c.Health-25;
			
		}
		return a;
	}
	
}
class Daemons extends Creature{
	public Daemons(String n, double p, double h, double c, double a) {
		super(n, p, h, c, a);
	}
	//update the Daemon's attack
	@Override
	protected double attack(Creature c){
		double a=super.attack(c);
		double flag=super.getRandomInt(100);
		if(flag<=50){
			c.Health=c.Health-a;
		}
		return a;
	}
}
class Wolves extends Creature{

	public Wolves(String n, double p, double h, double c, double a) {
		super(n, p, h, c, a);	
	}
	
}
class Ice_Dragon extends Dragons{

	public Ice_Dragon(String n, double p, double h, double c, double a) {
		super(n, p, h, c, a);
		
	}
	//update the ice dragon attack
	@Override
	protected double attack(Creature c){
		double a=super.attack(c);

		double flag2=super.getRandomInt(100);
		
		
		if(flag2<=5){
			double punch =getRandomInt(c.Power);
			c.Health=c.Health-punch;
		}		
		return a;
	}
}

public class Aerena{
	private boolean warlog;
	private TEAMS A;
	private TEAMS B;
	public boolean isWarlog() {
		return warlog;
	}
	public TEAMS getA() {
		return A;
	}
	public TEAMS getB() {
		return B;
	}
	public Aerena(TEAMS a,TEAMS b){
		this.A=a;
		this.B=b;
	}
	//all 5X4 attributes of the creatures as parameters
	//it prepares before the war prelimeary check if the two teams have members or at least money to buy else declares the other winner
	public void warprep(double c, double d, double e, double f, double g, double h, double i, double j, double k, double l, double m, double n, double o, double p, double q, double r, double s, double t, double u, double v) throws IOException{
		int round =1;
		System.out.println(" The War Begins -");
		while(A.getTeam().size()!=0 || B.getTeam().size()!=0){	
				warday(round);				
				if(declare(c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)==0){
					return;
				}
				else{
				show_selection_menu(c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t,u, v);
				round=round+1;
				declare(c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t,u, v);
		}
		
		}
	}
	//declares the winner in the case if both the team went out but have(if returns 1) or have not(else 0) money left to buy
	public int  declare(double c, double d, double e, double f, double g, double h, double i, double j, double k, double l, double m, double n, double o, double p, double q, double r, double s, double t, double u, double v) throws IOException{
		
		if(A.getTeam().size()==0 && B.getTeam().size()!=0){
			if(A.isCan_i_buy()==true){
				show_selection_menu(c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t,u, v);
			}
			else{
			System.out.println("Team Bad wins the war. The money the team has is "+B.getMoney());
			return 0;
			}			
		}
		else if((B.getTeam().size()==0) && A.getTeam().size()!=0){
			if(B.isCan_i_buy()==true){
				show_selection_menu(c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t,u, v);
			}
			else{
			System.out.println("Team Good wins the war. The money the team has is "+A.getMoney());
			return 0;}
		}
		else if((B.getTeam().size()==0) && A.getTeam().size()==0){
			if(B.isCan_i_buy()==true && A.isCan_i_buy()==true){
				show_selection_menu(c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t,u, v);
			}
			else if(B.isCan_i_buy()==true && A.isCan_i_buy()!=true){
				System.out.println("Team Bad wins the war. The money the team has is "+B.getMoney());
				return 0;
			}
			else if(A.isCan_i_buy()==true && B.isCan_i_buy()!=true){
				System.out.println("Team Good wins the war. The money the team has is "+A.getMoney());
				return 0;
			}
		}
		return -1;
	}
	// prepares the two fighter from each team or pick the survivor from the previous attack to combat with the new of the other team
	public void warday(int round) throws IOException{
	
	System.out.println("Round -  "+round);
	String fighter1="";
	String fighter2="";
	for(int i=0;i<A.getTeam().size();i++){
		if(A.getTeam().get(i).status==1){
			fighter1=A.getTeam().get(i).Name;
			System.out.println(fighter1);}
		
	}
	if(fighter1==""){
	System.out.println("Enter Creature from Good’s Team to fight in the war -");
	fighter1=Reader.next();
	}
	
	for(int i=0;i<B.getTeam().size();i++){
		if(B.getTeam().get(i).status==1){
			fighter2=B.getTeam().get(i).Name;}
		
	}
	if(fighter2==""){
	System.out.println("Enter Creature from Bad’s Team to fight in the war -");
	fighter2=Reader.next();
	}	
	outbreak(fighter1,fighter2,round);}
	

	//gives the real time war analysis of how attack is done, health is affected ,a fighter wins over the other one and winning team loots the looser's asset 
	//also do deep work over what happens id both the fighter in any round knock off each other in which we need two new fighter from each
	private void outbreak(String fighter1, String fighter2,int y) {
		int i,j;
		for(i=0;i<A.getTeam().size();i++){
			if(A.getTeam().get(i).Name.equals(fighter1)){
				break;
			}
		}
		for(j=0;j<B.getTeam().size();j++){
			if(B.getTeam().get(j).Name.equals(fighter2)){
				break;
			}
		}
		while(A.getTeam().get(i).Health>0 && B.getTeam().get(j).Health>0){
			A.getTeam().get(i).attack(B.getTeam().get(j));
			B.getTeam().get(j).attack(A.getTeam().get(i));
			
		}
		if(A.getTeam().get(i).Health<=0 && B.getTeam().get(j).Health<=0 ){
			System.out.println( A.getTeam().get(i).Name+" Loses In Round - "+y);
			System.out.println( B.getTeam().get(j).Name+" Loses In Round - "+y);

			B.setMoney(B.getMoney()+A.getTeam().get(i).asset);
			A.setMoney(A.getMoney()+B.getTeam().get(j).asset);
			System.out.println("Money Of Good’s Team is "+A.getMoney());
			System.out.println("Money Of Bad’s Team is "+B.getMoney());
			A.getTeam().remove(i);
			B.getTeam().remove(j);
		}
		else{
		if(A.getTeam().get(i).Health<=0){
			System.out.println( A.getTeam().get(i).Name+" Loses In Round - "+y);
			B.setMoney(B.getMoney()+A.getTeam().get(i).asset);
			System.out.println("Money Of Good’s Team is "+A.getMoney());
			System.out.println("Money Of Bad’s Team is "+B.getMoney());
			A.getTeam().remove(i);
			B.getTeam().get(j).status=1;
		}
		if(B.getTeam().get(j).Health<=0){
			System.out.println( B.getTeam().get(j).Name+" Loses In Round - "+y);
			A.setMoney(A.getMoney()+B.getTeam().get(j).asset);
			System.out.println("Money Of Good’s Team is "+A.getMoney());
			System.out.println("Money Of Bad’s Team is "+B.getMoney());
			B.getTeam().remove(j);
			A.getTeam().get(i).status=1;
		}
		}
		//update the team status after the loot loss and capability to buy further
		A.update();
		B.update();
		
	}
	//provides real time way for the team to buy new fighters for themselves after each victory when minimum funds are arranged from the winning loot
	public void show_selection_menu(double c, double d, double e, double f, double g, double h, double i, double j, double k, double l, double m, double n, double o, double p, double q, double r, double s, double t, double u, double v) throws IOException{
		int temp1=0;
		while(this.A.isCan_i_buy()!=false && temp1!=4 ){
			System.out.println("Select Creatures For Team Good:");
			System.out.println("	1.	Human");
			System.out.println("	2. 	Fire Dragon");
			System.out.println("	3.	Wolf ");
			System.out.println("	4.	Exit Selection");

			temp1=Reader.nextInt();
			if(temp1==1){
				//System.out.println(temp1);
				System.out.println("Enter Name Of The Human");
				String temm1=Reader.next();
				Creature hu=new Humans(temm1,c,d,e,f);
				this.A.add(hu);
			}
			else if(temp1==2){
				//System.out.println(temp1);
				System.out.println("Enter Name Of The Fire Dragon");
				String temm1=Reader.next();
				Creature hu=new Dragons(temm1,g,h,i,j);
			
				this.A.add(hu);
			}
			else if(temp1==3){
				//System.out.println(temp1);
				System.out.println("Enter Name Of The Wolf");
				String temm1=Reader.next();
				Creature hu=new Dragons(temm1,s,t,u,v);
				
				this.A.add(hu);
			}
		}
		
		int temp2=0;
		while(this.B.isCan_i_buy()!=false && temp2!=3 ){
			System.out.println("Select Creatures For Team Bad:");
			System.out.println("	1.	Daemon");
			System.out.println("	2. 	Ice Dragon");
			System.out.println("	3.	Exit Selection");

			temp2=Reader.nextInt();
			if(temp2==1){
		
				System.out.println("Enter Name Of The Daemon");
				String temm2=Reader.next();
				Creature hu=new Daemons(temm2,o,p,q,r);
				this.B.add(hu);
			}
			else if(temp2==2){
				
				System.out.println("Enter Name Of Ice Dragon");
				String temm2=Reader.next();
				Creature hu=new Ice_Dragon(temm2,k,l,m,n);
				this.B.add(hu);
			}
			
		}
		
	}
	public static void main(String[] args) throws IOException{
		Reader.init(System.in);
		System.out.println("Enter Team Good’s total money");
		int a=Reader.nextInt();		
		System.out.println("Enter Team Bad’s total money");
		int b=Reader.nextInt();
		
		System.out.println("Enter cost, asset , power and health for Human -");
		double c =Reader.nextDouble();
		double d=Reader.nextDouble();
		double e=Reader.nextDouble();
		double f=Reader.nextDouble();
		
		
		System.out.println("Enter cost, asset , power and health for Fire Dragon -");
		double g =Reader.nextDouble();
		double h=Reader.nextDouble();
		double i=Reader.nextDouble();
		double j=Reader.nextDouble();
		Creature fd=new Dragons("",g,h,i,j);
		
		System.out.println("Enter cost, asset , power and health for Ice Dragon -");
		double k =Reader.nextDouble();
		double l=Reader.nextDouble();
		double m=Reader.nextDouble();
		double n=Reader.nextDouble();
		Creature id=new Ice_Dragon("",k,l,m,n);
		
		System.out.println("Enter cost, asset , power and health for Daemon -");
		double o =Reader.nextDouble();
		double p=Reader.nextDouble();
		double q=Reader.nextDouble();
		double r=Reader.nextDouble();
		Creature da=new Daemons("",o,p,q,r);
		
		System.out.println("Enter cost, asset , power and health for Wolf -");
		double s =Reader.nextDouble();
		double t=Reader.nextDouble();
		double u=Reader.nextDouble();
		double v=Reader.nextDouble();
		Creature wo=new Wolves("",s,t,u,v);
		double[] a1={c,g,s};
		double[] a2={k,o};

		System.out.println();
		TEAMS alpha=new TEAMS(a,a1);
		TEAMS beta=new TEAMS(b,a2);
		
		Aerena war =new Aerena(alpha,beta);		
		war.show_selection_menu(c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v);
		war.warprep(c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v);
		
		
		
		
		}
		
	}











