package br.com.oliveira.address.api;

import java.util.List;

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

import br.com.oliveira.address.api.dtos.AddressDTO;
import br.com.oliveira.address.api.forms.AddressForm;
import br.com.oliveira.address.entity.Address;
import br.com.oliveira.address.service.AddressServices;

@RestController
@RequestMapping("addresses")
public class AddressAPI {

	@Autowired
	private AddressServices addressService;
	
	@PostMapping
	public ResponseEntity<AddressDTO> addAddress(@RequestBody AddressForm addressForm) {
		Address address = addressForm.toAddress();
		
		Address inserted = addressService.insertAddress(address);
		
		return new ResponseEntity<>(new AddressDTO(inserted), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<AddressDTO>> getAllAddress() {
		return new ResponseEntity<>(AddressDTO.converter(addressService.getAll()), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<AddressDTO> getAddress(@PathVariable Long id) {
		Address address = addressService.getAddressById(id);
		//TODO: return not found if no found on DB.
		return new ResponseEntity<>(new AddressDTO(address), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressForm addressForm) {
		Address address = addressForm.toAddress();
		
		Address inserted = addressService.updateAddress(address);
		
		return new ResponseEntity<>(new AddressDTO(inserted), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Long> deleteAddress(@PathVariable Long id) {
		Long idDeleted = addressService.deleteAddressById(id);
		//TODO: return not found if no found on DB.
		return new ResponseEntity<>(idDeleted, HttpStatus.OK);
	}
}
