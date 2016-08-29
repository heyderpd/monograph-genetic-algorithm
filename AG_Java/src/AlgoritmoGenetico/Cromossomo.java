/*
 *  autor : Heyder Pestana Dias
 *  TCC : INTELIGÊNCIA COMPUTACIONAL : APLICAÇÕES DO ALGORITMO GENÉTICO
 *  1/2015
 */

package AlgoritmoGenetico;

import Matematica.Funcao;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Cromossomo {
  
  private double LimiteMin;
  private double LimiteMax;
  private long combinacaoLimite;
  private int cromossomoTamanho;
  
  private Random R;
  private Funcao F;
  
  public Cromossomo(double LimiteMin, double LimiteMax, String binarioMax, Random R, Funcao F){    
    this.cromossomoTamanho = binarioMax.length();
    this.combinacaoLimite = Long.parseLong(binarioMax, 2);
    
    this.F = F;
    this.R = R;
    this.LimiteMin = LimiteMin;
    this.LimiteMax = LimiteMax;
  }

  public boolean seNoLimite(String cromossomo){
    int L = cromossomo.length();
    if(cromossomo.length() <= this.cromossomoTamanho){
      long valor = Long.parseLong(cromossomo, 2);
      if(valor <= this.combinacaoLimite){
        return true;
      }
    }
    return false;
  }
  
  public String geraCromossomoRandomico(){
    double rand = R.nextGaussian();
    if(rand > 0){
      rand = R.nextGaussian() / rand;
    } else {
      rand = R.nextGaussian() - R.nextGaussian();
    }
    if(rand < 0)
      rand = -rand;
    rand = rand % 1;
    double escolhido = rand * rand;
    long cromossomoLong = (long)(this.combinacaoLimite * escolhido);
    try {
      int tempo = 1000;
      TimeUnit.NANOSECONDS.sleep(tempo + R.nextInt(tempo));
    } catch (InterruptedException e) {}
    return this.geraCromossomo(cromossomoLong);
  }
  
  public String geraCromossomoMax(){
    return this.geraCromossomo(this.combinacaoLimite);
  }
  
  public String geraCromossomo(Long cromossomoLong){
    String cromossomo = Long.toBinaryString(cromossomoLong);
    while(cromossomo.length() < this.cromossomoTamanho){
      cromossomo = "0" + cromossomo;
    }
    return cromossomo; 
  }
  
  public double converteCromossomo(String cromossomo, int Precisao){
    long valor = Long.parseLong(cromossomo, 2);
    double real = ((valor / (double)this.combinacaoLimite) * (this.LimiteMax - this.LimiteMin)) + this.LimiteMin;
    return ((int)(real * Precisao))/((double)Precisao);
  }
  
  public double calculaFitness(double real){
    return F.Calcula(real);
  }

}
