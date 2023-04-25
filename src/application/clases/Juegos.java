package application.clases;

public class Juegos {

    int sEstomID;
    String sJuego;
    String sDescripcion;
    String sBanner;
    String sLogo;
    double sAlmacenamiento;

    public Juegos() {
        this.sEstomID = 0;
        this.sJuego = "Juego de estom";
        this.sDescripcion = "Esto es un juego de estom";
        this.sBanner = "";
        this.sLogo = "";
        this.sAlmacenamiento = 0;
    }

    public Juegos(String sJuego, double sAlmacenamiento, String sBanner, String sLogo) {
        this.sJuego = sJuego;
        this.sAlmacenamiento = sAlmacenamiento;
        this.sBanner = sBanner;
        this.sLogo = sLogo;
    }

    public double getsAlmacenamiento() {
        return sAlmacenamiento;
    }

    public void setsAlmacenamiento(double sAlmacenamiento) {
        this.sAlmacenamiento = sAlmacenamiento;
    }

    public Juegos(int sEstomID, String sJuego, String sDescripcion, String sBanner, String sLogo) {
        this.sEstomID = sEstomID;
        this.sJuego = sJuego;
        this.sDescripcion = sDescripcion;
        this.sBanner = sBanner;
        this.sLogo = sLogo;
    }

    public int getsEstomID() {
        return sEstomID;
    }

    public void setsEstomID(int sEstomID) {
        this.sEstomID = sEstomID;
    }

    public String getsLogo() {
        return sLogo;
    }

    public void setsLogo(String sLogo) {
        this.sLogo = sLogo;
    }

    public String getsBanner() {
        return sBanner;
    }

    public void setsBanner(String sBanner) {
        this.sBanner = sBanner;
    }

    public Juegos(String sJuego) {
        this.sJuego = sJuego;
    }

    public String getsJuego() {
        return sJuego;
    }

    public void setsJuego(String sJuego) {
        this.sJuego = sJuego;
    }

    public String getsDescripcion() {
        return sDescripcion;
    }

    public void setsDescripcion(String sDescripcion) {
        this.sDescripcion = sDescripcion;
    }

    @Override
    public String toString() {
        return "Juegos [sJuego=" + sJuego + ", sDescripcion=" + sDescripcion + "]";
    }

}
