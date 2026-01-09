package polymorphism_question6_Media_Player_Application;

public class Main {
	public static void main(String args[]) {
		MediaFile m;
		m=new AudioFile();
		m.play();
		m=new VideoFile();
		m.play();
		m=new Podcast();
		m.play();
	}
}
