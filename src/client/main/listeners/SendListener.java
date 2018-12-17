package client.main.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.main.graphical.MainFrame;

public class SendListener implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		try {
			System.out.print(MainFrame.textArea.getText());
		}
		catch(NullPointerException e1) {
		}
	}
	
}
