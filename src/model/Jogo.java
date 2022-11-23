/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Pancaldi
 */
public class Jogo {
       
    int idJogo;
    String nomeJogo;
    String generoJogo;
    int anoLancamentoJogo;
    String desenvolvedoraJogo;
    String distribuidoraJogo;
    float progressoJogo;

    public Jogo(int idJogo, String nomeJogo, String generoJogo, int anoLancamentoJogo, String desenvolvedoraJogo, String distribuidoraJogo, float progressoJogo) {
        
        this.idJogo = idJogo;
        this.nomeJogo = nomeJogo;
        this.generoJogo = generoJogo;
        this.anoLancamentoJogo = anoLancamentoJogo;
        this.desenvolvedoraJogo = desenvolvedoraJogo;
        this.distribuidoraJogo = distribuidoraJogo;
        this.progressoJogo = progressoJogo;
    }
    
    public Jogo(String nomeJogo, String generoJogo, int anoLancamentoJogo, String desenvolvedoraJogo, String distribuidoraJogo, float progressoJogo) {
        
        this.nomeJogo = nomeJogo;
        this.generoJogo = generoJogo;
        this.anoLancamentoJogo = anoLancamentoJogo;
        this.desenvolvedoraJogo = desenvolvedoraJogo;
        this.distribuidoraJogo = distribuidoraJogo;
        this.progressoJogo = progressoJogo;
        
    }   

    public Jogo() {
        
    }

    public int getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public String getNomeJogo() {
        return nomeJogo;
    }

    public void setNomeJogo(String nomeJogo) {
        this.nomeJogo = nomeJogo;
    }

    public String getGeneroJogo() {
        return generoJogo;
    }

    public void setGeneroJogo(String generoJogo) {
        this.generoJogo = generoJogo;
    }

    public int getAnoLancamentoJogo() {
        return anoLancamentoJogo;
    }

    public void setAnoLancamentoJogo(int anoLancamentoJogo) {
        this.anoLancamentoJogo = anoLancamentoJogo;
    }

    public String getDesenvolvedoraJogo() {
        return desenvolvedoraJogo;
    }

    public void setDesenvolvedoraJogo(String desenvolvedoraJogo) {
        this.desenvolvedoraJogo = desenvolvedoraJogo;
    }

    public String getDistribuidoraJogo() {
        return distribuidoraJogo;
    }

    public void setDistribuidoraJogo(String distribuidoraJogo) {
        this.distribuidoraJogo = distribuidoraJogo;
    }

    public float getProgressoJogo() {
        return progressoJogo;
    }

    public void setProgressoJogo(float progressoJogo) {
        this.progressoJogo = progressoJogo;
    }
    
    
}
