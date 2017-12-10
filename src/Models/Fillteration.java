package Models;

import Models.House; 
import java.util.*;

public class Fillteration {
	
	public ArrayList<House> fillterByRentSale(double price , ArrayList<House>  listOfHouse) {
		ArrayList<House> newList = new ArrayList<House>();
		for(int i=0 ; i <listOfHouse.size() ; i++)
			if(listOfHouse.get(i).getRate() == price)
				newList.add(listOfHouse.get(i)); 
		
		return newList ; 
	}
	
	/**when we put Location ask your team **/
	public void fillterByArea(double Area) {	
	}
	
	public ArrayList<House> fillterByArea(double Area, ArrayList<House>  listOfHouse ) {	
		ArrayList<House> newList = new ArrayList<House>();

		return newList ; 
	}

	public ArrayList<House> fillterBySize(int size , ArrayList<House>  listOfHouse ) {
		ArrayList<House> newList = new ArrayList<House>();
		for(int i=0 ; i <listOfHouse.size() ; i++)
			if(listOfHouse.get(i).getSize() == size)
				newList.add(listOfHouse.get(i)); 
		
		return newList ; 
	}
	
	public ArrayList<House> fillterByFloor(int numOfFloors , ArrayList<House>  listOfHouse ) {
		ArrayList<House> newList = new ArrayList<House>();
		for(int i=0 ; i <listOfHouse.size() ; i++)
			if(listOfHouse.get(i).getNumOfFloors()== numOfFloors)
				newList.add(listOfHouse.get(i)); 
		
		return newList ; 
	}
	
	public ArrayList<House> fillterByType(String type , ArrayList<House>  listOfHouse ) {
		ArrayList<House> newList = new ArrayList<House>();
		for(int i=0 ; i <listOfHouse.size() ; i++)
			if(listOfHouse.get(i).getType().equals(type))
				newList.add(listOfHouse.get(i)); 
		
		return newList ; 
	}
	
	

	
}
