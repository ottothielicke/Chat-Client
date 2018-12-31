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
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			MainFrame.setOutputText(MainFrame.getOutputText() + "\n Creating server socket.");
			serverSocket = new ServerSocket(networkInformation.getPort());
		} catch (IOException e) {
			String stackTrace = "";
			for(int i = 0; i < e.getStackTrace().length; i++) {
				stackTrace += "\n" + e.getStackTrace()[i];
			}
			MainFrame.setOutputText(MainFrame.getOutputText() + "\n Creation failed. IOException thrown" + "\n Stack Trace \n" + stackTrace);
			return;
		}
		catch(NullPointerException e) {
			String stackTrace = "";
			for(int i = 0; i < e.getStackTrace().length; i++) {
				stackTrace += "\n" + e.getStackTrace()[i];
			}
			MainFrame.setOutputText(MainFrame.getOutputText() + "\n Creation failed. NullPointerException thrown" + "\n Stack Trace \n" + stackTrace);
			return;
		}
		MainFrame.setOutputText(MainFrame.getOutputText() + "\n Socket creation successful.");
		try {
			MainFrame.setOutputText(MainFrame.getOutputText() + "\n Waiting for Connection...");
			socket = serverSocket.accept();
		} catch (IOException e) {
			String stackTrace = "";
			for(int i = 0; i < e.getStackTrace().length; i++) {
				stackTrace += "\n" + e.getStackTrace()[i];
			}
			MainFrame.setOutputText(MainFrame.getOutputText() + "\n Creation failed. IOException thrown" + "\n Stack Trace \n" + stackTrace);
			return;
		}
		MainFrame.setOutputText(MainFrame.getOutputText() + "\n User connected");
		try {
			SocketHandler();
		} catch (IOException e) {
			String stackTrace = "";
			for(int i = 0; i < e.getStackTrace().length; i++) {
				stackTrace += "\n" + e.getStackTrace()[i];
			}
			MainFrame.setOutputText(MainFrame.getOutputText() + "\n Creation failed. IOException thrown" + "\n Stack Trace \n" + stackTrace);
			return;
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
	private void SocketHandler() throws IOException {
		if(socket.isConnected()) {
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
		}
	}
	private void ioHandler() {
		while(true) {
			try {
				SocketHandler();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				inputStreamHandler();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			oldInputStream = inputStream;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private void inputStreamHandler() throws IOException {
		try {
			String text = "";
				if(socket.getInputStream().available() > 0) {
					while(socket.getInputStream().available() > 0) {
						text += (char)socket.getInputStream().read();
					}
					MainFrame.addText(text);
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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