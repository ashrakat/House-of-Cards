package Models;

import java.util.ArrayList;

public class Normal extends User{
	
	ArrayList<Advertise> listOfAdvertises = new ArrayList<Advertise>();
	ArrayList<Notifications> listOfNotifications= new ArrayList<Notifications>();
	ArrayList <String> Preferences = new ArrayList <String>() ; 

	public ArrayList<Advertise> getListOfAdvertises() {
		return listOfAdvertises;
	}

	public void setListOfAdvertises(ArrayList<Advertise> listOfAdvertises) {
		this.listOfAdvertises = listOfAdvertises;
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

	public void addAdvertise(Advertise advertise){
		this.listOfAdvertises.add(advertise);
	}
	
	public void addNotification(Notifications Notify){
		this.listOfNotifications.add(Notify);
	}
	
	public void addPreferences(String Preference){
		this.Preferences.add(Preference);
	}

}
