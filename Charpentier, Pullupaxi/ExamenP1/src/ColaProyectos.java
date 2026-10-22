import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ColaProyectos {

    private PriorityQueue<Proyecto> cola;

    public ColaProyectos() {
        cola = new PriorityQueue<>(Comparator.comparingInt(Proyecto::getPrioridad).reversed());

        Proyecto p1 = new Proyecto(101, "Sistema de matriculas", "Tecnológico", 12000.00f, 9);
        Proyecto p2 = new Proyecto(102, "Biblioteca comunitaria", "Social", 5000.00f, 5);
        Proyecto p3 = new Proyecto(103, "Reforestacion del parque", "Ambiental", 8000.00f, 7);
        Proyecto p4 = new Proyecto(104, "Aplicacion movil", "Tecnológico", 6500.00f, 3);
        Proyecto p5 = new Proyecto(105, "Comedor para ninos", "Social", 3000.00f, 10);

        cola.add(p1);
        cola.add(p2);
        cola.add(p3);
        cola.add(p4);
        cola.add(p5);
    }

    public boolean codigoExiste(int codigo) {
        for (Proyecto p : cola) {
            if (p.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    public String validar(String codigoTxt, String nombre, String costoTxt) {
        if (codigoTxt.isEmpty() || nombre.isEmpty() || costoTxt.isEmpty()) {
            return "Debe llenar todos los campos";
        }

        int codigo;
        try {
            codigo = Integer.parseInt(codigoTxt);
        } catch (NumberFormatException e) {
            return "El codigo debe ser un numero entero";
        }

        if (codigo <= 0) {
            return "El codigo debe ser mayor que cero";
        }

        if (codigoExiste(codigo)) {
            return "El codigo ya existe, ingrese otro";
        }

        float costo;
        try {
            costo = Float.parseFloat(costoTxt);
        } catch (NumberFormatException e) {
            return "El costo debe ser un numero valido";
        }

        if (Float.isNaN(costo) || Float.isInfinite(costo)) {
            return "El costo debe ser un numero valido";
        }

        if (costo <= 0) {
            return "El costo debe ser mayor que cero";
        }

        return null;
    }

    public boolean agregar(Proyecto p) {
        if (codigoExiste(p.getCodigo())) {
            return false;
        }
        cola.add(p);
        return true;
    }

    public List<Proyecto> obtenerListaOrdenada() {
        List<Proyecto> lista = new ArrayList<>(cola);
        Collections.sort(lista, Comparator.comparingInt(Proyecto::getPrioridad).reversed());
        return lista;
    }

    public float calcularCostoAjustado(Proyecto p) {
        float costo = p.getCostoEstimado();
        float ajustado = costo;

        if (p.getTipo().equals("Tecnológico")) {
            ajustado = costo + costo * 0.15f;
        } else if (p.getTipo().equals("Social")) {
            ajustado = costo - costo * 0.10f;
        } else if (p.getTipo().equals("Ambiental")) {
            ajustado = costo - costo * 0.05f;
        }

        return ajustado;
    }

    public float totalEstimadoPorTipo(String tipo) {
        float total = 0;
        for (Proyecto p : cola) {
            if (p.getTipo().equals(tipo)) {
                total = total + p.getCostoEstimado();
            }
        }
        return total;
    }

    public float totalAjustadoPorTipo(String tipo) {
        float total = 0;
        for (Proyecto p : cola) {
            if (p.getTipo().equals(tipo)) {
                total = total + calcularCostoAjustado(p);
            }
        }
        return total;
    }
}
