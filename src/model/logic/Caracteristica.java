package model.logic;

public class Caracteristica {


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Caracteristica other = (Caracteristica) obj;
		if (CLASE_VEHICULO == null) {
			if (other.CLASE_VEHICULO != null)
				return false;
		} else if (!CLASE_VEHICULO.equals(other.CLASE_VEHICULO))
			return false;
		if (FECHA_HORA == null) {
			if (other.FECHA_HORA != null)
				return false;
		} else if (!FECHA_HORA.equals(other.FECHA_HORA))
			return false;
		if (INFRACCION == null) {
			if (other.INFRACCION != null)
				return false;
		} else if (!INFRACCION.equals(other.INFRACCION))
			return false;
		return true;
	}

	// todos los parametros de una multa
	int OBJECTID;
	String FECHA_HORA;
	String MEDIO_DETECCION;
	String CLASE_VEHICULO;
	String TIPO_SERVI;
	String INFRACCION;
	String DES_INFRACCION;
	String LOCALIDAD;

	// inicializa la multa
	public Caracteristica(int id, String fecha, String medio, String clase, String servicio, String infraccion,
			String desInfraccion, String localidad) {
		OBJECTID = id;
		FECHA_HORA = fecha;
		MEDIO_DETECCION = medio;
		CLASE_VEHICULO = clase;
		TIPO_SERVI = servicio;
		INFRACCION = infraccion;
		DES_INFRACCION = desInfraccion;
		LOCALIDAD = localidad;
	}

	public int getId()
	{
		return OBJECTID;
	}

	public String getFecha()
	{
		return FECHA_HORA;
	}

	public String getMedio()
	{
		return MEDIO_DETECCION;
	}

	public String getClaseVehiculo()
	{
		return CLASE_VEHICULO;
	}

	public String getServicio()
	{
		return TIPO_SERVI;
	}

	public String getInfraccion()
	{
		return INFRACCION;
	}

	public String getDesInfraccioin()
	{
		return DES_INFRACCION;
	}

	public String getLocalidad()
	{
		return LOCALIDAD;
	}

	public String toString() {
		return "Caracteristica [OBJECTID=" + OBJECTID + 
		", FECHA_HORA=" + FECHA_HORA + 
		", TIPO_SERVI=" + TIPO_SERVI + 
		", CLASE_VEHI" + CLASE_VEHICULO +
		", INFRACCION=" + INFRACCION + 
		", LOCALIDAD=" + LOCALIDAD + "]";
	}

}
