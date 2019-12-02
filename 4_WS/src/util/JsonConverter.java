package util;

import com.google.gson.Gson;

public class JsonConverter {
	private Gson gson;
	
	public JsonConverter(){
	gson = new Gson();	
	}
	
	public String toJson(String key, Object value){
		return "{\"" + key + "\" : "+ gson.toJson(value);
	}

}

