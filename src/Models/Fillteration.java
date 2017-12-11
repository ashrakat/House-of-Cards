package  Models;
import Models.Advertise;  
import java.util.*;

public class Fillteration {
	
	public ArrayList<Advertise> fillterByRentSale(double rate , ArrayList<Advertise>  listOfAdvertise) {
		ArrayList<Advertise> newList = new ArrayList<Advertise>();
		for(int i=0 ; i <listOfAdvertise.size() ; i++)
			if(listOfAdvertise.get(i).getRate() == rate)
				newList.add(listOfAdvertise.get(i)); 
		
		return newList ; 
	}
	
	public ArrayList<Advertise> fillterByArea(String Area, ArrayList<Advertise>  listOfAdvertise ) {	
		ArrayList<Advertise> newList = new ArrayList<Advertise>();
		for(int i=0 ; i <listOfAdvertise.size() ; i++)
			if(listOfAdvertise.get(i).getAddress().equals(Area) )
				newList.add(listOfAdvertise.get(i)); 
		
		return newList ; 
	}

	public ArrayList<Advertise> fillterBySize(int size , ArrayList<Advertise>  listOfAdvertise ) {
		ArrayList<Advertise> newList = new ArrayList<Advertise>();
		for(int i=0 ; i <listOfAdvertise.size() ; i++)
			if(listOfAdvertise.get(i).getSize() == size)
				newList.add(listOfAdvertise.get(i)); 
		
		return newList ; 
	}
	
	public ArrayList<Advertise> fillterByFloor(int numOfFloors , ArrayList<Advertise>  listOfAdvertise ) {
		ArrayList<Advertise> newList = new ArrayList<Advertise>();
		for(int i=0 ; i <listOfAdvertise.size() ; i++)
			if(listOfAdvertise.get(i).getNumOfFloors()== numOfFloors)
				newList.add(listOfAdvertise.get(i)); 
		
		return newList ; 
	}
	
	public ArrayList<Advertise> fillterByType(String type , ArrayList<Advertise>  listOfAdvertise ) {
		ArrayList<Advertise> newList = new ArrayList<Advertise>();
		for(int i=0 ; i <listOfAdvertise.size() ; i++)
			if(listOfAdvertise.get(i).getType().equals(type))
				newList.add(listOfAdvertise.get(i)); 
		
		return newList ; 
	}
	
	public ArrayList<Advertise> fillterByPrice(double price , ArrayList<Advertise>  listOfAdvertise ) {
		ArrayList<Advertise> newList = new ArrayList<Advertise>();
		for(int i=0 ; i <listOfAdvertise.size() ; i++)
			if(listOfAdvertise.get(i).getPrice() == price)
				newList.add(listOfAdvertise.get(i)); 
		
		return newList ; 
	}
	
	
}
