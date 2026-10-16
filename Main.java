import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("       ¡BIENVENIDO A Q'VACANERIA!        ");
        System.out.println("==========================================");

        System.out.print("Ingrese el nombre del titular: ");
        String titular = scanner.nextLine();

        double saldoInicial = leerMontoSeguro(scanner, "Ingrese el saldo inicial: ");

        Billetera billetera = new Billetera(titular, saldoInicial);

        int opcion = 0;
        do {
            System.out.println("\n------------------------------------------");
            System.out.println("           MENÚ PRINCIPAL - Q'VACANERIA   ");
            System.out.println("------------------------------------------");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Registrar ingreso");
            System.out.println("3. Registrar gasto");
            System.out.println("4. Ver historial de movimientos");
            System.out.println("5. Ver reporte de gastos por categoría");
            System.out.println("6. Salir");
            
            opcion = leerEnteroSeguro(scanner, "Seleccione una opción (1-6): ");

            switch (opcion) {
                case 1:
                    System.out.println("\n👤 Titular: " + billetera.getTitular());
                    System.out.println("💵 Saldo disponible: $" + billetera.getSaldo());
                    break;

                case 2:
                    double ingreso = leerMontoSeguro(scanner, "Ingrese el monto a depositar: ");
                    System.out.print("Ingrese la categoría del ingreso (ej: Nomina, Venta): ");
                    String catIngreso = scanner.next();
                    billetera.registrarIngreso(ingreso, catIngreso);
                    break;

                case 3:
                    double gasto = leerMontoSeguro(scanner, "Ingrese el monto del gasto: ");
                    System.out.print("Ingrese la categoría del gasto (ej: Alimentacion, Transporte): ");
                    String catGasto = scanner.next();
                    billetera.registrarGasto(gasto, catGasto);
                    break;

                case 4:
                    System.out.println("\n--- HISTORIAL DE MOVIMIENTOS ---");
                    if (billetera.getHistorial().isEmpty()) {
                        System.out.println("ℹ️ No hay movimientos registrados aún.");
                    } else {
                        for (Movimiento m : billetera.getHistorial()) {
                            System.out.println(m);
                        }
                    } 
                    billetera.mostrarReporteCategorias();
                    break;

                case 5:
                    System.out.println("\n--- CATEGORÍAS DE GASTO REGISTRADAS ---");
                    Set<String> categoriasUnicas = billetera.getCategoriasDeGastos();
                    if (categoriasUnicas.isEmpty()) {
                        System.out.println("ℹ️ No hay categorías de gastos registradas aún.");
                    } else {
                        for (String cat : categoriasUnicas) {
                            System.out.println("• " + cat);
                        }
                    }
                    break;

                case 6:
                    System.out.println("\n¡Gracias por utilizar Q'vacaneria! Que tengas un excelente día.");
                    break;

                default:
                    System.out.println("⚠️ Opción no válida. Por favor intente de nuevo.");
                    break;
            }

        } while (opcion != 6);

        scanner.close();
    }

    private static double leerMontoSeguro(Scanner scanner, String mensaje) {
        double monto = -1;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            try {
                monto = scanner.nextDouble();
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("⚠️ Entrada no válida. Por favor, ingrese solo números.");
                scanner.nextLine(); 
            }
        }
        return monto;
    }

    private static int leerEnteroSeguro(Scanner scanner, String mensaje) {
        int entero = -1;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            try {
                entero = scanner.nextInt();
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("⚠️ Entrada no válida. Por favor, ingrese un número entero.");
                scanner.nextLine(); // Limpiar el buffer
            }
        }
        return entero;
    }
}