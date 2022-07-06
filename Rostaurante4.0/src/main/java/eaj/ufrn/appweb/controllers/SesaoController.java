package eaj.ufrn.appweb.controllers;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import eaj.ufrn.appweb.model.Usuario;
import eaj.ufrn.appweb.dao.UsuarioDAO;

@Controller
public class SesaoController {

    @RequestMapping(value = "/fimsesao", method = RequestMethod.GET)
    public void doFimsesao(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.getWriter().println("logout");
        HttpSession session = request.getSession(false);
        if(session == null){
            response.getWriter().println("a sessao ja tinha sido encerrada");
        }
        else{
            session.invalidate();
            response.getWriter().println("vc finalizou a sessao");
        }
    }
   
    @RequestMapping(value = "/fazer_login", method = RequestMethod.POST)
    public String doIniciarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	var n_usuario = (String) request.getParameter("id_usuario");
		var n_senha = (String) request.getParameter("senha");
		HttpSession session = request.getSession(false);
        if(session == null){
        	//não tinha sesão iniciada
        	UsuarioDAO objUsuarioDAO = new UsuarioDAO();
    		Usuario login = objUsuarioDAO.consultar_usuario(n_usuario,n_senha);
    		if(login == null) {
    			response.sendRedirect("/alertas/falhalogin.html");
    		}
    		else {
    			System.out.println(login.get_nome());//verificar nome no console
    			HttpSession nova_session = request.getSession();
    			nova_session = request.getSession();
    			nova_session.setAttribute("usuarioSe", login.get_id_usuario());
    			nova_session.setAttribute("senhaSe", login.get_senha());
    			nova_session.setAttribute("nomeSe", login.get_nome());
    			nova_session.setAttribute("cargoSe", login.get_cargo());
    			int tipoRe = (int) nova_session.getAttribute("cargoSe");
    			switch(tipoRe) {
            	case 1:
					return "adm/adm";
            	case 2:
					return "caixa/caixa";
            	case 3:
					return "garcon/garcon";
            	case 4:
					return "cozinha/cozinha";
            	}
    		}
        }
        else{
        	//ja tinha sesão iniciada , redirecionar para a pagina de acordo com o tipo de usuario
        	int tipoRe = (int) session.getAttribute("cargoSe");
        	switch(tipoRe) {
        	case 1:
				return "adm/adm_ativo.html";
        	case 2:
        		return "caixa/caixa_ativo.html";
        	case 3:
        		return "garcon/garcon_ativo.html";
        	case 4:
        		return "cozinha/cozinha_ativo.html";
        	}
        }
		return "0"	;
    }
}