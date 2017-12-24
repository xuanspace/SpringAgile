package com.agile.test;

import java.io.IOException;

import com.agile.framework.generator.FileParser;

public class TestFileParaser {

	/*
	public TestFileParaser() {
       comment
	}*/public
	
	void test1() {
        String path;
		try {
			path = new java.io.File(".").getCanonicalPath();
	        path += "/src/main/java/com/agile/test/TestFileParaser.java";                
			FileParser parser = new FileParser();			
			String temp = "as/*sdss*/6";
			parser.eraseComment(temp);
			parser.parseFilie(path);			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}      		
	}

	public static void main(String[] args) {
		TestFileParaser parser = new TestFileParaser();
		parser.test1();
    	System.exit(0);
	}

}
