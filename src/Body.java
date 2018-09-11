
public class Body {
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName; 
	/**
	 * create a Body from parameters
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public Body(double xp, double yp, double xv, double yv, double mass, String filename) {
		this.myXPos= xp;
		this.myYPos= yp;
		this.myXVel= xv;
		this.myYVel= yv;
		this.myMass= mass;
		this.myFileName= filename;
	}
	/**
	 * creates a copy of the previous Body constructor
	 * @param b is an object of Body type
	 */
	public Body(Body b) {
		myXPos= b.myXPos;
		myYPos= b.myYPos;
		myXVel= b.myXVel;
		myYVel= b.myYVel;
		myMass= b.myMass;
		myFileName= b.myFileName;
		
	}
	/**
	 * 
	 * @return instance variable myXPos
	 */
	public double getX(){
		return myXPos;
	}
	/**
	 * 
	 * @return instance variable myYPos
	 */
	public double getY(){
		return myYPos;
	}
	/**
	 * 
	 * @return instance variable myXVel
	 */
	public double getXVel() {
		return myXVel;
	}
	/**
	 * 
	 * @return instance variable myYVel
	 */
	public double getYVel() {
		return myYVel;
	}
	/**
	 * 
	 * @return instance variable myMass
	 */
	public double getMass() {
		return myMass;
	}
	/**
	 * 
	 * @return instance variable myFileName
	 */
	public String getName() {
		return myFileName;
	}
	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(Body b) {
		return Math.sqrt(Math.pow((myXPos- b.myXPos),2) + Math.pow(myYPos-b.myYPos,2));
	}
	/**
	 * Return the force exerted by this body on another
	 * @param b the other body to which Force is calculated
	 * @return force exerted by the body
	 */
	public double calcForceExertedBy(Body b) {
		return (6.67*1.0e-11)*((myMass*b.myMass)/Math.pow(calcDistance(b), 2));
	}
	/**
	 * 
	 * @param b the body to which Force exerted by X is calculated
	 * @return the force exerted by the x coordinate of the body on another
	 */
	public double calcForceExertedByX(Body b) {
		return -calcForceExertedBy(b)*((myXPos-b.myXPos)/calcDistance(b));
	}
	/**
	 * 
	 * @param b the body to which Force exerted byYX is calculated
	 * @return the force exerted by the y coordinate of the body on another
	 */
	public double calcForceExertedByY(Body b) {
		return -calcForceExertedBy(b)*((myYPos-b.myYPos)/calcDistance(b));
	}
	/**
	 * 
	 * @param bodies an array of Body objects
	 * @return the net X force exerted by all Body objects on each other
	 */
	public double calcNetForceExertedByX(Body[] bodies) {
		double force= 0.0;
		for (Body b : bodies) {
			if (!b.equals(this)) {
				force= force + calcForceExertedByX(b);
			}
		}
		return force;
	}
	/**
	 * 
	 * @param bodies an array of Body objects
	 * @return the net Y force exerted by all Body objects on each other
	 */
	public double calcNetForceExertedByY(Body[] bodies) {
		double force= 0.0;
		for (Body b : bodies) {
			if (! b.equals(this)) {
				force = force + calcForceExertedByY(b);
			}
		}
		return force;
	}
	/**
	 * updates the instance variables with new values for each amount of time 
	 * @param deltaT change is time
	 * @param xforce force of X coordinate
	 * @param yforce force of Y coordinate
	 */
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
	/**
	 * draws each Body object at its specified position using a picture from the file
	 */
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
	

}
