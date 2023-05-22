package com.java.basics.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name="groups")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Group implements Serializable
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="group_id")
	private long groupId;
	
	@Column(name="group_name")
	private String groupName;
	
	@Column(name="active")
	private Boolean active = true;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="created_date")
	private LocalDateTime createdDate = LocalDateTime.now();
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="updated_date")
	private LocalDateTime updatedDate = LocalDateTime.now();
	
	@Column(name="label")
	private String label;
}
