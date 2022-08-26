package c19CandApck;
import java.net.ResponseCache;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Stack;

public class C19Data {
	
	private static HashMap<String, String> covid19Data = new HashMap<String, String>();
	
	private static String res = "";
	
	public static boolean Covid19Data(String name) throws Exception {
		
		boolean validCountryName = false;
		
		try {
			
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://covid-19-tracking.p.rapidapi.com/v1/" + name))
					.header("X-RapidAPI-Host", "covid-19-tracking.p.rapidapi.com")
					.header("X-RapidAPI-Key", "bf5203b9d5mshfdca473ba89a534p156a6bjsn332578bb5d09")
					.method("GET", HttpRequest.BodyPublishers.noBody())
					.build();
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			
			
			if (response.body().contains("World")) {
				return false;
			}
			
			
			// System.out.println(response.body());
			
			res = response.body();
			
			// formatHM(res);
			
			// System.out.println(res);
			
			validCountryName = true;
			
		}
		catch (Exception e) {
			validCountryName = false;
		}
		
		return validCountryName;
		
	}
	
	public static String dataText() {
		return res;
	}
	
	
	public static void formatHM(String response) {
		
		HashMap<String, String> covidData = new HashMap<>();
		
		Stack<Integer> cuts = new Stack<Integer>();
		
		for (int i = 0; i < response.length(); i++) {
			
			
			
			if (response.charAt(i) == '"' && (response.charAt(i + 1) == ':' || response.charAt(i + 1) == ',' || response.charAt(i + 1) == '}') && !cuts.isEmpty()) {
				
				int pos = cuts.peek();
				
				cuts.pop();
				
				int len = i - 1 - pos;
				
				String output = response.substring(pos + 1, pos + 1 + len);
				
				String result = "";
				if (output.contains("_text")) {
					result = output.substring(0, output.lastIndexOf("_text"));
					System.out.println(result + ":\n");
				}
				else if (output.contains("Update")) {
					System.out.println(output + ":\n");
				} 
				else {
					if (!(output == "")) {
						System.out.println(output + "\n");
					} else {
						System.out.println("n/a\n");
					}
				}
				
				
			}
			
			else if (response.charAt(i) == '"') {
				cuts.add(i);
				
			}
			
		}
		
	}
	
}
