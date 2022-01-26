package telran.java40.forum.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java40.forum.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {

}
