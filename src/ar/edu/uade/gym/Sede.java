package ar.edu.uade.gym;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import ar.edu.uade.gym.articulos.Articulo;
import ar.edu.uade.gym.articulos.TipoArticulo;
import ar.edu.uade.usuarios.Cliente;
import ar.edu.uade.usuarios.Profesor;
import ar.edu.uade.usuarios.Usuario;

public class Sede {
    private final String ubicacion;
    private TipoNivel tipoNivel;
    private ArrayList<Articulo> stockArticulos;
    private ArrayList<Emplazamiento> emplazamientosDisponibles;
    private ArrayList<Clase> listaClases;
    private ArrayList<Ejercicio> ejerciciosDisponibles;
	private double alquilerSede;



	public Sede(String ubicacion, TipoNivel tipoNivel, ArrayList<Emplazamiento> emplazamientos,
				ArrayList<Ejercicio> ejercicios, double alquilerSede) {
    	this.ubicacion = ubicacion.toLowerCase();
    	this.tipoNivel = tipoNivel;
    	this.listaClases = new ArrayList<Clase>();
    	this.stockArticulos = new ArrayList<Articulo>();
		this.alquilerSede = alquilerSede;

        if (emplazamientos == null)
        	this.emplazamientosDisponibles = new ArrayList<Emplazamiento>();
        else
        	this.emplazamientosDisponibles = emplazamientos;

        if (ejerciciosDisponibles == null)
	        this.ejerciciosDisponibles = new ArrayList<Ejercicio>();
        else
        	this.ejerciciosDisponibles = ejercicios;

    }

	/* =======================================================
	 *                    METODOS DE PROFESORES
	 * =======================================================
	 */
    
	private void validarProfesor(Clase claseNueva, Usuario profesor) throws GymException {
		if (!profesor.soyProfesor())
			throw new GymException("El Usuario no es un profesor.");

		ArrayList<Clase> listaClasesMismoDia = getListaClases();
		listaClasesMismoDia.removeIf(clase -> (clase.getFecha() == claseNueva.getFecha()) );

		// Chequear que el profesor no tenga otra clase en el mismo horario.
		for (Clase clase: listaClasesMismoDia) {
			Profesor profeAsignado = clase.getProfesor();
			if (profeAsignado.equals(profesor) && clase.getFecha() == clase.getFecha()
					&& clase.getHorarioInicio() == claseNueva.getHorarioInicio()) {
				throw new GymException("El profesor tiene una clase asignada en el mismo horario.");
			}
		}

		// Chequear que el profesor no tenga un intervalo menor a 3 hs entre clase y clase.
		for (Clase clase: listaClasesMismoDia) {
			long tiempoEntreClases = clase.getHorarioInicio().until(claseNueva.getHorarioInicio(), ChronoUnit.MINUTES);
			if (Math.abs(tiempoEntreClases) >= 180) {
				throw new GymException("El profesor debe tener un intervalo de 3hs entre clase y clase.");
			}
		}
	}

	public ArrayList<Clase> getClasesProfesor(Profesor profesor) {
		ArrayList<Clase> clasesAsignadas = new ArrayList<Clase>();
		for (Clase clase: this.listaClases) {
			Profesor profeAsignado = clase.getProfesor();
			if (profeAsignado.equals(profesor)) {
				clasesAsignadas.add(clase);
			}
		}
		return clasesAsignadas;
	}

	public void asignarProfesor(Clase clase, Usuario profesor) throws GymException {
		validarProfesor(clase, profesor);
		clase.asignarProfesor((Profesor) profesor);
	}

	/* =======================================================
	 *                    METODOS DE CLIENTES
	 * =======================================================
	 */

	private void validarClaseDiariaAlumno(Clase claseNueva, Cliente alumno) throws GymException {
		for (Clase clase: this.listaClases) {
			if (clase.equals(claseNueva)) {
				throw new GymException("El alumno ya esta anotado en esta clase.");
			} else {
				if (clase.getFecha() == claseNueva.getFecha())
					if (clase.getListaAlumnos().contains(alumno))
						throw new GymException("El alumno ya esta anotado en una clase este mismo dia.");
			}
		}
	}

	private void validarListaAlumnos(Clase clase, ArrayList<Cliente> listaAlumnos) throws GymException {
		for (Cliente alumno: listaAlumnos) {
			validarClaseDiariaAlumno(clase, alumno);
		}
	}

	private void validarAlumno(Clase clase, Cliente alumno) throws GymException {
		ArrayList<Cliente> listaAlumnos = new ArrayList<Cliente>();
		listaAlumnos.add(alumno);
		validarListaAlumnos(clase, listaAlumnos);
	}


	public void agregarAlumno(Clase clase, Cliente cliente) throws GymException {
		validarAlumno(clase, cliente);
		clase.agregarAlumno(cliente, this.tipoNivel);
		validarYConfirmarClase(clase);
	}

	/* =======================================================
	 *                    METODOS DE CLASE
	 * =======================================================
	 */
    public void agregarClase(Profesor profesor, Ejercicio ejercicio, ArrayList<Cliente> listaAlumnos, LocalDate fecha,
							 LocalTime horarioInicio, Emplazamiento emplazamiento,
							 ArrayList<Articulo> listaArticulos, boolean esVirtual) throws GymException {
		Clase newClase = new Clase(profesor, ejercicio, listaAlumnos, this.getTipoNivel(), fecha, horarioInicio,
				emplazamiento, listaArticulos, esVirtual);
		validarProfesor(newClase, profesor);
		validarArticulosNecesarios(newClase, ejercicio);
    	validarEmplazamientoDisponible(emplazamiento, fecha, horarioInicio);
    	this.listaClases.add(newClase);
		validarYConfirmarClase(newClase);
    }


	private boolean validarArticulosNecesarios(Clase clase, Ejercicio ejercicio) {
		// Inicializo 2 arrays, los tipos de articulos que necesitamos para ejercicio
		// y los articulos que tenemos en la clase.
		ArrayList<TipoArticulo> tiposArticulosNecesarios = ejercicio.getArticuloNecesarios();
		ArrayList<Articulo> articulosClase = clase.getListaArticulos();

		for (TipoArticulo tipoArticulo : tiposArticulosNecesarios) {
			boolean flagFound = false;
			int index = 0;
			for (Articulo articulo : articulosClase) {
				if (tipoArticulo.equals(articulo.getTipoArticulo()) ) {
					flagFound = true;
					break;
				} else {
					index++;
				}
			}
			if (flagFound) {articulosClase.remove(index);}
			else {break;}

		}

		if (articulosClase.size() == 0) {
			return true;
		} else {
			System.out.println("No se encuentran los articulos necesarios para confirmar esta clase.");
			return false;
		}
	}

	public void validarYConfirmarClase(Clase clase) {
		// TO-DO AGREGAR VALIDACIONES DE EMPLAZAMIENTO, PROFESOR Y OTROS SI ES NECESARIO.
		if (validarRentabilidad(clase))
			if (validarArticulosNecesarios(clase, clase.getEjercicio()))
				clase.confirmarClase();
	}
	public void finalizarClase(Clase clase) {
		calcularDesgasteArticulos(clase);
		clase.finalizarClase();
	}



	/* =======================================================
	 *                    METODOS DE EJERCICIOS
	 * =======================================================
	 */
	public void agregarEjerciciosDisponibles(Ejercicio ejercicio) {
		this.ejerciciosDisponibles.add(ejercicio);
	}

	/* =======================================================
	 *                    METODOS DE EMPLAZAMIENTOS
	 * =======================================================
	 */
	public void crearEmplazamiento(TipoEmplazamiento tipoEmplazamiento, int capacidad, int metrosCuadrados) {
		this.emplazamientosDisponibles.add(new Emplazamiento(tipoEmplazamiento, capacidad, metrosCuadrados));
	}


	public void validarEmplazamientoDisponible(Emplazamiento emplazamiento, LocalDate fecha, LocalTime horario) throws GymException{
		for (Clase clase: this.listaClases) {
			if (fecha.isEqual(clase.getFecha()) && horario.compareTo(clase.getHorarioInicio()) == 0)
					if (clase.getEmplazamiento().equals(emplazamiento))
						throw new GymException("El emplazamiento no esta disponible en ese horario.");
		}
	}


	public ArrayList<Emplazamiento> getListaEmplazamientos(TipoEmplazamiento tipoEmplazamiento) {
		ArrayList<Emplazamiento> lista = new ArrayList<Emplazamiento>();
		for (Emplazamiento emplazamiento: emplazamientosDisponibles) {
			if (tipoEmplazamiento.equals(emplazamiento.getTipoEmplazamiento()))
				lista.add(emplazamiento);
		}
		return lista;
	}


	/* =======================================================
	 *                    METODOS DE ARTICULOS
	 * =======================================================
	 */

	public void agregarArticulo(Articulo newArticulo) {
		this.stockArticulos.add(newArticulo);
	}

	private void calcularDesgasteArticulos(Clase clase) {
		for (Articulo articulo : clase.getListaArticulos())
			articulo.setDesgaste();
	}

	public void eliminarArticulo(Articulo articulo) {
		this.stockArticulos.remove(articulo);
	}

	public void darDeBajaArticulo(Articulo articulo) {
		for (Articulo articuloStock : this.stockArticulos)
			if (articulo.equals(articuloStock))
				articulo.darDeBaja();
	}


	/* =======================================================
	 *                    METODOS DE RENTABILIDAD
	 * =======================================================
	 */

	private double calcularIngresos(Clase clase) {
		double ingresosPorMembresia = 0;
		ArrayList<Cliente> listaAlumnos = clase.getListaAlumnos();
		for (Cliente alumno : listaAlumnos)
			ingresosPorMembresia = ingresosPorMembresia + alumno.getCostoMembresia();
		return (ingresosPorMembresia / 30) * listaAlumnos.size();
	}

	private double calcularCostoProfesor(Clase clase) {
		return clase.getProfesor().getSueldo() / 90;
	}

	private double calcularCostoEmplazamiento(Clase clase) {
		Emplazamiento emplazamientoClase = clase.getEmplazamiento();
		return switch (emplazamientoClase.getTipoEmplazamiento()) {
			case AIRE_LIBRE -> this.alquilerSede / 300;
			case SALON -> this.alquilerSede / 150;
			case PILETA -> (500 * emplazamientoClase.getMetrosCuadrados()) / clase.getDuracionHoras(); //$ 500 por metro cuadrado/hora
		};
	}

	private double calcularCostoArticulo(Articulo articulo, long horas) {
		return switch (articulo.getFormaAmortizacion()) {
			case POR_USO  -> articulo.getCostoAmortizacion();
			case FECHA_FABRICACION -> ( horas / 8 ) * articulo.getCostoAmortizacion();
		};
	}
	private double calcularCostoListaArticulos(Clase clase) {
		double costoTotal = 0;
		for (Articulo articulo : clase.getListaArticulos()) {
			costoTotal = costoTotal + calcularCostoArticulo(articulo, clase.getDuracionHoras());
		}
		return costoTotal;
	}
	private double calcularCostos(Clase clase) {
		double costoProfesor = calcularCostoProfesor(clase);
		double costoEmplazamiento = calcularCostoEmplazamiento(clase);
		double costoListaArticulos = calcularCostoListaArticulos(clase);

		return costoProfesor + costoEmplazamiento + costoListaArticulos;
	}

	public boolean validarRentabilidad(Clase clase) {
		double ingresos = calcularIngresos(clase);
		double costos = calcularCostos(clase);
		double calculoRentabilidad = ingresos - costos;

		if (calculoRentabilidad > 0) {
			return true;
		} else {
			System.out.println("No se puede confirmar la clase debido a que NO es rentable.");
			return false;
		}
	}


	/* =======================================================
	 *                    GETTERS / SETTERS
	 * =======================================================
	 */
	public ArrayList<Ejercicio> getEjerciciosDisponibles() {
		return this.ejerciciosDisponibles;
	}
    
    public String getUbicacion() {
        return this.ubicacion;
    }
    
    public TipoNivel getTipoNivel() {
        return this.tipoNivel;
    }
    
    public ArrayList<Clase> getListaClases() {
        return this.listaClases;
    }

	public ArrayList<Articulo> getListaArticulos() {
		return this.stockArticulos;
	}
    
    @Override
    public String toString() {
    	return "["+this.ubicacion+","+this.tipoNivel.toString()+"]";
    }

}
