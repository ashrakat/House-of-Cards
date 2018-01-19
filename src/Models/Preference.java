package Models;

public class Preference {

	String type;	
	String statuse;
	PreferenceRange size;
	PreferenceRange price;
	boolean delete ; 	
	
	
	public Preference() {
		this.delete=false ; 
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatuse() {
		return statuse;
	}

	public void setStatuse(String statuse) {
		this.statuse = statuse;
	}

	public PreferenceRange getSize() {
		return size;
	}

	public void setSize(PreferenceRange size) {
		this.size = size;
	}

	public PreferenceRange getPrice() {
		return price;
	}

	public void setPrice(PreferenceRange price) {
		this.price = price;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	
	
}
