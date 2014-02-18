package edu.ccmu.egipc;

import javax.swing.JFrame;

import org.jdesktop.application.Application;

import edu.ccmu.egipc.form.MainFrame;
import edu.ccmu.egipc.form.eventlistener.WindowListener;
import edu.ccmu.egipc.i18n.EgipcI18N;

public class Egipc extends Application{

	public static Egipc application;
	public MainFrame frame;

	public static void main(String[] args) {
		Application.launch(Egipc.class, args);
	}

	@Override
	protected void startup() {
		application = this;
		EgipcI18N.init("lang");
		frame = new MainFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //We will handle the operation in WindowListener
		frame.addWindowListener(WindowListener.INSTANCE); //This is a class which extends WindowAdapter, not an interface.
		frame.setTitle("EGIPc - Entire Genome Inquiry Platform for COPD");
		EgipcI18N.reload();
		frame.setVisible(true);
	}

}
