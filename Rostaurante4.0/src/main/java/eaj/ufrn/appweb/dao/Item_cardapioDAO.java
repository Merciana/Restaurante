package eaj.ufrn.appweb.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import eaj.ufrn.appweb.model.Item_cardapio;

public class Item_cardapioDAO {
	private Conexao acessoBanco;
	private String adicionar_item = "INSERT INTO item_cardapio (pk_id_item_cardapio,nome,preco) VALUES (?,?,?)";
	private String consulta_item = "";
	
	public Item_cardapioDAO() {
		acessoBanco = new Conexao("jdbc:postgresql://localhost:5432/restaurante_bd","postgres","123");
	}
	
	public void cadastrar_item(int pk_id_item_cardapio,String nome,float preco) {
		try{
			acessoBanco.conectar();
			PreparedStatement instrucao = acessoBanco.getConection().prepareStatement(adicionar_item);
			instrucao.setInt(1, pk_id_item_cardapio);
			instrucao.setString(2, nome);
			instrucao.setFloat(3, preco);
			instrucao.execute();
			acessoBanco.desConectar();
		}
		catch(SQLException e){
            System.out.println("falha na canexao com o banco de dados");
        }
	}
	
	public ArrayList<Item_cardapio> consultar_item() {
		ArrayList<Item_cardapio> lista_item = new ArrayList<>();
		try {
			acessoBanco.conectar();
			Statement instrucao = acessoBanco.getConection().createStatement();
			ResultSet rs = instrucao.executeQuery(consulta_item);
			while(rs.next()) {
				Item_cardapio obj_item_cardapio = new Item_cardapio(rs.getInt("pk_id_item_cardapio"),rs.getString("nome"),rs.getFloat("preco"));
			}
			acessoBanco.desConectar();
		}
		catch(SQLException e){
            System.out.println("falha na canexao com o banco de dados");
        }
		return lista_item;
	}
}
