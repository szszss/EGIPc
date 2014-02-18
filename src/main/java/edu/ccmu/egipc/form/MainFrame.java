package edu.ccmu.egipc.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import edu.ccmu.egipc.form.eventlistener.ButtonListener;
import edu.ccmu.egipc.form.eventlistener.MenuListener;
import edu.ccmu.egipc.i18n.EgipcI18N;
import edu.ccmu.egipc.i18n.II18Nable;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

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

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setName("MainFrame");
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("文件(F)");
		menuBar.add(mnFile);
		
		mnEdit = new JMenu("编辑(E)");
		menuBar.add(mnEdit);
		
		mnLang = new JMenu("语言(L)");
		menuBar.add(mnLang);
		Set<ResourceBundle> langs = EgipcI18N.resourceBundles;
		for(ResourceBundle lang : langs)
		{
			JMenuItem jMenuItem = new JMenuItem(lang.getLocale().getDisplayLanguage(lang.getLocale()));
			jMenuItem.setName("miLang");
			jMenuItem.putClientProperty("lang", lang);
			jMenuItem.addActionListener(MenuListener.INSTANCE);
			mnLang.add(jMenuItem);
		}
		
		mnHelp = new JMenu("帮助(H)");
		menuBar.add(mnHelp);
		
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
		btnLoadData.addActionListener(ButtonListener.INSTANCE);
		panel.add(btnLoadData, "cell 2 0");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, "cell 0 1,grow");
		
		EgipcI18N.addI18Nable(this);
	}

	public void i18Ning() {
		mnFile.setText(EgipcI18N.getText("Form.mnFile.Text"));
		mnEdit.setText(EgipcI18N.getText("Form.mnEdit.Text"));
		mnLang.setText(EgipcI18N.getText("Form.mnLang.Text"));
		mnHelp.setText(EgipcI18N.getText("Form.mnHelp.Text"));
	}

}
