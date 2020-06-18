package com.baizhi.um.fastdfs;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadCallback;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;

/**
 * @Author SIMBA1949
 * @Date 2020/6/8 21:06
 */
public class FastDFS {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    public void uploadFile() throws IOException {
        FileInputStream fis = new FileInputStream("d:/a.jpg");

        StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(fis, fis.available(), "jpg", null);

        System.out.println("file id : ");
        System.out.println(storePath.getFullPath());
    }

    public void downloadFile() throws IOException {
        ByteArrayOutputStream downloadFile = fastFileStorageClient.downloadFile("group1", "", new DownloadCallback<ByteArrayOutputStream>() {
            @Override
            public ByteArrayOutputStream recv(InputStream inputStream) throws IOException {
                ByteArrayOutputStream fos = new ByteArrayOutputStream();
                IOUtils.copy(inputStream,fos);
                return fos;
            }
        });

        IOUtils.copy(new ByteArrayInputStream(downloadFile.toByteArray()),new FileOutputStream("d:/d.jpg"));
    }
}
