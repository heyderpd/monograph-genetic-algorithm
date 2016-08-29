/*
 *  autor : Heyder Pestana Dias
 *  TCC : INTELIGÊNCIA COMPUTACIONAL : APLICAÇÕES DO ALGORITMO GENÉTICO
 *  1/2015
 */

package Matematica;

public class Relogio {
  
  private long Zero, Final, Tempo;
  
  public void zeraRelogio(){
    this.Zero = this.getTempoSistema();
  }
  
  public double getTempo(){
    this.paraRelogio();
    this.Tempo = (this.Final - this.Zero) / 10000;  //1000000
    return this.Tempo / 100.0;
  }
  
  public void printTempo(){
    System.out.println("Tempo total de processo: "+this.getTempo()+" (milesegundos)");
  }
  
  public float getTempoNaoPara(){
    return this.Tempo;
  }
  
  private void paraRelogio(){
    this.Final = this.getTempoSistema();
  }
  
  private long getTempoSistema(){
    long mstime = System.nanoTime();
    return mstime;
  }

}
