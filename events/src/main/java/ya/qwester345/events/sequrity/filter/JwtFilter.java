//package ya.qwester345.events.sequrity.filter;
//
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import ya.qwester345.events.sequrity.utils.JwtTokenUtil;
//
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//import static org.apache.logging.log4j.util.Strings.isEmpty;
//
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//
//    private final UserDetailsManager userManager;
//
//    public JwtFilter(InMemoryUserDetailsManager userManager) {
//        this.userManager = userManager;
//    }
//
//    private String token;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain chain)
//            throws ServletException, IOException {
//        token = request.getHeader(HttpHeaders.AUTHORIZATION);
//        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (isEmpty(header) || !header.startsWith("Bearer ")) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        // Get jwt token and validate
//        final String token = header.split(" ")[1].trim();
//        if (!JwtTokenUtil.validate(token)) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        // Get user identity and set it on the spring security context
//        UserEntity userDetails = userManager
//                .findUserEntitiesByEmail(JwtTokenUtil.getEmail(token));
//
//        UsernamePasswordAuthenticationToken
//                authentication = new UsernamePasswordAuthenticationToken(
//                userDetails, null,
//                userDetails == null ?
//                        List.of() : userDetails.getAuthorities()
//        );
//
//        authentication.setDetails(
//                new WebAuthenticationDetailsSource().buildDetails(request)
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        chain.doFilter(request, response);
//    }
//
//    public String getToken() {
//        return token;
//    }
//}