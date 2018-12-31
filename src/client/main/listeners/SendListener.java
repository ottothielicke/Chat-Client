package client.main.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.main.Main;
import client.main.graphical.MainFrame;
import client.server.Server;

public class SendListener implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		try {
			if(Main.getNetworkInformation().isPeerToPeer())
			Server.sendText(MainFrame.getUserText());
		}
		catch(NullPointerException e1) {
		}
	}
	
}