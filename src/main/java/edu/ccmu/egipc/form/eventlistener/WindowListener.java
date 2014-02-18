package edu.ccmu.egipc.form.eventlistener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import edu.ccmu.egipc.Egipc;

public class WindowListener extends WindowAdapter{
	
	public final static WindowListener INSTANCE = new WindowListener();
	
	private WindowListener(){}
	
	@Override
	public void windowClosing(WindowEvent e) {
		if(e.getWindow().getName().equals("LoadDataFrame"))
		{
			e.getWindow().setVisible(false);
		}
		else if(e.getWindow().getName().equals("MainFrame"))
		{
			Egipc.application.exit();
		}
		
	}
	
}
