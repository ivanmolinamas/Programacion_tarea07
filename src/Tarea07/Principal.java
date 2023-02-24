
package Tarea07;

import java.util.Scanner;

/**
 *
 * @author ivanm
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    
    static Scanner teclado = new Scanner(System.in);
    
    public static int mostrarMenu(){
        int opcion;
        System.out.println("Menu Principal");
        System.out.println("1 - Abrir una nueva cuenta");
        System.out.println("2 - Ver un listado de las cuents disponibles (código de cuenta, titular y saldo actual).");
        System.out.println("3 - Obtener los datos de una cuenta concreta. Realizar ingreso en una cuenta.");
        System.out.println("4 - Retirar efectivo de una cuenta.");
        System.out.println("5 - Consultar el saldo actual de una cuenta.");
        System.out.println("6 - Salir de la apliacion.");
        opcion = teclado.nextInt();
        teclado.nextLine();
        return opcion;
    }

    public static void main(String[] args) {
        int menu;
        
        do{
            menu=mostrarMenu();
            System.out.println("opcion: " +menu);
            switch(menu){
                case 1:
                    //Abrir una nueva cuenta
                    break;
                case 2:
                    //Ver un listado de las cuents disponibles (código de cuenta, titular y saldo actual)
                    break;
                case 3:
                    //Obtener los datos de una cuenta concreta. Realizar ingreso en una cuenta
                    break;    
                case 4:
                    //Retirar efectivo de una cuenta
                    break;
                case 5:
                    //Consultar el saldo actual de una cuenta
                    break;
                case 6:
                    System.out.println("Opcion salir de la apliacion");
                    //Salir de la apliacion
                    break; 
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
    
        }while(menu != 6);
        System.out.println("Salida. Hasta luego!");
    }
    
}
