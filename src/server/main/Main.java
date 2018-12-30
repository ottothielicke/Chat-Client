package server.main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;

//just gonna write a test server
public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		@SuppressWarnings("resource")
		ServerSocket socket = new ServerSocket(666, 3, new InetAddress());
		socket.bind(new SocketAddress());
		while(true) {
			socket.accept();
			if(socket.isBound()) {
				
			}
			Thread.sleep(100);
		}
	}
}
