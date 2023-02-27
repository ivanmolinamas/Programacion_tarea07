
package Tarea07;

/**
 *
 * @author ivanm
 */
public abstract class CuentaBancaria {
    // creo una objeto que contendra el titular de la clase Persona
    private Persona Titular;
    private double saldo;
    private String IBAN;

    public CuentaBancaria(Persona Titular, double saldo, String IBAN) {
        this.Titular = Titular;
        this.saldo = saldo;
        this.IBAN = IBAN;
    }

    public Persona getTitular() {
        return Titular;
    }

    public void setTitular(Persona Titular) {
        this.Titular = Titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }
   
}
