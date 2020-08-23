package com.formssi.mvp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formssi.mvp.dao.UserDao;
import com.formssi.mvp.pojo.User;
import com.formssi.mvp.service.UserService;
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	@Override
	public List<User> findAll() {
		List<User> users = userDao.findAll();
		return users;
	}
	@Override
	public User findById(Integer id) {
		Optional<User> optional = userDao.findById(id);
		//增加空判断
		if(optional!=null&&optional.isPresent()){
			return optional.get();
		}
		return null;
	}
	@Override
	public void deleteById(Integer id) {
		userDao.deleteById(id);
	}
	@Override
	public void updateById(User user) {
		userDao.save(user);
	}
	@Override
	public void save(User user) {
		//int i=1/0;
		userDao.save(user);
	}
	

}
