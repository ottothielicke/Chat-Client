package client.server;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import client.main.graphical.MainFrame;
import client.main.objects.NetworkInformation;

public class ConnectionHandler implements Runnable{
	private static Socket socket;
	private static NetworkInformation networkInformation;
	private void beginConnection(NetworkInformation networkInformationInput) {
		try {
			Thread.currentThread().wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MainFrame.userTextArea.setText("Starting connection");
		createSocket(networkInformationInput);
	}
	private void createSocket(NetworkInformation input) {
		try {
			socket = new Socket(input.getIP(), input.getPort());
		} catch (UnknownHostException e) {
			MainFrame.userTextArea.setText(MainFrame.userTextArea.getText() + "\n" + "Connection Failed. UnknownHostException thrown" + "\n Stack Trace \n" + e.getStackTrace().toString());
		} catch (IOException e) {
			MainFrame.userTextArea.setText(MainFrame.userTextArea.getText() + "\n" + "Connection Failed. IOException thrown" + "\n Stack Trace \n" + e.getStackTrace().toString());
		}
		MainFrame.userTextArea.setText(MainFrame.userTextArea.getText() + "\n Connection Successful.");
	}
	public static void setNetworkInformation(NetworkInformation input) {
		networkInformation = input;
	}
	public void run() {
		beginConnection(networkInformation);
	}
}
