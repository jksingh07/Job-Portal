package com.javacodegeeks.snippets.core;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class crawler implements Runnable{
	public static final int MAX_DEPTH = 5;
	public static final int MAX_CONTENT = 5;
	private Thread thread;
	private String first_link;
	
	//private ArrayList<String> visitedLinks = new ArrayList<String>();
	private Map<String, String> visitedLinks = new HashMap<String, String>();  // to check if link is already visited
	private int ID;

	public crawler(String link, int num){
		System.out.print("Web Crawler created");
		first_link = link;
		ID = num;

		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		crawl(1,first_link,0);
		
	}
	
	private void crawl(int level, String url, int total){
		if(level <= MAX_DEPTH){
			Document doc = request(url);
			
			if(doc!= null) {
				while(total<MAX_CONTENT) {
				for(Element link: doc.select("a[href]")) {
					String next_link = link.absUrl("href");
					if(visitedLinks.containsKey(next_link)== false) {
						// Recursion 
						total += 1;
						crawl(level++, next_link, total);
					}
				}
			}}
		}
		else {
			writedata(visitedLinks);}
	}

	private Document request(String url){
		try{
			// Jsoup connection 
			Connection con = Jsoup.connect(url);
			Document doc = con.get();
			
			if(con.response().statusCode() == 200) {
				System.out.println("\n Site " + ID + " Received  webpage at " + url);
				String title = doc.title();
				System.out.println(title);
				visitedLinks.put(url, title);
				return doc;
			}
			return null;
		}
		catch(IOException e) {
			return null;
		}
	}
	
	public void writedata(Map<String, String> visitedLinks2) {
		File file = new File("/Users/bikramjeetsingh/eclipse-workspace/Job_Search_Final/Data Files/"+ID+".txt");
		  
        BufferedWriter bf = null;
  
        try {
  
        	FileWriter fstream = new FileWriter(file,true);
        	   
        	bf = new BufferedWriter(fstream);
        	//out.write("Line Added on: " + new java.util.Date()+"\n");
            // iterate map entries
            for (Map.Entry<String, String> entry :
                 visitedLinks2.entrySet()) {
  
                // put key and value separated by a colon
                bf.write(entry.getKey() + ":"
                         + entry.getValue());
  
                // new line
                bf.newLine();
            }
  
            bf.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
  
            try {
  
                // always close the writer
                bf.close();
            }
            catch (Exception e) {
            }
        }
	}
	
	public Thread getThread(){
		return thread;
	}
}