package xyz.timessuntech.cloud.mall.dao.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import xyz.timessuntech.cloud.dao.embedded.Gender;
import xyz.timessuntech.cloud.dao.embedded.Operation;
import xyz.timessuntech.cloud.dao.embedded.UserName;
import xyz.timessuntech.cloud.dao.support.BaseEntity;

/**
 * 系统用户
 */
@Entity
@Table(name = "mall_user", uniqueConstraints = {  @UniqueConstraint(columnNames = "number") })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MallUser extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2087101539668071245L;
	protected String password;
	protected long number;
	protected Gender gender;
	protected String mobile;
	protected String telephone;
	protected String email;
	protected UserName name;
	protected String remark;

	public MallUser() {
		this.name = new UserName();
		this.operation = new Operation();
	}

	public MallUser(String id) {
		this.id = id;
	}

	public MallUser(String id, Operation operation) {
		this.id = id;
		this.operation = operation;
	}

	@Column(name = "number", nullable = false, unique = true)
	public long getNumber() {
		return this.number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	@Column(name = "mobile", length = 32)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "title", column = @Column(name = "title")), @AttributeOverride(name = "cn", column = @Column(name = "name_cn")),
			@AttributeOverride(name = "en", column = @Column(name = "name_en")), @AttributeOverride(name = "first", column = @Column(name = "name_first")),
			@AttributeOverride(name = "middle", column = @Column(name = "name_middle")), @AttributeOverride(name = "last", column = @Column(name = "name_last")) })
	public UserName getName() {
		return name;
	}

	public void setName(UserName name) {
		this.name = name;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", length = 45)
	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Column(name = "password", length = 32)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
