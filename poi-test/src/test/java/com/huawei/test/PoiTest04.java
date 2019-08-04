package com.huawei.test;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 插入图片
 */
public class PoiTest04 {
    public static void main(String[] args) throws Exception {
        // 创建工作簿 2007版本
        Workbook wb = new XSSFWorkbook();
        // 创建一个sheet
        Sheet collection_guide = wb.createSheet("Collection Guide");

        // 读取图片流
        FileInputStream fis = new FileInputStream("D:\\a.jpg");
        // 转换成二进制数组
        byte[] bytes = IOUtils.toByteArray(fis);
        int read = fis.read(bytes);
        // 向poi中添加一张图片，返回图片在图片中的索引
        int i = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
        // 绘制图片的工具类
        CreationHelper creationHelper = wb.getCreationHelper();
        // 创建一个绘图对象
        Drawing<?> drawingPatriarch = collection_guide.createDrawingPatriarch();
        // 创建锚点，设置插入图片的坐标
        ClientAnchor clientAnchor = creationHelper.createClientAnchor();
        // 第几行
        clientAnchor.setRow1(0);
        // 第几个单元格
        clientAnchor.setCol1(0);
        // 绘制图片
        Picture picture = drawingPatriarch.createPicture(clientAnchor, i);// 图片的位置 图片的索引
        picture.resize(); // 自适应渲染图片


        // 输出保存
        FileOutputStream fos = new FileOutputStream("D:\\test.xlsx");
        // 写入文件
        wb.write(fos);
        // 关闭流
        fos.close();
    }
}
