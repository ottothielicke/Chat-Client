package client.main.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.main.Main;
import client.main.graphical.MainFrame;
import client.server.ConnectionHandler;
import client.server.Server;

public class SendListener implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		if(MainFrame.isPeerToPeer) {
			try {
				MainFrame.addText(Main.getNetworkInformation().getUserName() + ": " + MainFrame.getUserText());
				Server.sendText(MainFrame.getUserText());
				MainFrame.setUserText("");
			}
			catch(NullPointerException e1) {
			}
		}
		else {
			MainFrame.addText(Main.getNetworkInformation().getUserName() + ": " + MainFrame.getUserText());
			ConnectionHandler.sendText(MainFrame.getUserText());
			MainFrame.setUserText("");
		}
	}
	
}