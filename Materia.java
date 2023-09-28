package tp_1;

import java.util.ArrayList;
import java.util.List;

public class Materia {

	 private int id;
	 private String nombre;
	 private List<Materia> materiasCorrelativas;

	    public Materia(int id, String nombre) {
	        this.id = id;
	        this.nombre = nombre;
	        this.materiasCorrelativas = new ArrayList<>();
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
	
		public void agregarMateriaCorrelativa(Materia materiaCorrelativa) {
	        if (materiaCorrelativa != null) {
	            if (!materiasCorrelativas.contains(materiaCorrelativa)) {
	                materiasCorrelativas.add(materiaCorrelativa);
	                materiaCorrelativa.agregarMateriaCorrelativa(this); 
	            }
	        }
	    }

	    public List<Materia> obtenerMateriasCorrelativas() {
	        return materiasCorrelativas;
	    }

	    public void eliminarCorrelativa(Materia correlativaAEliminar) {
	        if (correlativaAEliminar != null) {
	            if (materiasCorrelativas.contains(correlativaAEliminar)) {
	                materiasCorrelativas.remove(correlativaAEliminar);
	                correlativaAEliminar.materiasCorrelativas.remove(this);
	            }
	        }
	    }

		public String getDescripcion() {
			// TODO Auto-generated method stub
			return null;
		}


		public List<Materia> getMateriasCorrelativas() {
	        return materiasCorrelativas;
	    }
		
}
