package com.spoloborota.teaching.storage.processor;


import com.spoloborota.teaching.storage.commands.Commands;

import com.spoloborota.teaching.storage.model.RAM;
import com.spoloborota.teaching.storage.processor.type.Save;
import com.spoloborota.teaching.storage.processor.type.Shutdown;
import com.spoloborota.teaching.storage.processor.type.Add;
import com.spoloborota.teaching.storage.processor.type.Create;
import com.spoloborota.teaching.storage.processor.type.Display;
import com.spoloborota.teaching.storage.processor.type.Use;
import com.spoloborota.teaching.storage.processor.type.List;
/**
 * process commands
 * @author Spoloborota
 *
 */
public class Processor {
	public RAM ram;
	private Shutdown shutdown;
	
	public Processor(RAM ram) {
		this.ram = ram;
		shutdown = new Shutdown();
	}
	public String process(String commandString) {
		String[] commandWords = commandString.trim().split("\\s+");
		if (commandWords.length != 0) {
			for (String s : commandWords) {
				System.out.println(s);
			}
			
			String result = "";
			switch (commandWords[0]) {
			case Commands.DISPLAY:
				if (!shutdown.getShutdownFlag()){
					result = Display.process(ram);
				}
				else{
					result = shutdown.getSaveYesNoMessage();
				}
				break;

			case Commands.USE:
				if (!shutdown.getShutdownFlag()){
					if (commandWords.length > 1) {
						result = Use.process(ram, commandWords);
					} else {
						result = "Storage name does not specified";
					}
				}
				else{
					result = shutdown.getSaveYesNoMessage();
				}
				break;
				
			case Commands.CREATE:
				if (!shutdown.getShutdownFlag()){
					if (commandWords.length > 1) {
						result = Create.process(ram, commandWords);
					} else {
						result = "Storage name does not specified";
					}
				}
				else{
					result = shutdown.getSaveYesNoMessage();
				}
				break;
				
			case Commands.ADD:
				if (!shutdown.getShutdownFlag()){
					if (commandWords.length > 2) {
						result = Add.process(ram, commandWords);					
					} else {
						result = "Data for storage does not specified correctly";
					}
				}
				else{
					result = shutdown.getSaveYesNoMessage();
				}
				break;

			case Commands.SAVE:
				if (!shutdown.getShutdownFlag()){
					result = Save.process(ram);					
				}
				else{
					if(shutdown.getShutdownSaveFlag()){
						result = Save.process(ram);
					}
					else{
						result = shutdown.getSaveYesNoMessage();
					}
				}
				break;

			case Commands.YES:
				if (shutdown.getShutdownFlag()){
					shutdown.setShutdownSaveFlag(true);
					result = Save.process(ram);
					System.out.println(result);
					shutdown.exit();
				}
				break;

			case Commands.NO:
				if (shutdown.getShutdownFlag()){
					shutdown.exit();
				}
				break;
				
			case Commands.LIST:
				if (ram.currentStorage != null){
					result = List.process(ram, commandWords) + "\n" + "Data listed" + "\n";					
				}
				else{
					result = "There is no selected storage";
				}
				break;
				
			case Commands.SHUTDOWN:
				shutdown.setShutdownFlag(true);				
				result = shutdown.getSaveYesNoMessage();
				break;

			default:
				if (shutdown.getShutdownFlag()){
					result = shutdown.getSaveYesNoMessage();
				}
				else{
					result = "You do not entered any comand";
				}
				break;
			}
			return result;
		} else {
			return "You do not entered any comand";
		}
	}
}
