package client.main.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.main.graphical.MainFrame;

public class SendListener implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		try {
			MainFrame.writeTextArea(MainFrame.getUserText());
		}
		catch(NullPointerException e1) {
		}
	}
	
}
