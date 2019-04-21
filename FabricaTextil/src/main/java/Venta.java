import java.util.ArrayList;

public class Venta {

    private ArrayList<Polera> polera;

    public Venta(){
        polera=new ArrayList<Polera>();
    }

    public void agregarPolera(Polera p){
        polera.add(p);
    }

    public void mostrarPoleras(){
        System.out.println("Poleras:");
        for (int i=0;i<polera.size();i++){
            Polera aux=polera.get(i);
            System.out.println("Identificador: "+aux.getIdentificador()+", Talla: "+aux.getTalla()+", Material: "+aux.getManufactura()+", Estampado: "+aux.isEstampado());
        }
    }

}
