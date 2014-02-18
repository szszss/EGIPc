package edu.ccmu.egipc.form.eventlistener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.ccmu.egipc.form.LoadDataFrame;
import edu.ccmu.egipc.i18n.EgipcI18N;

public class MenuListener  implements ActionListener{

public final static MenuListener INSTANCE = new MenuListener();
	
	private MenuListener(){}
	
	public void actionPerformed(ActionEvent e) {
		String name = ((Component)e.getSource()).getName();
		if(name.equals("miLang"))
		{
			EgipcI18N.setLang((ResourceBundle)((JMenuItem)e.getSource()).getClientProperty("lang"));
		}
	}
}
