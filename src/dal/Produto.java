
package dal;

import java.sql.*;
import dal.*;
import javax.swing.JOptionPane;


public class Produto {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public Produto() {
        conexao = ModuloConexao.conector();
        if (conexao == null) {
            JOptionPane.showMessageDialog(null, "Erro de conexao!");
        }
    }
    
    
   public void apagarProduto(String id){
       
       String sql = "delete from produtos where id=?";
       
       try {
           pst = conexao.prepareStatement(sql);
           pst.setString(1, id);
           int confirma = pst.executeUpdate();
           if (confirma == 1) {
                    JOptionPane.showMessageDialog(null, "Apagado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro: ");
                }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Erro: " + e);

       }
   }
    
}
