package com.vitali.cloud.jlong.simplewebservice;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class SimpleWebServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CatRepository catRepository;

    @Before
    public void before() {
        Stream.of("Felix", "Garfield", "Whiskers")
                .forEach(name -> catRepository.save(new Cat(name)));
    }

    @Test
    public void catsReflectedInRead() throws Exception {
        MediaType halJson = MediaType
                .parseMediaType("application/hal+json;charset=UTF-8");
        mockMvc
                .perform(get("/cats"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(halJson))
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    Assert.assertTrue(contentAsString.split("totalElements")[1]
                            .split(":")[1].trim()
                            .split(",")[0].equals("3"));
                });
    }

    @Test
    public void contextLoads() {
    }
}
