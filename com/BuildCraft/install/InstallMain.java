package com.BuildCraft.install;

import com.BuildCraft.BuildCraft;
import com.BuildCraft.states.BCExitState;
import com.BuildCraft.utils.ConsoleOut;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Author: {System 920}
 * Created On: {6/21/13} @ {6:18 PM}
 * CopyRight: Created by System 920, licensed under GNU GPLv3
 */
public class InstallMain {
	
	private File[] fileList;
	private boolean[] isDirectory;
	private URL[] installList;
	
	public InstallMain(String[] filePathList, boolean[] directory, String[] URLInstallList){
		fileList = new File[filePathList.length];
		for (int i=0; i < filePathList.length; i++){
			fileList[i] = new File(filePathList[i]);
		}
		isDirectory = directory;
		
		installList = new URL[URLInstallList.length];
		for (int i=0; i < URLInstallList.length; i++){
			try {
				installList[i] = new URL(URLInstallList[i]);
			}catch(MalformedURLException ex){
				ConsoleOut.doError("InstallMain: Given URL at position " + i + " was improperly given!");
			}
		}
	}
	
	private void doInstall(){
		boolean installSuccessful=true;
		for (int i=0; i < fileList.length; i++){
			if (!fileList[i].exists()){
				if (isDirectory[i]){
					ConsoleOut.printMsgNNL("InstallMain: Creating directory " + fileList[i].getName() + "...");
					try{
						FileUtils.forceMkdir(fileList[i]);
						ConsoleOut.printMsg("Done!");
					}catch(Exception ex){
						installSuccessful=false;
						ConsoleOut.printMsg("Failed!");
					}
				}else{
					ConsoleOut.printMsgNNL("InstallMain: Creating file " + fileList[i].getName() + "...");
					try{
						FileUtils.touch(fileList[i]);
						ConsoleOut.printMsg("Done!");
					}catch(Exception ex){
						installSuccessful=false;
						ConsoleOut.printMsg("Failed!");
					}
				}
			}
		}
	}
	
	public void confirmInstall(){
		boolean fileNotInstalled=false;
		for (int i=0; i < fileList.length; i++){
			if (!fileList[i].exists()){
				fileNotInstalled=true;
				break;
			}
		}
		
		if (fileNotInstalled){
			int option = JOptionPane.showConfirmDialog(null, "Part or All of BuildCraft is not installed!\nInstall?");
			if (option == JOptionPane.YES_OPTION){
				ConsoleOut.printMsg("Installing BuildCraft...");
				doInstall();
			}else{
				ConsoleOut.printMsg("BuildCraft Installation Canceled");
				BuildCraft.cleanExit(BCExitState.NORMAL);
			}
		}
	}
	
}
