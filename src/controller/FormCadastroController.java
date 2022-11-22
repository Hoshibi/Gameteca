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
            int tel = Integer.parseInt(view.getTxtTelefone().getText());
            String cpf = view.getTxtCPF().getText();

            Usuario user = new Usuario(usuario, senha, nome, email, tel, cpf);
        
            //validação dos campos
            if((usuario.isEmpty()) || (senha.isEmpty()) || (nome.isEmpty()) || (email.isEmpty()) || (cpf.isEmpty())){
                JOptionPane.showMessageDialog(null, "Os campos não podem retornar vazios");
            }
            else{
                Connection conexao = new Conexao().getConnection();
                UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
                usuarioDao.insert(user);

                JOptionPane.showMessageDialog(null, "Usuario salvo com sucesso");
            }
        
        }catch(SQLException ex){
            Logger.getLogger(FormCadastroView.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
}
