package cz.towns.parsers;

/**
 * Template pattern
 */
public abstract class Parser {

    /**
     * Main template method
     * @param fileName name
     */
    public void parseFile(String fileName) {
        parseData(fileName);
        saveData();
    }

    /**
     * Method will parse the data according to the parser
     * @param fileName name
     */
    abstract void parseData(String fileName);

    /**
     * Method will store the data in the database
     */
    abstract void saveData();
}