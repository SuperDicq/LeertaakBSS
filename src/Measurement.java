import java.util.Map;

/**
 * Created by Thijs on 11-10-2015.
 */
public class Measurement {
    private Map<String, String> measurementsMap;

    private String station_id = null;
    private String local_date = null;
    private String local_time = null;
    private String temperatuur = null;
    private String dauwpunt = null;
    private String luchtdruk_station = null;
    private String luchtdruk_zee = null;
    private String zichtbaarheid = null;
    private String neerslag = null;
    private String sneeuwdiepte = null;
    private String bewolking = null;
    private String windrichting = null;
    private String windsnelheid = null;
    private String gebeurtenissen = null;

    public Measurement(Map<String, String> measurementsMap) {
        this.measurementsMap = measurementsMap;

        this.station_id = measurementsMap.get("station_id");
        this.local_date = measurementsMap.get("local_date");
        this.local_time = measurementsMap.get("local_time");
        this.temperatuur = measurementsMap.get("temperatuur");
        this.dauwpunt =  measurementsMap.get("dauwpunt");
        this.luchtdruk_station = measurementsMap.get("luchtdruk_station");
        this.luchtdruk_zee =  measurementsMap.get("luchtdruk_zee");
        this.zichtbaarheid = measurementsMap.get("zichtbaarheid");
        this.neerslag = measurementsMap.get("neerslag");
        this.sneeuwdiepte = measurementsMap.get("sneeuwdiepte");
        this.bewolking = measurementsMap.get("bewolking");
        this.windrichting = measurementsMap.get("windrichting");
        this.windsnelheid =  measurementsMap.get("windsnelheid");
        this.gebeurtenissen = measurementsMap.get("gebeurtenissen");
    }

    /**
     * Getters for all weather items
     */

    public Map<String, String> getMeasurementsMap() {
        return measurementsMap;
    }

    public String getStation_id() {
        return station_id;
    }

    public String getLocal_date() {
        return local_date;
    }

    public String getLocal_time() {
        return local_time;
    }

    public String getTemperatuur() {
        return temperatuur;
    }

    public String getDauwpunt() {
        return dauwpunt;
    }

    public String getLuchtdruk_station() {
        return luchtdruk_station;
    }

    public String getLuchtdruk_zee() {
        return luchtdruk_zee;
    }

    public String getZichtbaarheid() {
        return zichtbaarheid;
    }

    public String getNeerslag() {
        return neerslag;
    }

    public String getSneeuwdiepte() {
        return sneeuwdiepte;
    }

    public String getBewolking() {
        return bewolking;
    }

    public String getWindrichting() {
        return windrichting;
    }

    public String getWindsnelheid() {
        return windsnelheid;
    }

    public String getGebeurtenissen() {
        return gebeurtenissen;
    }

    public String getCSV(){
        return (this.station_id + ", " + this.local_date + ", " + this.local_time + ", " + this.temperatuur + ", " + this.dauwpunt + ", " + this.luchtdruk_station + ", " + this.luchtdruk_zee + ", " + this.zichtbaarheid + ", " + this.neerslag + ", " + this.sneeuwdiepte + ", " + this.bewolking + ", " + this.windrichting + ", " + this.windsnelheid + ", " + this.gebeurtenissen);
    }
}
