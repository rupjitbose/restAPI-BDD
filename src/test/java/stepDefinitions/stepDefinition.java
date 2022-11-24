package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.ApiResource;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefinition extends Utils{

	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	static String placeId;
	TestDataBuild data=new TestDataBuild();
	@Given("^Add place Payload ([^\"]*) ([^\"]*) ([^\"]*)$")
	public void add_place_payload(String addr,String lang, String name) throws IOException {
		
		
		 
		 res=given().log().all().spec(RequestSpecBulider())
		.body(data.addPlacePayload(addr,lang,name));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource,String method) {
		 
		 ApiResource apires=ApiResource.valueOf(resource);
		 if(method.equalsIgnoreCase("POST"))
			 response =res.when().post(apires.getResource());
		 else if(method.equalsIgnoreCase("GET"))
			 response =res.when().get(apires.getResource()); 
		 
		 
	}

	@Then("Api call is success with status code {int}")
	public void api_call_is_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);
		
	}

	 @And("{string} in response body is {string}")
	    public void in_response_body_is(String keyValue, String dataValue) {
	        
	       
	       //System.out.println(r);
	       assertEquals(getJsonPath(response, keyValue),dataValue);
	    }
	 
	 @And("verify place id created maps with {string} using {string}")
	 public void verify_place_id_created_maps_with_name_using(String expectedname, String resource) throws IOException {
		 
		 placeId= getJsonPath(response, "place_id");
		 res=given().log().all().spec(RequestSpecBulider().queryParam("place_id", placeId));
		 user_calls_with_post_http_request(resource,"GET");
		 String actualname= getJsonPath(response, "name");
		 assertEquals(actualname,expectedname);
	 }
	 @Given("Delete place Payload")
	 public void delete_place_payload() throws IOException {
		 res=given().log().all().spec(RequestSpecBulider()).body(data.deletePlacePayload(placeId));
	 }
}
