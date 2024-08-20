/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linguagemII.aquaminas.controlador;

import com.linguagemII.aquaminas.modelo.dao.EnderecoDAO;
import com.linguagemII.aquaminas.modelo.entidade.Endereco;
import com.linguagemII.aquaminas.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author 17933118623
 */

@WebServlet(WebConstantes.BASE_PATH + "/EnderecoControlador")
public class EnderecoControlador extends HttpServlet {

    private EnderecoDAO enderecoDao;
    private Endereco endereco;
    String idEndereco = "";
    String numero = "";
    String cidade = "";
    String complemento = "";
    String cliente = "";
    String opcao = "";

    @Override
    public void init() throws ServletException {
        enderecoDao = new EnderecoDAO();
        endereco = new Endereco();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            idEndereco = request.getParameter("idEndereco");
            numero = request.getParameter("numero");
            complemento = request.getParameter("complemento");
            cidade = request.getParameter("cidade");
            cliente = request.getParameter("cliente");
            if (opcao == null || opcao.isEmpty()) {
                opcao = "cadastrar";
            }
            switch (opcao) {
                case "cadastrar":  cadastrar(request, response); break;
                case "editar":  editar(request, response); break;
                case "confirmarEditar":  confirmarEditar(request, response); break;
                case "excluir":  excluir(request, response); break;
                case "confirmarExcluir":  confirmarExcluir(request, response); break;
                case "cancelar":  cancelar(request, response); break;
                default:
                    throw new IllegalArgumentException("Opção inválida"+opcao);
            }
          

        } catch (NumberFormatException e) {
            response.getWriter().println("Erro: um ou mais parâmetros não são numeros válidos");
        } catch (IllegalArgumentException e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        endereco.setCidade(numero);
        enderecoDao.salvar(endereco);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idEndereco", idEndereco);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("numero", numero);
        request.setAttribute("complemento", complemento);
        request.setAttribute("cidade", cidade);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }
    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idEndereco", idEndereco);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("numero", numero);
        request.setAttribute("complemento", complemento);
        request.setAttribute("cidade", cidade);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        endereco.setIdEndereco(Integer.parseInt(idEndereco));
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        endereco.setCidade(numero);
        enderecoDao.alterar(endereco);
        cancelar(request, response);
    }
    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        endereco.setIdEndereco(Integer.parseInt(idEndereco));
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        endereco.setCidade(cidade);
        enderecoDao.excluir(endereco);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idEndereco", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("numero", "");
        request.setAttribute("complemento", "");
        request.setAttribute("numero", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Endereco> enderecoes = enderecoDao.buscarTodas();
        request.setAttribute("enderecoes", enderecoes);
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroEndereco.jsp");
        dispatcher.forward(request, response);

    }
    
    public void validaCampos(){
        if(numero==null || numero.isEmpty()|| complemento==null || complemento.isEmpty() || numero==null || numero.isEmpty()){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }

}

