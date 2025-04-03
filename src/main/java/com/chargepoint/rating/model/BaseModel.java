
package com.chargepoint.rating.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;


@Data
@MappedSuperclass
public class BaseModel {
	
	@Column(name = "CREATED_DATE", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
	private Date createdDate;
	
	@Column(name = "CREATED_BY", nullable = false)
	private String createdBy;
	
	@Column(name = "UPDATED_DATE", nullable = false)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date updatedDate;
	
	@Column(name = "UPDATED_BY", nullable = false)
	private String updatedBy;

}
