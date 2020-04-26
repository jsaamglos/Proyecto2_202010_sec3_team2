package model.data_structures;

public interface IArbolRojoNegro<K, T> {
	public int getSize();

	public boolean isEmpty();

	public T get(K llave);

	public K min();

	public K max();

	public boolean contains(K llave);

	public void agregarDato(K llave, T dato);

	public int getAltura(K llave);

	public int altura();
}
