/*
 *  autor : Heyder Pestana Dias
 *  TCC : INTELIGÊNCIA COMPUTACIONAL : APLICAÇÕES DO ALGORITMO GENÉTICO
 *  1/2015
 */

package AlgoritmoGenetico;

import AlgoritmoGenetico.Individuo;
import AlgoritmoGenetico.Cromossomo;
import AlgoritmoGenetico.Operador;
import Matematica.Funcao;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class GerenciaPopulacao {

  private Cromossomo C;
  private Operador O;
  private int Precisao;
  private int TamanhoPopulacao;
  private int FilhosPorGeracao;
  private double chanceCrossover;

  private List<Individuo> Populacao;
  private Random R = new Random();
  
  private double Real;
  private double Fitness;
  
  public GerenciaPopulacao(double chanceMutacao, double chanceCrossover, double LimiteMin, double LimiteMax, int Precisao, Funcao F){
    long combinacoes = (long) (LimiteMax - LimiteMin) * Precisao;
    String binarioMax = Long.toBinaryString(combinacoes);
    int cromossomoTamanho = binarioMax.length();
    C = new Cromossomo(LimiteMin, LimiteMax, binarioMax, R, F);
    O = new Operador(cromossomoTamanho, chanceMutacao, R);
    this.chanceCrossover = chanceCrossover;
    this.Precisao = Precisao;
  }
  
  public double getReal(){
    return this.Real;
  }
  
  public double getFitness(){
    return this.Fitness;
  }
  
  public void CriaPrimeiraGeracao(int TamanhoPopulacao, int FilhosPorGeracao){
    this.TamanhoPopulacao = TamanhoPopulacao;
    this.FilhosPorGeracao = FilhosPorGeracao;
    this.zeraPopulacao();

    tentaGerarIndividuo( this.C.geraCromossomoMax() );
    do {
      tentaGerarIndividuo( this.C.geraCromossomoRandomico() );
    } while(Populacao.size() < this.TamanhoPopulacao);
    
    this.Fitness = this.Populacao.get(0).getFitness();
    this.Real = this.Populacao.get(0).getReal();
    this.buscaMaior();
    this.amadurecePopulacao();
    this.geraRoleta();
  }
  
  public void proximaGeracao(){
    do {
      this.selecionaPorRoletaRussa();
    } while (Populacao.size() < this.TamanhoPopulacao + this.FilhosPorGeracao);
    this.selecaoTruncada();
    
    this.buscaMaior();
    this.amadurecePopulacao();
    this.geraRoleta();
  }
  
  public void selecionaPorRoletaRussa(){
    int A = this.testaRoleta();
    if(this.Populacao.get(A).getMaduro()){  
      int B = this.testaRoleta();
      if(A != B){
        if(this.Populacao.get(B).getMaduro()){
          double teste = R.nextInt(1000)/1000.0;
          if(teste <= this.chanceCrossover)
            this.tentaGerarIndividuo( this.O.Cruza( this.Populacao.get(A).getCromossomo(), this.Populacao.get(B).getCromossomo() ) );
          return;
        }
      }
    }
  }
  
  public void selecionaECruza(){
    int A = R.nextInt(this.Populacao.size());
    if(this.Populacao.get(A).getMaduro()){  
      int B = R.nextInt(this.Populacao.size());
      if(A != B){
        if(this.Populacao.get(B).getMaduro()){
          double teste = R.nextInt(1000)/1000.0;
          if(teste <= this.chanceCrossover)
            this.tentaGerarIndividuo( this.O.Cruza( this.Populacao.get(A).getCromossomo(), this.Populacao.get(B).getCromossomo() ) );
          return;
        }
      }
    }
  }

  private void tentaGerarIndividuo(String cromossomo){
    if(this.C.seNoLimite(cromossomo)){
      for(int i = 0; i < this.Populacao.size(); i++){
        if(this.Populacao.get(i).getCromossomo().equals(cromossomo)){
          return;
        }
      }
      this.addIndividuo(cromossomo);
    }
  }
  
  private void addIndividuo(String cromossomo){
    double real = this.C.converteCromossomo(cromossomo, this.Precisao);
    Populacao.add(new Individuo(cromossomo, real, this.C.calculaFitness(real)));
  }

  private void zeraPopulacao(){
    this.Populacao = new ArrayList<Individuo>();
  }
  
  private void selecaoTruncada(){
    while(this.Populacao.size() > this.TamanhoPopulacao){
      this.removeMenor();
    }
  }
  
  private void amadurecePopulacao(){
    for(int i = 0; i < this.Populacao.size(); i++){
      this.Populacao.get(i).setMaduro();
    }  
  }
  
  private void removeMenor(){
    if(this.Populacao.size() > 0){
      double Fitness, MenorFitness = this.Populacao.get(0).getFitness();
      int menor = 0;
      for(int i = 0; i < this.Populacao.size(); i++){
        Fitness = this.Populacao.get(i).getFitness();
        if(Fitness < MenorFitness){
          Fitness = MenorFitness;
          menor = i;
        }
      }
      this.Populacao.remove(menor);
    }    
  }
  
  private void buscaMaior(){
    if(this.Populacao.size() > 0){
      double Fitness;
      for(Individuo I : this.Populacao){
        Fitness = I.getFitness();
        if(Fitness > this.Fitness){
          this.Fitness = Fitness;
          this.Real = I.getReal();
        }
      }
    }
  }
  
  private int testaRoleta(){
    int escolhido = 0, precisao = 100000;
    double teste, maior = 0, roleta = R.nextInt(precisao) / (double)precisao;
    for (int i = 0; i < this.Populacao.size(); i++){
      teste = this.Populacao.get(i).getRoleta();
      if(roleta >= teste && teste > maior){
        maior = teste;
        escolhido = i;
      }
    }
    return escolhido;
  }
  
  private void geraRoleta(){
    double Soma = 0, roleta = 0, passo = 0;
    for( Individuo I : this.Populacao ){
      Soma += I.getFitness();
    }
    for( Individuo I : this.Populacao ){
      roleta = I.getFitness() * 0.2;
      roleta = (roleta / Soma) + passo;
      I.setRoleta(roleta);
      passo = roleta;
    }
  }

}