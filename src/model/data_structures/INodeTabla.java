package model.data_structures;

public interface INodeTabla<K,T> {

	/**
	 * Retorna el nodo siguiente
	 * 
	 * @return El nodo siguiente (null si no existe)
	 */
	public NodeTabla<K,T> getSiguiente();

	/**
	 * Retorna el nodo anterior
	 * 
	 * @return El nodo anterior (null si no existe)
	 */
	public NodeTabla<K,T> getAnterior();

	/**
	 * Establece la referencia al nodo dato
	 * 
	 * @param dato
	 *            el nodo que seria el siguiente a este.
	 */
	public void setSiguiente(NodeTabla<K,T> nodo);

	/**
		/**
	 * Establece la referencia al nodo dato
	 * 
	 * @param dato
	 *            el nodo que seria el anterior a este.
	 */
	public void setAnterior(NodeTabla<K,T> nodo);

	/**
	 * Retorna el elemento almacenado en este nodo
	 * 
	 * @return El elemento almacenado en este nodo
	 */
	public T getElemento();
	
	public K getLlave();
}