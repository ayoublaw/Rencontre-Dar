package Controlleur.Filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebFilter(filterName = "AuthentificationFilter")
public class AuthentificationFilter implements Filter {
    public String loginPage = "";
    public List<String> ExcludUrls;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;


        boolean isExcludedURL = false;
        for (String excludedURL : ExcludUrls) {
            if (request.getRequestURL().indexOf(excludedURL) > -1) {
                isExcludedURL = true;
                break;
            }
        }

        System.out.println(request.getRequestURL());

        if (isExcludedURL) {
            chain.doFilter(request, response);
        } else {
            HttpSession session;
            session = request.getSession(false);
            if (session == null || session.getAttribute("Email") == null) {
                response.sendRedirect(request.getContextPath() + "/login");
            } else {
                chain.doFilter(request, response);
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {
        ExcludUrls = new ArrayList<>();
        loginPage = config.getInitParameter("loginPage");
        Collections.addAll(ExcludUrls, config.getInitParameter("ExcludesUrls").split(";"));


    }

}
