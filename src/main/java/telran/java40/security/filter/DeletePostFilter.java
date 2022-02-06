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

import telran.java40.forum.dao.PostRepository;
import telran.java40.forum.model.Post;
import telran.java40.security.SecurityContext;
import telran.java40.security.UserProfile;

@Service
@Order(50)
public class DeletePostFilter implements Filter {

	PostRepository postRepository;
	SecurityContext securityContext;

	@Autowired
	public DeletePostFilter(PostRepository postRepository, SecurityContext securityContext) {
		this.postRepository = postRepository;
		this.securityContext = securityContext;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String path = request.getServletPath();
		if (checkEndPoints(request.getServletPath(), request.getMethod())) {
			Principal principal = request.getUserPrincipal();
			String[] arr = path.split("/");
			String postId = arr[arr.length - 1];
			Post post = postRepository.findById(postId).orElse(null);
			if (post == null) {
				response.sendError(404, "post id = " + postId + " not found");
				return;
			}
			String author = post.getAuthor();
			UserProfile userProfile = securityContext.getUser(principal.getName());
			if (!(principal.getName().equals(author) || userProfile.getRoles().contains("MODERATOR".toUpperCase()))) {
				response.sendError(403);
				return;
			}
		}
		chain.doFilter(request, response);

	}

	private boolean checkEndPoints(String path, String method) {
		return path.matches("/forum/post/\\w+/?") && "Delete".equalsIgnoreCase(method);

	}

}
