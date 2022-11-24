package resources;

public enum ApiResource {
	

	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	 private String resource;
	ApiResource(String resource){
		this.resource=resource;
	}
 public String getResource() {
	return resource;
	 
 }

}
