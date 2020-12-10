package com.benarczyk.kris.carrental;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWireMock(port= 6544)
public class CarRentalApplicationTests {

	@Test
	public void should_return_a_list_of_frauds() {
		// given: a stubbed server
        String expectedResponse = "['marcin', 'krzysiek']";
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/fraud"))
                .willReturn(WireMock.aResponse().withBody(expectedResponse)));

        // when: I send a request
        String response = new RestTemplate().getForObject("http://localhost:6544/fraud", String.class);
        // then: I get a list of frauds
        then(response).contains(expectedResponse);
	}

}
