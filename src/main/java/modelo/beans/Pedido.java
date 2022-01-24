package modelo.beans;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the pedidos database table.
 * 
 */
@Entity
@Table(name="pedidos")
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PEDIDO")
	private int idPedido;

	@Column(name="DIRECCION_ENTREGA")
	private String direccionEntrega;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ALTA")
	private Date fechaAlta;

	//bi-directional many-to-one association to LineaPedido
	@OneToMany(mappedBy="pedido", cascade={CascadeType.PERSIST})
	private List<LineaPedido> lineaPedidos;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="USERNAME")
	private Usuario usuario;

	public Pedido() {
	}

	public int getIdPedido() {
		return this.idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public String getDireccionEntrega() {
		return this.direccionEntrega;
	}

	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public List<LineaPedido> getLineaPedidos() {
		return this.lineaPedidos;
	}

	public void setLineaPedidos(List<LineaPedido> lineaPedidos) {
		this.lineaPedidos = lineaPedidos;
	}

	public LineaPedido addLineaPedido(LineaPedido lineaPedido) {
		if (lineaPedidos == null) {
			lineaPedidos = new ArrayList <LineaPedido> ();
		}
		getLineaPedidos().add(lineaPedido);
		lineaPedido.setPedido(this);

		return lineaPedido;
	}

	public LineaPedido removeLineaPedido(LineaPedido lineaPedido) {
		getLineaPedidos().remove(lineaPedido);
		lineaPedido.setPedido(null);

		return lineaPedido;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}