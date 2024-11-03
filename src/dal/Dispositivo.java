package dal;

import java.sql.*;
import javax.swing.JOptionPane;

public class Dispositivo {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Dispositivo() {
        conexao = ModuloConexao.conector();
    }

    public void cadastrarDipositivo() {
        JOptionPane.showMessageDialog(null, "Precisa cadastrar através do celular!");
    }

    public void editarDispositivo(String nome, String estado, String id) {
        String sql = "update aparelhos set proprietario=?, estado=? where id=?";
        if (nome.equals("") || estado.equals("") || id.equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, estado);
            pst.setString(3, id);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Actualizado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
        }
        
    }

    public void apagarDisposivo(String id) {
        String sql = "delete from aparelhos where id=?";
        if (id.equals("")) {
            JOptionPane.showMessageDialog(null, "Informe um identificador(ID)!");
        }else{
            try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluidob com sucesso!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
        }
        
    }
}
