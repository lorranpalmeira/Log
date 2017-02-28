package com.github.lorranpalmeira.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;



public class Log implements Serializable {
	
	private static final long serialVersionUID = 1L;
	static String path = "C:\\arquivos\\application.log";
	static int nextVal;

	
	public static void writerLog(String text) {
		
		
		boolean fileExist = new File(path).exists();
		
		try(BufferedWriter outStream= 
				new BufferedWriter(
						new FileWriter( path , true))){
		
			
			if(!(fileExist)){
				
				outStream.append("******************* LOG **********************\n");
				outStream.append("*************** Version 1.0 ******************\n");
				outStream.append("**********************************************\n");
				outStream.append(" \n");
			}
			
			//outStream.append("Date: "+ date.get(Calendar.MONTH) + "\n" );
			outStream.append(text+"\n");
			
			
			outStream.close();
						
		} catch(IOException e){
			e.printStackTrace();
		}finally{
			
				rotate(100);
			
		}
		
	}
	
	public static void rotate(int fileSize){
		
		File file = new File(path);
		long size = file.length();
		
		
		
		if(size >= fileSize ){
			
			
			file.renameTo(new File("C:\\arquivos\\application_"+nextVal+".log"));
			//file.renameTo(new File("C:\\arquivos\\application_"+nextVal+"_"+ System.currentTimeMillis() +".log"));
			nextVal += 1;			
			
		}
		
		
		
		
		
	}

	
	public static void debug(String text) {
		
		writerLog("DEBUG - " + text);
		
	}
	
	public static void info(String text) {
		
		writerLog("INFO - " + text);
		
	}
	
	public static void warn(String text){
		
		writerLog("WARN - " + text);
		
	}
	
	public static void error(String text){
		
		writerLog("ERROR - " + text);
		
	}
	
	public static void fatal(String text) {
		
		writerLog("FATAL - " + text);
		
	}

}
