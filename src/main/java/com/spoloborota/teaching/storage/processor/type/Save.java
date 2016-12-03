package com.spoloborota.teaching.storage.processor.type;

import com.spoloborota.teaching.storage.model.RAM;

public class Save {

	public static String process(RAM ram) {
		String isSaved = ram.save();
		if (isSaved != null) {
			return isSaved;
		} else {
			return "There is no selected storage";
		}
	}
}