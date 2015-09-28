import java.util.HashMap;

/**
 * Created by Thijs on 26/09/2015.
 */
public class Measurement {
    private String stationNr = null;
    private String date = null;
    private String time = null;
    private String dewPoint = null;
    private String airPressureStation = null;
    private String airPressureSea = null;
    private String visibility = null;
    private String windSpeed = null;
    private String temperature = null;
    private String precipitation = null;
    private String snowfall = null;
    private String event = null;
    private String clouds = null;
    private String windDirection = null;

    public Measurement(HashMap<String, String> measurements) {
        this.stationNr = measurements.get("STN");
        this.date = measurements.get("DATE");;
        this.time = measurements.get("TIME");;
        this.dewPoint = measurements.get("DEWP");;
        this.airPressureStation = measurements.get("STP");;
        this.airPressureSea = measurements.get("SLP");;
        this.visibility = measurements.get("VISIB");;
        this.windSpeed = measurements.get("WDSP");;
        this.temperature = measurements.get("TEMP");;
        this.precipitation = measurements.get("PRCP");;
        this.snowfall = measurements.get("SNDP");;
        this.event = measurements.get("FRSHTT");;
        this.clouds = measurements.get("CLDC");;
        this.windDirection = measurements.get("WNDDIR");;

        System.out.println(measurements);
    }
}
