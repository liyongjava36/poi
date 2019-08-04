package com.huawei.test;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 创建单元格写入数据
 */
public class PoiTest01 {
    public static void main(String[] args) throws IOException {
        // 创建工作簿 2007版本
        Workbook wb = new XSSFWorkbook();
        // 创建一个sheet
        wb.createSheet("Collection Guide");
        // 创建行对象 索引从零开始

        // 创建单元格对象
        // 输出保存
        FileOutputStream fos = new FileOutputStream("D:\\test.xlsx");
        // 写入文件
        wb.write(fos);
        // 关闭流
        fos.close();
    }
}
