package server.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

//just gonna write a test server
public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		initServer();
	}
	static ArrayList<Socket> clients;
	private static void initServer() {
		ServerSocket mainSocket = null;
		try {
			mainSocket = new ServerSocket(25566);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Socket socket;
		try {
			socket = mainSocket.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}