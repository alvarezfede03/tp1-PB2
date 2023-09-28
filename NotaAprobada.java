package tp_1;

public class NotaAprobada {

	 private int dni;
     private String nombre;
     private String apellido;
     private String nombreMateria;
     private int nota;
     private String descripcion;
     private int cicloLectivo;

     public NotaAprobada(int dni, String nombre, String apellido, String nombreMateria, int nota, String descripcion, int cicloLectivo) {
         this.dni = dni;
         this.nombre = nombre;
         this.apellido = apellido;
         this.nombreMateria = nombreMateria;
         this.nota = nota;
         this.descripcion = descripcion;
         this.cicloLectivo = cicloLectivo;
     }

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
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

	public String getNombreMateria() {
		return nombreMateria;
	}

	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCicloLectivo() {
		return cicloLectivo;
	}

	public void setCicloLectivo(int cicloLectivo) {
		this.cicloLectivo = cicloLectivo;
	}
	
	
}
