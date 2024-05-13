package ting.li.accountsecurity.services;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ting.li.accountsecurity.configurations.JwtUtils;
import ting.li.accountsecurity.models.TokenModel;

import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountAuthenticationProvider implements AuthenticationProvider {

    @Value("${auth.baseurl}")
    private String authBaseurl;

    @Value("${auth.login.endpoint}")
    private String authLoginEndpoint;

    private final JwtUtils jwtUtils;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        // Make a request to the API to authenticate the user and obtain the JWT token
        final var apiUrl = authBaseurl + authLoginEndpoint;

        // Prepare the request body
        final MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("username", username);
        requestBody.add("password", password);

        // Send the request to the API
        final var restTemplate = new RestTemplate();
        final var responseEntity = restTemplate.postForEntity(apiUrl, requestBody, TokenModel.class);

        if (responseEntity.getStatusCode() != HttpStatus.CREATED) {
            throw new BadCredentialsException("Invalid username or password");
        }

        log.info("Login Success");

        final var responseBody = responseEntity.getBody();
        if (responseBody == null) {
            throw new BadCredentialsException("Access token not valid");
        }

        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username,password);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes();
        HttpSession session = request.getRequest().getSession(false);
        session.setAttribute("accessToken", responseBody.getAccessToken());
        session.setAttribute("refreshToken", responseBody.getRefreshToken());

        Date jwtExpiration = jwtUtils.extractExpiration(responseBody.getAccessToken());

        long sessionTimeoutInMillis = jwtExpiration.getTime() - System.currentTimeMillis();
        long sessionExpiration = sessionTimeoutInMillis / 1000;
        session.setMaxInactiveInterval((int) sessionExpiration);

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
