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

public void setTextArea() throws IOException,ParseException {
	List<ConnectLocker.ItemData> itemlist = locker.getInfo(locker.loadLock(railcd, lncd, stincd));
	
}

}
