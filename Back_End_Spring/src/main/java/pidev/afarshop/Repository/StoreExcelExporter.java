package pidev.afarshop.Repository;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Repository;
import pidev.afarshop.Entity.Store;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Repository
public class StoreExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<Store> storeList;



    public StoreExcelExporter(List<Store> storeList) {
        this.storeList = storeList;
        workbook = new XSSFWorkbook();
        sheet= workbook.createSheet("stores");
    }

    private void writHeaderRow(){
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("storeId");

        cell  = row.createCell(1);
        cell.setCellValue("ImagePath");

        cell  = row.createCell(2);
        cell.setCellValue("Score");

        cell  = row.createCell(3);
        cell.setCellValue("contactInformation");

        cell  = row.createCell(4);
        cell.setCellValue("storeDescription");

        cell  = row.createCell(5);
        cell.setCellValue("storeEmailAddress");

        cell  = row.createCell(6);
        cell.setCellValue("storeLocation");

        cell  = row.createCell(7);
        cell.setCellValue("storeName");

    }

    private void writeDataRows(){
        int rowCount = 1;
        for(Store store : storeList){
            Row row = sheet.createRow(rowCount++);

            Cell cell = row.createCell(0);
            cell.setCellValue(store.getStoreId());

            cell = row.createCell(1);
            cell.setCellValue(store.getImagePath());

            cell = row.createCell(2);
            cell.setCellValue(store.getScore());

            cell = row.createCell(3);
            cell.setCellValue(store.getContactInformation());


            cell = row.createCell(4);
            cell.setCellValue(store.getStoreDescription());

            cell = row.createCell(5);
            cell.setCellValue(store.getStoreEmailAddress());

            cell = row.createCell(6);
            cell.setCellValue(store.getStoreLocation());

            cell = row.createCell(7);
            cell.setCellValue(store.getStoreName());


        }

    }

    public void export (HttpServletResponse response) throws IOException {
        writHeaderRow();
        writeDataRows();

        ServletOutputStream outputStream =  response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

    }

}
