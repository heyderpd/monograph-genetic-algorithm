/*
 *  autor : Heyder Pestana Dias
 *  TCC : INTELIGÊNCIA COMPUTACIONAL : APLICAÇÕES DO ALGORITMO GENÉTICO
 *  1/2015
 */

package AlgoritmoGenetico;

public class Individuo {
  
  private String Cromossomo;
  private double Real, Fitness, Roleta = 0;
  private boolean Maduro = false;
  
  public Individuo(String Cromossomo, double Real, double Fitness){
    this.Cromossomo = Cromossomo;
    this.Real = Real;
    this.Fitness = Fitness;
  }
  
  public String getCromossomo(){
    return Cromossomo;
  }
  
  public double getFitness(){
    return Fitness;
  }
  
  public double getReal(){
    return Real;
  }
  
  public boolean getMaduro(){
    return this.Maduro;
  }
  
  public void setMaduro(){
    this.Maduro = true;
  }
  
  public double getRoleta(){
    return this.Roleta;
  }
  
  public void setRoleta(double Roleta){
    this.Roleta = Roleta;
  }

}
