import java.util.ArrayList;

public class Lloyd {
	protected final int SIZE;
	protected int iterNum;
	protected ArrayList<Site> init_siteList;
	protected ArrayList<Voronoi> voronoiList;
	
	public Lloyd(ArrayList<Site> siteList, int size){
		SIZE = size;
		init_siteList = siteList;
		this.iterNum = 0;
		voronoiList = new ArrayList<>();
		
	}
	
	protected boolean equalSite(Voronoi v){
		ArrayList<Site> siteList = v.getSiteList();
		ArrayList<Site> centerList = v.getCenterList();
		
		for(int i = 0; i < siteList.size(); i++){
			Site s = siteList.get(i);
			Site c = centerList.get(i);
			
//			System.out.println("iter: " + i);
//			System.out.println("s: " + s);
//			System.err.println("c: " + c);
			
			if(!((s.getX() == c.getX()) && (s.getY() == c.getY()))){
				return false;
			}
		}
		
		return true;
	}
	
	public void executeLloyd(){
		iterNum = 0;
		boolean isStop = false;
		ArrayList<Site> sitelist = init_siteList;
		
//		for(int i = 0; i < iterNum; i++){
////			System.out.println(i);
//			Voronoi v = new Voronoi(sitelist, SIZE, i, null);
////			System.out.println(i);
//			voronoiList.add(v);
//			sitelist = v.getCenterSite();
//			
////			System.out.println(sitelist.get(0).getX());
//		}
		
		while(!isStop){
			Voronoi v = new Voronoi(sitelist, SIZE, iterNum);
			v.drawVoronoi();
			v.computeCenterSite();
			voronoiList.add(v);
//			sitelist = v.getCenterSite();
			iterNum++;
			isStop = equalSite(v);
			sitelist = v.getCenterList();
		}
	}
	
	public ArrayList<Voronoi> getVoronoiList(){ return voronoiList; }
	public int getIterNum() { return iterNum; }
}
