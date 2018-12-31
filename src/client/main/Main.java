package client.main;

import client.main.graphical.MainFrame;
import client.main.launcher.Launcher;
import client.main.objects.NetworkInformation;
import client.server.ConnectionHandler;
import client.server.Server;

public class Main {
	private static NetworkInformation networkInformation;
	private static Thread connectionHandlerThread;
	public static void main(String[] args) {
		Launcher.start();
	}
	public static void initializeClient(NetworkInformation networkInformationInput) {
		MainFrame.externalInit();
		if(networkInformation.IsPeerToPeer()) {
			Server.externalInit(networkInformationInput);
		}
		else {
			connectionHandlerThread = new Thread(new ConnectionHandler());
			connectionHandlerThread.start();
		}
	}
	public static void setNetworkInformation(NetworkInformation input) {
		networkInformation = input;
	}
	public static NetworkInformation getNetworkInformation() {
		return networkInformation;
	}
	public static void notifyConnectionHandler() {
		connectionHandlerThread.notify();
	}
}