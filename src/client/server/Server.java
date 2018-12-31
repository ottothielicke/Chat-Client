package client.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import client.main.graphical.MainFrame;
import client.main.objects.NetworkInformation;

public class Server implements Runnable{
	private static NetworkInformation networkInformation;
	private static ServerSocket serverSocket;
	private static Socket socket;
	private static InputStream inputStream;
	private static InputStream oldInputStream;
	private static OutputStream outputStream;
	public void run() {
		initialize();
	}
	private void initialize() {
		try {
			serverSocket = new ServerSocket(networkInformation.getPort());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			socket = serverSocket.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			SocketHandler(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ioHandler();
	}
	public static void externalInit(NetworkInformation networkInformationInput) {
		Thread server = new Thread(new Server(networkInformationInput));
		server.start();
	}
	private Server(NetworkInformation input) {
		Server.networkInformation = input;
	}
	private void SocketHandler(Socket socket) throws IOException {
		if(socket.isConnected()) {
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
		}
	}
	private void ioHandler() {
		while(true) {
			inputStreamHandler();
			oldInputStream = inputStream;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private void inputStreamHandler() {
		if(inputStream != oldInputStream) {
			try {
				if(inputStream.available() > 0) {
					byte[] input = inputStream.readAllBytes();
					char[] charArray = new char[input.length];
					for(int i = 0; i < input.length; i++) {
						charArray[i] = (char)input[i];
					}
					String text = new String(charArray);
					MainFrame.addText(text);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void sendText(String input) {
		String toProcess = networkInformation.getUserName() + ": " + input;
		byte[] processedArray = new byte[toProcess.length()];
		for(int i = 0; i < toProcess.length(); i++) {
			processedArray[i] = (byte)toProcess.charAt(i);
		}
		try {
			outputStream.write(processedArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}