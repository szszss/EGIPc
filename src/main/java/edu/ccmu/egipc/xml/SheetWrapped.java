package edu.ccmu.egipc.xml;

import jxl.Cell;
import jxl.CellView;
import jxl.Hyperlink;
import jxl.Image;
import jxl.LabelCell;
import jxl.Range;
import jxl.Sheet;
import jxl.SheetSettings;
import jxl.format.CellFormat;
import jxl.read.biff.SheetImpl;

public class SheetWrapped implements Sheet {

	private final Sheet sheet;
	
	public SheetWrapped(Sheet sheet)
	{
		this.sheet = sheet;
	}

	public Cell findCell(String arg0) {
		return sheet.findCell(arg0);
	}

	public LabelCell findLabelCell(String arg0) {
		return sheet.findLabelCell(arg0);
	}

	public Cell getCell(String arg0) {
		return sheet.getCell(arg0);
	}

	public Cell getCell(int arg0, int arg1) {
		return sheet.getCell(arg0,arg1);
	}

	public Cell[] getColumn(int arg0) {
		return sheet.getColumn(arg0);
	}

	public CellFormat getColumnFormat(int arg0) {
		return sheet.getColumnFormat(arg0);
	}

	public int[] getColumnPageBreaks() {
		return sheet.getColumnPageBreaks();
	}

	public CellView getColumnView(int arg0) {
		return sheet.getColumnView(arg0);
	}

	public int getColumnWidth(int arg0) {
		return sheet.getColumnWidth(arg0);
	}

	public int getColumns() {
		return sheet.getColumns();
	}

	public Image getDrawing(int arg0) {
		return sheet.getDrawing(arg0);
	}

	public Hyperlink[] getHyperlinks() {
		return sheet.getHyperlinks();
	}

	public Range[] getMergedCells() {
		return sheet.getMergedCells();
	}

	public String getName() {
		return sheet.getName();
	}
	
	@Override
	public String toString()
	{
		return getName();
	}

	public int getNumberOfImages() {
		return sheet.getNumberOfImages();
	}

	public Cell[] getRow(int arg0) {
		return sheet.getRow(arg0);
	}

	public int getRowHeight(int arg0) {
		return sheet.getRowHeight(arg0);
	}

	public int[] getRowPageBreaks() {
		return sheet.getRowPageBreaks();
	}

	public CellView getRowView(int arg0) {
		return sheet.getRowView(arg0);
	}

	public int getRows() {
		return sheet.getRows();
	}

	public SheetSettings getSettings() {
		return sheet.getSettings();
	}

	public boolean isHidden() {
		return sheet.isHidden();
	}

	public boolean isProtected() {
		return sheet.isProtected();
	}

}
