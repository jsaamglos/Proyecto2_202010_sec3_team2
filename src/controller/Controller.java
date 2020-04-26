package controller;

import java.util.Scanner;

import model.logic.Modelo;
import model.logic.Multa;
import view.View;

public class Controller {

	/* Instancia del Modelo */
	private Modelo modelo;

	/* Instancia de la Vista */
	private View view;

	private int cantDatos = 0;

	/**
	 * Crear la vista y el modelo del proyecto
	 * 
	 * @param capacidad
	 *            tamaNo inicial del arreglo
	 */
	public Controller() {
		view = new View();
		modelo = new Modelo();
	}

	public void run() {
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		// String codigo = "";
		String respuesta = "";

		while (!fin) {
			view.printMenu();

			int option = lector.nextInt();
			switch (option) {
			case 1:
				view.printMessage("--------- \nCargar Datos \nDar cantidad de datos cargados: ");
				modelo.crearHeapMax();
				respuesta = "" + modelo.darMayorObjectID();
				view.printMessage("Se cargaron los Comparendos en total son:" + modelo.darTamanoHeap()
						+ "\nEl objeto con mayor ObjectID es:" + respuesta);
				break;
			case 2:
				view.printMessage("--------- \nRequerimiento 1A: ");
				view.printMessage("--------- \nIngrese la cantidad de comparendos que quiere ver: ");
				dato = lector.next();
				respuesta = modelo.comparendosMayorGravedad(Integer.parseInt(dato));
				view.printMessage("Los comparendos con mayor gravedad son:" + respuesta);
				break;

			case 0:
				view.printMessage("--------- \n Hasta pronto !! \n---------");
				lector.close();
				fin = true;
				break;

			default:
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}
}
