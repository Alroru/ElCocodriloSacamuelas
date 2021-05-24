package es.studium.ElCocodriloSacamuelas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controlador implements WindowListener, ActionListener,MouseListener
{
	Vista MenuPrincipal;
	VistaJuego Juego;
	Modelo modelo;
	
	int marcaDiente[]={-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	int secreto;
	
	//Base datos
	String sentencia = "";
	Connection conexion = null;
	Statement statement = null;
	ResultSet rs = null;
	String datos="";
	String nombreJugador1="";
	String nombreJugador2="";
	int puntosJugador1=0;
	int puntosJugador2=0;
	int turno=0;
	int	jugadorEliminado1=0;
	int jugadorEliminado2=0;
	int jugadorPlantado1=0;
	int jugadorPlantado2=0;
	
	
	
	public Controlador(	Vista mp, Modelo mo)
	{	this.modelo=mo;
		conexion=modelo.conectar();
		this.Juego=new VistaJuego();
	
	
		this.Juego.addWindowListener(this);
		this.Juego.addMouseListener(this);
		this.Juego.dlgFin2.addWindowListener(this);
		this.Juego.dlgPlantar1.addWindowListener(this);
		this.Juego.dlgCambio.addWindowListener(this);
		this.Juego.dlgGana1.addWindowListener(this);
		this.Juego.dlgGana2.addWindowListener(this);
		this.Juego.dlgEmpate.addWindowListener(this);
		this.MenuPrincipal=mp;
		this.MenuPrincipal.btnNuevaPartida.addActionListener(this);
		this.MenuPrincipal.btnSalir.addActionListener(this);
		this.MenuPrincipal.btnVolver.addActionListener(this);
		this.MenuPrincipal.btnAceptar.addActionListener(this);
		this.MenuPrincipal.addWindowListener(this);
		this.MenuPrincipal.dlgNuevaPartida.addWindowListener(this);
		this.MenuPrincipal.btnRanking.addActionListener(this);
		this.MenuPrincipal.dlgRanking.addWindowListener(this);
		this.MenuPrincipal.btnAyuda.addActionListener(this);
		
		
		
	}
	

	@Override	public void windowActivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0)
	{ if(Juego.dlgFin2.isActive())
	{
		Juego.dlgFin2.setVisible(false);
	}
	if(Juego.dlgPlantar1.isActive())
	{
		Juego.dlgPlantar1.setVisible(false);
	}
	if(Juego.dlgCambio.isActive())
	{
		Juego.dlgCambio.setVisible(false);
	}
	if(Juego.dlgGana1.isActive())
	{
		Juego.dlgGana1.setVisible(false);
	}
	if(Juego.dlgGana2.isActive())
	{
		Juego.dlgGana2.setVisible(false);
	}
	if(Juego.dlgEmpate.isActive())
	{
		Juego.dlgEmpate.setVisible(false);
	}
	if(MenuPrincipal.dlgRanking.isActive())
	{
		MenuPrincipal.dlgRanking.setVisible(false);
	}
	if(Juego.isActive())
	{	Juego.setVisible(false);
		
		MenuPrincipal.txtJugador1.setText("");
		MenuPrincipal.txtJugador2.setText("");
		 jugadorEliminado1=0;
		 jugadorEliminado2=0;
		 jugadorPlantado1=0;
		 jugadorPlantado2=0;
		Juego.reiniciar();
		MenuPrincipal.setVisible(true);
	}	
	if(MenuPrincipal.isActive())
	{
		System.exit(0);
	}	
	}

	@Override
	public void windowDeactivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object a=arg0.getSource();
		
		if(a.equals(MenuPrincipal.btnSalir))
		{	conexion=modelo.cerrar(conexion);
			System.exit(0);
		}
		
		else if(a.equals(MenuPrincipal.btnNuevaPartida))
			{
			MenuPrincipal.dlgNuevaPartida.setVisible(true);
			}
		
		else if(a.equals(MenuPrincipal.btnVolver))
			{
			if(MenuPrincipal.dlgNuevaPartida.isActive())
			{
				MenuPrincipal.txtJugador1.setText("");
				MenuPrincipal.txtJugador2.setText("");
				MenuPrincipal.dlgNuevaPartida.setVisible(false);
			}
			}
		else if(a.equals(MenuPrincipal.btnAceptar))
			{
			if(((MenuPrincipal.txtJugador1.getText().length())!=0) && ((MenuPrincipal.txtJugador2.getText().length())!=0))
				{
					nombreJugador1=MenuPrincipal.txtJugador1.getText();
					nombreJugador2=MenuPrincipal.txtJugador2.getText();
					this.Juego.cambiarNombre(nombreJugador1,nombreJugador2);
					this.Juego.señalarNombre(turno);
					
				 if(MenuPrincipal.dlgNuevaPartida.isActive())
					{
						MenuPrincipal.dlgNuevaPartida.setVisible(false);
						this.Juego.setVisible(true);
						this.MenuPrincipal.setVisible(false);
					}
				}
			}	
		else if(a.equals(MenuPrincipal.btnRanking))
			{	
			datos=this.modelo.consulta(conexion);
			MenuPrincipal.txaConsulta.append(datos);
			this.modelo.cerrar(conexion);
			MenuPrincipal.dlgRanking.setVisible(true);
			}
		else if(a.equals(MenuPrincipal.btnAyuda))
		{
			modelo.ayuda();
		}
		
		}

	@Override
	public void mouseClicked(MouseEvent evento)
	{
		int x = evento.getX();
		int y = evento.getY();
		System.out.println(x+","+y);
		
		if((turno==0)&&(jugadorPlantado1==0)&&(jugadorEliminado1==0))
		{	
		if((x>=670)&&(x<=818)&&(y>=96)&&(y<=143)&&(turno==0))
		{
			modelo.puntosJugador1=puntosJugador1;
			Juego.dlgPlantar1.setVisible(true);
			turno=1;
			jugadorPlantado1=1;
			this.Juego.señalarNombre(turno);
		}
		if((x>=76)&&(x<=108)&&(y>=183)&&(y<=221)&&(turno==0))
		{
		marcaDiente[0]=0;
		this.Juego.marcarEquis(marcaDiente[0]);
		secreto=this.modelo.generar();
		System.out.println(secreto);
		if(secreto==1)
		{
			this.Juego.dlgFin2.setVisible(true);
			turno=1;
			jugadorEliminado1=1;
			this.Juego.señalarNombre(turno);
		}
		else
		{
			this.Juego.aumentarPuntos1();
			this.Juego.dlgCambio.setVisible(true);
			turno = 1;
			this.Juego.señalarNombre(turno);
		}	
		}
		if((x>=136)&&(x<=187)&&(y>=108)&&(y<=214)&&(turno==0))
		{
		marcaDiente[1]=1;
		this.Juego.marcarEquis(marcaDiente[1]);
		secreto=this.modelo.generar();
		System.out.println(secreto);
		if(secreto==1)
		{
			this.Juego.dlgFin2.setVisible(true);
			turno=1;
			jugadorEliminado1=1;
			this.Juego.señalarNombre(turno);
		}
		else
		{
			this.Juego.aumentarPuntos1();
			this.Juego.dlgCambio.setVisible(true);
			turno = 1;
			this.Juego.señalarNombre(turno);
		}	
		}
		 if((x>=185)&&(x<=205)&&(y>=187)&&(y<=193)&&(turno==0))
		{
		marcaDiente[2]=2;
		this.Juego.marcarEquis(marcaDiente[2]);
		secreto=this.modelo.generar();
		System.out.println(secreto);
		if(secreto==1)
		{
			this.Juego.dlgFin2.setVisible(true);
			turno=1;
			jugadorEliminado1=1;
			this.Juego.señalarNombre(turno);
		}
		else
		{
			this.Juego.aumentarPuntos1();
			this.Juego.dlgCambio.setVisible(true);
			turno = 1;
			this.Juego.señalarNombre(turno);
		}
			
		}
		 if((x>=228)&&(x<=257)&&(y>=187)&&(y<=214)&&(turno==0))
		{
		marcaDiente[3]=3;
		this.Juego.marcarEquis(marcaDiente[3]);
		secreto=this.modelo.generar();
		if(secreto==1)
		{
			this.Juego.dlgFin2.setVisible(true);
			turno=1;
			jugadorEliminado1=1;
			this.Juego.señalarNombre(turno);
		}
		else
		{
			this.Juego.aumentarPuntos1();
			this.Juego.dlgCambio.setVisible(true);
			turno = 1;
			this.Juego.señalarNombre(turno);
		}
			
		}
		 if((x>=283)&&(x<=310)&&(y>=183)&&(y<=222)&&(turno==0))
		{
		marcaDiente[4]=4;
		this.Juego.marcarEquis(marcaDiente[4]);
		secreto=this.modelo.generar();
		if(secreto==1)
		{
			this.Juego.dlgFin2.setVisible(true);
			turno=1;
			jugadorEliminado1=1;
			this.Juego.señalarNombre(turno);
		}
		else
		{
			this.Juego.aumentarPuntos1();
			this.Juego.dlgCambio.setVisible(true);
			turno = 1;
			this.Juego.señalarNombre(turno);
		}
			
		}
		 if((x>=331)&&(x<=350)&&(y>=181)&&(y<=191)&&(turno==0))
		{
		marcaDiente[5]=5;
		this.Juego.marcarEquis(marcaDiente[5]);
		secreto=this.modelo.generar();
		if(secreto==1)
		{
			this.Juego.dlgFin2.setVisible(true);
			turno=1;
			jugadorEliminado1=1;
			this.Juego.señalarNombre(turno);
		}
		else
		{
			this.Juego.aumentarPuntos1();
			this.Juego.dlgCambio.setVisible(true);
			turno = 1;
			this.Juego.señalarNombre(turno);
		}
			
		}
		if((x>=384)&&(x<=399)&&(y>=193)&&(y<=202)&&(turno==0))
		{
		marcaDiente[6]=6;
		this.Juego.marcarEquis(marcaDiente[6]);
		secreto=this.modelo.generar();
		if(secreto==1)
		{
			this.Juego.dlgFin2.setVisible(true);
			turno=1;
			jugadorEliminado1=1;
			this.Juego.señalarNombre(turno);
		}
		else
		{
			this.Juego.aumentarPuntos1();
			this.Juego.dlgCambio.setVisible(true);
			turno = 1;
			this.Juego.señalarNombre(turno);
		}
			
		}
		if((x>=425)&&(x<=441)&&(y>=205)&&(y<=213)&&(turno==0))
		{
		marcaDiente[7]=7;
		this.Juego.marcarEquis(marcaDiente[7]);
		secreto=this.modelo.generar();
		if(secreto==1)
		{
			this.Juego.dlgFin2.setVisible(true);
			turno=1;
			jugadorEliminado1=1;
			this.Juego.señalarNombre(turno);
		}
		else
		{
			this.Juego.aumentarPuntos1();
			this.Juego.dlgCambio.setVisible(true);
			turno = 1;
		}
			
		}
		 if((x>=473)&&(x<=492)&&(y>=252)&&(y<=279)&&(turno==0))
		{
		marcaDiente[8]=8;
		this.Juego.marcarEquis(marcaDiente[8]);
		secreto=this.modelo.generar();
		if(secreto==1)
		{
			this.Juego.dlgFin2.setVisible(true);
			turno=1;
			jugadorEliminado1=1;
			this.Juego.señalarNombre(turno);
		}
		else
		{
			this.Juego.aumentarPuntos1();
			this.Juego.dlgCambio.setVisible(true);
			turno = 1;
			this.Juego.señalarNombre(turno);
		}
			
		}
		 if((x>=436)&&(x<=455)&&(y>=296)&&(y<=324)&&(turno==0))
		{
		marcaDiente[9]=9;
		this.Juego.marcarEquis(marcaDiente[9]);
		secreto=this.modelo.generar();
		if(secreto==1)
		{
			this.Juego.dlgFin2.setVisible(true);
			turno=1;
			jugadorEliminado1=1;
			this.Juego.señalarNombre(turno);
		}
		else
		{
			this.Juego.aumentarPuntos1();
			this.Juego.dlgCambio.setVisible(true);
			turno = 1;
			this.Juego.señalarNombre(turno);
		}	
		
		}
		 if((x>=399)&&(x<=416)&&(y>=335)&&(y<=360)&&(turno==0))
		{
		marcaDiente[10]=10;
		this.Juego.marcarEquis(marcaDiente[10]);
		secreto=this.modelo.generar();
		if(secreto==1)
		{
			this.Juego.dlgFin2.setVisible(true);
			turno=1;
			jugadorEliminado1=1;
			this.Juego.señalarNombre(turno);
		}
		else
		{
			this.Juego.aumentarPuntos1();
			this.Juego.dlgCambio.setVisible(true);
			turno = 1;
			this.Juego.señalarNombre(turno);
		}
			
		}
		 if((x>=364)&&(x<=383)&&(y>=379)&&(y<=403)&&(turno==0))
		{
		marcaDiente[11]=11;
		this.Juego.marcarEquis(marcaDiente[11]);
		secreto=this.modelo.generar();
		if(secreto==1)
		{
			this.Juego.dlgFin2.setVisible(true);
			turno=1;
			jugadorEliminado1=1;
			this.Juego.señalarNombre(turno);
		}
		else
		{
			this.Juego.aumentarPuntos1();
			this.Juego.dlgCambio.setVisible(true);
			turno = 1;
			this.Juego.señalarNombre(turno);
		}	
			
		}
		 if((x>=312)&&(x<=342)&&(y>=376)&&(y<=439)&&(turno==0))
		{
		marcaDiente[12]=12;
		this.Juego.marcarEquis(marcaDiente[12]);
		secreto=this.modelo.generar();
		if(secreto==1)
		{
			this.Juego.dlgFin2.setVisible(true);
			turno=1;
			jugadorEliminado1=1;
			this.Juego.señalarNombre(turno);
		}
		else
		{
			this.Juego.aumentarPuntos1();
			this.Juego.dlgCambio.setVisible(true);
			turno = 1;
			this.Juego.señalarNombre(turno);
		}
			
		}
		 if((x>=240)&&(x<=262)&&(y>=415)&&(y<=432)&&(turno==0))
		{
		marcaDiente[13]=13;
		this.Juego.marcarEquis(marcaDiente[13]);
		secreto=this.modelo.generar();
		if(secreto==1)
		{
			this.Juego.dlgFin2.setVisible(true);
			turno=1;
			jugadorEliminado1=1;
			this.Juego.señalarNombre(turno);
		}
		else
		{
			this.Juego.aumentarPuntos1();
			this.Juego.dlgCambio.setVisible(true);
			turno = 1;
			this.Juego.señalarNombre(turno);
		}	
		
		}
		 if((x>=169)&&(x<=196)&&(y>=408)&&(y<=426)&&(turno==0))
		{
		marcaDiente[14]=14;
		this.Juego.marcarEquis(marcaDiente[14]);
		secreto=this.modelo.generar();
		if(secreto==1)
		{
			this.Juego.dlgFin2.setVisible(true);
			turno=1;
			jugadorEliminado1=1;
			this.Juego.señalarNombre(turno);
		}
		else
		{
			this.Juego.aumentarPuntos1();
			this.Juego.dlgCambio.setVisible(true);
			turno = 1;
			this.Juego.señalarNombre(turno);
		}
			
		}
		 if((x>=91)&&(x<=121)&&(y>=374)&&(y<=429)&&(turno==0))
		{
		marcaDiente[15]=15;
		this.Juego.marcarEquis(marcaDiente[15]);
		secreto=this.modelo.generar();
		if(secreto==1)
		{
			this.Juego.dlgFin2.setVisible(true);
			turno=1;
			jugadorEliminado1=1;
			this.Juego.señalarNombre(turno);
		}
		else
		{
			this.Juego.aumentarPuntos1();
			this.Juego.dlgCambio.setVisible(true);
			turno = 1;
			this.Juego.señalarNombre(turno);
		}	
			
		}
		 if((x>=141)&&(x<=161)&&(y>=378)&&(y<=405)&&(turno==0))
		{
		marcaDiente[16]=16;
		this.Juego.marcarEquis(marcaDiente[16]);
		secreto=this.modelo.generar();
		if(secreto==1)
		{
			this.Juego.dlgFin2.setVisible(true);
			turno=1;
			jugadorEliminado1=1;
			this.Juego.señalarNombre(turno);
		}
		else
		{
			this.Juego.aumentarPuntos1();
			this.Juego.dlgCambio.setVisible(true);
			turno = 1;
			this.Juego.señalarNombre(turno);
		}	
			
		}
		 if((x>=175)&&(x<=192)&&(y>=332)&&(y<=359)&&(turno==0))
		{
		marcaDiente[17]=17;
		this.Juego.marcarEquis(marcaDiente[17]);
		secreto=this.modelo.generar();
		if(secreto==1)
		{
			this.Juego.dlgFin2.setVisible(true);
			turno=1;
			jugadorEliminado1=1;
			this.Juego.señalarNombre(turno);
		}
		else
		{
			this.Juego.aumentarPuntos1();
			this.Juego.dlgCambio.setVisible(true);
			turno = 1;
			this.Juego.señalarNombre(turno);
		}	
			
		}
		 if((x>=212)&&(x<=233)&&(y>=294)&&(y<=323)&&(turno==0))
		{
		marcaDiente[18]=18;
		this.Juego.marcarEquis(marcaDiente[18]);
		secreto=this.modelo.generar();
		if(secreto==1)
		{
			this.Juego.dlgFin2.setVisible(true);
			turno=1;
			jugadorEliminado1=1;
			this.Juego.señalarNombre(turno);
		}
		else
		{
			this.Juego.aumentarPuntos1();
			this.Juego.dlgCambio.setVisible(true);
			turno = 1;
			this.Juego.señalarNombre(turno);
		}	
			
		}
		 if((x>=250)&&(x<=268)&&(y>=253)&&(y<=280)&&(turno==0))
		{
		marcaDiente[19]=19;
		this.Juego.marcarEquis(marcaDiente[19]);
		secreto=this.modelo.generar();
		if(secreto==1)
		{
			this.Juego.dlgFin2.setVisible(true);
			turno=1;
			jugadorEliminado1=1;
			this.Juego.señalarNombre(turno);
		}
		else
		{
			this.Juego.aumentarPuntos1();
			this.Juego.dlgCambio.setVisible(true);
			turno = 1;
			this.Juego.señalarNombre(turno);
		}	
			
		}
		 if((x>=670)&&(x<=818)&&(y>=496)&&(y<=540)&&(turno==0))
		{
		MenuPrincipal.txtJugador1.setText("");
		MenuPrincipal.txtJugador2.setText("");
		 jugadorEliminado1=0;
		 jugadorEliminado2=0;
		 jugadorPlantado1=0;
		 jugadorPlantado2=0;
		Juego.reiniciar();
		this.Juego.setVisible(false);
		this.MenuPrincipal.setVisible(true);
		}
		}
		else if((jugadorPlantado2==1)||(jugadorEliminado2==1))
		{
			turno=0;
			this.Juego.señalarNombre(turno);
		}
	
		else if((turno==1)&&(jugadorPlantado2==0)&&(jugadorEliminado2==0)) 
		{
			if((x>=670)&&(x<=818)&&(y>=196)&&(y<=245)&&(turno==1))
			{
				modelo.puntosJugador2=puntosJugador2;
				Juego.dlgPlantar1.setVisible(true);
				jugadorPlantado2=1;
				turno=0;
				this.Juego.señalarNombre(turno);
			}
			if((x>=76)&&(x<=108)&&(y>=183)&&(y<=221)&&(turno==1))
			{
			marcaDiente[0]=0;
			this.Juego.marcarEquis(marcaDiente[0]);
			secreto=this.modelo.generar();
			System.out.println(secreto);
			if(secreto==1)
			{
				this.Juego.dlgFin2.setVisible(true);
				jugadorEliminado2=1;
				turno=0;
				this.Juego.señalarNombre(turno);
				
			}
			else
			{
				this.Juego.aumentarPuntos2();
				this.Juego.dlgCambio.setVisible(true);
				turno = 0;
				this.Juego.señalarNombre(turno);
			}
				
			}
		 if((x>=136)&&(x<=187)&&(y>=108)&&(y<=214)&&(turno==1))
			{
			marcaDiente[1]=1;
			this.Juego.marcarEquis(marcaDiente[1]);
			secreto=this.modelo.generar();
			System.out.println(secreto);
			if(secreto==1)
			{
				this.Juego.dlgFin2.setVisible(true);
				jugadorEliminado2=1;
				turno=0;
				this.Juego.señalarNombre(turno);
			}
			else
			{
				this.Juego.aumentarPuntos2();
				this.Juego.dlgCambio.setVisible(true);
				turno = 0;
				this.Juego.señalarNombre(turno);
			}

			
			}
		 if((x>=185)&&(x<=205)&&(y>=187)&&(y<=193)&&(turno==1))
			{
			marcaDiente[2]=2;
			this.Juego.marcarEquis(marcaDiente[2]);
			secreto=this.modelo.generar();
			System.out.println(secreto);
			if(secreto==1)
			{
				this.Juego.dlgFin2.setVisible(true);
				jugadorEliminado2=1;
				turno=0;
				this.Juego.señalarNombre(turno);
			}
			else
			{
				this.Juego.aumentarPuntos2();
				this.Juego.dlgCambio.setVisible(true);
				turno = 0;
				this.Juego.señalarNombre(turno);
			}
			
			}
			 if((x>=228)&&(x<=257)&&(y>=187)&&(y<=214)&&(turno==1))
			{
			marcaDiente[3]=3;
			this.Juego.marcarEquis(marcaDiente[3]);
			secreto=this.modelo.generar();
			if(secreto==1)
			{
				this.Juego.dlgFin2.setVisible(true);
				jugadorEliminado2=1;
				turno=0;
				this.Juego.señalarNombre(turno);
			}
			else
			{
				this.Juego.aumentarPuntos2();
				this.Juego.dlgCambio.setVisible(true);
				turno = 0;
				this.Juego.señalarNombre(turno);
			}
				
			}
			 if((x>=283)&&(x<=310)&&(y>=183)&&(y<=222)&&(turno==1))
			{
			marcaDiente[4]=4;
			this.Juego.marcarEquis(marcaDiente[4]);
			secreto=this.modelo.generar();
			if(secreto==1)
			{
				this.Juego.dlgFin2.setVisible(true);
				jugadorEliminado2=1;
				turno=0;
				this.Juego.señalarNombre(turno);
			}
			else
			{
				this.Juego.aumentarPuntos2();
				this.Juego.dlgCambio.setVisible(true);
				turno = 0;
				this.Juego.señalarNombre(turno);
			}
			
			}
			 if((x>=331)&&(x<=350)&&(y>=181)&&(y<=191)&&(turno==1))
			{
			marcaDiente[5]=5;
			this.Juego.marcarEquis(marcaDiente[5]);
			secreto=this.modelo.generar();
			if(secreto==1)
			{
				this.Juego.dlgFin2.setVisible(true);
				jugadorEliminado2=1;
				turno=0;
				this.Juego.señalarNombre(turno);
			}
			else
			{
				this.Juego.aumentarPuntos2();
				this.Juego.dlgCambio.setVisible(true);
				turno = 0;
				this.Juego.señalarNombre(turno);
			}
				
			}
			 if((x>=384)&&(x<=399)&&(y>=193)&&(y<=202)&&(turno==1))
			{
			marcaDiente[6]=6;
			this.Juego.marcarEquis(marcaDiente[6]);
			secreto=this.modelo.generar();
			if(secreto==1)
			{
				this.Juego.dlgFin2.setVisible(true);
				jugadorEliminado2=1;
				turno=0;
				this.Juego.señalarNombre(turno);
			}
			else
			{
				this.Juego.aumentarPuntos2();
				this.Juego.dlgCambio.setVisible(true);
				turno = 0;
				this.Juego.señalarNombre(turno);
			}
				
			}
			 if((x>=425)&&(x<=441)&&(y>=205)&&(y<=213)&&(turno==1))
			{
			marcaDiente[7]=7;
			this.Juego.marcarEquis(marcaDiente[7]);
			secreto=this.modelo.generar();
			if(secreto==1)
			{
				this.Juego.dlgFin2.setVisible(true);
				jugadorEliminado2=1;
				turno=0;
				this.Juego.señalarNombre(turno);
			}
			else
			{
				this.Juego.aumentarPuntos2();
				this.Juego.dlgCambio.setVisible(true);
				turno = 0;
				this.Juego.señalarNombre(turno);
			}
				
			}
			 if((x>=473)&&(x<=492)&&(y>=252)&&(y<=279)&&(turno==1))
			{
			marcaDiente[8]=8;
			this.Juego.marcarEquis(marcaDiente[8]);
			secreto=this.modelo.generar();
			if(secreto==1)
			{
				this.Juego.dlgFin2.setVisible(true);
				jugadorEliminado2=1;
				turno=0;
				this.Juego.señalarNombre(turno);
			}
			else
			{
				this.Juego.aumentarPuntos2();
				this.Juego.dlgCambio.setVisible(true);
				turno = 0;
				this.Juego.señalarNombre(turno);
			}
			
			}
			 if((x>=436)&&(x<=455)&&(y>=296)&&(y<=324)&&(turno==1))
			{
			marcaDiente[9]=9;
			this.Juego.marcarEquis(marcaDiente[9]);
			secreto=this.modelo.generar();
			if(secreto==1)
			{
				this.Juego.dlgFin2.setVisible(true);
				jugadorEliminado2=1;
				turno=0;
				this.Juego.señalarNombre(turno);
			}
			else
			{
				this.Juego.aumentarPuntos2();
				this.Juego.dlgCambio.setVisible(true);
				turno = 0;
				this.Juego.señalarNombre(turno);
			}	
				
			}
			 if((x>=399)&&(x<=416)&&(y>=335)&&(y<=360)&&(turno==1))
			{
			marcaDiente[10]=10;
			this.Juego.marcarEquis(marcaDiente[10]);
			secreto=this.modelo.generar();
			if(secreto==1)
			{
				this.Juego.dlgFin2.setVisible(true);
				jugadorEliminado2=1;
				turno=0;
				this.Juego.señalarNombre(turno);
			}
			else
			{
				this.Juego.aumentarPuntos2();
				this.Juego.dlgCambio.setVisible(true);
				turno = 0;
				this.Juego.señalarNombre(turno);
			}
			
			}
			 if((x>=364)&&(x<=383)&&(y>=379)&&(y<=403)&&(turno==1))
			{
			marcaDiente[11]=11;
			this.Juego.marcarEquis(marcaDiente[11]);
			secreto=this.modelo.generar();
			if(secreto==1)
			{
				this.Juego.dlgFin2.setVisible(true);
				jugadorEliminado2=1;
				turno=0;
				this.Juego.señalarNombre(turno);
			}
			else
			{
				this.Juego.aumentarPuntos2();
				this.Juego.dlgCambio.setVisible(true);
				turno = 0;
			}	
				
			}
			 if((x>=312)&&(x<=342)&&(y>=376)&&(y<=439)&&(turno==1))
			{
			marcaDiente[12]=12;
			this.Juego.marcarEquis(marcaDiente[12]);
			secreto=this.modelo.generar();
			if(secreto==1)
			{
				this.Juego.dlgFin2.setVisible(true);
				jugadorEliminado2=1;
				turno=0;
				this.Juego.señalarNombre(turno);
			}
			else
			{
				this.Juego.aumentarPuntos2();
				this.Juego.dlgCambio.setVisible(true);
				turno = 0;
				this.Juego.señalarNombre(turno);
			}
				
			}
			 if((x>=240)&&(x<=262)&&(y>=415)&&(y<=432)&&(turno==1))
			{
			marcaDiente[13]=13;
			this.Juego.marcarEquis(marcaDiente[13]);
			secreto=this.modelo.generar();
			if(secreto==1)
			{
				this.Juego.dlgFin2.setVisible(true);
				jugadorEliminado2=1;
				turno=0;
				this.Juego.señalarNombre(turno);
			}
			else
			{
				this.Juego.aumentarPuntos2();
				this.Juego.dlgCambio.setVisible(true);
				turno = 0;
				this.Juego.señalarNombre(turno);
			}	
				
			}
			 if((x>=169)&&(x<=196)&&(y>=408)&&(y<=426)&&(turno==1))
			{
			marcaDiente[14]=14;
			this.Juego.marcarEquis(marcaDiente[14]);
			secreto=this.modelo.generar();
			if(secreto==1)
			{
				this.Juego.dlgFin2.setVisible(true);
				jugadorEliminado2=1;
				turno=0;
				this.Juego.señalarNombre(turno);
			}
			else
			{
				this.Juego.aumentarPuntos2();
				this.Juego.dlgCambio.setVisible(true);
				turno = 0;
				this.Juego.señalarNombre(turno);
			}
				
			}
			 if((x>=91)&&(x<=121)&&(y>=374)&&(y<=429)&&(turno==1))
			{
			marcaDiente[15]=15;
			this.Juego.marcarEquis(marcaDiente[15]);
			secreto=this.modelo.generar();
			if(secreto==1)
			{
				this.Juego.dlgFin2.setVisible(true);
				jugadorEliminado2=1;
				turno=0;
				this.Juego.señalarNombre(turno);
			}
			else
			{
				this.Juego.aumentarPuntos2();
				this.Juego.dlgCambio.setVisible(true);
				turno = 0;
				this.Juego.señalarNombre(turno);
			}	
			
			}
			 if((x>=141)&&(x<=161)&&(y>=378)&&(y<=405)&&(turno==1))
			{
			marcaDiente[16]=16;
			this.Juego.marcarEquis(marcaDiente[16]);
			secreto=this.modelo.generar();
			if(secreto==1)
			{
				this.Juego.dlgFin2.setVisible(true);
				jugadorEliminado2=1;
				turno=0;
				this.Juego.señalarNombre(turno);
			}
			else
			{
				this.Juego.aumentarPuntos2();
				this.Juego.dlgCambio.setVisible(true);
				turno = 0;
				this.Juego.señalarNombre(turno);
			}	
				
			}
			 if((x>=175)&&(x<=192)&&(y>=332)&&(y<=359)&&(turno==1))
			{
			marcaDiente[17]=17;
			this.Juego.marcarEquis(marcaDiente[17]);
			secreto=this.modelo.generar();
			if(secreto==1)
			{
				this.Juego.dlgFin2.setVisible(true);
				jugadorEliminado2=1;
				turno=0;
				this.Juego.señalarNombre(turno);
			}
			else
			{
				this.Juego.aumentarPuntos2();
				this.Juego.dlgCambio.setVisible(true);
				turno = 0;
				this.Juego.señalarNombre(turno);
			}	
				
			}
			 if((x>=212)&&(x<=233)&&(y>=294)&&(y<=323)&&(turno==1))
			{
			marcaDiente[18]=18;
			this.Juego.marcarEquis(marcaDiente[18]);
			secreto=this.modelo.generar();
			if(secreto==1)
			{
				this.Juego.dlgFin2.setVisible(true);
				jugadorEliminado2=1;
				turno=0;
				this.Juego.señalarNombre(turno);
			}
			else
			{
				this.Juego.aumentarPuntos2();
				this.Juego.dlgCambio.setVisible(true);
				turno = 0;
				this.Juego.señalarNombre(turno);
			}	
				
			}
			 if((x>=250)&&(x<=268)&&(y>=253)&&(y<=280)&&(turno==1))
			{
			marcaDiente[19]=19;
			this.Juego.marcarEquis(marcaDiente[19]);
			secreto=this.modelo.generar();
			if(secreto==1)
			{
				this.Juego.dlgFin2.setVisible(true);
				jugadorEliminado2=1;
				turno=0;
				this.Juego.señalarNombre(turno);
			}
			else
			{
				this.Juego.aumentarPuntos2();
				this.Juego.dlgCambio.setVisible(true);
				turno = 0;
				this.Juego.señalarNombre(turno);
			}	
				
			}
			 if((x>=670)&&(x<=818)&&(y>=496)&&(y<=540)&&(turno==1))
			{
			MenuPrincipal.txtJugador1.setText("");
			MenuPrincipal.txtJugador2.setText("");
			Juego.reiniciar();
			this.Juego.setVisible(false);
			this.MenuPrincipal.setVisible(true);
			}
			else if((jugadorPlantado1==1)||(jugadorEliminado1==1))
				{
					turno=1;
					this.Juego.señalarNombre(turno);
				}
		
		}
		 if ((jugadorEliminado1==1)&&(jugadorEliminado2==1)
				 ||(jugadorEliminado1==1)&&(jugadorPlantado2==1)
				 ||(jugadorPlantado1==1)&&(jugadorEliminado2==1)
			 	||(jugadorPlantado1==1)&&(jugadorPlantado2==1))
		 {
			 if(jugadorEliminado1==1)
			 {
				 modelo.puntosJugador1=0;
			 }
			 if(jugadorEliminado2==1)
			 {
				 modelo.puntosJugador2=0;
			 }
			 if(Juego.puntosJugador1>Juego.puntosJugador2)
			 {
				 this.Juego.dlgGana1.setVisible(true);
			
			 }
			 else if(Juego.puntosJugador1<Juego.puntosJugador2)
			 {
				 this.Juego.dlgGana2.setVisible(true);
				
			 }
			 else if(Juego.puntosJugador1==Juego.puntosJugador2)
			 {
				 this.Juego.dlgEmpate.setVisible(true);
				
			 }
			 modelo.altaPartida(conexion,nombreJugador1,nombreJugador2,Juego.puntosJugador1,Juego.puntosJugador2);
			 jugadorEliminado1=0;
			 jugadorEliminado2=0;
			 jugadorPlantado1=0;
			 jugadorPlantado2=0;
			 
		 }
		}
	

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
		}
		
		



