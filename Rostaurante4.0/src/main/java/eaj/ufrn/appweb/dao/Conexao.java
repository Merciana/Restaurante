package eaj.ufrn.appweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private String usuario;
	private String senha;
	private String caminho;
	private Connection con;
	public Conexao(String caminho,String usuario,String senha) {
		this.caminho=caminho;
		this.usuario=usuario;
		this.senha=senha;
	}

    public void conectar(){
		try {
			Class.forName("org.postgresql.Driver");//carrega o drive
			con = DriverManager.getConnection(caminho,usuario,senha);//cria a conexao
		}
		catch(Exception e){
			System.out.println("erro na conexao");
		}
	}

    public void desConectar() {
		try{
			con.close();
		}
		catch(Exception e){
			System.out.println("erro na desconexao");
		}
	}

    public Connection getConection() {
		return con;
	}
    
}