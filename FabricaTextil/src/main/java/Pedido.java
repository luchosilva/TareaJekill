import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.util.UUID.randomUUID;

public class Pedido {

    Venta ven;
    public Pedido(){ //instancia el objeto venta
        ven=new Venta();
    }

    public void menu(){ //menu
        boolean salir = false;
        while (!salir) {
            try {
                System.out.println("Seleccione una opción:");
                System.out.println("[1].-Pedir Polera");
                System.out.println("[2].-Mostrar Poleras");
                System.out.println("[3].-Agregar Poleras desde un archivo de texto");
                System.out.println("[4].-Agregar objeto al .csv");
                System.out.println("[5].-Salir");
                int opcion = leerEntero();
                if (opcion > 5 || opcion < 1) {
                    System.out.println("Opción no valida,intentelo nuevamente:");
                } else {
                    switch (opcion) {
                        case 1: {
                            pedirPolera();
                            break;
                        }
                        case 2: {
                            ven.mostrarPoleras();
                            break;
                        }
                        case 3: {
                            String ruta="listado.csv";
                            leerArchivo(ruta);
                            break;
                        }
                        case 4: {
                            Polera p = new Polera("algodon","XL",false, uuid());
                            agregarTexto(p);
                            break;
                        }
                        case 5: {
                            salir=true;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Valor no esperado,intentelo nuevamente:");
            }
        }
    }

    public void pedirPolera(){ //Pide los datos del objeto polera y luego los almacena en un arraylist
        boolean value=false;
        System.out.println("Para cada polera se generará un ID");
        String codigo=uuid();
        System.out.println("Ingrese la talla de la polera: ");
        String talla=talla();
        System.out.println("Ingrese tipo de material: ");
        String material=material();
        boolean estampado=false;
        do {
            System.out.println("¿Quiere estampado en su polera?");
            String respuesta=leerCadena();
            if (respuesta.equals("si")) {
                estampado = true;
                value=true;
            }
            if (respuesta.equals("no")) {
                estampado = false;
                value=true;
            }else{

            }
        }while(!value);
        Polera pol=new Polera(talla,material,estampado,codigo);
        ven.agregarPolera(pol);
    }

    public String talla(){ //Selecciona la talla de la polera
        String talla=null;
            try {
                System.out.println("Seleccione una opción:");
                System.out.println("[1].-S");
                System.out.println("[2].-M");
                System.out.println("[3].-L");
                System.out.println("[4].-XL");
                int opcion = leerEntero();
                if (opcion > 4 || opcion < 1) {
                    System.out.println("Opción no valida,intentelo nuevamente:");
                } else {
                    switch (opcion) {
                        case 1: {
                            talla="S";
                            break;
                        }
                        case 2: {
                            talla="M";
                            break;
                        }
                        case 3: {
                            talla="L";
                        }
                        case 4: {
                            talla="XL";
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Valor no esperado,intentelo nuevamente:");
            }
        return talla;
    }

    public String material(){ //selecciona el material de la polera
        String material=null;
        try {
            System.out.println("Seleccione una opción:");
            System.out.println("[1].-Algodón");
            System.out.println("[2].-Polyester");
            System.out.println("[3].-Spandex");
            int opcion = leerEntero();
            if (opcion > 3 || opcion < 1) {
                System.out.println("Opción no valida,intentelo nuevamente:");
            } else {
                switch (opcion) {
                    case 1: {
                        material="Algodón";
                        break;
                    }
                    case 2: {
                        material="Polyester";
                        break;
                    }
                    case 3: {
                        material="Spandex";
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Valor no esperado,intentelo nuevamente:");
        }
        return material;
    }

    public String leerArchivo(String ruta){ //Lee un archivo .csv y guarda sus datos en objetos de tipo polera
        String datos="";
        boolean estampado=false;
        File fichero = new File(ruta);
        Scanner s = null;
        int lineasLeidas=0;
        String linea="";
        System.out.println("... Leemos el contenido del fichero ...");
        try {
            s = new Scanner(fichero);
        } catch (FileNotFoundException e) {
            System.out.println("ocurrio un error");
        }
        try { // Obtengo los datos de las poleras del fichero
            while ((linea = s.nextLine()) != null) { // Obtengo una linea del fichero
                lineasLeidas++;
                if (lineasLeidas > 1) {
                    String[] cortarString = linea.split(",");
                    if (cortarString[2].equals("true")) {
                        estampado = true;
                    } else if (cortarString[2].equals("false")) {
                        estampado = false;
                    }
                    datos=datos+(cortarString[0]+","+cortarString[1]+","+estampado+"\n");
                    Polera pol = new Polera(cortarString[0], cortarString[1], estampado, uuid()); // Creo un objeto de la clase "Polera"
                    ven.agregarPolera(pol); // Añadimos el objeto "polera" al ArrayList
                }
            }
        } catch(NullPointerException e){
            System.out.println();
        } catch (NoSuchElementException e2) {
            System.out.println();
        }
        s.close();
        return datos; //datos sirve para probar el metodo
    }

    public String agregarTexto(Polera p){ //agrega el ultimo objeto polera pedido
        FileWriter archivo;
        String datos="";
        try{
            archivo = new FileWriter("listado.csv",true);
            BufferedWriter bufferedWriter = new BufferedWriter(archivo);
            ven.agregarPolera(p);
            bufferedWriter.write(p.getManufactura()+","+p.getTalla()+","+p.isEstampado()+"\n");
            datos=p.getManufactura()+","+p.getTalla()+","+p.isEstampado()+"\n";
            bufferedWriter.close();
            archivo.close();
        }catch (IOException e){
            System.out.println("Archivo no encontrado.");
        }
        return datos; //datos sirve para probar el metodo
    }

    public static final String uuid() { //Genera un ID unico para cada polera de tamaño 10
        String result = randomUUID().toString();
        result=result.replaceAll("-","");
        result=result.substring(0,10);
        return result;
    }

    public static int leerEntero(){ //Lee enteros
        Scanner leer = new Scanner(System.in);
        int num1 = leer.nextInt();
        return num1;
    }

    public static String leerCadena() { //Lee String
        Scanner leer = new Scanner(System.in);
        String cadena = leer.nextLine();
        return cadena;
    }
}
