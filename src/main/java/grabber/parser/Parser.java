package grabber.parser;

import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

public class Parser implements IParser{
	private JsonReader jsonReader;
	private JsonObject jsonObject;
	private JsonArray jsonArray;
	private JsonObjectBuilder object;
	private String news;

	public String parser(InputStream inputStream) {
		jsonReader = Json.createReader(inputStream);
		jsonObject = jsonReader.readObject();
		
		jsonArray = jsonObject.getJsonArray("articles");
		news = jsonArray.getJsonObject(0).getString("title");
		jsonReader.close();
		return news;
	}

	public String jsonCreate(String text) {
		object = Json.createObjectBuilder();
		object.add("text", text);
		jsonObject = object.build();
		return jsonObject.toString();
	}

}
