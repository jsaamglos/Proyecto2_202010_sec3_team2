package model.data_structures;

import java.util.Iterator;

public interface ITablaOrdenada<K, T> {

	public int size();

	public boolean isEmpty();

	public void agregarDato(K llave, T dato);

	public T obtenerDato(K llave);

	public T eliminarDato(K llave);

	public Iterable<K> keys();
}
