package server.main.networking;

import server.main.Main;

public class ServerSocketHandler implements Runnable{
	public void run() {
		startServerSocketLoop();
	}
	public void startServerSocketLoop() {
		SocketFactory serverSocketFactory = new SocketFactory();
		while(true) {
			Main.NWR.getThreadPoolHandler().addTask(new SocketHandler(serverSocketFactory.createServerSocket().accept()));
		}
	}
}
