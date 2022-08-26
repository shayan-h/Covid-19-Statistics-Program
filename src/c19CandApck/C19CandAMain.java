package c19CandApck;

import java.util.HashMap;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class C19CandAMain extends C19Data {
	
	static Scanner consol = new Scanner(System.in);
	static HashMap<String, String> c19Data = new HashMap<>();
	static String res = "";

	public static void main(String[] args) throws Exception {
		System.out.println("Welcome to C19 Compare and Analyze!\n");
		
		inputCountryName();
		
		
		
		

	}
	
	
	public static void printCovid19Data() {
		res = C19Data.dataText();
		C19Data.formatHM(res);
	}
	
	
	public static void inputCountryName() throws Exception { 
		boolean validCountryName = false;
		while (!validCountryName) {
			System.out.println("Input a country name: (0 to exit)");
			String name = consol.nextLine();   
			
			if (name.equals("0")) {
				System.out.println("Goodbye!");
				break;
			}
			
			if (Covid19Data(name)) {
				System.out.println("Current Covid19 Data [" + name + "]\n");
				printCovid19Data();
				break;
			} else {
				System.out.println("Invalid country name. Type again.\n"); 
			}
		}
		
	}
	

}
