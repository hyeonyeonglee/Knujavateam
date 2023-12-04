package whereLocker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

public class Model {
	private ConnectLocker connectLocker;
	private ConnectAtm connectAtm;
	private ConnectToil connectToil;
	private ConnectStation connectStation;
	private NaverMaps naverMaps;
	private ExcelData excelData;
	private String search;
	private String stinLocLon, stinLocLat;
	private ArrayList<String> railCd;
    private ArrayList<String> lnCd;
    private ArrayList<String> stinCd;
    private ArrayList<String> stinNm;
    private ArrayList<String> lnNm;
    private double myX=127.079300, myY=37.540941;
	
	public Model() {
		this.connectLocker = new ConnectLocker();
		this.connectAtm = new ConnectAtm();
		this.connectToil = new ConnectToil();
		this.connectStation= new ConnectStation();
		this.naverMaps = new NaverMaps(); 	
		this.excelData = new ExcelData();
	}
	public List<ConnectLocker.LockerData> getLock(String railcd, String lncd, String stincd) throws IOException, ParseException {
		return connectLocker.loadLock(lncd, railcd, stincd);
	}
	
	public List<ConnectAtm.AtmData> getAtm(String railcd, String lncd, String stincd) throws IOException, ParseException {
		return connectAtm.loadAtmInfo(railcd, lncd, stincd);
	}
	public List<ConnectToil.ToilData> getToil(String railcd, String lncd, String stincd) throws IOException, ParseException {
		return connectToil.loadToil(railcd, lncd, stincd);
	}
	public void setCode() throws Exception { //API요청 변수 코드 설정
		System.out.println("setCode method is called");
		excelData.processExcel(search);
		this.railCd = excelData.getRailcd();
		this.lnCd = excelData.getLncd();
		this.stinCd = excelData.getStincd();
		this.stinNm = excelData.getStinNm();
		this.lnNm = excelData.getLnNm();
	}
	public void setAllCode() throws Exception { //API요청 변수 코드 설정
		System.out.println("setCode method is called");
		excelData.getAllCode();
		this.railCd = excelData.getRailcd();
		this.lnCd = excelData.getLncd();
		this.stinCd = excelData.getStincd();
		this.stinNm = excelData.getStinNm();
		this.lnNm = excelData.getLnNm();
	}
	public void setLoc(String railcd, String lncd, String stincd,String stinNm) throws IOException, ParseException {
		connectStation.loadStation(railcd,lncd,stincd,stinNm);  
		String addr = getAddr();
		naverMaps.map_geocode(addr);
		this.stinLocLat = naverMaps.getY();
		this.stinLocLon = naverMaps.getX();  //지도 API 요청변수 좌표설정
	}
	public int cpDistance() throws IOException, ParseException {
		int minIdx=0;
		double minDis = 999999999;
		for(int i = 0; i<railCd.size();i++) {
			connectStation.loadStation(railCd.get(i), lnCd.get(i), stinCd.get(i), stinNm.get(i));
			String addr = getAddr();
			naverMaps.map_geocode(addr);
			double x = Double.parseDouble(naverMaps.getX());
			double y = Double.parseDouble(naverMaps.getY());
			double distance = calDistance(y,x,myY,myX);
			if(minDis>distance) {
				minDis = distance;
				minIdx = i; 
			}
			
			
		}
		return minIdx;
	}
	 public double calDistance(double lat1, double lon1, double lat2, double lon2) {
	        double R = 6371.0; // 지구 반지름 (단위: km)

	        double dLat = lat2 - lat1;
	        double dLon = lon2 - lon1;

	        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
	                + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dLon / 2) * Math.sin(dLon / 2);

	        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	        return R * c;
	    }

		
	public String getMapImage() {
		return naverMaps.map_service(stinLocLon, stinLocLat);
	}
	
	public void setSearch(String search) {
		this.search = search;
	}
	public String getSearch() {
		return search;
	}
	public String getrailCd(int index) {
		return railCd.get(index);
	}
	public String getlnCd(int index) {
		return lnCd.get(index);
	}
	public String getStinCd(int index) {
		return stinCd.get(index);
	}
	public String getStinNm(int index) {
		return stinNm.get(index);
	}
	public ArrayList<String> getLnNm() {
		return lnNm;
	}	
	public ArrayList<String> getStinNm(){
		return stinNm;
	}
	public String geton() {
		return stinLocLon;
	}
	public String getat() {
		return stinLocLat;
	}
	public String getAddr() {
		return connectStation.getAddr();
	}
	
	
	
	
}
