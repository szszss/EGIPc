package edu.ccmu.egipc.data;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import jxl.Cell;
import jxl.NumberCell;

public class DataGwas {
	
	public final DefaultTableModel tableModel;
	private final String[] ids;
	
	public DataGwas(Cell[] ids, Cell[] chromosome, Cell[] ra, Cell[] pv, Cell[] or, Cell[] floor, Cell[] ceiling)
	{
		tableModel = new DefaultTableModel(0, 7);
		this.ids = new String[ids.length-1];
		for(int i=1;i<ids.length;i++)
		{
			this.ids[i-1] = ids[i].getContents();
			tableModel.addRow(new Object[]{ ids[i].getContents(),
					chromosome[i].getContents(),
					ra[i].getContents(),
					String.format("%f", ((NumberCell)pv[i]).getValue()),
					String.format("%f", ((NumberCell)or[i]).getValue()),
					String.format("%f", ((NumberCell)floor[i]).getValue()),
					String.format("%f", ((NumberCell)ceiling[i]).getValue()) });
		}
	}

	public String[] getData(String id)
	{
		for(int i=0;i<ids.length;i++)
		{
			if(ids[i].equals(id))
			{
				String[] result = new String[6];
				result[0] = tableModel.getValueAt(i, 1).toString();
				result[1] = tableModel.getValueAt(i, 2).toString();
				result[2] = tableModel.getValueAt(i, 3).toString();
				result[3] = tableModel.getValueAt(i, 4).toString();
				result[4] = tableModel.getValueAt(i, 5).toString();
				result[5] = tableModel.getValueAt(i, 6).toString();
				return result;
			}
		}
		return null;
	}
}
