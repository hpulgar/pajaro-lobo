/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ENTITIES;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "mensajes_tablero")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MensajesTablero.findAll", query = "SELECT m FROM MensajesTablero m"),
    @NamedQuery(name = "MensajesTablero.findByIdMensajes", query = "SELECT m FROM MensajesTablero m WHERE m.idMensajes = :idMensajes"),
    @NamedQuery(name = "MensajesTablero.findByMensaje", query = "SELECT m FROM MensajesTablero m WHERE m.mensaje = :mensaje"),
    @NamedQuery(name = "MensajesTablero.findByFechaComentario", query = "SELECT m FROM MensajesTablero m WHERE m.fechaComentario = :fechaComentario")})
public class MensajesTablero implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mensajes")
    private Integer idMensajes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "mensaje")
    private String mensaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_comentario")
    @Temporal(TemporalType.DATE)
    private Date fechaComentario;
    @JoinColumn(name = "id_tablero", referencedColumnName = "id_tablero")
    @ManyToOne(optional = false)
    private Tablero idTablero;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public MensajesTablero() {
    }

    public MensajesTablero(Integer idMensajes) {
        this.idMensajes = idMensajes;
    }

    public MensajesTablero(Integer idMensajes, String mensaje, Date fechaComentario) {
        this.idMensajes = idMensajes;
        this.mensaje = mensaje;
        this.fechaComentario = fechaComentario;
    }

    public Integer getIdMensajes() {
        return idMensajes;
    }

    public void setIdMensajes(Integer idMensajes) {
        this.idMensajes = idMensajes;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(Date fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public Tablero getIdTablero() {
        return idTablero;
    }

    public void setIdTablero(Tablero idTablero) {
        this.idTablero = idTablero;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMensajes != null ? idMensajes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MensajesTablero)) {
            return false;
        }
        MensajesTablero other = (MensajesTablero) object;
        if ((this.idMensajes == null && other.idMensajes != null) || (this.idMensajes != null && !this.idMensajes.equals(other.idMensajes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ENTITIES.MensajesTablero[ idMensajes=" + idMensajes + " ]";
    }
    
}
