package ar.edu.uade.gym;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import ar.edu.uade.gym.articulos.CategoriaArticulo;
import ar.edu.uade.gym.bbdd.BaseDeDatos;
import ar.edu.uade.usuarios.Usuario;
import ar.edu.uade.usuarios.Cliente;
import ar.edu.uade.usuarios.SoporteTecnico;
import ar.edu.uade.usuarios.Profesor;
import ar.edu.uade.usuarios.Administrativo;
import ar.edu.uade.gym.articulos.Articulo;
import ar.edu.uade.gym.articulos.TipoArticulo;

public class CadenaGimnasio {
    public CadenaGimnasio(String nombre, ArrayList<SoporteTecnico> usuariosSoporteTecnico,
			ArrayList<Administrativo> usuariosAdministrativo, ArrayList<Cliente> usuariosClientes,
			ArrayList<Profesor> usuariosProfesores, ArrayList<TipoArticulo> catalogoDeArticulos, ArrayList<Sede> sedes,
			ArrayList<Ejercicio> ejercicios, BaseDeDatos baseDeClasesVirtuales) {
		this.nombre = nombre;
		this.usuariosSoporteTecnico = usuariosSoporteTecnico;
		this.usuariosAdministrativo = usuariosAdministrativo;
		this.usuariosClientes = usuariosClientes;
		this.usuariosProfesores = usuariosProfesores;
		this.catalogoDeArticulos = catalogoDeArticulos;
		this.sedes = sedes;
		this.ejercicios = ejercicios;
		this.baseDeClasesVirtuales = baseDeClasesVirtuales;
	}

	private static CadenaGimnasio instancia;
    private String nombre;
    private ArrayList<SoporteTecnico> usuariosSoporteTecnico;
    private ArrayList<Administrativo> usuariosAdministrativo;
    private ArrayList<Cliente> usuariosClientes;
    private ArrayList<Profesor> usuariosProfesores;
    private ArrayList<TipoArticulo> catalogoDeArticulos;
    private ArrayList<Sede> sedes;
    private ArrayList<Ejercicio> ejercicios;
    private BaseDeDatos baseDeClasesVirtuales;

    public CadenaGimnasio(String nombre) {
        this.nombre = nombre;
        this.usuariosSoporteTecnico = new ArrayList<SoporteTecnico>();
        this.usuariosAdministrativo = new ArrayList<Administrativo>();
        this.usuariosClientes = new ArrayList<Cliente>();
        this.usuariosProfesores = new ArrayList<Profesor>();
        this.catalogoDeArticulos = new ArrayList<TipoArticulo>();
        this.sedes = new ArrayList<Sede>();
        this.ejercicios = new ArrayList<Ejercicio>();
        this.baseDeClasesVirtuales = new BaseDeDatos();
    }

    public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "CadenaGimnasio [nombre=" + nombre + ", usuariosSoporteTecnico=" + usuariosSoporteTecnico
				+ ", usuariosAdministrativo=" + usuariosAdministrativo + ", usuariosClientes=" + usuariosClientes
				+ ", usuariosProfesores=" + usuariosProfesores + ", catalogoDeArticulos=" + catalogoDeArticulos
				+ ", sedes=" + sedes + ", ejercicios=" + ejercicios + ", baseDeClasesVirtuales=" + baseDeClasesVirtuales
				+ "]";
	}
    
    
	public static CadenaGimnasio getInstance() {
        if (instancia == null) {
            instancia = new CadenaGimnasio("Supertlon");
        }
        return instancia;
    }

    public String getNombre() {
    	return this.nombre;
    }

    public void eliminarClasesVirtualesAntiguas() {
        // TO-DO Codigo que limpia las clases viejas de la bbdd.
    }
   
    /* =======================================================
     *                     METODOS DE USUARIOS 
     * =======================================================
     */
    
    public ArrayList<Usuario> getListaUsuarios() {
    	ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
    	listaUsuarios.addAll(this.usuariosAdministrativo);
    	listaUsuarios.addAll(this.usuariosClientes);
    	listaUsuarios.addAll(this.usuariosProfesores);
    	listaUsuarios.addAll(this.usuariosSoporteTecnico);
    	return listaUsuarios;
    }
    
    public void agregarUsuario(Usuario usuario) {
     	if (usuario.soyAdministrativo())
     		usuariosAdministrativo.add((Administrativo) usuario);
    	if (usuario.soyCliente())
     		usuariosClientes.add((Cliente) usuario);
    	if (usuario.soySoporteTecnico())
     		usuariosSoporteTecnico.add((SoporteTecnico) usuario);
    	if (usuario.soyProfesor())
     		usuariosProfesores.add((Profesor) usuario);
    }

	public void asignarSede(Administrativo admin, Sede sedeNueva) {
		if (!(admin.getSedesAsignadas().contains(sedeNueva)))
			admin.asignarSede(sedeNueva);
		else {
			System.out.println("La sede ya estaba asignada.");
		}
	}
    
    public Usuario getUsuario(int id) throws GymException {
    	ArrayList<Usuario> listaUsuarios = getListaUsuarios();
    	
    	for (Usuario user: listaUsuarios) 
    		if (id == user.getID()) 
    			return user;
    	
		throw new GymException("User Not Found.");
    }


	public Usuario getUsuario(String nombre,  String contrasenia) throws GymException {
		ArrayList<Usuario> listaUsuarios = getListaUsuarios();

		for (Usuario user: listaUsuarios)
			if (nombre.equals(user.getNombre()) && contrasenia.equals(user.getContrasenia()))
				return user;

		throw new GymException("Los datos ingresados no son correctos.");
	}

	public Administrativo getAdministrativo(int id) throws GymException{
    	for (Administrativo user: this.usuariosAdministrativo) 
    		if (id == user.getID()) 
    			return user;
    	
		throw new GymException("User Not Found.");
	}
	
	public Cliente getCliente(int id) throws GymException{
    	for (Cliente user: this.usuariosClientes) 
    		if (id == user.getID()) 
    			return user;
    	
		throw new GymException("User Not Found.");
	}

	public Profesor getProfesor(int id) throws GymException{
    	for (Profesor user: this.usuariosProfesores) 
    		if (id == user.getID()) 
    			return user;
    	
		throw new GymException("User Not Found.");
	}
    
	public SoporteTecnico getSoporteTecnico(int id) throws GymException{
    	for (SoporteTecnico user: this.usuariosSoporteTecnico) 
    		if (id == user.getID()) 
    			return user;
    	
		throw new GymException("User Not Found.");
	}
	
    /* =======================================================
     *                     METODOS DE SEDE 
     * =======================================================
     */

	private boolean sedeYaExiste(String ubicacion) {
		for (Sede sede : sedes)
			if (sede.getUbicacion().equals(ubicacion.toLowerCase())) {
				return true;
			}
    	return false;
    }

    public void agregarSede(String ubicacion, TipoNivel tipoNivel, ArrayList<Emplazamiento> emplazamientos, 
    		ArrayList<Ejercicio> ejerciciosDisponibles, double alquilerSede) throws GymException {
    	if (!sedeYaExiste(ubicacion)) {
    		Sede newSede = new Sede(ubicacion, tipoNivel, emplazamientos, ejerciciosDisponibles, alquilerSede);
    		this.sedes.add(newSede);
    	} else {
    		throw new GymException("La sede ya existe.");
    	}
    }
    
    public ArrayList<Sede> getListaSedes() {
    	return this.sedes;
    }

	public ArrayList<String> getListaNombresSedes() {
		ArrayList<String> stringSedes = new ArrayList<String>();
		for (Sede sede : this.sedes)
			stringSedes.add(sede.getUbicacion());
		return stringSedes;
	}

    public Sede getSede(String ubicacion) {
    	for (Sede sede: sedes) 
    		if (sede.getUbicacion().equals(ubicacion.toLowerCase())) 
    			return sede;
    	
    	return null;
    }
    
    /* =======================================================
     *                  METODOS DE EJERCICIOS 
     * =======================================================
     */
    private boolean ejercicioYaExiste(String nombre) {
    	boolean flagEjercicioFound = false;
    	for (Ejercicio ejercicio: ejercicios) 
    		if (ejercicio.getNombre().equals(nombre.toLowerCase()))
    			flagEjercicioFound = true;
    	return flagEjercicioFound;
    }
    
    public void agregarEjercicio(String nombre, boolean puedeSerVirtual, int maxClasesVirtGuardadas,
    		ArrayList<TipoArticulo> listaArticulosNecesarios) throws GymException {
    	if (!ejercicioYaExiste(nombre)) {
        	Ejercicio newEjercicio = new Ejercicio(nombre, puedeSerVirtual, maxClasesVirtGuardadas ,listaArticulosNecesarios);
        	this.ejercicios.add(newEjercicio);
    	} else {
    		throw new GymException("El ejercicio ya existe.");
    	}
    }
    
    public void agregarEjercicio(String nombre, boolean puedeSerVirtual, int maxClasesVirtGuardadas,
    		TipoArticulo articuloNecesario) throws GymException {
    	ArrayList<TipoArticulo> listaArticulosNecesarios = new ArrayList<TipoArticulo>();
    	listaArticulosNecesarios.add(articuloNecesario);
    	agregarEjercicio(nombre, puedeSerVirtual, maxClasesVirtGuardadas ,listaArticulosNecesarios);
    }
    
    public ArrayList<Ejercicio> getListaEjercicios() {
    	return this.ejercicios;
    }

    public String getStringListaEjercicios() {
    	return Arrays.toString(this.ejercicios.toArray());
    }
    
    
    public Ejercicio getEjercicio(String nombre) {
    	for (Ejercicio ejercicio: ejercicios) 
    		if (ejercicio.getNombre().equals(nombre.toLowerCase()))
    			return ejercicio;
    	return null;
    }

	public void agregarEjerciciosDisponibles(Sede sede, Ejercicio newEjercicio) {
		sede.agregarEjerciciosDisponibles(newEjercicio);
	}
    
    /* =======================================================
     *                    METODOS DE CLASES 
     * =======================================================
     */
    
    public void agendarClase(Sede sede, Profesor profesor, Ejercicio ejercicio, ArrayList<Cliente> listaAlumnos,
							 LocalDate fecha, LocalTime horarioInicio, Emplazamiento emplazamiento,
							 ArrayList<Articulo> listaArticulos, boolean esVirtual) throws GymException {
		sede.agregarClase(profesor, ejercicio, listaAlumnos, fecha, horarioInicio, emplazamiento, listaArticulos, esVirtual);
    }

	public void asignarProfesorClase(Sede sede, Clase clase ,Usuario profesor) throws GymException {
		sede.asignarProfesor(clase,profesor);
	}

	public HashMap<LocalDate, ArrayList<LocalTime>> getHorariosClasesAsignadasProfesor(String ubicacionSede, Profesor profesor) {
	/// TOMA LA SIGUIENTE SEMANA Y RETORNA UN HASHMAP. CLAVE ES EL LOCALDATE Y LOS VALUES SON ARRAYS DE LOCALTIME
	//  QUE SON EL HORARIO DE INICIO DE LA CLASE.
		Sede sede = getSede(ubicacionSede);
		HashMap<LocalDate, ArrayList<LocalTime>> horariosAsignados = new HashMap<>();
		ArrayList<Clase> listaClases =  sede.getClasesProfesor(profesor);

		ArrayList<LocalDate> semana = new ArrayList<>();
		for (int day=0; day<7; day++) {	semana.add(LocalDate.now().plusDays(day));	}

		for (LocalDate dia : semana) {
			ArrayList<LocalTime> horarios = new ArrayList<>();
			for (Clase clase : listaClases) {
				if ( dia.isEqual(clase.getFecha()) )
					horarios.add(clase.getHorarioInicio());
			}
			horariosAsignados.put(dia,horarios);
		}
		return horariosAsignados;
	}

    /* =======================================================
     *                    METODOS DE ARTICULOS 
     * =======================================================
     */
	public ArrayList<TipoArticulo> getCatalogoDeArticulos() {
		return catalogoDeArticulos;
	}

	public void eliminarArticulo(Sede sede, Articulo articulo) {
		sede.eliminarArticulo(articulo);
	}

	public void darDeBajaArticulo(Sede sede, Articulo articulo) {
		sede.darDeBajaArticulo(articulo);
	}

	public void agregarTipoArticuloPorFecha(String nombre, CategoriaArticulo articulo, String marca, String descripcion, int diasAmortizacion) {
		boolean flagAmortizacionPorFecha = true;
	    TipoArticulo newTipoArticulo = new TipoArticulo(nombre, articulo, marca, descripcion, flagAmortizacionPorFecha, diasAmortizacion);
		this.catalogoDeArticulos.add(newTipoArticulo);
	}
	
	public void agregarTipoArticuloPorUso(String nombre, CategoriaArticulo articulo, String marca, String descripcion, int usosAmortizacion) {
		boolean flagAmortizacionPorFecha = false;
		TipoArticulo newTipoArticulo = new TipoArticulo(nombre, articulo, marca, descripcion, flagAmortizacionPorFecha, usosAmortizacion);
		this.catalogoDeArticulos.add(newTipoArticulo);
	}

	public void agregarArticulo(Sede sede, TipoArticulo tipoArticulo, double precio, LocalDate fechaCompra, LocalDate fechaFabricacion) {
		Articulo newArticulo = new Articulo(tipoArticulo, precio, fechaCompra, fechaFabricacion);
		sede.agregarArticulo(newArticulo);
	}

	public void crearEmplazamiento(Sede sede, TipoEmplazamiento tipoEmplazamiento, int capacidad, int metrosCuadrados) {
		sede.crearEmplazamiento(tipoEmplazamiento, capacidad, metrosCuadrados);
	}

	public ArrayList<Emplazamiento> getListaEmplazamientos(Sede sede, TipoEmplazamiento tipoEmplazamiento) {
		return sede.getListaEmplazamientos(tipoEmplazamiento);
	}

	public void finalizarClase(Sede sede, Clase clase) {
		sede.finalizarClase(clase);
		this.baseDeClasesVirtuales.agregarClaseVirtual(clase);
	}

	public ArrayList<Clase> getClasesVirtualesAlmacenadas() {
		return this.baseDeClasesVirtuales.getClasesVirtuales();
	}

	public void llenarGym() {
		try {

			// AGREGAR USUARIO
			Usuario admin = new Administrativo("admin","admin");
			Usuario cliente1 = new Cliente("cliente","cliente", TipoNivel.PLATINUM);
			Usuario cliente2 = new Cliente("ramona","flowers", TipoNivel.BLACK);
			Usuario profe = new Profesor("profe","profe",  50000);
			Usuario soporte = new SoporteTecnico("soporte", "soporte");

			this.agregarUsuario(admin);
			this.agregarUsuario(cliente1);
			this.agregarUsuario(cliente2);
			this.agregarUsuario(profe);
			this.agregarUsuario(soporte);



			// AGREGAR SEDE
			this.agregarSede("Caballito", TipoNivel.BLACK, null, null, 80000);
			this.agregarSede("Belgrano", TipoNivel.ORO, null, null, 100000);
			this.agregarSede("Palermo", TipoNivel.PLATINUM, null, null, 120000);
			

			
			// AGREGAR ARTICULOS
			Sede sedeBelgrano = this.getSede("Belgrano");
			
			this.agregarTipoArticuloPorFecha("Colchoneta", CategoriaArticulo.COLCHONETA,"Pepito", "Colchoneta de 2m x 0.75m", 200);
			this.agregarTipoArticuloPorUso("Pesa", CategoriaArticulo.PESA,"Pepito", "Pesa marca Pepito de 20kg", 50);

			TipoArticulo tipoArticulo = this.getCatalogoDeArticulos().get(0);

			LocalDate fechaFabricacion = LocalDate.of(2023,5,10);
			LocalDate fechaCompra = LocalDate.of(2023,6,10);

			this.agregarArticulo(sedeBelgrano, tipoArticulo, 2000 , fechaCompra, fechaFabricacion);
			this.agregarArticulo(sedeBelgrano, tipoArticulo, 1500 , fechaCompra, fechaFabricacion);

			
			//AGREGAR EJERCICIO
			TipoArticulo tipoArticulo1 = this.getCatalogoDeArticulos().get(0);
			TipoArticulo tipoArticulo2 = this.getCatalogoDeArticulos().get(1);
			this.agregarEjercicio("Crossfit", true, 10, tipoArticulo1);
			this.agregarEjercicio("Yoga", true, 15 ,tipoArticulo2);
			
			//EMPLAZAMIENTO
			this.crearEmplazamiento(sedeBelgrano,TipoEmplazamiento.SALON, 25, 30);
			this.crearEmplazamiento(sedeBelgrano,TipoEmplazamiento.PILETA, 40, 50);
			
			//CLASE
			// Invento dos alumnos, uno con nivel suficiente y otro no.
			ArrayList<Cliente> listaAlumnos = new ArrayList<Cliente>();
			
			Cliente cliente4 = new Cliente("Ivan","rodriguez", TipoNivel.PLATINUM);
			this.agregarUsuario(cliente4);
			listaAlumnos.add(cliente4);

			//Cliente cliente2 = new Cliente("Sabrina", TipoNivel.BLACK);
			//this.agregarUsuario(cliente2);
			//listaAlumnos.add(cliente2);

			// Agendo la clase
			Profesor profesor = this.getProfesor(3);
			Ejercicio ejercicio = this.getEjercicio("Crossfit");
			LocalDate fecha = LocalDate.of(2023,7,1);
			LocalTime horarioInicio = LocalTime.of(19,0,0);
			LocalTime horarioFin = LocalTime.of(20,0,0);
			ArrayList<Emplazamiento> listaEmplazamientos = this.getListaEmplazamientos(sedeBelgrano, TipoEmplazamiento.SALON);
			Emplazamiento emplazamiento = listaEmplazamientos.get(0);
			ArrayList<Articulo> listaArticulos = null;
			boolean esVirtual = true;
			this.agendarClase(sedeBelgrano, profesor, ejercicio, listaAlumnos, LocalDate.of(2023,6,29), LocalTime.of(19,0,0), emplazamiento, listaArticulos, esVirtual);
			this.agendarClase(sedeBelgrano, profesor, ejercicio, listaAlumnos, LocalDate.of(2023,7,1), LocalTime.of(20,0,0), emplazamiento, listaArticulos, esVirtual);
			this.agendarClase(sedeBelgrano, profesor, ejercicio, listaAlumnos, LocalDate.of(2023,7,2), LocalTime.of(21,0,0), emplazamiento, listaArticulos, esVirtual);
			this.agendarClase(sedeBelgrano, profesor, ejercicio, listaAlumnos, LocalDate.of(2023,7,4), LocalTime.of(15,0,0), emplazamiento, listaArticulos, esVirtual);
			this.agendarClase(sedeBelgrano, profesor, ejercicio, listaAlumnos, LocalDate.of(2023,7,8), LocalTime.of(19,0,0), emplazamiento, listaArticulos, esVirtual);

			
			//ALMACENAR CLASE EN BBDD
			ArrayList<Clase> listaClases = sedeBelgrano.getListaClases();
			Clase clase = listaClases.get(0);
			this.finalizarClase(sedeBelgrano, clase);
			
			ArrayList<Clase> clasesVirtuales = this.getClasesVirtualesAlmacenadas();
			
			//Agregar Ejercicio
			Ejercicio ejercicio1 = this.getEjercicio("Crossfit");
			this.agregarEjerciciosDisponibles(sedeBelgrano, ejercicio1);
			
		} catch (GymException e) {
			e.printStackTrace();
		}
	}

	public double getSueldo(Profesor profesor) {
		return profesor.getSueldo();
	}

	public ArrayList<String> getEjerciciosDisponiblesSede(String ubicacionSede) {
		Sede sede = getSede(ubicacionSede);
		ArrayList<String> ejerciciosSede = new ArrayList<String>();
		for (Ejercicio ejercicio : sede.getEjerciciosDisponibles())
			ejerciciosSede.add(ejercicio.getNombre());
		return ejerciciosSede;
	}
}