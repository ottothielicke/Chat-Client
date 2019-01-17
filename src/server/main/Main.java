package server.main;

import server.main.graphical.MainWindow;
import server.main.networking.NetworkingReferenceObject;
import server.main.networking.ThreadPoolHandler;

public class Main {
	public static NetworkingReferenceObject NWR;
	public static void main(String[] args) {
		MainWindow.externalInit();
		NWR = new NetworkingReferenceObject();
		ThreadPoolHandler TPH = new ThreadPoolHandler();
		NWR.setThreadPoolHandler(TPH);
		Thread TPHThread = new Thread(NWR.getThreadPoolHandler());
		TPHThread.start();
	}
}