package com.huawei.test;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class PoiReadTest05 {

    public static void main(String[] args) throws IOException {
        // 获取工作簿
        Workbook wb = new XSSFWorkbook("C:\\Users\\92112\\Desktop\\用章说明.xlsx");
        // 获取sheet
        Sheet sheet = wb.getSheetAt(0);
        // 得到行对象遍历
        for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            StringBuilder sb = new StringBuilder();
                // 遍历循环每一行
                for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
                    Cell cell = row.getCell(cellNum);
                    Object cellValue = getCellValue(cell);
                    sb.append(cellValue).append("-");
                }
                System.out.println(sb.toString());
            }


    }
        public static Object getCellValue(Cell cell) {

            CellType  cellType = cell.getCellTypeEnum();

            Object value = null;
        // 获取单元格的属性类型
            switch (cellType) {
                case STRING:
                    value = cell.getStringCellValue();
                    break;
                case BOOLEAN: // buer
                    value = cell.getBooleanCellValue();
                    break;
                case NUMERIC: // 日期
                    if (DateUtil.isCellDateFormatted(cell)) {
                        value = cell.getDateCellValue();
                    }else {// 数字
                        value = cell.getNumericCellValue();
                    }
                    break;
                case FORMULA: // 公式类型
                    value = cell.getCellFormula();
                    break;
                default :
                    break;
            }
            return value;
        }

}
