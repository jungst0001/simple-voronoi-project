import java.util.ArrayList;
import java.util.Random;

public class ModifiedLloyd extends Lloyd {
	protected final int MAX_WEIGHT;
	protected final int MIN_WEIGHT;
	protected Site newSite;
	protected int index;
	
	protected ArrayList<Double> errorList;
	
	public ModifiedLloyd(ArrayList<Site> siteList, int size){
		super(siteList, size);
		MAX_WEIGHT = size;
		MIN_WEIGHT = 0;
		newSite = null;
		errorList = new ArrayList<>();
		decideNewSite();
	}
	
	protected void decideNewSite(){
		int weight = MIN_WEIGHT;
//		int weight = MAX_WEIGHT;
		Site site = null;
		for(Site s : init_siteList){
//			if(s.getWeight() < weight){
			if(s.getWeight() > weight){
				weight = s.getWeight();
				site = s;
			}
		}
		
//		weight *= 2;
		weight /= 2;
		site.setWeight(weight);
		newSite = new Site(site.getX() + 10, site.getY() + 10, weight, new Random().nextInt(1234556));
	}
	
	@Override
	protected boolean equalSite(Voronoi v) {
		// TODO Auto-generated method stub
		ArrayList<Site> siteList = v.getSiteList();
		ArrayList<Site> centerList = v.getCenterList();
		
		Site s = siteList.get(index);
		Site c = centerList.get(index);
		
		return (s.getX() == c.getX()) && (s.getY() == c.getY());
	}
	
	@Override
	public void executeLloyd() {
		// TODO Auto-generated method stub
		
		iterNum = 0;
		boolean isStop = false;
		ArrayList<Site> sitelist = init_siteList;
		Site targetSite = newSite;
		sitelist.add(newSite);
		
		while(!isStop){
			Voronoi v = new Voronoi(sitelist, SIZE, iterNum);
			index = sitelist.indexOf(targetSite);
			v.drawVoronoi();
			v.computeCenterSite();
			voronoiList.add(v);
			iterNum++;
			isStop = equalSite(v);
			targetSite = v.getCenterList().get(index);
			sitelist.remove(index);
			sitelist.add(targetSite);
			errorList.add(v.getError());
		}
	}
	
	public Voronoi getMinErrorVoronoi(){
		int n = 0;
		double error = errorList.get(0);
		
		for(int i = 1; i < errorList.size(); i++){
			if(error > errorList.get(i).doubleValue()){
				n = i;
			}
		}
		
		return voronoiList.get(n);
	}
}
