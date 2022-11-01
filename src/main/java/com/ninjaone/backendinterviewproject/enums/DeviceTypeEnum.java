package com.ninjaone.backendinterviewproject.enums;

public enum DeviceTypeEnum {

	WINDOWS_WORKSTATION(1, "Windows Workstation"),
	WINDOWS_SERVER(2, "Windows Server"),
	MAC(3, "Mac"),
	LINUX(4, "Linux");
	
	private Integer cod;
	private String description;

	private DeviceTypeEnum(Integer cod, String description) {
		this.cod = cod;
		this.description = description;
	}
	
	public static DeviceTypeEnum toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for (DeviceTypeEnum x : DeviceTypeEnum.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid id: " + cod); 
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getDescription() {
		return description;
	}

	public void setDescricao(String description) {
		this.description = description;
	}
}
