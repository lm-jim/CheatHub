package com.cheatHub.entities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Usuario {
	
	
	@Id
	private String nombreUsuario;
	
	private String contraseña;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date fechaRegistro;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date fechaNacimiento;
	
	private String avatar; //URL (?)
	private String nombreReal;
	private String descripcion;
	private String correo;
	
	@OneToMany(mappedBy="usuario",cascade=CascadeType.ALL)
	private List<Comentario> listaComentarios;
	
	@OneToMany(mappedBy="usuario",cascade=CascadeType.ALL)
	private List<Publicacion> listaPublicaciones;
	
	@ManyToMany
	private List<Videojuego> listaJuegosSeguidos;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	
	public Usuario() {}
	
	public Usuario(String nombreUsuario, String contraseña,String correo) {
		this.nombreUsuario = nombreUsuario;
		this.contraseña = contraseña;
		this.correo=correo;
		this.fechaRegistro = new Date();
		this.listaComentarios=new ArrayList<>();
		this.listaPublicaciones=new ArrayList<>();
		this.listaPublicaciones=new ArrayList<>();
		this.avatar = "https://www.royalunibrew.com/wp-content/uploads/2021/07/blank-profile-picture-973460_640.png";
	}
	
	public Usuario(String nombreUsuario,String contraseña,String correo, String nombreReal,String descripcion,Date fechaNacimiento, String avatar) {
		this.nombreUsuario = nombreUsuario;
		this.contraseña = contraseña;
		this.correo=correo;
		this.fechaRegistro = new Date();
		this.listaComentarios=new ArrayList<>();
		this.listaPublicaciones=new ArrayList<>();
		this.nombreReal=nombreReal;
		this.descripcion=descripcion;
		this.fechaNacimiento=fechaNacimiento;
		this.avatar = avatar;
		this.roles = new ArrayList<>();
		this.roles.add("USER");
		this.roles = Collections.unmodifiableList(this.roles);
	}
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombreReal() {
		return nombreReal;
	}

	public void setNombreReal(String nombreReal) {
		this.nombreReal = nombreReal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Comentario> getListaComentarios() {
		return listaComentarios;
	}

	public void setListaComentarios(List<Comentario> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}

	public List<Publicacion> getListaPublicaciones() {
		return listaPublicaciones;
	}

	public void setListaPublicaciones(List<Publicacion> listaPublicaciones) {
		this.listaPublicaciones = listaPublicaciones;
	}
	
	public List<Videojuego> getListaJuegosSeguidos() {
		return listaJuegosSeguidos;
	}

	public void setListaJuegosSeguidos(List<Videojuego> listaJuegosSeguidos) {
		this.listaJuegosSeguidos = listaJuegosSeguidos;
	}

	public void addComentario(Comentario comentario) {
		if(!listaComentarios.contains(comentario))
			listaComentarios.add(comentario);
	}
	
	public void addPublicacion(Publicacion publicacion) {
		if(!listaPublicaciones.contains(publicacion))
			listaPublicaciones.add(publicacion);
	}
	
	public void addJuegoSeguido(Videojuego videojuego) {
		if(!listaJuegosSeguidos.contains(videojuego))
			listaJuegosSeguidos.add(videojuego);
	}
	
	public void removeJuegoSeguido(Videojuego videojuego) {
		if(listaJuegosSeguidos.contains(videojuego))
			listaJuegosSeguidos.remove(videojuego);
	}
	
	@Override
	public String toString() {
		return nombreUsuario;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
}
