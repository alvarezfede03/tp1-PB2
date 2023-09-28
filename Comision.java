package tp_1;

import java.util.List;
import java.util.ArrayList;


public class Comision {

	private int id;
    private Materia materia;
    private CicloLectivo cicloLectivo;
    private String turno;
    private Aula aula;
    private List<Profesor> profesores;
    
    
    public Comision(int id, Materia materia, CicloLectivo cicloLectivo, String turno) {
        this.id = id;
        this.materia = materia;
        this.cicloLectivo = cicloLectivo;
        this.turno = turno;
        this.profesores = new ArrayList<>();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public CicloLectivo getCicloLectivo() {
		return cicloLectivo;
	}

	public void setCicloLectivo(CicloLectivo cicloLectivo) {
		this.cicloLectivo = cicloLectivo;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public void agregarProfesor(Profesor profesor) {
        if (profesor != null && !profesores.contains(profesor)) {
            profesores.add(profesor);
            profesor.asignarComision(this); 
        }
    }

    
	public List<Profesor> getProfesores() {
        return profesores;
    }
	
}
