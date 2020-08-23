package com.formssi.mvp.pojo;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AddUserRequest
 */
@Validated

public class AddUserRequest   {
  @JsonProperty("userName")
  private String userName = null;

  @JsonProperty("gender")
  private String gender = null;

  @JsonProperty("age")
  private Integer age = null;

  @JsonProperty("dateOfBirth")
  private LocalDate dateOfBirth = null;

  public AddUserRequest userName(String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * 用户名
   * @return userName
  **/
  @ApiModelProperty(value = "用户名")


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public AddUserRequest gender(String gender) {
    this.gender = gender;
    return this;
  }

  /**
   * 性別
   * @return gender
  **/
  @ApiModelProperty(value = "性別")


  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public AddUserRequest age(Integer age) {
    this.age = age;
    return this;
  }

  /**
   * 年齡
   * @return age
  **/
  @ApiModelProperty(value = "年齡")


  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public AddUserRequest dateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
    return this;
  }

  /**
   * 生日
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
    AddUserRequest addUserRequest = (AddUserRequest) o;
    return Objects.equals(this.userName, addUserRequest.userName) &&
        Objects.equals(this.gender, addUserRequest.gender) &&
        Objects.equals(this.age, addUserRequest.age) &&
        Objects.equals(this.dateOfBirth, addUserRequest.dateOfBirth);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userName, gender, age, dateOfBirth);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddUserRequest {\n");
    
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

