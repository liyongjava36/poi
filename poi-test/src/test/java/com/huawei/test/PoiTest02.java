package com.huawei.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 使用poi创建excel
 */
public class PoiTest02 {
    public static void main(String[] args) throws IOException {
        // 创建工作簿 2007版本
        Workbook wb = new XSSFWorkbook();
        // 创建一个sheet
        Sheet collection_guide = wb.createSheet("Collection Guide");
        Row row = collection_guide.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("字段名称");
        // 输出保存
        FileOutputStream fos = new FileOutputStream("D:\\test.xlsx");
        // 写入文件
        wb.write(fos);
        // 关闭流
        fos.close();
    }
}
