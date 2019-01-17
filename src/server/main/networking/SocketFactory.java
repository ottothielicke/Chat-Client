package server.main.networking;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

import javax.net.ServerSocketFactory;

public class SocketFactory extends ServerSocketFactory{

	@Override
	public ServerSocket createServerSocket(int port) throws IOException {
		ServerSocket socket = new ServerSocket(port);
		return socket;
	}

	@Override
	public ServerSocket createServerSocket(int port, int backlog) throws IOException {
		ServerSocket socket = new ServerSocket(port, backlog);
		return socket;
	}

	@Override
	public ServerSocket createServerSocket(int port, int backlog, InetAddress ifAddress) throws IOException {
		ServerSocket socket = new ServerSocket(port, backlog, ifAddress);
		return socket;
	}
	
	public SocketFactory() {
		super();
	}
}
