package client.server;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import client.main.graphical.MainFrame;
import client.main.objects.NetworkInformation;

public class ConnectionHandler implements Runnable{
	private static Socket socket;
	private static NetworkInformation networkInformation;
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
	}
	public static void setNetworkInformation(NetworkInformation input) {
		networkInformation = input;
	}
	public void run() {
		beginConnection();
	}
}
