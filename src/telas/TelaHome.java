package telas;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import dal.*;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import javax.swing.Icon;
// importando a biblioteca para a pesquisa
import net.proteanit.sql.DbUtils;


/**
 *
 * @author Clemente
 */
public class TelaHome extends javax.swing.JFrame {

    private FileInputStream fis;
    private int tamanho;

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaHome
     */
    public TelaHome() {
        initComponents();

        conexao = ModuloConexao.conector();
        if (conexao != null) {
            //
        } else {
            JOptionPane.showMessageDialog(null, "Erro: ");
        }

        setIcon();

        startStyle();

        verDispositivo();
        verUsuario();
        verProduto();
        verFuncionario();
        verReserva();
        verVenda();

        contarUsuarios();
        contarDispositivos();
        contarFuncionarios();

        printUserInfo();

        mostrarData();

    }

    public void printUserInfo() {
        UserSession userSession = UserSession.getInstance();
        lblUserName.setText(userSession.getUsername());
        lblUserTipo.setText(userSession.getTipo());
    }

    public void contarUsuarios() {

        String sql = "select count(*) from tbusers";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                int n = rs.getInt(1);
                System.out.println(n);
                txtNumerodeUsuarios.setText("" + n);
            } else {
                txtNumerodeUsuarios.setText("0");
            }

        } catch (Exception e) {

        }
    }

    public void contarDispositivos() {

        String sql = "select count(*) from aparelhos;";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                int n = rs.getInt(1);
                System.out.println(n);
                txtNumerodeDispositivos.setText("" + n);
            } else {
                txtNumerodeDispositivos.setText("0");
            }

        } catch (Exception e) {

        }

    }

    public void contarFuncionarios() {

        String sql = "select count(*) from funcionarios;";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                int n = rs.getInt(1);
                System.out.println(n);
                txtNumerodeFuncionarios.setText("" + n);
            } else {
                txtNumerodeFuncionarios.setText("0");
            }

        } catch (Exception e) {

        }

    }

    public void mostrarData() {
        Date data = new Date();
        SimpleDateFormat dataf = new SimpleDateFormat("dd/MM/yyyy");
        String dataActual = dataf.format(data);
        vTxtData.setText(dataActual);
    }

    public void startStyle() {
        TelaDefFid.setVisible(false);
        TelaDefinicoes.setVisible(false);
        TelaDispositivos.setVisible(false);
        TelaFuncionarios.setVisible(false);
        TelaMais.setVisible(false);
        TelaProduto.setVisible(false);
        TelaRecFid.setVisible(false);
        TelaRelatorio.setVisible(false);
        TelaUsuario.setVisible(false);
        TelaDashboard.setVisible(true);

        tbDispositivo.setBackground(new Color(255, 255, 255));
        tbDispositivo.getTableHeader().setForeground(new Color(0, 0, 0));
        tbDispositivo.getTableHeader().setOpaque(false);
        tbDispositivo.getTableHeader().setBackground(new Color(255, 255, 255));
        tbDispositivo.getTableHeader().setFont(new Font("", Font.BOLD, 12));
        tbDispositivo.setRowHeight(30);

        tbProdutos.setBackground(new Color(255, 255, 255));
        tbProdutos.getTableHeader().setForeground(new Color(0, 0, 0));
        tbProdutos.getTableHeader().setOpaque(false);
        tbProdutos.getTableHeader().setBackground(new Color(255, 255, 255));
        tbProdutos.getTableHeader().setFont(new Font("", Font.BOLD, 12));
        tbProdutos.setRowHeight(30);

        tbFuncionarios.setBackground(new Color(255, 255, 255));
        tbFuncionarios.getTableHeader().setForeground(new Color(0, 0, 0));
        tbFuncionarios.getTableHeader().setOpaque(false);
        tbFuncionarios.getTableHeader().setBackground(new Color(255, 255, 255));
        tbFuncionarios.getTableHeader().setFont(new Font("", Font.BOLD, 12));
        tbFuncionarios.setRowHeight(30);

        tbUsuario.setBackground(new Color(255, 255, 255));
        tbUsuario.getTableHeader().setForeground(new Color(0, 0, 0));
        tbUsuario.getTableHeader().setOpaque(false);
        tbUsuario.getTableHeader().setBackground(new Color(255, 255, 255));
        tbUsuario.getTableHeader().setFont(new Font("", Font.BOLD, 12));
        tbUsuario.setRowHeight(30);

        tbVendas.setBackground(new Color(255, 255, 255));
        tbVendas.getTableHeader().setForeground(new Color(0, 0, 0));
        tbVendas.getTableHeader().setOpaque(false);
        tbVendas.getTableHeader().setBackground(new Color(255, 255, 255));
        tbVendas.getTableHeader().setFont(new Font("", Font.BOLD, 12));
        tbVendas.setRowHeight(30);

        tbReservas.setBackground(new Color(255, 255, 255));
        tbReservas.getTableHeader().setForeground(new Color(0, 0, 0));
        tbReservas.getTableHeader().setOpaque(false);
        tbReservas.getTableHeader().setBackground(new Color(255, 255, 255));
        tbReservas.getTableHeader().setFont(new Font("", Font.BOLD, 12));
        tbReservas.setRowHeight(30);

        tbVendas.setBackground(new Color(255, 255, 255));
        tbVendas.getTableHeader().setForeground(new Color(0, 0, 0));
        tbVendas.getTableHeader().setOpaque(false);
        tbVendas.getTableHeader().setBackground(new Color(255, 255, 255));
        tbVendas.getTableHeader().setFont(new Font("", Font.BOLD, 12));
        tbVendas.setRowHeight(30);
    }

    public void verDispositivo() {
        String sql = "select *from aparelhos";

        try {

            pst = conexao.prepareStatement(sql);

            // a linha abaixo executa a query
            rs = pst.executeQuery();
            // se existir usario e senha correspondentes

            while (rs.next()) {

                Object[] dados = {rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4)};
                DefaultTableModel modelo = (DefaultTableModel) tbDispositivo.getModel();
                modelo.addRow(dados);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void verProduto() {
        String sql = "select *from produtos";

        try {

            pst = conexao.prepareStatement(sql);

            // a linha abaixo executa a query
            rs = pst.executeQuery();
            // se existir usario e senha correspondentes

            while (rs.next()) {

                Object[] dados = {rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4), rs.getObject(5), rs.getBlob(6)};
                //Blob blob = (Blob) rs.getBlob(6);
                DefaultTableModel modelo = (DefaultTableModel) tbProdutos.getModel();
                modelo.addRow(dados);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void verUsuario() {
        String sql = "select *from tbusers";

        try {

            pst = conexao.prepareStatement(sql);

            // a linha abaixo executa a query
            rs = pst.executeQuery();
            // se existir usario e senha correspondentes

            while (rs.next()) {

                Object[] dados = {rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4), rs.getObject(5)};
                DefaultTableModel modelo = (DefaultTableModel) tbUsuario.getModel();
                modelo.addRow(dados);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void verFuncionario() {
        String sql = "select *from funcionarios";

        try {

            pst = conexao.prepareStatement(sql);

            // a linha abaixo executa a query
            rs = pst.executeQuery();
            // se existir usario e senha correspondentes

            while (rs.next()) {

                Object[] dados = {rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4)};
                DefaultTableModel modelo = (DefaultTableModel) tbFuncionarios.getModel();
                modelo.addRow(dados);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void verReserva() {
        String sql = "select *from reserva";

        try {

            pst = conexao.prepareStatement(sql);

            // a linha abaixo executa a query
            rs = pst.executeQuery();
            // se existir usario e senha correspondentes

            while (rs.next()) {

                Object[] dados = {rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4), rs.getObject(5), rs.getObject(6), rs.getObject(7), rs.getObject(8)};
                DefaultTableModel modelo = (DefaultTableModel) tbReservas.getModel();
                modelo.addRow(dados);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void verVenda() {
        String sql = "select *from vendas";

        try {

            pst = conexao.prepareStatement(sql);

            // a linha abaixo executa a query
            rs = pst.executeQuery();
            // se existir usario e senha correspondentes

            while (rs.next()) {

                Object[] dados = {rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4), rs.getObject(5)};
                DefaultTableModel modelo = (DefaultTableModel) tbVendas.getModel();
                modelo.addRow(dados);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void cadastrarProduto() {
        String sql = "insert into produtos(nome, descricao, quantidade, valor, foto) values(?, ?, ?, ?, ?)";
        if (pTxtNome.getText().equals("") || pTxtDesc.getText().equals("") || pTxtQuant.getText().equals("") || pTextValor.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, pTxtNome.getText());
                pst.setString(2, pTxtDesc.getText());
                pst.setString(3, pTxtQuant.getText());
                pst.setString(4, pTextValor.getText());
                pst.setBlob(5, fis, tamanho);
                int confirma = pst.executeUpdate();
                if (confirma == 1) {
                    JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro: ");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e);
            }
        }

    }

    public void actualizarProduto() {
        String sql = "update produtos set nome=?, descricao=?, quantidade=?, valor=?, foto=? where id=?";
        if (pTxtNome.getText().equals("") || pTxtDesc.getText().equals("") || pTxtQuant.getText().equals("") || pTextValor.getText().equals("") || pTxtID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, pTxtNome.getText());
                pst.setString(2, pTxtDesc.getText());
                pst.setString(3, pTxtQuant.getText());
                pst.setString(4, pTextValor.getText());
                pst.setBlob(5, fis, tamanho);
                pst.setString(6, pTxtID.getText());
                int confirma = pst.executeUpdate();
                if (confirma == 1) {
                    JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro: ");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e);
            }
        }

    }

    public void setarDipositivo() {
        int setar = tbDispositivo.getSelectedRow();
        desTxtProprietrario.setText(tbDispositivo.getModel().getValueAt(setar, 1).toString());
        desTxtIDProcessador.setText(tbDispositivo.getModel().getValueAt(setar, 2).toString());
        desTxtID.setText(tbDispositivo.getModel().getValueAt(setar, 0).toString());
    }

    public void setarUsuario() {
        int setar = tbUsuario.getSelectedRow();
        uTxtID.setText(tbUsuario.getModel().getValueAt(setar, 0).toString());
        uTxtNome.setText(tbUsuario.getModel().getValueAt(setar, 1).toString());
        uTxtUser.setText(tbUsuario.getModel().getValueAt(setar, 2).toString());
        uTxtSenha.setText(tbUsuario.getModel().getValueAt(setar, 3).toString());
    }

    public void setarFuncionario() {
        int setar = tbFuncionarios.getSelectedRow();
        fTxtID.setText(tbFuncionarios.getModel().getValueAt(setar, 0).toString());
        fTxtNome.setText(tbFuncionarios.getModel().getValueAt(setar, 1).toString());
        fTxtIdade.setText(tbFuncionarios.getModel().getValueAt(setar, 2).toString());
        fTxtCargo.setText(tbFuncionarios.getModel().getValueAt(setar, 3).toString());

    }

    public void setarReserva() {
        int setar = tbReservas.getSelectedRow();
        rTxtID.setText(tbReservas.getModel().getValueAt(setar, 0).toString());
        rTxtNome.setText(tbReservas.getModel().getValueAt(setar, 1).toString());
        rTxtEmail.setText(tbReservas.getModel().getValueAt(setar, 2).toString());
        rTxtTel.setText(tbReservas.getModel().getValueAt(setar, 3).toString());
        rTxtData.setText(tbReservas.getModel().getValueAt(setar, 4).toString());
        rTxtHora.setText(tbReservas.getModel().getValueAt(setar, 5).toString());
        rTxtnPessoas.setText(tbReservas.getModel().getValueAt(setar, 6).toString());
        rTxtMsg.setText(tbReservas.getModel().getValueAt(setar, 7).toString());
    }

    public void setarVenda() {
        int setar = tbVendas.getSelectedRow();
        vTxtID.setText(tbVendas.getModel().getValueAt(setar, 0).toString());
        vTxtCliente.setText(tbVendas.getModel().getValueAt(setar, 1).toString());
        vTxtProdutos.setText(tbVendas.getModel().getValueAt(setar, 2).toString());
        vTxtData.setText(tbVendas.getModel().getValueAt(setar, 3).toString());
        vTxtValorTotal.setText(tbVendas.getModel().getValueAt(setar, 4).toString());

    }

    public void setarProduto() {
        int setar = tbProdutos.getSelectedRow();
        pTxtID.setText(tbProdutos.getModel().getValueAt(setar, 0).toString());
        pTxtNome.setText(tbProdutos.getModel().getValueAt(setar, 1).toString());
        pTxtDesc.setText(tbProdutos.getModel().getValueAt(setar, 2).toString());
        pTxtQuant.setText(tbProdutos.getModel().getValueAt(setar, 3).toString());
        pTextValor.setText(tbProdutos.getModel().getValueAt(setar, 4).toString());

        String sql = "select foto from produtos where id= ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tbProdutos.getModel().getValueAt(setar, 0).toString());
            rs = pst.executeQuery();
            if (rs.next()) {
                Blob blob = (Blob) rs.getBlob("foto");
                byte[] img = blob.getBytes(1, (int) blob.length());
                BufferedImage imagem = null;
                try {
                    imagem = ImageIO.read(new ByteArrayInputStream(img));
                    ImageIcon icone = new ImageIcon(imagem);
                    Icon foto = new ImageIcon(icone.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH));
                    lblFoto.setIcon(foto);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro: " + e);
                }
            } else {
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }

    private void pesquisarProduto() {
        String sql = "select *from produtos where nome like? ";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, "%" + txtPesquisarProduto.getText() + "%");
            rs = pst.executeQuery();
            tbProdutos.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }

    private void pesquisarUsuario() {
        String sql = "select *from tbusers where nome like? ";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, "%" + txtPesquisarUsuario.getText() + "%");
            rs = pst.executeQuery();
            tbUsuario.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }

    private void pesquisarFuncionario() {
        String sql = "select *from funcionarios where nome like? ";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, "%" + txtPesquisarFuncionario.getText() + "%");
            rs = pst.executeQuery();
            tbFuncionarios.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }

    private void pesquisarDipositivo() {
        String sql = "select *from aparelhos where proprietario like? ";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, "%" + txtPesquisarDispositivo.getText() + "%");
            rs = pst.executeQuery();
            tbDispositivo.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }

    private void pesquisarReserva() {
        String sql = "select *from reserva where nome like? ";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, "%" + txtPesquisarReserva.getText() + "%");
            rs = pst.executeQuery();
            tbReservas.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }

    private void pesquisarVenda() {
        String sql = "select *from vendas where cliente like? ";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, "%" + txtPesquisarVenda.getText() + "%");
            rs = pst.executeQuery();
            tbVendas.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnProduto = new javax.swing.JLabel();
        btnRelatorio = new javax.swing.JLabel();
        btnUsuario = new javax.swing.JLabel();
        btnMais = new javax.swing.JLabel();
        btnfuncionario = new javax.swing.JLabel();
        btnDispositivo = new javax.swing.JLabel();
        btndefinicoes = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnHome = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnDefFidelidade = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnRecFidelidade = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnOfertaespecial = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        DIV = new javax.swing.JPanel();
        TelaProduto = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        txtPesquisarProduto = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        pBtnInserir = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        pBtnApagar = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        pBtnActualizar = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        pBtnLimpar = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        pTxtNome = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        pTxtDesc = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        pTxtQuant = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        pTextValor = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        pTxtID = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        lblFoto = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbProdutos = new javax.swing.JTable();
        TelaRelatorio = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbVendas = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        txtPesquisarVenda = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        vBtnInserir = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        vBtnApagar = new javax.swing.JPanel();
        jLabel94 = new javax.swing.JLabel();
        vBtnActualizar = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        vBtnLimpar = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jLabel97 = new javax.swing.JLabel();
        vTxtCliente = new javax.swing.JTextField();
        jLabel98 = new javax.swing.JLabel();
        vTxtProdutos = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        vTxtID = new javax.swing.JTextField();
        vTxtValorTotal = new javax.swing.JTextField();
        vTxtData = new javax.swing.JFormattedTextField();
        jPanel32 = new javax.swing.JPanel();
        btnImprimir = new javax.swing.JButton();
        btnEsvaziarVendas = new javax.swing.JButton();
        TelaUsuario = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        txtPesquisarUsuario = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        uBtnInserir = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        uBtnApagar = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        uBtnActualizar = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        uBtnLimpar = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        uTxtNome = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        uTxtUser = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        uTxtSenha = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        uTxtTipo = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        uTxtID = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUsuario = new javax.swing.JTable();
        TelaMais = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        txtPesquisarReserva = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        rBtnInserir = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        rBtnApagar = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        rBtnActualizar = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        rBtnLimpar = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        rTxtNome = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        rTxtEmail = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        rTxtID = new javax.swing.JTextField();
        rTxtHora = new javax.swing.JFormattedTextField();
        rTxtTel = new javax.swing.JFormattedTextField();
        rTxtData = new javax.swing.JFormattedTextField();
        jPanel28 = new javax.swing.JPanel();
        jLabel89 = new javax.swing.JLabel();
        rTxtnPessoas = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        rTxtMsg = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbReservas = new javax.swing.JTable();
        TelaFuncionarios = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        txtPesquisarFuncionario = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        fBtnInserir = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        fBtnApagar = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        fBtnActualizar = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        fBtnLimpar = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        fTxtNome = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        fTxtIdade = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        fTxtCargo = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        fTxtID = new javax.swing.JTextField();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbFuncionarios = new javax.swing.JTable();
        TelaDispositivos = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        txtPesquisarDispositivo = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        desBtnInserir = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        desBtnApagar = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        desBtnActualizar = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        desBtnLimpar = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        desTxtProprietrario = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        desTxtIDProcessador = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        desTxtEstado = new javax.swing.JComboBox<>();
        jLabel54 = new javax.swing.JLabel();
        desTxtID = new javax.swing.JTextField();
        jPanel30 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbDispositivo = new javax.swing.JTable();
        TelaDefinicoes = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lblUserName = new javax.swing.JLabel();
        lblUserTipo = new javax.swing.JLabel();
        btnSobre = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        btnComunicarProblema = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        btnMaisApps = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        btnVerDispositivos = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        btnTerminarSessao = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        TelaDefFid = new javax.swing.JPanel();
        TelaRecFid = new javax.swing.JPanel();
        TelaDashboard = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtNumerodeFuncionarios = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtNumerodeDispositivos = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtNumerodeUsuarios = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnLocal = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnPrever = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btnAjuda = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(41, 55, 64));

        jPanel2.setBackground(new java.awt.Color(29, 40, 47));

        btnProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ic_store_mall_directory_white_24dp.png"))); // NOI18N
        btnProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProdutoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnProdutoMouseEntered(evt);
            }
        });

        btnRelatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ic_assignment_white_24dp.png"))); // NOI18N
        btnRelatorio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRelatorioMouseClicked(evt);
            }
        });

        btnUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ic_person_white_24dp.png"))); // NOI18N
        btnUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUsuarioMouseClicked(evt);
            }
        });

        btnMais.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ic_local_offer_white_24dp.png"))); // NOI18N
        btnMais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMaisMouseClicked(evt);
            }
        });

        btnfuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ic_people_white_24dp.png"))); // NOI18N
        btnfuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnfuncionarioMouseClicked(evt);
            }
        });

        btnDispositivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ic_phone_android_white_24dp.png"))); // NOI18N
        btnDispositivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDispositivoMouseClicked(evt);
            }
        });

        btndefinicoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ic_settings_white_24dp.png"))); // NOI18N
        btndefinicoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btndefinicoesMouseClicked(evt);
            }
        });

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icon-left.png"))); // NOI18N

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ic_home_white_24dp.png"))); // NOI18N
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHomeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(btndefinicoes)
                    .addComponent(btnfuncionario)
                    .addComponent(btnRelatorio)
                    .addComponent(btnProduto)
                    .addComponent(btnHome)
                    .addComponent(btnDispositivo)
                    .addComponent(btnMais)
                    .addComponent(btnUsuario))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(btnHome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRelatorio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMais)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnfuncionario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDispositivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btndefinicoes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(41, 55, 64));

        btnDefFidelidade.setBackground(new java.awt.Color(41, 55, 64));
        btnDefFidelidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDefFidelidadeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDefFidelidadeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDefFidelidadeMouseExited(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Def. fidelidade");

        javax.swing.GroupLayout btnDefFidelidadeLayout = new javax.swing.GroupLayout(btnDefFidelidade);
        btnDefFidelidade.setLayout(btnDefFidelidadeLayout);
        btnDefFidelidadeLayout.setHorizontalGroup(
            btnDefFidelidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDefFidelidadeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnDefFidelidadeLayout.setVerticalGroup(
            btnDefFidelidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        btnRecFidelidade.setBackground(new java.awt.Color(41, 55, 64));
        btnRecFidelidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRecFidelidadeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRecFidelidadeMouseEntered(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Rec. fidelidade");

        javax.swing.GroupLayout btnRecFidelidadeLayout = new javax.swing.GroupLayout(btnRecFidelidade);
        btnRecFidelidade.setLayout(btnRecFidelidadeLayout);
        btnRecFidelidadeLayout.setHorizontalGroup(
            btnRecFidelidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnRecFidelidadeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnRecFidelidadeLayout.setVerticalGroup(
            btnRecFidelidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        btnOfertaespecial.setBackground(new java.awt.Color(41, 55, 64));
        btnOfertaespecial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOfertaespecialMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnOfertaespecialMouseEntered(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Oferta especial");

        javax.swing.GroupLayout btnOfertaespecialLayout = new javax.swing.GroupLayout(btnOfertaespecial);
        btnOfertaespecial.setLayout(btnOfertaespecialLayout);
        btnOfertaespecialLayout.setHorizontalGroup(
            btnOfertaespecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnOfertaespecialLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnOfertaespecialLayout.setVerticalGroup(
            btnOfertaespecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Serviços");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDefFidelidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRecFidelidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOfertaespecial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDefFidelidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRecFidelidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnOfertaespecial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        DIV.setBackground(new java.awt.Color(255, 255, 255));

        TelaProduto.setBackground(new java.awt.Color(255, 255, 255));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtPesquisarProduto.setForeground(new java.awt.Color(153, 153, 153));
        txtPesquisarProduto.setText("Pesquisar");
        txtPesquisarProduto.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtPesquisarProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarProdutoKeyReleased(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("Apg.tds");

        pBtnInserir.setBackground(new java.awt.Color(148, 32, 33));
        pBtnInserir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pBtnInserirMouseClicked(evt);
            }
        });

        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("inserir");

        javax.swing.GroupLayout pBtnInserirLayout = new javax.swing.GroupLayout(pBtnInserir);
        pBtnInserir.setLayout(pBtnInserirLayout);
        pBtnInserirLayout.setHorizontalGroup(
            pBtnInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pBtnInserirLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel32)
                .addContainerGap())
        );
        pBtnInserirLayout.setVerticalGroup(
            pBtnInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        pBtnApagar.setBackground(new java.awt.Color(148, 32, 33));
        pBtnApagar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pBtnApagarMouseClicked(evt);
            }
        });

        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("apagar");

        javax.swing.GroupLayout pBtnApagarLayout = new javax.swing.GroupLayout(pBtnApagar);
        pBtnApagar.setLayout(pBtnApagarLayout);
        pBtnApagarLayout.setHorizontalGroup(
            pBtnApagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pBtnApagarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel33)
                .addContainerGap())
        );
        pBtnApagarLayout.setVerticalGroup(
            pBtnApagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        pBtnActualizar.setBackground(new java.awt.Color(148, 32, 33));
        pBtnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pBtnActualizarMouseClicked(evt);
            }
        });

        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("actual");

        javax.swing.GroupLayout pBtnActualizarLayout = new javax.swing.GroupLayout(pBtnActualizar);
        pBtnActualizar.setLayout(pBtnActualizarLayout);
        pBtnActualizarLayout.setHorizontalGroup(
            pBtnActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pBtnActualizarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel34)
                .addContainerGap())
        );
        pBtnActualizarLayout.setVerticalGroup(
            pBtnActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        pBtnLimpar.setBackground(new java.awt.Color(148, 32, 33));
        pBtnLimpar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pBtnLimparMouseClicked(evt);
            }
        });

        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("limpar");

        javax.swing.GroupLayout pBtnLimparLayout = new javax.swing.GroupLayout(pBtnLimpar);
        pBtnLimpar.setLayout(pBtnLimparLayout);
        pBtnLimparLayout.setHorizontalGroup(
            pBtnLimparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pBtnLimparLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel35)
                .addContainerGap())
        );
        pBtnLimparLayout.setVerticalGroup(
            pBtnLimparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel36.setText("Nome:");

        pTxtNome.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel37.setText("Descrição:");

        pTxtDesc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel38.setText("Quantidade:");

        pTxtQuant.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel40.setText("Valor unitário:");

        pTextValor.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel41.setText("ID:");

        pTxtID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel36)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(pTxtID, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pTextValor)
                            .addComponent(pTxtDesc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(pTxtQuant, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pTxtNome, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(pTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(pTxtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(pTxtQuant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(pTextValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(pTxtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lblFoto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/images_icon-icons.png"))); // NOI18N
        lblFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFotoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(txtPesquisarProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel31))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(pBtnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pBtnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pBtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pBtnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pBtnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pBtnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pBtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pBtnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(204, 204, 204));
        jLabel42.setText("Tabela de produtos");

        tbProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Descrição", "Quantidade", "Valor unitário", "Foto"
            }
        ));
        tbProdutos.setRowHeight(25);
        tbProdutos.setSelectionBackground(new java.awt.Color(33, 111, 219));
        tbProdutos.setShowHorizontalLines(true);
        tbProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProdutosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbProdutos);

        javax.swing.GroupLayout TelaProdutoLayout = new javax.swing.GroupLayout(TelaProduto);
        TelaProduto.setLayout(TelaProdutoLayout);
        TelaProdutoLayout.setHorizontalGroup(
            TelaProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TelaProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TelaProdutoLayout.createSequentialGroup()
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        TelaProdutoLayout.setVerticalGroup(
            TelaProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(TelaProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        TelaRelatorio.setBackground(new java.awt.Color(255, 255, 255));

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(204, 204, 204));
        jLabel61.setText("Relatório de vendas");

        tbVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cliente", "Produtos", "Data", "Valor total"
            }
        ));
        tbVendas.setSelectionBackground(new java.awt.Color(33, 111, 219));
        tbVendas.setShowHorizontalLines(true);
        tbVendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbVendasMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbVendas);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtPesquisarVenda.setForeground(new java.awt.Color(153, 153, 153));
        txtPesquisarVenda.setText("Pesquisar");
        txtPesquisarVenda.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtPesquisarVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarVendaKeyReleased(evt);
            }
        });

        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel92.setText("Apg.tds");

        vBtnInserir.setBackground(new java.awt.Color(148, 32, 33));
        vBtnInserir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vBtnInserirMouseClicked(evt);
            }
        });

        jLabel93.setForeground(new java.awt.Color(255, 255, 255));
        jLabel93.setText("inserir");

        javax.swing.GroupLayout vBtnInserirLayout = new javax.swing.GroupLayout(vBtnInserir);
        vBtnInserir.setLayout(vBtnInserirLayout);
        vBtnInserirLayout.setHorizontalGroup(
            vBtnInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vBtnInserirLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel93)
                .addContainerGap())
        );
        vBtnInserirLayout.setVerticalGroup(
            vBtnInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel93, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        vBtnApagar.setBackground(new java.awt.Color(148, 32, 33));
        vBtnApagar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vBtnApagarMouseClicked(evt);
            }
        });

        jLabel94.setForeground(new java.awt.Color(255, 255, 255));
        jLabel94.setText("apagar");

        javax.swing.GroupLayout vBtnApagarLayout = new javax.swing.GroupLayout(vBtnApagar);
        vBtnApagar.setLayout(vBtnApagarLayout);
        vBtnApagarLayout.setHorizontalGroup(
            vBtnApagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vBtnApagarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel94)
                .addContainerGap())
        );
        vBtnApagarLayout.setVerticalGroup(
            vBtnApagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel94, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        vBtnActualizar.setBackground(new java.awt.Color(148, 32, 33));
        vBtnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vBtnActualizarMouseClicked(evt);
            }
        });

        jLabel95.setForeground(new java.awt.Color(255, 255, 255));
        jLabel95.setText("actual");

        javax.swing.GroupLayout vBtnActualizarLayout = new javax.swing.GroupLayout(vBtnActualizar);
        vBtnActualizar.setLayout(vBtnActualizarLayout);
        vBtnActualizarLayout.setHorizontalGroup(
            vBtnActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vBtnActualizarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel95)
                .addContainerGap())
        );
        vBtnActualizarLayout.setVerticalGroup(
            vBtnActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel95, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        vBtnLimpar.setBackground(new java.awt.Color(148, 32, 33));
        vBtnLimpar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vBtnLimparMouseClicked(evt);
            }
        });

        jLabel96.setForeground(new java.awt.Color(255, 255, 255));
        jLabel96.setText("limpar");

        javax.swing.GroupLayout vBtnLimparLayout = new javax.swing.GroupLayout(vBtnLimpar);
        vBtnLimpar.setLayout(vBtnLimparLayout);
        vBtnLimparLayout.setHorizontalGroup(
            vBtnLimparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vBtnLimparLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel96)
                .addContainerGap())
        );
        vBtnLimparLayout.setVerticalGroup(
            vBtnLimparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel96, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel97.setText("Cliente:");

        vTxtCliente.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel98.setText("Produtos:");

        vTxtProdutos.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel99.setText("Data:");

        jLabel100.setText("Valor total:");

        jLabel101.setText("ID:");

        vTxtID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        vTxtValorTotal.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        vTxtData.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        try {
            vTxtData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel100)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vTxtValorTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(vTxtID))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel97)
                            .addComponent(jLabel99)
                            .addComponent(jLabel98))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vTxtProdutos)
                            .addComponent(vTxtCliente)
                            .addComponent(vTxtData))))
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel97)
                    .addComponent(vTxtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel98)
                    .addComponent(vTxtProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel99)
                    .addComponent(vTxtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel100)
                    .addComponent(vTxtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel101)
                    .addComponent(vTxtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));
        jPanel32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        btnImprimir.setBackground(new java.awt.Color(148, 32, 33));
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/printer-icon-3d-modeling-png.png"))); // NOI18N
        btnImprimir.setBorderPainted(false);
        btnImprimir.setFocusPainted(false);
        btnImprimir.setFocusable(false);
        btnImprimir.setVerifyInputWhenFocusTarget(false);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnEsvaziarVendas.setBackground(new java.awt.Color(247, 247, 255));
        btnEsvaziarVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/del.png"))); // NOI18N
        btnEsvaziarVendas.setFocusable(false);
        btnEsvaziarVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEsvaziarVendasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnImprimir)
                .addGap(39, 39, 39)
                .addComponent(btnEsvaziarVendas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEsvaziarVendas)
                    .addComponent(btnImprimir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(txtPesquisarVenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel92))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(vBtnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vBtnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vBtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vBtnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel92))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vBtnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vBtnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vBtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vBtnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout TelaRelatorioLayout = new javax.swing.GroupLayout(TelaRelatorio);
        TelaRelatorio.setLayout(TelaRelatorioLayout);
        TelaRelatorioLayout.setHorizontalGroup(
            TelaRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaRelatorioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TelaRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TelaRelatorioLayout.createSequentialGroup()
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        TelaRelatorioLayout.setVerticalGroup(
            TelaRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaRelatorioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TelaRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TelaRelatorioLayout.createSequentialGroup()
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE))
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        TelaUsuario.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(204, 204, 204));
        jLabel19.setText("Tabela de usuários");

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtPesquisarUsuario.setForeground(new java.awt.Color(153, 153, 153));
        txtPesquisarUsuario.setText("Pesquisar");
        txtPesquisarUsuario.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtPesquisarUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarUsuarioKeyReleased(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Apg.tds");

        uBtnInserir.setBackground(new java.awt.Color(148, 32, 33));
        uBtnInserir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uBtnInserirMouseClicked(evt);
            }
        });

        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("inserir");

        javax.swing.GroupLayout uBtnInserirLayout = new javax.swing.GroupLayout(uBtnInserir);
        uBtnInserir.setLayout(uBtnInserirLayout);
        uBtnInserirLayout.setHorizontalGroup(
            uBtnInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, uBtnInserirLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addContainerGap())
        );
        uBtnInserirLayout.setVerticalGroup(
            uBtnInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        uBtnApagar.setBackground(new java.awt.Color(148, 32, 33));
        uBtnApagar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uBtnApagarMouseClicked(evt);
            }
        });

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("apagar");

        javax.swing.GroupLayout uBtnApagarLayout = new javax.swing.GroupLayout(uBtnApagar);
        uBtnApagar.setLayout(uBtnApagarLayout);
        uBtnApagarLayout.setHorizontalGroup(
            uBtnApagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, uBtnApagarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addContainerGap())
        );
        uBtnApagarLayout.setVerticalGroup(
            uBtnApagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        uBtnActualizar.setBackground(new java.awt.Color(148, 32, 33));
        uBtnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uBtnActualizarMouseClicked(evt);
            }
        });

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("actual");

        javax.swing.GroupLayout uBtnActualizarLayout = new javax.swing.GroupLayout(uBtnActualizar);
        uBtnActualizar.setLayout(uBtnActualizarLayout);
        uBtnActualizarLayout.setHorizontalGroup(
            uBtnActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, uBtnActualizarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addContainerGap())
        );
        uBtnActualizarLayout.setVerticalGroup(
            uBtnActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        uBtnLimpar.setBackground(new java.awt.Color(148, 32, 33));
        uBtnLimpar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uBtnLimparMouseClicked(evt);
            }
        });

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("limpar");

        javax.swing.GroupLayout uBtnLimparLayout = new javax.swing.GroupLayout(uBtnLimpar);
        uBtnLimpar.setLayout(uBtnLimparLayout);
        uBtnLimparLayout.setHorizontalGroup(
            uBtnLimparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, uBtnLimparLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addContainerGap())
        );
        uBtnLimparLayout.setVerticalGroup(
            uBtnLimparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel25.setText("Nome:");

        uTxtNome.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel26.setText("Usuário:");

        uTxtUser.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel27.setText("Senha:");

        uTxtSenha.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel28.setText("Tipo:");

        uTxtTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Normal" }));

        jLabel30.setText("ID:");

        uTxtID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addComponent(jLabel25)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(uTxtTipo, 0, 154, Short.MAX_VALUE)
                            .addComponent(uTxtNome)
                            .addComponent(uTxtSenha)
                            .addComponent(uTxtUser)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(uTxtID)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(uTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(uTxtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(uTxtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(uTxtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(uTxtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 138, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(txtPesquisarUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(uBtnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(uBtnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(uBtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(uBtnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(uBtnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uBtnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uBtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uBtnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Usuário", "Senha", "Tipo"
            }
        ));
        tbUsuario.setSelectionBackground(new java.awt.Color(33, 111, 219));
        tbUsuario.setShowHorizontalLines(true);
        tbUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbUsuario);

        javax.swing.GroupLayout TelaUsuarioLayout = new javax.swing.GroupLayout(TelaUsuario);
        TelaUsuario.setLayout(TelaUsuarioLayout);
        TelaUsuarioLayout.setHorizontalGroup(
            TelaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TelaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TelaUsuarioLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 153, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        TelaUsuarioLayout.setVerticalGroup(
            TelaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(TelaUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        TelaMais.setBackground(new java.awt.Color(255, 255, 255));

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(204, 204, 204));
        jLabel78.setText("Tabela de reservas");

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtPesquisarReserva.setForeground(new java.awt.Color(153, 153, 153));
        txtPesquisarReserva.setText("Pesquisar");
        txtPesquisarReserva.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtPesquisarReserva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarReservaKeyReleased(evt);
            }
        });

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel79.setText("Apg.tds");

        rBtnInserir.setBackground(new java.awt.Color(148, 32, 33));
        rBtnInserir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rBtnInserirMouseClicked(evt);
            }
        });

        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setText("inserir");

        javax.swing.GroupLayout rBtnInserirLayout = new javax.swing.GroupLayout(rBtnInserir);
        rBtnInserir.setLayout(rBtnInserirLayout);
        rBtnInserirLayout.setHorizontalGroup(
            rBtnInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rBtnInserirLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel80)
                .addContainerGap())
        );
        rBtnInserirLayout.setVerticalGroup(
            rBtnInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        rBtnApagar.setBackground(new java.awt.Color(148, 32, 33));
        rBtnApagar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rBtnApagarMouseClicked(evt);
            }
        });

        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setText("apagar");

        javax.swing.GroupLayout rBtnApagarLayout = new javax.swing.GroupLayout(rBtnApagar);
        rBtnApagar.setLayout(rBtnApagarLayout);
        rBtnApagarLayout.setHorizontalGroup(
            rBtnApagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rBtnApagarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel81)
                .addContainerGap())
        );
        rBtnApagarLayout.setVerticalGroup(
            rBtnApagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        rBtnActualizar.setBackground(new java.awt.Color(148, 32, 33));
        rBtnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rBtnActualizarMouseClicked(evt);
            }
        });

        jLabel82.setForeground(new java.awt.Color(255, 255, 255));
        jLabel82.setText("actual");

        javax.swing.GroupLayout rBtnActualizarLayout = new javax.swing.GroupLayout(rBtnActualizar);
        rBtnActualizar.setLayout(rBtnActualizarLayout);
        rBtnActualizarLayout.setHorizontalGroup(
            rBtnActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rBtnActualizarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel82)
                .addContainerGap())
        );
        rBtnActualizarLayout.setVerticalGroup(
            rBtnActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel82, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        rBtnLimpar.setBackground(new java.awt.Color(148, 32, 33));
        rBtnLimpar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rBtnLimparMouseClicked(evt);
            }
        });

        jLabel83.setForeground(new java.awt.Color(255, 255, 255));
        jLabel83.setText("limpar");

        javax.swing.GroupLayout rBtnLimparLayout = new javax.swing.GroupLayout(rBtnLimpar);
        rBtnLimpar.setLayout(rBtnLimparLayout);
        rBtnLimparLayout.setHorizontalGroup(
            rBtnLimparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rBtnLimparLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel83)
                .addContainerGap())
        );
        rBtnLimparLayout.setVerticalGroup(
            rBtnLimparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel83, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel84.setText("Nome:");

        rTxtNome.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel85.setText("Email:");

        rTxtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel86.setText("Telefone:");

        jLabel87.setText("Data:");

        jLabel88.setText("Hora:");

        jLabel91.setText("ID:");

        rTxtID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        rTxtHora.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        try {
            rTxtHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        rTxtTel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        try {
            rTxtTel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        rTxtData.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        try {
            rTxtData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel84)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rTxtHora))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rTxtID))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel85)
                            .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel86))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rTxtTel)
                            .addComponent(rTxtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(rTxtNome, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rTxtData))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel84)
                    .addComponent(rTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel85)
                    .addComponent(rTxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel86)
                    .addComponent(rTxtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel87)
                    .addComponent(rTxtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel88)
                    .addComponent(rTxtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel91)
                    .addComponent(rTxtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel89.setText("# de Pessoas:");

        rTxtnPessoas.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel90.setText("Mensagem:");

        rTxtMsg.setToolTipText("Escreva sua mensagem");
        rTxtMsg.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rTxtMsg, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rTxtnPessoas)))
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel89)
                    .addComponent(rTxtnPessoas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel90)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rTxtMsg, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(txtPesquisarReserva)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel79))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(rBtnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rBtnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rBtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rBtnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel79))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rBtnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rBtnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rBtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rBtnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Email", "Telefone", "Data", "Hora", "N* pessoas", "Mensagem"
            }
        ));
        tbReservas.setRowHeight(25);
        tbReservas.setSelectionBackground(new java.awt.Color(33, 111, 219));
        tbReservas.setShowHorizontalLines(true);
        tbReservas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbReservasMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbReservas);

        javax.swing.GroupLayout TelaMaisLayout = new javax.swing.GroupLayout(TelaMais);
        TelaMais.setLayout(TelaMaisLayout);
        TelaMaisLayout.setHorizontalGroup(
            TelaMaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaMaisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TelaMaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TelaMaisLayout.createSequentialGroup()
                        .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        TelaMaisLayout.setVerticalGroup(
            TelaMaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaMaisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        TelaFuncionarios.setBackground(new java.awt.Color(255, 255, 255));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(204, 204, 204));
        jLabel29.setText("Tabela de funcionários");

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtPesquisarFuncionario.setForeground(new java.awt.Color(153, 153, 153));
        txtPesquisarFuncionario.setText("Pesquisar");
        txtPesquisarFuncionario.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtPesquisarFuncionario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarFuncionarioKeyReleased(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setText("Apg.tds");

        fBtnInserir.setBackground(new java.awt.Color(148, 32, 33));
        fBtnInserir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fBtnInserirMouseClicked(evt);
            }
        });

        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("inserir");

        javax.swing.GroupLayout fBtnInserirLayout = new javax.swing.GroupLayout(fBtnInserir);
        fBtnInserir.setLayout(fBtnInserirLayout);
        fBtnInserirLayout.setHorizontalGroup(
            fBtnInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fBtnInserirLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel53)
                .addContainerGap())
        );
        fBtnInserirLayout.setVerticalGroup(
            fBtnInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        fBtnApagar.setBackground(new java.awt.Color(148, 32, 33));
        fBtnApagar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fBtnApagarMouseClicked(evt);
            }
        });

        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("apagar");

        javax.swing.GroupLayout fBtnApagarLayout = new javax.swing.GroupLayout(fBtnApagar);
        fBtnApagar.setLayout(fBtnApagarLayout);
        fBtnApagarLayout.setHorizontalGroup(
            fBtnApagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fBtnApagarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel55)
                .addContainerGap())
        );
        fBtnApagarLayout.setVerticalGroup(
            fBtnApagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        fBtnActualizar.setBackground(new java.awt.Color(148, 32, 33));
        fBtnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fBtnActualizarMouseClicked(evt);
            }
        });

        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("actual");

        javax.swing.GroupLayout fBtnActualizarLayout = new javax.swing.GroupLayout(fBtnActualizar);
        fBtnActualizar.setLayout(fBtnActualizarLayout);
        fBtnActualizarLayout.setHorizontalGroup(
            fBtnActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fBtnActualizarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel56)
                .addContainerGap())
        );
        fBtnActualizarLayout.setVerticalGroup(
            fBtnActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        fBtnLimpar.setBackground(new java.awt.Color(148, 32, 33));
        fBtnLimpar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fBtnLimparMouseClicked(evt);
            }
        });

        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("limpar");

        javax.swing.GroupLayout fBtnLimparLayout = new javax.swing.GroupLayout(fBtnLimpar);
        fBtnLimpar.setLayout(fBtnLimparLayout);
        fBtnLimparLayout.setHorizontalGroup(
            fBtnLimparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fBtnLimparLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel57)
                .addContainerGap())
        );
        fBtnLimparLayout.setVerticalGroup(
            fBtnLimparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel58.setText("Nome:");

        fTxtNome.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel59.setText("Idade:");

        fTxtIdade.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel60.setText("Cargo:");

        fTxtCargo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel62.setText("ID:");

        fTxtID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel59)
                    .addComponent(jLabel60)
                    .addComponent(jLabel58)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fTxtID, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fTxtNome)
                    .addComponent(fTxtCargo)
                    .addComponent(fTxtIdade))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(fTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(fTxtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(fTxtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fTxtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 145, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(txtPesquisarFuncionario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel39))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(fBtnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fBtnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fBtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fBtnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fBtnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fBtnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fBtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fBtnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Idade", "Cargo"
            }
        ));
        tbFuncionarios.setSelectionBackground(new java.awt.Color(33, 111, 219));
        tbFuncionarios.setShowHorizontalLines(true);
        tbFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFuncionariosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbFuncionarios);

        javax.swing.GroupLayout TelaFuncionariosLayout = new javax.swing.GroupLayout(TelaFuncionarios);
        TelaFuncionarios.setLayout(TelaFuncionariosLayout);
        TelaFuncionariosLayout.setHorizontalGroup(
            TelaFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaFuncionariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TelaFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TelaFuncionariosLayout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        TelaFuncionariosLayout.setVerticalGroup(
            TelaFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaFuncionariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        TelaDispositivos.setBackground(new java.awt.Color(255, 255, 255));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(204, 204, 204));
        jLabel43.setText("Tabela de dispositivos");

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtPesquisarDispositivo.setForeground(new java.awt.Color(153, 153, 153));
        txtPesquisarDispositivo.setText("Pesquisar");
        txtPesquisarDispositivo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtPesquisarDispositivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarDispositivoKeyReleased(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("Apg.tds");

        desBtnInserir.setBackground(new java.awt.Color(148, 32, 33));
        desBtnInserir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                desBtnInserirMouseClicked(evt);
            }
        });

        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("inserir");

        javax.swing.GroupLayout desBtnInserirLayout = new javax.swing.GroupLayout(desBtnInserir);
        desBtnInserir.setLayout(desBtnInserirLayout);
        desBtnInserirLayout.setHorizontalGroup(
            desBtnInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, desBtnInserirLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel45)
                .addContainerGap())
        );
        desBtnInserirLayout.setVerticalGroup(
            desBtnInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        desBtnApagar.setBackground(new java.awt.Color(148, 32, 33));
        desBtnApagar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                desBtnApagarMouseClicked(evt);
            }
        });

        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("apagar");

        javax.swing.GroupLayout desBtnApagarLayout = new javax.swing.GroupLayout(desBtnApagar);
        desBtnApagar.setLayout(desBtnApagarLayout);
        desBtnApagarLayout.setHorizontalGroup(
            desBtnApagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, desBtnApagarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel46)
                .addContainerGap())
        );
        desBtnApagarLayout.setVerticalGroup(
            desBtnApagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        desBtnActualizar.setBackground(new java.awt.Color(148, 32, 33));
        desBtnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                desBtnActualizarMouseClicked(evt);
            }
        });

        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("actual");

        javax.swing.GroupLayout desBtnActualizarLayout = new javax.swing.GroupLayout(desBtnActualizar);
        desBtnActualizar.setLayout(desBtnActualizarLayout);
        desBtnActualizarLayout.setHorizontalGroup(
            desBtnActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, desBtnActualizarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel47)
                .addContainerGap())
        );
        desBtnActualizarLayout.setVerticalGroup(
            desBtnActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        desBtnLimpar.setBackground(new java.awt.Color(148, 32, 33));
        desBtnLimpar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                desBtnLimparMouseClicked(evt);
            }
        });

        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("limpar");

        javax.swing.GroupLayout desBtnLimparLayout = new javax.swing.GroupLayout(desBtnLimpar);
        desBtnLimpar.setLayout(desBtnLimparLayout);
        desBtnLimparLayout.setHorizontalGroup(
            desBtnLimparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, desBtnLimparLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel48)
                .addContainerGap())
        );
        desBtnLimparLayout.setVerticalGroup(
            desBtnLimparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel49.setText("Prop.");

        desTxtProprietrario.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel50.setText("ID proc.");

        desTxtIDProcessador.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel52.setText("Status:");

        desTxtEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ativo", "inativo" }));

        jLabel54.setText("ID:");

        desTxtID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50)
                            .addComponent(jLabel49))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(desTxtProprietrario)
                            .addComponent(desTxtIDProcessador)))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addGap(8, 8, 8)
                        .addComponent(desTxtEstado, 0, 154, Short.MAX_VALUE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(desTxtID, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(desTxtProprietrario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(desTxtIDProcessador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52)
                    .addComponent(desTxtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(desTxtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 133, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(txtPesquisarDispositivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel44))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(desBtnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(desBtnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(desBtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(desBtnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarDispositivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(desBtnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(desBtnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(desBtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(desBtnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbDispositivo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Proprietário", "ID processador", "Status"
            }
        ));
        tbDispositivo.setRowHeight(30);
        tbDispositivo.setSelectionBackground(new java.awt.Color(33, 111, 219));
        tbDispositivo.setShowHorizontalLines(true);
        tbDispositivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDispositivoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbDispositivo);

        javax.swing.GroupLayout TelaDispositivosLayout = new javax.swing.GroupLayout(TelaDispositivos);
        TelaDispositivos.setLayout(TelaDispositivosLayout);
        TelaDispositivosLayout.setHorizontalGroup(
            TelaDispositivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaDispositivosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TelaDispositivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TelaDispositivosLayout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 197, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        TelaDispositivosLayout.setVerticalGroup(
            TelaDispositivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaDispositivosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        TelaDefinicoes.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/foto-do-usuario.png"))); // NOI18N

        lblUserName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblUserName.setText("Nome do usuário");

        lblUserTipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUserTipo.setForeground(new java.awt.Color(153, 153, 153));
        lblUserTipo.setText("Tipo de conta");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUserName)
                    .addComponent(lblUserTipo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblUserName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUserTipo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSobre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSobreMouseClicked(evt);
            }
        });

        jLabel69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ic_info.png"))); // NOI18N

        jLabel70.setBackground(new java.awt.Color(49, 49, 49));
        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(49, 49, 49));
        jLabel70.setText("Sobre");

        jLabel71.setForeground(new java.awt.Color(102, 102, 102));
        jLabel71.setText("Ver informações do App");

        javax.swing.GroupLayout btnSobreLayout = new javax.swing.GroupLayout(btnSobre);
        btnSobre.setLayout(btnSobreLayout);
        btnSobreLayout.setHorizontalGroup(
            btnSobreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSobreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(btnSobreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(btnSobreLayout.createSequentialGroup()
                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        btnSobreLayout.setVerticalGroup(
            btnSobreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSobreLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(btnSobreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(btnSobreLayout.createSequentialGroup()
                        .addComponent(jLabel70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel71))
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        btnComunicarProblema.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnComunicarProblemaMouseClicked(evt);
            }
        });

        jLabel72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ic_error.png"))); // NOI18N

        jLabel73.setBackground(new java.awt.Color(49, 49, 49));
        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(49, 49, 49));
        jLabel73.setText("Comunicar problema");

        jLabel74.setForeground(new java.awt.Color(102, 102, 102));
        jLabel74.setText("Falar com a assistência tecnica");

        javax.swing.GroupLayout btnComunicarProblemaLayout = new javax.swing.GroupLayout(btnComunicarProblema);
        btnComunicarProblema.setLayout(btnComunicarProblemaLayout);
        btnComunicarProblemaLayout.setHorizontalGroup(
            btnComunicarProblemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnComunicarProblemaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(btnComunicarProblemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 131, Short.MAX_VALUE))
                .addContainerGap())
        );
        btnComunicarProblemaLayout.setVerticalGroup(
            btnComunicarProblemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnComunicarProblemaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnComunicarProblemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btnComunicarProblemaLayout.createSequentialGroup()
                        .addComponent(jLabel73)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel74))
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        btnMaisApps.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMaisAppsMouseClicked(evt);
            }
        });

        jLabel75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ic_more.png"))); // NOI18N

        jLabel76.setBackground(new java.awt.Color(49, 49, 49));
        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(49, 49, 49));
        jLabel76.setText("Mais apps");

        jLabel77.setForeground(new java.awt.Color(102, 102, 102));
        jLabel77.setText("Conheça mais apps do desenvolvedor");

        javax.swing.GroupLayout btnMaisAppsLayout = new javax.swing.GroupLayout(btnMaisApps);
        btnMaisApps.setLayout(btnMaisAppsLayout);
        btnMaisAppsLayout.setHorizontalGroup(
            btnMaisAppsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnMaisAppsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(btnMaisAppsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btnMaisAppsLayout.createSequentialGroup()
                        .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                        .addGap(14, 14, 14))
                    .addGroup(btnMaisAppsLayout.createSequentialGroup()
                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        btnMaisAppsLayout.setVerticalGroup(
            btnMaisAppsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnMaisAppsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnMaisAppsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btnMaisAppsLayout.createSequentialGroup()
                        .addComponent(jLabel76)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel77))
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 189, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 58, Short.MAX_VALUE)
        );

        btnVerDispositivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVerDispositivosMouseClicked(evt);
            }
        });

        jLabel66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ic_def_dispositivo.png"))); // NOI18N

        jLabel67.setBackground(new java.awt.Color(49, 49, 49));
        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(49, 49, 49));
        jLabel67.setText("Ver dispositivos");

        jLabel68.setForeground(new java.awt.Color(102, 102, 102));
        jLabel68.setText("Clique para ver aparelhos");

        javax.swing.GroupLayout btnVerDispositivosLayout = new javax.swing.GroupLayout(btnVerDispositivos);
        btnVerDispositivos.setLayout(btnVerDispositivosLayout);
        btnVerDispositivosLayout.setHorizontalGroup(
            btnVerDispositivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnVerDispositivosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(btnVerDispositivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 131, Short.MAX_VALUE))
                .addContainerGap())
        );
        btnVerDispositivosLayout.setVerticalGroup(
            btnVerDispositivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnVerDispositivosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnVerDispositivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btnVerDispositivosLayout.createSequentialGroup()
                        .addComponent(jLabel67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel68))
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        btnTerminarSessao.setPreferredSize(new java.awt.Dimension(189, 58));
        btnTerminarSessao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTerminarSessaoMouseClicked(evt);
            }
        });

        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ic_terminar_sessao.png"))); // NOI18N

        jLabel63.setBackground(new java.awt.Color(49, 49, 49));
        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(49, 49, 49));
        jLabel63.setText("Terminar Sessão");

        jLabel64.setForeground(new java.awt.Color(102, 102, 102));
        jLabel64.setText("Clique para sair");

        javax.swing.GroupLayout btnTerminarSessaoLayout = new javax.swing.GroupLayout(btnTerminarSessao);
        btnTerminarSessao.setLayout(btnTerminarSessaoLayout);
        btnTerminarSessaoLayout.setHorizontalGroup(
            btnTerminarSessaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnTerminarSessaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(btnTerminarSessaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        btnTerminarSessaoLayout.setVerticalGroup(
            btnTerminarSessaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnTerminarSessaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnTerminarSessaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btnTerminarSessaoLayout.createSequentialGroup()
                        .addComponent(jLabel63)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel64))
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout TelaDefinicoesLayout = new javax.swing.GroupLayout(TelaDefinicoes);
        TelaDefinicoes.setLayout(TelaDefinicoesLayout);
        TelaDefinicoesLayout.setHorizontalGroup(
            TelaDefinicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(TelaDefinicoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TelaDefinicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSobre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(TelaDefinicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVerDispositivos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnComunicarProblema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(TelaDefinicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMaisApps, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTerminarSessao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        TelaDefinicoesLayout.setVerticalGroup(
            TelaDefinicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaDefinicoesLayout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(TelaDefinicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaDefinicoesLayout.createSequentialGroup()
                        .addComponent(btnSobre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaDefinicoesLayout.createSequentialGroup()
                        .addGroup(TelaDefinicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnMaisApps, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnComunicarProblema, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)))
                .addGroup(TelaDefinicoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTerminarSessao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerDispositivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(131, 131, 131))
        );

        TelaDefFid.setBackground(new java.awt.Color(255, 153, 153));

        javax.swing.GroupLayout TelaDefFidLayout = new javax.swing.GroupLayout(TelaDefFid);
        TelaDefFid.setLayout(TelaDefFidLayout);
        TelaDefFidLayout.setHorizontalGroup(
            TelaDefFidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 593, Short.MAX_VALUE)
        );
        TelaDefFidLayout.setVerticalGroup(
            TelaDefFidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 384, Short.MAX_VALUE)
        );

        TelaRecFid.setBackground(new java.awt.Color(153, 0, 153));

        javax.swing.GroupLayout TelaRecFidLayout = new javax.swing.GroupLayout(TelaRecFid);
        TelaRecFid.setLayout(TelaRecFidLayout);
        TelaRecFidLayout.setHorizontalGroup(
            TelaRecFidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 593, Short.MAX_VALUE)
        );
        TelaRecFidLayout.setVerticalGroup(
            TelaRecFidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 384, Short.MAX_VALUE)
        );

        TelaDashboard.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(254, 112, 150));
        jPanel4.setPreferredSize(new java.awt.Dimension(128, 44));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ic_funcionarios.png"))); // NOI18N

        txtNumerodeFuncionarios.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtNumerodeFuncionarios.setForeground(new java.awt.Color(255, 255, 255));
        txtNumerodeFuncionarios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNumerodeFuncionarios.setText("00");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumerodeFuncionarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNumerodeFuncionarios, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(92, 194, 237));
        jPanel5.setPreferredSize(new java.awt.Dimension(128, 44));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ic_celulares.png"))); // NOI18N

        txtNumerodeDispositivos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtNumerodeDispositivos.setForeground(new java.awt.Color(255, 255, 255));
        txtNumerodeDispositivos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNumerodeDispositivos.setText("00");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumerodeDispositivos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNumerodeDispositivos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(56, 197, 178));
        jPanel6.setPreferredSize(new java.awt.Dimension(128, 44));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ic_usuarios.png"))); // NOI18N

        txtNumerodeUsuarios.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtNumerodeUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        txtNumerodeUsuarios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNumerodeUsuarios.setText("00");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumerodeUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNumerodeUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/baixados.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addGap(72, 72, 72))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/cor-1.png"))); // NOI18N
        jLabel16.setText("Funcionários");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/cor-2.png"))); // NOI18N
        jLabel17.setText("Dispositivos");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/cor-3.png"))); // NOI18N
        jLabel18.setText("Usuários");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout TelaDashboardLayout = new javax.swing.GroupLayout(TelaDashboard);
        TelaDashboard.setLayout(TelaDashboardLayout);
        TelaDashboardLayout.setHorizontalGroup(
            TelaDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TelaDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TelaDashboardLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(101, 101, 101))
                    .addGroup(TelaDashboardLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(208, 208, 208))))
        );
        TelaDashboardLayout.setVerticalGroup(
            TelaDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaDashboardLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(TelaDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout DIVLayout = new javax.swing.GroupLayout(DIV);
        DIV.setLayout(DIVLayout);
        DIVLayout.setHorizontalGroup(
            DIVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TelaProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(DIVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TelaRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DIVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TelaUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DIVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TelaMais, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DIVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TelaFuncionarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DIVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TelaDispositivos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DIVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TelaDefinicoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DIVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TelaDefFid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DIVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TelaRecFid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DIVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TelaDashboard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DIVLayout.setVerticalGroup(
            DIVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TelaProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(DIVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TelaRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DIVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TelaUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DIVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TelaMais, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DIVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TelaFuncionarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DIVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TelaDispositivos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DIVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TelaDefinicoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DIVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TelaDefFid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DIVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TelaRecFid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DIVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TelaDashboard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnLocal.setBackground(new java.awt.Color(255, 255, 255));
        btnLocal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLocalMouseClicked(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ic_share_black_24dp.png"))); // NOI18N
        jLabel5.setText("Ecolha um local");

        javax.swing.GroupLayout btnLocalLayout = new javax.swing.GroupLayout(btnLocal);
        btnLocal.setLayout(btnLocalLayout);
        btnLocalLayout.setHorizontalGroup(
            btnLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnLocalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnLocalLayout.setVerticalGroup(
            btnLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        btnPrever.setBackground(new java.awt.Color(255, 255, 255));
        btnPrever.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPreverMouseClicked(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ic_desktop_windows_black_24dp.png"))); // NOI18N
        jLabel6.setText("Prever");

        javax.swing.GroupLayout btnPreverLayout = new javax.swing.GroupLayout(btnPrever);
        btnPrever.setLayout(btnPreverLayout);
        btnPreverLayout.setHorizontalGroup(
            btnPreverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPreverLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        btnPreverLayout.setVerticalGroup(
            btnPreverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        btnAjuda.setBackground(new java.awt.Color(255, 255, 255));
        btnAjuda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAjudaMouseClicked(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ic_help_black_24dp.png"))); // NOI18N
        jLabel7.setText("Ajuda");

        javax.swing.GroupLayout btnAjudaLayout = new javax.swing.GroupLayout(btnAjuda);
        btnAjuda.setLayout(btnAjudaLayout);
        btnAjudaLayout.setHorizontalGroup(
            btnAjudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAjudaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnAjudaLayout.setVerticalGroup(
            btnAjudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ic_autorenew_white_24dp.png"))); // NOI18N
        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrever, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAjuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DIV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPrever, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAjuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DIV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDefFidelidadeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDefFidelidadeMouseEntered
        btnDefFidelidade.setBackground(new Color(148, 32, 33));
        btnOfertaespecial.setBackground(new Color(41, 55, 64));
        btnRecFidelidade.setBackground(new Color(41, 55, 64));
    }//GEN-LAST:event_btnDefFidelidadeMouseEntered

    private void btnRecFidelidadeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRecFidelidadeMouseEntered
        btnDefFidelidade.setBackground(new Color(41, 55, 64));
        btnOfertaespecial.setBackground(new Color(41, 55, 64));
        btnRecFidelidade.setBackground(new Color(148, 32, 33));
    }//GEN-LAST:event_btnRecFidelidadeMouseEntered

    private void btnOfertaespecialMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOfertaespecialMouseEntered
        btnDefFidelidade.setBackground(new Color(41, 55, 64));
        btnOfertaespecial.setBackground(new Color(148, 32, 33));
        btnRecFidelidade.setBackground(new Color(41, 55, 64));
    }//GEN-LAST:event_btnOfertaespecialMouseEntered

    private void btnProdutoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProdutoMouseEntered
        btnProduto.setBackground(new Color(41, 55, 64));
    }//GEN-LAST:event_btnProdutoMouseEntered

    private void btnProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProdutoMouseClicked
        TelaDefFid.setVisible(false);
        TelaDefinicoes.setVisible(false);
        TelaDispositivos.setVisible(false);
        TelaFuncionarios.setVisible(false);
        TelaMais.setVisible(false);
        TelaProduto.setVisible(true);
        TelaRecFid.setVisible(false);
        TelaRelatorio.setVisible(false);
        TelaUsuario.setVisible(false);
        TelaDashboard.setVisible(false);
    }//GEN-LAST:event_btnProdutoMouseClicked

    private void btnRelatorioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRelatorioMouseClicked
        TelaDefFid.setVisible(false);
        TelaDefinicoes.setVisible(false);
        TelaDispositivos.setVisible(false);
        TelaFuncionarios.setVisible(false);
        TelaMais.setVisible(false);
        TelaProduto.setVisible(false);
        TelaRecFid.setVisible(false);
        TelaRelatorio.setVisible(true);
        TelaUsuario.setVisible(false);
        TelaDashboard.setVisible(false);
    }//GEN-LAST:event_btnRelatorioMouseClicked

    private void btnUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUsuarioMouseClicked
        UserSession userSession = UserSession.getInstance();
        if (userSession.getTipo().equals("Admin")) {
            TelaDefFid.setVisible(false);
            TelaDefinicoes.setVisible(false);
            TelaDispositivos.setVisible(false);
            TelaFuncionarios.setVisible(false);
            TelaMais.setVisible(false);
            TelaProduto.setVisible(false);
            TelaRecFid.setVisible(false);
            TelaRelatorio.setVisible(false);
            TelaUsuario.setVisible(true);
            TelaDashboard.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Você não tem permissão para entrar!");
        }

    }//GEN-LAST:event_btnUsuarioMouseClicked

    private void btnMaisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaisMouseClicked
        TelaDefFid.setVisible(false);
        TelaDefinicoes.setVisible(false);
        TelaDispositivos.setVisible(false);
        TelaFuncionarios.setVisible(false);
        TelaMais.setVisible(true);
        TelaProduto.setVisible(false);
        TelaRecFid.setVisible(false);
        TelaRelatorio.setVisible(false);
        TelaUsuario.setVisible(false);
        TelaDashboard.setVisible(false);
    }//GEN-LAST:event_btnMaisMouseClicked

    private void btnfuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnfuncionarioMouseClicked
        TelaDefFid.setVisible(false);
        TelaDefinicoes.setVisible(false);
        TelaDispositivos.setVisible(false);
        TelaFuncionarios.setVisible(true);
        TelaMais.setVisible(false);
        TelaProduto.setVisible(false);
        TelaRecFid.setVisible(false);
        TelaRelatorio.setVisible(false);
        TelaUsuario.setVisible(false);
        TelaDashboard.setVisible(false);
    }//GEN-LAST:event_btnfuncionarioMouseClicked

    private void btnDispositivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDispositivoMouseClicked
        UserSession userSession = UserSession.getInstance();
        if (userSession.getTipo().equalsIgnoreCase("Admin")) {
            TelaDefFid.setVisible(false);
            TelaDefinicoes.setVisible(false);
            TelaDispositivos.setVisible(true);
            TelaFuncionarios.setVisible(false);
            TelaMais.setVisible(false);
            TelaProduto.setVisible(false);
            TelaRecFid.setVisible(false);
            TelaRelatorio.setVisible(false);
            TelaUsuario.setVisible(false);
            TelaDashboard.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Você não tem permissão para entrar!");
        }

    }//GEN-LAST:event_btnDispositivoMouseClicked

    private void btndefinicoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btndefinicoesMouseClicked
        TelaDefFid.setVisible(false);
        TelaDefinicoes.setVisible(true);
        TelaDispositivos.setVisible(false);
        TelaFuncionarios.setVisible(false);
        TelaMais.setVisible(false);
        TelaProduto.setVisible(false);
        TelaRecFid.setVisible(false);
        TelaRelatorio.setVisible(false);
        TelaUsuario.setVisible(false);
        TelaDashboard.setVisible(false);
    }//GEN-LAST:event_btndefinicoesMouseClicked

    private void btnDefFidelidadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDefFidelidadeMouseClicked
        TelaDefFid.setVisible(true);
        TelaDefinicoes.setVisible(false);
        TelaDispositivos.setVisible(false);
        TelaFuncionarios.setVisible(false);
        TelaMais.setVisible(false);
        TelaProduto.setVisible(false);
        TelaRecFid.setVisible(false);
        TelaRelatorio.setVisible(false);
        TelaUsuario.setVisible(false);
        TelaDashboard.setVisible(false);
    }//GEN-LAST:event_btnDefFidelidadeMouseClicked

    private void btnRecFidelidadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRecFidelidadeMouseClicked
        TelaDefFid.setVisible(false);
        TelaDefinicoes.setVisible(false);
        TelaDispositivos.setVisible(false);
        TelaFuncionarios.setVisible(false);
        TelaMais.setVisible(false);
        TelaProduto.setVisible(false);
        TelaRecFid.setVisible(true);
        TelaRelatorio.setVisible(false);
        TelaUsuario.setVisible(false);
        TelaDashboard.setVisible(false);
    }//GEN-LAST:event_btnRecFidelidadeMouseClicked

    private void btnOfertaespecialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOfertaespecialMouseClicked
        TelaDefFid.setVisible(false);
        TelaDefinicoes.setVisible(false);
        TelaDispositivos.setVisible(false);
        TelaFuncionarios.setVisible(false);
        TelaMais.setVisible(false);
        TelaProduto.setVisible(false);
        TelaRecFid.setVisible(false);
        TelaRelatorio.setVisible(false);
        TelaUsuario.setVisible(false);
        TelaDashboard.setVisible(true);

        JOptionPane.showMessageDialog(null, "Sem recompensa especial disponíveis!");
    }//GEN-LAST:event_btnOfertaespecialMouseClicked

    private void btnLocalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocalMouseClicked
        TelaDefFid.setVisible(false);
        TelaDefinicoes.setVisible(false);
        TelaDispositivos.setVisible(false);
        TelaFuncionarios.setVisible(false);
        TelaMais.setVisible(false);
        TelaProduto.setVisible(false);
        TelaRecFid.setVisible(false);
        TelaRelatorio.setVisible(false);
        TelaUsuario.setVisible(false);
        TelaDashboard.setVisible(true);

        JOptionPane.showMessageDialog(null, "A opção não está disponivel!");
    }//GEN-LAST:event_btnLocalMouseClicked

    private void btnPreverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreverMouseClicked
        try {
            Desktop.getDesktop().browse(new URL("http://192.168.43.1:8080/Mafa%20site/").toURI());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnPreverMouseClicked

    private void btnAjudaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAjudaMouseClicked
        TelaDefFid.setVisible(false);
        TelaDefinicoes.setVisible(false);
        TelaDispositivos.setVisible(false);
        TelaFuncionarios.setVisible(false);
        TelaMais.setVisible(false);
        TelaProduto.setVisible(false);
        TelaRecFid.setVisible(false);
        TelaRelatorio.setVisible(false);
        TelaUsuario.setVisible(false);
        TelaDashboard.setVisible(true);

        JOptionPane.showMessageDialog(null, "Visite a página da BlueArt!");
    }//GEN-LAST:event_btnAjudaMouseClicked

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        TelaDefFid.setVisible(false);
        TelaDefinicoes.setVisible(false);
        TelaDispositivos.setVisible(false);
        TelaFuncionarios.setVisible(false);
        TelaMais.setVisible(false);
        TelaProduto.setVisible(false);
        TelaRecFid.setVisible(false);
        TelaRelatorio.setVisible(false);
        TelaUsuario.setVisible(false);
        TelaDashboard.setVisible(true);
    }//GEN-LAST:event_btnHomeMouseClicked

    private void lblFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFotoMouseClicked
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("selecionar arquivo");
        jfc.setFileFilter(new FileNameExtensionFilter("arquivo de imagem(*.PNG, *JPG, *JPEG)", "png", "jpg", "jpeg"));
        int resultado = jfc.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            try {
                fis = new FileInputStream(jfc.getSelectedFile());
                tamanho = (int) jfc.getSelectedFile().length();
                Image foto = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);
                lblFoto.setIcon(new ImageIcon(foto));
                lblFoto.updateUI();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_lblFotoMouseClicked

    private void desBtnInserirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desBtnInserirMouseClicked
        Dispositivo d = new Dispositivo();
        d.cadastrarDipositivo();
        desTxtID.setText("");
        desTxtIDProcessador.setText("");
        desTxtProprietrario.setText("");
    }//GEN-LAST:event_desBtnInserirMouseClicked

    private void desBtnApagarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desBtnApagarMouseClicked
        int confirma = JOptionPane.showConfirmDialog(null, "Tem a certeza que quer apagar o registro?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            Dispositivo d = new Dispositivo();
            d.apagarDisposivo(desTxtID.getText());
            desTxtID.setText("");
            desTxtIDProcessador.setText("");
            desTxtProprietrario.setText("");
        }


    }//GEN-LAST:event_desBtnApagarMouseClicked

    private void desBtnActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desBtnActualizarMouseClicked
        Dispositivo d = new Dispositivo();
        d.editarDispositivo(desTxtProprietrario.getText(), desTxtEstado.getSelectedItem().toString(), desTxtID.getText());
        desTxtID.setText("");
        desTxtIDProcessador.setText("");
        desTxtProprietrario.setText("");
    }//GEN-LAST:event_desBtnActualizarMouseClicked

    private void desBtnLimparMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desBtnLimparMouseClicked
        desTxtID.setText("");
        desTxtIDProcessador.setText("");
        desTxtProprietrario.setText("");

    }//GEN-LAST:event_desBtnLimparMouseClicked

    private void uBtnInserirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uBtnInserirMouseClicked
        ListaUsuarios lu = new ListaUsuarios();
        lu.cadastrarUsuario(uTxtNome.getText(), uTxtUser.getText(), uTxtSenha.getText(), uTxtTipo.getSelectedItem().toString());
        uTxtID.setText("");
        uTxtNome.setText("");
        uTxtSenha.setText("");
        uTxtUser.setText("");
    }//GEN-LAST:event_uBtnInserirMouseClicked

    private void uBtnApagarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uBtnApagarMouseClicked
        int confirma = JOptionPane.showConfirmDialog(null, "Tem a certeza que quer apagar o registro?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            ListaUsuarios lu = new ListaUsuarios();
            lu.apagarUsuario(uTxtID.getText());
            uTxtID.setText("");
            uTxtNome.setText("");
            uTxtSenha.setText("");
            uTxtUser.setText("");
        }

    }//GEN-LAST:event_uBtnApagarMouseClicked

    private void uBtnActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uBtnActualizarMouseClicked
        ListaUsuarios lu = new ListaUsuarios();
        lu.editarUsuario(uTxtNome.getText(), uTxtUser.getText(), uTxtSenha.getText(), uTxtTipo.getSelectedItem().toString(), uTxtID.getText());
        uTxtID.setText("");
        uTxtNome.setText("");
        uTxtSenha.setText("");
        uTxtUser.setText("");
    }//GEN-LAST:event_uBtnActualizarMouseClicked

    private void uBtnLimparMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uBtnLimparMouseClicked
        uTxtID.setText("");
        uTxtNome.setText("");
        uTxtSenha.setText("");
        uTxtUser.setText("");
    }//GEN-LAST:event_uBtnLimparMouseClicked

    private void btnActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseClicked
        this.dispose();
        TelaHome t = new TelaHome();
        t.setVisible(true);

    }//GEN-LAST:event_btnActualizarMouseClicked

    private void fBtnInserirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fBtnInserirMouseClicked
        Funcionario f = new Funcionario();
        f.cadastrarFuncionario(fTxtNome.getText(), fTxtIdade.getText(), fTxtCargo.getText());
        fTxtCargo.setText("");
        fTxtID.setText("");
        fTxtIdade.setText("");
        fTxtNome.setText("");
    }//GEN-LAST:event_fBtnInserirMouseClicked

    private void fBtnApagarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fBtnApagarMouseClicked
        int confirma = JOptionPane.showConfirmDialog(null, "Tem a certeza que quer apagar o registro?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            Funcionario f = new Funcionario();
            f.apagarFuncionario(fTxtID.getText());
            fTxtCargo.setText("");
            fTxtID.setText("");
            fTxtIdade.setText("");
            fTxtNome.setText("");
        }

    }//GEN-LAST:event_fBtnApagarMouseClicked

    private void fBtnActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fBtnActualizarMouseClicked
        Funcionario f = new Funcionario();
        f.editarFuncionario(fTxtNome.getText(), fTxtIdade.getText(), fTxtCargo.getText(), fTxtID.getText());
        fTxtCargo.setText("");
        fTxtID.setText("");
        fTxtIdade.setText("");
        fTxtNome.setText("");
    }//GEN-LAST:event_fBtnActualizarMouseClicked

    private void fBtnLimparMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fBtnLimparMouseClicked
        fTxtCargo.setText("");
        fTxtID.setText("");
        fTxtIdade.setText("");
        fTxtNome.setText("");
    }//GEN-LAST:event_fBtnLimparMouseClicked

    private void pBtnInserirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pBtnInserirMouseClicked
        cadastrarProduto();
        pTxtID.setText("");
        pTxtNome.setText("");
        pTxtDesc.setText("");
        pTxtQuant.setText("");
        pTextValor.setText("");
    }//GEN-LAST:event_pBtnInserirMouseClicked

    private void tbDispositivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDispositivoMouseClicked
        setarDipositivo();
    }//GEN-LAST:event_tbDispositivoMouseClicked

    private void tbUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUsuarioMouseClicked
        setarUsuario();
    }//GEN-LAST:event_tbUsuarioMouseClicked

    private void tbProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProdutosMouseClicked
        setarProduto();
    }//GEN-LAST:event_tbProdutosMouseClicked

    private void pBtnLimparMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pBtnLimparMouseClicked
        pTxtID.setText("");
        pTxtNome.setText("");
        pTxtDesc.setText("");
        pTxtQuant.setText("");
        pTextValor.setText("");
    }//GEN-LAST:event_pBtnLimparMouseClicked

    private void btnDefFidelidadeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDefFidelidadeMouseExited
        btnRecFidelidade.setBackground(new Color(41, 55, 64));
    }//GEN-LAST:event_btnDefFidelidadeMouseExited

    private void pBtnActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pBtnActualizarMouseClicked
        actualizarProduto();
        pTxtID.setText("");
        pTxtNome.setText("");
        pTxtDesc.setText("");
        pTxtQuant.setText("");
        pTextValor.setText("");
    }//GEN-LAST:event_pBtnActualizarMouseClicked

    private void pBtnApagarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pBtnApagarMouseClicked
        int confirma = JOptionPane.showConfirmDialog(null, "Tem a certeza que quer apagar o registro?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            Produto p = new Produto();
            p.apagarProduto(pTxtID.getText());
            pTxtID.setText("");
            pTxtNome.setText("");
            pTxtDesc.setText("");
            pTxtQuant.setText("");
            pTextValor.setText("");

        }

    }//GEN-LAST:event_pBtnApagarMouseClicked

    private void tbFuncionariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFuncionariosMouseClicked
        setarFuncionario();
    }//GEN-LAST:event_tbFuncionariosMouseClicked

    private void btnTerminarSessaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTerminarSessaoMouseClicked

        int confirma = JOptionPane.showConfirmDialog(null, "Tem a certeza que quer sair?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            TelaLogin t = new TelaLogin();
            t.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnTerminarSessaoMouseClicked

    private void btnSobreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSobreMouseClicked
        AboutUs a = new AboutUs();
        a.setVisible(true);
    }//GEN-LAST:event_btnSobreMouseClicked

    private void txtPesquisarProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarProdutoKeyReleased
        pesquisarProduto();
    }//GEN-LAST:event_txtPesquisarProdutoKeyReleased

    private void txtPesquisarUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarUsuarioKeyReleased
        pesquisarUsuario();
    }//GEN-LAST:event_txtPesquisarUsuarioKeyReleased

    private void txtPesquisarFuncionarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarFuncionarioKeyReleased
        pesquisarFuncionario();
    }//GEN-LAST:event_txtPesquisarFuncionarioKeyReleased

    private void txtPesquisarDispositivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarDispositivoKeyReleased
        pesquisarDipositivo();
    }//GEN-LAST:event_txtPesquisarDispositivoKeyReleased

    private void txtPesquisarReservaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarReservaKeyReleased
        pesquisarReserva();
    }//GEN-LAST:event_txtPesquisarReservaKeyReleased

    private void rBtnInserirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rBtnInserirMouseClicked
        Reserva r = new Reserva();
        r.fazerRareserva(rTxtNome.getText(), rTxtEmail.getText(), rTxtTel.getText(), rTxtData.getText(), rTxtHora.getText(), rTxtnPessoas.getText(), rTxtMsg.getText());

        rTxtID.setText("");
        rTxtNome.setText("");
        rTxtEmail.setText("");
        rTxtTel.setText("");
        rTxtData.setText("");
        rTxtHora.setText("");
        rTxtnPessoas.setText("");
        rTxtMsg.setText("");
    }//GEN-LAST:event_rBtnInserirMouseClicked

    private void rBtnApagarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rBtnApagarMouseClicked
        if (rTxtID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe um identificador(ID)");
        } else {

            int confirma = JOptionPane.showConfirmDialog(null, "Tem a certeza que quer apagar o registro?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                Reserva r = new Reserva();
                r.apagarRareserva(rTxtID.getText());

                rTxtID.setText("");
                rTxtNome.setText("");
                rTxtEmail.setText("");
                rTxtTel.setText("");
                rTxtData.setText("");
                rTxtHora.setText("");
                rTxtnPessoas.setText("");
                rTxtMsg.setText("");

            }

        }


    }//GEN-LAST:event_rBtnApagarMouseClicked

    private void rBtnActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rBtnActualizarMouseClicked
        if (rTxtID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe um identificador(ID)");
        } else {
            Reserva r = new Reserva();
            r.editarRareserva(rTxtNome.getText(), rTxtEmail.getText(), rTxtTel.getText(), rTxtData.getText(), rTxtHora.getText(), rTxtnPessoas.getText(), rTxtMsg.getText(), rTxtID.getText());
        }

    }//GEN-LAST:event_rBtnActualizarMouseClicked

    private void rBtnLimparMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rBtnLimparMouseClicked
        rTxtID.setText("");
        rTxtNome.setText("");
        rTxtEmail.setText("");
        rTxtTel.setText("");
        rTxtData.setText("");
        rTxtHora.setText("");
        rTxtnPessoas.setText("");
        rTxtMsg.setText("");

    }//GEN-LAST:event_rBtnLimparMouseClicked

    private void tbReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbReservasMouseClicked
        setarReserva();
    }//GEN-LAST:event_tbReservasMouseClicked

    private void tbVendasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVendasMouseClicked
        setarVenda();
    }//GEN-LAST:event_tbVendasMouseClicked

    private void txtPesquisarVendaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarVendaKeyReleased
        pesquisarVenda();
    }//GEN-LAST:event_txtPesquisarVendaKeyReleased

    private void vBtnInserirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vBtnInserirMouseClicked
        Vendas v = new Vendas();
        v.fazerVenda(vTxtCliente.getText(), vTxtProdutos.getText(), vTxtData.getText(), vTxtValorTotal.getText());

        vTxtCliente.setText("");
        vTxtID.setText("");
        vTxtProdutos.setText("");
        vTxtValorTotal.setText("");
    }//GEN-LAST:event_vBtnInserirMouseClicked

    private void vBtnApagarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vBtnApagarMouseClicked

        if (vTxtID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe um identificador(ID)");
        } else {

            int confirma = JOptionPane.showConfirmDialog(null, "Tem a certeza que quer apagar o registro?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                Vendas v = new Vendas();
                v.apagarVenda(vTxtID.getText());

                vTxtCliente.setText("");
                vTxtID.setText("");
                vTxtProdutos.setText("");
                vTxtValorTotal.setText("");

            }

        }


    }//GEN-LAST:event_vBtnApagarMouseClicked

    private void vBtnActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vBtnActualizarMouseClicked
        if (vTxtID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe um identificador(ID)!");
        } else {
            Vendas v = new Vendas();
            v.editarVenda(vTxtID.getText(), vTxtCliente.getText(), vTxtProdutos.getText(), vTxtData.getText(), vTxtValorTotal.getText());
            vTxtCliente.setText("");
            vTxtID.setText("");
            vTxtProdutos.setText("");
            vTxtValorTotal.setText("");
        }

    }//GEN-LAST:event_vBtnActualizarMouseClicked

    private void vBtnLimparMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vBtnLimparMouseClicked
        vTxtCliente.setText("");
        vTxtID.setText("");
        vTxtProdutos.setText("");
        vTxtValorTotal.setText("");
    }//GEN-LAST:event_vBtnLimparMouseClicked

    private void btnComunicarProblemaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComunicarProblemaMouseClicked
        JOptionPane.showMessageDialog(null, "Visite a página da BlueArt!");
    }//GEN-LAST:event_btnComunicarProblemaMouseClicked

    private void btnMaisAppsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaisAppsMouseClicked
        JOptionPane.showMessageDialog(null, "Visite a página da BlueArt!");
    }//GEN-LAST:event_btnMaisAppsMouseClicked

    private void btnVerDispositivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerDispositivosMouseClicked
        JOptionPane.showMessageDialog(null, "Use o botão do menu a esquerda!");
    }//GEN-LAST:event_btnVerDispositivosMouseClicked

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        //Código para exportar a tabela em pdf
        DefaultTableModel mod = (DefaultTableModel) tbVendas.getModel();
        String path = "";
        JFileChooser j = new JFileChooser();
        int y = tbVendas.getColumnCount();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(this);
        if (x == JFileChooser.APPROVE_OPTION) {
            path = j.getSelectedFile().getPath();
        }

        Document doc = new Document();
        String nm = JOptionPane.showInputDialog(null, "Dê um nome ao arquivo:");
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(path + "\\" + nm + ".pdf"));
            doc.open();
            PdfPTable tb = new PdfPTable(y);
            tb.addCell("ID");
            tb.addCell("CLIENTE");
            tb.addCell("PRODUTOS");
            tb.addCell("DATA");
            tb.addCell("VALOR TOTAL");
            for (int i = 0; i < tbVendas.getRowCount(); ++i) {
                int a =  (int) tbVendas.getValueAt(i, 0);
                String b = (String) tbVendas.getValueAt(i, 1);
                String c = (String) tbVendas.getValueAt(i, 2);
                String d = (String) tbVendas.getValueAt(i, 3);
                String e = (String) tbVendas.getValueAt(i, 4);

                tb.addCell(""+a);
                tb.addCell(b);
                tb.addCell(c);
                tb.addCell(d);
                tb.addCell(e);
            }
            doc.add(tb);
            JOptionPane.showMessageDialog(null, "PDF criado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu algum erro! \n"+e);
        }
        doc.close();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnEsvaziarVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEsvaziarVendasActionPerformed
        int confirma = JOptionPane.showConfirmDialog(null, "Tem a certeza que quer apagar todas as vendas?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {

                Vendas v = new Vendas();
                v.esfaziarVendas();

            }
    }//GEN-LAST:event_btnEsvaziarVendasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DIV;
    private javax.swing.JPanel TelaDashboard;
    private javax.swing.JPanel TelaDefFid;
    private javax.swing.JPanel TelaDefinicoes;
    private javax.swing.JPanel TelaDispositivos;
    private javax.swing.JPanel TelaFuncionarios;
    private javax.swing.JPanel TelaMais;
    private javax.swing.JPanel TelaProduto;
    private javax.swing.JPanel TelaRecFid;
    private javax.swing.JPanel TelaRelatorio;
    private javax.swing.JPanel TelaUsuario;
    private javax.swing.JLabel btnActualizar;
    private javax.swing.JPanel btnAjuda;
    private javax.swing.JPanel btnComunicarProblema;
    private javax.swing.JPanel btnDefFidelidade;
    private javax.swing.JLabel btnDispositivo;
    private javax.swing.JButton btnEsvaziarVendas;
    private javax.swing.JLabel btnHome;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JPanel btnLocal;
    private javax.swing.JLabel btnMais;
    private javax.swing.JPanel btnMaisApps;
    private javax.swing.JPanel btnOfertaespecial;
    private javax.swing.JPanel btnPrever;
    private javax.swing.JLabel btnProduto;
    private javax.swing.JPanel btnRecFidelidade;
    private javax.swing.JLabel btnRelatorio;
    private javax.swing.JPanel btnSobre;
    private javax.swing.JPanel btnTerminarSessao;
    private javax.swing.JLabel btnUsuario;
    private javax.swing.JPanel btnVerDispositivos;
    private javax.swing.JLabel btndefinicoes;
    private javax.swing.JLabel btnfuncionario;
    private javax.swing.JPanel desBtnActualizar;
    private javax.swing.JPanel desBtnApagar;
    private javax.swing.JPanel desBtnInserir;
    private javax.swing.JPanel desBtnLimpar;
    private javax.swing.JComboBox<String> desTxtEstado;
    private javax.swing.JTextField desTxtID;
    private javax.swing.JTextField desTxtIDProcessador;
    private javax.swing.JTextField desTxtProprietrario;
    private javax.swing.JPanel fBtnActualizar;
    private javax.swing.JPanel fBtnApagar;
    private javax.swing.JPanel fBtnInserir;
    private javax.swing.JPanel fBtnLimpar;
    private javax.swing.JTextField fTxtCargo;
    private javax.swing.JTextField fTxtID;
    private javax.swing.JTextField fTxtIdade;
    private javax.swing.JTextField fTxtNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JLabel lblUserTipo;
    private javax.swing.JPanel pBtnActualizar;
    private javax.swing.JPanel pBtnApagar;
    private javax.swing.JPanel pBtnInserir;
    private javax.swing.JPanel pBtnLimpar;
    private javax.swing.JTextField pTextValor;
    private javax.swing.JTextField pTxtDesc;
    private javax.swing.JTextField pTxtID;
    private javax.swing.JTextField pTxtNome;
    private javax.swing.JTextField pTxtQuant;
    private javax.swing.JPanel rBtnActualizar;
    private javax.swing.JPanel rBtnApagar;
    private javax.swing.JPanel rBtnInserir;
    private javax.swing.JPanel rBtnLimpar;
    private javax.swing.JFormattedTextField rTxtData;
    private javax.swing.JTextField rTxtEmail;
    private javax.swing.JFormattedTextField rTxtHora;
    private javax.swing.JTextField rTxtID;
    private javax.swing.JTextField rTxtMsg;
    private javax.swing.JTextField rTxtNome;
    private javax.swing.JFormattedTextField rTxtTel;
    private javax.swing.JTextField rTxtnPessoas;
    private javax.swing.JTable tbDispositivo;
    private javax.swing.JTable tbFuncionarios;
    private javax.swing.JTable tbProdutos;
    private javax.swing.JTable tbReservas;
    private javax.swing.JTable tbUsuario;
    private javax.swing.JTable tbVendas;
    private javax.swing.JLabel txtNumerodeDispositivos;
    private javax.swing.JLabel txtNumerodeFuncionarios;
    private javax.swing.JLabel txtNumerodeUsuarios;
    private javax.swing.JTextField txtPesquisarDispositivo;
    private javax.swing.JTextField txtPesquisarFuncionario;
    private javax.swing.JTextField txtPesquisarProduto;
    private javax.swing.JTextField txtPesquisarReserva;
    private javax.swing.JTextField txtPesquisarUsuario;
    private javax.swing.JTextField txtPesquisarVenda;
    private javax.swing.JPanel uBtnActualizar;
    private javax.swing.JPanel uBtnApagar;
    private javax.swing.JPanel uBtnInserir;
    private javax.swing.JPanel uBtnLimpar;
    private javax.swing.JTextField uTxtID;
    private javax.swing.JTextField uTxtNome;
    private javax.swing.JTextField uTxtSenha;
    private javax.swing.JComboBox<String> uTxtTipo;
    private javax.swing.JTextField uTxtUser;
    private javax.swing.JPanel vBtnActualizar;
    private javax.swing.JPanel vBtnApagar;
    private javax.swing.JPanel vBtnInserir;
    private javax.swing.JPanel vBtnLimpar;
    private javax.swing.JTextField vTxtCliente;
    private javax.swing.JFormattedTextField vTxtData;
    private javax.swing.JTextField vTxtID;
    private javax.swing.JTextField vTxtProdutos;
    private javax.swing.JTextField vTxtValorTotal;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imgs/icon_setup.png")));
    }
}
