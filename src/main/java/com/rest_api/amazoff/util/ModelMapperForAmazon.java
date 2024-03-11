package com.rest_api.amazoff.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperForAmazon {

	@Bean
	public ModelMapper modelaMapping() {
		return new ModelMapper();
	}

}
