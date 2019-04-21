import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class PedidoTest {
    Venta venta;
    Pedido instance;
    @org.junit.Before
    public void setUp() throws Exception {
    venta = new Venta();
    instance = new Pedido();
    }

    @Test
    public void leerArchivoTest() {
        System.out.println("leerArch");
        String ruta = "listado.csv";
        String expResult = "polyester,S,false\n"+"algodon,M,true\n"+
                "spandex,XL,true\n"+"algodon,L,false\n"+"polyester,M,true\n"+"algodon,XL,false\n";
        String result = instance.leerArchivo(ruta);
        assertEquals(expResult, result);
    }

    @Test
    public void agregarTextoTest(){
        Polera p = new Polera("algodon","XL",false, Pedido.uuid());
        String expResult=p.getManufactura()+","+p.getTalla()+","+p.isEstampado()+"\n";
        assertEquals(expResult,instance.agregarTexto(p));
    }
}
