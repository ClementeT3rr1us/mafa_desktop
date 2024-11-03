
package dal;

import java.sql.*;
import javax.swing.JOptionPane;


public class LoadVendas {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public LoadVendas() {
        conexao = ModuloConexao.conector();
        if (conexao == null) {
            JOptionPane.showMessageDialog(null, "Erro de conexão");
        }
    }
    
    public void cadastrarVenda(){
    }
    
    public void editarVenda(){
    }
    
    public void apagarVebda(){
    }
    
    
    
    
}
