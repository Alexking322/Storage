package com.spoloborota.teaching.storage.type;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FilesLoader {

	private String path;
	private boolean directoryOrNot = false; 
	private String[] listOfStorages ={};
	private String[] loadedData ={};



	public FilesLoader(String path){
		this.path = path;
		searchAndFilter();
	}  


	public boolean isDirectoryOrNot() {
		return directoryOrNot;
	}

	public String[] getlistOfStorages() {
		return listOfStorages;
	}

	public String[] getloadedData() {
		return loadedData;
	}

	private void searchAndFilter(){
		String [] catalogContains;
		String [] temp;
		StringBuilder rezultContains = new StringBuilder();
		final String tek = ".storage";

		File f1 = new File(path);

		if (f1.exists()){
			if (f1.isDirectory()){
				directoryOrNot = true;
				catalogContains = f1.list();
				for (int i = 0; i<catalogContains.length;i++){
					if (catalogContains[i].endsWith(tek)){								
						temp = catalogContains[i].split(".storage");
						rezultContains.append(temp[0] + "\r\n");
					}
				}
				listOfStorages = (rezultContains.toString().trim().split("\r\n"));


				rezultContains = null; 
			}			
		}
		else{
			directoryOrNot = false;
		}

	}

	public boolean load(String fileName){

		String stringOfFile = "";

		File f1 = new File(path + "\\" + fileName + ".storage");
		if (f1.exists()){
			try{
				char [] dataFromFile = new char [(int)f1.length()];
				FileReader f = new FileReader(path + "\\" + fileName + ".storage");
				f.read(dataFromFile);
				stringOfFile = new String(dataFromFile);
				loadedData = stringOfFile.trim().split("\r\n");
				f.close();
			}
			catch(IOException e){
			}
			return true; 
		}
		else{
			return false; 
		}
	}

	public boolean save(String fileName, String data){
		File f1 = new File(path + "\\");
		if (f1.exists()){
			if (f1.isDirectory()){
				try{
					FileWriter f = new FileWriter(path + "\\" + fileName + ".storage");
					f.write(data);			
					f.close();
				}
				catch(IOException e){
				}
				return true;
			}
			else{
				return false;
			}
		}
		else {
			return false;
		}		
	}	
}