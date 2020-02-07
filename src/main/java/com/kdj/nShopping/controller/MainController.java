package com.kdj.nShopping.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
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
	
	@RequestMapping(value ="/map" ,method=RequestMethod.GET)
	public String Map() {
		
		return "map";
	}
	
	@RequestMapping("/shop")
	public ModelAndView APIExamDatalabTrend() {
		String clientId = "p8RoFYE73pcN8Y_XySea";//애플리케이션 클라이언트 아이디 값";
        String clientSecret = "DQEd3Bynck";//애플리케이션 클라이언트 시크릿 값";
        String answer="";
        String[] arr;
        Gson gson = new Gson();

        try {
//            String apiURL = "https://openapi.naver.com/v1/datalab/shopping/categories";
            String apiURL = "https://openapi.naver.com/v1/datalab/shopping/category/keywords";
            
            String[] charSet = {"utf-8", "euc-kr", "ksc5601", "iso-8859-1", "x-windows-949"};

            Request request = Request.builder()
            				  .startDate("2017-08-01")
            				  .endDate("2017-10-30")
            				  .timeUnit("month")
            				  .category(new Category[] {
            						  Category.builder()
            						  .name("패션의류")
            						  .param(new String[] {"50000000"})
            						  .build() ,
            						  Category.builder()
            						  .name("화장품/미용")
            						  .param(new String[] {"50000002"})
            						  .build()})
            				  .device("")
            				  .gender("")
            				  .ages(new String[] {})
            				  .build();
            
            RequestKeyword requestKeyword = RequestKeyword.builder()
            		.startDate("2017-08-01")
            		.endDate("2017-10-01")
            		.timeUnit("month")
            		.category("50000000")
            		.keyword(new Keyword[] {
            				Keyword.builder()
            				.name(encoder.encode("패션의류/정장"))
            				.param(new String[] {encoder.encode("pants")})
            				.build(),
            				Keyword.builder()
            				.name(encoder.encode("패션의류/비지니스 캐주얼"))
            				.param(new String[] {encoder.encode("정장")})
            				.build()
            		})
            		.device("")
            		.gender("")
            		.ages(new String[] {"20","30"})
            		.build();
            String str = "뭐지";
            
           
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            con.setRequestProperty("Content-Type", "application/json");

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            
            wr.writeBytes(gson.toJson(requestKeyword));
            System.out.println(gson.toJson(requestKeyword));
            
            
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
            answer =response.toString();

        } catch (Exception e) {
            System.out.println(e);
        }
        
        arr = answer.split(",");
        answer="";
        for(String str : arr)
        	answer+=str+"<br>";
        return new ModelAndView("welcome","answer",answer);
	}
	
	@RequestMapping("/test")
	public ModelAndView APItest() throws IOException {
	
        String answer="";
        String[] arr;
        String apiURL = "http://www.cdc.go.kr/npt/biz/npp/rest/getLwcrContent.do?key=3fHo%2FlqxnGak9gvutlBC3Y1%2F3i2AWqal&icdgrpCd=04&icdCd=ND0707&isTxt=1&updtDt=2010-01-06";
            
        String USER_AGENT = "Mozilla/5.0";

        URL url;
		try {
			url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
	        con.setRequestMethod("GET"); // optional default is GET 
//	        con.setRequestProperty("Content-Type", "application/json"); 
	        int responseCode = con.getResponseCode(); 
	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())); 
	        String inputLine; 
	        StringBuffer response = new StringBuffer();
	        while ((inputLine = in.readLine()) != null) { 
	        	response.append(inputLine); 
	        	} 
	        in.close(); // print result 
	        System.out.println("HTTP 응답 코드 : " + responseCode); 
	        System.out.println("HTTP body : " + response.toString());
	        answer = response.toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
       
   
        return new ModelAndView("welcome","answer",answer);
	}
}
