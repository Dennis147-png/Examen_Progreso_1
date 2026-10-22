import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Ventana extends JFrame {
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JComboBox cmbTipo;
    private JTextField tsxtCosto;
    private JComboBox cmbPrioridad;
    private JButton agregarButton;
    private JTextArea textArea1;
    private JButton ajustarPresupuestoButton;
    private JButton totalesButton;
    private JPanel Ventana;

    private ColaProyectos colaProyectos = new ColaProyectos();

    public Ventana() {
        setTitle("Gestion de proyectos con prioridad");
        setContentPane(Ventana);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);

        textArea1.setEditable(false);
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProyecto();
            }
        });

        ajustarPresupuestoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProyectos(true);
            }
        });

        totalesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularTotales();
            }
        });

        mostrarProyectos(false);
    }

    private void agregarProyecto() {
        String codigoTxt = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String costoTxt = tsxtCosto.getText().trim();

        String error = colaProyectos.validar(codigoTxt, nombre, costoTxt);
        if (error != null) {
            JOptionPane.showMessageDialog(this, error);
            return;
        }

        int codigo = Integer.parseInt(codigoTxt);
        float costo = Float.parseFloat(costoTxt);
        String tipo = (String) cmbTipo.getSelectedItem();
        int prioridad = Integer.parseInt((String) cmbPrioridad.getSelectedItem());

        Proyecto nuevo = new Proyecto(codigo, nombre, tipo, costo, prioridad);
        boolean agregado = colaProyectos.agregar(nuevo);

        if (agregado == false) {
            JOptionPane.showMessageDialog(this, "El codigo ya existe, no se pudo agregar");
            return;
        }

        txtCodigo.setText("");
        txtNombre.setText("");
        tsxtCosto.setText("");
        cmbTipo.setSelectedIndex(0);
        cmbPrioridad.setSelectedIndex(0);

        mostrarProyectos(false);
        JOptionPane.showMessageDialog(this, "Proyecto agregado correctamente");
    }

    private void mostrarProyectos(boolean conAjuste) {
        List<Proyecto> lista = colaProyectos.obtenerListaOrdenada();

        String texto = "";
        for (Proyecto p : lista) {
            texto = texto + "Codigo: " + p.getCodigo()
                    + " | Nombre: " + p.getNombre()
                    + " | Tipo: " + p.getTipo()
                    + " | Costo: " + String.format("%.2f", p.getCostoEstimado())
                    + " | Prioridad: " + p.getPrioridad();

            if (conAjuste == true) {
                float ajustado = colaProyectos.calcularCostoAjustado(p);
                texto = texto + " | Costo ajustado: " + String.format("%.2f", ajustado);
            }

            texto = texto + "\n";
        }

        textArea1.setText(texto);
    }

    private void calcularTotales() {
        String tipo = (String) cmbTipo.getSelectedItem();

        float totalEstimado = colaProyectos.totalEstimadoPorTipo(tipo);
        float totalAjustado = colaProyectos.totalAjustadoPorTipo(tipo);

        JOptionPane.showMessageDialog(this,
                "Tipo: " + tipo + "\n"
                        + "Costo estimado total: " + String.format("%.2f", totalEstimado) + "\n"
                        + "Costo ajustado total: " + String.format("%.2f", totalAjustado));
    }

    public static void main(String[] args) {
        Ventana ventana = new Ventana();
        ventana.setVisible(true);
    }
}
