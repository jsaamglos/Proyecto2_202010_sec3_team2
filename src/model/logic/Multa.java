package model.logic;

public class Multa implements Comparable<Multa> {

	private Caracteristica properties;
	private Ubicacion geometry;
	private String type;
	private String comparacion = "OBJECTID";

	public Caracteristica getProperties() {
		return properties;
	}

	public void setProperties(Caracteristica properties) {
		this.properties = properties;
	}

	public Ubicacion getGeometry() {
		return geometry;
	}

	public void setGeometry(Ubicacion geometry) {
		this.geometry = geometry;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int compareTo(Multa arg0, int caso) {
		return 0;
	}

	public void cambiarComparacion(int x) {
		if(x==0){
			comparacion="FECHA";
		}else if(x==1){
			comparacion="OBJECTID";
		}else if(x==2){
			comparacion="TIPOSER";
		}
	}

	@Override
	public int compareTo(Multa o) {
		int valor = 200;
		if (comparacion.equals("OBJECTID")) {
			if (this.getProperties().OBJECTID > o.getProperties().OBJECTID) {
				valor = 1;
			} else if (this.getProperties().OBJECTID < o.getProperties().OBJECTID) {
				valor = -1;
			} else if (this.getProperties().OBJECTID == o.getProperties().OBJECTID) {
				valor = 0;
			}
		} else if (comparacion.equals("FECHA")) {
			if (this.getProperties().getFecha().compareTo(o.getProperties().getFecha()) < 0) {
				valor = 1;
			} else if (this.getProperties().getFecha().compareTo(o.getProperties().getFecha()) > 0) {
				valor = -1;
			} else if (this.getProperties().getFecha().compareTo(o.getProperties().getFecha()) == 0) {
				valor = 0;
			}
		} else if (comparacion.equals("TIPOSER")) {
			if (this.getProperties().getServicio().equalsIgnoreCase("Público")
					&& (o.getProperties().getServicio().equalsIgnoreCase("Oficial")
							|| o.getProperties().getServicio().equalsIgnoreCase("Particular"))) {
				valor = 1;
			} else if (this.getProperties().getServicio().equalsIgnoreCase("Oficial")
					&& o.getProperties().getServicio().compareTo("Oficial") != 0) {
				if (o.getProperties().getServicio().equalsIgnoreCase("Público")) {
					valor = -1;
				} else if (o.getProperties().getServicio().equalsIgnoreCase("Particular")) {
					valor = 1;
				}
			} else if (this.getProperties().getServicio().equalsIgnoreCase("Particular")
					&& (o.getProperties().getServicio().equalsIgnoreCase("Oficial")
							|| o.getProperties().getServicio().equalsIgnoreCase("Público"))) {
				valor = -1;
			} else if (this.getProperties().getServicio().equalsIgnoreCase(o.getProperties().getServicio())) {
				if (this.getProperties().getInfraccion().compareTo(o.getProperties().getInfraccion()) > 0) {
					valor = 1;
				} else if (this.getProperties().getInfraccion().compareTo(o.getProperties().getInfraccion()) < 0) {
					valor = -1;
				} else if (this.getProperties().getInfraccion().compareTo(o.getProperties().getInfraccion()) == 0) {
					if (this.getProperties().OBJECTID > o.getProperties().OBJECTID) {
						valor = 1;
					} else if (this.getProperties().OBJECTID < o.getProperties().OBJECTID) {
						valor = -1;
					} else if (this.getProperties().OBJECTID == o.getProperties().OBJECTID) {
						valor = 0;
					}
				}
			}
		}

		/*
		 * else if (comparacion == 4) { if
		 * (this.darDescripcion().contains("INMOVILIZADO") == true &&
		 * o.darDescripcion().contains("INMOVILIZADO") == false) { valor = 1; }
		 * else if (this.darDescripcion().contains("INMOVILIZADO") == true &&
		 * o.darDescripcion().contains("INMOVILIZADO") == true) { if
		 * (this.getProperties().getFecha().compareTo(o.getProperties().getFecha
		 * ()) < 0) { valor = 1; } else if
		 * (this.getProperties().getFecha().compareTo(o.getProperties().getFecha
		 * ()) > 0) { valor = -1; } else if
		 * (this.getProperties().getFecha().compareTo(o.getProperties().getFecha
		 * ()) == 0) { valor = 0; } }
		 * 
		 * else if (this.darDescripcion().contains("LICENCIA") == true &&
		 * o.darDescripcion().contains("INMOVILIZADO") == true) { valor = -1; }
		 * else if (this.darDescripcion().contains("LICENCIA") == true &&
		 * o.darDescripcion().contains("LICENCIA") == true) { if
		 * (this.getProperties().getFecha().compareTo(o.getProperties().getFecha
		 * ()) < 0) { valor = 1; } else if
		 * (this.getProperties().getFecha().compareTo(o.getProperties().getFecha
		 * ()) > 0) { valor = -1; } else if
		 * (this.getProperties().getFecha().compareTo(o.getProperties().getFecha
		 * ()) == 0) { valor = 0; } } else if
		 * (this.darDescripcion().contains("LICENCIA") == true &&
		 * o.darDescripcion().contains("INMOVILIZADO") == false &&
		 * o.darDescripcion().contains("LICENCIA") == false) { valor = 1; }
		 * 
		 * else if (this.darDescripcion().contains("LICENCIA") == false &&
		 * this.darDescripcion().contains("INMOVILIZADO") == false &&
		 * (o.darDescripcion().contains("INMOVILIZADO") == true ||
		 * o.darDescripcion().contains("LICENCIA") == true)) { valor = -1; }
		 * else if (this.darDescripcion().contains("LICENCIA") == false &&
		 * this.darDescripcion().contains("INMOVILIZADO") == false &&
		 * o.darDescripcion().contains("INMOVILIZADO") == false &&
		 * o.darDescripcion().contains("LICENCIA") == false) { if
		 * (this.getProperties().getFecha().compareTo(o.getProperties().getFecha
		 * ()) < 0) { valor = 1; } else if
		 * (this.getProperties().getFecha().compareTo(o.getProperties().getFecha
		 * ()) > 0) { valor = -1; } else if
		 * (this.getProperties().getFecha().compareTo(o.getProperties().getFecha
		 * ()) == 0) { valor = 0; } } }
		 */
		return valor;
	}

}