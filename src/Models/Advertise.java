package Models;

import java.util.ArrayList;

public class Advertise {
	
	User owner;
	String type;
	String title;
	String status;
	String forWhat;
	String address;
	String description;
	
	int id;
	int size;
	double rate;
	int numOfFloors;
	int mainPhoto[];
	int otherPhotos[][];
	boolean susbinded = false;
	ArrayList<Comment> comments = new ArrayList<Comment>();
	
	
	public Advertise(){
	}


	public Normal getOwner() {
		return (Normal) owner;
	}


	public void setOwner(Normal owner) {
		this.owner = owner;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
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


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public int getNumOfFloors() {
		return numOfFloors;
	}


	public void setNumOfFloors(int numOfFloors) {
		this.numOfFloors = numOfFloors;
	}


	public int[] getMainPhoto() {
		return mainPhoto;
	}


	public void setMainPhoto(int[] mainPhoto) {
		this.mainPhoto = mainPhoto;
	}


	public int[][] getOtherPhotos() {
		return otherPhotos;
	}


	public void setOtherPhotos(int[][] otherPhotos) {
		this.otherPhotos = otherPhotos;
	}


	public boolean isSusbinded() {
		return susbinded;
	}


	public void setSusbinded(boolean susbinded) {
		this.susbinded = susbinded;
	}


	public ArrayList<Comment> getComments() {
		return comments;
	}


	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}
	
	
	
	
}

	