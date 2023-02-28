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
    static Scanner sc = new Scanner(System.in);

    public static int mostrarMenu() {
        int opcion;
        System.out.println("");
        System.out.println("Menu Principal");
        System.out.println("1 - Abrir una nueva cuenta");
        System.out.println("2 - Ver un listado de las cuents disponibles (código de cuenta, titular y saldo actual).");
        System.out.println("3 - Obtener los datos de una cuenta concreta.");
        System.out.println("4 - Realizar ingreso en una cuenta.");
        System.out.println("5 - Retirar efectivo de una cuenta.");
        System.out.println("6 - Consultar el saldo actual de una cuenta.");
        System.out.println("7 - Salir de la apliacion.");
        opcion = sc.nextInt();
        sc.nextLine();
        return opcion;
    }

    public static void main(String[] args) {
        int menu;
        String nombreTitular, apellidosTitular, DNITitular, IBAN, listaEntidades, listaEntidadesEmp,buscaIBAN;
        double saldo, tipoInteres, mantenimientoPer, maxDescubierto, interesDescubierto, comisionDescubierto,ingreso,retirar;
        int opcionCuenta;
        String [] listaCuentas;
        boolean menuCuenta = false;
        boolean checkIBAN = false;

        Banco banco = new Banco();
        Persona titular;
        CuentaBancaria cuenta = null;

        do {
            menu = mostrarMenu();
            System.out.println("opcion: " + menu);
            switch (menu) {
                case 1:
                    //Abrir una nueva cuenta

                    // datos para crear la persona
                    System.out.println("Introduce el nombre del titular");
                    nombreTitular = sc.next();
                    System.out.println("Introduce los apellidos del titular");
                    apellidosTitular = sc.next();
                    System.out.println("Introduce el DNI del titular");
                    DNITitular = sc.next();

                    titular = new Persona(nombreTitular, apellidosTitular, DNITitular);
                    //se pide el IBAN
                    do {
                        System.out.println("Introduzca el IBAN");
                        IBAN = sc.next();
                        if (!IBAN.matches("^ES[0-9]{20}$")) {
                            System.out.println("El IBAN no tiene el formato correcto");
                            checkIBAN=false;
                        } else {
                            System.out.println("IBAN correcto");
                            checkIBAN=true;
                        }
                    } while (!checkIBAN);

                    System.out.println("Introduce saldo inicial");
                    saldo = sc.nextDouble();

                    do {
                        System.out.println("Elige el tipo de cuenta");
                        System.out.println("1 - Cuenta de Ahorro");
                        System.out.println("2 - Cuenta Corriente Personal");
                        System.out.println("3 - Cuenta Corriente Empresa");
                        opcionCuenta = sc.nextInt();

                        switch (opcionCuenta) {
                            case 1:
                                // Cuenta de Ahorro
                                System.out.println("Introduzca el tipo de interes");
                                tipoInteres = sc.nextDouble();

                                //Se crae la cuenta Ahorro
                                cuenta = new CuentaAhorro(tipoInteres, titular, saldo, IBAN);

                                menuCuenta = true;
                                break;

                            case 2:
                                // Cuenta corriente personal
                                System.out.println("Introduzca lista de entidades autorizadas para cobrar recibos domciliados");
                                listaEntidades = sc.next();

                                System.out.println("Introduzca comision de mantenimiento anual");
                                mantenimientoPer = sc.nextDouble();

                                //Se crea la cuenta corriente personal
                                cuenta = new CuentaCorrientePersonal(mantenimientoPer, listaEntidades, titular, saldo, IBAN);

                                menuCuenta = true;
                                break;

                            case 3:
                                // Cuenta corriente Empresa
                                System.out.println("Introduzca lista de entidades autorizadas para cobrar recibos domciliados");
                                listaEntidadesEmp = sc.next();

                                System.out.println("Introduzca comision por descubierto");
                                comisionDescubierto = sc.nextDouble();

                                System.out.println("Introduzca maximo descubierto permitido");
                                maxDescubierto = sc.nextDouble();

                                System.out.println("Introduzca interes por descubierto");
                                interesDescubierto = sc.nextDouble();

                                //Se crea la cuenta Empesa
                                cuenta = new CuentaCorrienteEmpresa(maxDescubierto, interesDescubierto, comisionDescubierto, listaEntidadesEmp, titular, saldo, IBAN);

                                menuCuenta = true;
                                break;
                            default:
                                System.out.println("Opcion incorrecta");
                                menuCuenta = false;
                        }
                    } while (!menuCuenta);

                    if (banco.abrirCuenta(cuenta)) {
                        System.out.println("Se ha abierto la cuenta correctamente");
                    } else {
                        System.out.println("Ha habido un error y no se ha podido abrir la cuenta");
                    }

                    break;
                case 2:
                    //Ver un listado de las cuents disponibles (código de cuenta, titular y saldo actual)
                    listaCuentas=banco.listadoCuentas();
                    for (int i = 0; i < listaCuentas.length; i++) {
                        System.out.println(listaCuentas[i]);
                    }
                    
                    break;
                case 3:
                    //Obtener los datos de una cuenta concreta. 
                    System.out.println("Introduce el IBAN de la cuenta para obtener datos");
                    buscaIBAN=sc.next();

                    if (banco.informacionCuenta(buscaIBAN)!=null) {
                        System.out.println(banco.informacionCuenta(buscaIBAN));
                    }else{
                        System.out.println("Cuenta no encontrada");
                    }
                    break;
                case 4:
                    // Realizar ingreso en una cuenta
                    System.out.println("Introduzca el IBAN");
                    buscaIBAN=sc.next();
                    System.out.println("Introduzca el importe a ingresar");
                    ingreso=sc.nextDouble();
                    if (banco.ingresoCuenta(buscaIBAN, ingreso)==true) {
                        System.out.println("Ingreso realizado con exito");
                    }else{
                        System.out.println("No se pudo realizar el ingreso");
                    }
                    
                    break;
                case 5:
                    //Retirar efectivo de una cuenta
                    System.out.println("Introduzca el IBAN");
                    buscaIBAN=sc.next();
                    System.out.println("Introduzca el importe a retirar");
                    retirar=sc.nextDouble();
                    if (banco.retiradaCuenta(buscaIBAN, retirar)==true) {
                        System.out.println("Retirada realizado con exito");
                    }else{
                        System.out.println("No se pudo realizar la retirada");
                    }
                    
                    break;
                case 6:
                    //Consultar el saldo actual de una cuenta
                    System.out.println("Introduzca el IBAN");
                    buscaIBAN=sc.next();
                    
                    if (banco.obtenerSaldo(buscaIBAN)!= -1) {
                        System.out.println(banco.obtenerSaldo(buscaIBAN));
                    }else{
                        System.out.println("No existe la cuenta");
                    }
                    
                    break;
                case 7:
                    System.out.println("Salir de la apliacion");
                    //Salir de la apliacion
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }

        } while (menu != 7);
        System.out.println("Hasta luego!");
    }

}
