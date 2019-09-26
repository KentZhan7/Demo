import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;


public class TestSXSSFWorkbook {

	public static void main(String[] args) {
//		Long start = System.currentTimeMillis();
//		
//		File file = new File("C:\\Users\\tp1231\\03SLM08-D1_1080806.xls");
//	    FileInputStream fis = new FileInputStream(file);
//	    
//        SXSSFWorkbook book = new SXSSFWorkbook(fis);
//        File tempfile = null;
//        FileOutputStream out = null;
//        List<ExcelShellProperty> sheets = new LinkedList<ExcelShellProperty>();
//        
//        ExcelShellProperty esp = new ExcelShellProperty();
//        esp.setSheetName("03SLM.08-D");
        
        
//        sheets.add(esp);
//        try {
//            book = new SXSSFWorkbook(100);//keep 100 rows in memory, exceeding rows will be flushed to disk
//            int sheetNo = 1;
//            for (ExcelShellProperty excelShellProperty : sheets) {
//                Sheet sheet = book.createSheet(excelShellProperty.getSheetName());
//                
//                // add
//                Font headFont = book.createFont();
//                headFont.setFontName("宋體");
//                headFont.setColor(IndexedColors.BLUE.index);
//                headFont.setBoldweight((short)12);
//
//                // 樣式設置
//                CellStyle cellStyle = book.createCellStyle();
//                cellStyle.setFont(headFont);
//                cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
//                cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//                cellStyle.setFillBackgroundColor(IndexedColors.YELLOW.index);
//                
//                String[] colView = excelShellProperty.getColView();
//                int i = 0;
//                //生成表頭信息
//                Row row = sheet.createRow(0);
//                for (int len = colView.length; i < len; ++i) {
//                    sheet.setColumnWidth(i, 3766);
//                    Cell cell = row.createCell(i);
//                    cell.setCellValue(colView[i]);
//                    cell.setCellStyle(cellStyle);   
//                }
//
//                setExcelBody(excelShellProperty.getDataList(),excelShellProperty.getColProperties(),excelShellProperty.getExtMsg(),colView.length, sheet,headFont,cellStyle);
//                ++sheetNo;
//            }
//            
//            // 將流信息轉換為文件流 創建文件流
//            String tempFileName = createTempFileName(n);
//            tempfile = new File(fileDir + "//" + tempFileName);
//            System.out.println("@@ExcelUtils2Xlsx 文件路徑" + tempfile.getAbsolutePath()+ ";文件名稱為" + tempfile.getName());
//            out = new FileOutputStream(tempfile);
//            book.write(out);
//            out.flush();
//            book.dispose();
//            
//            Long end = System.currentTimeMillis();
//            System.out.println("@@ExcelUtils2Xlsx create excel文件  end,生成excel文件用時 "+(end-start)+"毫秒");
//            return tempfile;
//        } catch (Exception e) {
//            System.out.println("@@ExcelUtils2Xlsx occur exception",e);
//        } finally {
//            try {
//                if(out != null){
//                    IOUtils.closeQuietly(out);
//                }
//            } catch (Exception e2) {
//            	System.out.println("@@ExcelUtils2Xlsx downLoad occur exception",e2);
//            }
//        }
//        return null;
        
	}

}

class ExcelShellProperty {
    
    /**
     * excel表頭屬性值
     */
    private String[] colProperties;
    
    /**
     * excel表格展示信息
     */
    private String[] colView;
    
    /**
     * excel sheet名稱
     */
    private String sheetName;
    
    /**
     * excel 數據
     */
    private List<Map<String,Object>> dataList;
    
    /**
    * excel 附加信息  如總記錄數等 
    */
    private String extMsg;
    
    
    public String[] getColProperties() {
        return colProperties;
    }
    public void setColProperties(String[] colProperties) {
        this.colProperties = colProperties;
    }
    public String[] getColView() {
        return colView;
    }
    public void setColView(String[] colView) {
        this.colView = colView;
    }
    public String getSheetName() {
        return sheetName;
    }
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
    public List<Map<String,Object>> getDataList() {
        return dataList;
    }
    public void setDataList(List<Map<String,Object>> dataList) {
        this.dataList = dataList;
    }
    public String getExtMsg() {
        return extMsg;
    }
    public void setExtMsg(String extMsg) {
        this.extMsg = extMsg;
    }
}