package test.dto;

public class TravelDto {
	private int num;
	private String country;
	private String city;
	
	public TravelDto() {}
	
	public TravelDto(int num, String country, String city) {
		super();
		this.num = num;
		this.country = country;
		this.city = city;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
}
