package tp_1;

public class Nota {

	private Comision comision;
    private Alumnos alumno;
    private Materia materia;
    private int valorNota;

    public Nota(Comision comision, Alumnos alumno, Materia materia, int valorNota) {
        this.comision = comision;
        this.alumno = alumno;
        this.materia = materia;
        this.valorNota = valorNota;
    }

	public Comision getComision() {
		return comision;
	}

	public void setComision(Comision comision) {
		this.comision = comision;
	}

	public Alumnos getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumnos alumno) {
		this.alumno = alumno;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public int getValorNota() {
		return valorNota;
	}

	public void setValorNota(int valorNota) {
		this.valorNota = valorNota;
	}
    
    
}
