/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linguagemII.aquaminas.controlador;

import com.linguagemII.aquaminas.modelo.dao.ClienteDAO;
import com.linguagemII.aquaminas.modelo.dao.FornecedorDAO;
import com.linguagemII.aquaminas.modelo.dao.FuncionarioDAO;
import com.linguagemII.aquaminas.modelo.dao.PeixeDAO;
import com.linguagemII.aquaminas.modelo.dao.RacaoDAO;
import com.linguagemII.aquaminas.modelo.dao.VendaDAO;
import com.linguagemII.aquaminas.modelo.entidade.Cliente;
import com.linguagemII.aquaminas.modelo.entidade.Fornecedor;
import com.linguagemII.aquaminas.modelo.entidade.Funcionario;
import com.linguagemII.aquaminas.modelo.entidade.Peixe;
import com.linguagemII.aquaminas.modelo.entidade.Racao;
import com.linguagemII.aquaminas.modelo.entidade.Venda;
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

@WebServlet(WebConstantes.BASE_PATH + "/VendaControlador")
public class VendaControlador extends HttpServlet {

    private RacaoDAO racaoDao;
    private Racao racao;
    private Peixe peixe;
    private PeixeDAO peixeDAO;
    private Cliente cliente;
    private ClienteDAO clienteDAO;
    private FuncionarioDAO funcionarioDAO;
    private Funcionario funcionario;
    private VendaDAO vendaDAO;
    private Venda venda;
    
    String quantidade = "";
    String clienteVenda = "";
    String racaoVenda = "";
    String funcionarioVenda = "";
    String peixeVenda = "";
    String opcao = "";

    @Override
    public void init() throws ServletException {
        racaoDao = new RacaoDAO();
        racao = new Racao();
        clienteDAO = new ClienteDAO();
        cliente = new Cliente();
        peixeDAO = new PeixeDAO();
        peixe = new Peixe();
        funcionarioDAO = new FuncionarioDAO();
        funcionario = new Funcionario();
        vendaDAO = new VendaDAO();
        venda = new Venda();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            quantidade = request.getParameter("quantidade");
            clienteVenda = request.getParameter("clienteVenda");
            racaoVenda = request.getParameter("racaoVenda");
            
            
            funcionarioVenda = request.getParameter("vendedor");
            peixeVenda = request.getParameter("peixeVenda");
            if (peixeVenda == null){
                peixeVenda = "-1";
            }else{
                peixeVenda = request.getParameter("peixeVenda");
            }
            
            if (opcao == null || opcao.isEmpty()) {
                opcao = "cadastrar";
            }
            switch (opcao) {
                case "cadastrar":  cadastrar(request, response); break;
                case "cancelar":  cancelar(request, response); break;
                default:
                    throw new IllegalArgumentException("Opção inválida"+opcao);
            }
          

        } catch (NumberFormatException e) {
            response.getWriter().println("Erro: um ou mais parâmetros não são numeros válidos"+e.getMessage());
        } catch (IllegalArgumentException e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        venda.getCliente().setIdCliente(Integer.valueOf(clienteVenda));
        venda.getFuncionario().setIdFuncionario(Integer.parseInt(funcionarioVenda));
        System.out.println("\n\n\nPeixeVendaControlador="+peixeVenda+"\n\n\n\n");
        if (peixeVenda.equals("-1")) {
            Racao racaoCadastro = racaoDao.buscarPorId(Integer.valueOf(racaoVenda));
            venda.setVenda_Total(racaoCadastro.getValor() * Integer.valueOf(quantidade));
        }else{
            Peixe peixeCadastro = peixeDAO.buscarPorId(Integer.valueOf(peixeVenda));
            venda.setVenda_Total(peixeCadastro.getValor_Unid()*Integer.valueOf(quantidade));
        }
        vendaDAO.salvar(venda, Integer.valueOf(racaoVenda), Integer.valueOf(peixeVenda), Integer.valueOf(quantidade));
        encaminharParaPagina(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idRacao", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("peixeVenda", "");
        request.setAttribute("racaoVenda", "");
        request.setAttribute("vendedor", "");
        request.setAttribute("cliente", "");
        request.setAttribute("quantidade", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<Funcionario> funcionarios = funcionarioDAO.buscarTodas();
        request.setAttribute("vendedores", funcionarios);
        
        List<Cliente> clientes = clienteDAO.buscarTodas();
        request.setAttribute("clientes", clientes);
        
        List<Racao> racoes = racaoDao.buscarTodas();
        request.setAttribute("racoes", racoes);
        
        List<Peixe> peixes = peixeDAO.buscarTodas();
        request.setAttribute("peixes", peixes);
        
        List<Venda> vendas = null;
        //venda = vendaDao.buscarTodas();
        request.setAttribute("vendas", vendas);
        
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Venda.jsp");
        dispatcher.forward(request, response);

    }
    
    public void validaCampos(){
        if(clienteVenda==null || clienteVenda.isEmpty()|| funcionarioVenda==null || funcionarioVenda.isEmpty() || quantidade==null || quantidade.isEmpty()){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes valida campos");
        }
    }

}

