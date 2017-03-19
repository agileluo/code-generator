package io.github.agileluo.codegenerator.example.entity;

import java.util.Date;

import io.github.agileluo.codegenerator.core.MyEntity;
import io.github.agileluo.codegenerator.core.MyField;

@MyEntity(comment="用户")
public class User {
	
	@MyField(comment="用户Id",  key = true, autoIncrease = true)
	private Long userId;
	
	@MyField(comment = "密码", length = 50, notNull = true)
	private String password;
	
	@MyField(comment = "创建人员 ", length=20)
	private String createBy;

	@MyField(comment = "创建时间")
	private Date createDate;

	@MyField(comment = "更新人员", length=20)
	private String updateBy;

	@MyField(comment = "更新时间")
	private Date updateDate;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
