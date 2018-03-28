package xyz.timessuntech.cloud.dao.embedded;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * @author Joeshing
 *
 *         嵌入类，时间区间
 */
@Embeddable
public class TimeRang implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5106899305427601569L;
	private Date begin;
	private Date end;

	public TimeRang() {
		super();
		this.begin = new Date();
		this.end = new Date();
	}

	public TimeRang(Date begin, Date end) {
		super();
		this.begin = begin;
		this.end = end;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "begin_time", length = 19)
	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time", length = 19)
	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
	/**
	 * @return
	 */
	@Transient
	public boolean isOpening(){
		Date now = new Date();
		return now.after(getBegin()) && now.before(getEnd());
	}

}
