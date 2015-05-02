/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ENTITIES;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "comentario_publicacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComentarioPublicacion.findAll", query = "SELECT c FROM ComentarioPublicacion c"),
    @NamedQuery(name = "ComentarioPublicacion.findByIdComentario", query = "SELECT c FROM ComentarioPublicacion c WHERE c.idComentario = :idComentario"),
    @NamedQuery(name = "ComentarioPublicacion.findByComentario", query = "SELECT c FROM ComentarioPublicacion c WHERE c.comentario = :comentario")})
public class ComentarioPublicacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comentario")
    private Integer idComentario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "comentario")
    private String comentario;
    @JoinColumn(name = "id_publicacion", referencedColumnName = "id_publicacion")
    @ManyToOne(optional = false)
    private PublicacionPerfil idPublicacion;
    @JoinColumn(name = "id_comentador", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idComentador;

    public ComentarioPublicacion() {
    }

    public ComentarioPublicacion(Integer idComentario) {
        this.idComentario = idComentario;
    }

    public ComentarioPublicacion(Integer idComentario, String comentario) {
        this.idComentario = idComentario;
        this.comentario = comentario;
    }

    public Integer getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Integer idComentario) {
        this.idComentario = idComentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public PublicacionPerfil getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(PublicacionPerfil idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public Usuario getIdComentador() {
        return idComentador;
    }

    public void setIdComentador(Usuario idComentador) {
        this.idComentador = idComentador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComentario != null ? idComentario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComentarioPublicacion)) {
            return false;
        }
        ComentarioPublicacion other = (ComentarioPublicacion) object;
        if ((this.idComentario == null && other.idComentario != null) || (this.idComentario != null && !this.idComentario.equals(other.idComentario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ENTITIES.ComentarioPublicacion[ idComentario=" + idComentario + " ]";
    }
    
}
