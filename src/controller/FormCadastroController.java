/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.Conexao;
import DAO.UsuarioDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Usuario;
import view.FormCadastroView;

/**
 *
 * @author Pancaldi
 */
public class FormCadastroController {
    
    private FormCadastroView view;

    public FormCadastroController(FormCadastroView view) {
        this.view = view;
    }
    
    public void salvaUsuario(){
        try{
            String usuario = view.getTxtUsuario().getText();
            String senha = view.getTxtSenha().getText();
            String nome = view.getTxtNome().getText();
            String email = view.getTxtEmail().getText();
            String telTeste = view.getTxtTelefone().getText();
            String cpf = view.getTxtCPF().getText();

            
        
            //validação dos campos
            if((usuario.isEmpty()) || (senha.isEmpty()) || (nome.isEmpty()) || (email.isEmpty()) || (cpf.isEmpty()) || telTeste.isEmpty()){
                JOptionPane.showMessageDialog(null, "Os campos não podem retornar vazios");
            }
            else{
                int tel = Integer.parseInt(view.getTxtTelefone().getText());
                
                Usuario user = new Usuario(usuario, senha, nome, email, tel, cpf);
                
                Connection conexao = new Conexao().getConnection();
                UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
                usuarioDao.insert(user);

                JOptionPane.showMessageDialog(null, "Usuario salvo com sucesso");
            }
        
        }catch(SQLException ex){
            String type = ex.getSQLState();
            if( type.equals("23505")) {
                JOptionPane.showMessageDialog(null, "Já existe um usuário com esse email. \nPor favor, insira outro email para dar prosseguimento no cadastro.");
            }else{
                JOptionPane.showMessageDialog(null, "Erro: " + ex );                
                Logger.getLogger(FormCadastroView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }      
    }
}
