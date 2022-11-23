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
import view.EditarJogoView;

/**
 *
 * @author tayna
 */
public class EditarJogoController {
    
    private EditarJogoView view;
    
    public EditarJogoController(EditarJogoView view){
        this.view = view;
    }
    
    public void editarJogo() throws SQLException{
        try{
            String nomeJogo = view.getTxtNomeJogo().getText();
            String generoJogo = view.getGenero();
            int anoLancamento = Integer.parseInt(view.getTxtAnoLancamento().getText());
            String distribuidoraJogo = view.getDistribuidora();
            String desenvolvedoraJogo = view.getDesenvolvedora();
            float progressoJogo = Float.parseFloat(view.getTxtProgresso().getText());

            Jogo jog = new Jogo(nomeJogo, generoJogo, anoLancamento, desenvolvedoraJogo, distribuidoraJogo, progressoJogo);
            //Jogo jog = new Jogo();
            
            //Connection conexao = new Conexao().getConnection();           
            //JogoDAO jogoDao = new JogoDAO(conexao);  
            JogoDAO jogoDao = new JogoDAO();
            jogoDao.editar(jog);
          
            JOptionPane.showMessageDialog(null, "Jogo atualizado com sucesso");
                       
        }catch(SQLException ex){
            Logger.getLogger(EditarJogoView.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
