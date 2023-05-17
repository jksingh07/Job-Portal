package com.javacodegeeks.snippets.core;


import java.util.ArrayList;

public class runcrawler {

	public static void main(String[] args) {
		ArrayList<crawler> bots = new ArrayList<>();
		main b=new main();																				//creating thread list
		String var=com.javacodegeeks.snippets.core.main.Suggested_word;
		String urli="https://www.linkedin.com/jobs/search/?currentJobId=3364419766&location=canada&keywords="+var;
		System.out.println(urli);
		bots.add(new crawler(urli,2)); 
		for( crawler cw: bots) {
			try {
				cw.getThread().join();
				
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		

	}

}
