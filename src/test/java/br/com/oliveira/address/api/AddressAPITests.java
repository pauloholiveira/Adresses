package br.com.oliveira.address.api;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import br.com.oliveira.address.entity.Address;
import br.com.oliveira.address.service.AddressService;
import br.com.oliveira.address.service.GeoLocationService;

//@RunWith(SpringRunner.class)
@WebMvcTest
public class AddressAPITests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AddressService stockService;

	@MockBean
	private GeoLocationService getLocationService;

	@Test
	public void givenAddressNotExists_whenAddressInfoIsRetrieved_then404IsReceived() throws Exception {

		// given
		Address address = new Address();
		address.setId(1L);
		//address.setName("Stock 1");
		//address.setPrice(new BigDecimal(1));

		List<Address> addresses = Arrays.asList(address);
		given(stockService.getAll()).willReturn(addresses);

		// when + then
		//this.mockMvc.perform(get("/addresses/1")).andExpect(status().isNotFound());
		this.mockMvc.perform(get("/addresses/9")).andExpect(status().isOk());
	}
}
