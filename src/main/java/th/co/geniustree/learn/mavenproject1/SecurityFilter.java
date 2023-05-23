package th.co.geniustree.learn.mavenproject1;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/*"})
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
        System.out.println("-------before--------");
        HttpServletRequest httpReq = (HttpServletRequest) request;
        String username = (String)httpReq.getSession().getAttribute(LoginServlet.SESSION_KEY);
        if (httpReq.getRequestURI().endsWith("login") || httpReq.getRequestURI().endsWith("logout")) {
            fc.doFilter(request, response);
        } else {
            if (username != null) {
                fc.doFilter(request, response);
            } else {
                HttpServletResponse httpRes = (HttpServletResponse) response;
                httpRes.sendError(403);

            }
        }
        System.out.println("-------after--------");
    }

}
