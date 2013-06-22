package com.BuildCraft.utils;

import com.BuildCraft.BuildCraft;
import com.BuildCraft.states.BCExitState;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: {System 920}
 * Created On: {6/21/13} @ {11:11 AM}
 * CopyRight: Created by System 920, licensed under GNU GPLv3
 */
public class ConsoleOut {
	
	private static String getTimeAndDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static void printMsg(String message){
		System.out.println(getTimeAndDate() + " | " + message);
	}
	
	public static void doError(String message){
		Toolkit.getDefaultToolkit().beep();
		printMsg("Error: " +message);
		if (Display.isCreated() && Display.isFullscreen()){
			try{
				Display.setFullscreen(false);
			}catch(LWJGLException ex){
				printMsg("Warning: Failed to minimize display!");
			}
		}
		JOptionPane.showMessageDialog(null, "BuildCraft has encountered an error!\n\nError: " + message + "\n\nBuildCraft will now close...");
		BuildCraft.cleanExit(BCExitState.ERROR);
	}
	
	public static void printMsgNNL(String message){
		System.out.print(getTimeAndDate() + " | " + message);
	}
}
