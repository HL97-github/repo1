package com.formssi.mvp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.formssi.mvp.pojo.User;

public interface UserDao extends JpaSpecificationExecutor<User>,JpaRepository<User, Integer>{

}
