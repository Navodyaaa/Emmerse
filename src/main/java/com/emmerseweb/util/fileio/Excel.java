package com.emmerseweb.util.fileio;

import com.emmerseweb.exception.DataReadException;
import com.emmerseweb.util.AppConstant;
import com.emmerseweb.util.StringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

    public static <T> List<T> readExcelData(String fileName, String sheetName, Class<T> clazz) {
        List<Map<String, String>> dataList = readExcelData(fileName, sheetName);
        List<T> result = new ArrayList<>();
        
        for (Map<String, String> rowData : dataList) {
            try {
                T instance = clazz.getDeclaredConstructor().newInstance();
                for (Map.Entry<String, String> entry : rowData.entrySet()) {
                    try {
                        clazz.getDeclaredField(entry.getKey()).set(instance, entry.getValue());
                    } catch (NoSuchFieldException e) {
                        // Skip if field doesn't exist
                    }
                }
                result.add(instance);
            } catch (Exception e) {
                throw new DataReadException("Error creating instance of " + clazz.getName(), e);
            }
        }
        return result;
    }

    public static List<Map<String, String>> readExcelData(String fileName, String sheetName) {
        List<Map<String, String>> dataList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(fileName));
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet " + sheetName + " not found in " + fileName);
            }

            Iterator<Row> rowIterator = sheet.iterator();
            if (!rowIterator.hasNext()) {
                return dataList;
            }

            Row headerRow = rowIterator.next();
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue().trim());
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Map<String, String> rowData = new HashMap<>();

                for (int i = 0; i < headers.size(); i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    rowData.put(headers.get(i), getCellValueAsString(cell));
                }

                dataList.add(rowData);
            }
        } catch (IOException e) {
            throw new DataReadException(
                StringUtil.formatString(AppConstant.ExceptionMessage.ERROR_WHILE_READING_THE_EXCEL_FILE_NAME_SHEET, fileName, sheetName),
                e);
        }
        return dataList;
    }

    private static String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
            case ERROR:
            default:
                return "";
        }
    }
}
