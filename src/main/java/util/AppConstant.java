package util;

public class AppConstant {

  public static final long EXPLICIT_WAIT_DURATION = 10;

  private AppConstant() {}

  public static class ExceptionMessage {
    public static final String NO_SUCH_ELEMENT = "No such element {}";
    public static final String UNDEFINED_BROWSER_TYPE = "Undefined Browser type {}";
    public static final String ERROR_WHILE_READING_THE_EXCEL_FILE_NAME_SHEET =
        "Error while reading the excel file name {}, sheet {}";

    private ExceptionMessage() {}
  }
}
