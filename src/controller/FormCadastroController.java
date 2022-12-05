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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Pancaldi
 */
public class FormCadastroController {
    
    private FormCadastroView view;

    public FormCadastroController(FormCadastroView view) {
        this.view = view;
    }
    
    public static boolean isValidEmailAddressRegex(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }
    
    public void salvaUsuario(){
        try{
            String usuario = view.getTxtUsuario().getText();
            String senha = view.getTxtSenha().getText();
            String nome = view.getTxtNome().getText();
            String email = view.getTxtEmail().getText();
            String tel = view.getTxtTelefone().getText();
            String cpf = view.getTxtCPF().getText();
            
            view.setErrorName().setText("");
            view.setErrorEmail().setText("");
            view.setErrorUser().setText("");
            view.setErrorPassword().setText("");
            view.setErrorCPF().setText("");
            view.setErrorPhone().setText("");

            //validação dos campos
            if((usuario.isEmpty()) || (senha.isEmpty()) || (nome.isEmpty()) || (email.isEmpty()) || (tel.equals("(  )      -    ")) || (cpf.equals("   .   .   -  ")) ){
                if(nome.isEmpty()) {
                    view.setErrorName().setText("Campo nome é obrigatório");
                }
                if(email.isEmpty()) {
                    view.setErrorEmail().setText("Campo email é obrigatório");
                }
                if(usuario.isEmpty()) {
                    view.setErrorUser().setText("Campo usuário é obrigatório");
                }
                if(senha.isEmpty()) {
                    view.setErrorPassword().setText("Campo senha é obrigatório");
                }
                if(cpf.equals("   .   .   -  ")) {
                    view.setErrorCPF().setText("Campo CPF é obrigatório");
                }
                if(tel.equals("(  )      -    ")) {
                    view.setErrorPhone().setText("Campo telefone é obrigatório");
                }
            } 
            else{
                boolean resultValidation = isValidEmailAddressRegex(email);

                if(resultValidation == true) {
                    view.setErrorName().setText("");
                    view.setErrorEmail().setText("");
                    view.setErrorUser().setText("");
                    view.setErrorPassword().setText("");
                    view.setErrorCPF().setText("");
                    view.setErrorPhone().setText("");
                    
                    Usuario user = new Usuario(usuario, senha, nome, email, tel, cpf);

                    Connection conexao = new Conexao().getConnection();
                    UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
                    usuarioDao.insert(user);

                    JOptionPane.showMessageDialog(null, "Usuario salvo com sucesso");
                }else {
                    view.setErrorName().setText("");
                    view.setErrorUser().setText("");
                    view.setErrorPassword().setText("");
                    view.setErrorCPF().setText("");
                    view.setErrorPhone().setText("");
                    
                    view.setErrorEmail().setText("Formato do e-mail inválido");
                }
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
