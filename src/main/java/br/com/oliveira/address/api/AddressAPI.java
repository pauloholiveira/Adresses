package br.com.oliveira.address.api;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;

import br.com.oliveira.address.api.dtos.AddressDTO;
import br.com.oliveira.address.api.forms.AddressForm;
import br.com.oliveira.address.entity.Address;
import br.com.oliveira.address.service.AddressService;
import br.com.oliveira.address.service.GeoLocationService;

@RestController
@RequestMapping("addresses")
public class AddressAPI {

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private GeoLocationService locationService;
	
	@PostMapping
	public ResponseEntity<AddressDTO> addAddress(@Valid @RequestBody AddressForm addressForm) throws ApiException, InterruptedException, IOException {
		
		if(addressForm.getId() != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(addressForm.getLatitude() == null || addressForm.getLongitude() == null) {
			LatLng latlng = locationService.getLatLong(addressForm);
				
			String lat = Double.toString(latlng.lat);
			addressForm.setLatitude(lat);
			String lng = Double.toString(latlng.lng);
			addressForm.setLongitude(lng);
			
		}
		
		Address address = addressForm.toAddress();
		Address inserted = addressService.insertAddress(address);
		
		return new ResponseEntity<>(new AddressDTO(inserted), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<AddressDTO>> getAllAddress() {
		List<Address> addresses = addressService.getAll();
		if(addresses == null || addresses.isEmpty()) {
			return new ResponseEntity<>(AddressDTO.converter(addresses), HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(AddressDTO.converter(addresses), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<AddressDTO> getAddress(@PathVariable Long id) {
		Address address = addressService.getAddressById(id);
		AddressDTO addressDto = null;
		
		if(address == null) {
			return new ResponseEntity<>(addressDto, HttpStatus.NOT_FOUND);
		}
		
		addressDto = new AddressDTO(address);
		return new ResponseEntity<>(addressDto, HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressForm addressForm, @PathVariable Long id) {
		if(addressForm.getId() != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Address getted = addressService.getAddressById(id);
		if(getted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Address address = addressForm.toAddress();
		address.setId(id);
		
		Address updated = addressService.updateAddress(address);
		if(updated == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Long> deleteAddress(@PathVariable Long id) {
		Long idDeleted = addressService.deleteAddressById(id);
		
		if(idDeleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(idDeleted, HttpStatus.NO_CONTENT);
	}
}
