package edu.ccmu.egipc.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

import edu.ccmu.egipc.form.eventlistener.ButtonListener;
import edu.ccmu.egipc.form.eventlistener.WindowListener;
import edu.ccmu.egipc.i18n.EgipcI18N;
import edu.ccmu.egipc.i18n.II18Nable;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class LoadDataFrame extends JFrame implements II18Nable{

	public final static LoadDataFrame INSTANCE = new LoadDataFrame();
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	private JButton btnOk;
	private JButton btnCancel;
	private JCheckBox chckbxEnableGwas;
	private JComboBox comboBoxGwas;
	private JTable tableGwas;
	private JCheckBox chckbxEnableSnp;
	private JComboBox comboBoxSnp;
	private JTable tableSnp;
	private JCheckBox chckbxEnableVt;
	private JComboBox comboBoxVt;
	private JTable tableVt;
	
	/**
	 * Create the frame.
	 */
	private LoadDataFrame() {
		setType(Type.UTILITY);
		setAlwaysOnTop(true);
		setName("LoadDataFrame");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(50, 50, 400, 600);
		addWindowListener(WindowListener.INSTANCE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[35px:n:35px][grow][grow][grow][35px:n:35px]"));
		
		JPanel panel1 = new JPanel();
		contentPane.add(panel1, "cell 0 0,grow");
		panel1.setLayout(new MigLayout("", "[grow][]", "[]"));
		
		textField = new JTextField();
		textField.setEditable(false);
		panel1.add(textField, "cell 0 0,growx");
		textField.setColumns(10);
		
		btnNewButton = new JButton("选择文件");
		panel1.add(btnNewButton, "cell 1 0");
		btnNewButton.setName("btnLoadData");
		btnNewButton.addActionListener(ButtonListener.INSTANCE);
		
		JPanel panel2 = new JPanel();
		contentPane.add(panel2, "cell 0 1,grow");
		panel2.setLayout(new MigLayout("", "[50px][grow]", "[][grow]"));
		
		chckbxEnableGwas = new JCheckBox("New check box");
		panel2.add(chckbxEnableGwas, "cell 0 0");
		
		comboBoxGwas = new JComboBox();
		panel2.add(comboBoxGwas, "cell 1 0,growx");
		
		tableGwas = new JTable();
		panel2.add(tableGwas, "cell 0 1 2 1,grow");
		
		panel3 = new JPanel();
		contentPane.add(panel3, "cell 0 2,grow");
		panel3.setLayout(new MigLayout("", "[50px][grow]", "[][grow]"));
		
		chckbxEnableSnp = new JCheckBox("New check box");
		panel3.add(chckbxEnableSnp, "cell 0 0");
		
		comboBoxSnp = new JComboBox();
		panel3.add(comboBoxSnp, "cell 1 0,growx");
		
		tableSnp = new JTable();
		panel3.add(tableSnp, "cell 0 1 2 1,grow");
		
		panel4 = new JPanel();
		contentPane.add(panel4, "cell 0 3,grow");
		panel4.setLayout(new MigLayout("", "[50px][grow]", "[][grow]"));
		
		chckbxEnableVt = new JCheckBox("New check box");
		panel4.add(chckbxEnableVt, "cell 0 0");
		
		comboBoxVt = new JComboBox();
		panel4.add(comboBoxVt, "cell 1 0,growx");
		
		tableVt = new JTable();
		panel4.add(tableVt, "cell 0 1 2 1,grow");
		
		panel5 = new JPanel();
		contentPane.add(panel5, "cell 0 4,grow");
		panel5.setLayout(new BorderLayout(0, 0));
		
		btnOk = new JButton("New button");
		panel5.add(btnOk, BorderLayout.WEST);
		btnOk.setName("btnLDFOk");
		btnOk.addActionListener(ButtonListener.INSTANCE);
		
		btnCancel = new JButton("New button");
		panel5.add(btnCancel, BorderLayout.EAST);
		btnCancel.setName("btnLDFCancel");
		btnCancel.addActionListener(ButtonListener.INSTANCE);
		
		EgipcI18N.addI18Nable(this);
	}

	public void i18Ning() {
		this.setTitle(EgipcI18N.getText("Form.LoadDataFrame.Title"));
		btnNewButton.setText(EgipcI18N.getText("Form.btnNewButton.Text"));
		chckbxEnableGwas.setText(EgipcI18N.getText("Form.chckbxEnableGwas.Text"));
		chckbxEnableSnp.setText(EgipcI18N.getText("Form.chckbxEnableSnp.Text"));
		chckbxEnableVt.setText(EgipcI18N.getText("Form.chckbxEnableVt.Text"));
		btnOk.setText(EgipcI18N.getText("Form.btnOk.Text"));
		btnCancel.setText(EgipcI18N.getText("Form.btnCancel.Text"));
	}
	
	public void setFile(String path)
	{
		textField.setText(path);
	}

}
