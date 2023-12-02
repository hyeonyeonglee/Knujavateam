package whereLocker;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

public class Controller  {
	
private Model model = new Model();
private View view = new View();
String railcd, lncd, stincd;

public void setCode(String railcd, String lncd, String stincd) {
	this.railcd = railcd;
	this.lncd = lncd;
	this.stincd = stincd;
}

public void handleSearchButtonClick(String searchText) throws Exception {
	model.setSearch(searchText);
	model.setCode();
	setParameter();
	model.setLoc(railcd,  lncd, stincd, model.getSearch());
	setTextArea();
	test();
}

public void setParameter() {
	System.out.println("setParameter method is called");
	railcd = model.getrailCd();
	lncd = model.getlnCd();
	stincd = model.getStinCd();
	
}
public void updateView() {
	
}
public void setTextArea() throws IOException, ParseException {
     // Clear previous content
	view.clearTextArea(view.textAreaLocker);
	view.clearTextArea(view.textAreaToil);
	view.clearTextArea(view.textAreaAtm);
	
	List<ConnectLocker.ItemData> lockerData =model.getLock(railcd,lncd,stincd);
    for (ConnectLocker.ItemData itemData : lockerData) {
        // Customize how you want to display the data in the JTextArea
        String lockerInfo = itemData.num+"\n"
        		+"상세위치: " + itemData.dtloc + "\n"
                + "이용요금: " + itemData.utlFare + "\n"
                + "지상구분: " + itemData.grndDvNm + "\n\n";

        view.setTextArea(view.textAreaLocker, lockerInfo);
    }
    List<ConnectToil.ItemData>  toilData = model.getToil(railcd, lncd, stincd);
    for (ConnectToil.ItemData itemData : toilData) {
        // Customize how you want to display the data in the JTextArea
        String lockerInfo = itemData.num+"\n"
        		+"상세위치: " + itemData.dtloc + "\n"
                + "이용요금: " + itemData.gateInotDvNm + "\n"
                + "지상구분: " + itemData.grndDvNm + "\n\n";

        view.setTextArea(view.textAreaToil, lockerInfo);
    }
    List<ConnectAtm.ItemData>  atmData = model.getAtm(railcd, lncd, stincd);
    for (ConnectAtm.ItemData itemData : atmData) {
        // Customize how you want to display the data in the JTextArea
        String lockerInfo = itemData.num+"\n"
        		+"상세위치: " + itemData.dtloc + "\n"
                + "이용요금: " + itemData.utlPsbHr + "\n"
                + "지상구분: " + itemData.grndDvNm + "\n\n";

        view.setTextArea(view.textAreaAtm, lockerInfo);
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
+"search:"+model.getSearch());
}
}
