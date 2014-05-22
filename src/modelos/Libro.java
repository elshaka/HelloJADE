package modelos;

public class Libro {
    private String nombre;
    private int precio;
    
    public Libro(String titulo) {
        this.nombre = titulo;
    }

    public Libro(String titulo, int precio) {
        this.nombre = titulo;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.toLowerCase().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Libro other = (Libro) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.toLowerCase().equals(other.nombre.toLowerCase()))
            return false;
        return true;
    }
}
