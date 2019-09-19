import java.util.ArrayList;
import java.util.Random;

public class VoronoiMain {
	public static void main(String[] args){
		   Random rand = new Random();
		   int size = 500;
		   int cells = 5;
//		   int iterNum = 30;
		   ArrayList<Site> sitelist = new ArrayList<>();
		   
//		   StdDraw.setCanvasSize(size + 10, size + 10);
//		   StdDraw.setScale(-.1, 1.1);
		      
		   for (int i = 0; i < cells; i++) {
			   int px = rand.nextInt(size) + 125;
			   int py = rand.nextInt(size) + 125;
			   int color = rand.nextInt(16777215);
//			   int w = rand.nextInt(size);
			   int w = 1;
			   Site s = new Site(px, py, w, color);
			   sitelist.add(s);
			   //w[i] = 1;
//			   System.out.println(i+"   px: "+px+" "+"py: "+py+" "+"w: "+ w);
		   }
	         
	      //new Voronoi().setVisible(true);
	      
//		   Voronoi v = new Voronoi(sitelist, size + 250);
//		   v.drawVoronoi();
//	       v.setVisible(true);
	      
		   System.out.println("\n");
	      
//		   v.setVisible(true);
		   
//		   ModifiedLloyd lloyd = new ModifiedLloyd(sitelist, size + 250);
		   Lloyd lloyd = new Lloyd(sitelist, size);
		   lloyd.executeLloyd();
//		   for(int i = 0; i < 5; i++)
//			   lloyd.voronoiList.get(i).setVisible(true);;
//		   lloyd.getVoronoiList().get(9).setVisible(true);
		   
		   new VoronoiAnimation(size + 250, lloyd.getIterNum());
		   
//		   lloyd.getVoronoiList().get(1).setVisible(true);
//		   lloyd.getMinErrorVoronoi().setVisible(true);
//		   System.out.println("Voronoi Diagram index with Minimum Error: " 
//				   + lloyd.getVoronoiList().indexOf(lloyd.getMinErrorVoronoi()));
//		   System.out.println();
	}
}
