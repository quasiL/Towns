package cz.towns;

import cz.towns.parsers.Parser;
import cz.towns.parsers.XMLParser;
import cz.towns.service.FileDownloader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Starting application...");
        FileDownloader fileDownloader = new FileDownloader();
        fileDownloader.downloadFile("https://www.smartform.cz/download/kopidlno.xml.zip");

        Parser parser = new XMLParser();
        parser.parseFile(fileDownloader.getFileName());
    }
}