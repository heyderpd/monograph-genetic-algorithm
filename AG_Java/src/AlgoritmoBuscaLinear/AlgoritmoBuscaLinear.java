/*
 *  autor : Heyder Pestana Dias
 *  TCC : INTELIGÊNCIA COMPUTACIONAL : APLICAÇÕES DO ALGORITMO GENÉTICO
 *  1/2015
 */

package AlgoritmoBuscaLinear;

import Matematica.Funcao;

public class AlgoritmoBuscaLinear {
  
  private Funcao F;
  private int Precisao;
  private double Passo;
  private double LimiteMin;
  private double LimiteMax;
  
  public AlgoritmoBuscaLinear(Funcao F, double LimiteMin, double LimiteMax, int Precisao){    
    this.F = F;
    this.LimiteMin = LimiteMin;
    this.LimiteMax = LimiteMax;
    this.Precisao = (int) Math.pow(10, Precisao);
    this.Passo = 1 / (double) this.Precisao;
  }
  
  public void Buscar(){
    System.out.println("\n[Iniciando Busca Linear]");
    F.Zera();

    double Max = this.LimiteMin;
    double x = this.LimiteMin;
    double Res, MaxRes = F.Calcula(x);
    while ( x < this.LimiteMax ){
      x = this.Passo(x);
      Res = F.Calcula(x);
      if(Res >= MaxRes){
        MaxRes = Res;
        Max = x;
      }
    }

    System.out.println("[Resultado]");
    System.out.println("x: "+Max+" f(x): "+MaxRes);
    System.out.println("Iteracoes: "+F.getContador());
  }
  
  private double Passo(double x){
    x = x + this.Passo;
    x = Math.round(x * this.Precisao);
    return x / this.Precisao;
  }

}
