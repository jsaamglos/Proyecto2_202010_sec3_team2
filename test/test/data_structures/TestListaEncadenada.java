package test.data_structures;

import model.data_structures.ListaEncadenada;

import static org.junit.Assert.*;

/*
import org.junit.Before;
import org.junit.Test;
*/

public class TestListaEncadenada {
	ListaEncadenada<String, String> lista;

	public TestListaEncadenada() {
		testSetup1();
		testSetup2();
	}

	public void setUp1() {
		lista = new ListaEncadenada<String, String>();
	}

	public void setUp2() {
		lista = new ListaEncadenada<String, String>();
		lista.agregarElemento("a", "Primero");
		lista.agregarElemento("b", "Segundo");
		lista.agregarElemento("c", "Tercero");
	}

	public void testSetup1() {
		setUp1();

		assertEquals(lista.darTamano(), 0);

		lista.agregarElemento("a", "dato");

		assertEquals(lista.darPrimerElemento(), "dato");
		assertEquals(lista.darElemento(0), "dato");
		assertEquals(lista.darTamano(), 1);
		assertEquals(lista.darPrimeraPosicion().getLlave(), "a");

		lista.eliminarElemento("dato");

		assertEquals(lista.darTamano(), 0);

	}

	public void testSetup2() {
		setUp2();

		assertEquals(lista.darUltimoElemento(), "Tercero");

		lista.agregarElemento("d", "Cuarto");

		assertEquals(lista.darUltimoElemento(), "Cuarto");

	}

}