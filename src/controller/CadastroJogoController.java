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
import view.CadastroJogoView;

/**
 *
 * @author Pancaldi
 */
public class CadastroJogoController {
    
    private CadastroJogoView view;
    
    public CadastroJogoController(CadastroJogoView view){
        this.view = view;
    }
    
    public void salvarJogo() throws SQLException{
        try{
            String nomeJogo = view.getTxtNomeJogo().getText();
            String generoJogo = view.getGenero();
            String anoLancamentoTeste = view.getTxtAnoLancamento().getText();
            String distribuidoraJogo = view.getDistribuidora();
            String desenvolvedoraJogo = view.getDesenvolvedora();
            String progressoJogoTeste = view.getTxtProgresso().getText();
            
            //validação dos campos
            if((nomeJogo.isEmpty()) || (generoJogo.isEmpty()) || (distribuidoraJogo.isEmpty()) || (desenvolvedoraJogo.isEmpty()) || anoLancamentoTeste.isEmpty() || progressoJogoTeste.isEmpty()){
                JOptionPane.showMessageDialog(null, "Há campos vazios, é necessário o preenchimento de todos");
            }
            else{
                int anoLancamento = Integer.parseInt(view.getTxtAnoLancamento().getText());
                float progressoJogo = Float.parseFloat(view.getTxtProgresso().getText());
                
                Jogo jog = new Jogo(nomeJogo, generoJogo, anoLancamento, desenvolvedoraJogo, distribuidoraJogo, progressoJogo);
                //Connection conexao = new Conexao().getConnection();           
                JogoDAO jogoDao = new JogoDAO();            
                jogoDao.insert(jog);
          
            JOptionPane.showMessageDialog(null, "Jogo salvo com sucesso");
            }
            
        }catch(SQLException ex){
            Logger.getLogger(CadastroJogoView.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }        
  }

