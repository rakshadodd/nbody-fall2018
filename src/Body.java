
public class Body {
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName; 
	
	public Body(double xp, double yp, double xv, double yv, double mass, String filename) {
		this.myXPos= xp;
		this.myYPos= yp;
		this.myXVel= xv;
		this.myYVel= yv;
		this.myMass= mass;
		this.myFileName= filename;
	}
	public Body(Body b) {
		myXPos= b.myXPos;
		myYPos= b.myYPos;
		myXVel= b.myXVel;
		myYVel= b.myYVel;
		myMass= b.myMass;
		myFileName= b.myFileName;
		
	}
	public double getX(){
		return myXPos;
	}
	public double getY(){
		return myYPos;
	}
	public double getXVel() {
		return myXVel;
	}
	public double getYVel() {
		return myYVel;
	}
	public double getMass() {
		return myMass;
	}
	public String getName() {
		return myFileName;
	}
	public double calcDistance(Body b) {
		return Math.sqrt(Math.pow((myXPos- b.myXPos),2) + Math.pow(myYPos-b.myYPos,2));
	}
	public double calcForceExertedBy(Body b) {
		return (6.67*1.0e-11)*((myMass*b.myMass)/Math.pow(calcDistance(b), 2));
	}
	public double calcForceExertedByX(Body b) {
		return -calcForceExertedBy(b)*((myXPos-b.myXPos)/calcDistance(b));
	}
	public double calcForceExertedByY(Body b) {
		return -calcForceExertedBy(b)*((myYPos-b.myYPos)/calcDistance(b));
	}
	public double calcNetForceExertedByX(Body[] bodies) {
		double force= 0.0;
		for (Body b : bodies) {
			if (!b.equals(this)) {
				force= force + calcForceExertedByX(b);
			}
		}
		return force;
	}
	public double calcNetForceExertedByY(Body[] bodies) {
		double force= 0.0;
		for (Body b : bodies) {
			if (! b.equals(this)) {
				force = force + calcForceExertedByY(b);
			}
		}
		return force;
	}
	public void update(double deltaT, double xforce, double yforce) {
		double ax= xforce/myMass;
		double ay= yforce/myMass;
		double nvx= myXVel + deltaT * ax;
		double nvy= myYVel + deltaT * ay;
		double nx= myXPos + deltaT * nvx;
		double ny= myYPos + deltaT * nvy;
		this.myXPos= nx;
		this.myYPos= ny;
		this.myXVel= nvx;
		this.myYVel= nvy;
		
	}
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
	

}
