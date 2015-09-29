import javax.xml.parsers.*;

import org.xml.sax.*;
import org.w3c.dom.*;


import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by Thijs on 24/09/2015.
 */
public class XMLParser {

    private Map<String, String> measurements;
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
                measurements.put("station_id", getValueOfTag(el, "STN"));
                measurements.put("local_date", getValueOfTag(el, "DATE"));
                measurements.put("local_time", getValueOfTag(el, "TIME"));
                measurements.put("temperatuur", getValueOfTag(el, "TEMP"));
                measurements.put("dauwpunt", getValueOfTag(el, "DEWP"));
                measurements.put("luchtdruk_station", getValueOfTag(el, "STP"));
                measurements.put("luchtdruk_zee", getValueOfTag(el, "SLP"));
                measurements.put("zichtbaarheid", getValueOfTag(el, "VISIB"));
                measurements.put("neerslag", getValueOfTag(el, "PRCP"));
                measurements.put("sneeuwdiepte", getValueOfTag(el, "SNDP"));
                measurements.put("bewolking", getValueOfTag(el, "CLDC"));
                measurements.put("windrichting", getValueOfTag(el, "WNDDIR"));
                measurements.put("windsnelheid", getValueOfTag(el, "WDSP"));
                measurements.put("gebeurtenissen", getValueOfTag(el, "FRSHTT"));


                Iterator<Entry<String, String>> it = measurements.entrySet().iterator();
                while (it.hasNext()) {
                	
                    Map.Entry<String, String> pair = (Entry<String, String>)it.next();
                    //System.out.println(pair.getKey() + " = " + pair.getValue());
                    if(pair.getValue() == null) {
                    	try {
        	            	DatabaseConnect database = new DatabaseConnect();
        	            	ResultSet rs = database.executeSQL("SELECT AVG(" + pair.getKey() + ") FROM measurement WHERE station_id="+ measurements.get("station_id") +";");
        	            	System.out.println(rs);
        	            	String val;
        					val = rs.getString(0);
        					pair.setValue(val);
        				} catch (SQLException e) {}
                    	
                    }
                    
                }

                String sql = "INSERT INTO measurement(station_id, local_date, local_time, temperatuur, dauwpunt, luchtdruk_station, luchtdruk_zee, zichtbaarheid, neerslag, sneeuwdiepte, bewolking, windrichting, windsnelheid, gebeurtenissen) VALUES(" +
                        "'" + measurements.get("station_id") + "', "+
                        "'" + measurements.get("local_date") + "', "+
                        "'" + measurements.get("local_time") + "', "+
                        "'" + measurements.get("temperatuur") + "', "+
                        "'" + measurements.get("dauwpunt") + "', "+
                        "'" + measurements.get("luchtdruk_station") + "', "+
                        "'" + measurements.get("luchtdruk_zee") + "', "+
                        "'" + measurements.get("zichtbaarheid") + "', "+
                        "'" + measurements.get("neerslag") + "', "+
                        "'" + measurements.get("sneeuwdiepte") + "', "+
                        "'" + measurements.get("bewolking") + "', "+
                        "'" + measurements.get("windrichting") + "', "+
                        "'" + measurements.get("windsnelheid") + "', "+
                        "'" + measurements.get("gebeurtenissen") + "')";


                //System.out.println(sql);
                DatabaseConnect database = new DatabaseConnect();
                database.insertSQL(sql);

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
