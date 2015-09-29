import javax.xml.parsers.*;

import org.xml.sax.*;
import org.w3c.dom.*;


import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Thijs on 24/09/2015.
 */
public class XMLParser {

    private HashMap<String, String> measurements;
    private Document dom;
    /**
     * Prepare the document so we can parse
     */
    public void readXML(String xml) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            // Obtain DOM Document instance from the XML string
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.parse(new InputSource(new StringReader(xml)));

            // Parse the XML string and create measurement objects from it
            parseDocument();

        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    /**
     * Parse the XML file and create a new 'Measurement' object which contains all the data
     */
    private synchronized void parseDocument() {
        // Create new hashmap, in which we will store all measurements data
        measurements = new HashMap<String, String>();

        // Loop through every <MEASUREMENT>
        Element docEle = dom.getDocumentElement();

        NodeList nl = docEle.getElementsByTagName("MEASUREMENT");

        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                // Get all the elements within the <MEASUREMENT> tag
                Element el = (Element) nl.item(i);

                // Clear the hashmap
                measurements.clear();

                // Put all measurement values in the hashmap
                measurements.put("STN", getValueOfTag(el, "STN"));
                measurements.put("DATE", getValueOfTag(el, "DATE"));
                measurements.put("TIME", getValueOfTag(el, "TIME"));
                measurements.put("TEMP", getValueOfTag(el, "TEMP"));
                measurements.put("DEWP", getValueOfTag(el, "DEWP"));
                measurements.put("STP", getValueOfTag(el, "STP"));
                measurements.put("SLP", getValueOfTag(el, "SLP"));
                measurements.put("VISIB", getValueOfTag(el, "VISIB"));
                measurements.put("WDSP", getValueOfTag(el, "WDSP"));
                measurements.put("PRCP", getValueOfTag(el, "PRCP"));
                measurements.put("SNDP", getValueOfTag(el, "SNDP"));
                measurements.put("FRSHTT", getValueOfTag(el, "FRSHTT"));
                measurements.put("CLDC", getValueOfTag(el, "CLDC"));
                measurements.put("WNDDIR", getValueOfTag(el, "WNDDIR"));

                // Create Measurment object and pass on the hashmap for the constructor
//                new Measurement(measurements);

                Iterator it = measurements.entrySet().iterator();



                String tablename = "unwdi";
                String values = "";

                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    values += pair.getValue() + ", ";
                    it.remove(); // avoids a ConcurrentModificationException
                }

                String sql = "INSERT INTO " + tablename + " VALUES(" + values;

                sql = sql.substring(0, sql.length()-2);
                sql += ")";

                System.out.println(sql);
            }
        }
    }

    /**
     * Get the value of a specific tag within the Element
     */
    private String getValueOfTag(Element doc, String tag) {
        String value = null;
        NodeList nl;
        nl = doc.getElementsByTagName(tag);
        if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
            value = nl.item(0).getFirstChild().getNodeValue();
        }
        return value;
    }
}
