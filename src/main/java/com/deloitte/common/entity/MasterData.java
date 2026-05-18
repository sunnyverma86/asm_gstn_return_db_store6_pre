package com.deloitte.common.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.ColumnDefault;

import com.deloitte.service.support.StringCryptoConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
@Table(name = "master_data", uniqueConstraints = @UniqueConstraint(columnNames = { "userName", "clientId",
		"clientSecret" }), schema = "public")
public class MasterData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	// @Convert(converter = StringCryptoConverter.class)
	private String userName;
	@Column
	@Convert(converter = StringCryptoConverter.class)
	private String password;
	@Column
	@Convert(converter = StringCryptoConverter.class)
	private String clientId;
	@Column
	@Convert(converter = StringCryptoConverter.class)
	private String clientSecret;
	@Column
	@Convert(converter = StringCryptoConverter.class)
	private String hostName;
	@Column
	@Convert(converter = StringCryptoConverter.class)
	private String stateCd;
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
		if (!(o instanceof MasterData that))
			return false;
		return Objects.equals(getId(), that.getId()) && Objects.equals(getUserName(), that.getUserName())
				&& Objects.equals(getPassword(), that.getPassword())
				&& Objects.equals(getClientId(), that.getClientId())
				&& Objects.equals(getClientSecret(), that.getClientSecret())
				&& Objects.equals(getHostName(), that.getHostName()) && Objects.equals(getStateCd(), that.getStateCd())
				&& Objects.equals(getIsActive(), that.getIsActive())
				&& Objects.equals(getCreateDateTime(), that.getCreateDateTime())
				&& Objects.equals(getUpdatedDateTime(), that.getUpdatedDateTime());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getUserName(), getPassword(), getClientId(), getClientSecret(), getHostName(),
				getStateCd(), getIsActive(), getCreateDateTime(), getUpdatedDateTime());
	}

	@Override
	public String toString() {
		return "MasterData{" + "id=" + id + ", userName='" + userName + '\'' + ", password='" + password + '\''
				+ ", clientId='" + clientId + '\'' + ", clientSecret='" + clientSecret + '\'' + ", hostName='"
				+ hostName + '\'' + ", stateCd='" + stateCd + '\'' + ", isActive='" + isActive + '\''
				+ ", createDateTime=" + createDateTime + ", updatedDateTime=" + updatedDateTime + '}';
	}
}
