package com.spoloborota.teaching.storage.model;

import java.util.HashMap;
import java.util.Map;

import com.spoloborota.teaching.storage.type.FilesLoader;
import com.spoloborota.teaching.storage.type.MapStorage;

/**
 * All data saved to RAM memory first
 * @author Spoloborota
 *
 */
public class RAM {
	String path;
	FilesLoader filesloader;

	public Map<String, MapStorage> map;
	public MapStorage currentStorage = null;
	
	public RAM() {
		map = new HashMap<>();
	}
	public RAM(String path) {

		map = new HashMap<>();
		this.path = path;

		filesloader = new FilesLoader(this.path);
		load();
	}
	
	/**
	 * Show all storages
	 * @return string with all storage names
	 */
	public String display() {
		return map.keySet().toString();
	}
	
	/**
	 * Create new storage
	 * @param name - name of the creating storage
	 * @return "true" if all is Ok and "false" if storage with specified name already exists
	 */
	public boolean create(String name) {
		if (map.containsKey(name)) {
			return false;
		} else {
			map.put(name, new MapStorage(name));
			return true;
		}
	}
	
	/**
	 * Delete existing storage by name
	 * @param name
	 */
	public void delete(String name) {
		MapStorage deleted = map.remove(name);
		if (deleted.equals(currentStorage)) {
			currentStorage = null;
		}
	}
	
	/**
	 * Select existing storage by name to operate with it
	 * @param name
	 * @return - "true" if storage with such name exist and "false" otherwise
	 */
	public boolean use(String name) {
		MapStorage mapStorage = map.get(name);
		if (mapStorage != null) {
			currentStorage = mapStorage;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Add data to storage
	 * @param data
	 * @return - "true" if all is Ok and "false" if there is no selected storage 
	 */
	public boolean add(String[] data) {
		if (currentStorage != null) {
			return currentStorage.add(data);
		} else {
			return false;
		}
	}

	public String save(){
		String buffer = "";
		String outString = "";
		boolean savedFlag = false;
		if (currentStorage != null) {
			buffer = currentStorage.getDataToSave();
			savedFlag = filesloader.save(currentStorage.name, buffer);

			if (savedFlag){
				outString = "Data from the current storage " + currentStorage.name 
						+ " Save file " + path 
						+ "\\" 
						+ currentStorage.name +  ".storage";
			}
			else{
				outString = "Invalid path. Restart the program and specify the path to directory.";
			}
			return outString;
		} 
		else {
			return null;
		}
	}

	private void load(){
		String [] listOfStorages;
		String [] buffer;
		String [] toAdd = new String[2];

		if (filesloader.isDirectoryOrNot()){
			listOfStorages = filesloader.getlistOfStorages();
			for (int i = 0 ; i<listOfStorages.length;i++){
				create(listOfStorages[i]);
				filesloader.load((listOfStorages[i]));
				buffer = filesloader.getloadedData();
				use(listOfStorages[i]);
				for (int j = 0; j< buffer.length; j+=2){
					toAdd[0] = buffer[j];
					toAdd[1] = buffer[j+1];				
					add(toAdd);
				}				
			}
			currentStorage = null;
		}
	}

	public String list(String[] data) {
				if (currentStorage != null) {
		 	return currentStorage.hashMap.entrySet().toString();
		 			}
		 	else {
		 		return "Please select the Storage";
		 		}
		 	}
}

