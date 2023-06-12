package ar.edu.uade.gym;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import ar.edu.uade.usuarios.Usuario;
import ar.edu.uade.usuarios.Cliente;
import ar.edu.uade.usuarios.SoporteTecnico;
import ar.edu.uade.usuarios.Profesor;
import ar.edu.uade.usuarios.Administrativo;
import ar.edu.uade.articulos.Articulo;
import ar.edu.uade.articulos.TipoArticulo;
import ar.edu.uade.bbdd.BaseDeDatos;

public class CadenaGimnasio {
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

    private CadenaGimnasio(String nombre) {
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

    public static CadenaGimnasio getInstance() {
        if (instancia == null) {
            instancia = new CadenaGimnasio("Supertlon");
        }
        return instancia;
    }

    public void eliminarClasesVirtualesAntiguas() {
        // TO-DO Codigo que limpia las clases viejas de la bbdd.
    }
    
    public void agregarUsuario(Usuario usuario) {
    	
    }
    
    public ArrayList<Usuario> getListaUsuarios() {
    	ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
    	listaUsuarios.addAll(usuariosSoporteTecnico);
    	listaUsuarios.addAll(usuariosAdministrativo);
    	listaUsuarios.addAll(usuariosClientes);
    	listaUsuarios.addAll(usuariosProfesores);
    	return listaUsuarios;
    }
    
    public Usuario getUsuario(int id) throws GymException {
    	ArrayList<Usuario> listaUsuarios = getListaUsuarios();
    	
    	for (Usuario user: listaUsuarios) 
    		if (id == user.getID()) 
    			return user;
    	
		throw new GymException("User Not Found.");
    }
    
    
    public String getNombre() {
    	return this.nombre;
    }
    
    /* =======================================================
     *                     METODOS DE SEDE 
     * =======================================================
     */
    
    private boolean sedeYaExiste(String ubicacion) {
    	boolean flagSedeFound = false;
    	for (Sede sede: sedes) 
    		if (sede.getUbicacion().equals(ubicacion.toLowerCase()))
    			flagSedeFound = true;
    	return flagSedeFound;
    }
    
    public void agregarSede(String ubicacion, TipoNivel tipoNivel, ArrayList<Emplazamiento> emplazamientos, 
    		ArrayList<Ejercicio> ejerciciosDisponibles) throws GymException {
    	if (!sedeYaExiste(ubicacion)) {
    		Sede newSede = new Sede(ubicacion, tipoNivel, emplazamientos, ejerciciosDisponibles);
    		this.sedes.add(newSede);
    	} else {
    		throw new GymException("La sede ya existe.");
    	}
    }
    
    public ArrayList<Sede> getListaSedes() {
    	return this.sedes;
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
    
    public void agregarEjercicio(String nombre, boolean puedeSerVirtual, 
    		ArrayList<TipoArticulo> listaArticulosNecesarios) throws GymException {
    	if (!ejercicioYaExiste(nombre)) {
        	Ejercicio newEjercicio = new Ejercicio(nombre, puedeSerVirtual, listaArticulosNecesarios);
        	this.ejercicios.add(newEjercicio);
    	} else {
    		throw new GymException("El ejercicio ya existe.");
    	}
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


    
    /* =======================================================
     *                    METODOS DE CLASES 
     * =======================================================
     */
    
    //public String getListaClases(Sede sede) {
    //	return this.sede.getClases();
    //}
    
    //public String getStringListaClases() {
    //	return Arrays.toString(this.sedes.toArray());
    //}
    
    /* =======================================================
     *                    METODOS DE ARTICULOS 
     * =======================================================
     */
	public ArrayList<TipoArticulo> getCatalogoDeArticulos() {
		return catalogoDeArticulos;
	}
	
	public void agregarTipoArticuloPorFecha(String nombre, String marca, String descripcion, int diasAmortizacion) {
		boolean flagAmortizacionPorFecha = true;
	    TipoArticulo newTipoArticulo = new TipoArticulo(nombre, marca, descripcion, flagAmortizacionPorFecha, diasAmortizacion);
		this.catalogoDeArticulos.add(newTipoArticulo);
	}
	
	public void agregarTipoArticuloPorUso(String nombre, String marca, String descripcion, int usosAmortizacion) {
		boolean flagAmortizacionPorFecha = false;
		TipoArticulo newTipoArticulo = new TipoArticulo(nombre, marca, descripcion, flagAmortizacionPorFecha, usosAmortizacion);
		this.catalogoDeArticulos.add(newTipoArticulo);
	}

	public void agregarArticulo(Sede sede, TipoArticulo tipoArticulo, Date fechaCompra, Date fechaFabricacion) {
		Articulo newArticulo = new Articulo(tipoArticulo, fechaCompra, fechaFabricacion);
		sede.agregarArticulo(newArticulo);
	}
    
}