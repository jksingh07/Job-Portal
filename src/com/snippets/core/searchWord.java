package com.javacodegeeks.snippets.core;

import java.util.Scanner;

public class searchWord {
	public static String output_corrected = "";
	public static String Suggested_word = "";
	
	
	public static String search(String input1) {     			// we are putting "s" entered in main here
		

		try {
			// Auto Correction
			Correction ac = new Correction();
			// Dictionary for auto correction
			ac.dictionaryOfWords("src/dictionary.txt"); 		// the directory for all words.

			Scanner sc = new Scanner(System.in);

			String input = input1.toLowerCase();
			String correct[] = input.split(" ");
		    System.out.println("Input: " + input);
			String string = "";
		    output_corrected = "";
			
			// Auto Correction
			for (String word : correct) {
				System.out.println("before suggestwords");
				Suggested_word = ac.suggestWords(word);
					if (!(Suggested_word.equals(word))) {
						string += Suggested_word + " ";
						output_corrected += word + " [You mean: " + Suggested_word + "]";
						System.out.println("output_corrected "+ output_corrected);
						
					}
				}
			
		}catch (Exception e) {
			System.out.println(e);
		}
		return Suggested_word;
	}
	

}
