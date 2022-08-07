package ya.qwester345.events.sequrity.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import ya.qwester345.events.dto.UserDto;
import ya.qwester345.events.sequrity.utils.JwtTokenUtil;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Value("${users.service.url}")
    private String userServiceUrl;

    private final RestTemplate template;
    private final ObjectMapper mapper;

    public JwtFilter(RestTemplateBuilder template,
                     ObjectMapper mapper) {
        this.template = template.build();
        this.mapper = mapper;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        // Get jwt token and validate
        final String token = header.split(" ")[1].trim();
        if (!JwtTokenUtil.validate(token)) {
            chain.doFilter(request, response);
            return;
        }

        //Going to the user-service and getting information
        ClientHttpRequest httpRequest = template.getRequestFactory()
                .createRequest(URI.create(userServiceUrl), HttpMethod.GET);

        httpRequest.getHeaders()
                .put(HttpHeaders.AUTHORIZATION, List.of(header));

        UserDto user;

        try(ClientHttpResponse execute = httpRequest.execute()) {
            if (!execute.getStatusCode().is2xxSuccessful()) {
                response.setStatus(401);
                return;
            }
        try{
            user = mapper.readValue(execute.getBody(), UserDto.class);
        }catch (IOException e){
            user = null;
        }
        }

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                user, null,
                user == null ?
                        List.of() : user.getAuthorities()
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}