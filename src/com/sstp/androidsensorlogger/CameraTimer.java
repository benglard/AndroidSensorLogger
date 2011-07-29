package com.sstp.androidsensorlogger;

import java.util.TimerTask;



public class CameraTimer extends TimerTask {
	
	private int picCounter;
	
	CameraTimer(int counter)
	{
		picCounter = counter;
	}
	
	public void run()
	{
        CameraSWAGActivity cs = new CameraSWAGActivity(picCounter);
    }
	
}
