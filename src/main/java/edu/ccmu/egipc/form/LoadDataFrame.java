package edu.ccmu.egipc.form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.miginfocom.swing.MigLayout;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.ListSelectionModel;

import edu.ccmu.egipc.Egipc;
import edu.ccmu.egipc.data.DataGwas;
import edu.ccmu.egipc.data.DataSnp;
import edu.ccmu.egipc.data.DataVt;
import edu.ccmu.egipc.form.eventlistener.WindowListener;
import edu.ccmu.egipc.i18n.EgipcI18N;
import edu.ccmu.egipc.i18n.II18Nable;
import edu.ccmu.egipc.xml.CellsWrapped;
import edu.ccmu.egipc.xml.XmlWork;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import jxl.Cell;
import jxl.Sheet;
import jxl.biff.drawing.ComboBox;

import javax.swing.JScrollPane;

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
	public JComboBox<Sheet> comboBoxGwas;
	public JTable tableGwas;
	private JCheckBox chckbxEnableSnp;
	public JComboBox<Sheet> comboBoxSnp;
	public JTable tableSnp;
	private JCheckBox chckbxEnableVt;
	public JComboBox<Sheet> comboBoxVt;
	public JTable tableVt;
	private XmlWork xmlWork;
	private JLabel lblInfo;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private Map<Sheet, JComboBox> comboboxMap;
	
	private MyListener listener = new MyListener();
	
	/**
	 * Create the frame.
	 */
	private LoadDataFrame() {
		setType(Type.UTILITY);
		setAlwaysOnTop(true);
		setName("LoadDataFrame");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(50, 50, 400, 650);
		addWindowListener(WindowListener.INSTANCE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[65px:n:65px][grow][grow][grow][35px:n:35px]"));
		
		JPanel panel1 = new JPanel();
		contentPane.add(panel1, "cell 0 0,grow");
		panel1.setLayout(new MigLayout("", "[grow][]", "[][]"));
		
		textField = new JTextField();
		textField.setEditable(false);
		panel1.add(textField, "cell 0 0,growx");
		textField.setColumns(10);
		
		btnNewButton = new JButton("选择文件");
		panel1.add(btnNewButton, "cell 1 0");
		btnNewButton.setName("btnLoadData");
		
		lblInfo = new JLabel("");
		panel1.add(lblInfo, "cell 0 1 2 1");
		btnNewButton.addActionListener(listener);
		
		JPanel panel2 = new JPanel();
		contentPane.add(panel2, "cell 0 1,grow");
		panel2.setLayout(new MigLayout("", "[50px][grow]", "[][100px,grow]"));
		
		chckbxEnableGwas = new JCheckBox("New check box");
		panel2.add(chckbxEnableGwas, "cell 0 0");
		chckbxEnableGwas.addActionListener(listener);
		
		comboBoxGwas = new JComboBox();
		comboBoxGwas.setName("comboBoxGwas");
		comboBoxGwas.addItemListener(listener);
		panel2.add(comboBoxGwas, "cell 1 0,growx");
		
		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		panel2.add(scrollPane, "flowx,cell 0 1 2 1");
		
		tableGwas = new JTable();
		tableGwas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					EgipcI18N.getText("Form.Item.Text"), EgipcI18N.getText("Form.Source.Text")
			}
		));
		tableGwas.addPropertyChangeListener(listener);
		//tableGwas.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBox));
		scrollPane.setViewportView(tableGwas);
		
		panel3 = new JPanel();
		contentPane.add(panel3, "cell 0 2,grow");
		panel3.setLayout(new MigLayout("", "[50px][grow]", "[][100px,grow]"));
		
		chckbxEnableSnp = new JCheckBox("New check box");
		panel3.add(chckbxEnableSnp, "cell 0 0");
		chckbxEnableSnp.addActionListener(listener);
		
		comboBoxSnp = new JComboBox();
		comboBoxSnp.setName("comboBoxSnp");
		comboBoxSnp.addItemListener(listener);
		panel3.add(comboBoxSnp, "cell 1 0,growx");
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		panel3.add(scrollPane_1, "flowx,cell 0 1 2 1");
		
		tableSnp = new JTable();
		tableSnp.setModel(new DefaultTableModel(new Object[][] {},new String[] {
				EgipcI18N.getText("Form.Item.Text"), EgipcI18N.getText("Form.Source.Text")}));
		//tableSnp.getColumnModel().getColumn(1).setCellEditor(new CustomComboBoxEditor());
		tableSnp.addPropertyChangeListener(listener);
		scrollPane_1.setViewportView(tableSnp);
		
		panel4 = new JPanel();
		contentPane.add(panel4, "cell 0 3,grow");
		panel4.setLayout(new MigLayout("", "[50px][grow]", "[][100px,grow]"));
		
		chckbxEnableVt = new JCheckBox("New check box");
		panel4.add(chckbxEnableVt, "cell 0 0");
		chckbxEnableVt.addActionListener(listener);
		
		comboBoxVt = new JComboBox();
		comboBoxVt.setName("comboBoxVt");
		comboBoxVt.addItemListener(listener);
		panel4.add(comboBoxVt, "cell 1 0,growx");
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setEnabled(false);
		panel4.add(scrollPane_2, "flowx,cell 0 1 2 1");
		
		tableVt = new JTable();
		scrollPane_2.setViewportView(tableVt);
		tableVt.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					EgipcI18N.getText("Form.Item.Text"), EgipcI18N.getText("Form.Source.Text")
			}
		));
		tableVt.addPropertyChangeListener(listener);
		//tableVt.getColumnModel().getColumn(1).setCellEditor(new CustomComboBoxEditor());
		
		panel5 = new JPanel();
		contentPane.add(panel5, "cell 0 4,grow");
		panel5.setLayout(new BorderLayout(0, 0));
		
		btnOk = new JButton("New button");
		panel5.add(btnOk, BorderLayout.WEST);
		btnOk.setName("btnLDFOk");
		btnOk.addActionListener(listener);
		
		btnCancel = new JButton("New button");
		panel5.add(btnCancel, BorderLayout.EAST);
		btnCancel.setName("btnLDFCancel");
		btnCancel.addActionListener(listener);
		
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

	public void setXmlWork(XmlWork xml)
	{
		comboboxMap = new HashMap<Sheet,JComboBox>();
		xmlWork = xml;
		btnOk.setEnabled(false);
		comboBoxGwas.removeAllItems();
		comboBoxSnp.removeAllItems();
		comboBoxVt.removeAllItems();
		/*comboBoxGwas.removeItemListener(listener);
		comboBoxSnp.removeItemListener(listener);
		comboBoxVt.removeItemListener(listener);
		tableGwas.removePropertyChangeListener(listener);
		tableSnp.removePropertyChangeListener(listener);
		tableVt.removePropertyChangeListener(listener);*/
		if(xml!=null)
		{
			lblInfo.setText("");
			Collection<Sheet> sheets = xmlWork.getAllSheet();
			for(Sheet sheet : sheets)
			{
				JComboBox comboBox = new JComboBox();
				for(int j=0;j<sheet.getColumns();j++)
				{
					comboBox.addItem(new CellsWrapped(sheet.getColumn(j)));
				}
				comboboxMap.put(sheet, comboBox);
				comboBoxGwas.addItem(sheet);
				comboBoxSnp.addItem(sheet);
				comboBoxVt.addItem(sheet);
			}
			chckbxEnableGwas.setSelected(false);
			chckbxEnableSnp.setSelected(false);
			chckbxEnableVt.setSelected(false);
			//comboBoxGwas.addItemListener(listener);
			//comboBoxSnp.addItemListener(listener);
			//comboBoxVt.addItemListener(listener);
			switch (sheets.size()) {
			default:
			case 3:
				comboBoxVt.setSelectedIndex(2);
				chckbxEnableVt.setSelected(true);
			case 2:
				comboBoxSnp.setSelectedIndex(1);
				chckbxEnableSnp.setSelected(true);
			case 1:
				comboBoxGwas.setSelectedIndex(0);
				chckbxEnableGwas.setSelected(true);
			case 0:
				break;
			}
		}
		else 
		{
			lblInfo.setText(EgipcI18N.getText("Form.lblInfo.Text"));
		}
		//tableGwas.addPropertyChangeListener(listener);
		//tableSnp.addPropertyChangeListener(listener);
		//tableVt.addPropertyChangeListener(listener);
		btnOk.setEnabled(check());
	}
	
	private boolean check()
	{
		try {
			DefaultTableModel target = (DefaultTableModel)tableGwas.getModel();
			if( chckbxEnableGwas.isSelected()&&
				(target == null||
				target.getValueAt(0, 1)==null||
				target.getValueAt(1, 1)==null||
				target.getValueAt(2, 1)==null||
				target.getValueAt(3, 1)==null||
				target.getValueAt(4, 1)==null||
				target.getValueAt(5, 1)==null||
				target.getValueAt(6, 1)==null))
				return false;
			target = (DefaultTableModel)tableSnp.getModel();
			if( chckbxEnableSnp.isSelected()&&
				(target == null||
				target.getValueAt(0, 1)==null||
				target.getValueAt(1, 1)==null||
				target.getValueAt(2, 1)==null||
				target.getValueAt(3, 1)==null||
				target.getValueAt(4, 1)==null))
				return false;
			target = (DefaultTableModel)tableVt.getModel();
			if( chckbxEnableVt.isSelected()&&
				(target == null||
				target.getValueAt(0, 1)==null||
				target.getValueAt(1, 1)==null||
				target.getValueAt(2, 1)==null))
				return false;
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public DataGwas getDataGwas()
	{
		DefaultTableModel target = (DefaultTableModel)tableGwas.getModel();
		return new DataGwas(((CellsWrapped)target.getValueAt(0, 1)).cells, 
				((CellsWrapped)target.getValueAt(1, 1)).cells, 
				((CellsWrapped)target.getValueAt(2, 1)).cells, 
				((CellsWrapped)target.getValueAt(3, 1)).cells, 
				((CellsWrapped)target.getValueAt(4, 1)).cells, 
				((CellsWrapped)target.getValueAt(5, 1)).cells, 
				((CellsWrapped)target.getValueAt(6, 1)).cells);
		//Sheet sheet = (Sheet)LoadDataFrame.INSTANCE.comboBoxGwas.getSelectedItem();
		//return new DataGwas(sheet.getColumn(0), sheet.getColumn(1), sheet.getColumn(2), 
		//		sheet.getColumn(3), sheet.getColumn(4), sheet.getColumn(5), sheet.getColumn(6));
	}
	
	public DataSnp getDataSnp()
	{
		DefaultTableModel target = (DefaultTableModel)tableSnp.getModel();
		return new DataSnp(((CellsWrapped)target.getValueAt(0, 1)).cells, 
				((CellsWrapped)target.getValueAt(1, 1)).cells, 
				((CellsWrapped)target.getValueAt(2, 1)).cells, 
				((CellsWrapped)target.getValueAt(3, 1)).cells, 
				((CellsWrapped)target.getValueAt(4, 1)).cells);
		//Sheet sheet = (Sheet)LoadDataFrame.INSTANCE.comboBoxSnp.getSelectedItem();
		//return new DataSnp(sheet.getColumn(0), sheet.getColumn(1), sheet.getColumn(2), 
		//		sheet.getColumn(3), sheet.getColumn(4));
	}
	
	public DataVt getDataVt()
	{
		DefaultTableModel target = (DefaultTableModel)tableVt.getModel();
		return new DataVt(((CellsWrapped)target.getValueAt(0, 1)).cells, 
				((CellsWrapped)target.getValueAt(1, 1)).cells, 
				((CellsWrapped)target.getValueAt(2, 1)).cells);
		//Sheet sheet = (Sheet)LoadDataFrame.INSTANCE.comboBoxVt.getSelectedItem();
		//return new DataVt(sheet.getColumn(0), sheet.getColumn(1), sheet.getColumn(2));
	}
	
	private class MyListener implements ActionListener,ItemListener,PropertyChangeListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnOk)
			{
				Egipc.application.frame.loadData(textField.getText(),
						chckbxEnableGwas.isSelected()?getDataGwas():null,
						chckbxEnableSnp.isSelected()?getDataSnp():null,
						chckbxEnableVt.isSelected()?getDataVt():null);
				LoadDataFrame.INSTANCE.setVisible(false);
			}
			else if(e.getSource()==btnCancel)
			{
				LoadDataFrame.INSTANCE.setVisible(false);
			}
			else {
				btnOk.setEnabled(check());
			}
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			Object source = e.getSource();
			if(e.getStateChange()!=ItemEvent.SELECTED)
				return;
			if(source == comboBoxGwas)
			{
				JTable target = tableGwas;
				Sheet sheet = (Sheet)e.getItem();
				target.setModel(new DefaultTableModel(new Object[][] {},new String[] {
						EgipcI18N.getText("Form.Item.Text"), EgipcI18N.getText("Form.Source.Text")}));
				JComboBox jComboBox = comboboxMap.get(sheet);
				((DefaultTableModel)target.getModel()).addRow(new Object[]{EgipcI18N.getText("Form.lblId.Text"),jComboBox.getItemAt(0)});
				((DefaultTableModel)target.getModel()).addRow(new Object[]{EgipcI18N.getText("Form.lblChromosome.Text"),jComboBox.getItemAt(1)});
				((DefaultTableModel)target.getModel()).addRow(new Object[]{EgipcI18N.getText("Form.lblRiskAllele.Text"),jComboBox.getItemAt(2)});
				((DefaultTableModel)target.getModel()).addRow(new Object[]{EgipcI18N.getText("Form.lblPValue.Text"),jComboBox.getItemAt(3)});
				((DefaultTableModel)target.getModel()).addRow(new Object[]{EgipcI18N.getText("Form.lblORValue.Text"),jComboBox.getItemAt(4)});
				((DefaultTableModel)target.getModel()).addRow(new Object[]{EgipcI18N.getText("Form.lblFloorValue.Text"),jComboBox.getItemAt(5)});
				((DefaultTableModel)target.getModel()).addRow(new Object[]{EgipcI18N.getText("Form.lblCeilingValue.Text"),jComboBox.getItemAt(6)});
				target.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(jComboBox));
			}
			else if(source == comboBoxSnp)
			{
				JTable target = tableSnp;
				Sheet sheet = (Sheet)e.getItem();
				target.setModel(new DefaultTableModel(new Object[][] {},new String[] {
						EgipcI18N.getText("Form.Item.Text"), EgipcI18N.getText("Form.Source.Text")}));
				JComboBox jComboBox = comboboxMap.get(sheet);
				((DefaultTableModel)target.getModel()).addRow(new Object[]{EgipcI18N.getText("Form.lblRsID.Text"),jComboBox.getItemAt(0)});
				((DefaultTableModel)target.getModel()).addRow(new Object[]{EgipcI18N.getText("Form.lblGeneSymbol.Text"),jComboBox.getItemAt(1)});
				((DefaultTableModel)target.getModel()).addRow(new Object[]{EgipcI18N.getText("Form.lblSift.Text"),jComboBox.getItemAt(2)});
				((DefaultTableModel)target.getModel()).addRow(new Object[]{EgipcI18N.getText("Form.lblPolyPhen.Text"),jComboBox.getItemAt(3)});
				((DefaultTableModel)target.getModel()).addRow(new Object[]{EgipcI18N.getText("Form.lblVarioWatch.Text"),jComboBox.getItemAt(4)});
				target.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(jComboBox));
			}
			else if(source == comboBoxVt)
			{
				JTable target = tableVt;
				Sheet sheet = (Sheet)e.getItem();
				target.setModel(new DefaultTableModel(new Object[][] {},new String[] {
						EgipcI18N.getText("Form.Item.Text"), EgipcI18N.getText("Form.Source.Text")}));
				JComboBox jComboBox = comboboxMap.get(sheet);
				((DefaultTableModel)target.getModel()).addRow(new Object[]{EgipcI18N.getText("Form.lblRsID.Text"),jComboBox.getItemAt(0)});
				((DefaultTableModel)target.getModel()).addRow(new Object[]{EgipcI18N.getText("Form.lblSymbol.Text"),jComboBox.getItemAt(1)});
				((DefaultTableModel)target.getModel()).addRow(new Object[]{EgipcI18N.getText("Form.lblBasedVT.Text"),jComboBox.getItemAt(2)});
				target.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(jComboBox));
			}
			else {
				
			}
		}

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			btnOk.setEnabled(check());
		}		
	}
}
