package br.com.oliveira.address.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.oliveira.address.entity.Address;

@Repository
public interface AddressRepository  extends CrudRepository<Address, Long>{


}
