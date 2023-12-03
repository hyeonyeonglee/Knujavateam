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


public class ConnectAtm {
	class AtmData{
		String num;
		String dtloc;
		String utlPsbHr;
		String grndDvNm;
		public AtmData(String num,String dtloc, String utlPsbHr, String grndDvNm) {
			this.num = num;
			this.dtloc = dtloc;
			this.utlPsbHr = utlPsbHr;
			this.grndDvNm = grndDvNm;
		}
	}
	public List<AtmData> loadAtmInfo(String railcd ,String lncd, String stincd)throws IOException, ParseException {
		StringBuilder urlBuilder = new StringBuilder("https://openapi.kric.go.kr/openapi/convenientInfo/stationATM");
		 urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=$2a$10$mE4jdGOHE.fzyA8Qw8PSwOQjdMe/TduCDXvhuaZoPwBeHTbr3T1gK"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("format","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));  
	        urlBuilder.append("&" + URLEncoder.encode("railOprIsttCd","UTF-8") + "=" + URLEncoder.encode(railcd, "UTF-8")); 
	        urlBuilder.append("&" + URLEncoder.encode("lnCd","UTF-8") + "=" + URLEncoder.encode(lncd, "UTF-8"));
	        urlBuilder.append("&" + URLEncoder.encode("stinCd","UTF-8") + "=" + URLEncoder.encode(stincd, "UTF-8")); 
	        
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
        JSONObject jsonObject = (JSONObject)jsonParser.parse(sb.toString());
        JSONArray array = (JSONArray) jsonObject.get("body");
        List<AtmData> itemDataList = new ArrayList();
        if (array == null) {
            System.out.println("Atm 없음");
        } else {
            for (int i = 0; i < array.size(); i++) {
                System.out.println("ATM" + (i + 1) + " ===========================================");

                JSONObject object = (JSONObject) array.get(i);
                System.out.println("상세위치 ==> " + object.get("dtlLoc"));
                System.out.println("이용가능시간 ==> " + object.get("utlPsbHr"));
                System.out.println("지상구분 ==> " + object.get("grndDvNm"));
              String num = "Atm"+(i+1);
      	      String dtloc = (String)object.get("dtlLoc");
      	      String utlPsbHr = (String)object.get("utlPsbHr");
      	      String grndDvnm = (String)object.get("grndDvNm");
      	      AtmData itemData = new AtmData(num,dtloc,utlPsbHr, grndDvnm);
      	      itemDataList.add(itemData);
      	      
      	      }
      	      
      	      }
      	      return itemDataList;
            }
        
	}
	
     
      
      
      
      
    

