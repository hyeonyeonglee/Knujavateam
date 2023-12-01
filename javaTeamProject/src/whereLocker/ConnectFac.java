package whereLocker;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConnectFac {
	public static void main(String args[]) throws IOException, ParseException {
		 StringBuilder urlBuilder = new StringBuilder("https://openapi.kric.go.kr/openapi/handicapped/stationCnvFacl");
		 urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=$2a$10$BZRlkGiNJl1q//99Q6B5B.rkjevl6cCQJsTj7KVB1KH9wskgWZ51m"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("format","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));  
	        urlBuilder.append("&" + URLEncoder.encode("railOprIsttCd","UTF-8") + "=" + URLEncoder.encode("S1", "UTF-8")); 
	        urlBuilder.append("&" + URLEncoder.encode("lnCd","UTF-8") + "=" + URLEncoder.encode("3", "UTF-8"));
	        urlBuilder.append("&" + URLEncoder.encode("stinCd","UTF-8") + "=" + URLEncoder.encode("322", "UTF-8")); 
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        
	        System.out.println(sb);
	        JSONParser jsonParser = new JSONParser();

	      //JSON데이터를 넣어 JSON Object 로 만들어 준다.
	      JSONObject jsonObject = (JSONObject)jsonParser.parse(sb.toString());
	      
	     //**         
	      //배열 추출
	      JSONArray array = (JSONArray) jsonObject.get("body");
	      if(array == null) {
	    	  System.out.println("화장실 없음");
	      }
	      else {
	      for(int i=0; i<array.size(); i++){
	        	 
	    	    System.out.println("편의시설"+(i+1)+" ===========================================");
	    	             
	    	
	      JSONObject object = (JSONObject) array.get(i);
	      String gubun = null;
	      gubun = (String) object.get("gubun");
	      switch(gubun) {
	      case "EV": {System.out.println("편의시설"+(i+1)+" ===========================================");
	    	  System.out.println("엘리베이터 정보");
	      System.out.println("상세위치 ==> "+object.get("dtlLoc"));
	      System.out.println("역층 ==> "+object.get("stinFlor"));
	      if((String)object.get("grndDvNm")== "1") {
	    	  System.out.println("지상구분 ==> 지상");
	      }
	      else if(((String)object.get("grndDvNm"))== "2")
	      {
	    	  System.out.println("지상구분 ==> 지하");
	      }
	      break;
	      }
	      case "TOLT": {System.out.println("편의시설"+(i+1)+" ===========================================");
	    	  System.out.println("화장실");
	      System.out.println("상세위치 ==> "+object.get("dtlLoc"));
	      System.out.println("역층 ==> "+object.get("stinFlor"));
	      break;}
	      default: break;
	      
	      
	      }
	      
	     
	      
	      }
	      
	      }
	    }
}
