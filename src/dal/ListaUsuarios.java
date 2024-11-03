package dal;

import java.sql.*;
import dal.*;
import javax.swing.JOptionPane;

public class ListaUsuarios {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public ListaUsuarios() {
        conexao = ModuloConexao.conector();
        if (conexao == null) {
            JOptionPane.showMessageDialog(null, "Erro de conexao!");
        }
    }

    public void cadastrarUsuario(String nome, String login, String senha, String tipo) {
        String sql = "insert into tbusers(nome, login, senha, tipo) values(?, ?, ?, ?)";
        if (nome.equals("") || login.equals("") || senha.equals("") || tipo.equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, nome);
                pst.setString(2, login);
                pst.setString(3, senha);
                pst.setString(4, tipo);
                pst.executeUpdate();

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

    public void editarUsuario(String nome, String login, String senha, String tipo, String id) {
        String sql = "update tbusers set nome=?, login=?, senha=?, tipo=? where iduser=?";
        if (nome.equals("") || login.equals("") || senha.equals("") || tipo.equals("") || id.equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, nome);
                pst.setString(2, login);
                pst.setString(3, senha);
                pst.setString(4, tipo);
                pst.setString(5, id);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Editado com sucesso!");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e);
            }
        }
    }

    public void apagarUsuario(String id) {
        String sql = "delete from tbusers where iduser=?";
        if (id.equals("")) {
            JOptionPane.showMessageDialog(null, "Informr um identificador (ID)!");
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
