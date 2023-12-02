package whereLocker;


import java.util.ArrayList;
import java.util.Scanner;
import com.aspose.cells.*;

public class ExcelData {
    private ArrayList<String> railcd;
    private ArrayList<String> lncd;
    private ArrayList<String> stincd;
    private ArrayList<String> stinNm;
    private ArrayList<String> lnNm;

    public ExcelData() {
        railcd = new ArrayList<>();
        lncd = new ArrayList<>();
        stincd = new ArrayList<>();
        stinNm = new ArrayList<>();
        lnNm = new ArrayList<>();
    }

    public ArrayList<String> getRailcd() {
        return railcd;
    }

    public ArrayList<String> getLncd() {
        return lncd;
    }

    public ArrayList<String> getStincd() {
        return stincd;
    }
    public ArrayList<String> getStinNm(){
    	return stinNm;
    }
    public ArrayList<String> getLnNm(){
    	return lnNm;
    }

    public void processExcel(String searchString) throws Exception {
    	   railcd.clear();
    	    lncd.clear();
    	    stincd.clear();
    	    stinNm.clear();
    	    lnNm.clear();
        ArrayList<Integer> cellrow = new ArrayList<>();
        Workbook workbook = new Workbook("C:\\Users\\gusdu\\eclipse-workspace\\javaTeamProject\\운영기관_역사_코드정보_2023.09.26.xlsx");  //엑셀위치에 따라 수정
        Worksheet worksheet = workbook.getWorksheets().get(0);
        Cells cells = worksheet.getCells();
        FindOptions findOptions = new FindOptions();
        findOptions.setLookAtType(LookAtType.CONTAINS);

        Cell cell = cells.find(searchString, null, findOptions);
        while (cell != null) {
        	if(cell.getColumn()==5){
        		cellrow.add(cell.getRow());
        	}
            cell = cells.find(searchString, cell, findOptions);
        }
        for (int i = 0; i < cellrow.size(); i++) {
            int row = cellrow.get(i);
            Cell foundCell = cells.get(row, 0);
            railcd.add(foundCell.getStringValue());
            foundCell = cells.get(row, 2);
            lncd.add(foundCell.getStringValue());
            foundCell = cells.get(row, 4);
            stincd.add(foundCell.getStringValue());
            foundCell = cells.get(row, 5);
            stinNm.add(foundCell.getStringValue());
            foundCell = cells.get(row,4);
            lnNm.add(foundCell.getStringValue());
        }
        
    }
    



    public static void main(String[] args) throws Exception {
    	//해당부분은 필요에따라 다른곳에서 입력받은 값으로 대채가능
        Scanner scanner = new Scanner(System.in);
        System.out.print("찾을 문자열을 입력하세요: ");
        String searchString = scanner.nextLine();
       
        //필수부분
        ExcelData excelData = new ExcelData();
        excelData.processExcel(searchString);
        ArrayList<String> railcdResult = excelData.getRailcd();
        ArrayList<String> lncdResult = excelData.getLncd();
        ArrayList<String> stincdResult = excelData.getStincd();
        //여기까지 필수 나머지 위로 아래로 다 삭제가능
        
        // 결과 값출력 삭제해서 사용할것
        System.out.println("Railcd Result: " + railcdResult);
        System.out.println("Lncd Result: " + lncdResult);
        System.out.println("Stincd Result: " + stincdResult);
    }
}