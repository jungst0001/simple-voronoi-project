import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Voronoi extends JFrame{
	private int imageNum;
	private BufferedImage I;
	
	protected final int SIZE;
	protected int cellNum;
	
	protected ArrayList<Site> sitelist;
	protected ArrayList<Site> centerlist;
	protected Point[] midPointList;
	protected int[] midPointNum;
	
	public Voronoi(ArrayList<Site> sitelist, int size){
		this(sitelist, size, 0);
	}
	
	public Voronoi(ArrayList<Site> sitelist, int size, int imageNum){
		SIZE = size;		
		this.imageNum = imageNum;
		centerlist = new ArrayList<>();
		I = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_RGB);
		
		
		setBounds(0, 0, SIZE, SIZE);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.sitelist = sitelist;
		cellNum = sitelist.size();
		midPointList = new Point[cellNum];
		midPointNum = new int[cellNum];
		
		for(int i = 0; i < cellNum; i++) {
			midPointList[i] = new Point(0, 0);
			midPointNum[i] = 0;
		}
	}
	
	public void drawVoronoi(){
		for(int x = 0; x < SIZE; x++){
			for(int y = 0; y < SIZE; y++){
				Site sn = sitelist.get(0);
				for(int i = 1; i < cellNum; i++){
					Site si = sitelist.get(i);
					Point p = new Point(x, y);
					if(distance(si, p) < distance(sn, p)){
						sn = si;
					}
					
				}
				I.setRGB(x, y, sn.getColor());

				int n = sitelist.indexOf(sn);
				Point p = midPointList[n];
				p.setLocation(p.getX() + x, p.getY() + y);
				midPointNum[n]++;
			}
		}
		
		Graphics2D g = I.createGraphics();
		g.setColor(Color.BLACK);
		for(int i = 0; i < cellNum; i++){
			Site s = sitelist.get(i);
			g.fill(new Ellipse2D.Double(s.getX() - 2.5, s.getY() - 2.5, 5, 5));
		}
		
//		computeCenterSite();
		
		try{
			String fileName = "voronoi" + imageNum + ".png";
			ImageIO.write(I, "png", new File(fileName));
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	private double distance(Site s, Point pixel){
		double sx = s.getX();
		double sy = s.getY();
		double sw = s.getWeight();
		
		double px = pixel.getX();
		double py = pixel.getY();
		
		double dist = Math.sqrt((sx - px) * (sx - px) + (sy - py) * (sy - py)) / sw;
		
		return dist;
	}
	
	public void paint(Graphics g){
		g.drawImage(I, 0, 0, this);
//		g.drawImage(I, 0, 0, I.getWidth() - 10, I.getHeight() - 10, this);
//		System.out.println(I.getWidth());
	}
	
	public void computeCenterSite(){
		for(int i = 0; i < cellNum; i++){
			Point p = midPointList[i];
//			System.out.println(i + ", " + p.getX());
			p.setLocation(p.getX() / midPointNum[i], p.getY() / midPointNum[i]);
//			System.out.println(i + ", " + p);
			Site s = sitelist.get(i);
//			s.setLocation((int) p.getX(), (int) p.getY());
			Site c = new Site((int) p.x, (int) p.y, s.getWeight(), s.getColor());
			centerlist.add(c);
		}
	}
	
	public ArrayList<Site> getCenterList(){	return centerlist; }
	public ArrayList<Site> getSiteList() { return sitelist; }
	
	public double getError(){
		double error = 0.0;
		
		double meanRegion = SIZE * SIZE / cellNum;
		
		for(int i = 0; i < cellNum; i ++){
			error += (midPointNum[i] - meanRegion) * (midPointNum[i] - meanRegion);
		}
		
		return error;
	}
}
