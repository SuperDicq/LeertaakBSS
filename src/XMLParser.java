import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by Thijs on 24/09/2015.
 */
public class XMLParser {
    private String STN = null;
    private String DATE = null;
    private String TIME = null;
    private String DEWP = null;
    private String STP = null;
    private String SLP = null;
    private String VISIB = null;
    private String WDSP = null;
    private String TEMP = null;
    private String PRCP = null;
    private String SNDP = null;
    private String FRSHTT = null;
    private String CLDC = null;
    private String WNDDIR = null;

    private ArrayList<String> measurementsArray;

    public ArrayList<String> readXML(String xml) {
        measurementsArray = new ArrayList<String>();


        Document dom;

        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            // parse using the builder to get the DOM mapping of the
            // XML file
            dom = db.parse(new InputSource(new StringReader(xml)));
            Element doc = dom.getDocumentElement();

//          Set all details in Array

            STN = getTextValue(STN, doc, "STN");
            measurementsArray.add(STN);

            DATE = getTextValue(DATE, doc, "DATE");
            measurementsArray.add(DATE);

            TIME = getTextValue(TIME, doc, "TIME");
            measurementsArray.add(TIME);

            TEMP = getTextValue(TEMP, doc, "TEMP");
            measurementsArray.add(TEMP);

            DEWP = getTextValue(DEWP, doc, "DEWP");
            measurementsArray.add(DEWP);

            STP = getTextValue(STP, doc, "STP");
            measurementsArray.add(STP);

            SLP = getTextValue(SLP, doc, "SLP");
            measurementsArray.add(SLP);

            VISIB = getTextValue(VISIB, doc, "VISIB");
            measurementsArray.add(VISIB);

            WDSP = getTextValue(WDSP, doc, "WDSP");
            measurementsArray.add(WDSP);

            PRCP = getTextValue(PRCP, doc, "PRCP");
            measurementsArray.add(PRCP);

            SNDP = getTextValue(SNDP, doc, "SNDP");
            measurementsArray.add(SNDP);

            FRSHTT = getTextValue(FRSHTT, doc, "FRSHTT");
            measurementsArray.add(FRSHTT);

            CLDC = getTextValue(CLDC, doc, "CLDC");
            measurementsArray.add(CLDC);

            WNDDIR = getTextValue(WNDDIR, doc, "WNDDIR");
            measurementsArray.add(WNDDIR);

        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

        return measurementsArray;
    }

    private String getTextValue(String def, Element doc, String tag) {
        String value = def;
        NodeList nl;
        nl = doc.getElementsByTagName(tag);
        if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
            value = nl.item(0).getFirstChild().getNodeValue();
        }
        return value;
    }
}
