package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	
	
	 public static RequestSpecification req;
	
	public RequestSpecification RequestSpecBulider() throws IOException {
		if(req==null) {
		PrintStream log=new PrintStream(new FileOutputStream("log.txt"));
		req =new RequestSpecBuilder().setBaseUri(getdata("baseuri")).addQueryParam("key", "qaclick123")
		.addFilter(RequestLoggingFilter.logRequestTo(log))
		.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return req;
		}
		return req;
	
	}
	public static String getdata(String key) throws IOException {
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\Rupjit\\eclipse-workspace\\apiBDD\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		
	}
	public String getJsonPath(Response response,String key) {
		
		String r=response.asString();
        JsonPath js=new JsonPath(r);
        return js.getString(key);
	}

}
