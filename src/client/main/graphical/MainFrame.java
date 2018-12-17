package client.main.graphical;

import java.awt.Frame;

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
	public static JTextArea textArea;
	private static Container container;
	private static JFrame frame;
	protected static void initializeFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initializePanel();
		frame.setContentPane(container);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		System.out.println(frame.getToolkit().getScreenSize());
		textArea = new JTextArea();
		JButton print = new JButton("print");
		print.addActionListener(new SendListener());
		frame.add(textArea);
		frame.add(print);
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
}
