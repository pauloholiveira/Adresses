package br.com.oliveira.address.api.forms;

import br.com.oliveira.address.entity.Address;

public class AddressForm {
	private Long id;
    private String streetName;
    private String number;
    private String complement;
    private String neighbourhood;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String latitude;
    private String longitude;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	public String getNeighbourhood() {
		return neighbourhood;
	}
	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
    
    public Address toAddress() {
    	Address address = new Address();
    	
    	address.setId(this.getId());
    	address.setStreetName(this.getStreetName());
    	address.setNumber(this.getNumber());
    	address.setComplement(this.getComplement());
    	address.setNeighbourhood(this.getNeighbourhood());
    	address.setCity(this.getCity());
    	address.setState(this.getState());
    	address.setCountry(this.getCountry());
    	address.setZipcode(this.getZipcode());
    	address.setLatitude(this.getLatitude());
    	address.setLongitude(this.getLatitude());
    	
    	return address;
    }

}
