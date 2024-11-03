
package dal;

import java.sql.*;
import javax.swing.JOptionPane;



public class Vendas {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Vendas() {
        
        conexao = ModuloConexao.conector();
        if (conexao == null) {
            JOptionPane.showMessageDialog(null, "Erro de conexão!");
        }
    }
    
    public void fazerVenda(String cliente, String produtos, String data, String valor){
        
        String sql="insert into vendas( cliente, produtos, _data, valor_total) values (?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cliente);
            pst.setString(2, produtos);
            pst.setString(3, data);
            pst.setString(4, valor);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao inaseir dados: "+e);
        }
        
     }
    public void editarVenda(String id, String cliente, String produtos, String data, String valor){
        
        String sql="update vendas set cliente=?, produtos=?, _data=?, valor_total=? where id=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cliente);
            pst.setString(2, produtos);
            pst.setString(3, data);
            pst.setString(4, valor);
            pst.setString(5, id);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Editado com sucesso!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar dados: /n"+e);
        }
    }
    public void apagarVenda(String id){
        
        String sql="delete from vendas where id=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Apagado com sucesso!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar dados: "+e);
        }

    }
    
    public void esfaziarVendas(){
              String sql="delete from vendas";
        try {
            pst = conexao.prepareStatement(sql);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Dados apagado com sucesso!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar dados: "+e);
        }
    }
    
    
}
