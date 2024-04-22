package conversormoedas.modulos;

import java.util.Currency;

public class Moedas {
    private double dolar;
    private double real;
    private double pesoArgentino;
    private double pesoColombiano;

    public Moedas(MoedasExchange moedasExchange){
        this.dolar = moedasExchange.USD();
        this.real = moedasExchange.BRL();
        this.pesoArgentino = moedasExchange.ARS();
        this.pesoColombiano = moedasExchange.COP();
    }

    public double getDolar() {
        return dolar;
    }

    public double getReal() {
        return real;
    }

    public double getPesoArgentino() {
        return pesoArgentino;
    }

    public double getPesoColombiano() {
        return pesoColombiano;
    }

}
