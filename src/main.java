

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class main implements Constants {
	public static String Suggested_word;

	public static void main(String []args) throws IOException {
		HashMap<String,String> links = new HashMap<String,String>();
		System.out.println("Job Search Program:Canada");
		System.out.println("Press 1 to proceed");
		//String Suggested_word;
		try (Scanner sc = new Scanner(System.in)) {
			int input;
			do {
				while (!sc.hasNextInt()) {
					String val = sc.next();
					System.out.printf("\"%s\"This is not a valid number.%n", val);
				}
				input = sc.nextInt();
				if(input!=1)
					System.out.printf("\"%s\"This is not a valid input.%n", input);

			} while (input != 1);
			while (true) {
				
				String s =""; 
				sc.nextLine();
				System.out.println("Enter the desired job skill:");
				s = sc.nextLine();


				if(s.length()!=0)
				{
					Suggested_word = searchWord.search(s);   // Suggested word here is holding the output of the SEARCHWORD CLASS 
					System.out.println("Auto Corrected Word: "+Suggested_word);
					runcrawler.main(args);
					links = SearchCrawlerData.SearchWord(Suggested_word);  // we're adding the desired word, for the search.
					Object[] key = links.keySet().toArray();
					String[] URL = Arrays.stream(key).toArray(String[]::new);

					Object[] fname = links.values().toArray();
					String[] fileName = Arrays.stream(fname).toArray(String[]::new);
					Linktotext.htmlToTextParser(links);
					HashMap<String,Integer> count = new HashMap<>();
					count = Ranking_final.Counting(URL, fileName, Suggested_word);
					Ranking_final.sortIndex(count);

				}
				else if (s.equalsIgnoreCase("exit")) {

					break;
				}
				}
		}


	} 
}
