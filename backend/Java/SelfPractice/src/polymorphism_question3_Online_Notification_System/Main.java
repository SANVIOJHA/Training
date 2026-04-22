package polymorphism_question3_Online_Notification_System;

public class Main {
	public static void main(String args[]) {
		Notification n;
		n=new EmailNotification();
		n.send("wertyuiogdasdfghjkhgfsdfghjklkj");
		n=new SMSNotification();
		n.send("sdfghjknxcvbnsdfghj");
		n=new PushNotification();
		n.send("qwertsdfghjcvbnm");
	}
}
