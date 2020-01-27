package com.kdj.nShopping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestKeyword {
	
	
	@NonNull private String startDate; 
	@NonNull private String endDate;
	@NonNull private String timeUnit;
	@NonNull private String category;
	@NonNull private Keyword[] keyword;
	private String device;
	private String gender;
	private String[] ages;
	

}