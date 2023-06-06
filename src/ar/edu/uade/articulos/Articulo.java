package ar.edu.uade.articulos;

import java.util.Date;

public class Articulo {
	
    private int codigoArticulo;
    private TipoArticulo tipoArticulo;
    private Date fechaCompra;
    private int usos;
    private boolean flagDesgastado;
	
    public Articulo(TipoArticulo tipoArticulo) {
        // Code for initializing an article with tipoArticulo
    }

    public Articulo(TipoArticulo tipoArticulo, Date fechaCompra) {
        // Code for initializing an article with tipoArticulo and fechaCompra
    }

    public FormaAmortizacion getFormaAmortizacion() {
        // Code for getting amortization method
        return null;
    }

    private int getUsosRestantes() {
        // Code for getting remaining uses
        return 0;
    }
	
    private int getDiasRestantes() {
        // Code for getting remaining days
        return 0;
    }

    public int getDesgaste() {
        // Code for getting wear and tear
        return 0;
    }

    public void setDesgaste() {
        // Code for setting wear and tear
    }

    public boolean isDesgastado() {
        // Code for checking if the article is worn-out
        return false;
    }
}
