
import Models.Advertise ; 
import java.util.*;

public class Fillteration {
	
	public ArrayList<Advertise> fillterByRentSale(double price , ArrayList<Advertise>  listOfHouse) {
		ArrayList<Advertise> newList = new ArrayList<Advertise>();
		for(int i=0 ; i <listOfHouse.size() ; i++)
			if(listOfHouse.get(i).getRate() == price)
				newList.add(listOfHouse.get(i)); 
		
		return newList ; 
	}
	
	/**when we put Location ask your team **/
	public ArrayList<Advertise> fillterByArea(double Area, ArrayList<Advertise>  listOfHouse ) {	
		ArrayList<Advertise> newList = new ArrayList<Advertise>();

		return newList ; 
	}

	public ArrayList<Advertise> fillterBySize(int size , ArrayList<Advertise>  listOfHouse ) {
		ArrayList<Advertise> newList = new ArrayList<Advertise>();
		for(int i=0 ; i <listOfHouse.size() ; i++)
			if(listOfHouse.get(i).getSize() == size)
				newList.add(listOfHouse.get(i)); 
		
		return newList ; 
	}
	
	public ArrayList<Advertise> fillterByFloor(int numOfFloors , ArrayList<Advertise>  listOfHouse ) {
		ArrayList<Advertise> newList = new ArrayList<Advertise>();
		for(int i=0 ; i <listOfHouse.size() ; i++)
			if(listOfHouse.get(i).getNumOfFloors()== numOfFloors)
				newList.add(listOfHouse.get(i)); 
		
		return newList ; 
	}
	
	public ArrayList<Advertise> fillterByType(String type , ArrayList<Advertise>  listOfHouse ) {
		ArrayList<Advertise> newList = new ArrayList<Advertise>();
		for(int i=0 ; i <listOfHouse.size() ; i++)
			if(listOfHouse.get(i).getType().equals(type))
				newList.add(listOfHouse.get(i)); 
		
		return newList ; 
	}
	
	

	
}
