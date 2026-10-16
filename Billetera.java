import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Billetera {
    private String titular;
    private double saldo;
    private List<Movimiento> historial;
    private Map<String, Double> gastosPorCategoria;

    public Billetera(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = (saldoInicial >= 0) ? saldoInicial : 0.0;
        this.historial = new ArrayList<>();
        this.gastosPorCategoria = new HashMap<>();
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }

    public boolean registrarIngreso(double monto, String categoria) {
        if (monto <= 0) {
            System.out.println("⚠️ El monto a ingresar debe ser mayor a cero.");
            return false;
        }

        saldo += monto;
        historial.add(new Movimiento("INGRESO", monto, categoria));
        System.out.println("✅ Ingreso registrado exitosamente. Nuevo saldo: $" + saldo);
        return true;
    }

    public boolean registrarGasto(double monto, String categoria) {
        if (monto <= 0) {
            System.out.println("⚠️ El monto del gasto debe ser mayor a cero.");
            return false;
        }

        if (monto > saldo) {
            System.out.println("❌ Saldo insuficiente. No se puede realizar el gasto.");
            return false;
        }

        saldo -= monto;
        historial.add(new Movimiento("GASTO", monto, categoria));

        double acumuladoActual = gastosPorCategoria.getOrDefault(categoria, 0.0);
        gastosPorCategoria.put(categoria, acumuladoActual + monto);

        System.out.println("✅ Gasto registrado exitosamente. Nuevo saldo: $" + saldo);
        return true;
    }

    public List<Movimiento> getHistorial() {
        return Collections.unmodifiableList(historial);
    }

    public Set<String> getCategoriasDeGastos() {
        Set<String> categoriasUnicas = new HashSet<>();
        for (Movimiento m : historial) {
            if (m.getTipo().equals("GASTO")) {
                categoriasUnicas.add(m.getCategoria());
            }
        }
        return categoriasUnicas;
    }

    public void mostrarReporteCategorias() {
        if (gastosPorCategoria.isEmpty()) {
            System.out.println("ℹ️ No hay gastos registrados por categoría.");
            return;
        }

        System.out.println("\n--- REPORTE DE GASTOS POR CATEGORÍA ---");
        for (Map.Entry<String, Double> entry : gastosPorCategoria.entrySet()) {
            System.out.println("• " + entry.getKey() + ": $" + entry.getValue());
        }
    }
}