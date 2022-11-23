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
import model.Jogo;
import view.ExcluirJogoView;

/**
 *
 * @author Pancaldi
 */
public class ExcluirJogoController {
   
    private ExcluirJogoView view;
    
    public ExcluirJogoController(ExcluirJogoView view){
        this.view = view;
    }
    
    public void excluirJogo() throws SQLException{
        
        String nomeJogo = view.getTxtNomeJogo().getText();
        
        try {
            //Connection conexao = new Conexao().getConnection();
            JogoDAO jogoDao = new JogoDAO();
            
            if(jogoDao.excluir(nomeJogo) == 1){
                JOptionPane.showMessageDialog(null,"Jogo excluído com sucesso!" );
                view.getTxtNomeJogo().setText("");
            }else{
                JOptionPane.showMessageDialog(null,"Esse jogo não existe" );
            }
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir esse jogo! ");
            Logger.getLogger(ExcluirJogoView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
  }

