package tp_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Universidad {

	private List<Materia> materias;
	private List<Alumnos> alumnos;
	private List<CicloLectivo> ciclosLectivos;
	private List<Comision> cursos;
	private List<Profesor> docentes;
	private List<Inscripcion> inscripciones;
	private List<Nota> notas;

	public Universidad() {
		materias = new ArrayList<>();
		alumnos = new ArrayList<>();
		ciclosLectivos = new ArrayList<>();
		cursos = new ArrayList<>();
		docentes = new ArrayList<>();
		inscripciones = new ArrayList<>();
		notas = new ArrayList<>();
	}

	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	public List<Alumnos> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumnos> alumnos) {
		this.alumnos = alumnos;
	}

	public List<CicloLectivo> getCiclosLectivos() {
		return ciclosLectivos;
	}

	public void setCiclosLectivos(List<CicloLectivo> ciclosLectivos) {
		this.ciclosLectivos = ciclosLectivos;
	}

	public List<Comision> getCursos() {
		return cursos;
	}

	public void setCursos(List<Comision> cursos) {
		this.cursos = cursos;
	}

	public List<Profesor> getDocentes() {
		return docentes;
	}

	public void setDocentes(List<Profesor> docentes) {
		this.docentes = docentes;
	}

	public List<Inscripcion> getInscripciones() {
		return inscripciones;
	}

	public void setInscripciones(List<Inscripcion> inscripciones) {
		this.inscripciones = inscripciones;
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	public boolean AgregarMateria(Materia materia) {
		if (materias.contains(materia)) {
			return false;
		}
		materias.add(materia);
		return true;
	}

	public boolean AgregarAlumno(Alumnos alumno) {
		if (alumnos.contains(alumno)) {
			return false;
		}
		alumnos.add(alumno);
		return true;
	}

	public boolean CrearCicloLectivo(CicloLectivo cicloLectivo) {
		if (ciclosLectivos.contains(cicloLectivo)) {
			return false;
		}
		ciclosLectivos.add(cicloLectivo);
		return true;
	}

	public boolean CrearCurso(Comision curso) {
		if (cursos.contains(curso)) {
			return false;
		}
		cursos.add(curso);
		return true;
	}

	public void CrearDocente(Profesor docente) {
		docentes.add(docente);
	}

	public void AsignarMateriaCorrelativa(int codigoMateria, int codigoDeMateriaCorrelativa) {
		Materia materiaPrincipal = null;
		Materia materiaCorrelativa = null;

		for (Materia materia : materias) {
			if (materia.getId() == codigoMateria) {
				materiaPrincipal = materia;
			}
			if (materia.getId() == codigoDeMateriaCorrelativa) {
				materiaCorrelativa = materia;
			}
		}

		if (materiaPrincipal != null && materiaCorrelativa != null) {
			materiaPrincipal.agregarMateriaCorrelativa(materiaCorrelativa);
		} else {
			System.out.println("Una o ambas materias no existen. No se pudo asignar la materia correlativa.");
		}
	}

	public void EliminarCorrelativa(int codigoMateria, int codigoCorrelativaAEliminar) {

		Materia materiaPrincipal = buscarMateriaPorCodigo(codigoMateria);

		Materia correlativaAEliminar = buscarMateriaPorCodigo(codigoCorrelativaAEliminar);

		if (materiaPrincipal != null && correlativaAEliminar != null) {

			materiaPrincipal.eliminarCorrelativa(correlativaAEliminar);
		} else {
			System.out.println("No se encontró la materia principal o la correlativa a eliminar.");
		}

	}

	private Materia buscarMateriaPorCodigo(int codigo) {
		for (Materia materia : materias) {
			if (materia.getId() == codigo) {
				return materia;
			}
		}
		return null;
	}

	public boolean InscribirAlumnoACurso(int id, int codigoCurso) {

		Alumnos alumno = buscarAlumnoPorID(id);

		Comision curso = buscarCursoPorCodigo(codigoCurso);

		if (alumno != null && curso != null) {

			CicloLectivo cicloLectivo = obtenerCicloLectivoActual();
			if (cicloLectivo != null && cicloLectivo.permitirInscripciones()) {

				Inscripcion inscripcion = new Inscripcion(alumno, curso);
				inscripciones.add(inscripcion);
				return true;
			}
		}
		return false;
	}

	public Alumnos buscarAlumnoPorID(int id) {
		for (Alumnos alumno : alumnos) {
			if (alumno.getId() == id) {
				return alumno;
			}
		}
		return null;
	}

	public Comision buscarCursoPorCodigo(int codigoCurso) {
		for (Comision curso : cursos) {
			if (curso.getId() == codigoCurso) {
				return curso;
			}
		}
		return null;
	}

	public CicloLectivo obtenerCicloLectivoActual() {

		Date fechaActual = new Date();

		for (CicloLectivo cicloLectivo : ciclosLectivos) {
			Date fechaInicio = cicloLectivo.getFechaInicioCicloLectivo();
			Date fechaFin = cicloLectivo.getFechaFinalizacionCicloLectivo();

			if (fechaActual.after(fechaInicio) && fechaActual.before(fechaFin)) {
				return cicloLectivo;
			}
		}

		return null;
	}

	public void AsignarProfesoresALCurso(int idCurso, int idDocente) {
		Comision curso = buscarCursoPorCodigo(idCurso);

		if (curso != null) {
			Profesor docente = buscarDocentePorCodigo(idDocente);

			if (docente != null) {
				curso.agregarProfesor(docente);
			}
		}
	}

	private Profesor buscarDocentePorCodigo(int idDocente) {
		for (Profesor docente : docentes) {
			if (docente.getId() == idDocente) {
				return docente;
			}
		}
		return null;
	}

	public List<Object[]> obtenerListadoMateriasAprobadasParaUnAlumno(int idAlumno) {
		List<Object[]> listadoMateriasAprobadas = new ArrayList<>();

		Alumnos alumno = buscarAlumnoPorID(idAlumno);

		if (alumno != null) {

			List<Nota> notasDelAlumno = obtenerNotasDelAlumno(alumno);

			for (Nota nota : notasDelAlumno) {
				if (nota.getValorNota() >= 4) {

					Materia materia = nota.getMateria();
					CicloLectivo cicloLectivo = obtenerCicloLectivoDeMateria(materia);

					Object[] infoMateriaAprobada = new Object[] { alumno.getId(), alumno.getNombre(),
							alumno.getApellido(), materia.getNombre(), nota.getValorNota(), materia.getDescripcion(),
							cicloLectivo != null ? cicloLectivo.getId() : "N/A" };

					listadoMateriasAprobadas.add(infoMateriaAprobada);
				}
			}
		}

		return listadoMateriasAprobadas;
	}

	public CicloLectivo obtenerCicloLectivoDeMateria(Materia materia) {
		for (CicloLectivo cicloLectivo : ciclosLectivos) {
			if (cicloLectivo.contieneMateria(materia)) {
				return cicloLectivo;
			}
		}
		return null;
	}

	private List<Nota> obtenerNotasDelAlumno(Alumnos alumno) {

		List<Nota> notasDelAlumno = new ArrayList<>();
		for (Nota nota : notas) {
			if (nota.getAlumno().equals(alumno)) {
				notasDelAlumno.add(nota);
			}
		}
		return notasDelAlumno;
	}

	public List<Materia> obtenerMateriasQueFaltanCursarParaUnAlumno(int idAlumno) {
		List<Materia> materiasFaltantes = new ArrayList<>();
		Alumnos alumno = buscarAlumnoPorID(idAlumno);

		if (alumno != null) {

			List<Materia> materiasAprobadas = obtenerMateriasAprobadasParaUnAlumno(idAlumno);

			List<Materia> todasLasMaterias = getMaterias();

			for (Materia materia : todasLasMaterias) {
				if (!materiasAprobadas.contains(materia)) {

					boolean cumpleCorrelativas = true;
					for (Materia correlativa : materia.obtenerMateriasCorrelativas()) {
						if (!materiasAprobadas.contains(correlativa)) {
							cumpleCorrelativas = false;
							break;
						}
					}
					if (cumpleCorrelativas) {
						materiasFaltantes.add(materia);
					}
				}
			}
		}
		return materiasFaltantes;
	}

	public List<Materia> obtenerMateriasAprobadasParaUnAlumno(int idAlumno) {
		List<Materia> materiasAprobadas = new ArrayList<>();
		Alumnos alumno = buscarAlumnoPorID(idAlumno);

		if (alumno != null) {

			List<Nota> notasDelAlumno = obtenerNotasDelAlumno(alumno);

			for (Nota nota : notasDelAlumno) {
				if (nota.getValorNota() >= 4) {

					materiasAprobadas.add(nota.getMateria());
				}
			}
		}

		return materiasAprobadas;
	}

	public boolean registrarNota(int idComision, int idAlumno, int idMateria, int nota) {
		Comision comision = buscarComisionPorID(idComision);
		Alumnos alumno = buscarAlumnoPorID(idAlumno);
		Materia materia = buscarMateriaPorID(idMateria);

		if (comision != null && alumno != null && materia != null) {

			if (alumnoEstaInscritoEnComision(alumno, comision)) {

				Nota nuevaNota = new Nota(comision, alumno, materia, nota);
				notas.add(nuevaNota);
				return true;
			}
		}
		return false;
	}

	private boolean alumnoEstaInscritoEnComision(Alumnos alumno, Comision comision) {

		for (Inscripcion inscripcion : inscripciones) {
			if (inscripcion.getAlumno().equals(alumno) && inscripcion.getCurso().equals(comision)) {
				return true;
			}
		}
		return false;
	}

	public Comision buscarComisionPorID(int idComision) {
		for (Comision comision : cursos) {
			if (comision.getId() == idComision) {
				return comision;
			}
		}
		return null;
	}

	public Materia buscarMateriaPorID(int idMateria) {
		for (Materia materia : materias) {
			if (materia.getId() == idMateria) {
				return materia;
			}
		}
		return null;
	}

	public boolean CrearMateria(Materia materia) {
		if (materias.contains(materia)) {
			return false;
		}
		materias.add(materia);
		return true;
	}

	public Nota obtenerNota(Alumnos alumno, Materia materia, CicloLectivo cicloLectivo) {

		for (Nota nota : notas) {
			if (nota.getAlumno().equals(alumno) && nota.getMateria().equals(materia)) {
				return nota;
			}
		}
		return null;
	}

}
