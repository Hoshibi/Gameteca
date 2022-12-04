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
import javax.swing.JOptionPane;
import model.Usuario;
import view.LoginView;
import view.MenuView;


/**
 *
 * @author Pancaldi
 */
public class LoginController {
    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
    }

    public void autenticar() throws SQLException {
        
        String email = view.getTxtLogin().getText();
        String senha = view.getTxtSenha().getText();
        
        Usuario userAutenticar = new Usuario(email, senha);
        
        Connection conexao = new Conexao().getConnection();
        UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
        
        boolean existe = usuarioDao.autenticarUsuario(userAutenticar);
        
        if (existe){
            MenuView teladeMenu = new MenuView();
            teladeMenu.setVisible(existe);    
            view.dispose();
        }else{
            JOptionPane.showMessageDialog(view, "usuario ou senha invalidos");
        }
        
    }
    
    
}
