package model.data_structures;

public class Node<T>
{
	
	private T dato;

	/**
	 * Atributo que representa el siguiente nodo. 
	 */
	private Node<T> siguiente;

	//----------------------------------------------------------------
	//Constructor ----------------------------------------------------
	//----------------------------------------------------------------

	/**
	 *  Constructor de la clase Nodo. 
	 *  @param pdato dato con el cual se va inicializar el nodo.
	 */
	public Node(T pDato)
	{
		dato = pDato;
		siguiente = null;
	}

	//----------------------------------------------------------------
	//Metodos --------------------------------------------------------
	//----------------------------------------------------------------

	/**
	 * Retornar el elemento que esta en el nodo.
	 * @return el dato.
	 */
	public T darElemento()
	{
		return dato;
	}

	/**
	 * Retornar el siguiente nodo.
	 * @return Siguinete nodo.
	 */
	public Node<T> darSiguiente()
	{
		return siguiente;
	}

	/**
	 * Asigna al nodo actual un nodo siguiente que entra por parametro. 
	 * @param pNodo el nodo a asignar como siguiente.
	 */
	public void asignarSiguiente(Node<T> pNodo)
	{
		siguiente = pNodo;
	}
}