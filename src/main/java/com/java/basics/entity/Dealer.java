package com.java.basics.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="dealers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Dealer implements Serializable
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long dealerId;
	
	@Column(name="dealer_name")
	private String dealerName;
	
	@Column(name="name")
	private String name;
	
	@Column(name="country")
	private String country;
	
	@Column(name="state")
	private String State;
	
    @NotEmpty(message = "Email cannot be empty")
	@Email(regexp="[a-z0-9]+[@][a-z]+[.][a-z]+")//flags = Pattern.Flag.CASE_INSENSITIVE)
	@Column(name="email")
	private String email;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="type")
	private String type;
	
	@Column(name="website")
	private String website;
	
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
	
	@ManyToMany(cascade = CascadeType.ALL)
	@Column(nullable = true)
    @JoinTable(name = "dealers_groups", joinColumns = @JoinColumn(name = "dealer_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groups;
}
