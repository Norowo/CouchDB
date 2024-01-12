package aed.couchdb;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

@TypeDiscriminator("doc.type === 'Alumno'")
public class Alumno extends CouchDbDocument {

    private String nombre;
    private String apellido;

    // Constructor sin argumentos necesario para Jackson
    public Alumno() {}

    // Constructor con argumentos
    public Alumno(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // Getters y setters
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
}