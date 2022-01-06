package me.postMethod;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class postXWWWUrlEncoded {
		private static HttpURLConnection con;
		private static String response;
		private String Htmlresponse;
	    private static String postXWWWUrl(String url,String param)  {
	        byte[] postData = param.getBytes(StandardCharsets.UTF_8);

	        try {

	            var myurl = new URL(url);
	            con = (HttpURLConnection) myurl.openConnection();

	            con.setDoOutput(true);
	            con.setRequestMethod("POST");
	            con.setRequestProperty("User-Agent", "Java client");
	            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

	            try (var wr = new DataOutputStream(con.getOutputStream())) {

	                wr.write(postData);
	            }

	            StringBuilder content;

	            try (var br = new BufferedReader(
	                    new InputStreamReader(con.getInputStream()))) {

	                String line;
	                content = new StringBuilder();

	                while ((line = br.readLine()) != null) {
	                    content.append(line);
	                    content.append(System.lineSeparator());
	                }
	            }
	            response = content.toString();
	        } catch (IOException e) {
				e.printStackTrace();
			} finally {
	            con.disconnect();
	        }
			return response;	    	
		}
	    public void HtmlPost(String url,String param) {
	    	this.Htmlresponse = postXWWWUrl(url,param);
	    }
	    public String getHtmlReponse() {
	    	return Htmlresponse;
	    }
}
