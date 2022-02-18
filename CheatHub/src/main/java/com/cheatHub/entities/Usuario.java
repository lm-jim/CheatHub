package com.cheatHub.entities;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	
	@OneToMany(mappedBy="usuario",cascade=CascadeType.ALL)
	private List<Comentario> listaComentarios;
	
	@OneToMany(mappedBy="usuario",cascade=CascadeType.ALL)
	private List<Publicacion> listaPublicaciones;
	
	public Usuario() {}
	
	public Usuario(String nombreUsuario, String contraseña) {
		this.nombreUsuario = nombreUsuario;
		this.contraseña = contraseña;
		this.fechaRegistro = new Date();
		this.listaComentarios=new ArrayList<>();
		this.listaPublicaciones=new ArrayList<>();
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
	
	public void addComentario(Comentario comentario) {
		if(!listaComentarios.contains(comentario))
			listaComentarios.add(comentario);
	}
	
	public void addPublicacion(Publicacion publicacion) {
		if(!listaPublicaciones.contains(publicacion))
			listaPublicaciones.add(publicacion);
	}
}
