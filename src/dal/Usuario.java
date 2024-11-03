
package dal;

import java.sql.*;
import javax.swing.JOptionPane;


public class Usuario {
    
    Connection conexao = null;
    PreparedStatement pst  = null;
    ResultSet rs = null;

    public Usuario() {
        conexao = ModuloConexao.conector();
        if (conexao == null) {
            JOptionPane.showMessageDialog(null, "Erro de conexão!");
        } else {
            //JOptionPane.showMessageDialog(null, "Conectado!");
        }
        
        
    }
    
    public int login(String login, String senha){
        String sql = "select *from tbusers where login=? and senha=? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, login);
            pst.setString(2, senha);
            rs = pst.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("iduser");
                UserSession userSession = UserSession.getInstance();
                userSession.setUsername(rs.getString("nome"));
                userSession.setTipo(rs.getString("tipo"));
                userSession.setId(userId);
                return 1;
            } else {
                return 2;
            }
        } catch (Exception e) {
            return 3;
        }
    }
    
    public void criarConta(String nome, String login, String senha){
        String sql = "insert into tbusers (nome, login, senha) values(?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, login);
            pst.setString(3, senha);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: "+e);
        }
    }
    
    public void criarUsuario(String nome, String login, String senha, String tipo){
        String sql = "insert into tbusers (nome, login, senha. tipo) values(?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, login);
            pst.setString(3, senha);
            pst.setString(4, tipo);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: "+e);
        }
    } 
    
    public void actualizarUsuario(String nome, String login, String senha, String tipo, String id){
        String sql = "update tbusers set nome=?, login=?, senha=?, tipo=? where id=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, login);
            pst.setString(3, senha);
            pst.setString(4, tipo);
            pst.setString(5, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Actualizado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: "+e);
        }
    }  
    
    public void apagarUsuario(String id){
        String sql = "delete from tbusers set where id=?";
        
           try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Apagado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: "+e);
        }
    
    }  
            
            
            
}
