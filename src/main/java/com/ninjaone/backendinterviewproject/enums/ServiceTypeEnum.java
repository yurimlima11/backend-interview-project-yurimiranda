package com.ninjaone.backendinterviewproject.enums;


public enum ServiceTypeEnum {
		
		ANTIVIRUS(1, "Antivirus"),
		BACKUP(2, "Backup"),
		PSA(3, "PSA"),
		SCREEN_SHARE(4, "Screen Share");

		private Integer cod;
		private String description;

		private ServiceTypeEnum(Integer cod, String description) {
			this.cod = cod;
			this.description = description;
		}
		
		public static ServiceTypeEnum toEnum(Integer cod) {
			if(cod == null) {
				return null;
			}
			for (ServiceTypeEnum x : ServiceTypeEnum.values()) {
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

		public void setDescription(String description) {
			this.description = description;
		}
}
