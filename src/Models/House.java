package Models;

import java.util.ArrayList;

public class House {
	
	User owner;
	String type; //Villa, Studi
	String status; //finished or half finished
	String forWhat;
	String description;
	
	int size;
	double rate;
	int pics[][];
	int numOfFloors;
	ArrayList<Comment> comments = new ArrayList<Comment>();
	
	
	public House() {
		
	}


	public User getOwner() {
		return owner;
	}


	public void setOwner(User owner) {
		this.owner = owner;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getForWhat() {
		return forWhat;
	}


	public void setForWhat(String forWhat) {
		this.forWhat = forWhat;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public double getRate() {
		return rate;
	}


	public void setRate(double rate) {
		this.rate = rate;
	}


	public int[][] getPics() {
		return pics;
	}


	public void setPics(int[][] pics) {
		this.pics = pics;
	}


	public int getNumOfFloors() {
		return numOfFloors;
	}


	public void setNumOfFloors(int numOfFloors) {
		this.numOfFloors = numOfFloors;
	}


	public ArrayList<Comment> getComments() {
		return comments;
	}


	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}
	
	
	
	

}
