package com.kdj.nShopping.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.GsonBuilder;
import com.kdj.nShopping.dto.Category;
import com.kdj.nShopping.dto.Keyword;
import com.kdj.nShopping.dto.Request;
import com.kdj.nShopping.dto.RequestKeyword;
import com.kdj.nShopping.encoding.EncodingUTF8;

@Controller
public class MainController {
	
	EncodingUTF8 encoder = new EncodingUTF8();
	
	@RequestMapping(value ="/hello" ,method=RequestMethod.GET)
	public String Hello() {
		
		return "welcome";
	}
	
	@RequestMapping("/shop")
	public ModelAndView APIExamDatalabTrend() {
		String clientId = "p8RoFYE73pcN8Y_XySea";//애플리케이션 클라이언트 아이디 값";
        String clientSecret = "DQEd3Bynck";//애플리케이션 클라이언트 시크릿 값";
        String answer="";
        String[] arr;

        try {
//            String apiURL = "https://openapi.naver.com/v1/datalab/shopping/categories";
            String apiURL = "https://openapi.naver.com/v1/datalab/shopping/category/keywords";
            
            Request request = Request.builder()
            				  .startDate("2017-08-01")
            				  .endDate("2017-09-30")
            				  .timeUnit("month")
            				  .category(new Category[] {
            						  Category.builder()
            						  .name(encoder.encode("패션의류"))
            						  .param(new String[] {"50000000"})
            						  .build() ,
            						  Category.builder()
            						  .name(encoder.encode("화장품/미용"))
            						  .param(new String[] {"50000002"})
            						  .build()})
            				  .device("")
            				  .gender("")
            				  .ages(new String[] {})
            				  .build();
            
            RequestKeyword requsetKeyword = RequestKeyword.builder()
            		.startDate("2017-08-01")
            		.endDate("2017-09-30")
            		.timeUnit("month")
            		.category("50000003")
            		.keyword(new Keyword[] {
            				Keyword.builder()
            				.name(encoder.encode("갤럭시 버즈"))
            				.param(new String[] {encoder.encode("갤럭시")})
            				.build(),
            				Keyword.builder()
            				.name(encoder.encode("아이폰 에어팟 프로"))
            				.param(new String[] {encoder.encode("iPhone")})
            				.build()
            		})
            		.device("")
            		.gender("")
            		.ages(new String[] {"20","30"})
            		.build();
            
            
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            con.setRequestProperty("Content-Type", "application/json");

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            
            wr.writeBytes(new GsonBuilder().create().toJson(requsetKeyword));
            
            
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(URLDecoder.decode(response.toString(),"UTF-8"));
            answer = URLDecoder.decode(response.toString(),"UTF-8");

        } catch (Exception e) {
            System.out.println(e);
        }
        
        arr = answer.split(",");
        answer="";
        for(String str : arr)
        	answer+=str+"<br>";
        return new ModelAndView("welcome","answer",answer);
	}
}
