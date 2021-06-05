package com.example.wdm;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import org.junit.Before;
import org.springframework.http.*;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

//    //int daprPort = 3500;
//    String stateUrl = "http://localhost:3500/v1.0/state/statestore";
//    RestTemplate restTemplate = null;
//
//    @Before
//    public void setup() {
//        restTemplate = new RestTemplate();
//    }

    @RequestMapping("/")
    public String index() {

        return "Greetings from Spring Boot!";

    }

//    @GetMapping("/order")
//    public String getorder() {
//
//        String result = restTemplate.getForObject(stateUrl+"/order", String.class);
////        res.send(result);
//        System.out.println("getorder返回结果：" + result);
//        //Assert.hasText(result, "get_product1返回结果为空");
//
//        return "getorder";
//
//    }
//
//    @PostMapping("/neworder")
//    public String postorder(String s) {
//
//        MultiValueMap<String, String> header = new LinkedMultiValueMap();
//        header.add(HttpHeaders.CONTENT_TYPE, (MediaType.APPLICATION_FORM_URLENCODED_VALUE));
//
//        String productStr = s;
//        HttpEntity<String> request = new HttpEntity<>(productStr, header);
//        ResponseEntity<String> exchangeResult = restTemplate.exchange(stateUrl, HttpMethod.POST, request, String.class);
//        System.out.println("post_product1: " + exchangeResult);
//        //Assert.isTrue(exchangeResult.getStatusCode().equals(HttpStatus.OK), "post_product1 请求不成功");
//
//        return "neworder";
//
//    }

    @RequestMapping("/test")
    public String test() {

        try (DaprClient client = new DaprClientBuilder().build()) {
            System.out.println("Waiting for Dapr sidecar ...");
            client.waitForSidecar(10000).block();
            System.out.println("Dapr sidecar is ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "test";

    }

}