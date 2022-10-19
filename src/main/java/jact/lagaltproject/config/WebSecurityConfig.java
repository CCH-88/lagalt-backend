package jact.lagaltproject.config;


import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
public class WebSecurityConfig {
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                // Enable CORS -- this is further configured on the controllers
                .cors().and()
                // Sessions will not be used
                .sessionManagement().disable()
                // Disable CSRF -- not necessary when there are no sessions
                .csrf().disable()
                // Enable security for http requests
                .authorizeHttpRequests(authorize -> authorize
                        // Specify paths where public access is allowed
                        .mvcMatchers("/public").permitAll()
                        // Specify paths to be protected with scope
                        //.mvcMatchers("/products").hasAuthority("SCOPE_profile")
                        //.mvcMatchers("/products").hasAuthority("client-role")
                        // Specify paths to be protected with role
                        //.mvcMatchers("/api/v1/resources/roles").hasRole("ADMIN")
                        // All remaining paths require authentication
                        .antMatchers(HttpMethod.GET, "/*").permitAll()
                        .antMatchers(HttpMethod.OPTIONS, "/*").permitAll()
                        .antMatchers(HttpMethod.HEAD, "/*").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtRoleAuthenticationConverter());

        return http.build();

    }
    public JwtAuthenticationConverter jwtRoleAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        // Use roles claim as authorities
        grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        // Add the ROLE_ prefix - for hasRole
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }
}
