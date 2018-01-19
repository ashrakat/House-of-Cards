package Models;

public class Notification{
	
	String link;
	String body;
	notificationType type ; 
	boolean read ; 
	
	public enum notificationType{
		Comment, Advertise
	}
	
	private enum NotificationBody {
		newAdvertise("There is an Advertise that meets your preferences"),
		newComment("There is a new Comment on Advertise you have or commented on before");
		
		 private String type;
		 
		 NotificationBody(String type) {
		        this.type = type;
		 }		 
		 public String getBody() {
		        return type;
	     }
	};
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getBody() {
		return body;
	}
	public void setBody(notificationType type) {
		if(type == notificationType.Comment)
			body = NotificationBody.newComment.getBody();
		else if(type == notificationType.Advertise)
			body = NotificationBody.newAdvertise.getBody();
	
	 this.type = type;
	}
	public notificationType getType() {
		return type;
	}
	public void setType(notificationType type) {
		this.type = type;
	}
	public boolean isRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
	public Notification() {
		this.read = false ; 
	}
	
}
