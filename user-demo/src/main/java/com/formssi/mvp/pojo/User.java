package com.formssi.mvp.pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.formssi.mvp.configuration.UserUpdate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * User
 */
@Validated
@Entity
@Table(name = "t_user")
@Data
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty("userId")
	@NotNull(groups=UserUpdate.class)
	private Integer userId = null;

	@JsonProperty("userName")
	private String userName = null;

	@JsonProperty("gender")
	private String gender = null;

	@JsonProperty("age")
	private Integer age = null;

	@JsonProperty("dateOfBirth")
	private LocalDate dateOfBirth = null;

	public User userId(Integer userId) {
		this.userId = userId;
		return this;
	}

	/**
	 * 用户的唯一id
	 * 
	 * @return userId
	 **/
	@ApiModelProperty(value = "用户的唯一id")

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public User userName(String userName) {
		this.userName = userName;
		return this;
	}

	/**
	 * 用户名
	 * 
	 * @return userName
	 **/
	@ApiModelProperty(value = "用户名")

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public User gender(String gender) {
		this.gender = gender;
		return this;
	}

	/**
	 * 性別
	 * 
	 * @return gender
	 **/
	@ApiModelProperty(value = "性別")

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public User age(Integer age) {
		this.age = age;
		return this;
	}

	/**
	 * 年齡
	 * 
	 * @return age
	 **/
	@ApiModelProperty(value = "年齡")

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public User dateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		return this;
	}

	/**
	 * 生日
	 * 
	 * @return dateOfBirth
	 **/
	@ApiModelProperty(value = "生日")

	@Valid

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		User user = (User) o;
		return Objects.equals(this.userId, user.userId) && Objects.equals(this.userName, user.userName)
				&& Objects.equals(this.gender, user.gender) && Objects.equals(this.age, user.age)
				&& Objects.equals(this.dateOfBirth, user.dateOfBirth);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, userName, gender, age, dateOfBirth);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class User {\n");

		sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
		sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
		sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
		sb.append("    age: ").append(toIndentedString(age)).append("\n");
		sb.append("    dateOfBirth: ").append(toIndentedString(dateOfBirth)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
