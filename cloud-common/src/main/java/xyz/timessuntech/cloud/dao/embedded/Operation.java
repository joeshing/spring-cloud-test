package xyz.timessuntech.cloud.dao.embedded;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * @author Joeshing 记录对象的操作信息
 */
@Embeddable
public class Operation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3653439719146211966L;
	private boolean enable = true;
	private Date createTime = new Date();
	private Date modifyTime;

	@Column(name = "enable", nullable = false)
	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, length = 19)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_time", length = 19)
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Transient
	public void update(){
		this.modifyTime = new Date();
	}
}
