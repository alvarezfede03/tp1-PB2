package tp_1;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Alumnos {

	private int id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private Date fechaIngreso;
    private List<MateriaAprobada> materiasAprobadas;

    public Alumnos(int id, String nombre, String apellido, Date fechaNacimiento, Date fechaIngreso) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.materiasAprobadas = new ArrayList<>();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	 public void aprobarMateria(Materia materia, CicloLectivo cicloLectivo, int nota) {
	        
	        if (correlativasAprobadas(materia)) {
	            
	            MateriaAprobada materiaAprobada = new MateriaAprobada(materia, cicloLectivo, nota);
	            materiasAprobadas.add(materiaAprobada);
	        }
	    } 
	 
	 private boolean correlativasAprobadas(Materia materia) {
	        for (Materia correlativa : materia.getMateriasCorrelativas()) {
	            boolean correlativaAprobada = false;
	            for (MateriaAprobada materiaAprobada : materiasAprobadas) {
	                if (materiaAprobada.getMateria().equals(correlativa)) {
	                    correlativaAprobada = true;
	                    break;
	                }
	            }
	            if (!correlativaAprobada) {
	                return false;
	            }
	        }
	        return true;
	    }
}
