package Tarea07;

/**
 *
 * @author ivanm
 */
public class Banco {

    private CuentaBancaria[] cuentas;
    private final int MAX_CUENTAS = 100;
    private int numeroCuentas;

    public Banco() {
        this.cuentas = new CuentaBancaria[this.MAX_CUENTAS];
        this.numeroCuentas = 0;
    }

    //metodo abrir cuenta
    public boolean abrirCuenta(CuentaBancaria cuenta) {
        // comprueba que hay espacio en el array
        if (this.numeroCuentas >= this.MAX_CUENTAS) {
            System.out.println("El sistema esta lleno");
            return false;
        }
        // comprueba que la cuenta no existe
        CuentaBancaria existe = this.buscarCuenta(cuenta.getIBAN());
        if (existe != null) {
            System.out.println("La cuenta bancaria ya existe");
            return false;
        }
        // si hay espacio y no existe, se añade la cuenta al array y se devuelve true
        this.cuentas[this.numeroCuentas] = cuenta;
        this.numeroCuentas++;
        return true;
    }

    // busca cuenta bancaria que se pase por argumento
    private CuentaBancaria buscarCuenta(String IBAN) {
        // con un bucle for se ba buscando hasta donde haya cuentas
        // con el contador numeroCuentas, si existe se devuelve el objeto
        for (int i = 0; i < numeroCuentas; i++) {
            if (this.cuentas[i].getIBAN().equals(IBAN)) {
                return this.cuentas[i];
            }
        }
        return null;
    }

    public String[] listadoCuentas() {
        String[] infoCuentas = new String[this.numeroCuentas];
        for (int i = 0; i < this.numeroCuentas; i++) {
            infoCuentas[i] = this.cuentas[i].devolverInfoString();
        }
        return infoCuentas;
    }

    public String informacionCuenta(String IBAN) {
        CuentaBancaria c = this.buscarCuenta(IBAN);
        if (c != null) {
            return c.devolverInfoString();
        }
        return null;
    }

    public boolean ingresoCuenta(String IBAN, double ingreso) {
        CuentaBancaria c = this.buscarCuenta(IBAN);
        if (c != null) {
            c.setSaldo(c.getSaldo() + ingreso);
            return true;
        }
        return false;
    }

    public boolean retiradaCuenta(String IBAN, double retirada) {
        CuentaBancaria c = this.buscarCuenta(IBAN);

        if (c != null) {
            if (c.getSaldo() - retirada > 0) {
                c.setSaldo(c.getSaldo() - retirada);
                return true;
            }
            return false;
        }
        return false;
    }

    public double obtenerSaldo(String IBAN) {
        CuentaBancaria c = this.buscarCuenta(IBAN);
        if(c!= null){
           return c.getSaldo(); 
        }
        return -1;
    }

}
