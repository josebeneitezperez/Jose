package clasesVO;

public class Departamento implements java.io.Serializable {

	private int codigo;
	private String nombre;
	private int codResponsable;

	public Departamento() {
	}

	public Departamento(int codigo, String nombre, int codResponsable) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.codResponsable = codResponsable;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCodResponsable() {
		return this.codResponsable;
	}

	public void setCodResponsable(int codResponsable) {
		this.codResponsable = codResponsable;
	}
}
