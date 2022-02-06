package telran.java40.security.filter;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import telran.java40.security.SecurityContext;

@Service
@Order(10000)
public class ClearSecurityContextFilter implements Filter {
	SecurityContext securityContext;
	
	@Autowired
	public ClearSecurityContextFilter(SecurityContext securityContext) {
		this.securityContext = securityContext;
	}


	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		Principal principal = request.getUserPrincipal();
		if(principal != null) {
			securityContext.removeUser(principal.getName());
		}
		chain.doFilter(request, response);
	}

}
