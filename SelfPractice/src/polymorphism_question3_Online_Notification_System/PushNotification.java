package polymorphism_question3_Online_Notification_System;

public class PushNotification extends Notification {
	@Override
	void send(String email) {
		System.out.println("email notification: "+email);
	}
}
