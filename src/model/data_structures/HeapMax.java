package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*****************************************************************************
 * Compilation: javac MaxPQ.java Execution: java MaxPQ < input.txt Dependencies:
 * StdIn.java StdOut.java Data files:
 * https://algs4.cs.princeton.edu/24pq/tinyPQ.txt
 * 
 * Generic max priority queue implementation with a binary heap. Can be used
 * with a comparator instead of the natural order, but the generic Key type must
 * still be Comparable.
 *
 * % java MaxPQ < tinyPQ.txt Q X P (6 left on pq)
 *
 * We use a one-based array to simplify parent and child calculations.
 *
 * Can be optimized by replacing full exchanges with half exchanges (ala
 * insertion sort).
 *
 ******************************************************************************/
public class HeapMax<T extends Comparable<T>> implements IHeapMax<T> {

	/**
	 * Atributo que representa el primer elemento de la cola de prioridad.
	 */
	private T[] elementos;

	/**
	 * Atributo que hace referencia al tamaño.
	 */
	private int size;

	public HeapMax(int capacidad) {

		size = 0;
		elementos = (T[]) new Comparable[capacidad + 1];
	}

	public HeapMax(T[] lista) {
		size = lista.length;
		elementos = (T[]) new Comparable[lista.length + 1];
		for (int i = 0; i < size; i++)
			elementos[i + 1] = lista[i];
		for (int k = size / 2; k >= 1; k--)
			sink(k);
		assert isMaxHeap();
	}

	public HeapMax() {
		size = 0;
		elementos = (T[]) new Comparable[50];
	}

	public int getSize() {
		return size;
	}

	public void agregar(T elemento) {
		if (size == elementos.length - 1) {
			resize(2 * elementos.length);
		}

		elementos[++size] = elemento;
		swim(size);
		assert isMaxHeap();
	}

	public T removeMax() {
		if (isEmpty() == false) {
			T maximo = elementos[1];
			swap(1, size--);
			sink(1);
			elementos[size + 1] = null;

			if ((size > 0) && (size == (elementos.length - 1) / 4)) {
				resize(elementos.length / 2);
			}

			assert isMaxHeap();
			return maximo;
		}

		return null;
	}

	public T getMax() {
		if (isEmpty() == false) {
			return elementos[1];
		} else {
			return null;
		}
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	private void swim(int k) {
		while (k > 1 && less(k / 2, k)) {
			swap(k, k / 2);
			k = k / 2;
		}
	}

	private void sink(int k) {
		while (2 * k <= size) {
			int j = 2 * k;
			if (j < size && less(j, j + 1))
				j++;
			if (!less(k, j))
				break;
			swap(k, j);
			k = j;
		}
	}

	private boolean less(int i, int j) {
		return elementos[i].compareTo(elementos[j]) < 0;
	}

	private void swap(int i, int j) {
		T swap = elementos[i];
		elementos[i] = elementos[j];
		elementos[j] = swap;
	}

	private boolean isMaxHeap() {
		for (int i = 1; i <= size; i++) {
			if (elementos[i] == null)
				return false;
		}
		for (int i = size + 1; i < elementos.length; i++) {
			if (elementos[i] != null)
				return false;
		}
		if (elementos[0] != null)
			return false;
		return isMaxHeapOrdered(1);
	}

	private boolean isMaxHeapOrdered(int k) {
		if (k > size)
			return true;
		int left = 2 * k;
		int right = 2 * k + 1;
		if (left <= size && less(k, left))
			return false;
		if (right <= size && less(k, right))
			return false;
		return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
	}

	private void resize(int capacidad) {
		assert capacidad > size;
		T[] temp = (T[]) new Comparable[capacidad];
		for (int i = 1; i <= size; i++) {
			temp[i] = elementos[i];
		}
		elementos = temp;
	}

	public Iterator<T> iterator() {
		return new HeapIterator(elementos);
	}

	public class HeapIterator<T extends Comparable<T>> implements Iterator<T> {
		private HeapMax<T> duplicado;

		public HeapIterator(T[] elemenotos) {
			duplicado = new HeapMax<T>(elemenotos.length);

			for (int i = 1; i <= elemenotos.length; i++) {
				duplicado.agregar(elemenotos[i]);
			}
		}

		public boolean hasNext() {
			return !duplicado.isEmpty();
		}

		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return duplicado.getMax();
		}
	}
}
