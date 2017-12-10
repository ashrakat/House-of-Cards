package Models;

import java.util.ArrayList;

public class Normal extends User{
	
	ArrayList<Advertise> listOfHouses = new ArrayList<Advertise>();
	ArrayList<Notifications> listOfNotifications= new ArrayList<Notifications>();
	ArrayList <String> Preferences = new ArrayList <String>() ; 

	public ArrayList<Advertise> getListOfHouses() {
		return listOfHouses;
	}

	public void setListOfHouses(ArrayList<Advertise> listOfHouses) {
		this.listOfHouses = listOfHouses;
	}
	
	public ArrayList<Notifications> getListOfNotifications() {
		return listOfNotifications;
	}

	public void setListOfNotifications(ArrayList<Notifications> listOfNotifications) {
		this.listOfNotifications = listOfNotifications;
	}
	
	public ArrayList<String> getPreferences() {
		return Preferences;
	}

	public void setPreferences(ArrayList<String> preferences) {
		Preferences = preferences;
	}

	public void addHouse(Advertise house){
		this.listOfHouses.add(house);
	}
	
	public void addNotification(Notifications Notify){
		this.listOfNotifications.add(Notify);
	}
	
	public void addPreferences(String Preference){
		this.Preferences.add(Preference);
	}

}
