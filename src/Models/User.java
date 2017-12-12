package Models;


import java.io.File;
import java.io.InputStream;

public class User {
	
	String name;
	String Type; 
	String userName;
	String password;
	String email;
	String phone;
	InputStream pic;
	
	public User() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public InputStream getPic() {
		return pic;
	}
	public void setPic(InputStream pic) {
		this.pic = pic;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	
	
	
}
