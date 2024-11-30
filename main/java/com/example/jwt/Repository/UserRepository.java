package com.example.jwt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.jwt.Model.UserModel;

public interface UserRepository extends JpaRepository<UserModel,Long>{
	
	UserModel findByUsername(String username);

}
