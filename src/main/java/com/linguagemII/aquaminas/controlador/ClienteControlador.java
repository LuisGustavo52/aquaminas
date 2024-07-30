/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linguagemII.aquaminas.controlador;

import com.linguagemII.aquaminas.modelo.dao.ClienteDao;
import com.linguagemII.aquaminas.modelo.entidade.Cliente;
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
@WebServlet(WebConstantes.BASE_PATH + "/ClienteControlador")


public class ClienteControlador extends HttpServlet {

    private ClienteDao clienteDao;
    private Cliente cliente;
    String idCliente = "";
    String cpf = "";
    String telefone = "";
    String nome = "";
    String opcao = "";

    @Override
    public void init() throws ServletException {
        clienteDao = new ClienteDao();
        cliente = new Cliente();
    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            idCliente = request.getParameter("idCliente");
            cliente = request.getParameter("Cliente");
            cpf = request.getParameter("cpf");
            telefone = request.getParameter("telefone");
            nome = request.getParameter("nome");
            
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
        cliente.setIdCliente(Integer.parseInt(idCliente));
        cliente.setCliente(idCliente);
        clienteDao.salvar(Cliente);
        
        
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idCliente", idCliente);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("Cliente", cliente);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }
    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idCliente", idCliente);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("Cliente", cliente);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        Cliente.setIdCliente(Integer.valueOf(idCliente));
        Cliente.setCliente(cliente);
        ClienteDao.alterar(cliente);
        cancelar(request, response);
    }
    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cliente.setIdCliente(Integer.valueOf(idCliente));
        Cliente.setCliente(cliente);
        ClienteDao.excluir(cliente);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("IdCliente", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("Cliente", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> Clientes = ClienteDao.buscarTodas();
        request.setAttribute("Clientes", Clientes);
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroCliente.jsp");
        dispatcher.forward(request, response);

    }
    
    public void validaCampos(){
        if(Cliente==null || Cliente.isEmpty() ){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }

}

}
