package whereLocker;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConnectLocker {
	class ItemData{
		String num;
		String dtloc;
		String utlFare;
		String grndDvNm;
		public ItemData(String num,String dtloc, String utlFare, String grndDvNm) {
			this.num = num;
			this.dtloc = dtloc;
			this.utlFare = utlFare;
			this.grndDvNm = grndDvNm;
		}
	}
	public List<ItemData> loadLock(String lncd, String railOpr, String stinCd)throws IOException, ParseException {
		 StringBuilder urlBuilder = new StringBuilder("https://openapi.kric.go.kr/openapi/convenientInfo/stationLocker");
		 urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=$2a$10$BZRlkGiNJl1q//99Q6B5B.rkjevl6cCQJsTj7KVB1KH9wskgWZ51m"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("format","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); 
	        urlBuilder.append("&" + URLEncoder.encode("lnCd","UTF-8") + "=" + URLEncoder.encode(lncd, "UTF-8")); 
	        urlBuilder.append("&" + URLEncoder.encode("railOprIsttCd","UTF-8") + "=" + URLEncoder.encode(railOpr, "UTF-8")); 
	        urlBuilder.append("&" + URLEncoder.encode("stinCd","UTF-8") + "=" + URLEncoder.encode(stinCd, "UTF-8")); 
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
		List<ItemData> itemDataList = new ArrayList<>();

	      //JSON데이터를 넣어 JSON Object 로 만들어 준다.
	      JSONObject jsonObject = (JSONObject)jsonParser.parse(sb.toString());
	      
	              
	      //배열 추출
	      JSONArray array = (JSONArray) jsonObject.get("body");
	      if(array == null) {
	    	  System.out.println("물품보관함 없음");
	      }
	      else {
	      for(int i=0; i<array.size(); i++){
	        	 
	    	    System.out.println("물품보관함"+(i+1)+" ===========================================");
	    	             
	    	
	      JSONObject object = (JSONObject) array.get(i);
	      String num = "물품보관함"+(i+1);
	      String dtloc = (String)object.get("dtlLoc");
	      String utlFare = (String)object.get("utlFare");
	      String grndDvnm = (String)object.get("grndDvNm");
	      ItemData itemData = new ItemData(num,dtloc,utlFare, grndDvnm);
	      itemDataList.add(itemData);
	      
	      }
	      
	      }
	      return itemDataList;
	}
	
	 
	
	
	
				 
}
		

		
	
	

