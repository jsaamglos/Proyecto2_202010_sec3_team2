package model.data_structures;

public class Node<K, T> implements INode<K, T> {
	// apuntador al siguiente elemento
	private Node<K, T> siguienteElemento;

	// apuntador al elemento anterior
	private Node<K, T> anteriorElamento;

	// elemento
	private T elemento;

	private K llave;

	// constructor
	public Node(K llave, T elemento, Node<K, T> anterior) {
		this.elemento = elemento;
		this.llave = llave;
		anteriorElamento = anterior;
		if (anterior != null) {
			anterior.setSiguiente(this);
		}
	}

	// da el elemento siguiente
	public Node<K, T> getSiguiente() {
		return siguienteElemento;
	}

	// da el elemento anterior
	public Node<K, T> getAnterior() {
		return anteriorElamento;
	}

	// returna el elemento
	public T getElemento() {
		return elemento;
	}

	public K getLlave() {
		//
		return llave;
	}

	// asigna el nodo por parametro al siguiete
	@Override
	public void setSiguiente(Node<K, T> nodo) {
		siguienteElemento = nodo;

	}

	// asigna el nodo por parametro al anterior
	@Override
	public void setAnterior(Node<K, T> nodo) {
		anteriorElamento = nodo;
	}
}