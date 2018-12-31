package client.main.launcher;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import client.main.Main;
import client.main.objects.NetworkInformation;
import client.server.ConnectionHandler;

public class Launcher implements ActionListener{
	private static NetworkInformation clientInformation;
	private static JFrame frame;
	private static JButton startClient;
	private static JButton setIP;
	private static JButton setName;
	private static JButton setPort;
	private static JButton useDefaults;
	private static JButton startP2PServer;
	private static JButton startDedicatedServer;
	private static JTextField name;
	private static JTextField IP;
	private static JTextField port;
	private static GridLayout layout;
	public static void start() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Launcher l = new Launcher();
				l.initLauncher();
			}
		});
	}
	private void initLauncher() {
		frame = new JFrame("Chat Client Launcher");
		startClient = new JButton("Start Client");
		setIP = new JButton("Set Server IP");
		setName = new JButton("Set Name");
		setPort = new JButton("Set Port");
		useDefaults = new JButton("Start With Default Settings");
		startP2PServer = new JButton("Start Peer-To-Peer Server");
		startDedicatedServer = new JButton("Start Dedicated Server(doesn't work yet)");
		name = new JTextField();
		IP = new JTextField();
		port = new JTextField();
		layout = new GridLayout(5, 2);
		frame.setLayout(layout);
		startClient.addActionListener(this);
		setIP.addActionListener(this);
		setPort.addActionListener(this);
		setName.addActionListener(this);
		useDefaults.addActionListener(this);
		startP2PServer.addActionListener(this);
		frame.setSize(512, 512);
		frame.setPreferredSize(new Dimension(512, 512));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(setIP);
		frame.add(IP);
		frame.add(setName);
		frame.add(name);
		frame.add(setPort);
		frame.add(port);
		frame.add(startP2PServer);
		frame.add(startDedicatedServer);
		frame.add(useDefaults);
		frame.add(startClient);
		frame.pack();
		frame.setVisible(true);
		createClientInformationObject();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(startClient)) {
			frame.dispose();
			Main.setNetworkInformation(clientInformation);
			ConnectionHandler.setNetworkInformation(clientInformation);
			Main.initializeClient(clientInformation);
			return;
		}
		else if(e.getSource().equals(setIP)) {
			String input = IP.getText();
			if(input.isEmpty()) {
				IP.setText("Please enter IP address.");
				return;
			}
			if(input.equals("Please enter IP address."))
				return;
			int periodCount = 0;
			for(int i = 0; i < input.length(); i++) { 
				if(input.charAt(i) == '.')
					periodCount++;
			}
			for(int i = 0; i < input.length(); i++) {
				if(!((input.charAt(i) > 47 && input.charAt(i) < 58) || input.charAt(i) == '.')) {
					IP.setText("Invalid IPv4 address entered");
					return;
				}
			}
			if(periodCount != 3) {
				IP.setText("Invalid IPv4 address entered");
				return;
			}
			else {
				IP.setText("IP address set to " + input);
				clientInformation.setIP(input);
				return;
			}
		}
		else if(e.getSource().equals(setName)) {
			if(name.getText().isEmpty()) {
				name.setText("Please enter a name.");
				return;
			}
			if(name.getText().equals("Please enter a name."))
				return;
			clientInformation.setUsername(name.getText());
			name.setText("Name set to \"" + name.getText() + "\"");
		}
		else if(e.getSource().equals(setPort)) {
			if(port.getText().isEmpty()) {
				port.setText("Please enter a port.");
				return;
			}
			if(port.getText().equals("Please enter a port."))
				return;
			try {
				clientInformation.setPort(Integer.parseInt(port.getText()));
			}
			catch(NumberFormatException exception) {
				port.setText("Please enter a valid port");
			}
		}
		else if(e.getSource().equals(startP2PServer)) {
			clientInformation.isPeerToPeer(true);
			Main.initializeClient(clientInformation);
		}
	}
	private void createClientInformationObject() {
		clientInformation = new NetworkInformation();
	}
}