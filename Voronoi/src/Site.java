public class Site {
	private int x;
	private int y;
	private int color;
	private int weight;
	
	public Site(int x, int y, int weight, int color){
		this.x = x;
		this.y = y;
		this.weight = weight;
		this.color = color;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "px: " + x + " " + "py: " + y + " " + "w: " + weight;
	}
	
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public void setWeight(int weight) { this.weight = weight; }
	public void setLocation(int x, int y) { setX(x); setY(y); }
	public int getX() { return x; }
	public int getY() { return y; }
	public int getWeight() { return weight; }
	public int getColor() { return color; }
}
