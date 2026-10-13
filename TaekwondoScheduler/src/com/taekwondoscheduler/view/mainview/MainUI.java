package com.taekwondoscheduler.view.mainview;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.taekwondoscheduler.model.business.ScheduleManager;
import com.taekwondoscheduler.view.signinview.SignInFrame;

/**
 * The main view of the application
 */
public class MainUI extends JFrame {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -8126310951739883124L;

	private JDesktopPane desktop;
	private JMenuBar menubar;
	private JMenu menu;
	private JMenuItem exit;
	private JMenuItem signout;

	public MainUI() {
		super("Mueller's Martial Arts Scheduler");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		desktop = new JDesktopPane();

		menubar = new JMenuBar();
		this.setJMenuBar(menubar);

		menu = new JMenu("File");

		exit = new JMenuItem("Exit");

		// Exits the application when exit option is pressed
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}

		});

		signout = new JMenuItem("Sign Out");

		/**
		 * Clears JInternalFrames and displays sign in page
		 */
		signout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ScheduleManager.getInstance().resetClasses();
				
				// Clears all other frames
				for (JInternalFrame frame : desktop.getAllFrames()) {

					frame.setVisible(false);
					frame.dispose();
				}

				

				// Creates a new sign in frame
				SignInFrame signIn = new SignInFrame();

				signIn.setVisible(true);
				desktop.add(signIn);
				signIn.setLocation(desktop.getWidth() / 2 - signIn.getWidth() / 2,
						desktop.getHeight() / 2 - signIn.getHeight() / 2);
			}

		});

		menu.add(signout);
		menu.add(exit);

		menubar.add(menu);

		this.getContentPane().add(desktop);

		// Creates a new sign in frame to begin application
		SignInFrame signIn = new SignInFrame();

		signIn.setVisible(true);
		desktop.add(signIn);

		this.pack();

		this.setSize(700, 500);

		this.setVisible(true);

		this.setLocationRelativeTo(null);

		signIn.setLocation(desktop.getWidth() / 2 - signIn.getWidth() / 2,
				desktop.getHeight() / 2 - signIn.getHeight() / 2);

	}

	/**
	 * The main method that starts the application
	 * 
	 * @param args the arguments for the application
	 */
	public static void main(String args[]) {

		// Special thread to help with UI
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MainUI();

			}

		});
	}

}
