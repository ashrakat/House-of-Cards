package Models;

import java.util.ArrayList;

public class Normal extends User{
	
	ArrayList<Advertise> listOfAdvertises = new ArrayList<Advertise>();
	ArrayList<Notification> listOfNotifications= new ArrayList<Notification>();
	ArrayList<Preference> preferences = new ArrayList<Preference>() ; 

	public ArrayList<Advertise> getListOfAdvertises() {
		return listOfAdvertises;
	}

	public void setListOfAdvertises(ArrayList<Advertise> listOfAdvertises) {
		this.listOfAdvertises = listOfAdvertises;
	}
	
	public ArrayList<Notification> getListOfNotifications() {
		return listOfNotifications;
	}

	public void setListOfNotifications(ArrayList<Notification> listOfNotifications) {
		this.listOfNotifications = listOfNotifications;
	}
	
	public ArrayList<Preference> getPreferences() {
		return preferences;
	}

	public void setPreferences(ArrayList<Preference> preferences) {
		this.preferences = preferences;
	}


	public void addPreference(Preference preference) {
		preferences.add(preference);
		
	}

	public void addAdvertise(Advertise advertise){
		this.listOfAdvertises.add(advertise);
	}
	
	public void addNotification(Notification Notify){
		this.listOfNotifications.add(Notify);
	}
	


}
