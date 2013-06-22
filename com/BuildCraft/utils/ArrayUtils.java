package com.BuildCraft.utils;

/**
 * Author: {System 920}
 * Created On: {6/21/13} @ {11:17 AM}
 * CopyRight: Created by System 920, licensed under GNU GPLv3
 */
public class ArrayUtils {

	public static String[] appendToArray(String[] array, String value){
		String[] OLD = array;
		String[] NEW = new String[OLD.length + 1];

		for (int i=0; i < OLD.length; i++){
			NEW[i] = OLD[i];
		}
		NEW[NEW.length-1] = value;
		OLD = null;
		return NEW;
	}

	public static boolean contains(String[] array, String checkValue){
		boolean doesExist=false;
		for (int i=0; i < array.length; i++){
			if (array[i].equals(checkValue)){
				doesExist=true;
				break;
			}
		}
		return doesExist;
	}
	
}
