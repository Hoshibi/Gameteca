/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.Conexao;
import DAO.JogoDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
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
            if((nomeJogo.isEmpty()) || (generoJogo.isEmpty()) || (distribuidoraJogo.isEmpty()) || (desenvolvedoraJogo.isEmpty()) || anoLancamentoTeste.equals("    ") || progressoJogoTeste.isEmpty()){
                if(nomeJogo.isEmpty()) {
                    view.setErrorName().setText("Campo nome é obrigatório");
                }
                if(generoJogo.isEmpty()) {
                    view.setErrorGenre().setText("Campo gênero é obrigatório");
                }
                if(anoLancamentoTeste.equals("    ")) {
                    view.setErrorYear().setText("Campo ano é obrigatório");
                }
                if(progressoJogoTeste.isEmpty()) {
                    view.setErrorProgress().setText("Campo progresso é obrigatório");
                }
            }
            else{
                boolean validateTypeProgress = false, validatebreakProgress = false, validatebreakYear = false;
                int anoLancamento = Integer.parseInt(view.getTxtAnoLancamento().getText());;
                float progressoJogo = 0;
                
                view.setErrorName().setText("");
                view.setErrorGenre().setText("");
                view.setErrorYear().setText("");
                view.setErrorProgress().setText("");

                // Valida se Progresso é um número         
                try {
                    progressoJogo = Float.parseFloat(view.getTxtProgresso().getText());
                    validateTypeProgress = true;
                } catch(NumberFormatException ex) {
                    view.setErrorProgress().setText("Campo progresso deve ser número");
                    validateTypeProgress = false;
                }
                
                // Valida se Progresso é um número entre 0 a 100
                if((progressoJogo >= 0) &&  (progressoJogo < 101)){
                    validatebreakProgress = true;
                }else {
                    validatebreakProgress = false;
                    view.setErrorProgress().setText("O valor deve ser entre 0 a 100");
                }
                
                // Valida se Ano é um número até o ano atual
                Calendar cal = Calendar.getInstance();
                if(anoLancamento <= cal.get(Calendar.YEAR)){
                    validatebreakYear = true;
                }else {
                    validatebreakYear = false;
                    view.setErrorYear().setText("O ano é superior a do atual");
                }
                
                //   Requisição para o bd             
                if(validateTypeProgress == true && validatebreakProgress == true && validatebreakYear == true) {
                    Jogo jog = new Jogo(nomeJogo, generoJogo, anoLancamento, desenvolvedoraJogo, distribuidoraJogo, progressoJogo);
                    //Connection conexao = new Conexao().getConnection();           
                    JogoDAO jogoDao = new JogoDAO();            
                    jogoDao.insert(jog);

                    JOptionPane.showMessageDialog(null, "Jogo salvo com sucesso");
                }  
            }
        }catch(SQLException ex){
            Logger.getLogger(CadastroJogoView.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }        
  }

