package xyz.timessuntech.cloud.dao.embedded;

import java.io.Serializable;

/**
 * @author Joeshing
 *
 *         嵌入类，名称，统一定义前、中、后名字
 */
public class UserName implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5719613362644532363L;

	private String title;//称呼
	private String cn;
	private String en;
	private String first;
	private String middle;
	private String last;

	public UserName() {
		super();
	}

	public UserName(String title, String cn, String en, String first, String middle, String last) {
		super();
		this.title = title;
		this.cn = cn;
		this.en = en;
		this.first = first;
		this.middle = middle;
		this.last = last;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getEn() {
		return this.en;
	}
	
	public void setEn(String en){
		this.en = en;
	}

	public String getFirst() {
		return this.first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getMiddle() {
		return middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

}
