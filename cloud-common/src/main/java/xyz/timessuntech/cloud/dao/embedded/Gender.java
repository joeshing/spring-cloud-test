package xyz.timessuntech.cloud.dao.embedded;

public enum Gender {

	MALE("男"), FEMALE("女");

	private String label;

	private Gender(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
