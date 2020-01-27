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
public class Keyword {
	
	@NonNull private String name;
	@NonNull private String[] param;

}
