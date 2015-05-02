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
import javax.persistence.Lob;
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
@Table(name = "posteos_comentario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PosteosComentario.findAll", query = "SELECT p FROM PosteosComentario p"),
    @NamedQuery(name = "PosteosComentario.findByIdComentario", query = "SELECT p FROM PosteosComentario p WHERE p.idComentario = :idComentario"),
    @NamedQuery(name = "PosteosComentario.findByFecha", query = "SELECT p FROM PosteosComentario p WHERE p.fecha = :fecha")})
public class PosteosComentario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comentario")
    private Integer idComentario;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "comentario")
    private String comentario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "id_post", referencedColumnName = "id_post")
    @ManyToOne(optional = false)
    private ForoPosteos idPost;
    @JoinColumn(name = "posteador", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario posteador;

    public PosteosComentario() {
    }

    public PosteosComentario(Integer idComentario) {
        this.idComentario = idComentario;
    }

    public PosteosComentario(Integer idComentario, String comentario, Date fecha) {
        this.idComentario = idComentario;
        this.comentario = comentario;
        this.fecha = fecha;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ForoPosteos getIdPost() {
        return idPost;
    }

    public void setIdPost(ForoPosteos idPost) {
        this.idPost = idPost;
    }

    public Usuario getPosteador() {
        return posteador;
    }

    public void setPosteador(Usuario posteador) {
        this.posteador = posteador;
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
        if (!(object instanceof PosteosComentario)) {
            return false;
        }
        PosteosComentario other = (PosteosComentario) object;
        if ((this.idComentario == null && other.idComentario != null) || (this.idComentario != null && !this.idComentario.equals(other.idComentario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ENTITIES.PosteosComentario[ idComentario=" + idComentario + " ]";
    }
    
}
