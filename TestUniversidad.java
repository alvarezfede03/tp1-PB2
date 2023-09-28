package tp_1;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Date;

import java.util.Calendar;

public class TestUniversidad {

	@Test
	public void testAgregarMateria() {
		Universidad universidad = new Universidad();
		Materia materia = new Materia(1, "PB2");
		universidad.AgregarMateria(materia);
		assertTrue(universidad.getMaterias().contains(materia));
	}

	@Test
	public void testAgregarAlumno() {
		Universidad universidad = new Universidad();
		Alumnos alumno = new Alumnos(1, "Juan", "Perez", new Date(), new Date());
		universidad.AgregarAlumno(alumno);
		assertTrue(universidad.getAlumnos().contains(alumno));
	}

	@Test
	public void testCrearCicloLectivo() {
		Universidad universidad = new Universidad();
		CicloLectivo cicloLectivo = new CicloLectivo(1, new Date(), new Date(), new Date(), new Date());
		universidad.CrearCicloLectivo(cicloLectivo);
		assertTrue(universidad.getCiclosLectivos().contains(cicloLectivo));
	}

	@Test
	public void testCrearCurso() {
		Universidad universidad = new Universidad();
		Comision curso = new Comision(1, null, null, "Mañana");
		universidad.CrearCurso(curso);
		assertTrue(universidad.getCursos().contains(curso));
	}

	@Test
	public void testCrearDocente() {
		Universidad universidad = new Universidad();
		Profesor docente = new Profesor(1, "Maria", "Gomez", new Date(), new Date());
		universidad.CrearDocente(docente);
		assertTrue(universidad.getDocentes().contains(docente));
	}

	@Test
	public void testNoAsignarMateriasConMismoId() {
		Universidad universidad = new Universidad();
		Materia materia1 = new Materia(1, "PB2");
		Materia materia2 = new Materia(1, "Base de Datos");
		universidad.AgregarMateria(materia1);
		assertTrue(universidad.AgregarMateria(materia2));
	}

	@Test
	public void testNoAsignarAlumnosConMismoDNI() {
		Universidad universidad = new Universidad();
		Alumnos alumno1 = new Alumnos(1, "Juan", "Perez", new Date(), new Date());
		Alumnos alumno2 = new Alumnos(1, "María", "Gomez", new Date(), new Date());
		universidad.AgregarAlumno(alumno1);
		assertTrue(universidad.AgregarAlumno(alumno2));
	}

	@Test
	public void testNoAsignarCiclosLectivosConMismoId() {
		Universidad universidad = new Universidad();
		CicloLectivo ciclo1 = new CicloLectivo(1, new Date(), new Date(), new Date(), new Date());
		CicloLectivo ciclo2 = new CicloLectivo(1, new Date(), new Date(), new Date(), new Date());
		universidad.CrearCicloLectivo(ciclo1);
		assertTrue(universidad.CrearCicloLectivo(ciclo2));
	}

	@Test
	public void testNoGenerarDosComisionesParaLaMismaMateriaYCiclo() {
		Universidad universidad = new Universidad();
		Materia materia = new Materia(1, "PB2");
		CicloLectivo ciclo = new CicloLectivo(1, new Date(), new Date(), new Date(), new Date());
		Comision comision1 = new Comision(1, materia, ciclo, "Mañana");
		Comision comision2 = new Comision(2, materia, ciclo, "Tarde");
		universidad.CrearCurso(comision1);
		assertTrue(universidad.CrearCurso(comision2));
	}

	@Test
	public void testAsignarMateriaCorrelativa() {
		Universidad universidad = new Universidad();
		Materia matematicas = new Materia(1, "PB1");
		Materia fisica = new Materia(2, "PB2");

		universidad.AgregarMateria(matematicas);
		universidad.AgregarMateria(fisica);

		assertEquals(0, matematicas.obtenerMateriasCorrelativas().size());
		assertEquals(0, fisica.obtenerMateriasCorrelativas().size());

		universidad.AsignarMateriaCorrelativa(1, 2);

		assertEquals(1, matematicas.obtenerMateriasCorrelativas().size());
		assertTrue(matematicas.obtenerMateriasCorrelativas().contains(fisica));

		assertEquals(1, fisica.obtenerMateriasCorrelativas().size());
	}

	@Test
	public void testEliminarCorrelativa() {
		Universidad universidad = new Universidad();
		Materia materia1 = new Materia(1, "PB1");
		Materia materia2 = new Materia(2, "Informatica");
		universidad.AgregarMateria(materia1);
		universidad.AgregarMateria(materia2);

		materia1.agregarMateriaCorrelativa(materia2);

		assertTrue(materia1.obtenerMateriasCorrelativas().contains(materia2));

		universidad.EliminarCorrelativa(1, 2);

		assertFalse(materia1.obtenerMateriasCorrelativas().contains(materia2));
	}

	@Test
	public void testInscribirAlumnoACursoExitoso() {
		Universidad universidad = new Universidad();
		Alumnos alumno = new Alumnos(1, "Federico", "Alvarez", new Date(), new Date());
		Comision curso = new Comision(1, new Materia(1, "PB2"),
				new CicloLectivo(1, new Date(), new Date(), new Date(), new Date()), "Mañana");

		universidad.AgregarAlumno(alumno);
		universidad.CrearCurso(curso);
		universidad.CrearCicloLectivo(curso.getCicloLectivo());

		boolean inscripcionExitosa = universidad.InscribirAlumnoACurso(alumno.getId(), curso.getId());
		assertFalse(inscripcionExitosa);
	}

	@Test
	public void testInscribirAlumnoACursoCicloLectivoNoPermite() {
		Universidad universidad = new Universidad();
		Alumnos alumno = new Alumnos(1, "Juan", "Lopez", new Date(), new Date());
		Comision curso = new Comision(1, new Materia(1, "InglesII"),
				new CicloLectivo(1, new Date(), new Date(), new Date(), new Date()), "Noche");

		universidad.AgregarAlumno(alumno);
		universidad.CrearCurso(curso);

		Calendar fechaInicioInscripcion = Calendar.getInstance();
		fechaInicioInscripcion.set(2023, Calendar.JANUARY, 1);
		Calendar fechaFinalizacionInscripcion = Calendar.getInstance();
		fechaFinalizacionInscripcion.set(2023, Calendar.FEBRUARY, 1);

		curso.getCicloLectivo().setFechaInicioInscripcion(fechaInicioInscripcion.getTime());
		curso.getCicloLectivo().setFechaFinalizacionInscripcion(fechaFinalizacionInscripcion.getTime());

		boolean inscripcionExitosa = universidad.InscribirAlumnoACurso(alumno.getId(), curso.getId());
		assertFalse(inscripcionExitosa);
	}

	@Test
	public void testInscribirAlumnoACursoAlumnoNoExiste() {
		Universidad universidad = new Universidad();
		Comision curso = new Comision(1, new Materia(1, "Prog.Web"),
				new CicloLectivo(1, new Date(), new Date(), new Date(), new Date()), "Mañana");

		boolean inscripcionExitosa = universidad.InscribirAlumnoACurso(99, curso.getId());
		assertFalse(inscripcionExitosa);
	}

	@Test
	public void testInscribirAlumnoACursoCursoNoExiste() {
		Universidad universidad = new Universidad();
		Alumnos alumno = new Alumnos(1, "Pedro", "Fernandez", new Date(), new Date());

		boolean inscripcionExitosa = universidad.InscribirAlumnoACurso(alumno.getId(), 99);
		assertFalse(inscripcionExitosa);
	}

	@Test
	public void testInscripcionRequiereAlumnoYCursoRegistrados() {
		Universidad universidad = new Universidad();
		Alumnos alumno = new Alumnos(1, "Laura", "Flores", new Date(), new Date());
		Comision curso = new Comision(1, new Materia(1, "Pb2"),
				new CicloLectivo(1, new Date(), new Date(), new Date(), new Date()), "noche");

		boolean inscripcionExitosa = universidad.InscribirAlumnoACurso(alumno.getId(), curso.getId());
		assertFalse(inscripcionExitosa);
	}

	@Test
	public void testInscripcionRequiereCorrelativasAprobadas() {
		Universidad universidad = new Universidad();
		Alumnos alumno = new Alumnos(1, "Marcos", "Juarez", new Date(), new Date());
		Comision curso = new Comision(1, new Materia(1, "InglesII"),
				new CicloLectivo(1, new Date(), new Date(), new Date(), new Date()), "Tarde");

		universidad.AgregarAlumno(alumno);
		universidad.CrearCurso(curso);
		universidad.CrearCicloLectivo(curso.getCicloLectivo());

		Materia materiaCorrelativa = new Materia(2, "InglesI");
		curso.getMateria().agregarMateriaCorrelativa(materiaCorrelativa);

		boolean inscripcionExitosa = universidad.InscribirAlumnoACurso(alumno.getId(), curso.getId());
		assertFalse(inscripcionExitosa);
	}

	@Test
	public void testInscripcionFueraDeFechaNoPermitida() {
		Universidad universidad = new Universidad();
		Alumnos alumno = new Alumnos(1, "Maria", "Lopez", new Date(), new Date());
		Comision curso = new Comision(1, new Materia(1, "Base de Datos"),
				new CicloLectivo(1, new Date(), new Date(), new Date(), new Date()), "mañana");

		universidad.AgregarAlumno(alumno);
		universidad.CrearCurso(curso);
		universidad.CrearCicloLectivo(curso.getCicloLectivo());

		Date fechaActual = new Date();
		curso.getCicloLectivo().setFechaInicioInscripcion(new Date(fechaActual.getTime() + 86400000));
		curso.getCicloLectivo().setFechaFinalizacionInscripcion(new Date(fechaActual.getTime() + 2 * 86400000)); // mañana

		boolean inscripcionExitosa = universidad.InscribirAlumnoACurso(alumno.getId(), curso.getId());
		assertFalse(inscripcionExitosa);
	}

	@Test
	public void testNoPuedeInscribirseEnMismoDiaYTurno() {
		Universidad universidad = new Universidad();
		Alumnos alumno1 = new Alumnos(1, "Juan", "Alvarez", new Date(), new Date());
		Alumnos alumno2 = new Alumnos(2, "Federico", "Alvarez", new Date(), new Date());
		Comision curso1 = new Comision(1, new Materia(1, "PB2"),
				new CicloLectivo(1, new Date(), new Date(), new Date(), new Date()), "Mañana");
		Comision curso2 = new Comision(2, new Materia(2, "INglesII"),
				new CicloLectivo(2, new Date(), new Date(), new Date(), new Date()), "Mañana");

		universidad.AgregarAlumno(alumno1);
		universidad.CrearCurso(curso1);
		universidad.CrearCicloLectivo(curso1.getCicloLectivo());

		boolean inscripcionExitosa1 = universidad.InscribirAlumnoACurso(alumno1.getId(), curso1.getId());
		assertFalse(inscripcionExitosa1);

		universidad.AgregarAlumno(alumno2);
		universidad.CrearCurso(curso2);
		universidad.CrearCicloLectivo(curso2.getCicloLectivo());

		boolean inscripcionExitosa2 = universidad.InscribirAlumnoACurso(alumno2.getId(), curso2.getId());
		assertFalse(inscripcionExitosa2);
	}

	@Test
	public void testAsignarProfesoresALCursoDocenteNoExiste() {
		Universidad universidad = new Universidad();
		Comision curso = new Comision(1, new Materia(1, "InglesII"),
				new CicloLectivo(1, new Date(), new Date(), new Date(), new Date()), "Noche");

		universidad.CrearCurso(curso);
		universidad.CrearCicloLectivo(curso.getCicloLectivo());

		universidad.AsignarProfesoresALCurso(curso.getId(), 1);

		assertFalse(curso.getProfesores().size() > 0);
	}

	@Test
	public void testRegistrarNotaExitoso() {
		Universidad universidad = new Universidad();
		Alumnos alumno = new Alumnos(1, "Lucas", "Miranda", new Date(), new Date());
		Materia materia = new Materia(1, "InglesI");
		CicloLectivo cicloLectivo = new CicloLectivo(1, new Date(), new Date(), new Date(), new Date());
		Comision curso = new Comision(1, materia, cicloLectivo, "Mañana");

		universidad.AgregarAlumno(alumno);
		universidad.CrearMateria(materia);
		universidad.CrearCicloLectivo(cicloLectivo);
		universidad.CrearCurso(curso);
		universidad.InscribirAlumnoACurso(alumno.getId(), curso.getId());

		int nota = 8;

		boolean registroExitoso = universidad.registrarNota(curso.getId(), alumno.getId(), materia.getId(), nota);

		assertFalse(registroExitoso);

	}

	@Test
	public void testRegistrarNotaCursoNoExiste() {
		Universidad universidad = new Universidad();
		Alumnos alumno = new Alumnos(1, "Juan", "Lopez", new Date(), new Date());
		Materia materia = new Materia(1, "Pb1");
		CicloLectivo cicloLectivo = new CicloLectivo(1, new Date(), new Date(), new Date(), new Date());

		universidad.AgregarAlumno(alumno);
		universidad.CrearMateria(materia);
		universidad.CrearCicloLectivo(cicloLectivo);

		int nota = 8;

		boolean registroExitoso = universidad.registrarNota(1, alumno.getId(), materia.getId(), nota);

		assertFalse(registroExitoso);
	}

	@Test
	public void testNotaDebeEstarEntre1y10() {
		Universidad universidad = new Universidad();
		Alumnos alumno = new Alumnos(1, "Marcos", "Benitez", new Date(), new Date());
		Materia materia = new Materia(1, "PB2");
		CicloLectivo cicloLectivo = new CicloLectivo(1, new Date(), new Date(), new Date(), new Date());

		universidad.AgregarAlumno(alumno);
		universidad.CrearMateria(materia);
		universidad.CrearCicloLectivo(cicloLectivo);

		boolean asignacionExitosa = universidad.registrarNota(alumno.getId(), materia.getId(), cicloLectivo.getId(),
				11);
		assertFalse(asignacionExitosa);
	}

	@Test
	public void testNoPuedeAsignarNotaSinCorrelativasAprobadas() {
		Universidad universidad = new Universidad();
		Alumnos alumno = new Alumnos(1, "Marcos", "Benitez", new Date(), new Date());
		Materia materiaPrincipal = new Materia(1, "InglesII");
		Materia materiaCorrelativa = new Materia(2, "InglesI");
		CicloLectivo cicloLectivo = new CicloLectivo(1, new Date(), new Date(), new Date(), new Date());

		universidad.AgregarAlumno(alumno);
		universidad.CrearMateria(materiaPrincipal);
		universidad.CrearMateria(materiaCorrelativa);
		universidad.CrearCicloLectivo(cicloLectivo);

		alumno.aprobarMateria(materiaCorrelativa, cicloLectivo, 6);

		boolean asignacionExitosa = universidad.registrarNota(alumno.getId(), materiaPrincipal.getId(),
				cicloLectivo.getId(), 7);
		assertFalse(asignacionExitosa);
	}

}