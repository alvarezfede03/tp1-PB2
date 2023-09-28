package tp_1;

public class Aula {

	 private int id;
	 private int cantidadDeAlumnos;
	 

	    public Aula(int id, int cantidadDeAlumnos) {
	        this.id = id;
	        this.cantidadDeAlumnos = cantidadDeAlumnos;
	    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getCantidadDeAlumnos() {
			return cantidadDeAlumnos;
		}

		public void setCantidadDeAlumnos(int cantidadDeAlumnos) {
			this.cantidadDeAlumnos = cantidadDeAlumnos;
		}
	
	   
}
