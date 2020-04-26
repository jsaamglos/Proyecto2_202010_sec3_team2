package model.data_structures;

public interface IHeapMax<T> {
	/*
	 * Retorna n�mero de elementos presentes en la cola de prioridad.
	 */
	public int getSize();

	/*
	 * Agrega un elemento a la cola de prioridad. Para comparar la prioridad de
	 * dos elementos T se debe usar el comparador "natural" de la clase T.
	 */
	public void agregar(T elemento);

	/*
	 * Saca/atiende el elemento m�ximo en la cola y lo retorna; null en caso de 
	 * cola vac�a.
	 */
	public T removeMax();

	/*
	 * Obtener el elemento m�ximo (sin sacarlo de la Cola); null en caso de cola 
	 * vac�a.
	 */
	public T getMax();


	public boolean isEmpty();	
}
