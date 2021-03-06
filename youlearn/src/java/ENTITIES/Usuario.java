/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ENTITIES;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.logIn", query = "SELECT u FROM Usuario u WHERE  u.username = :username AND u.password =:password"),
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findByUsername", query = "SELECT u FROM Usuario u WHERE u.username = :username"),
    @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password"),
    @NamedQuery(name = "Usuario.findByFechaCreacion", query = "SELECT u FROM Usuario u WHERE u.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo"),
    @NamedQuery(name = "Usuario.findByCreditos", query = "SELECT u FROM Usuario u WHERE u.creditos = :creditos")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Size(max = 50)
    @Column(name = "correo")
    private String correo;
    @Column(name = "creditos")
    private Integer creditos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "posteador")
    private List<PosteosComentario> posteosComentarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Sesion> sesionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComentador")
    private List<ComentarioPublicacion> comentarioPublicacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<MensajesTablero> mensajesTableroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPublicador")
    private List<PublicacionPerfil> publicacionPerfilList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<PublicacionPerfil> publicacionPerfilList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario1")
    private List<Amigos> amigosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario2")
    private List<Amigos> amigosList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idReceptor")
    private List<MensajePara> mensajeParaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private List<ForoPosteos> foroPosteosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<InscripcionCurso> inscripcionCursoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Curso> cursoList;
    @JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil")
    @ManyToOne(optional = false)
    private Perfil idPerfil;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmisor")
    private List<Mensaje> mensajeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Tablero> tableroList;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String username, String password, Date fechaCreacion) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    @XmlTransient
    public List<PosteosComentario> getPosteosComentarioList() {
        return posteosComentarioList;
    }

    public void setPosteosComentarioList(List<PosteosComentario> posteosComentarioList) {
        this.posteosComentarioList = posteosComentarioList;
    }

    @XmlTransient
    public List<Sesion> getSesionList() {
        return sesionList;
    }

    public void setSesionList(List<Sesion> sesionList) {
        this.sesionList = sesionList;
    }

    @XmlTransient
    public List<ComentarioPublicacion> getComentarioPublicacionList() {
        return comentarioPublicacionList;
    }

    public void setComentarioPublicacionList(List<ComentarioPublicacion> comentarioPublicacionList) {
        this.comentarioPublicacionList = comentarioPublicacionList;
    }

    @XmlTransient
    public List<MensajesTablero> getMensajesTableroList() {
        return mensajesTableroList;
    }

    public void setMensajesTableroList(List<MensajesTablero> mensajesTableroList) {
        this.mensajesTableroList = mensajesTableroList;
    }

    @XmlTransient
    public List<PublicacionPerfil> getPublicacionPerfilList() {
        return publicacionPerfilList;
    }

    public void setPublicacionPerfilList(List<PublicacionPerfil> publicacionPerfilList) {
        this.publicacionPerfilList = publicacionPerfilList;
    }

    @XmlTransient
    public List<PublicacionPerfil> getPublicacionPerfilList1() {
        return publicacionPerfilList1;
    }

    public void setPublicacionPerfilList1(List<PublicacionPerfil> publicacionPerfilList1) {
        this.publicacionPerfilList1 = publicacionPerfilList1;
    }

    @XmlTransient
    public List<Amigos> getAmigosList() {
        return amigosList;
    }

    public void setAmigosList(List<Amigos> amigosList) {
        this.amigosList = amigosList;
    }

    @XmlTransient
    public List<Amigos> getAmigosList1() {
        return amigosList1;
    }

    public void setAmigosList1(List<Amigos> amigosList1) {
        this.amigosList1 = amigosList1;
    }

    @XmlTransient
    public List<MensajePara> getMensajeParaList() {
        return mensajeParaList;
    }

    public void setMensajeParaList(List<MensajePara> mensajeParaList) {
        this.mensajeParaList = mensajeParaList;
    }

    @XmlTransient
    public List<ForoPosteos> getForoPosteosList() {
        return foroPosteosList;
    }

    public void setForoPosteosList(List<ForoPosteos> foroPosteosList) {
        this.foroPosteosList = foroPosteosList;
    }

    @XmlTransient
    public List<InscripcionCurso> getInscripcionCursoList() {
        return inscripcionCursoList;
    }

    public void setInscripcionCursoList(List<InscripcionCurso> inscripcionCursoList) {
        this.inscripcionCursoList = inscripcionCursoList;
    }

    @XmlTransient
    public List<Curso> getCursoList() {
        return cursoList;
    }

    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }

    public Perfil getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Perfil idPerfil) {
        this.idPerfil = idPerfil;
    }

    @XmlTransient
    public List<Mensaje> getMensajeList() {
        return mensajeList;
    }

    public void setMensajeList(List<Mensaje> mensajeList) {
        this.mensajeList = mensajeList;
    }

    @XmlTransient
    public List<Tablero> getTableroList() {
        return tableroList;
    }

    public void setTableroList(List<Tablero> tableroList) {
        this.tableroList = tableroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ENTITIES.Usuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
