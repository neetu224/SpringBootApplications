package com.example.demo.model.audit;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@JsonIgnoreProperties(value= {"createdDate","updatedDate"},allowGetters=true)
public abstract class DateAudit implements Serializable {
@CreatedDate
@Column(nullable=false,updatable=false)
private Instant createdDate;
@LastModifiedDate
@Column(nullable=false)
private Instant updatedDate;
}
