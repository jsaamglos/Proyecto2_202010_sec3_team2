package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArbolRojoNegro<K extends Comparable<K>, T extends Comparable<T>> implements IArbolRojoNegro<K, T> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private NodoRojoNegro<K, T> raiz;

	int size;

	public ArbolRojoNegro() {
		raiz = null;
	}

	public int tamano() {
		return size;
	}

	public int size() {
		return size(raiz);
	}

	public int getSize() {
		return size(raiz);
	}

	int getSize(NodoRojoNegro<K, T> nodo) {
		if (nodo == null)
			return 0;
		else
			return (size(nodo.darHijoIzquierdo()) + 1 + size(nodo.darHijoDerecho()));
	}

	private int size(NodoRojoNegro<K, T> actual) {
		if (actual == null) {
			return 0;
		}
		return actual.size();
	}

	public int size(K izquierdo, K derecho) {
		if (izquierdo == null)
			throw new IllegalArgumentException("El hijo izquierdo es null");
		if (derecho == null)
			throw new IllegalArgumentException("El hijo derecho es null");

		if (izquierdo.compareTo(derecho) > 0)
			return 0;
		if (contains(derecho))
			return rank(derecho) - rank(izquierdo) + 1;
		else
			return rank(derecho) - rank(izquierdo);
	}

	public boolean isEmpty() {
		return raiz == null;
	}

	public T get(K llave) {
		if (llave == null)
			throw new IllegalArgumentException("El argumento de get() es null");
		return get(raiz, llave);
	}

	private T get(NodoRojoNegro<K, T> nodo, K llave) {
		while (nodo != null) {
			int cmp = llave.compareTo(nodo.darLLave());
			if (cmp < 0)
				nodo = nodo.darHijoIzquierdo();
			else if (cmp > 0)
				nodo = nodo.darHijoDerecho();
			else
				return nodo.darValor();
		}
		return null;

	}

	public boolean esHoja(K llave) {
		NodoRojoNegro<K, T> nodo = raiz;
		while (nodo != null) {
			int cmp = llave.compareTo(nodo.darLLave());
			if (cmp < 0)
				nodo = nodo.darHijoIzquierdo();
			else if (cmp > 0)
				nodo = nodo.darHijoDerecho();
			else
				return nodo.esHoja();
		}
		return false;
	}

	@Override
	public int getAltura(K llave) {
		if (llave == null)
			throw new IllegalArgumentException("No hay mas arbol");
		return getAltura(raiz, llave);
	}

	private int getAltura(NodoRojoNegro<K, T> nodo, K llave) {
		int tam = 0;
		while (nodo != null) {
			int cmp = llave.compareTo(nodo.darLLave());
			if (cmp < 0) {
				nodo = nodo.darHijoIzquierdo();
				tam++;
			} else if (cmp > 0) {
				nodo = nodo.darHijoDerecho();
				tam++;
			} else
				return tam;
		}
		return -1;
	}

	public boolean contains(K llave) {
		return get(llave) != null;
	}

	@Override
	public void agregarDato(K llave, T dato) {
		if (llave == null)
			throw new IllegalArgumentException("La llave es null");
		if (dato == null)
			throw new IllegalArgumentException("El dato es null");

		raiz = agregarDato(raiz, llave, dato);
		size++;
		raiz.cambiarColor(BLACK);
	}

	private NodoRojoNegro<K, T> agregarDato(NodoRojoNegro<K, T> nodo, K llave, T dato) {
		if (nodo == null)
			return new NodoRojoNegro<K, T>(llave, dato, RED, 1);

		int cmp = llave.compareTo(nodo.darLLave());
		if (cmp < 0)
			nodo.cambiarIzquierda(agregarDato(nodo.darHijoIzquierdo(), llave, dato));
		else if (cmp > 0)
			nodo.cambiarDerecha(agregarDato(nodo.darHijoDerecho(), llave, dato));
		else
			nodo.cambiarValor(dato);

		if (esRojo(nodo.darHijoDerecho()) && !esRojo(nodo.darHijoIzquierdo()))
			nodo = rotateLeft(nodo);
		if (esRojo(nodo.darHijoIzquierdo()) && esRojo(nodo.darHijoIzquierdo().darHijoIzquierdo()))
			nodo = rotateRight(nodo);
		if (esRojo(nodo.darHijoIzquierdo()) && esRojo(nodo.darHijoDerecho()))
			flipColors(nodo);
		nodo.cambiarSize(size(nodo.darHijoIzquierdo()) + size(nodo.darHijoDerecho()) + 1);

		return nodo;
	}

	public int altura() {
		return altura(raiz);
	}

	private int altura(NodoRojoNegro<K, T> nodo) {
		if (nodo == null)
			return -1;
		return 1 + Math.max(altura(nodo.darHijoIzquierdo()), altura(nodo.darHijoDerecho()));
	}

	public K max() {
		if (isEmpty())
			throw new NoSuchElementException("El arbol esta vacio.");
		return max(raiz).darLLave();
	}

	private NodoRojoNegro<K, T> max(NodoRojoNegro<K, T> nodo) {
		if (nodo.darHijoDerecho() == null)
			return nodo;
		else
			return max(nodo.darHijoDerecho());
	}

	private boolean esRojo(NodoRojoNegro<K, T> nodo) {
		if (nodo == null) {
			return false;
		}
		return nodo.darColor() == RED;
	}

	public K min() {
		if (isEmpty())
			throw new NoSuchElementException("El arbol esta vacio.");
		return min(raiz).darLLave();
	}

	private NodoRojoNegro<K, T> min(NodoRojoNegro<K, T> nodo) {
		if (nodo.darHijoIzquierdo() == null)
			return nodo;
		else
			return min(nodo.darHijoIzquierdo());
	}

	public Queue<K> keys() {
		if (isEmpty())
			return new Queue<K>();
		return llavesRango(min(), max());
	}

	private void keys(NodoRojoNegro<K, T> nodo, Queue<K> cola, K init, K end) {
		if (nodo == null)
			return;
		int cmplo = init.compareTo(nodo.darLLave());
		int cmphi = end.compareTo(nodo.darLLave());
		if (cmplo < 0)
			keys(nodo.darHijoIzquierdo(), cola, init, end);
		if (cmplo <= 0 && cmphi >= 0)
			cola.enqueue(nodo.darLLave());
		if (cmphi > 0)
			keys(nodo.darHijoDerecho(), cola, init, end);
	}

	private void hojas(NodoRojoNegro<K, T> nodo, Queue<K> cola, K init, K end) {
		if (nodo == null)
			return;
		int cmplo = init.compareTo(nodo.darLLave());
		int cmphi = end.compareTo(nodo.darLLave());
		if (cmplo < 0)
			hojas(nodo.darHijoIzquierdo(), cola, init, end);
		if (cmplo <= 0 && cmphi >= 0 && nodo.esHoja() == true) {
			cola.enqueue(nodo.darLLave());
		}
		if (cmphi > 0)
			hojas(nodo.darHijoDerecho(), cola, init, end);
	}

	public K piso(K llave) {
		if (llave == null)
			return null;
		if (isEmpty())
			return null;

		NodoRojoNegro<K, T> nodo = piso(raiz, llave);
		if (nodo == null)
			return null;
		else
			return nodo.darLLave();
	}

	private NodoRojoNegro<K, T> piso(NodoRojoNegro<K, T> nodo, K llave) {
		if (nodo == null)
			return null;
		int compar = llave.compareTo(nodo.darLLave());
		if (compar == 0)
			return nodo;
		if (compar < 0)
			return piso(nodo.darHijoIzquierdo(), llave);
		NodoRojoNegro<K, T> nodo2 = piso(nodo.darHijoDerecho(), llave);
		if (nodo2 != null)
			return nodo2;
		else
			return nodo;
	}

	public K techo(K llave) {
		if (llave == null)
			throw new IllegalArgumentException("La llave es null");
		if (isEmpty())
			throw new NoSuchElementException("El arbol esta vacio");
		NodoRojoNegro<K, T> nodo = techo(raiz, llave);
		if (nodo == null)
			throw new NoSuchElementException("El nodo es null");
		else
			return nodo.darLLave();
	}

	private NodoRojoNegro<K, T> techo(NodoRojoNegro<K, T> nodo, K llave) {
		if (nodo == null)
			return null;
		int comp = llave.compareTo(nodo.darLLave());
		if (comp == 0)
			return nodo;
		if (comp > 0)
			return techo(nodo.darHijoDerecho(), llave);
		NodoRojoNegro<K, T> nodo2 = techo(nodo.darHijoIzquierdo(), llave);
		if (nodo2 != null)
			return nodo2;
		else
			return nodo;
	}

	private NodoRojoNegro<K, T> rotateRight(NodoRojoNegro<K, T> pNodo) {
		NodoRojoNegro<K, T> nodo = pNodo.darHijoIzquierdo();
		pNodo.cambiarIzquierda(nodo.darHijoDerecho());
		nodo.cambiarDerecha(pNodo);
		nodo.cambiarColor(nodo.darHijoDerecho().darColor());
		nodo.darHijoDerecho().cambiarColor(RED);
		nodo.cambiarSize(pNodo.size());
		pNodo.cambiarSize(size(pNodo.darHijoIzquierdo()) + size(pNodo.darHijoDerecho()) + 1);
		return nodo;
	}

	public K select(int rango) {
		if (rango < 0 || rango >= getSize())
			throw new IllegalArgumentException("El rango no es valido.");
		return select(raiz, rango);
	}

	private K select(NodoRojoNegro<K, T> nodo, int rango) {
		if (nodo == null)
			return null;
		int izquierdaTamano = size(nodo.darHijoIzquierdo());
		if (izquierdaTamano > rango)
			return select(nodo.darHijoIzquierdo(), rango);
		else if (izquierdaTamano < rango)
			return select(nodo.darHijoDerecho(), rango - izquierdaTamano - 1);
		else
			return nodo.darLLave();
	}

	public int rank(K llave) {
		if (llave == null)
			throw new IllegalArgumentException("La llave no es valida");
		return rank(llave, raiz);
	}

	public int rank(K llave, NodoRojoNegro<K, T> nodo) {
		if (nodo == null)
			return 0;
		int comp = llave.compareTo(nodo.darLLave());
		if (comp < 0)
			return rank(llave, nodo.darHijoIzquierdo());
		else if (comp > 0)
			return 1 + size(nodo.darHijoIzquierdo()) + rank(llave, nodo.darHijoDerecho());
		else
			return size(nodo.darHijoIzquierdo());
	}

	private NodoRojoNegro<K, T> rotateLeft(NodoRojoNegro<K, T> pNodo) {
		NodoRojoNegro<K, T> nodo = pNodo.darHijoDerecho();
		pNodo.cambiarDerecha(nodo.darHijoIzquierdo());
		nodo.cambiarIzquierda(pNodo);
		nodo.cambiarColor(nodo.darHijoIzquierdo().darColor());
		nodo.darHijoIzquierdo().cambiarColor(RED);
		nodo.cambiarSize(pNodo.size());
		pNodo.cambiarSize(size(pNodo.darHijoIzquierdo()) + size(pNodo.darHijoDerecho()) + 1);
		return nodo;
	}

	private void flipColors(NodoRojoNegro<K, T> nodo) {
		nodo.cambiarColor(!nodo.darColor());
		nodo.darHijoIzquierdo().cambiarColor(!nodo.darHijoIzquierdo().darColor());
		nodo.darHijoDerecho().cambiarColor(!nodo.darHijoDerecho().darColor());
	}

	public Queue<T> valoresEnRango(K primero, K ultimo) {
		if (primero == null)
			throw new IllegalArgumentException("El primer argumento de keys() es null");
		if (ultimo == null)
			throw new IllegalArgumentException("El segundo argumento de keys() es null");

		Queue<T> cola = new Queue<T>();
		comparador(cola, raiz, primero, ultimo);
		return cola;
	}

	private void comparador(Queue<T> cola, NodoRojoNegro<K, T> nodo, K primero, K ultimo) {
		if (nodo == null)
			return;
		int min = primero.compareTo(nodo.darLLave());
		int max = ultimo.compareTo(nodo.darLLave());
		if (min < 0)
			comparador(cola, nodo.darHijoIzquierdo(), primero, ultimo);
		if (min <= 0 && max >= 0)
			cola.enqueue(nodo.darValor());
		if (max > 0)
			comparador(cola, nodo.darHijoDerecho(), primero, ultimo);
	}

	public Queue<K> llavesRango(K primero, K ultimo) {
		if (primero == null)
			throw new IllegalArgumentException("La primera llave es null");
		if (ultimo == null)
			throw new IllegalArgumentException("La ultima llave es null");

		Queue<K> cola = new Queue<K>();
		keys(raiz, cola, primero, ultimo);
		return cola;

	}

	public Queue<K> darHojas() {
		if (min() == null)
			throw new IllegalArgumentException("No hay minimo null");
		if (max() == null)
			throw new IllegalArgumentException("No hay maximo.");

		Queue<K> cola = new Queue<K>();
		hojas(raiz, cola, min(), max());
		return cola;
	}

	public Iterator<K> iterator() {
		return keys().iterator();
	}
}