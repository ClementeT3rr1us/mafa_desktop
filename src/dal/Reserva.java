
package dal;

import java.sql.*;
import javax.swing.JOptionPane;


public class Reserva {
    
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Reserva() {
        conexao = ModuloConexao.conector();
        if (conexao == null) {
            JOptionPane.showMessageDialog(null, "Erro de conexão:  \n Verifique sua conexão a internet");
        }
    }
    
    public void fazerRareserva(String nome, String email, String tel, String data, String hora, String n, String msg){
        String sql = "insert into reserva (nome, email, tel, ddata, hora, n_pessoas, mensagem) values(?,?,?,?,?,?,?)";
    
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, email);
            pst.setString(3, tel);
            pst.setString(4, data);
            pst.setString(5, hora);
            pst.setString(6, n);
            pst.setString(7, msg);
            JOptionPane.showMessageDialog(null, "RERVADO COM SUCESSO!");
            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void editarRareserva(String nome, String email, String tel, String data, String hora, String n, String msg, String id){
        String sql = "update reserva set nome=?, email=?, tel=?, ddata=?, hora=?, n_pessoas=?, mensagem=? where id=?";
    
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, email);
            pst.setString(3, tel);
            pst.setString(4, data);
            pst.setString(5, hora);
            pst.setString(6, n);
            pst.setString(7, msg);
            pst.setString(8, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "EDITADO COM SUCESSO!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void apagarRareserva(String id){
        String sql = "delete from reserva where id=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "APAGADO COM SUCESSO!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro, verifique se o id existe! \n Dicas do erro: \n "+e.toString());
        }
    }
    
}
