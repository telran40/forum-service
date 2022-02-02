package telran.java40.security.filter;

import java.io.IOException;

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

import telran.java40.accounting.dao.UserAccountRepository;
import telran.java40.accounting.model.UserAccount;

@Service
@Order(20)
public class AdminFilter implements Filter {
	
	UserAccountRepository repository;

	@Autowired
	public AdminFilter(UserAccountRepository repository) {
		this.repository = repository;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		if (checkEndPoints(request.getMethod(), request.getServletPath())) {
			UserAccount userAccount = repository.findById(request.getUserPrincipal().getName()).get();
			if(!userAccount.getRoles().contains("Administrator".toUpperCase())) {
				response.sendError(403);
				return;
			}
		}
		chain.doFilter(request, response);

	}

	private boolean checkEndPoints(String method, String path) {
		return path.matches("/account/user/\\w+/role/\\w+/?");
	}

}
