package polymorphism_question3_Online_Notification_System;

public class SMSNotification extends Notification {
	@Override
	void send(String email){
		System.out.println("email notification: "+email);
	}
}
