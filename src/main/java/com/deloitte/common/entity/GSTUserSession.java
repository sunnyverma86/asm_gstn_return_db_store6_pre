package com.deloitte.common.entity;

import java.time.LocalDateTime;
import java.util.Objects;

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
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gst_user_session", uniqueConstraints = @UniqueConstraint(columnNames = { "userName", "authToken",
		"sek" }), schema = "public")
public class GSTUserSession {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String userName;
	@Column
	@Convert(converter = StringCryptoConverter.class)
	private String appKey;
	@Column
	@Convert(converter = StringCryptoConverter.class)
	private String authToken;
	@Column
	@Convert(converter = StringCryptoConverter.class)
	private String sek;
	@Column(name = "auth_date_time")
	private LocalDateTime authDateTime;
	@Column(name = "create_date_time", updatable = false)
	private LocalDateTime createDateTime;
	@Column(name = "updated_date_time")
	private LocalDateTime updatedDateTime;

	@Column(name = "failed_attempt")
	private Integer failedAttempt = 0;

	@Column(name = "retry_round")
	private Integer retryRound = 0;

	@Column(name = "next_retry_time")
	private LocalDateTime nextRetryTime;

	@Column(name = "stop_for_today")
	private Boolean stopForToday = false;

	@Column(name = "expiry_time")
	private LocalDateTime expiryTime;

	@PreUpdate
	protected void onUpdate() {
		updatedDateTime = LocalDateTime.now();
	}

	@PrePersist
	protected void onCreate() {

		authDateTime = LocalDateTime.now();
		createDateTime = LocalDateTime.now();
		updatedDateTime = LocalDateTime.now();

		expiryTime = LocalDateTime.now().plusHours(4);

		failedAttempt = 0;
		retryRound = 0;
		stopForToday = false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof GSTUserSession that))
			return false;
		return Objects.equals(getId(), that.getId()) && Objects.equals(getUserName(), that.getUserName())
				&& Objects.equals(getAppKey(), that.getAppKey()) && Objects.equals(getAuthToken(), that.getAuthToken())
				&& Objects.equals(getSek(), that.getSek()) && Objects.equals(getAuthDateTime(), that.getAuthDateTime())
				&& Objects.equals(getCreateDateTime(), that.getCreateDateTime())
				&& Objects.equals(getUpdatedDateTime(), that.getUpdatedDateTime());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getUserName(), getAppKey(), getAuthToken(), getSek(), getAuthDateTime(),
				getCreateDateTime(), getUpdatedDateTime());
	}

	@Override
	public String toString() {
		return "GSTUserSession{" + "id=" + id + ", userName='" + userName + '\'' + ", app_key='" + appKey + '\''
				+ ", authToken='" + authToken + '\'' + ", sek='" + sek + '\'' + ", authDateTime=" + authDateTime
				+ ", createDateTime=" + createDateTime + ", updatedDateTime=" + updatedDateTime + '}';
	}
}
