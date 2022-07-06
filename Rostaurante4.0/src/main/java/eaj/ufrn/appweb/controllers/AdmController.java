package eaj.ufrn.appweb.controllers;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import eaj.ufrn.appweb.dao.Item_cardapioDAO;
import eaj.ufrn.appweb.dao.UsuarioDAO;

@Controller
public class AdmController {

    @RequestMapping(value = "/cadastrofuncionario")
    public String doCadastroFuncionario(HttpServletRequest request, HttpServletResponse response) throws IOException{
        return "adm/cadastrar_funcionario";
    }
    
    @RequestMapping(value = "/cadastroitem")
    public String doCadastroItem(HttpServletRequest request, HttpServletResponse response) throws IOException{
        return "adm/cadastrar_item";
    }

    @RequestMapping(value = "/inicioadm")
    public String doInicioAdm(HttpServletRequest request, HttpServletResponse response) throws IOException{
        return "adm/adm";
    }

    @RequestMapping(value = "/adicionar_funcionario", method = RequestMethod.POST)
	public void doCadastrarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException{
		var nome = (String) request.getParameter("n_nome");
		var usuario = (String) request.getParameter("n_usuario");
		var senha = (String) request.getParameter("n_senha");
		var cargo = request.getParameter("n_cargo");
		var cargo_int = Integer.parseInt(cargo);
		UsuarioDAO objUsuarioDAO = new UsuarioDAO();
		boolean v = objUsuarioDAO.verificar_usuario_existe(usuario);
		if(v) {
			System.out.println("ja existe um usuario com essa id");
		}
		else {
			objUsuarioDAO.cadastrar_usuario(usuario,senha,nome,cargo_int);
			System.out.println("novo usuario cadastrado");
		}
	}

    @RequestMapping(value = "/adicionar_produto_cardapio", method = RequestMethod.POST)
	public void doCadastrarItem(HttpServletRequest request, HttpServletResponse response) throws IOException{
		var item = (String) request.getParameter("n_codigo_item");
		var nome = (String) request.getParameter("n_nome");
		var valor = (String) request.getParameter("n_valor");
		var item2 = Integer.parseInt(item);
		var valor2 = Float.parseFloat(valor);
		Item_cardapioDAO obj_item_cardapioDAO = new Item_cardapioDAO();
		obj_item_cardapioDAO.cadastrar_item(item2, nome, valor2);
	}
	
}