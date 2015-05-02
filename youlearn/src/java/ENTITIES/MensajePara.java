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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "mensaje_para")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MensajePara.findAll", query = "SELECT m FROM MensajePara m"),
    @NamedQuery(name = "MensajePara.findByIdMensaje", query = "SELECT m FROM MensajePara m WHERE m.idMensaje = :idMensaje"),
    @NamedQuery(name = "MensajePara.findByFechaEnvio", query = "SELECT m FROM MensajePara m WHERE m.fechaEnvio = :fechaEnvio")})
public class MensajePara implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_mensaje")
    private Integer idMensaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_envio")
    @Temporal(TemporalType.DATE)
    private Date fechaEnvio;
    @JoinColumn(name = "id_mensaje", referencedColumnName = "id_mensaje", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Mensaje mensaje;
    @JoinColumn(name = "id_receptor", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idReceptor;

    public MensajePara() {
    }

    public MensajePara(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public MensajePara(Integer idMensaje, Date fechaEnvio) {
        this.idMensaje = idMensaje;
        this.fechaEnvio = fechaEnvio;
    }

    public Integer getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    public Usuario getIdReceptor() {
        return idReceptor;
    }

    public void setIdReceptor(Usuario idReceptor) {
        this.idReceptor = idReceptor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMensaje != null ? idMensaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MensajePara)) {
            return false;
        }
        MensajePara other = (MensajePara) object;
        if ((this.idMensaje == null && other.idMensaje != null) || (this.idMensaje != null && !this.idMensaje.equals(other.idMensaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ENTITIES.MensajePara[ idMensaje=" + idMensaje + " ]";
    }
    
}
