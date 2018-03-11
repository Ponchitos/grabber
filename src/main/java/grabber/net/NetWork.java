package grabber.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import grabber.config.Config;
import grabber.parser.IParser;
import grabber.parser.Parser;

public class NetWork implements INetwork{
	private URL url;
	private IParser parser;
	private String news;
	private HttpsURLConnection urlConnection;
	private InputStream inputStream;
	private HttpClient httpClient;
	private StringEntity entity;
	private HttpPost request;
	private HttpResponse response;
	
	public NetWork() throws IOException {
		url = new URL(Config.URL_GOOGLE_NEWS + Config.API_KEY);
		request = new HttpPost(Config.TARGET_SERVER);
		parser = new Parser();
	}
	
	public String connect() throws IOException {
		urlConnection = (HttpsURLConnection) url.openConnection();
		urlConnection.setRequestMethod("GET");
		urlConnection.connect();
		
		inputStream = urlConnection.getInputStream();
		news = parser.parser(inputStream);
		inputStream.close();
		return news;
	}

	public void sendJson(String text) throws IOException {
			
		entity = new StringEntity(parser.jsonCreate(text), ContentType.APPLICATION_JSON);
		httpClient = HttpClientBuilder.create().build();
		
		request.setEntity(entity);
		
		response = httpClient.execute(request);
		System.out.println(response.getStatusLine().getStatusCode());
		
	}

}
