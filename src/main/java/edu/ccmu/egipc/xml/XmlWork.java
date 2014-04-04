package edu.ccmu.egipc.xml;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.read.biff.WorkbookParser;

public class XmlWork {
	
	public final Workbook xmlWorkbook;
	private final HashMap<String, Sheet> sheetMap;
	
	public XmlWork(File xmlFile) throws BiffException, IOException
	{
		WorkbookSettings settings = new WorkbookSettings();
		//settings.setEncoding("UTF8");
		xmlWorkbook = WorkbookParser.getWorkbook(xmlFile, settings);
		sheetMap = new HashMap<String, Sheet>();
		for(Sheet sheet : xmlWorkbook.getSheets())
		{
			sheetMap.put(sheet.getName(), new SheetWrapped(sheet));
		}
	}
	
	public Collection<Sheet> getAllSheet()
	{
		return sheetMap.values();
	}
}
