package com.aluracurso.forohub.usuario;

public class LoginDTO {
    private String usurname;
    private String password;

    public String getUsername() {
        return usurname;
    }

    public void setUsername(String usuario) {
        this.usurname = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
