package model.data_structures;

public class ListaEncadenada<K, T> implements IListaEncadenada<K,T> {
	// apuntador a primer elemento
	private Node<K, T> primerElemento;

	// apuntador a ultimo elemento
	private Node<K, T> ultimoElemento;

	// tamano de la lista
	private int tamano;

	// constructor
	public ListaEncadenada() {
		tamano = 0;
	}

	// agrega un elemento al final de la lista
	public void agregarElemento(K llave, T dato) {

		if (primerElemento == null) {
			Node<K, T> nodo = new Node<K, T>(llave, dato, null);
			// agrega el nodo como ultimo y primer elemento
			primerElemento = nodo;
			ultimoElemento = nodo;
		} else {
			// pone el elemento al final de la fila
			ultimoElemento = new Node<K, T>(llave, dato, ultimoElemento);
		}

		// incrementa el tamano
		tamano++;
	}

	// retorna el tamano de la lista
	public int darTamano() {
		return tamano;
	}

	// retorna el elemento dado por parametro
	public boolean existeElemento(T dato) {
		// crea nodo igual al primer elemento
		Node<K, T> nodo = primerElemento;

		// itera los elementos hasta que se acaben o encuentre el elemento
		while (nodo != null && !nodo.getElemento().equals(dato)) {
			nodo = nodo.getSiguiente();
		}

		// retorna true si encontro el elemento
		return nodo != null;
	}

	public void eliminarElemento(T dato) {
		// crea nodo igual al primer elemento
		Node<K, T> nodo = primerElemento;

		// itera los elementos hasta que se acaben o encuentre el elemento
		while (nodo != null && !nodo.getElemento().equals(dato)) {
			nodo = nodo.getSiguiente();
		}

		// se encontro el nodo?
		if (nodo != null) {
			// borra el nodo de la existencia
			nodo.getAnterior().setSiguiente(nodo.getSiguiente());
			nodo.getSiguiente().setAnterior(nodo.getAnterior());

			// baja en uno el tamano
			tamano--;

		} else {
			// Bruh, no esta el nodo
			System.out.println("No se encontro el elemento a eliminar.");
		}

	}

	public T darElemento(int index) {
		T elemento = null;
		if (index < tamano) {
			Node<K, T> n = primerElemento;
			for (int i = 0; i < index + 1;) {
				n = n.getSiguiente();
			}
			elemento = n.getElemento();
		}
		return elemento;
	}

	// existen elementos en la lista?
	public boolean isEmpty() {
		return tamano == 0;
	}

	public Node<K, T> darPrimeraPosicion() {
		return primerElemento;
	}

	public Node<K, T> darUltimaPosicion() {
		return ultimoElemento;
	}

	// retorna primer elemento
	public T darPrimerElemento() {
		return primerElemento.getElemento();
	}

	// retorna ultimo elemento
	public T darUltimoElemento() {
		return ultimoElemento.getElemento();
	}

}