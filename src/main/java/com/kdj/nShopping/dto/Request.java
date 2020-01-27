package com.kdj.nShopping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Request {
	
	
	@NonNull private String startDate; 
	@NonNull private String endDate;
	@NonNull private String timeUnit;
	@NonNull private Category[] category;
//	@NonNull private String category;
	private String keyword;
	private String device;
	private String gender;
	private String[] ages;
	

}
