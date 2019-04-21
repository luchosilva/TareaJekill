public class Polera {

    private String talla;
    private String manufactura;
    private boolean estampado;
    private String identificador;

    public Polera(String manuf,String talla,boolean estam,String ident){
        this.manufactura=manuf;
        this.talla=talla;
        this.estampado=estam;
        this.identificador=ident;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getManufactura() {
        return manufactura;
    }

    public void setManufactura(String manufactura) {
        this.manufactura = manufactura;
    }

    public boolean isEstampado() {
        return estampado;
    }

    public void setEstampado(boolean estampado) {
        this.estampado = estampado;
    }

    public String getIdentificador() {
        return identificador;
    }

}
