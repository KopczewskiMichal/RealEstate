package springServer.oauth2Config;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Bean
public JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withSecretKey("secret_key").build(); // Użyj tego samego klucza co przy generowaniu JWT
}

@Override
protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
            .antMatchers("/public/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .oauth2Login()
            .userInfoEndpoint()
            .userService(oAuth2UserService())
            .and()
            .successHandler((request, response, authentication) -> {
                OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
                String jwt = generateToken(token.getPrincipal());
                response.addHeader("Authorization", "Bearer " + jwt);
            })
            .and()
            .oauth2ResourceServer()
            .jwt();
}
