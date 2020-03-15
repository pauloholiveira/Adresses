package br.com.oliveira.address.api.dtos;


import java.util.List;
import java.util.stream.Collectors;

import br.com.oliveira.address.entity.Address;

public class AddressDTO {
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
    
    
    public AddressDTO() {
    	super();
    }
    
    public AddressDTO(Address address) {
    	if(address !=null) {
			this.setId(address.getId());
			this.setStreetName(address.getStreetName());
			this.setNumber(address.getNumber());
			this.setComplement(address.getComplement());
			this.setNeighbourhood(address.getNeighbourhood());
			this.setCity(address.getCity());
			this.setState(address.getState());
			this.setCountry(address.getCountry());
			this.setZipcode(address.getZipcode());
			this.setLatitude(address.getLatitude());
			this.setLongitude(address.getLongitude());
    	}
	}
    
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
	
	public static List<AddressDTO> converter(List<Address> adresses) {
		return adresses.stream().map(address -> new AddressDTO(address)).collect(Collectors.toList());
		
	}
}
