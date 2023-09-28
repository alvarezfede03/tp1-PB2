package tp_1;

import java.util.Date;

public class Inscripcion {

	private int id;
	private Alumnos alumno;
	private Comision curso;
	private Date fechaInscripcion;

	public Inscripcion(int id, Alumnos alumno, Comision curso, Date fechaInscripcion) {
		this.id = id;
		this.alumno = alumno;
		this.curso = curso;
		this.fechaInscripcion = fechaInscripcion;
	}

	public Inscripcion(Alumnos alumno, Comision curso) {
		this.alumno = alumno;
		this.curso = curso;
		this.fechaInscripcion = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Alumnos getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumnos alumno) {
		this.alumno = alumno;
	}

	public Comision getCurso() {
		return curso;
	}

	public void setCurso(Comision curso) {
		this.curso = curso;
	}

	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

}
