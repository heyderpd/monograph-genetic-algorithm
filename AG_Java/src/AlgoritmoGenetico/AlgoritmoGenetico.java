/*
 *  autor : Heyder Pestana Dias
 *  TCC : INTELIGÊNCIA COMPUTACIONAL : APLICAÇÕES DO ALGORITMO GENÉTICO
 *  1/2015
 */

package AlgoritmoGenetico;

import Matematica.Funcao;
import Matematica.Relogio;
import AlgoritmoGenetico.GerenciaPopulacao;

public class AlgoritmoGenetico {

  private Relogio R = new Relogio();
  private Funcao F;
  private GerenciaPopulacao P;
  private int Precisao;
  private double LimiteMin;
  private double LimiteMax;
  
  /*
   *   configuracao da primeira bateria de testes
   *
  private double chanceMutacao = 0.40;  //12
  private double chanceCrossover = 0.80;
  private int LimiteGeracoes = 10;
  private int TamanhoPopulacao = 10;
  private int FilhosPorGeracao = 7;
  
  /*
   *   configuracao da segunda bateria de testes
   *
  private double chanceMutacao = 0.40;  //12
  private double chanceCrossover = 0.80;
  private int LimiteGeracoes = 20;
  private int TamanhoPopulacao = 10;
  private int FilhosPorGeracao = 7;
  
  /*
   *   configuracao da terceira bateria de testes
   *
  private double chanceMutacao = 0.40;
  private double chanceCrossover = 0.80;
  private int LimiteGeracoes = 20;
  private int TamanhoPopulacao = 6;
  private int FilhosPorGeracao = 3;
  
  /*
   *   configuracao da quarta bateria de testes
   */
  private double chanceMutacao = 0.70;
  private double chanceCrossover = 0.70;
  private int LimiteGeracoes = 20;
  private int TamanhoPopulacao = 6;
  private int FilhosPorGeracao = 3;

  public AlgoritmoGenetico(Funcao F, double LimiteMin, double LimiteMax, int Precisao){
    this.F = F;
    this.LimiteMin = LimiteMin;
    this.LimiteMax = LimiteMax;
    this.Precisao = (int) Math.pow(10, Precisao);
  }
  
  public void Buscar(){
    System.out.println("\n[Iniciando Algoritmo Genetico]");
    
    F.Zera();
    P = new GerenciaPopulacao(this.chanceMutacao, this.chanceCrossover, this.LimiteMin, this.LimiteMax, this.Precisao, this.F);
    P.CriaPrimeiraGeracao(this.TamanhoPopulacao, this.FilhosPorGeracao);
    for (int i = 1; i < this.LimiteGeracoes; i++){
      P.proximaGeracao();
    }

    System.out.println("[Resultado]");
    System.out.println("x: "+this.P.getReal()+" f(x): "+this.P.getFitness());
    System.out.println("Iteracoes: "+F.getContador());
  }
  
  public void BuscarPorMedia(int repetir){
    System.out.println("\n[Iniciando Algoritmo Genetico]");
    R.zeraRelogio();
    
    double[] respostas = F.Resposta();
    int acerto = 0;
    double x = 0.0, res = 0.0, tmp, desvio = 3.0;
    double maiordesvio = 0.0, menordesvio = 100.0, mediadesvio, somadesvio = 0;
    double tempo, media, real = -1, fitness = -1;

    for (int j = 1; j < repetir; j++){
      
      F.Zera();      
      P = new GerenciaPopulacao(this.chanceMutacao, this.chanceCrossover, this.LimiteMin, this.LimiteMax, this.Precisao, this.F);
      P.CriaPrimeiraGeracao(this.TamanhoPopulacao, this.FilhosPorGeracao);
      for (int i = 1; i < this.LimiteGeracoes; i++){
        P.proximaGeracao();
      }
      
      tempo = F.getContador();
      x = P.getReal();
      res = P.getFitness();
      
      for(int i = 0; i < respostas.length; i++){
        tmp = x - respostas[i];
        tmp = Math.abs(tmp);
        if(tmp <= desvio){
          acerto++;
          if(menordesvio > tmp){
            menordesvio = tmp;
            
            real = x;
            fitness = res;
          }
          if(maiordesvio < tmp){
            maiordesvio = tmp;
          }
          somadesvio += tmp;
          break;
        }        
      }
    }
    
    media = acerto / ((double) repetir) * 100;
    tempo = R.getTempo() /( (double)repetir );
    mediadesvio = somadesvio / repetir;
    
    System.out.println("[Resultado]");
    System.out.println("x: "+this.P.getReal()+" f(x): "+this.P.getFitness());
    System.out.println("Iteracoes: "+F.getContador());
    System.out.println("[Resultado Médio]");
    System.out.println("Tempo total de processo: "+tempo+" (milesegundos)");
    System.out.println("Acerto: "+acerto+" de "+repetir+" = "+media+" %");
    System.out.println("Desvio aceito: "+desvio+" %");
    System.out.println("Maior desvio: "+maiordesvio+" %");
    System.out.println("Media desvio: "+mediadesvio+" %");
    System.out.println(">>> Menor desvio: "+menordesvio+" %");

  }

}