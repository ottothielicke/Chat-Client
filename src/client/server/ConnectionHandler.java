package client.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import client.main.graphical.MainFrame;
import client.main.objects.NetworkInformation;

public class ConnectionHandler implements Runnable{
	private static Socket socket;
	private static NetworkInformation networkInformation;
	private static InputStream inputStream;
	private static OutputStream outputStream;
	private static InputStream oldInputStream;
	private void beginConnection() {
		/*try {
			Thread.currentThread().wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainFrame.setOutputText("Starting connection");
		createSocket();
	}
	private void createSocket() {
		try {
			socket = new Socket(networkInformation.getIP(), networkInformation.getPort());
		} catch (UnknownHostException e) {
			String stackTrace = "";
			for(int i = 0; i < e.getStackTrace().length; i++) {
				stackTrace += "\n" + e.getStackTrace()[i];
			}
			MainFrame.setOutputText(MainFrame.getOutputText() + "\n" + "Connection Failed. UnknownHostException thrown" + "\n Stack Trace \n" + stackTrace);
			return;
		} catch (IOException e) {
			String stackTrace = "";
			for(int i = 0; i < e.getStackTrace().length; i++) {
				stackTrace += "\n" + e.getStackTrace()[i];
			}
			MainFrame.setOutputText(MainFrame.getOutputText() + "\n" + "Connection Failed. IOException thrown" + "\n Stack Trace \n" + stackTrace);
			return;
		}
		MainFrame.setOutputText(MainFrame.getOutputText() + "\n Connection Successful.");
		try {
			SocketHandler();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ioHandler();
	}
	public static void setNetworkInformation(NetworkInformation input) {
		networkInformation = input;
	}
	public void run() {
		beginConnection();
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
