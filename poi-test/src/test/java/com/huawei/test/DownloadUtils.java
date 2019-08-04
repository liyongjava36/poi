package com.huawei.test;

import java.io.IOException;

public class DownloadUtils {
    public void download (ByteArrayOutputStrem byteArrayOutputStrem, HttpServletResponse response, String returnName) throws IOException {
        response.setContentType("application/octet-stream");
        returnName = response.encodeURL(new String(returnName.getBytes(),"iso8859-1"));
        response.addHeader("content-disposition","attachment;filename=" + returnName);
        response.setContentLength(byteArrayOutputStrem.size());
        ServletOutputStream outputStream = response.getOutputStream();
        byteArrayOutputStrem.writeTo(outputStream);
        byteArrayOutputStrem.close();
        outputStream.flush();

    }
}
