package com.formssi.mvp.pojo;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ExceptionResponse
 */
@Validated

public class ExceptionResponse   {
  @JsonProperty("code")
  private String code = null;

  @JsonProperty("msg")
  private String msg = null;

  public ExceptionResponse code(String code) {
    this.code = code;
    return this;
  }

  /**
   * 错误码
   * @return code
  **/
  @ApiModelProperty(value = "错误码")


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ExceptionResponse msg(String msg) {
    this.msg = msg;
    return this;
  }

  /**
   * 错误信息
   * @return msg
  **/
  @ApiModelProperty(value = "错误信息")


  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExceptionResponse exceptionResponse = (ExceptionResponse) o;
    return Objects.equals(this.code, exceptionResponse.code) &&
        Objects.equals(this.msg, exceptionResponse.msg);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, msg);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExceptionResponse {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    msg: ").append(toIndentedString(msg)).append("\n");
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

