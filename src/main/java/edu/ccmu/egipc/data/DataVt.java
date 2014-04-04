package edu.ccmu.egipc.data;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import jxl.Cell;
import jxl.NumberCell;

public class DataVt {
	
	public final DefaultTableModel tableModel;
	private final String[] ids;
	
	public DataVt(Cell[] ids, Cell[] symbol, Cell[] basedVt)
	{
		tableModel = new DefaultTableModel(0, 3);
		this.ids = new String[ids.length-1];
		for(int i=1;i<ids.length;i++)
		{
			this.ids[i-1] = ids[i].getContents();
			tableModel.addRow(new Object[]{ ids[i].getContents(),
					symbol[i].getContents(),
					basedVt[i].getContents()});
		}
	}
	
	public String[] getData(String id)
	{
		for(int i=0;i<ids.length;i++)
		{
			if(ids[i].equals(id))
			{
				String[] result = new String[2];
				result[0] = tableModel.getValueAt(i, 1).toString();
				result[1] = tableModel.getValueAt(i, 2).toString();
				return result;
			}
		}
		return null;
	}
}
