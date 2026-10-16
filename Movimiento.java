public class Movimiento {
    private String tipo;        
    private double monto;
    private String categoria;   

    public Movimiento(String tipo, double monto, String categoria) {
        this.tipo = tipo;
        this.monto = monto;
        this.categoria = categoria;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return String.format("[%s] Categoría: %s | Monto: $%.2f", tipo, categoria, monto);
    }
}