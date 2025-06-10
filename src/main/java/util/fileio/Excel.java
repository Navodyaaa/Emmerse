package util.fileio;

import static util.AppConstant.ExceptionMessage.ERROR_WHILE_READING_THE_EXCEL_FILE_NAME_SHEET;

import exception.DataReadException;
import util.StringUtil;
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

  public static List<Map<String, String>> readExcelData(
      final String fileName, final String sheetName) {
    final List<Map<String, String>> dataList = new ArrayList<>();

    try (final FileInputStream fis = new FileInputStream(new File(fileName));
        final Workbook workbook = new XSSFWorkbook(fis)) {

      final Sheet sheet = workbook.getSheet(sheetName);
      if (sheet == null) {
        throw new IllegalArgumentException("Sheet " + sheetName + " not found in " + fileName);
      }

      final Iterator<Row> rowIterator = sheet.iterator();
      if (!rowIterator.hasNext()) {
        return dataList;
      }

      final Row headerRow = rowIterator.next();
      final List<String> headers = new ArrayList<>();
      for (Cell cell : headerRow) {
        headers.add(cell.getStringCellValue().trim());
      }

      while (rowIterator.hasNext()) {
        final Row row = rowIterator.next();
        final Map<String, String> rowData = new HashMap<>();

        for (int i = 0; i < headers.size(); i++) {
          final Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
          rowData.put(headers.get(i), getCellValueAsString(cell));
        }

        dataList.add(rowData);
      }
    } catch (IOException e) {
      throw new DataReadException(
          StringUtil.formatString(
              ERROR_WHILE_READING_THE_EXCEL_FILE_NAME_SHEET, fileName, sheetName),
          e);
    }
    return dataList;
  }

  private static String getCellValueAsString(final Cell cell) {
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
