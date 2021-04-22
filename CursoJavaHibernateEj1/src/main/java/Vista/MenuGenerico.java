package Vista;

public class MenuGenerico {

	private String[] opcionesMenu;
	private int numOpciones;
	public static final int SALIR = 0;
	
	MenuGenerico(String [] opcionesMenu){
		
		this.opcionesMenu = opcionesMenu;
		numOpciones = opcionesMenu.length;
	}
	
	public void mostrarMenu(){
		
		System.out.println("Menú");
		for(int i = 0; i<opcionesMenu.length;i++) {
			
			System.out.println(opcionesMenu[i]);
		}
	}

	public String[] getOpcionesMenu() {
		return opcionesMenu;
	}

	public void setOpcionesMenu(String[] opcionesMenu) {
		this.opcionesMenu = opcionesMenu;
		numOpciones = opcionesMenu.length;
	}
	
	public int getNumOpciones() {
		return numOpciones;
	}
}
