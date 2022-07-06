package eaj.ufrn.appweb.model;

public class Usuario {
    private String id_usuario;
	private String senha;
	private String nome;
	private int cargo;
	public Usuario(String id_usuario, String senha, String nome, int cargo) {
		this.id_usuario = id_usuario;
		this.senha = senha;
		this.nome = nome;
		this.cargo = cargo;
	}
	public Usuario() {
		
	}
	public String get_id_usuario() {
		return id_usuario;
	}
	public String get_senha() {
		return senha;
	}
	public String get_nome() {
		return nome;
	}
	public int get_cargo() {
		return cargo;
	}
}
