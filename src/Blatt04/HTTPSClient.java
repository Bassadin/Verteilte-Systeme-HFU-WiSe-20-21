package Blatt04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class HTTPSClient {
	static final int portNumber = 443;

	public static void main(String[] args) {
		System.out.println(urlExists("https://www.bundestag.de/presse"));
	}

	public static void get(String url) {
		try {
			URL targetUrl = new URL(url);
			SocketFactory factory = SSLSocketFactory.getDefault();
			try (Socket socket = factory.createSocket(targetUrl.getHost(), HTTPSClient.portNumber)) {
				BufferedWriter printWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				printWriter.write(String.format("GET %s HTTP/1.1", targetUrl.getFile()));
				printWriter.newLine();
				printWriter.write(String.format("Host: %s", targetUrl.getHost()));
				printWriter.newLine();
				printWriter.newLine();

				// Send message
				printWriter.flush();

				// Receive HTTP answer
				String nextLine = bufferedReader.readLine();
				while (nextLine != null) {
					System.out.println(nextLine);
					nextLine = bufferedReader.readLine();
				}
				socket.close();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
	}

	public static boolean urlExists(String url) {
		try {
			URL targetUrl = new URL(url);
			SocketFactory factory = SSLSocketFactory.getDefault();
			try (Socket socket = factory.createSocket(targetUrl.getHost(), HTTPSClient.portNumber)) {

				BufferedWriter printWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				printWriter.write(String.format("GET %s HTTP/1.1", targetUrl.getFile()));
				printWriter.newLine();
				printWriter.write(String.format("Host: %s", targetUrl.getHost()));
				printWriter.newLine();
				printWriter.newLine();

				// Send message
				printWriter.flush();

				// Receive HTTP answer
				String firstResponseLine = bufferedReader.readLine();

				// Check for "OK" Status code in first answer line
				return firstResponseLine.split(" ")[1].equals("200");
			} catch (Exception e) {
				return false;
			}
		} catch (MalformedURLException e) {
			return false;
		}
	}
}
