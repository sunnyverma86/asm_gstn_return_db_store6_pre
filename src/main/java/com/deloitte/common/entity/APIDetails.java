package com.deloitte.common.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "api_details", uniqueConstraints = @UniqueConstraint(columnNames = { "apiName", "apiPath",
		"apiAction" }), schema = "public")
public class APIDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String apiName;
	@Column
	private String apiPath;
	@Column
	private String apiContentType;
	@Column
	private String apiUrlParameters;
	@Column
	private String apiEncryption;
	@Column
	private String apiAction;
	@Column
	@ColumnDefault("true")
	private String isActive;

	@Column(name = "create_date_time", updatable = false)
	private LocalDateTime createDateTime;
	@Column(name = "updated_date_time")
	private LocalDateTime updatedDateTime;

	@PrePersist
	protected void onCreate() {
		createDateTime = LocalDateTime.now();
		updatedDateTime = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedDateTime = LocalDateTime.now();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof APIDetails that))
			return false;
		return Objects.equals(getId(), that.getId()) && Objects.equals(getApiName(), that.getApiName())
				&& Objects.equals(getApiPath(), that.getApiPath())
				&& Objects.equals(getApiContentType(), that.getApiContentType())
				&& Objects.equals(getApiUrlParameters(), that.getApiUrlParameters())
				&& Objects.equals(getApiEncryption(), that.getApiEncryption())
				&& Objects.equals(getApiAction(), that.getApiAction())
				&& Objects.equals(getIsActive(), that.getIsActive())
				&& Objects.equals(getCreateDateTime(), that.getCreateDateTime())
				&& Objects.equals(getUpdatedDateTime(), that.getUpdatedDateTime());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getApiName(), getApiPath(), getApiContentType(), getApiUrlParameters(),
				getApiEncryption(), getApiAction(), getIsActive(), getCreateDateTime(), getUpdatedDateTime());
	}

	@Override
	public String toString() {
		return "APIDetails{" + "id=" + id + ", apiName='" + apiName + '\'' + ", apiPath='" + apiPath + '\''
				+ ", apiContentType='" + apiContentType + '\'' + ", apiUrlParameters='" + apiUrlParameters + '\''
				+ ", apiEncryption='" + apiEncryption + '\'' + ", apiAction='" + apiAction + '\'' + ", isActive='"
				+ isActive + '\'' + ", createDateTime=" + createDateTime + ", updatedDateTime=" + updatedDateTime + '}';
	}
}
