package cz.towns;

import cz.towns.parsers.Parser;
import cz.towns.parsers.XMLParser;
import cz.towns.service.FileDownloader;

public class Main {
    public static void main(String[] args) {
        FileDownloader fileDownloader = new FileDownloader();
        fileDownloader.downloadFile("https://www.smartform.cz/download/kopidlno.xml.zip");

        Parser parser = new XMLParser();
        parser.parseFile(fileDownloader.getFileName());
    }
}