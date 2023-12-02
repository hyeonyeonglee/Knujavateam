package whereLocker;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import javax.swing.*;
import javax.swing.JLabel;

	

public class NaverMaps {
	private String x,y;
	private String addr;
	public String map_service(String x, String y) {
		String URL_STATICMAP = "https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?";
		try {
			String pos = URLEncoder.encode(x + " " + y, "UTF-8");
			URL_STATICMAP += "center=" + x + "," + y;
			URL_STATICMAP += "&level=15&w=320&h=320&scale=1";
			URL_STATICMAP += "&markers=type:d|size:mid|pos:" + pos;
			
			URL url = new URL(URL_STATICMAP);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "bwv1gihpqd");
			con.setRequestProperty("X-NCP-APIGW-API-KEY", "8Y0YpBAwcbhT76WtCujJADcOMdYwubJWJjPrNohq");
			
			int responseCode = con.getResponseCode();
			
			
			// 정상호출인 경우.
			 if (responseCode == 200) {
				 InputStream is = con.getInputStream();
					
					int read = 0;
					byte[] bytes = new byte[1024];
					
					// 랜덤 파일명으로 파일 생성
					String tempName = Long.valueOf(new Date().getTime()).toString();
					File file = new File(tempName + ".jpg");	// 파일 생성.
					
					file.createNewFile();
					
					OutputStream out = new FileOutputStream(file);
					
					while ((read = is.read(bytes)) != -1) {
						out.write(bytes, 0, read);	// 파일 작성
					}
					
					is.close();
					return file.getAbsolutePath();
					
		        } else {
		            System.out.println(responseCode);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		return null;
		}
	public void map_geocode(String addr) {
		
		String URL_GEOCODE = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=";
		try {
			System.out.println("geocodeTest:"+addr);
			String add = URLEncoder.encode(addr,"UTF-8");
			URL_GEOCODE += add;
			
			
			
			
			URL url = new URL(URL_GEOCODE);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "bwv1gihpqd");
			con.setRequestProperty("X-NCP-APIGW-API-KEY", "8Y0YpBAwcbhT76WtCujJADcOMdYwubJWJjPrNohq");
			
			int responseCode = con.getResponseCode();
			
			
			// 정상호출인 경우.
			
			BufferedReader br;
			if (responseCode == 200) {
				System.out.println("geocode정상");
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			} else {
				System.out.println("geocode오류");
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(response.toString());
			
			
			JSONArray arr = (JSONArray)jsonObject.get("addresses");
			for (int i = 0; i < arr.size(); i++) {
				JSONObject temp = (JSONObject) arr.get(i);
				x=(String)temp.get("x");
				System.out.println("x:"+x);
				y=(String)temp.get("y");
				System.out.println("y:"+y);
				
			}
			
			
			

		} catch (Exception err) {
			System.out.println(err);
		}
	}
	public String getX() {
		return x;
	}
	public String getY() {
		return y;
	}
	
	}

