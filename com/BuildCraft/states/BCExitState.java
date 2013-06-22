package com.BuildCraft.states;

/**
 * Author: {System 920}
 * Created On: {6/21/13} @ {11:25 AM}
 * CopyRight: Created by System 920, licensed under GNU GPLv3
 */
public enum BCExitState {
	NORMAL(0), FORCED(1), ERROR(2);
	public int value;
	BCExitState(int value){
		this.value = value;
	}
}
