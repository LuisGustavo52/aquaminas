/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linguagemII.aquaminas.controlador;

import com.linguagemII.aquaminas.modelo.dao.FornecedorDAO;
import com.linguagemII.aquaminas.modelo.dao.RacaoDAO;
import com.linguagemII.aquaminas.modelo.entidade.Fornecedor;
import com.linguagemII.aquaminas.modelo.entidade.Racao;
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

@WebServlet(WebConstantes.BASE_PATH + "/RacaoControlador")
public class RacaoControlador extends HttpServlet {

    private RacaoDAO racaoDao;
    private Racao racao;
    private FornecedorDAO fornecedorDao;
    private Fornecedor fornecedor;
    
    String idRacao = "";
    String nome = "";
    String peso = "";
    String valor = "";
    String fornecedorRacao = "";
    String opcao = "";

    @Override
    public void init() throws ServletException {
        racaoDao = new RacaoDAO();
        racao = new Racao();
        fornecedorDao = new FornecedorDAO();
        fornecedor = new Fornecedor();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            idRacao = request.getParameter("idRacao");
            nome = request.getParameter("nome");
            valor = request.getParameter("valor");
            peso = request.getParameter("peso");
            fornecedorRacao = request.getParameter("fornecedor");
            
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
        racao.setNome(nome);
        racao.setValor(Double.parseDouble(valor));
        racao.setPeso(Double.parseDouble(peso));
        racao.getFornecedor().setIdFornecedor(Integer.parseInt(fornecedorRacao));
        racaoDao.salvar(racao);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idRacao", idRacao);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("nome", nome);
        request.setAttribute("valor", valor);
        request.setAttribute("peso", peso);
        request.setAttribute("fornecedor", fornecedor);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }
    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idRacao", idRacao);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("nome", nome);
        request.setAttribute("valor", valor);
        request.setAttribute("peso", peso);
        request.setAttribute("fornecedor", fornecedor);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        racao.setIdRacao(Integer.parseInt(idRacao));
        racao.setNome(nome);
        racao.setValor(Double.parseDouble(valor));
        racao.setPeso(Double.parseDouble(peso));
        racao.getFornecedor().setIdFornecedor(Integer.parseInt(fornecedorRacao));
        racaoDao.alterar(racao);
        cancelar(request, response);
    }
    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        racao.setIdRacao(Integer.parseInt(idRacao));
        racao.setNome(nome);
        racao.setValor(Double.parseDouble(valor));
        racao.setPeso(Double.parseDouble(peso));
        racao.getFornecedor().setIdFornecedor(Integer.parseInt(fornecedorRacao));
        racaoDao.excluir(racao);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idRacao", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("nome", "");
        request.setAttribute("valor", "");
        request.setAttribute("peso", "");
        request.setAttribute("fornecedor", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("\n\n\n\n\n robertices12345\n\n\n\n\n");
        List<Fornecedor> fornecedores = fornecedorDao.buscarTodas();
        if(fornecedores.isEmpty()){
            System.out.println("gay gay gay gay gay\n gay gay gay gay gay\n \n gay gay gay gay gay");
        }
        request.setAttribute("fornecedores", fornecedores);
        
        List<Racao> racoes = racaoDao.buscarTodas();
        request.setAttribute("racoes", racoes);
        
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroRacao.jsp");
        dispatcher.forward(request, response);

    }
    
    public void validaCampos(){
        if(nome==null || nome.isEmpty()|| valor==null || valor.isEmpty() || peso==null || peso.isEmpty()){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }

}

