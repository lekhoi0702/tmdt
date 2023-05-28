package springboot.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class Collector<T> {
	private static RestClient rc = new RestClient();


	private static ObjectMapper objectMapper = new ObjectMapper();

	



	public static <T> List<T> getListAll(String url, Class<T> elementClass) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		List<T> list = null;
		CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, elementClass);
		String json=rc.get(url);
		System.out.println(json);

		list = objectMapper.readValue(rc.get(url), listType);
		System.out.println();

		return list;

	}

	@SuppressWarnings("hiding")
	public static <K> K postObj(String url, K pojo, Class<K> clazz) {
		K obj = null;

		try {
			String json= objectMapper.writeValueAsString(pojo);
			System.out.println( json);

			obj = objectMapper.readValue(rc.post(url, json), clazz);
			System.out.println(rc.post(url, objectMapper.writeValueAsString(pojo)));

		} catch (JsonParseException e) {
			System.out.print("loi json");

			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			System.out.print("loi mapping");

			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.print("chua bat api");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;

	}
	
	@SuppressWarnings("hiding")
	public static <K> String postMess(String url, K pojo) {
		String obj = null;

		try {
			System.out.println( "js: "+objectMapper.writeValueAsString(pojo));
			obj = rc.post(url, objectMapper.writeValueAsString(pojo));
			

		} catch (JsonParseException e) {
			System.out.print("loi json");

			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			System.out.print("loi mapping");

			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.print("chua bat api");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;

	}


}
