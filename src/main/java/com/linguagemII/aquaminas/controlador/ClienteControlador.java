/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linguagemII.aquaminas.controlador;

import com.linguagemII.aquaminas.modelo.dao.ClienteDAO;
import com.linguagemII.aquaminas.modelo.entidade.Cliente;
import com.linguagemII.aquaminas.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
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

    private ClienteDAO clienteDao;
    private Cliente cliente;
    String idCliente = "";
    String cpf = "";
    String telefone = "";
    String nome = "";
    String opcao = "";

    @Override
    public void init() throws ServletException {
        clienteDao = new ClienteDAO();
        cliente = new Cliente();
    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            idCliente = request.getParameter("idCliente");
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
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);
        clienteDao.salvar(cliente);
        
        
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idCliente", idCliente);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("nome", nome);
        request.setAttribute("cpf", cpf);
        request.setAttribute("telefone", telefone);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }
    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idCliente", idCliente);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("nome", nome);
        request.setAttribute("cpf", cpf);
        request.setAttribute("telefone", telefone);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        cliente.setIdCliente(Integer.valueOf(idCliente));
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);
        clienteDao.alterar(cliente);
        cancelar(request, response);
        
    }
    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cliente.setIdCliente(Integer.valueOf(idCliente));
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);
        clienteDao.excluir(cliente);
        cancelar(request, response);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("IdCliente", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("nome", "");
        request.setAttribute("cpf", "");
        request.setAttribute("telefone", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> Clientes = clienteDao.buscarTodas();
        request.setAttribute("clientes", Clientes);
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroCliente.jsp");
        dispatcher.forward(request, response);

    }
    
    public void validaCampos(){
        if(nome==null || nome.isEmpty()|| cpf==null || cpf.isEmpty() || telefone==null || telefone.isEmpty()){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
}
