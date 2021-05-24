package es.studium.ElCocodriloSacamuelas;


import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;


public class Vista extends Frame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Frame Menú Principal		
	Label  lblMenuPrincipal = new Label ("Menú Principal");
	Label  lblMenuPrincipal2 = new Label ("-------------");
	Button btnNuevaPartida = new Button ("Nueva Partida");
	Button btnRanking = new Button ("Ranking");
	Button btnAyuda = new Button ("Ayuda");
	Button btnSalir = new Button ("Salir del juego");
	
	//Dialog Nueva Partida
	Dialog dlgNuevaPartida= new Dialog(this,"El Cocodrilo Sacamuelas: Nueva Partida");
	Label  lblJugador1 = new Label ("Jugador 1-Escribe tu nombre:");
	Label  lblJugador2 = new Label ("Jugador 2-Escribe tu nombre:");
	TextField txtJugador1= new TextField(35); 
	TextField txtJugador2= new TextField(35);
	Button    btnAceptar=  new Button("Aceptar");
	Button    btnVolver=   new Button("Volver");
	
	//Dialog Ranking
	Dialog dlgRanking= new Dialog(this,"El Cocodrilo Sacamuelas:Ranking");
	TextArea txaConsulta = new TextArea(20,80);
	
	public Vista()
	{	//Montando el Frame Principal
		setTitle("Menú El Cocodrilo Sacamuelas");
		setBackground(Color.green);
		setLayout (new FlowLayout());
		add(lblMenuPrincipal);
		add(lblMenuPrincipal2);
		add(btnNuevaPartida);
		add(btnRanking);
		add(btnAyuda);
		add(btnSalir);
		setSize(160,190);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		//Montar Dialogo para guardar nombres
		dlgNuevaPartida.setBackground(Color.green);
		dlgNuevaPartida.setLayout(new FlowLayout());
		dlgNuevaPartida.setSize(500,150);
		dlgNuevaPartida.setLocationRelativeTo(null);
		dlgNuevaPartida.setResizable(false);
		dlgNuevaPartida.add(lblJugador1);
		dlgNuevaPartida.add(txtJugador1);
		dlgNuevaPartida.add(lblJugador2);
		dlgNuevaPartida.add(txtJugador2);
		dlgNuevaPartida.add(btnAceptar);
		dlgNuevaPartida.add(btnVolver);
		
		//Dialogo para el ranking
		dlgRanking.setBackground(Color.green);
		dlgRanking.setLayout(new FlowLayout());
		dlgRanking.setSize(600,450);
		dlgRanking.setLocationRelativeTo(null);
		dlgRanking.setResizable(false);
		txaConsulta.append("ID\tNombre\t\tPuntuación\n");
		dlgRanking.add(txaConsulta);
		
	}
}



	

