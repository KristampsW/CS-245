import java.util.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;

public class N_body extends JPanel implements ActionListener{
	private String name;	
	private double mass;	
	private int rx, ry;	
	private double vx, vy;	
	private int size;		
	private Color color;
	private double fx, fy; 
	private static final double G = 6.673e-11; 


	public N_body(String name, String mass, String rx, String ry, String vx, String vy, String size){
		this.name=name;
		this.mass=Double.parseDouble(mass);	
		this.rx=Integer.parseInt(rx);
		this.ry=Integer.parseInt(ry);
		this.vx=Double.parseDouble(vx);
		this.vy=Double.parseDouble(vy);
		this.size=Integer.parseInt(size.substring(1));
		Random rand = new Random();
		color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
	}
	private List<N_body> list;
	private double scale;
	public N_body(List<N_body> newList, double newScale){
		list = newList;
		scale = newScale;
	}
	
	public void force(N_body b, double scale){
		N_body a = this;
		double dx = b.rx - a.rx;
		double dy = b.ry - a.ry;
		
		double distance = Math.sqrt((dx * dx) + (dy * dy));
	
		double force = (G*a.mass*b.mass)/((distance * distance)/scale);
	
		a.fx += force*dx/distance;
		a.fy += force*dy/distance;
	}
	
	public void resetForce(){
		fx = 0.0;
		fy = 0.0;
	}
	
	public void updatePosition(){
		
		vx += fx/mass;
		vy += fy/mass;
	
		rx += vx;
		ry += vy;
	}
	
	public double getMass(){
		return mass;
	}
	public int getXcoordinate(){
		return rx;
	}
	public int getYcoordinate(){
		return ry;
	}
	public double getXvelocity(){
		return vx;
	}
	public double getYvelocity(){
		return vy;
	}
	public int get_size(){
		return size;
	} 
	public Color getColor(){
		return color;
	}

	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		Timer tm = new Timer(200, this); 
		
		for (int i=0; i<list.getSize(); i++){
			
			g.setColor(list.get(i).getColor());

			g.fillOval(list.get(i).getXcoordinate(), list.get(i).getYcoordinate(), list.get(i).get_size(), list.get(i).get_size());
		}
		tm.start();
	}

	public void updateShapes(){
		int i;
		for (i=0; i<list.getSize()-1; i++){
			list.get(i).force(list.get(i+1), scale);
			list.get(i).updatePosition();
			list.get(i).resetForce();
		}
		if(list.getSize()>1){
			list.get(i).force(list.get(i-1), scale);
			list.get(i).updatePosition();
			list.get(i).resetForce();
		}
	}
	public void actionPerformed(ActionEvent a){
		updateShapes();

		repaint();
	}
 
	public static void main(String[] args){
		
		List<N_body> temp = null;
		double tempS = 0;
		File file = new File(args[0]);
		try{
			Scanner sc = new Scanner(file);
			String listType = sc.nextLine();
		
			if(listType.equals("ArrayList")){
			
				temp = new ArrayList<>();
			}
			
			else if(listType.equals("LinkedList")){
				
				temp = new LinkedList<>();
			}
		
			else{
				System.out.println("Invalid type of list");
			}
			tempS = Double.parseDouble(sc.nextLine());
			sc.useDelimiter(",");
			while(sc.hasNext()){
				assert temp!=null;
				temp.add(new N_body(sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.nextLine()));//add each value of each body
			}
			sc.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		
		N_body nBody = new N_body(temp, tempS);
		JFrame jf = new JFrame();
		jf.setTitle( "N_body");
	
		jf.setSize( 768 , 768 );
		jf.add(nBody);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}