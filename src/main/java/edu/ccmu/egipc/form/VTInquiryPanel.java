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
import edu.ccmu.egipc.data.DataVt;
import edu.ccmu.egipc.i18n.EgipcI18N;
import edu.ccmu.egipc.i18n.II18Nable;
import edu.ccmu.egipc.xml.XmlWork;

public class VTInquiryPanel extends JPanel implements II18Nable{
	private JTable table;
	private JTextField tfRsId;
	private JTextField tfSymbol;
	private JTextField tfBasedVT;
	private JButton btnInquiry;
	private JLabel lblRsId;
	private JLabel lblSymbol;
	private JLabel lblBasedVT;
	
	private MyListener listener = new MyListener();
	private DataVt data;
	private JLabel lblResult;

	/**
	 * Create the panel.
	 */
	public VTInquiryPanel() {
		setLayout(new MigLayout("", "[200px:200px:300px,grow][grow]", "[grow]"));
		
		JPanel panel = new JPanel();
		add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[grow][grow]", "[][][][][][][][][][]"));
		
		lblRsId = new JLabel("ID");
		panel.add(lblRsId, "cell 0 0");
		
		tfRsId = new JTextField();
		panel.add(tfRsId, "cell 0 1 2 1,growx");
		tfRsId.setColumns(10);
		
		btnInquiry = new JButton("New button");
		btnInquiry.addActionListener(listener);
		panel.add(btnInquiry, "cell 0 2 2 1");
		
		lblSymbol = new JLabel("New label");
		panel.add(lblSymbol, "cell 0 3,alignx left");
		
		tfSymbol = new JTextField();
		panel.add(tfSymbol, "cell 1 3,growx");
		tfSymbol.setColumns(10);
		
		lblBasedVT = new JLabel("New label");
		panel.add(lblBasedVT, "cell 0 4,alignx left");
		
		tfBasedVT = new JTextField();
		panel.add(tfBasedVT, "cell 1 4,growx");
		tfBasedVT.setColumns(10);
		
		lblResult = new JLabel("");
		panel.add(lblResult, "cell 0 5 2 1");
		
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
				"New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);

		EgipcI18N.addI18Nable(this);
	}

	public void i18Ning() {
		btnInquiry.setText(EgipcI18N.getText("Form.btnInquiry.Text"));
		lblRsId.setText(EgipcI18N.getText("Form.lblRsId.Text"));
		lblSymbol.setText(EgipcI18N.getText("Form.lblSymbol.Text"));
		lblBasedVT.setText(EgipcI18N.getText("Form.lblBasedVT.Text"));
		i18Ning_2();
		//table.getColumnModel().getColumns().nextElement().
	}
	
	public void loadData(DataVt dataVt){
		data = dataVt;
		table.setModel(dataVt.tableModel);
		i18Ning_2();
	}
	
	private void i18Ning_2()
	{
		Enumeration<TableColumn> enumeration = table.getColumnModel().getColumns();
		enumeration.nextElement().setHeaderValue(EgipcI18N.getText("Form.lblRsId.Text"));
		enumeration.nextElement().setHeaderValue(EgipcI18N.getText("Form.lblSymbol.Text"));
		enumeration.nextElement().setHeaderValue(EgipcI18N.getText("Form.lblBasedVT.Text"));
	}
	
	private class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnInquiry)
			{
				String[] result = data.getData(tfRsId.getText());
				if(result==null)
				{
					lblResult.setText(EgipcI18N.getText("Form.VTInquiryPanel.NotFound")+" "+tfRsId.getText());
					tfSymbol.setText("");
					tfBasedVT.setText("");
				}
				else
				{
					lblResult.setText("");
					tfSymbol.setText(result[0]);
					tfBasedVT.setText(result[1]);
				}
			}
		}
		
	}

}
