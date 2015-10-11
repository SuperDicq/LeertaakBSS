public class Csv {
   
   //variabelen
   private String STN;
   private String DATE;
   private String TIME;
   private String DEWP;
   private String STP;
   private String SLP;
   private String VISIB;
   private String WDSP;
   private String TEMP;
   private String PRCP;
   private String SNDP;
   private String FRSHTT;
   private String CLDC;
   private String WNDDIR; 
   

   //constructor
   public Csv(String DATE, String TIME, String DEWP, String STP, String SLP, String VISIB, String WDSP, 
   String TEMP, String PRCP, String SNDP, String FRSHTT, String CLDC, String WNDDIR){
   
   this.DATE = DATE;
   this.TIME = TIME;
   this.DEWP = DEWP;
   this.STP = STP;
   this.SLP = SLP;
   this.VISIB = VISIB;
   this.WDSP = WDSP;
   this.TEMP = TEMP;
   this.PRCP = PRCP;
   this.SNDP = SNDP;
   this.FRSHTT = FRSHTT;
   this.CLDC = CLDC;
   this.WNDDIR = WNDDIR; 
   
}
   
   //-- ALLE GETTERS EN SETTERS VOOR DE VARIABELEN --

    Csv() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

   public String getSTN() {
        return STN;
    }

    public void setSTN(String STN) {
        this.STN = STN;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getDEWP() {
        return DEWP;
    }

    public void setDEWP(String DEWP) {
        this.DEWP = DEWP;
    }

    public String getSTP() {
        return STP;
    }

    public void setSTP(String STP) {
        this.STP = STP;
    }

    public String getSLP() {
        return SLP;
    }

    public void setSLP(String SLP) {
        this.SLP = SLP;
    }

    public String getVISIB() {
        return VISIB;
    }

    public void setVISIB(String VISIB) {
        this.VISIB = VISIB;
    }

    public String getWDSP() {
        return WDSP;
    }

    public void setWDSP(String WDSP) {
        this.WDSP = WDSP;
    }

    public String getTEMP() {
        return TEMP;
    }

    public void setTEMP(String TEMP) {
        this.TEMP = TEMP;
    }

    public String getPRCP() {
        return PRCP;
    }

    public void setPRCP(String PRCP) {
        this.PRCP = PRCP;
    }

    public String getSNDP() {
        return SNDP;
    }

    public void setSNDP(String SNDP) {
        this.SNDP = SNDP;
    }

    public String getFRSHTT() {
        return FRSHTT;
    }

    public void setFRSHTT(String FRSHTT) {
        this.FRSHTT = FRSHTT;
    }

    public String getCLDC() {
        return CLDC;
    }

    public void setCLDC(String CLDC) {
        this.CLDC = CLDC;
    }

    public String getWNDDIR() {
        return WNDDIR;
    }

    public void setWNDDIR(String WNDDIR) {
        this.WNDDIR = WNDDIR;
    }
  
    
    //-- EINDE VAN GETTERS EN SETTERS --
    
    
    
}
