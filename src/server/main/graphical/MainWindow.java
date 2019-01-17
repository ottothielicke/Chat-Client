package server.main.graphical;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class MainWindow {
	private JFrame frame;
	private JPanel panel;
	static boolean initialized = false;
	private boolean receiveInput;
	private JTextArea consoleOutput;
	private JTextArea consoleInput;
	private BorderLayout layout;
	private ScrollPane outputScroll;
	private ScrollPane inputScroll;
	public static boolean externalInit() {
		if(!initialized) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					MainWindow stop = new MainWindow();
					stop.init();
				}
			});
			return true;
		}
		else {
			return false;
		}
	}
	private void init() {
		frame = new JFrame("Server Console");
		panel = new JPanel();
		consoleOutput = new JTextArea();
		consoleInput = new JTextArea();
		layout = new BorderLayout();
		outputScroll = new ScrollPane();
		inputScroll = new ScrollPane();
		outputScroll.add(consoleOutput);
		inputScroll.add(consoleInput);
		frame.setContentPane(panel);
		panel.setLayout(layout);
		layout.setVgap(20);
		panel.add(outputScroll, BorderLayout.CENTER);
		panel.add(inputScroll, BorderLayout.SOUTH);
		consoleInput.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					System.out.println(consoleInput.getText());
					consoleInput.setText("");
				}
			}
			public void keyPressed(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
			}
		});
		consoleInput.setText("alright");
		frame.setPreferredSize(new Dimension(512,512));
		frame.pack();
		frame.setVisible(true);
	}
}