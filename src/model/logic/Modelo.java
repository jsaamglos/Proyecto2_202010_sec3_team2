package model.logic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.Gson;

import model.data_structures.ArbolRojoNegro;
import model.data_structures.HeapMax;
import sun.util.resources.CalendarData;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	/**
	 * Atributos del modelo del mundo
	 */

	private ArbolRojoNegro<Date, Multa> arbol = new ArbolRojoNegro<Date, Multa>();
	private HeapMax<Multa> heap = new HeapMax<Multa>();
	private int mayorOBID;
	private PrimeraClase prClase;
	private final static String path3 = "./data/Comparendos_DEI_2018_Bogotá_D.C_small_50000_sorted.geojson";
	private final static String path2 = "./data/Comparendos_DEI_2018_Bogotá_D.C_small.geojson";
	private final static String path = "./data/Comparendos_DEI_2018_Bogotá_D.C.geojson";

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo() {
		Gson gson = new Gson();
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

	public void crearArbol() {
		Multa[] multas = prClase.getFeatures();
		mayorOBID = 0;
		if (arbol != null) {
			System.out.println("Tamaño multas en Archivo" + multas.length);
			for (Multa multa : multas) {
				arbol.agregarDato(multa.getProperties().getFecha(), multa);

				if (mayorOBID < multa.getProperties().getId()) {
					mayorOBID = multa.getProperties().getId();
				}
			}
		} else {
			System.out.println("No se ha inicializado.");
		}
	}

	public void crearHeapMax() {
		Multa[] multas = prClase.getFeatures();
		for (Multa multa : multas) {
			heap.agregar(multa);
		}
	}

	public int darTamanoHeap() {
		return heap.getSize();
	}

	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo
	 * 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamanoArbol() {
		return arbol.getSize();
	}

	/**
	 * Requerimiento de agregar dato
	 * 
	 * @param dato
	 */

	public void agregar(Date llave, Multa dato) {

		arbol.agregarDato(llave, dato);
	}

	public int darMayorObjectID() {

		return heap.getMax().getProperties().getId();

	}

	// Requerimiento 1A

	public String comparendosMayorGravedad(int m) {
		String resp = "\n";
		HeapMax<Multa> aux = new HeapMax<Multa>();
		HeapMax<Multa> respaldo = new HeapMax<Multa>();
		HeapMax<Multa> original = new HeapMax<Multa>();
		int size = heap.getSize();
		for (int i = 0; i < size; i++) {
			Multa max = heap.getMax();
			respaldo.agregar(max);
			original.agregar(max);
		}
		heap = original;
		int sizeNueva = respaldo.getSize();
		while (sizeNueva > 0) {
			Multa actual = respaldo.removeMax();
			if (actual != null) {
				actual.cambiarComparacion(2);
			}
			aux.agregar(actual);
			sizeNueva--;
		}

		for (int i = 0; i <= m; i++) {
			resp += aux.removeMax().getProperties().toString() + "\n";
		}
		return resp;
	}

	public String textoMultas(Multa[] x) {
		String resp = "\n";
		for (int i = 0; i < x.length; i++) {
			resp += x[i].getProperties().toString() + "\n";
		}
		return resp;

	}
	// Requerimiento 2A

	public String comparendosPorFecha(int pMes, int pDia) {
		HeapMax<Multa> aux = new HeapMax<Multa>();
		HeapMax<Multa> nueva = new HeapMax<Multa>();
		HeapMax<Multa> vieja = new HeapMax<Multa>();
		int size = heap.getSize();
		for (int i = 0; i < size; i++) {
			Multa max = heap.getMax();
			nueva.agregar(max);
			vieja.agregar(max);
		}
		heap = vieja;
		
		int tamaño=nueva.getSize();
		
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		while(tamaño>0){
			Multa actual=nueva.removeMax();
			Calendar cal=Calendar.getInstance();
			cal.
			if () {
				
			}
		}
		return null;
	}
}