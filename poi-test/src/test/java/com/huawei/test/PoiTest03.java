package com.huawei.test;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 使用设置字体样式
 */
public class PoiTest03 {
    public static void main(String[] args) throws Exception {
        // 创建工作簿 2007版本
        Workbook wb = new XSSFWorkbook();
        // 创建一个sheet
        Sheet collection_guide = wb.createSheet("Collection Guide");
        Row row = collection_guide.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("字段名称");

        // 创建样式对象
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderTop(BorderStyle.THIN ); // 设置上边框细线
        cellStyle.setBorderBottom(BorderStyle.THIN ); // 设置上边框细线
        cellStyle.setBorderLeft(BorderStyle.THIN ); // 设置上边框细线
        cellStyle.setBorderRight(BorderStyle.THIN ); // 设置上边框细线
        // 创建字体对象
        Font font = wb.createFont();
        font.setFontName("华文仿宋"); // 字体
        font.setFontHeightInPoints((short)28); // 字号
        // 将字体设置到样式当中
        cellStyle.setFont(font);

        row.setHeightInPoints(50);
        // 列宽宽度指的是字符宽度
        collection_guide.setColumnWidth(0,31*256);

        // 居中显示
        cellStyle.setAlignment(HorizontalAlignment.CENTER);// 水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 垂直居中

        // 设置背景颜色
        cellStyle.setFillBackgroundColor((short) 1);
        //       cellStyle.setFillForegroundColor((short) 1);
        cellStyle.setFillPattern(FillPatternType.THICK_FORWARD_DIAG);

        // 向单元格设置样式
        cell.setCellStyle(cellStyle);

        // 输出保存
        FileOutputStream fos = new FileOutputStream("D:\\test.xlsx");
        // 写入文件
        wb.write(fos);
        // 关闭流
        fos.close();
    }
}
