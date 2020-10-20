package blatt03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TimeServiceClient {

	public static void main(String[] args) {
		System.out.println(timeFromServer("localhost"));
	}

	public static String dateFromServer(String ipAddress) {
		try {
			Socket socket = new Socket(ipAddress, TimeService.port);

			String message = "date";
//			
//			writeMessage(socket, message);
//			return readMessage(socket);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		;

		return "";
	}

	public static String timeFromServer(String ipAddress) {

		
		try(Socket socket = new Socket(ipAddress, TimeService.port)) {
			BufferedWriter printWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String message = "time";

			if (!bufferedReader.readLine().equals("time service")) {
				socket.close();
				return "wrong service";
			}
			
			printWriter.write(message);
			printWriter.newLine();

			//Send message
			printWriter.flush();
			
			//Get answer
			String resultString = bufferedReader.readLine();
			
			socket.close();
			
			return resultString;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}

//	static void writeMessage(java.net.Socket socket, String message) throws IOException {
//		BufferedWriter printWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//		printWriter.write(message);
//		printWriter.flush();
//	}
//
//	static String readMessage(java.net.Socket socket) throws IOException {
//		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//		char[] buffer = new char[200];
//		int amountOfChars = bufferedReader.read(buffer, 0, 200);
//		String message = new String(buffer, 0, amountOfChars);
//		return message;
//	}

}
