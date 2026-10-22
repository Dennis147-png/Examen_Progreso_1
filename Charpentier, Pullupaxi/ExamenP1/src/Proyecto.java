public class Proyecto {

    private int codigo;
    private String nombre;
    private String tipo;
    private float costoEstimado;
    private int prioridad;

    public Proyecto() {
        this.codigo = 0;
        this.nombre = "Sin nombre";
        this.tipo = "Tecnológico";
        this.costoEstimado = 1.0f;
        this.prioridad = 1;
    }

    public Proyecto(int codigo, String nombre, String tipo, float costoEstimado, int prioridad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.costoEstimado = costoEstimado;
        this.prioridad = prioridad;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getCostoEstimado() {
        return costoEstimado;
    }

    public void setCostoEstimado(float costoEstimado) {
        this.costoEstimado = costoEstimado;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", costoEstimado=" + costoEstimado +
                ", prioridad=" + prioridad +
                '}';
    }

}
