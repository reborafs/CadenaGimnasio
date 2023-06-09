package ar.edu.uade.gym;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;

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
    private HashSet<Ejercicio> ejerciciosDisponibles;
	private double alquilerSede;



	public Sede(String ubicacion, TipoNivel tipoNivel, ArrayList<Emplazamiento> emplazamientos,
				ArrayList<Ejercicio> ejercicios, double alquilerSede) {
    	this.ubicacion = ubicacion.toLowerCase();
    	this.tipoNivel = tipoNivel;
    	this.listaClases = new ArrayList<>();
    	this.stockArticulos = new ArrayList<>();
		this.alquilerSede = alquilerSede;

        if (emplazamientos == null)
        	this.emplazamientosDisponibles = new ArrayList<>();
        else
        	this.emplazamientosDisponibles = emplazamientos;

        if (ejercicios == null){
			this.ejerciciosDisponibles = new HashSet<>();}
        else
        	this.ejerciciosDisponibles = new HashSet<>(ejercicios);

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
			if (profeAsignado.equals(profesor) && clase.getFecha() == claseNueva.getFecha()
					&& clase.getHorarioInicio() == claseNueva.getHorarioInicio()) {
				throw new GymException("El profesor tiene una clase asignada en el mismo horario.");
			}
		}

		// Chequear que el profesor no tenga un intervalo menor a 3 hs entre clase y clase.
		for (Clase clase: listaClasesMismoDia) {
			long tiempoEntreClases = clase.getHorarioInicio().until(claseNueva.getHorarioInicio(), ChronoUnit.MINUTES);
			if(clase.getFecha() == claseNueva.getFecha()){
				if (Math.abs(tiempoEntreClases) >= 180) {
					throw new GymException("El profesor debe tener un intervalo de 3hs entre clase y clase.");
				}
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

	private void validarClaseDiariaAlumno(int claseNueva, Cliente alumno) throws GymException {
		for (Clase clase: this.listaClases) {
			if (clase.getClaseID() == claseNueva) {
				throw new GymException("El alumno ya esta anotado en esta clase.");
			} else {
				if (clase.getFecha() == this.listaClases.get(claseNueva).getFecha())
					if (clase.getListaAlumnos().contains(alumno))
						throw new GymException("El alumno ya esta anotado en una clase este mismo dia.");
			}
		}
	}

	private void validarListaAlumnos(int idClase, ArrayList<Cliente> listaAlumnos, Cliente alumnoNuevo) throws GymException {
		for (Cliente alumno: listaAlumnos) {
			//if(alumno.getID() == alumnoNuevo.getID()){
				validarClaseDiariaAlumno(idClase, alumnoNuevo);
			//}
		}
	}

	private void validarAlumno(int idClase, Cliente alumno) throws GymException {
		ArrayList<Cliente> listaAlumnos = new ArrayList<Cliente>();
		//listaAlumnos.add(alumno);
		validarListaAlumnos(idClase, listaAlumnos, alumno);
	}


	public void agregarAlumno(int idClase, Cliente cliente) throws GymException {
		validarAlumno(idClase, cliente);
		this.listaClases.get(idClase).agregarAlumno(cliente, this.tipoNivel);
		validarYConfirmarClase(this.listaClases.get(idClase));
	}

	public void eliminarAlumno(int idClase, Cliente cliente) throws GymException {
		this.listaClases.get(idClase).agregarAlumno(cliente, this.tipoNivel);
		validarYConfirmarClase(this.listaClases.get(idClase));
	}

	public ArrayList<Clase> getClasesCliente(Cliente cliente) {
		ArrayList<Clase> clasesAsignadas = new ArrayList<Clase>();
		for (Clase clase: this.listaClases) {
			ArrayList<Cliente> clienteAsignados = clase.getListaAlumnos();
			for(Cliente clienteAsignado:clienteAsignados) {
				if (clienteAsignado.equals(cliente)) {
					clasesAsignadas.add(clase);
				}
			}
		}
		return clasesAsignadas;
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
		ArrayList<Articulo> articulosClase = new ArrayList<>(clase.getListaArticulos());

		if (articulosClase.size() == 0)
			return false;

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
			//System.out.println("No se encuentran los articulos necesarios para confirmar esta clase.");
			return false;
		}
	}

	public void validarYConfirmarClase(Clase clase) {
		// TO-DO AGREGAR VALIDACIONES DE EMPLAZAMIENTO, PROFESOR Y OTROS SI ES NECESARIO.
		if (validarRentabilidad(clase)) {
			if (validarArticulosNecesarios(clase, clase.getEjercicio())) {
				clase.confirmarClase();
			}
		}
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
		//System.out.println(newArticulo);
		//boolean flag =
				this.stockArticulos.add(newArticulo);
		//System.out.println("FLAG SE AGREGO: " + flag);
		//System.out.println(this.stockArticulos);
	}

	private void calcularDesgasteArticulos(Clase clase) {
		//System.out.println("PRE DESGASTE" + this.stockArticulos);
		for (Articulo articulo : clase.getListaArticulos())
			articulo.setDesgaste();

		//System.out.println("POST DESGASTE" + this.stockArticulos);

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
	public HashSet<Ejercicio> getEjerciciosDisponibles() {
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

	public ArrayList<Articulo> getStockArticulos() {
		return this.stockArticulos;
	}
    
    @Override
    public String toString() {
    	return "["+this.ubicacion+","+this.tipoNivel.toString()+"]";
    }


	public String[] getInfo() {
		String[] array = new String[7];
		array[0] = ubicacion;
		array[1] = tipoNivel.toString();
		array[2] = String.valueOf(stockArticulos.size());
		array[3] = emplazamientosDisponibles.toString();
		array[4] = String.valueOf(listaClases.size());
		array[5] = ejerciciosDisponibles.toString();
		array[6] = String.valueOf(alquilerSede);
		return array;
	}

	public ArrayList<Emplazamiento> getEmplazamientosDisponibles() {
		return emplazamientosDisponibles;
	}

	public Clase getClase(int claseId) throws GymException {
		for (Clase clase : listaClases)
			if (clase.getClaseID() == claseId)
				return clase;

		throw new GymException("Clase no existe.");
	}
	public void cambiarEstadoClase(int claseId, String estado) throws GymException {
		Clase clase = getClase(claseId);
		switch (estado) {
			case "Finalizada" -> finalizarClase(clase);
			case "Agendada" -> clase.setEstadoAgendada();
			case "Confirmada" -> validarYConfirmarClase(clase);
		}
	}
	public HashMap<LocalDate, ArrayList<String[]>> getHorariosClasesAsignadasProfesor(Profesor profesor) {
		/// TOMA LA SIGUIENTE SEMANA Y RETORNA UN HASHMAP. CLAVE ES EL LOCALDATE Y LOS VALUES SON ARRAYS DE LOCALTIME
		//  QUE SON EL HORARIO DE INICIO DE LA CLASE.
		HashMap<LocalDate, ArrayList<String[]>> horariosAsignados = new HashMap<>();
		ArrayList<Clase> listaClases = this.getClasesProfesor(profesor);

		ArrayList<LocalDate> semana = new ArrayList<>();
		for (int day=0; day<7; day++) {	semana.add(LocalDate.now().plusDays(day));	}

		for (LocalDate dia : semana) {
			ArrayList<String[]> horarios = new ArrayList<>();
			for (Clase clase : listaClases) {
				if ( dia.isEqual(clase.getFecha()) ) {
					ArrayList<String> claseinfo = clase.getInfo();
					claseinfo.add(0, this.ubicacion);
					horarios.add( claseinfo.toArray(new String[0]) );
				}

			}
			horariosAsignados.put(dia,horarios);
		}
		return horariosAsignados;
	}
}
