package com.TaskManager.App.Repository;

import com.TaskManager.App.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findByUsername(String username);
    User deleteByUsername(String username);
}
