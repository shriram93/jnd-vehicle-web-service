package com.udacity.pricing.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class PricingControllerTest {
    @Autowired
    private MockMvc mvc;

    /**
     * Tests for getting price of a vehicle based on id
     * @throws Exception when cant able to find vehicle by id
     */
    @Test
    public void getPrice() throws Exception {
        // Valid vehicle id
        mvc.perform(get(new URI("/price?vehicleId=1")))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString()
                .contains("\"currency\":\"USD\",\"price\":");

        // Invalid vehicle id
        mvc.perform(get(new URI("/price?vehicleId=1000")))
                .andExpect(status().isNotFound());
    }
}
