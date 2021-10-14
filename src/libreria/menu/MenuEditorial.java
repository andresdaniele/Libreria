package libreria.menu;

import java.util.List;
import java.util.Scanner;
import libreria.entities.Autor;
import libreria.entities.Editorial;
import libreria.services.AutorService;
import libreria.services.EditorialService;

public class MenuEditorial {
    
    private Scanner leer;
    private EditorialService es;

    public MenuEditorial() {
        this.leer = new Scanner(System.in).useDelimiter("\n");
        this.es = new EditorialService();
    }

    public void menuPrincipal() throws Exception {
        int respuesta;

        try {
            do {
                System.out.println("Ingrese una opcion");
                System.out.println(" 1-INGRESAR UNA EDITORIAL");
                System.out.println(" 2-BUSCAR EDITORIAL POR NOMBRE");
                System.out.println(" 3-LISTAR LAS EDITORIALES");
                System.out.println(" 4- SALIR");
                respuesta = leer.nextInt();

                switch (respuesta) {
                    case 1:
                        es.crearEditorial(crearEditorial());
                        break;
                    case 2: 
                        System.out.println("INGRESE EL NOMBRE");
                        String nombre = leer.next().toUpperCase();
                        System.out.println(es.buscarPorNombre(nombre).toString());
                        break;
                    case 3:
                        List<Editorial> editoriales = es.listarEditorial();
                        for (Editorial aux : editoriales) {
                            System.out.println(aux.toString());
                        }
                        break;
                    default:
                        System.out.println("OPCION INVALIDA. INTENTE NUEVAMENTE");
                        
                }
            } while (respuesta != 4);
        } catch (Exception e) {
            throw e;
        }
    }

    public Editorial crearEditorial() throws Exception {
        try {
            System.out.println("Ingrese el nombre de la editorial");
            String nombre = leer.next().toUpperCase();
            Editorial editorial = new Editorial(nombre, true);
            return editorial;

        } catch (Exception e) {
            throw e;
        }

    }
}
