/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import model.Jogo;

/**
 *
 * @author Pancaldi
 */
public class JogoDAO {
    private final Connection connection;
    private PreparedStatement pstdados = null;
    public Jogo jogo;
    
    //sql
    private static final String sqlexcluir = "DELETE FROM jogo WHERE nome_jogo = ?";    
    
//    public JogoDAO(Connection connection){
//            this.connection = connection;
//    }
      public JogoDAO() throws SQLException{
          this.connection = new Conexao().getConnection();
      }
    

    // Método Inserir    
    public void insert(Jogo jogo) throws SQLException{
        
        String sql = "insert into jogo(nome_jogo, genero_jogo, ano_jogo, desenvolvedora_jogo, distribuidora_jogo, progresso_jogo) values("
                + "'" + jogo.getNomeJogo() + "', '" + jogo.getGeneroJogo() + "', '" + jogo.getAnoLancamentoJogo() + "', '" + jogo.getDesenvolvedoraJogo() + "', '" + jogo.getDistribuidoraJogo() + "', '" + jogo.getProgressoJogo() + "');" ;
    
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
    }
    
    // Método Excluir
    public int excluir(String nameTarget) {

        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            
            pstdados = connection.prepareStatement(sqlexcluir, tipo, concorrencia);
            pstdados.setString(1, nameTarget);
            int resposta = pstdados.executeUpdate();
            pstdados.close();
            
            return resposta;
            
        } catch (SQLException erro) {
            System.out.println("Erro na execução da exclusão = " + erro);
            return 0;
        }
        
    }    
    
    //Método Listar Jogos    
    public ArrayList<Jogo> listarTodosJogos() throws SQLException{
        
        
        String sql = "select * from jogo";
        ArrayList<Jogo> jogo = new ArrayList<Jogo>();
        
        try{
        
        PreparedStatement statement = connection.prepareStatement(sql);
        
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        
        resultSet = statement.executeQuery();
        
        while(resultSet.next()){
            String nomeJogo = resultSet.getString("nome_jogo");
            String generoJogo = resultSet.getString("genero_jogo");
            int anoLancamento = resultSet.getInt("ano_jogo");
            String desenvolvedora = resultSet.getString("desenvolvedora_jogo");
            String distribuidora = resultSet.getString("distribuidora_jogo");
            Float  progresso = resultSet.getFloat("progresso_jogo");
            
            //Jogo jogocomDados = new Jogo(nomeJogo, generoJogo, anoLancamento, desenvolvedora, distribuidora, progresso);
            Jogo jogocomDados = new Jogo();
            jogo.add(jogocomDados);
        }
        
        
        
    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar!");
    } 
       
    return jogo;    
        
    }
    //Método Editar jogos
    public void editar(Jogo jogo){
        String sql = "UPDATE jogo set genero_jogo=?, ano_jogo=?, desenvolvedora_jogo=?, distribuidora_jogo?, progresso_jogo=? WHERE nome_jogo=?";
        
        try{
            PreparedStatement pds = connection.prepareStatement(sql);
            
            pds.setString(1, jogo.getGeneroJogo());
            pds.setInt(2, jogo.getAnoLancamentoJogo());
            pds.setString(3, jogo.getDesenvolvedoraJogo());
            pds.setString(4, jogo.getDistribuidoraJogo());
            pds.setFloat(5, jogo.getProgressoJogo());
            pds.setString(6, jogo.getNomeJogo());
            
            pds.executeUpdate();
            pds.close();
            
        }catch(SQLException ex){
            throw new RuntimeException(ex); 
        }
    }
    //Método de Pesquisar jogos
    //Método para o botão de pesquisar na tela de EditarJogoView
    public boolean pesquisaJogo(Jogo jogo){
        String sql = "SELECT * from jogo WHERE nome_jogo=?";
        
        try{
            PreparedStatement pst = connection.prepareStatement(sql);
        
            pst.setString(1, jogo.getNomeJogo());
            ResultSet rs = pst.executeQuery(); //pesquisa e traz as informações do bd e inseri nos campos da tela de edição.
        
            if(rs.next()){
                jogo.setGeneroJogo(rs.getString("genero_jogo"));
                jogo.setAnoLancamentoJogo(rs.getInt("ano_jogo"));
                jogo.setDesenvolvedoraJogo(rs.getString("desenvolvedora_jogo"));
                jogo.setDistribuidoraJogo(rs.getString("distribuidora_jogo"));
                jogo.setProgressoJogo(rs.getFloat("progresso_jogo"));
            }
            return true;
        }
        catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }
}
