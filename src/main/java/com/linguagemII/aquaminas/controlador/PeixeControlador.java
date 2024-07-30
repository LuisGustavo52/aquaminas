/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linguagemII.aquaminas.controlador;


import com.linguagemII.aquaminas.modelo.dao.PeixeDao;
import com.linguagemII.aquaminas.modelo.entidade.Peixe;
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
 * @author tulio
 */
@WebServlet(WebConstantes.BASE_PATH + "/PeixeControlador")
public class PeixeControlador extends HttpServlet {

    private PeixeDao peixeDao;
    private Peixe peixe;
    String idPeixe = "";
    String especie = "";
    String valor_Unid = "";
    String nome_cientifico = "";
    String opcao = "";

    @Override
    public void init() throws ServletException {
        peixeDao = new PeixeDao();
        peixe = new Peixe();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            idPeixe = request.getParameter("idPeixe");
            nomePeixe = request.getParameter("nomePeixe");
            ufPeixe = request.getParameter("ufPeixe");
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
        peixe.setNomePeixe(nomePeixe);
        peixe.setUfPeixe(ufPeixe);
        peixeDao.salvar(peixe);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idPeixe", idPeixe);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("nomePeixe", nomePeixe);
        request.setAttribute("ufPeixe", ufPeixe);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }
    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idPeixe", idPeixe);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("nomePeixe", nomePeixe);
        request.setAttribute("ufPeixe", ufPeixe);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        peixe.setCodigoPeixe(Integer.valueOf(idPeixe));
        peixe.setNomePeixe(nomePeixe);
        peixe.setUfPeixe(ufPeixe);
        peixeDao.alterar(peixe);
        cancelar(request, response);
    }
    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        peixe.setCodigoPeixe(Integer.valueOf(idPeixe));
        peixe.setNomePeixe(nomePeixe);
        peixe.setUfPeixe(ufPeixe);
        peixeDao.excluir(peixe);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idPeixe", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("nomePeixe", "");
        request.setAttribute("ufPeixe", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Peixe> peixes = peixeDao.buscarTodas();
        request.setAttribute("peixes", peixes);
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroPeixe.jsp");
        dispatcher.forward(request, response);

    }
    
    public void validaCampos(){
        if(nomePeixe==null || nomePeixe.isEmpty()|| ufPeixe==null || ufPeixe.isEmpty()){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }

}
