package edu.ccmu.egipc.xml;

import jxl.Cell;

public class CellsWrapped {

	public final Cell[] cells;
	
	public CellsWrapped(Cell[] cells)
	{
		this.cells = cells;
	}
	
	@Override
	public String toString() {
		return cells[0].getContents();
	}
	
	
}
