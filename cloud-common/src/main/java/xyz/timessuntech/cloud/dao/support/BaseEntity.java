package xyz.timessuntech.cloud.dao.support;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import xyz.timessuntech.cloud.dao.embedded.Operation;

@MappedSuperclass
public class BaseEntity extends AbstractAppEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5357691855337080550L;
	protected String id;
	protected Operation operation;

	public BaseEntity() {
		super();
		this.operation = new Operation();
	}

	public BaseEntity(String id) {
		super();
		this.id = id;
		this.operation = new Operation();
	}

	@Override
	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid.hex")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Embedded
	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	@Override
	public void update() {
		this.operation.update();
	}
}
