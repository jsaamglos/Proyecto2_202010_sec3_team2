package model.data_structures;

import java.util.Iterator;

import sun.misc.Queue;

public class TablaOrdenada<K, T> implements ITablaOrdenada<K, T> {

	private ListaEncadenada[] buckets;
	private int cantBuckets = 1000;

	private int size;

	public TablaOrdenada() {
		buckets = new ListaEncadenada[cantBuckets];
		for (ListaEncadenada listaEncadenada : buckets) {
			listaEncadenada = new ListaEncadenada<K, T>();
		}
		size = 0;
	}

	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	private int darIndiceBucket(K llave) {
		int hashCode = llave.hashCode();
		int indice = hashCode % cantBuckets;
		return indice;
	}

	@Override
	public void agregarDato(K llave, T dato) {
		int indiceBucket = darIndiceBucket(llave);
		NodeTabla<K, T> actual = buckets[indiceBucket].darPrimeraPosicion();
		while (actual != null) {
			if (actual.getLlave().equals(llave)) {
				actual.setElemento(dato);
				return;
			}
			actual = actual.getSiguiente();
		}

		buckets[indiceBucket].agregarElemento(llave, dato);
		size++;

		// ReHash
		if ((1.0 * size) / cantBuckets >= 0.7) {
			ListaEncadenada[] temp = buckets;
			cantBuckets = cantBuckets * 2;
			buckets = new ListaEncadenada[cantBuckets];
			size = 0;
			for (ListaEncadenada listaEncadenada : buckets) {
				listaEncadenada = new ListaEncadenada<K, T>();
			}
			for (ListaEncadenada listaEncadenada : temp) {
				actual = listaEncadenada.darPrimeraPosicion();
				while (listaEncadenada != null) {
					agregarDato(actual.getLlave(), actual.getElemento());
					actual = actual.getSiguiente();
				}

			}

		}
	}

	public T obtenerDato(K llave) {
		int indiceBucket = darIndiceBucket(llave);
		NodeTabla<K, T> actual = buckets[indiceBucket].darPrimeraPosicion();
		while (actual != null) {
			if (actual.getLlave().equals(llave)) {
				return actual.getElemento();
			}
			actual = actual.getSiguiente();
		}
		// Si no se encuentra.
		return null;
	}

	public T eliminarDato(K llave) {
		int indiceBucket = darIndiceBucket(llave);
		NodeTabla<K, T> actual = buckets[indiceBucket].darPrimeraPosicion();
		NodeTabla<K, T> anterior = null;
		T dato = null;
		while (actual != null) {
			if (actual.getLlave().equals(llave)) {
				dato = actual.getElemento();
				break;
			}
			anterior = actual;
			actual = actual.getSiguiente();
		}
		if (actual == null)
			return null;
		size--;
		if (anterior != null) {
			anterior.setSiguiente(actual.getSiguiente());
		} else {
			buckets[indiceBucket].eliminarElemento(dato);
		}
		return dato;

	}

	@Override
	public Iterable<K> keys() {
		return null;
	}

}
