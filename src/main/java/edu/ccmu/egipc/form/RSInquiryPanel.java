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
import edu.ccmu.egipc.data.DataSnp;
import edu.ccmu.egipc.i18n.EgipcI18N;
import edu.ccmu.egipc.i18n.II18Nable;
import edu.ccmu.egipc.xml.XmlWork;

public class RSInquiryPanel extends JPanel implements II18Nable{
	private JTable table;
	private JTextField tfRsID;
	private JTextField tfGeneSymbol;
	private JTextField tfSift;
	private JTextField tfPolyPhen;
	private JTextField tfVarioWatch;
	private JButton btnInquiry;
	private JLabel lblRsID;
	private JLabel lblGeneSymbol;
	private JLabel lblSift;
	private JLabel lblPolyPhen;
	private JLabel lblVarioWatch;
	private JLabel lblResult;
	private JButton btnVarioWatch;
	
	private MyListener listener = new MyListener();
	private DataSnp data;

	/**
	 * Create the panel.
	 */
	public RSInquiryPanel() {
		setLayout(new MigLayout("", "[200px:200px:300px,grow][grow]", "[grow]"));
		
		JPanel panel = new JPanel();
		add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[grow][grow]", "[][][][][][][][][][]"));
		
		lblRsID = new JLabel("ID");
		panel.add(lblRsID, "cell 0 0");
		
		tfRsID = new JTextField();
		panel.add(tfRsID, "cell 0 1 2 1,growx");
		tfRsID.setColumns(10);
		
		btnInquiry = new JButton("New button");
		btnInquiry.addActionListener(listener);
		panel.add(btnInquiry, "cell 0 2 2 1");
		
		lblGeneSymbol = new JLabel("New label");
		panel.add(lblGeneSymbol, "cell 0 3,alignx left");
		
		tfGeneSymbol = new JTextField();
		panel.add(tfGeneSymbol, "cell 1 3,growx");
		tfGeneSymbol.setColumns(10);
		
		lblSift = new JLabel("New label");
		panel.add(lblSift, "cell 0 4,alignx left");
		
		tfSift = new JTextField();
		panel.add(tfSift, "cell 1 4,growx");
		tfSift.setColumns(10);
		
		lblPolyPhen = new JLabel("New label");
		panel.add(lblPolyPhen, "cell 0 5,alignx left");
		
		tfPolyPhen = new JTextField();
		panel.add(tfPolyPhen, "cell 1 5,growx");
		tfPolyPhen.setColumns(10);
		
		lblVarioWatch = new JLabel("New label");
		panel.add(lblVarioWatch, "cell 0 6,alignx left");
		
		tfVarioWatch = new JTextField();
		panel.add(tfVarioWatch, "cell 1 6,growx");
		tfVarioWatch.setColumns(10);

		lblResult = new JLabel("");
		panel.add(lblResult, "cell 0 8 2 1");
		
		btnVarioWatch = new JButton("New button");
		panel.add(btnVarioWatch, "cell 0 9 2 1");
		btnVarioWatch.addActionListener(listener);
		
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
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);

		EgipcI18N.addI18Nable(this);
	}

	public void i18Ning() {
		btnInquiry.setText(EgipcI18N.getText("Form.btnInquiry.Text"));
		lblRsID.setText(EgipcI18N.getText("Form.lblRsID.Text"));
		lblGeneSymbol.setText(EgipcI18N.getText("Form.lblGeneSymbol.Text"));
		lblSift.setText(EgipcI18N.getText("Form.lblSift.Text"));
		lblPolyPhen.setText(EgipcI18N.getText("Form.lblPolyPhen.Text"));
		lblVarioWatch.setText(EgipcI18N.getText("Form.lblVarioWatch.Text"));
		btnVarioWatch.setText(EgipcI18N.getText("Form.btnShowVW.Text"));
		i18Ning_2();
		//table.getColumnModel().getColumns().nextElement().
	}
	
	public void loadData(DataSnp dataGwas){
		data = dataGwas;
		table.setModel(dataGwas.tableModel);
		i18Ning_2();
	}
	
	private void i18Ning_2()
	{
		Enumeration<TableColumn> enumeration = table.getColumnModel().getColumns();
		enumeration.nextElement().setHeaderValue(EgipcI18N.getText("Form.lblRsID.Text"));
		enumeration.nextElement().setHeaderValue(EgipcI18N.getText("Form.lblGeneSymbol.Text"));
		enumeration.nextElement().setHeaderValue(EgipcI18N.getText("Form.lblSift.Text"));
		enumeration.nextElement().setHeaderValue(EgipcI18N.getText("Form.lblPolyPhen.Text"));
		enumeration.nextElement().setHeaderValue(EgipcI18N.getText("Form.lblVarioWatch.Text"));
	}
	
	private class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnInquiry)
			{
				String[] result = data.getData(tfRsID.getText());
				if(result==null)
				{
					lblResult.setText(EgipcI18N.getText("Form.RSInquiryPanel.NotFound")+" "+tfRsID.getText());
					tfGeneSymbol.setText("");
					tfSift.setText("");
					tfPolyPhen.setText("");
					tfVarioWatch.setText("");
				}
				else
				{
					lblResult.setText("");
					tfGeneSymbol.setText(result[0]);
					tfSift.setText(result[1]);
					tfPolyPhen.setText(result[2]);
					tfVarioWatch.setText(result[3]);
				}
			}
			else if(e.getSource()==btnVarioWatch)
			{
				VWFrame.INSTANCE.setVisible(true);
			}
		}
		
	}

}
