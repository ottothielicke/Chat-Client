package client.main.objects;

public class NetworkInformation {
	private int port;
	private String IP;
	private String username;
	boolean isPeerToPeer;
	public int getPort() {
		return port;
	}
	public String getIP() {
		return IP;
	}
	public String getUserName() {
		return username;
	}
	public void setPort(int input) {
		port = input;
	}
	public void setIP(String input) {
		IP = input;
	}
	public void setUsername(String input) {
		username = input;
	}
	public void IsPeerToPeer(boolean value) {
		isPeerToPeer = value;
	}
	public NetworkInformation() {
	}
	public NetworkInformation(String IP) {
		this.IP = IP;
	}
	public NetworkInformation(String IP, int port) {
		this.IP = IP;
		this.port = port;
	}
	public NetworkInformation(String IP, int port, String username) {
		this.IP = IP;
		this.port = port;
		this.username = username;
	}
	public NetworkInformation(String IP, int port, String username, boolean isPeerToPeer) {
		this.IP = IP;
		this.port = port;
		this.username = username;
		this.isPeerToPeer = isPeerToPeer;
	}
	public boolean IsPeerToPeer() {
		return isPeerToPeer;
	}
}