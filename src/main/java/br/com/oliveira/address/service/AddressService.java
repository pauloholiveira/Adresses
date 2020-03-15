package br.com.oliveira.address.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.oliveira.address.entity.Address;
import br.com.oliveira.address.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository repo;
	
	public List<Address> getAll() {
		Iterable<Address> addressesIter = repo.findAll();

        return StreamSupport.stream(addressesIter.spliterator(), false).collect(Collectors.toList());
		
	}
	
	public Address getAddressById(Long id) {
		Optional<Address> addressOpt = repo.findById(id);
		
		return addressOpt.isPresent() ? addressOpt.get() : null;
		
	}
	
	public Address insertAddress(Address address) {
		Address inserted = null;
		if(address != null) {
			inserted = repo.save(address);
		}
		
		return inserted;
	}
	
	public Address updateAddress(Address address) {
		Address updated = null;
		if(address != null) {
			updated = repo.save(address);
		}
		
		return updated;
	}
	
	public Long deleteAddressById(Long id) {
		if(id != null) {
			repo.deleteById(id);
		}
		
		return id;
	}
}
