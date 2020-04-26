package model.data_structures;

public class NodeTabla<K, T> implements INodeTabla<K, T> {
	// apuntador al siguiente elemento
	private NodeTabla<K, T> siguienteElemento;

	// apuntador al elemento anterior
	private NodeTabla<K, T> anteriorElamento;

	// elemento
	private T elemento;

	private K llave;

	// constructor
	public NodeTabla(K llave, T elemento, NodeTabla<K, T> anterior) {
		this.elemento = elemento;
		this.llave = llave;
		anteriorElamento = anterior;
		if (anterior != null) {
			anterior.setSiguiente(this);
		}
	}

	// da el elemento siguiente
	public NodeTabla<K, T> getSiguiente() {
		return siguienteElemento;
	}

	// da el elemento anterior
	public NodeTabla<K, T> getAnterior() {
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

	public void setSiguiente(NodeTabla<K, T> nodo) {
		siguienteElemento = nodo;

	}

	public void setElemento(T dato) {
		elemento = dato;
	}

	// asigna el nodo por parametro al anterior

	public void setAnterior(NodeTabla<K, T> nodo) {
		anteriorElamento = nodo;
	}
}