package model.logic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import com.google.gson.Gson;

import model.data_structures.ListaEncadenada;
import model.data_structures.Node;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	/**
	 * Atributos del modelo del mundo
	 */

	private ListaEncadenada<String, Multa> lista;

	private PrimeraClase prClase;
	private final static String path2 = "./data/Comparendos_DEI_2018_Bogotá_D.C_small.geojson";
	private final static String path = "./data/Comparendos_DEI_2018_Bogotá_D.C.geojson";

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo() {
		Gson gson = new Gson();
		lista = new ListaEncadenada<String, Multa>();
		try {
			FileInputStream inputStream = new FileInputStream(path);
			InputStreamReader ISReader = new InputStreamReader(inputStream);
			BufferedReader bf = new BufferedReader(ISReader);
			PrimeraClase pc = gson.fromJson(bf, PrimeraClase.class);
			prClase = pc;
			System.out.println(pc);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Se crea la lista con base en los datos cargados
	 */

	public void crearLista() {
		Multa[] multas = prClase.getFeatures();
		for (Multa multa : multas) {
			agregar(multa.getProperties().getFecha(), multa);
		}
	}

	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo
	 * 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamanoLista() {
		return lista.darTamano();
	}

	/**
	 * Requerimiento de agregar dato
	 * 
	 * @param dato
	 */

	public void agregar(String llave, Multa dato) {

		lista.agregarElemento(llave, dato);
	}

	/**
	 * Requerimiento eliminar dato
	 * 
	 * @param dato
	 *            Dato a eliminar
	 * @return dato eliminado
	 */

	public void eliminar(Multa dato) {
		lista.eliminarElemento(dato);
	}

	public Multa getMultaMayorOBID() {
		return lista.darUltimoElemento();
	}

}