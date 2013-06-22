package com.BuildCraft.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Author: {System 920}
 * Created On: {6/21/13} @ {10:59 AM}
 * CopyRight: Created by System 920, licensed under GNU GPLv3
 */
public class Settings {
	
	private String[] aKeys;
	private String[] aValues;
	private String[] defaults;
	private File file;
	
	public Settings(String[] keys, String[] defaults, String location){
		aKeys = keys;
		aValues = new String[0];
		this.defaults = defaults;
		file = new File(location);
	}
	
	public boolean loadSettings(){
		String fileData = "";
		String[] parseData = null;
		boolean operationCompleted=true;
		boolean lengthMatch=true;
		
		try{
			fileData = FileUtils.readFileToString(file);
		}catch(IOException ex){
			ConsoleOut.printMsg("Settings: Warning: Failed to load " + file.getName());
			operationCompleted=false;
		}
		
		parseData = fileData.split("\n");
		
		if (parseData.length != aKeys.length){
			ConsoleOut.printMsg("Settings: Warning: Invalid settings file for defined keys!");
			operationCompleted=false;
			lengthMatch=false;
		}
		
		if (lengthMatch){
			for (int i=0; i < parseData.length; i++){
				if (aKeys[i] != null){
					aValues = ArrayUtils.appendToArray(aValues, parseData[i].replace(aKeys[i] + "=", ""));
				}
			}
		}
		return operationCompleted;
	}
	
	public boolean saveSettings(){
		boolean operationCompleted=true;
		String fileData = "";
		
		for(int i=0; i < aKeys.length; i++){
			if (i==0){
				fileData += aKeys[i] + "=" + aValues[i];
			}else{
				fileData += "\n" + aKeys[i] + "=" + aValues[i];
			}
		}
		
		try{
			FileUtils.writeStringToFile(file, fileData);
		}catch(IOException ex){
			ConsoleOut.printMsg("Settings: Warning: Failed to save " + file.getName());
			operationCompleted=false;
		}
		return operationCompleted;
	}
	
	public void setValue(int id, String value){
		aValues[id] = value;
	}
	
	public String getValue(int id){
		return aValues[id];
	}
	
	public int getKeyID(String key){
		int id=-1;
		for (int i=0; i < aKeys.length; i++){
			if (aKeys[i].equals(key)){
				id=i;
				break;
			}
		}
		return id;
	}
	
	public void checkSettings(){
		if(!file.exists()){
			ConsoleOut.printMsg("Settings: Creating new settings " + file.getName());
			try{
				FileUtils.touch(file);
			}catch(IOException ex){
				ConsoleOut.doError("Failed to generate settings " + file.getName());
			}
			aValues = defaults;
			if(saveSettings()){
				ConsoleOut.printMsg("Settings: File creation successful!");
			}else{
				ConsoleOut.doError("Failed to generate settings " + file.getName());
			}
		}else{
			ConsoleOut.printMsg("Settings: Loaded settings " + file.getName());
		}
	}
}
