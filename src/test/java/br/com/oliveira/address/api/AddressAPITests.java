package br.com.oliveira.address.api;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;

import br.com.oliveira.address.api.dtos.AddressDTO;
import br.com.oliveira.address.entity.Address;
import br.com.oliveira.address.repository.AddressRepository;
import br.com.oliveira.address.service.AddressService;
import br.com.oliveira.address.service.GeoLocationService;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
//import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;

//@RunWith(SpringRunner.class)
@WebMvcTest
public class AddressAPITests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AddressService stockService;

	@MockBean
	private GeoLocationService getLocationService;
	
	@MockBean
	private AddressRepository repo;

	@Test
	public void listAddressEmpty_then204IsReceived() throws Exception {

		List<Address> addresses = new ArrayList<Address>();
		given(stockService.getAll()).willReturn(addresses);

		this.mockMvc.perform(get("/addresses")).andExpect(status().isNoContent());
	}
	
	@Test
	public void listAddressWithElements_then200IsReceived() throws Exception {

		Address address = new Address();
		address.setId(1L);
		address.setStreetName("Rua Saldanha da Gama");
		address.setNumber("230");
		address.setComplement("");
		address.setNeighbourhood("Alto da Lapa");
		address.setCity("São Paulo");
		address.setState("SP");
		address.setCountry("Brasil");
		address.setZipcode("05081-000");
		address.setLatitude("");
		address.setLongitude("");
		
		List<Address> addresses = Arrays.asList(address);
		given(stockService.getAll()).willReturn(addresses);

		this.mockMvc.perform(get("/addresses")).andExpect(status().isOk());
	}
	
	@Test
	public void listAddressWithElements_CorrectContent() throws Exception {

		Address address = new Address();
		address.setId(1L);
		address.setStreetName("Rua Saldanha da Gama");
		address.setNumber("230");
		address.setComplement("");
		address.setNeighbourhood("Alto da Lapa");
		address.setCity("São Paulo");
		address.setState("SP");
		address.setCountry("Brasil");
		address.setZipcode("05081-000");
		address.setLatitude("");
		address.setLongitude("");
		
		List<Address> addresses = Arrays.asList(address);
		given(stockService.getAll()).willReturn(addresses);
		
		this.mockMvc.perform(get("/addresses"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$", hasSize(1)))
		;
	}
	
	//@Test
	public void insertAddress() throws Exception {
		String addressJson = "{\"streetName\":\"Rua Saldanha da Gama\",\"number\":\"230\",\"neighbourhood\":\"Alto da Lapa\",\"city\":\"São Paulo\",\"state\":\"SP\",\"country\":\"Brasil\",\"zipcode\":\"05081-000\",\"latitude\":\"-23.5285193\",\"longitude\":\"-46.7117078\"}";

		Address address = new Address();
		//address.setId(1L);
		address.setStreetName("Rua Saldanha da Gama");
		address.setNumber("230");
		//address.setComplement("");
		address.setNeighbourhood("Alto da Lapa");
		address.setCity("São Paulo");
		address.setState("SP");
		address.setCountry("Brasil");
		address.setZipcode("05081-000");
		address.setLatitude("-23.5285193");
		address.setLongitude("-46.7117078");
		
		Gson gson = new Gson();
		
		Address address2 = new Address();
		address2.setId(1L);
		address2.setStreetName("Rua Saldanha da Gama");
		address2.setNumber("230");
		//address2.setComplement("");
		address2.setNeighbourhood("Alto da Lapa");
		address2.setCity("São Paulo");
		address2.setState("SP");
		address2.setCountry("Brasil");
		address2.setZipcode("05081-000");
		address2.setLatitude("-23.5285193");
		address2.setLongitude("-46.7117078");
		given(stockService.insertAddress(address)).willReturn(address2);
		when(stockService.insertAddress(address)).thenReturn(address2);
		
//        mockMvc.perform(post("/addresses").characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(address))
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
        
        mockMvc.perform( MockMvcRequestBuilders
        	      .post("/addresses")
        	      .content(gson.toJson(address))
        	      .contentType(MediaType.APPLICATION_JSON)
        	      .accept(MediaType.APPLICATION_JSON))
        	      .andExpect(status().isCreated())
        	      .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
        

	}
}
