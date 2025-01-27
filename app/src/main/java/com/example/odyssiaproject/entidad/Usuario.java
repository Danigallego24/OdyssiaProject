package com.example.odyssiaproject.entidad;

public class Usuario {
   private int id;
   private String nombre, apellido, fechaNacimiento,nacionalidad, contraseña, correo;
   private Sexo sexo;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public String getFechaNacimiento() {
      return fechaNacimiento;
   }

   public void setFechaNacimiento(String fechaNacimiento) {
      this.fechaNacimiento = fechaNacimiento;
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

   public Sexo getSexo() {
      return sexo;
   }

   public void setSexo(Sexo sexo) {
      this.sexo = sexo;
   }

   public String getNacionalidad() {
      return nacionalidad;
   }

   public void setNacionalidad(String nacionalidad) {
      this.nacionalidad = nacionalidad;
   }

   public String getApellido() {
      return apellido;
   }

   public void setApellido(String apellido) {
      this.apellido = apellido;
   }

   @Override
   public String toString() {
      return "Usuario{" +
              "id=" + id +
              ", nombre='" + nombre + '\'' +
              ", apellido='" + apellido + '\'' +
              ", fechaNacimiento='" + fechaNacimiento + '\'' +
              ", nacionalidad='" + nacionalidad + '\'' +
              ", contraseña='" + contraseña + '\'' +
              ", correo='" + correo + '\'' +
              ", sexo=" + sexo +
              '}';
   }
}
