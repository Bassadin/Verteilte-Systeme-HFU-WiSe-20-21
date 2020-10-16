package blatt02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeService {
	static int port = 75;

	public static void main(String[] args) {
		try {
			System.out.println("Opening service on port " + port);
			ServerSocket serverSocket = new ServerSocket(port);
			Socket socket = serverSocket.accept();

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			writer.write("time service");
			writer.newLine();
			writer.flush();

			while (true) {
				String commandString = reader.readLine();
				if (commandString.equals("date")) {
					writer.write(Clock.date());
				} else if (commandString.equals("time")) {
					writer.write(Clock.time());
				} else {
					socket.close();
					break;
				}
				writer.newLine();
				writer.flush();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
