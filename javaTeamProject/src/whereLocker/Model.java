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
	private double stinLocLon, stinLocLat;
	private ArrayList<String> railCd;
    private ArrayList<String> lnCd;
    private ArrayList<String> stinCd;
	
	
	
	public Model() {
		this.connectLocker = new ConnectLocker();
		this.connectAtm = new ConnectAtm();
		this.connectToil = new ConnectToil();
		this.connectStation= new ConnectStation();
		this.naverMaps = new NaverMaps(); 	
		this.excelData = new ExcelData();
	}
	public List<ConnectLocker.ItemData> getLock(String railcd, String lncd, String stincd) throws IOException, ParseException {
		return connectLocker.loadLock(lncd, railcd, stincd);
	}
	public List<ConnectAtm.ItemData> getAtm(String railcd, String lncd, String stincd) throws IOException, ParseException {
		return connectAtm.loadAtmInfo(railcd, lncd, stincd);
	}
	public List<ConnectToil.ItemData> getToil(String railcd, String lncd, String stincd) throws IOException, ParseException {
		return connectToil.loadToil(railcd, lncd, stincd);
	}
	public void setLoc(String railcd, String lncd, String stincd,String stinNm) throws IOException, ParseException {
		List<ConnectStation.ItemData> itemDataList = connectStation.loadStation(railcd,lncd,stincd,stinNm);
		for(ConnectStation.ItemData itemData : itemDataList) {
			this.stinLocLon = itemData.stinLocLon;
			this.stinLocLat = itemData.stinLocLat;
		}
	}
	public String getMapImage() {
		return naverMaps.map_service(stinLocLon, stinLocLat);
	}
	public void setCode(String search) throws Exception {
		excelData.processExcel(search);
		this.railCd = excelData.getRailcd();
		this.lnCd = excelData.getLncd();
		this.stinCd = excelData.getStincd();
	}
}