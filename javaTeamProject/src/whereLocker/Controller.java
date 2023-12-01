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
	updateView();
}
public void setTextArea(String railcd, String lncd, String stincd) {
	
}
public void setParameter() {
	railcd = model.getrailCd();
	lncd = model.getlnCd();
	stincd = model.getStinCd();
}
public void updateView() {
	
}

}
