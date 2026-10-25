package GenericUtility;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtiility {

    public String readDataFromExcel(String sheet, int row, int cell)
            throws Exception {

        FileInputStream fis = new FileInputStream(
                "./src/test/resources/DDT/CreateMilestone.xlsx");

        Workbook wb = WorkbookFactory.create(fis);

        String data = wb.getSheet(sheet)
                        .getRow(row)
                        .getCell(cell)
                        .toString();

        wb.close();
        fis.close();

        return data;
    }
}

