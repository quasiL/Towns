package cz.towns.service;

import java.io.*;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileDownloader {

    private String fileName;

    /**
     * Method will download and unzip files
     * @param fileUrl url
     */
    public void downloadFile(String fileUrl) {
        try {
            URL url = new URL(fileUrl);
            InputStream in = url.openStream();

            File tempFile = File.createTempFile("archive", ".zip");
            FileOutputStream out = new FileOutputStream(tempFile);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            ZipInputStream zipIn = new ZipInputStream(new FileInputStream(tempFile));
            ZipEntry entry;
            while ((entry = zipIn.getNextEntry()) != null) {
                if (!entry.isDirectory()) {
                    fileName = entry.getName();
                    FileOutputStream fileOut = new FileOutputStream(fileName);
                    byte[] zipBuffer = new byte[1024];
                    int zipBytesRead;
                    while ((zipBytesRead = zipIn.read(zipBuffer)) != -1) {
                        fileOut.write(zipBuffer, 0, zipBytesRead);
                    }
                    fileOut.close();
                }
            }
            zipIn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getFileName() {
        return fileName;
    }
}