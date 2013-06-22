package com.BuildCraft;

import com.BuildCraft.install.InstallMain;
import com.BuildCraft.states.BCExitState;
import com.BuildCraft.utils.ConsoleOut;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;

import javax.swing.*;

/**
 * Author: {System 920}
 * Created On: {6/21/13} @ {10:00 AM}
 * CopyRight: Created by System 920, licensed under GNU GPLv3
 */
public class BuildCraft {
	
	private static String[] prgArgs;
	private static boolean debug=false;
	private static final String userHome = System.getProperty("user.home");
	
	private static final String[] installFileList = new String[] {
			userHome + "\\AppData\\Roaming\\.System920",
			userHome + "\\AppData\\Roaming\\.System920\\BuildCraft",
			userHome + "\\AppData\\Roaming\\.System920\\BuildCraft\\resources", 
			userHome + "\\AppData\\Roaming\\.System920\\BuildCraft\\bin",
			userHome + "\\AppData\\Roaming\\.System920\\BuildCraft\\bin\\natives",
			userHome + "\\AppData\\Roaming\\.System920\\BuildCraft\\temp.bin",
			userHome + "\\AppData\\Roaming\\.System920\\BuildCraft\\saves",
			userHome + "\\AppData\\Roaming\\.System920\\BuildCraft\\config.bin"
	};
	
	private static final String[] installURLList = new String[] {
			
	};
	
	private static final boolean[] installIsDirectory = new boolean[] {
			true, 
			true,
			true,
			true,
			true,
			true,
			true,
			true
	};
	
	public static void main(String[] args){
		prgArgs = args;
		setUI();
		InstallMain installMain = new InstallMain(new String[] {System.getProperty("user.home") + "\\AppData\\Roaming\\.System920"}, new boolean[] {true});
		installMain.confirmInstall();
	}
	
	private static void buildDisplay(){
		
	}
	
	public static void cleanExit(BCExitState exitState){
		if (Display.isCreated()){
			Display.destroy();
		}
		if (AL.isCreated()){
			AL.destroy();
		}
		if (Keyboard.isCreated()){
			Keyboard.destroy();
		}
		if (Mouse.isCreated()){
			Mouse.destroy();
		}
		ConsoleOut.printMsg("BuildCraft is closing...\nProcess stopped with code " + exitState.value);
		System.exit(exitState.value);
	}
	
	private static void setUI(){
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, fall back to cross-platform
			ConsoleOut.printMsg("Warning: Failed to load \"Nimbus\" L&F, falling back to default...");
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception ex) {
				ConsoleOut.printMsg("Warning: Failed to set L&F to default!");
			}
		}
	}
}
