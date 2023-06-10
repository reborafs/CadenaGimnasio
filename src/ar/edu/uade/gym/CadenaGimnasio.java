package ar.edu.uade.gym;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import ar.edu.uade.usuarios.Usuario;
import ar.edu.uade.usuarios.Cliente;
import ar.edu.uade.usuarios.SoporteTecnico;
import ar.edu.uade.usuarios.Profesor;
import ar.edu.uade.usuarios.Administrativo;
import ar.edu.uade.articulos.Articulo;
import ar.edu.uade.bbdd.BaseDeDatos;

public class CadenaGimnasio {
    private static CadenaGimnasio instancia;
    private String nombre;
    private ArrayList<SoporteTecnico> usuariosSoporteTecnico;
    private ArrayList<Administrativo> usuariosAdministrativo;
    private ArrayList<Cliente> usuariosClientes;
    private ArrayList<Profesor> usuariosProfesores;
    private ArrayList<Articulo> catalogoDeArticulos;
    private ArrayList<Sede> sedes;
    private BaseDeDatos baseDeClasesVirtuales;

    private CadenaGimnasio(String nombre) {
        this.nombre = nombre;
        this.usuariosSoporteTecnico = new ArrayList<SoporteTecnico>();
        this.usuariosAdministrativo = new ArrayList<Administrativo>();
        this.usuariosClientes = new ArrayList<Cliente>();
        this.usuariosProfesores = new ArrayList<Profesor>();
        this.catalogoDeArticulos = new ArrayList<Articulo>();
        this.sedes = new ArrayList<Sede>();
        this.baseDeClasesVirtuales = new BaseDeDatos();
    }

    public static CadenaGimnasio getInstance() {
        if (instancia == null) {
            instancia = new CadenaGimnasio("Supertlon");
        }
        return instancia;
    }

    public void eliminarClasesVirtualesAntiguas() {
        // Code for eliminating old virtual classes
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
     *                    METODOS DE CLASES 
     * =======================================================
     */
    
    //public String getListaClases(Sede sede) {
    //	return this.sede.getClases();
    //}
    
    //public String getStringListaClases() {
    //	return Arrays.toString(this.sedes.toArray());
    //}
    
}