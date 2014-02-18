package edu.ccmu.egipc.form.eventlistener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.ccmu.egipc.Egipc;
import edu.ccmu.egipc.form.LoadDataFrame;

public class ButtonListener implements ActionListener{

	public final static ButtonListener INSTANCE = new ButtonListener();
	
	private ButtonListener(){}
	
	public void actionPerformed(ActionEvent e) {
		String name = ((Component)e.getSource()).getName();
		if(name.equals("btnLoadData"))
		{
			JFileChooser fileChooser = null;
			String path = null;
			try {
				fileChooser = new JFileChooser(System.getProperty("user.dir"));
			} catch (Exception e2) {
				fileChooser = new JFileChooser();
			}
			try {
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setFileFilter(new FileNameExtensionFilter("数据文件(xls)", "xls"));
				fileChooser.setDialogTitle("");
				if (fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
				{  
		            path = fileChooser.getSelectedFile().getAbsolutePath();
		        }
			} catch (Exception e2) {
				// TODO: handle exception
			}
			if(path==null)
				return;
			LoadDataFrame.INSTANCE.setFile(path);
			LoadDataFrame.INSTANCE.setVisible(true);
		}
		else if(name.equals("btnLDFOk"))
		{
			LoadDataFrame.INSTANCE.setVisible(false);
		}
		else if(name.equals("btnLDFCancel"))
		{
			LoadDataFrame.INSTANCE.setVisible(false);
		}
	}
}
