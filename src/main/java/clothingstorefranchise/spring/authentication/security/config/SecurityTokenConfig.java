package clothingstorefranchise.spring.authentication.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

import clothingstorefranchise.spring.common.constants.Rol;
import clothingstorefranchise.spring.common.security.config.JwtConfiguration;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import java.util.Arrays;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {
    protected final JwtConfiguration jwtConfiguration;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().configurationSource(request -> {	
                	CorsConfiguration cors = new CorsConfiguration();                 
                    cors.setAllowedMethods(
                      Arrays.asList( 
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.OPTIONS.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.HEAD.name()
                   ));   
                   cors.applyPermitDefaultValues();           
                   return cors;   
               })
                .and()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint((req, resp, e) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .antMatchers(jwtConfiguration.getLoginUrl(), "/**/swagger-ui.html").permitAll()
                .antMatchers(HttpMethod.GET, "/**/swagger-resources/**", "/**/webjars/springfox-swagger-ui/**", "/**/v2/api-docs/**").permitAll()
                
                .antMatchers(HttpMethod.GET,"/user/**").hasAnyRole(Rol.CUSTOMER,Rol.ADMIN, Rol.WAREHOUSE_EMPLOYEE, Rol.SHOP_EMPLOYEE)
                .antMatchers(HttpMethod.DELETE,"/user/**").hasAnyRole(Rol.CUSTOMER,Rol.ADMIN)
                .antMatchers(HttpMethod.POST,"/user/**").permitAll()
                .anyRequest().authenticated();
        }
}
