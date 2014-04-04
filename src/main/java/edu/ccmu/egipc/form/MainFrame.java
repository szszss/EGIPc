package edu.ccmu.egipc.form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.miginfocom.swing.MigLayout;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import edu.ccmu.egipc.Egipc;
import edu.ccmu.egipc.data.DataGwas;
import edu.ccmu.egipc.data.DataSnp;
import edu.ccmu.egipc.data.DataVt;
import edu.ccmu.egipc.i18n.EgipcI18N;
import edu.ccmu.egipc.i18n.II18Nable;
import edu.ccmu.egipc.xml.XmlWork;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import jxl.Sheet;

public class MainFrame extends JFrame implements II18Nable{

	public JPanel contentPane;
	public JLabel lblDataFile;
	public JTextField tfDataFile;
	public JButton btnLoadData;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnEdit;
	private JMenu mnLang;
	private JMenu mnHelp;
	private JTabbedPane tabbedPane;
	private SNPInquiryPanel panelGwas = null;
	private RSInquiryPanel panelSnp = null;
	private VTInquiryPanel panelVt = null;
	private JMenuItem jMenuItemLoad;
	
	private MyListener listener = new MyListener();

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setName("MainFrame");
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("文件(F)");
		menuBar.add(mnFile);
		jMenuItemLoad = new JMenuItem(EgipcI18N.getText("Form.Load.Text"));
		jMenuItemLoad.addActionListener(listener);
		mnFile.add(jMenuItemLoad);
		
		mnEdit = new JMenu("编辑(E)");
		//menuBar.add(mnEdit);
		
		mnLang = new JMenu("语言(L)");
		menuBar.add(mnLang);
		Set<ResourceBundle> langs = EgipcI18N.resourceBundles;
		for(ResourceBundle lang : langs)
		{
			JMenuItem jMenuItem = new JMenuItem(lang.getLocale().getDisplayLanguage(lang.getLocale()));
			jMenuItem.setName("miLang");
			jMenuItem.putClientProperty("lang", lang);
			jMenuItem.addActionListener(listener);
			mnLang.add(jMenuItem);
		}
		
		mnHelp = new JMenu("帮助(H)");
		//menuBar.add(mnHelp);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[35px:n:35px][grow]"));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[][grow][]", "[]"));
		
		lblDataFile = new JLabel("数据文件");
		panel.add(lblDataFile, "cell 0 0,alignx trailing");
		
		tfDataFile = new JTextField();
		tfDataFile.setEditable(false);
		panel.add(tfDataFile, "cell 1 0,growx");
		tfDataFile.setColumns(10);
		
		btnLoadData = new JButton("加载数据");
		btnLoadData.setName("btnLoadData");
		btnLoadData.addActionListener(listener);
		panel.add(btnLoadData, "cell 2 0");
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, "cell 0 1,grow");
		
		EgipcI18N.addI18Nable(this);
	}

	public void i18Ning() {
		mnFile.setText(EgipcI18N.getText("Form.mnFile.Text"));
		mnEdit.setText(EgipcI18N.getText("Form.mnEdit.Text"));
		mnLang.setText(EgipcI18N.getText("Form.mnLang.Text"));
		mnHelp.setText(EgipcI18N.getText("Form.mnHelp.Text"));
		jMenuItemLoad.setText(EgipcI18N.getText("Form.Load.Text"));
		btnLoadData.setText(EgipcI18N.getText("Form.Load.Text"));
		lblDataFile.setText(EgipcI18N.getText("Form.Data.Text"));
		if(panelGwas!=null)
			tabbedPane.setTitleAt(tabbedPane.indexOfComponent(panelGwas), EgipcI18N.getText("Form.SNPInquiryPanel.Title"));
		if(panelSnp!=null)
			tabbedPane.setTitleAt(tabbedPane.indexOfComponent(panelSnp), EgipcI18N.getText("Form.RSInquiryPanel.Title"));
		if(panelVt!=null)
			tabbedPane.setTitleAt(tabbedPane.indexOfComponent(panelVt), EgipcI18N.getText("Form.VTInquiryPanel.Title"));
	}
	
	public void loadData(String path,DataGwas dataGwas, DataSnp dataSnp, DataVt dataVt) {
		tfDataFile.setText(path);
		tabbedPane.removeAll();
		if(dataGwas!=null){
			if(panelGwas==null)
			{
				panelGwas = new SNPInquiryPanel();
			}
			tabbedPane.add(EgipcI18N.getText("Form.SNPInquiryPanel.Title"),panelGwas);
			panelGwas.loadData(dataGwas);
		}
		if(dataSnp!=null){
			if(panelSnp==null)
			{
				panelSnp = new RSInquiryPanel();
			}
			tabbedPane.add(EgipcI18N.getText("Form.RSInquiryPanel.Title"),panelSnp);
			panelSnp.loadData(dataSnp);
		}
		if(dataVt!=null)
		{
			if(panelVt==null)
			{
				panelVt = new VTInquiryPanel();
			}
			tabbedPane.add(EgipcI18N.getText("Form.VTInquiryPanel.Title"),panelVt);
			panelVt.loadData(dataVt);
		}
	}
	
	private class MyListener implements ActionListener {
		
		private void load()
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
			XmlWork xmlWork;
			try {
				xmlWork = new XmlWork(new File(path));
			} catch (Exception e1) {
				e1.printStackTrace();
				xmlWork = null;
			}
			LoadDataFrame.INSTANCE.setFile(path);
			LoadDataFrame.INSTANCE.setXmlWork(xmlWork);
			LoadDataFrame.INSTANCE.setVisible(true);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnLoadData)
			{
				load();
			}
			else if(e.getSource()==jMenuItemLoad)
			{
				load();
			}
			else if(((Component)e.getSource()).getName().equals("miLang"))
			{
				EgipcI18N.setLang((ResourceBundle)((JMenuItem)e.getSource()).getClientProperty("lang"));
			}
		}
	}

}
