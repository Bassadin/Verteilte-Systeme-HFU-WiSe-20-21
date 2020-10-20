package blatt03;

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

		System.out.println("Opening service on port " + port);
		try (ServerSocket serverSocket = new ServerSocket(port)) {

			while (true) {
				// "Try with Resources"-Block
				try (Socket socket = serverSocket.accept()) {
					System.out.println("Accepting new connection from: " + socket.getRemoteSocketAddress().toString());

					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

					writer.write("time service");
					writer.newLine();
					writer.flush();

					while (true) {
						String commandString = reader.readLine();

						if (commandString == null) {
							break;
						}

						System.out.println("Input received: " + commandString);
						if (commandString.equals("date")) {
							writer.write(Clock.date());
							System.out.println("Answering date");
						} else if (commandString.equals("time")) {
							writer.write(Clock.time());
							System.out.println("Answering time");
						} else {
							System.out.println("Terminating...");
							socket.close();
							break;
						}
						writer.newLine();
						writer.flush();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
