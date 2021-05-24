package es.studium.ElCocodriloSacamuelas;

public class ElCocodriloSacamuelas
{
	//Clase principal; Crear controlador con la vista y el modelo
	public static void main(String[] args)
	{
		new Controlador(new Vista() ,new Modelo());
	}
}
