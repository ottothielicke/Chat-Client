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
				MainFrame.addText(Main.getNetworkInformation().getUserName() + MainFrame.getUserText());
				MainFrame.setUserText("");
				Server.sendText(MainFrame.getUserText());
			}
			catch(NullPointerException e1) {
			}
		}
		else {
			MainFrame.addText(Main.getNetworkInformation().getUserName() + MainFrame.getUserText());
			MainFrame.setUserText("");
			ConnectionHandler.sendText(MainFrame.getUserText());
		}
	}
	
}