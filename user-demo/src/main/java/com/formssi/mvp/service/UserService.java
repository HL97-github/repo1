package com.formssi.mvp.service;

import java.util.List;

import com.formssi.mvp.pojo.User;

public interface UserService {

	List<User> findAll();

	User findById(Integer id);

	void deleteById(Integer id);

	void updateById(User user);

	void save(User user);

}
