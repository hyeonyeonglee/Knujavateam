package whereLocker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import org.json.simple.parser.ParseException;

public class Controller  {
	
private Model model = new Model();
private View view = new View();
private SearchList searchList = new SearchList();
String railcd, lncd, stincd;


public void handleSearchButtonClick(String searchText) throws Exception {
	model.setSearch(searchText);
	model.setCode();
	searchList.setController(this);
	searchList.refreshList(model.getStinNm(),model.getLnNm());
	searchList.setVisible(true);

}
public void handleListClick(int index) throws IOException, ParseException {
	setParameter(index);
	model.setLoc(railcd, lncd, stincd, model.getStinNm(index));
	setTextArea();
	showMaps();
	test();
}
public void handleCloseClick() throws Exception {
	model.setAllCode();
	int idx =model.cpDistance();
	setParameter(idx);
	model.setLoc(railcd, lncd, stincd, model.getStinNm(idx));
	view.setSrcTxt(model.getStinNm(idx));
	setTextArea();
	showMaps();
}

public void setParameter(int index) {
	railcd = model.getrailCd(index);
	lncd = model.getlnCd(index);
	stincd = model.getStinCd(index);
	
}
public void showMaps() {
	String path = model.getMapImage();
	ImageIcon img = new ImageIcon(path);
	view.setIcon(img);
}
public void setTextArea() throws IOException, ParseException {
     
	view.clearTextArea(view.textAreaLocker);
	view.clearTextArea(view.textAreaToil);
	view.clearTextArea(view.textAreaAtm);
	
	List<ConnectLocker.LockerData> lockerData =model.getLock(railcd,lncd,stincd);
	if(lockerData.size()== 0) {
		String lockerInfo ="물품보관함 없음";
		view.setTextArea(view.textAreaLocker, lockerInfo);
	}
	else {
    for (ConnectLocker.LockerData itemData : lockerData) {
        String lockerInfo = itemData.num+"\n"
        		+"상세위치: " + itemData.dtloc + "\n"
                + "이용요금: " + itemData.utlFare + "\n"
                + "지상구분: " + itemData.grndDvNm + "\n\n";

        view.setTextArea(view.textAreaLocker, lockerInfo);
    }
	}
    List<ConnectToil.ToilData>  toilData = model.getToil(railcd, lncd, stincd);
    if(toilData.size()==0) {
		String lockerInfo ="화장실 없음";
		view.setTextArea(view.textAreaToil, lockerInfo);
	}
    else {
    for (ConnectToil.ToilData itemData : toilData) {
        
        String lockerInfo = itemData.num+"\n"
        		+"상세위치: " + itemData.dtloc + "\n"
                + "개찰구내외구분: " + itemData.gateInotDvNm + "\n"
                + "지상구분: " + itemData.grndDvNm + "\n\n";

        view.setTextArea(view.textAreaToil, lockerInfo);
    }
    }
    List<ConnectAtm.AtmData>  atmData = model.getAtm(railcd, lncd, stincd);
    if(atmData.size()==0) {
		String lockerInfo ="Atm기기 없음";
		view.setTextArea(view.textAreaAtm, lockerInfo);
	}
    else {
    for (ConnectAtm.AtmData itemData : atmData) {
        // Customize how you want to display the data in the JTextArea
        String lockerInfo = itemData.num+"\n"
        		+"상세위치: " + itemData.dtloc + "\n"
                + "이용가능시간: " + itemData.utlPsbHr + "\n"
                + "지상구분: " + itemData.grndDvNm + "\n\n";

        view.setTextArea(view.textAreaAtm, lockerInfo);
    }
    }
}


public void setModel(Model model) {
	this.model = model;
}
public void setView(View view) {
	this.view = view;
}

public void test() {
	System.out.println("railcd:"+railcd+"\n"+
"lncd: "+lncd+"\n"
+"stincd: "+stincd+"\n"
+"search:"+model.getSearch()
+"경도: "+model.geton()
+"위도: "+model.getat());
}
}
