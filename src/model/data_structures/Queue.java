package model.data_structures;

import java.util.Iterator;

public class Queue<T extends Comparable<T>> implements IQueue<T> {

	private Node<T> primero;

	private int size;

	private Node<T> ultimo;

	public Queue() {
		size = 0;
		primero = null;
		ultimo = primero;
	}

	/**
	 * Añade un elemento al final de la fila.
	 * 
	 * @param dato
	 *            Elemento a añadir en la fila.
	 */
	public void enqueue(T dato) {
		Node<T> nuevo = new Node<T>(dato);
		if (isEmpty() == false) {
			ultimo.asignarSiguiente(nuevo);
			ultimo = nuevo;
		} else {
			primero = nuevo;
			ultimo = primero;
		}
		size++;
	}

	/**
	 * Quita el primer elemento y retorna el primer elemento de la fila.
	 * 
	 * @return Primer elemento de la cola.
	 * 
	 */
	public Node<T> dequeue() {
		if (isEmpty() == false) {
			Node<T> resp = primero;
			if (primero != ultimo) {
				primero = primero.darSiguiente();
			} else {
				primero = null;
				ultimo = null;
			}
			size--;
			return resp;
		}
		return null;
	}

	public T getElement() {
		if (primero != null) {
			return primero.darElemento();
		}
		return null;
	}

	/**
	 * Retorna el elemento que este de primeras en la fila
	 * 
	 * @return El elemento en la primera posición.
	 */
	public Node<T> getPrimero() {
		if (primero != null) {
			return primero;
		}
		return null;
	}

	/**
	 * Muestra si la fila esta vacia.
	 * 
	 * @return Verdadero si esta vacia, falso de lo contrario.
	 */
	public boolean isEmpty() {
		boolean resp = false;
		if (primero == null && ultimo == null) {
			resp = true;
		}
		return resp;
	}

	/**
	 * Retorna el numero de elementos en la fila
	 * 
	 * @return numero total de elementos.
	 */

	public int size() {
		return size;
	}

	public Iterator<T> iterator() {
		return null;
	}

}