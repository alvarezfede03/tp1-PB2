package tp_1;

public class MateriaAprobada {
	private Materia materia;
	private CicloLectivo cicloLectivo;
	private int nota;

	public MateriaAprobada(Materia materia, CicloLectivo cicloLectivo, int nota) {
		this.materia = materia;
		this.cicloLectivo = cicloLectivo;
		this.nota = nota;
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

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}
}
