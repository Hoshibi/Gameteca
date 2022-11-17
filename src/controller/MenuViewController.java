/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.Conexao;
import DAO.JogoDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.ExcluirJogoView;
import view.FormCadastroView;
import view.MenuView;

/**
 *
 * @author Pancaldi
 */
public class MenuViewController {
    
    private MenuView view;
    
    public MenuViewController(MenuView view){
        this.view = view;
    }
    
    public void lista(){
        
        try {
            Connection conexao = new Conexao().getConnection();
            JogoDAO jogoDao = new JogoDAO(conexao);
            jogoDao.listarTodosJogos();
            
            JOptionPane.showMessageDialog(null, "Jogos listados com sucesso");
        
        
        } catch (SQLException ex) {
            Logger.getLogger(MenuView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
    

