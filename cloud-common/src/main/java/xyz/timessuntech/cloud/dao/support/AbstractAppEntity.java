package xyz.timessuntech.cloud.dao.support;

import xyz.timessuntech.cloud.dao.embedded.Operation;

public abstract class AbstractAppEntity{
	
	abstract public String getId();

	abstract public void setId(String id);

	abstract public Operation getOperation();
	
	abstract public void update();
}
