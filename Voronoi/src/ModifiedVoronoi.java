import java.awt.Point;
import java.util.ArrayList;

@Deprecated
public class ModifiedVoronoi extends Voronoi {
	protected Site targetSite;
	protected Site centerSite;
	
	public ModifiedVoronoi(ArrayList<Site> sitelist, int size, Site targetSite) {
		// TODO Auto-generated constructor stub
		this(sitelist, size, 0, targetSite);
	}
	
	public ModifiedVoronoi(ArrayList<Site> sitelist, int size, int imageNum, Site targetSite){
		super(sitelist, size, imageNum);
		sitelist.add(targetSite);
		this.targetSite = targetSite;
		centerSite = null;
		
		this.sitelist = sitelist;
		cellNum = sitelist.size();
		midPointList = new Point[cellNum];
		midPointNum = new int[cellNum];
		
		for(int i = 0; i < cellNum; i++) {
			midPointList[i] = new Point(0, 0);
			midPointNum[i] = 0;
		}
	}
	
	@Override
	public void computeCenterSite() {
		// TODO Auto-generated method stub
		int index = sitelist.indexOf(targetSite);
		Point p = midPointList[index];
		p.setLocation(p.getX() / midPointNum[index], p.getY() / midPointNum[index]);
		centerSite = new Site(p.x, p.y, targetSite.getWeight(), targetSite.getColor());
	}
	
	public Site getTargetSite() { return targetSite; }
	public Site getCenterSite() { return centerSite; }
}
