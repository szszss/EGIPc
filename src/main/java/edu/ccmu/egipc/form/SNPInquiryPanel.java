package edu.ccmu.egipc.form;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import jxl.Cell;
import jxl.Sheet;

import edu.ccmu.egipc.data.DataGwas;
import edu.ccmu.egipc.i18n.EgipcI18N;
import edu.ccmu.egipc.i18n.II18Nable;
import edu.ccmu.egipc.xml.XmlWork;

public class SNPInquiryPanel extends JPanel implements II18Nable{
	private JTable table;
	private JTextField tfID;
	private JTextField tfChromosome;
	private JTextField tfRiskAllele;
	private JTextField tfPValue;
	private JTextField tfORValue;
	private JTextField tfFloorValue;
	private JTextField tfCeilingValue;
	private JButton btnInquiry;
	private JLabel lblId;
	private JLabel lblChromosome;
	private JLabel lblRiskAllele;
	private JLabel lblPValue;
	private JLabel lblORValue;
	private JLabel lblFloorValue;
	private JLabel lblCeilingValue;
	
	private MyListener listener = new MyListener();
	private DataGwas data;
	private JLabel lblResult;

	/**
	 * Create the panel.
	 */
	public SNPInquiryPanel() {
		setLayout(new MigLayout("", "[200px:200px:300px,grow][grow]", "[grow]"));
		
		JPanel panel = new JPanel();
		add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[grow][grow]", "[][][][][][][][][][]"));
		
		lblId = new JLabel("ID");
		panel.add(lblId, "cell 0 0");
		
		tfID = new JTextField();
		panel.add(tfID, "cell 0 1 2 1,growx");
		tfID.setColumns(10);
		
		btnInquiry = new JButton("New button");
		btnInquiry.addActionListener(listener);
		panel.add(btnInquiry, "cell 0 2 2 1");
		
		lblChromosome = new JLabel("New label");
		panel.add(lblChromosome, "cell 0 3,alignx left");
		
		tfChromosome = new JTextField();
		panel.add(tfChromosome, "cell 1 3,growx");
		tfChromosome.setColumns(10);
		
		lblRiskAllele = new JLabel("New label");
		panel.add(lblRiskAllele, "cell 0 4,alignx left");
		
		tfRiskAllele = new JTextField();
		panel.add(tfRiskAllele, "cell 1 4,growx");
		tfRiskAllele.setColumns(10);
		
		lblPValue = new JLabel("New label");
		panel.add(lblPValue, "cell 0 5,alignx left");
		
		tfPValue = new JTextField();
		panel.add(tfPValue, "cell 1 5,growx");
		tfPValue.setColumns(10);
		
		lblORValue = new JLabel("New label");
		panel.add(lblORValue, "cell 0 6,alignx left");
		
		tfORValue = new JTextField();
		panel.add(tfORValue, "cell 1 6,growx");
		tfORValue.setColumns(10);
		
		lblFloorValue = new JLabel("New label");
		panel.add(lblFloorValue, "cell 0 7,alignx left");
		
		tfFloorValue = new JTextField();
		panel.add(tfFloorValue, "cell 1 7,growx");
		tfFloorValue.setColumns(10);
		
		lblCeilingValue = new JLabel("New label");
		panel.add(lblCeilingValue, "cell 0 8,alignx left");
		
		tfCeilingValue = new JTextField();
		panel.add(tfCeilingValue, "cell 1 8,growx");
		tfCeilingValue.setColumns(10);
		
		lblResult = new JLabel("");
		panel.add(lblResult, "cell 0 9 2 1");
		
		JPanel panel_1 = new JPanel();
		add(panel_1, "cell 1 0,grow");
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);

		EgipcI18N.addI18Nable(this);
	}

	public void i18Ning() {
		btnInquiry.setText(EgipcI18N.getText("Form.btnInquiry.Text"));
		lblId.setText(EgipcI18N.getText("Form.lblId.Text"));
		lblChromosome.setText(EgipcI18N.getText("Form.lblChromosome.Text"));
		lblRiskAllele.setText(EgipcI18N.getText("Form.lblRiskAllele.Text"));
		lblPValue.setText(EgipcI18N.getText("Form.lblPValue.Text"));
		lblORValue.setText(EgipcI18N.getText("Form.lblORValue.Text"));
		lblFloorValue.setText(EgipcI18N.getText("Form.lblFloorValue.Text"));
		lblCeilingValue.setText(EgipcI18N.getText("Form.lblCeilingValue.Text"));
		i18Ning_2();
		//table.getColumnModel().getColumns().nextElement().
	}
	
	public void loadData(DataGwas dataGwas){
		data = dataGwas;
		table.setModel(dataGwas.tableModel);
		i18Ning_2();
	}
	
	private void i18Ning_2()
	{
		Enumeration<TableColumn> enumeration = table.getColumnModel().getColumns();
		enumeration.nextElement().setHeaderValue(EgipcI18N.getText("Form.lblId.Text"));
		enumeration.nextElement().setHeaderValue(EgipcI18N.getText("Form.lblChromosome.Text"));
		enumeration.nextElement().setHeaderValue(EgipcI18N.getText("Form.lblRiskAllele.Text"));
		enumeration.nextElement().setHeaderValue(EgipcI18N.getText("Form.lblPValue.Text"));
		enumeration.nextElement().setHeaderValue(EgipcI18N.getText("Form.lblORValue.Text"));
		enumeration.nextElement().setHeaderValue(EgipcI18N.getText("Form.lblFloorValue.Text"));
		enumeration.nextElement().setHeaderValue(EgipcI18N.getText("Form.lblCeilingValue.Text"));
	}
	
	private class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnInquiry)
			{
				String[] result = data.getData(tfID.getText());
				if(result==null)
				{
					lblResult.setText(EgipcI18N.getText("Form.SNPInquiryPanel.NotFound")+" "+tfID.getText());
					tfChromosome.setText("");
					tfRiskAllele.setText("");
					tfPValue.setText("");
					tfORValue.setText("");
					tfFloorValue.setText("");
					tfCeilingValue.setText("");
				}
				else
				{
					lblResult.setText("");
					tfChromosome.setText(result[0]);
					tfRiskAllele.setText(result[1]);
					tfPValue.setText(result[2]);
					tfORValue.setText(result[3]);
					tfFloorValue.setText(result[4]);
					tfCeilingValue.setText(result[5]);
				}
			}
		}
		
	}

}
