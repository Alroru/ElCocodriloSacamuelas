package es.studium.ElCocodriloSacamuelas;


import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;


public class VistaJuego extends Frame
{
	private static final long serialVersionUID = 1L;
	
	Controlador control;
	Toolkit herramientas;
	Image cocodrilo, equis;
	int marcaDiente[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	int posicionDiente[][]={{0,0},{0,0},{0,0},{0,0},
			{0,0},{0,0},{0,0},{0,0},
			{0,0},{0,0},{0,0},{0,0},
			{0,0},{0,0},{0,0},{0,0},{0,0},
			{0,0},{0,0},{0,0}};
	int puntosJugador1=0;
	int puntosJugador2=0;
	String nombreJugador1="Jugador1";
	String nombreJugador2="Jugador2";
	int turno=0;
	
	Dialog dlgFin2 = new Dialog(this, "Has pulsado un diente malo", true);
	Label lblMensaje2 = new Label("¡Eliminado!");
	
	Dialog dlgPlantar1 = new Dialog(this, "Plantarse", true);
	Label lblMensaje1 = new Label("Puntos Guardados");
	
	Dialog dlgCambio = new Dialog(this, "Cambio", true);
	Label lblMensaje3 = new Label("Pasa el ratón al siguiente jugador");
	
	Dialog dlgGana1= new Dialog(this, "Fin del juego", true);
	Dialog dlgGana2= new Dialog(this, "Fin del juego", true);
	Dialog dlgEmpate= new Dialog(this, "Fin del juego", true);
	Label lblGana1= new Label("¡Ha ganado "+nombreJugador1);
	Label lblGana2= new Label("¡Ha ganado "+nombreJugador2);
	Label lblEmpate= new Label("Es un empate!");
	
	
	public VistaJuego()
	{
		herramientas = getToolkit();
		cocodrilo = herramientas.getImage("cocodrilo.png");
		equis = herramientas.getImage("equis.png");
		this.setBackground(Color.green);
		this.setTitle("El Cocodrilo Sacamuelas");
		this.setSize(900,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(false);
		
		dlgFin2.setLayout(new FlowLayout());
		dlgFin2.setSize(100,100);
		dlgFin2.setResizable(false);
		dlgFin2.add(lblMensaje2);
		dlgFin2.setLocationRelativeTo(null);
		
		dlgPlantar1.setLayout(new FlowLayout());
		dlgPlantar1.setSize(100,100);
		dlgPlantar1.setResizable(false);
		dlgPlantar1.add(lblMensaje1);
		dlgPlantar1.setLocationRelativeTo(null);
		
		dlgCambio.setLayout(new FlowLayout());
		dlgCambio.setSize(300,100);
		dlgCambio.setResizable(false);
		dlgCambio.add(lblMensaje3);
		dlgCambio.setLocationRelativeTo(null);
		
		dlgGana1.setLayout(new FlowLayout());
		dlgGana1.setSize(300,100);
		dlgGana1.setResizable(false);
		dlgGana1.add(lblGana1);
		dlgGana1.setLocationRelativeTo(null);
		
		dlgGana2.setLayout(new FlowLayout());
		dlgGana2.setSize(300,100);
		dlgGana2.setResizable(false);
		dlgGana2.add(lblGana2);
		dlgGana2.setLocationRelativeTo(null);
		
		dlgEmpate.setLayout(new FlowLayout());
		dlgEmpate.setSize(300,100);
		dlgEmpate.setResizable(false);
		dlgEmpate.add(lblEmpate);
		dlgEmpate.setLocationRelativeTo(null);
		
		
		

}
	
	public void paint(Graphics g)
	{
		g.drawImage(cocodrilo, 20, 20, this);
		Font fuente = new Font("Arial", Font.BOLD, 24);
		g.setFont(fuente);
		g.setColor(Color.red);
		g.drawString(""+nombreJugador1+":"+puntosJugador1+" puntos", 650, 78);
		g.setColor(Color.gray);
		g.fill3DRect(670, 96, 150, 50, true);
		g.setColor(Color.black);
		g.drawString("Me Planto!", 680, 130);
		g.setColor(Color.blue);
		g.drawString(""+nombreJugador2+":"+puntosJugador2+" puntos", 650, 178);
		g.setColor(Color.gray);
		g.fill3DRect(670, 196, 150, 50, true);
		g.setColor(Color.black);
		g.drawString("Me Planto!", 680, 230);
		g.setColor(Color.gray);
		g.fill3DRect(670, 496, 150, 50, true);
		g.setColor(Color.black);
		g.drawString("Salir!", 710, 530);
		switch(turno)
		{
		case 0:
		{
			g.drawLine(650, 80, 880,80 );
			break;
		}	
		case 1:
			g.drawLine(650, 180, 880, 180);
			break;
		}
		if(marcaDiente[0]==1)
		{
			g.drawImage(equis,75,190,this);
		}
	 if(marcaDiente[1]==1)
		{
			g.drawImage(equis,140,185,this);
		}
		 if(marcaDiente[2]==1)
		{
			g.drawImage(equis,185,175,this);
		}
	 if(marcaDiente[3]==1)
		{
			g.drawImage(equis,230,185,this);
		}
		 if(marcaDiente[4]==1)
		{
			g.drawImage(equis,285,185,this);
		}
		 if(marcaDiente[5]==1)
		{
			g.drawImage(equis,330,170,this);
		}
		 if(marcaDiente[6]==1)
		{
			g.drawImage(equis,385,180,this);
		}
		 if(marcaDiente[7]==1)
		{
			g.drawImage(equis,420,195,this);
		}
	 if(marcaDiente[8]==1)
		{
			g.drawImage(equis,465,255,this);
		}
	 if(marcaDiente[9]==1)
		{
			g.drawImage(equis,442,308,this);
		}
		 if(marcaDiente[10]==1)
		{
			g.drawImage(equis,390,335,this);
		}
		 if(marcaDiente[11]==1)
		{
			g.drawImage(equis,360,375,this);
		}
		 if(marcaDiente[12]==1)
		{
			g.drawImage(equis,310,400,this);
		}
		 if(marcaDiente[13]==1)
		{
			g.drawImage(equis,235,410,this);
		}
		 if(marcaDiente[14]==1)
		{
			g.drawImage(equis,165,400,this);
		}
		 if(marcaDiente[15]==1)
		{
			g.drawImage(equis,85,390,this);
		}
		 if(marcaDiente[16]==1)
		{
			g.drawImage(equis,135,375,this);
		}
		if(marcaDiente[17]==1)
		{
			g.drawImage(equis,165,330,this);
		}
		 if(marcaDiente[18]==1)
		{
			g.drawImage(equis,205,295,this);
		}
		 if(marcaDiente[19]==1)
		{
			g.drawImage(equis,245,250,this);
		}
		}
		
	
	
	public void marcarEquis(int a)
	{
		marcaDiente[a]=1;
		repaint();
		
	}
	public void borrarEquis()
	{
		for(int i=0;i<=19;i++)
		{	
		marcaDiente[i]=0;
		}
		repaint();
		
	}
	public void aumentarPuntos1()
	{
		puntosJugador1++;
		repaint();
	}
	public void aumentarPuntos2()
	{
		puntosJugador2++;
		repaint();
	}
	
	public void cambiarNombre(String nombreJugadorA, String nombreJugadorB)
	{
		nombreJugador1=""+nombreJugadorA;
		nombreJugador2=""+nombreJugadorB;
		repaint();	
	}



	public void señalarNombre(int turnoActual)
	{
		turno=turnoActual;
		repaint();
		
	}
	public void reiniciar()
	{
	
		for(int i=0;i<=19;i++)
		{	
		marcaDiente[i]=0;
		}	
		puntosJugador1=0;;
		puntosJugador2=0;
		nombreJugador1="";
		nombreJugador2="";
		turno=0;
	
		repaint();
		
	}


}
