package controller;

import java.util.Scanner;

import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo */
	private Modelo modelo;

	/* Instancia de la Vista */
	private View view;

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
				modelo.crearLista();
				respuesta = modelo.getMultaMayorOBID().getProperties().toString();
				view.printMessage("Se cargaron los Comparendos en total son:" + modelo.darTamanoLista()
						+ "\nEl objeto con mayor ObjectID es:" + respuesta);
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
