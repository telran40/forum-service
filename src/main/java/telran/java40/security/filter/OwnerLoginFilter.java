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

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(30)
public class OwnerLoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String path = request.getServletPath();
		if(checkEndPoints(path, request.getMethod())) {
			Principal principal = request.getUserPrincipal();
			String[] arr = path.split("/");
			String user = arr[arr.length - 1];
			if (!user.equals(principal.getName())) {
				response.sendError(403);
				return;
			}
		}
		chain.doFilter(request, response);

	}
	private boolean checkEndPoints(String path, String method) {
		return path.matches("/account/user/\\w+/?") ||
				path.matches("/forum/post/\\w+/comment/\\w+/?") ||
				(path.matches("/forum/post/\\w+/?") &&  "Post".equalsIgnoreCase(method));
				
	}

}
