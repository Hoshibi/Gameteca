/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Usuario;

/**
 *
 * @author Pancaldi
 */
public class UsuarioDAO {
    private final Connection connection;
    private PreparedStatement pstdados = null;
    
    //sql
    private static final String sqlexcluir = "DELETE FROM usuario WHERE cpf_usuario = ?";   

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }
    
    //Método Inserir    
    public void insert(Usuario usuario) throws SQLException{
  
        String sql = "insert into usuario( user_usuario, senha_usuario, nome_usuario, email_usuario, telefone_usuario, cpf_usuario) values('"+ usuario.getUsuario() +"', '"+ usuario.getSenha() +"' , '" + usuario.getNome()+"', '" + usuario.getEmail() + "' , '" + usuario.getTelefone() + "' , '" + usuario.getCpf() + "');";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
   
    }

    //Método Autenticar    
    public boolean autenticarUsuario(Usuario user) throws SQLException {
        String sql = "select * from usuario where email_usuario = '"+user.getEmail()+"' and senha_usuario = '"+user.getSenha()+"'";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        
        ResultSet resultSet = statement.getResultSet();
        
       return resultSet.next();
    }
    
    // Método Excluir
    public int excluir(String cpfTarget) {
        
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            
            pstdados = connection.prepareStatement(sqlexcluir, tipo, concorrencia);
            pstdados.setString(1, cpfTarget);
            int resposta = pstdados.executeUpdate();
            pstdados.close();
           
            return resposta;

        } catch (SQLException erro) {
            System.out.println("Erro na execução da exclusão = " + erro);
            return 0;
        }
    }   

    
}
