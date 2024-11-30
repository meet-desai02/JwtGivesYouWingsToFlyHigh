package com.example.jwt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jwt.Model.MrRegistrationModel;

@Repository
public interface RegistrationRepo extends JpaRepository<MrRegistrationModel, Long>{

}
