package com.spoloborota.teaching.storage.processor.type;


public class Shutdown {
private boolean shutdownFlag;	
	private boolean shutdownSaveFlag;	
	
	public Shutdown(){
		shutdownFlag = false;
		shutdownSaveFlag = false;
	}
	
	public boolean getShutdownFlag(){
		return shutdownFlag;
	}
	
	public void setShutdownFlag(boolean shutdownFlag){
		this.shutdownFlag = shutdownFlag;
	}
	
	public void setShutdownSaveFlag(boolean shutdownSaveFlag){
		this.shutdownSaveFlag = shutdownSaveFlag;
	}
	
	public boolean getShutdownSaveFlag(){
		return shutdownSaveFlag;
	}
	
	public String getSaveYesNoMessage(){
		return "Save this storage? y/n";
	}
		
	
	public void exit(){
		if (shutdownFlag){
			System.out.println("Good bye!");
			System.exit(0);
		}
	}
 }