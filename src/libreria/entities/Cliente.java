package libreria.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private Long documento;
    private String nombre;
    private String apellido;
    private String telefono;

    public Cliente() {
    }

    public Cliente(Long documento, String nombre, String apellido, String telefono) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public Cliente(Integer id, Long documento, String nombre, String apellido, String telefono) {
        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Prestamo{" + "id=" + id + ", documento=" + documento + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + '}';
    }

}
