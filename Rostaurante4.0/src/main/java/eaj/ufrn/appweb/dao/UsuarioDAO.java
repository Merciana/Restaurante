package eaj.ufrn.appweb.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eaj.ufrn.appweb.model.Usuario;

public class UsuarioDAO {
    private Conexao acessoBanco;
	private final String consulta = "SELECT * FROM usuario WHERE pk_id_usuario = ? AND senha = ?";
	private final String cadastro_usuario = "INSERT INTO usuario (pk_id_usuario,senha,nome,cargo) VALUES (?,?,?,?)";
	private final String verificar_usuario = "SELECT * FROM usuario WHERE email = ?";
	
	public UsuarioDAO() {
		acessoBanco = new Conexao("jdbc:postgresql://localhost:5432/restaurante_bd","postgres","123");
	}
			
	public Usuario consultar_usuario(String usuario, String senha) {
		Usuario objLogin = null;
		try {
			acessoBanco.conectar();
			PreparedStatement instrucao = acessoBanco.getConection().prepareStatement(consulta);
			instrucao.setString(1, usuario);
			instrucao.setString(2, senha);
			ResultSet rs = instrucao.executeQuery();
			if(rs.next()) {
				objLogin = new Usuario(rs.getString("pk_id_usuario"),rs.getString("senha"),rs.getString("nome"),rs.getInt("cargo"));
			}
			acessoBanco.desConectar();
		}
		catch(SQLException e){
            System.out.println("falha na canexao com o banco de dados");
        }
		return objLogin;
	}
	
	public void cadastrar_usuario(String id, String senha, String nome, int cargo) {
		try{
			acessoBanco.conectar();
			PreparedStatement instrucao = acessoBanco.getConection().prepareStatement(cadastro_usuario);
			instrucao.setString(1, id);
			instrucao.setString(2, senha);
			instrucao.setString(3, nome);
			instrucao.setInt(4, cargo);
			instrucao.execute();
			acessoBanco.desConectar();
		}
		catch(SQLException e){
            System.out.println("falha na canexao com o banco de dados");
        }
	}
	
	public boolean verificar_usuario_existe(String id){
		Usuario objLogin = null;
		try {
			acessoBanco.conectar();
			PreparedStatement instrucao = acessoBanco.getConection().prepareStatement(verificar_usuario);
			instrucao.setString(1, id);
			ResultSet rs = instrucao.executeQuery();
			if(rs.next()) {
				objLogin = new Usuario(rs.getString("pk_id_usuario"),rs.getString("senha"),rs.getString("nome"),rs.getInt("cargo"));
			}
			acessoBanco.desConectar();
		}
		catch(SQLException e){
            System.out.println("falha na canexao com o banco de dados");
        }
		if(objLogin == null) {
			return false;
		}
		return true;
	}

}
