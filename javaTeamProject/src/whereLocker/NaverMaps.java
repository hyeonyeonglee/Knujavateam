package whereLocker;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
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
	
	}

