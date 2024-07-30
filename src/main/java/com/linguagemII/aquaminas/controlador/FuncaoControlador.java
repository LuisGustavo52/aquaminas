/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linguagemII.aquaminas.controlador;

import com.linguagemII.aquaminas.modelo.dao.FuncaoDAO;
import com.linguagemII.aquaminas.modelo.entidade.Funcao;
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

    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
@WebServlet(WebConstantes.BASE_PATH + "/FuncaoControlador")


public class FuncaoControlador extends HttpServlet {

    private FuncaoDAO FuncaoDao;
    private Funcao Funcao;
    String idFuncao = "";
    String funcao = "";
    String opcao = "";

    @Override
    public void init() throws ServletException {
        FuncaoDao = new FuncaoDAO();
        Funcao = new Funcao();
    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            idFuncao = request.getParameter("idFuncao");
            funcao = request.getParameter("funcao");
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
        Funcao.setIdFuncao (Integer.parseInt(idFuncao));
        Funcao.setFuncao(funcao);
        FuncaoDao.salvar(Funcao);
        
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idFuncao", idFuncao);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("Funcao", Funcao);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }
    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idFuncao", idFuncao);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("Funcao", Funcao);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        Funcao.setIdFuncao(Integer.valueOf(idFuncao));
        Funcao.setFuncao(funcao);
        FuncaoDao.alterar(Funcao);
        cancelar(request, response);
    }
    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Funcao.setIdFuncao(Integer.valueOf(idFuncao));
        Funcao.setFuncao(funcao);
        FuncaoDao.excluir(Funcao);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("IdFuncao", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("Funcao", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Funcao> Funcaos = FuncaoDao.buscarTodas();
        request.setAttribute("Funcaos", Funcaos);
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroFuncao.jsp");
        dispatcher.forward(request, response);

    }
    
    public void validaCampos(){
        if(Funcao==null || funcao.isEmpty() ){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }

}

