package pl.kielce.tu.travel_agency.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.kielce.tu.travel_agency.security.CustomUserDetailsService;
import pl.kielce.tu.travel_agency.security.RestAuthenticationEntryPoint;
import pl.kielce.tu.travel_agency.security.SecurityUtils;
import pl.kielce.tu.travel_agency.security.jwt.JWTConfigurer;
import pl.kielce.tu.travel_agency.security.jwt.JWTTokenProvider;

@Configuration
@EnableWebSecurity
@CrossOrigin
public class WebApplicationConfig extends WebSecurityConfigurerAdapter {

    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    private final SimpleUrlAuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();

    private final CustomUserDetailsService userDetailsService;

    private final JWTTokenProvider tokenProvider;

    private final SecurityUtils utils;

    @Autowired
    public WebApplicationConfig(RestAuthenticationEntryPoint restAuthenticationEntryPoint,
                                CustomUserDetailsService userDetailsService, JWTTokenProvider tokenProvider, SecurityUtils utils) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
        this.utils = utils;
    }

    @Bean("authenticationManager")
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(getPasswordEncoder());
        return authProvider;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/user/authenticate", "/user/register", "/*", "/index.html")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/mvc/country/add",
                        "/mvc/country/edit",
                        "/mvc/country/delete/**"
                )
                .hasAuthority("EMPLOYEE")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .logout()
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                .and()
                .apply(securityConfigurerAdapter());
    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider, utils);
    }
}
