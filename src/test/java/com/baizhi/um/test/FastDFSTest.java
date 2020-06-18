package com.baizhi.um.test;

import com.baizhi.um.Application;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadCallback;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

/**
 * @Author SIMBA1949
 * @Date 2020/6/8 21:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class FastDFSTest {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Test
    public void uploadFile() throws IOException {
        FileInputStream fis = new FileInputStream("d:/d.jpg");

        StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(fis, fis.available(), "jpg", null);

        System.out.println("file id : ");
        System.out.println(storePath.getFullPath());
    }

    @Test
    public void downloadFile() throws IOException {
        ByteArrayOutputStream downloadFile = fastFileStorageClient.downloadFile("group1", "M01/00/00/wKjqZF7eOqGAfypAAAD_38rUsJE768.jpg", new DownloadCallback<ByteArrayOutputStream>() {
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
