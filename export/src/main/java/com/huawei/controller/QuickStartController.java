package com.huawei.controller;

import com.huawei.Utils.DownLoadUtils;
import com.huawei.dao.UserMapper;
import com.huawei.po.User;
import org.apache.catalina.connector.Response;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class QuickStartController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/findAll")

    public void findAll(HttpServletResponse response) throws IOException {

        // 获取报表数据
        List<User> all = userMapper.findAll();
        // 创建一个工作簿
        Workbook wb = new XSSFWorkbook();
        // 创建sheet页
        Sheet sheet = wb.createSheet("test");
        // 创建行
        // 标题
        String[] titles = "ID,姓名,生日,性别,地址".split(",");
        // 处理标题
        // 创建单元格
        Row row = sheet.createRow(0);
        int index = 0;
        for (String title : titles) {
            Cell cell = row.createCell(index);
            index++;
            cell.setCellValue(title);
        }
        int rowIndex = 1;
        Cell cell = null;
        for (User user : all) {
            row = sheet.createRow(rowIndex);

            cell = row.createCell(0);
            cell.setCellValue(user.getId());
            cell = row.createCell(1);
            cell.setCellValue(user.getUsername());
            cell = row.createCell(2);
            cell.setCellValue(user.getBirthday());
            cell = row.createCell(3);
            cell.setCellValue(user.getSex());
            cell = row.createCell(4);
            cell.setCellValue(user.getAddress());

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            wb.write(byteArrayOutputStream);
            new DownLoadUtils().download(byteArrayOutputStream, response, "用户.xlsx");
        }

    }

    @GetMapping("export")
    public void export(HttpServletResponse response) throws IOException {

        // 获取报表数据
        List<User> all = userMapper.findAll();
        // 加载模板
        Resource resource = new ClassPathResource("user.xlsx");
        FileInputStream fis = new FileInputStream(resource.getFile());

        //根据模板创建工作簿
        Workbook wb = new XSSFWorkbook(fis);
        // 读取工作表
        Sheet sheet = wb.getSheetAt(0);
        // 抽取公共样式
        Row row = sheet.getRow(1);
        short lastCellNum = row.getLastCellNum();
        System.out.println(lastCellNum);
        CellStyle[] cellStyle = new CellStyle[row.getLastCellNum()];

        for (int i = 0; i < cellStyle.length; i++) {
            Cell cell = row.getCell(i);
            // 得到格子样式
            cellStyle[i] = cell.getCellStyle();
        }

        // 构造单元格
        int rowIndex = 1;
        Cell cell = null;
        for (User user : all) {
            row = sheet.createRow(rowIndex);

            cell = row.createCell(0);
            cell.setCellValue(user.getId());
            cell.setCellStyle(cellStyle[0]);

            cell = row.createCell(1);
            cell.setCellValue(user.getUsername());
            cell.setCellStyle(cellStyle[1]);

            cell = row.createCell(2);
            cell.setCellValue(user.getBirthday());
            cell.setCellStyle(cellStyle[2]);

            cell = row.createCell(3);
            cell.setCellValue(user.getSex());
            cell.setCellStyle(cellStyle[3]);

            cell = row.createCell(4);
            cell.setCellValue(user.getAddress());
            cell.setCellStyle(cellStyle[4]);
            rowIndex++;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        wb.write(byteArrayOutputStream);
        new DownLoadUtils().download(byteArrayOutputStream, response, "用户.xlsx");
    }

}
