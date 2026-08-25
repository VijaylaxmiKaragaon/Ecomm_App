package com.ecomm.user.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Entity
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(unique=true)
	private String email;
	
	private String password;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;
	
	private LocalDateTime modifiedAt;
	
	@PreUpdate
	public void setModifiedAt() {
		this.modifiedAt=LocalDateTime.now();
	}
	
	@OneToOne(mappedBy = "user",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Profile profile;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;

}
