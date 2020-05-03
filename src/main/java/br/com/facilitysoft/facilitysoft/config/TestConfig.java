package br.com.facilitysoft.facilitysoft.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.facilitysoft.facilitysoft.services.DBServices;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBServices dbServices;
	
	@Bean
	public boolean instantiateDataBase() throws ParseException {
		dbServices.instantiateTestDataBase();
		return true;
	}

}
