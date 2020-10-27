package Blatt04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class HTTPSClient {
	static final int portNumber = 443;

	public static void main(String[] args) {
		get("https://www.bundestag.de/presse");
	}

	public static void get(String url) {
		try {
			URL targetUrl = new URL(url);
			SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
			try (SSLSocket socket = (SSLSocket) factory.createSocket(targetUrl.getAuthority(), HTTPSClient.portNumber)) {
				BufferedWriter printWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				printWriter.write(String.format("GET %s HTTP/1.1", targetUrl.getFile()));
				printWriter.newLine();
				printWriter.write(String.format("Host: %s", targetUrl.getAuthority()));
				printWriter.newLine();
				printWriter.newLine();

				// Send message
				printWriter.flush();

				//Receive HTTP answer
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
}
