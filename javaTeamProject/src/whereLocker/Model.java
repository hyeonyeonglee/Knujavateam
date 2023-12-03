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
	public void setLoc(String railcd, String lncd, String stincd,String stinNm) throws IOException, ParseException {
		connectStation.loadStation(railcd,lncd,stincd,stinNm);
		String addr = getAddr();
		naverMaps.map_geocode(addr);
		this.stinLocLat = naverMaps.getY();
		this.stinLocLon = naverMaps.getX();
		
		}
		
		
	
	public String getMapImage() {
		return naverMaps.map_service(stinLocLon, stinLocLat);
	}
	public void setCode() throws Exception {
		System.out.println("setCode method is called");
		excelData.processExcel(search);
		this.railCd = excelData.getRailcd();
		this.lnCd = excelData.getLncd();
		this.stinCd = excelData.getStincd();
		this.stinNm = excelData.getStinNm();
		this.lnNm = excelData.getLnNm();
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
