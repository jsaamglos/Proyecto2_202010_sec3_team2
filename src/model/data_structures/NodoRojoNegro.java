package model.data_structures;

public class NodoRojoNegro<K, T> {
	private K llave;

	private T dato;

	private boolean color;
	
	private NodoRojoNegro<K, T> derecha;
	
	private NodoRojoNegro<K, T> izquierda;
	
	private int size;

	public NodoRojoNegro(K pLlave, T pDato, boolean pColor, int pTamano) {
		llave = pLlave;
		dato = pDato;
		color = pColor;
		size = pTamano;
	}

	public K darLLave() {
		return llave;
	}

	public T darValor() {
		return dato;
	}

	public NodoRojoNegro<K, T> darHijoDerecho() {
		return derecha;
	}

	public NodoRojoNegro<K, T> darHijoIzquierdo() {
		return izquierda;
	}

	public boolean darColor() {
		return color;
	}

	public void cambiarColor(boolean pColor) {
		color = pColor;
	}

	public int size() {
		return size;
	}

	public void cambiarSize(int pSize) {
		size = pSize;
	}

	public void cambiarValor(T pValor) {
		dato = pValor;
	}

	public void cambiarDerecha(NodoRojoNegro<K, T> pDerecha) {
		derecha = pDerecha;
	}

	public void cambiarIzquierda(NodoRojoNegro<K, T> pIzquierda) {
		izquierda = pIzquierda;
	}

	public boolean esHoja() {
		return (derecha == null && izquierda == null);
	}
}
