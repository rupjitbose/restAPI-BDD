package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		stepDefinition step=new stepDefinition();
		if(stepDefinition.placeId==null) {
			step.add_place_payload("addr","lang","name");
			step.user_calls_with_post_http_request("AddPlaceAPI","post");
			step.verify_place_id_created_maps_with_name_using("name","GetPlaceAPI");
		}
		
	}
	
}
