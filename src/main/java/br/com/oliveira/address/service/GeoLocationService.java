package br.com.oliveira.address.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;

import br.com.oliveira.address.api.forms.AddressForm;

@Service
public class GeoLocationService {

	public static final String APIKEY = "AIzaSyBm3xh9oZP1ksMWcMzVaZQevWlrtb8tIgc";
	
	public LatLng getLatLong(AddressForm addform) throws ApiException, InterruptedException, IOException {
		
		String fullAddress = addform.getNumber().concat(" ")
				.concat(addform.getStreetName()).concat(" ")
				.concat(addform.getNeighbourhood()).concat(", ")
				.concat(addform.getState()).concat(" ")
				.concat(addform.getCity()).concat(" ")
				.concat(addform.getZipcode());
		
		GeoApiContext context = new GeoApiContext.Builder().apiKey(APIKEY).build();
		
		GeocodingResult[] results =  GeocodingApi.geocode(context, fullAddress).await();
		
		Geometry geo = results[0].geometry;

		return geo.location;
	}
}
