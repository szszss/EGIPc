package edu.ccmu.egipc.i18n;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;
import java.util.Set;


public abstract class EgipcI18N {

	public final static Set<ResourceBundle> resourceBundles = new HashSet<ResourceBundle>();
	
	public static ResourceBundle currentResourceBundle;
	public static ResourceBundle backupResourceBundle;
	
	public static LinkedList<II18Nable> i18Nables = new LinkedList<II18Nable>();
	
	public static void init(String location)
	{
		try {
			String path = location+"/lang";
			resourceBundles.add(backupResourceBundle = ResourceBundle.getBundle(path,Locale.US,UTF8Control.INSTANCE));
			resourceBundles.add(ResourceBundle.getBundle(path,Locale.CHINA,UTF8Control.INSTANCE));
			currentResourceBundle = ResourceBundle.getBundle(path,Locale.getDefault(),UTF8Control.INSTANCE);
			resourceBundles.add(currentResourceBundle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getText(String key)
	{
		String valueString = currentResourceBundle.containsKey(key)?currentResourceBundle.getString(key):
							 backupResourceBundle.containsKey(key)?backupResourceBundle.getString(key):
						     "Missing String:"+key;
		return valueString;
	}
	
	public static void addI18Nable(II18Nable ii18Nable)
	{
		i18Nables.add(ii18Nable);
		if(ii18Nable!=null)
			ii18Nable.i18Ning();
	}
	
	public static void removeI18Nable(II18Nable ii18Nable)
	{
		i18Nables.remove(ii18Nable);
	}
	
	public static void setLang(ResourceBundle resourceBundle)
	{
		currentResourceBundle = resourceBundle;
		reload();
	}
	
	public static void reload()
	{
		II18Nable i18Nable;
		for(Iterator<II18Nable> iterator = i18Nables.iterator();iterator.hasNext();)
		{
			i18Nable = iterator.next();
			if(i18Nable==null)
			{
				iterator.remove();
				continue;
			}
			i18Nable.i18Ning();
		}
	}
	
}
