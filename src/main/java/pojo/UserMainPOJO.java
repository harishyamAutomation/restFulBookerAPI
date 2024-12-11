package pojo;

import java.util.List;

public class UserMainPOJO {
	
	private String name;
	private String job;
	private String id;
	private List<CityList> city;
	
	public List<CityList> getCity() {
		return city;
	}
	public void setCity(List<CityList> city) {
		this.city = city;
	}	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
}
