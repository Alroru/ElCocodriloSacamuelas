package es.studium.ElCocodriloSacamuelas;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class Modelo
{
	Random rnd = new Random();
	String nombreJugador1="";
	String nombreJugador2="";
	int puntosJugador1=0;
	int puntosJugador2=0; 
	
	//Generar un número aleatorio entre 0 y 4
	public int generar()
	{	
		return rnd.nextInt(5);
	}



	// Método conectar BD
	public  Connection conectar()
	{
		Connection c = null;
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/cocodrilo?serverTimezone=UTC";
		String login = "root";
		String password = "Studium2020;";
		try
		{
			//Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			//Establecer la conexión con la BD clientes
			c = DriverManager.getConnection(url, login, password);
		}
		catch (ClassNotFoundException cnfe)
		{
			System.out.println("Error 1-"+cnfe.getMessage());
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (c);
	}

	// Método desconectar BD
	public Connection cerrar(Connection conexion)
	{
		try
		{
			if(conexion!=null)
			{
				conexion.close();
			}
		}
		catch (SQLException error)
		{
			System.out.println("Error 3-"+error.getMessage());
		}
		return conexion;
	}
	
	public void altaPartida(Connection conexion,String nj1,String nj2,int pj1,int pj2)
	{
			
			Statement statement = null;
			String sentencia = "INSERT INTO `cocodrilo`.`ranking` "
					+ "(idRanking, jugadorRanking, puntosJugador) VALUES (null,'" 
					+ nj1 + "',"+ pj1+"), (null,' "
					+nj2 + "',"+pj2+")";
			
			System.out.println(sentencia);
			try
			{
				//Crear una sentencia
				statement = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				//Crear un objeto ResultSet para guardar lo obtenido
				//y ejecutar la sentencia SQL
				statement.executeUpdate(sentencia);
		
			}
			catch (SQLException error)
			{
				System.out.println("Error 4-"+error.getMessage());
			}
			
	}
	

	// Método obtener datos BD
	public String consulta(Connection conexion)
	{
		String datos = "";
		Statement statement = null;
		ResultSet rs = null;
		String sentencia = "SELECT  idRanking, jugadorRanking, puntosJugador" +
				" FROM ranking\r\n" +
				" ORDER BY 3 DESC"+
				" LIMIT 10";
		try
		{
			//Crear una sentencia
			statement = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			while(rs.next())
			{	
				datos = datos + rs.getInt("idRanking") + "\t" ;
				datos = datos + rs.getString("jugadorRanking") + "\t" + "\t";
				datos = datos + rs.getInt("puntosJugador")+ "\t"+"\n";
				System.out.println(datos);
			}
		}
		catch (SQLException error)
		{
			System.out.println("Error 5-"+error.getMessage());
		}
		return (datos);
	}
	
	public void ayuda()
	{
		try 
		{
		Runtime.getRuntime().exec("hh.exe _AyudaJuego.chm");
		}
		catch (IOException e) 
		{
		e.printStackTrace();
		System.out.println(e.getMessage());
		}
	}
	
}




