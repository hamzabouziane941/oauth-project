package com.oauth.project.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MyResourceController {

  @GetMapping("/myResources")
  public String findResources(OAuth2AuthenticationToken token) throws JsonProcessingException {
    log.info("OAuth2 token : '{}'", token.getPrincipal());
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(MyResource.builder().name("My Resource").build());
  }
}
