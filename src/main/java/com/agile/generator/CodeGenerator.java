package com.agile.generator;

import java.io.IOException;

import com.agile.framework.generator.CodeBuilder;

public class CodeGenerator {

	private CodeBuilder builder;
	
	public CodeGenerator() {
		try {
			builder = new CodeBuilder();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void run() {
		builder.generateAll();
	}

    public static void main(String[] args) throws Exception {  
    	CodeGenerator generator  = new CodeGenerator();
    	generator.run();
    	System.exit(0);
    }  
  	
}
