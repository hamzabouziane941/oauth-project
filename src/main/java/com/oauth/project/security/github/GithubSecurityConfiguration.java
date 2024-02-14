package com.oauth.project.security.github;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("github-configuration")
public class GithubSecurityConfiguration {


  @Value("${github.client-id}")
  private String clientId;

  @Value("${github.client-secret}")
  private String clientSecret;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .oauth2Login(customizer -> customizer.clientRegistrationRepository(clientRepository()))
        .authorizeHttpRequests()
        .anyRequest()
        .authenticated();
    return http.build();
  }

  private ClientRegistrationRepository clientRepository() {
    return new InMemoryClientRegistrationRepository(githubClientRegistration());
  }

  private ClientRegistration githubClientRegistration() {
    return CommonOAuth2Provider.GITHUB
        .getBuilder("github")
        .clientId(clientId)
        .clientSecret(clientSecret)
        .build();
  }
}
