package com.fleabagsolutions.productservice;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleabagsolutions.productservice.dto.ProductRequest;

import org.testcontainers.junit.jupiter.Container;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest // Indicates that this is a Spring Boot test and should load the application context.
@Testcontainers // Enables support for Testcontainers in the test. This is a JUnit Jupiter extension.
@AutoConfigureMockMvc // Auto-configures MockMvc for testing Spring MVC controllers without starting a full HTTP server.
class ProductServiceApplicationTests {

	@Container
	// Declares a MongoDB test container. Testcontainers will start and stop this container around the test methods.
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

	@Autowired
	// Injects the MockMvc instance. MockMvc is used to simulate HTTP requests and assert responses in Spring MVC.
	private MockMvc mockMvc;

	@Autowired    
	// Injects an ObjectMapper instance. This is used to convert Java objects to JSON and vice versa.
	private ObjectMapper objectMapper;

	@DynamicPropertySource
	// Dynamically sets properties for the test context. Here, it's configuring the MongoDB URI to the test container.
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void shouldCreateProduct() throws Exception{
		ProductRequest productRequest = getProductRequest();
		String productRequestString = objectMapper.writeValueAsString(productRequest);
    
		// Performs an HTTP POST request to "/api/product" with the productRequestString as the body.
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
							.contentType(MediaType.APPLICATION_JSON)
							.content(productRequestString))
						.andExpect(status().isCreated());
	}
	
	// Helper method to create a ProductRequest object using the builder pattern.
	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
									.name("iphone 13")
									.description("iphone 13")
									.price(BigDecimal.valueOf(1200))
									.build();
	}

}
