package com.formssi.mvp.controller.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.formssi.mvp.configuration.UserUpdate;
import com.formssi.mvp.pojo.SuccessResponse;
import com.formssi.mvp.pojo.User;
import com.formssi.mvp.pojo.UsersResponse;
import com.formssi.mvp.service.UserService;

import io.swagger.annotations.ApiParam;


@RestController
public class UserApiController implements UserApi {

    @Autowired
    private UserService userService;
    
    @Autowired
    private StringRedisTemplate template;
    
    private final static Logger logger = LoggerFactory.getLogger(UserApiController.class);
    /**
     * 查询用户列表
     */
    public ResponseEntity<UsersResponse> getUsers() {
    	logger.info("----开始执行用户列表查询操作----");
    	List<Object> values = template.opsForHash().values("user");
    	if(CollectionUtils.isEmpty(values)) {
    		return ResponseEntity.noContent().build();
    	}
    	List<User>users=new ArrayList<User>();
    	for (Object value : values) {
			String userStr=(String)value;
			User user = JSON.parseObject(userStr,User.class);
			users.add(user);
		}
    	UsersResponse response=new UsersResponse();
    	response.users(users);
    	
    	return ResponseEntity.ok(response);
    }
    
    
    public ResponseEntity<User> getUser(@NotNull @ApiParam(value = "根据id查询用戶信息，是唯一标识", required = true) @Validated @RequestParam(value = "id", required = true) Integer id){
    	//@Requestparam和@NotNull只能判断有没有id参数，不能判断id有没有值，所以要校验
    	
    	logger.info("----开始执行用户查询操作----");
    	String userStr =(String) template.opsForHash().get("user",id.toString());
    	if(userStr==null) {
    		 // 响应204，请求成功但找不到资源
            //return ResponseEntity.noContent().build();
    		//查询数据库并存入redis
    		User user = userService.findById(id);
    		if(user==null) {
    			return ResponseEntity.noContent().build();
    		}
    		template.opsForHash().put("user",String.valueOf(user.getUserId()),JSONObject.toJSONString(user));
    	}
    	User user = JSON.parseObject(userStr,User.class);
    	return ResponseEntity.ok(user);
    	
    	/*User user=userService.findById(id);
    	if(user==null){
    		 // 响应204，请求成功但找不到资源
            return ResponseEntity.noContent().build();
    	}
    	return ResponseEntity.ok(user);*/
    }

    
     public ResponseEntity<SuccessResponse> deleteUser(@ApiParam(value = "用户的唯一标示符",required=true) @PathVariable("id") Integer id){
    	//参数无需校验、不是键值对，null对于url是无意义的。如果是其他类型@PathVariable会转换错误返回400
    	logger.info("----开始执行用户删除操作----");
    	SuccessResponse response=new SuccessResponse();
		//先查找redis是否存在用户
		String exist =(String) template.opsForHash().get("user",id.toString());
		if(exist==null) {
			response.setStatus(false);
			logger.info("用户删除失败，不存在ID为" + id + "的用户，无法删除！");
			return ResponseEntity.noContent().build();
		}
		//删除不存在时捕获异常,返回不同状态
		userService.deleteById(id);
		response.setStatus(true);
		//删除redis用户,与数据库同步
		template.opsForHash().delete("user",id.toString());
		logger.info("用户删除成功！");
		return ResponseEntity.ok(response);
    }

    /**
     * 修改用户信息
     */
    public ResponseEntity<SuccessResponse> updateUser(@PathVariable("id")Integer id, @Validated(value=UserUpdate.class) @RequestBody User user) {
    	logger.info("----开始执行用户更新操作----");

    	SuccessResponse response=new SuccessResponse();
		//User existUser=userService.findById(id);
    	//查询redis
    	String existUser =(String) template.opsForHash().get("user",String.valueOf(id));
		if(existUser!=null) {
			userService.updateById(user);
			response.setStatus(true);
			//替换redis中原来的user
			template.opsForHash().put("user",String.valueOf(id), JSONObject.toJSONString(user));
			logger.info("保存用户"+user+"成功");
		}else {
			response.setStatus(false);
			logger.info("不存在id为"+id+"的用户！不能更新！");
		}
    	return ResponseEntity.ok(response);    
    	}
    
    /**
     * 添加一个用户
     */
	public ResponseEntity<SuccessResponse> addUser( User user) {
    	logger.info("----开始执行用户保存操作----");
    	if(user.getUserName() == null || user.getUserName() == ""){
    		//防止保存空对象
    		return ResponseEntity.badRequest().build();
    	}
    	SuccessResponse successResponse = new SuccessResponse();
    	try {
    		userService.save(user);
    		successResponse.setStatus(true);
    		//存入redis
    		template.opsForHash().put("user",String.valueOf(user.getUserId()),JSONObject.toJSONString(user));
    		
    		logger.info("用户:" + user + "保存成功！");
		} catch (Exception e) {
			//防止重复数据库保存失败,
			successResponse.setStatus(false);
			logger.error("用户:" + user + "保存失败！错误信息:" + e.getMessage());
		}
		return ResponseEntity.ok(successResponse);
	}
    
}
