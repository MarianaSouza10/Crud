

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author RA21504533
 */
public class cadastrarProdutos extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /*Capiturando os valores digitados no formulario do html*/
        String produto = request.getParameter("produtos");
        String valor = request.getParameter("valor");
        int valorProduto = Integer.parseInt(valor);
        
        /*Preenchendo o objeto da class produto para poder salvar um produto no banco*/
        Produto produtoSalva = new Produto();
        produtoSalva.setNome(produto);
        produtoSalva.setValor(valorProduto);
        
        
        /*Criar sessão com o hibernate e mandar salvar o objeto produto*/
        Session sessao =  HibernateUtil.getSessionFactory().openSession();  
        
        
        Transaction tx = sessao.beginTransaction();
        try{
            sessao.save(valorProduto);
            sessao.flush();
            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }
        sessao.close();
        

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
