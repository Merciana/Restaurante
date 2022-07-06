package eaj.ufrn.appweb.model;

public class Item_cardapio {
	private int pk_id_item_cardapio;
	private String nome;
	private float preco;
	
	public Item_cardapio(int pk_id_item_cardapio, String nome,float preco) {
		this.pk_id_item_cardapio = pk_id_item_cardapio;
		this.nome = nome;
		this.preco = preco;
	}
	public Item_cardapio() {
	}
}