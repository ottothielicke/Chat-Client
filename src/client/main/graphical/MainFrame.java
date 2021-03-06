package client.main.graphical;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import client.main.listeners.SendListener;

public class MainFrame {
	public static void externalInit() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainFrame.initializeFrame();
			}
		});
	}
	public static JTextArea userTextArea;
	private static JTextArea outputTextArea;
	private static Container container;
	private static Container textContainer;
	private static JFrame frame;
	private static BorderLayout layout;
	protected static void initializeFrame() {
		outputTextArea = new JTextArea(100, 100);
		textContainer = new Container();
		layout = new BorderLayout(10, 10);
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initializePanel();
		frame.setContentPane(container);
		container.setLayout(layout);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		userTextArea = new JTextArea();
		JButton print = new JButton("print");
		print.addActionListener(new SendListener());
		container.add(outputTextArea, BorderLayout.CENTER);
		container.add(textContainer, BorderLayout.SOUTH);
		textContainer.add(print);
		textContainer.add(userTextArea);
		userTextArea.setColumns(100);
		userTextArea.setRows(10);
		userTextArea.setLineWrap(true);
		//frame.pack();
		frame.setVisible(true);
	}
	/* Elements to be added on startup for content pane should
	 * be added to this method. Any objects that are being added
	 * during runtime should either be on the glass pane. Furthermore,
	 * backgrounds should be drawn on the root pane.
	 */ 
	protected static void initializePanel() { 
		container = new Container();
	}
	public static void writeTextArea(String input) {
		outputTextArea.setText(input);
	}
	public static String getUserText() {
		return userTextArea.getText();
	}
}
