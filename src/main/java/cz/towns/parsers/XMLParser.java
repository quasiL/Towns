package cz.towns.parsers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import cz.towns.model.Town;
import cz.towns.model.TownPart;
import cz.towns.service.TownService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLParser extends Parser {

    private final List<Town> towns;
    private final List<TownPart> townParts;
    private final TownService townService;

    public XMLParser() {
        this.towns = new ArrayList<>();
        this.townParts = new ArrayList<>();
        this.townService = new TownService();
    }

    @Override
    void parseData(String fileName) {
        try {
            File inputFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            parseTownParts(doc);
            parseTowns(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method will parse tag Obec
     * @param doc root
     */
    private void parseTowns(Document doc) {

        NodeList tag2List = doc.getElementsByTagName("vf:Obec");
        Element tag2Element = (Element) tag2List.item(0);

        NodeList tagList = tag2Element.getElementsByTagName("obi:Kod");
        for (int i = 0; i < tagList.getLength(); i++) {
            Town town = new Town();
            String nestedTagContent = tagList.item(i).getTextContent();
            town.setCode(nestedTagContent);
            towns.add(town);
        }

        tagList = tag2Element.getElementsByTagName("obi:Nazev");
        for (int i = 0; i < tagList.getLength(); i++) {
            String nestedTagContent = tagList.item(i).getTextContent();
            towns.get(i).setName(nestedTagContent);
        }
    }

    /**
     * Method will parse tag CastiObci
     * @param doc root
     */
    private void parseTownParts(Document doc) {

        NodeList tag2List = doc.getElementsByTagName("vf:CastiObci");
        Element tag2Element = (Element) tag2List.item(0);

        NodeList tagList = tag2Element.getElementsByTagName("coi:Kod");
        for (int i = 0; i < tagList.getLength(); i++) {
            TownPart townPart = new TownPart();
            String nestedTagContent = tagList.item(i).getTextContent();
            townPart.setCode(nestedTagContent);
            townParts.add(townPart);
        }

        tagList = tag2Element.getElementsByTagName("coi:Nazev");
        for (int i = 0; i < tagList.getLength(); i++) {
            String nestedTagContent = tagList.item(i).getTextContent();
            townParts.get(i).setName(nestedTagContent);
        }

        tagList = tag2Element.getElementsByTagName("coi:Nazev");
        for (int i = 0; i < tagList.getLength(); i++) {
            String nestedTagContent = tagList.item(i).getTextContent();
            townParts.get(i).setName(nestedTagContent);
        }

        tagList = tag2Element.getElementsByTagName("coi:Obec");
        for (int i = 0; i < tagList.getLength(); i++) {
            NodeList tagList2 = tag2Element.getElementsByTagName("obi:Kod");
            townParts.get(i).setTownCode(tagList2.item(0).getTextContent());
        }
    }

    @Override
    void saveData() {
        townService.addTowns(towns);
        townService.addTownParts(townParts);
    }
}