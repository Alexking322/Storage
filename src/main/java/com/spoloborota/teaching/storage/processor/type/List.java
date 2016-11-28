package com.spoloborota.teaching.storage.processor.type;

import com.spoloborota.teaching.storage.model.RAM;

public class List {
	 	
		public static String process(RAM ram, String[] commandWords) {
	 		String isListed = ram.list(new String[]{commandWords[0]});
	 		if (isListed != null) {
	 			return isListed;
	 		} else {
	 			return "There is no selected storage";
	 		}
	 	}
	 }