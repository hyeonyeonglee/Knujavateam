package whereLocker;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ConnectStation {
	class ItemData{
		String stinLocLon;
		String stinLocLat;
		public ItemData(String stinLocLon, String stinLocLat) {
			this.stinLocLat = stinLocLat;
			this.stinLocLon = stinLocLon;
		}
}

	public List<ItemData> loadStation(String railCd, String lnCd, String stinCd, String stinNm)throws IOException, ParseException{
		 StringBuilder urlBuilder = new StringBuilder("https://openapi.kric.go.kr/openapi/convenientInfo/stationInfo");
		 urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=$2a$10$BZRlkGiNJl1q//99Q6B5B.rkjevl6cCQJsTj7KVB1KH9wskgWZ51m"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("format","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));  
	        urlBuilder.append("&" + URLEncoder.encode("railOprIsttCd","UTF-8") + "=" + URLEncoder.encode(railCd, "UTF-8")); 
	        urlBuilder.append("&" + URLEncoder.encode("lnCd","UTF-8") + "=" + URLEncoder.encode(lnCd, "UTF-8"));
	        urlBuilder.append("&" + URLEncoder.encode("stinCd","UTF-8") + "=" + URLEncoder.encode(stinCd, "UTF-8")); 
	        urlBuilder.append("&" + URLEncoder.encode("stinNm","UTF-8") + "=" + URLEncoder.encode(stinNm, "UTF-8")); 
	        
	        
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
	 
		JSONParser jsonParser = new JSONParser();

	      //JSON데이터를 넣어 JSON Object 로 만들어 준다.
	      JSONObject jsonObject = (JSONObject)jsonParser.parse(sb.toString());
	      
	     //**         
	      //배열 추출
	      JSONArray array = (JSONArray) jsonObject.get("body");
	      List<ItemData> itemDataList = new ArrayList();
	      if(array == null) {
	    	  System.out.println("화장실 없음");
	      }
	      else {
	      for(int i=0; i<array.size(); i++){
	        	 
	    	    
	    	             
	    	
	      JSONObject object = (JSONObject) array.get(i);
	      String stinLocLon = String.valueOf(object.get("stinLocLon"));
	      String stinLocLat = String.valueOf(object.get("stinLocLat"));
	      ItemData itemData = new ItemData(stinLocLon,stinLocLat);
	      itemDataList.add(itemData);
	     
	      }
	      }
	      return itemDataList;
	}
	
	      
}
