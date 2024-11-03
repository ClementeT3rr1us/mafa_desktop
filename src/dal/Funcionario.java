
package dal;
import java.sql.*;
import javax.swing.JOptionPane;

public class Funcionario {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Funcionario() {
        conexao = ModuloConexao.conector();
         if (conexao == null) {
            JOptionPane.showMessageDialog(null, "Erro de conexao!");
        }
    }
    
    public void cadastrarFuncionario(String nome, String idade, String cargo) {
        String sql = "insert into funcionarios(nome, idade, cargo) values(?, ?, ?)";
        if (nome.equals("") || idade.equals("") || cargo.equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, nome);
                pst.setString(2, idade);
                pst.setString(3, cargo);
                int confirma = pst.executeUpdate();
                if (confirma == 1) {
                    JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
                } else {
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e);
            }
        }
    }

    public void editarFuncionario(String nome, String idade, String cargo, String id) {
        String sql = "update funcionarios set nome=?, idade=?, cargo=? where id=?";
        if (nome.equals("") || idade.equals("") || cargo.equals("") || id.equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, nome);
                pst.setString(2, idade);
                pst.setString(3, cargo);
                pst.setString(4, id);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Editado com sucesso!");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e);
            }
        }
    }

    public void apagarFuncionario(String id) {
        String sql = "delete from funcionarios where id=?";
        if (id.equals("")) {
            JOptionPane.showMessageDialog(null, "Informar um identificador (ID)!");
        } else {
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, id);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Excluido com sucesso!");

            } catch (Exception e) {
            }
        }
    }
}
