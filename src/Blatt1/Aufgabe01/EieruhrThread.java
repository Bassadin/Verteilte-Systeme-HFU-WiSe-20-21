package Blatt1.Aufgabe01;

public class EieruhrThread extends Thread {
	String message;
	int remainingMilliseonds;

	public static void main(String[] args) {
		EieruhrStarten(3000, "Hallo");
		EieruhrStarten(4000, "UwU");
		EieruhrStarten(5320, "lul");
	}

	public EieruhrThread(int milliSeconds, String text) {
		message = text;
		remainingMilliseonds = milliSeconds;
	}

	public void run() {
		while (remainingMilliseonds >= 0) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (remainingMilliseonds % 1000 == 0) {
				System.out.println(String.format("Uhr: %s 	- Verbleibend: %ss", message, remainingMilliseonds / 1000));
			}
			
			remainingMilliseonds -= 10;
		}
		System.out.println(message);
	}

	public static void EieruhrStarten(int milliSeconds, String text) {
		EieruhrThread newEieruhrThread = new EieruhrThread(milliSeconds, text);
		newEieruhrThread.start();
	}

}
