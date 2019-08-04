package com.huawei.Utils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class DownLoadUtils {
    public void download(ByteArrayOutputStream byteArrayOutputStrem, HttpServletResponse response, String returnName) throws IOException {
        response.setContentType("application/octet-stream");
        returnName = response.encodeURL(new String(returnName.getBytes(), "iso8859-1"));
        response.addHeader("content-disposition", "attachment;filename=" + returnName);
        response.setContentLength(byteArrayOutputStrem.size());
        ServletOutputStream outputStream = response.getOutputStream();
        byteArrayOutputStrem.writeTo(outputStream);
        byteArrayOutputStrem.close();
        outputStream.flush();

    }

  /*  public void download(HttpServletResponse response, String fileName) throws IOException {
        response.setHeader("content-disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1"));
        File path = new File("src/main/resources/file");
        if (!path.exists()){
            path.mkdirs();
        }
        File target = new File(path.getAbsolutePath()+File.separator+fileName);
        FileInputStream fileInputStream = new FileInputStream(target);
        ServletOutputStream servletOutputStream = response.getOutputStream();
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = fileInputStream.read(buffer)) != -1) {
            servletOutputStream.write(buffer, 0, len);
        }
        servletOutputStream.flush();
        servletOutputStream.close();
        fileInputStream.close();
    }*/
}
