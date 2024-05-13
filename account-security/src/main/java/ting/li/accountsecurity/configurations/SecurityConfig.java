package ting.li.accountsecurity.configurations;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ting.li.accountsecurity.services.AccountAuthenticationProvider;
import ting.li.accountsecurity.services.AccountDetailService;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@Slf4j
public class SecurityConfig {
    private final AccountAuthenticationProvider accountAuthenticationProvider;
    private final JwtAthFilter jwtAthFilter;
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, AccountDetailService userDetailService)
            throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.authenticationProvider(accountAuthenticationProvider)
                .userDetailsService(userDetailService)
                .passwordEncoder(bCryptPasswordEncoder);
        return builder.build();
    }

    @Bean
    public  BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(request -> request
                        .requestMatchers("/posts/create").hasAuthority("post_write")
                        .requestMatchers("/user/**").hasAuthority("USER")
                        .requestMatchers("/stock/**","/signup","/posts/**","/login/**").permitAll()
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .anyRequest()
                        .authenticated())
                .headers(headers->headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .csrf(csrf->csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")))
                .formLogin(fl -> fl.loginPage("/login.html")
                        .loginProcessingUrl("login")
                        .successHandler(authenticationSuccessHandler())
                        .failureUrl("/login.html?error=true"))
                .rememberMe(rm -> rm.key("uniqueAndSecret").tokenValiditySeconds(86400))
                .logout(logout -> logout.deleteCookies("JSESSIONID"))
                .addFilterBefore(jwtAthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                log.info("onAuthenticationSuccess pending to redirect to different page logic");
            }
        };
    }

}
