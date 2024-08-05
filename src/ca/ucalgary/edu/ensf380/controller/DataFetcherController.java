package ca.ucalgary.edu.ensf380.controller;

import java.io.IOException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.util.Scanner;

public abstract class DataFetcherController {
	
	protected String fetchData(String stringUrl) throws IOException {
		StringBuilder str = new StringBuilder();
    	URL url = new URL(stringUrl);
    	HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        
        if(connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
     	   Scanner scanner;
     	   scanner = new Scanner(connection.getInputStream());
     	   while(scanner.hasNext()) {
     		   str.append(scanner.nextLine()).append("\n");
     	   }
     	   scanner.close();
        } else {
        	throw new IOException("Error in fetching Data");
     	}
        
        return str.toString();
	}
}
