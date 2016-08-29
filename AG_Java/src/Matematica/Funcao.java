/*
 *  autor : Heyder Pestana Dias
 *  TCC : INTELIGÊNCIA COMPUTACIONAL : APLICAÇÕES DO ALGORITMO GENÉTICO
 *  1/2015
 */

package Matematica;

public class Funcao {
  
  private int Caso;
  private int Contador = 0;
  
  double[] resposta1 = { 100.0 };
  double[] resposta2 = { 6.28319, 31.41593, 56.54867, 81.68141 };
  double[] resposta3 = { 81.87667 };
  
  public Funcao(String funcao){
    switch(funcao){
    default:
    case "PrimeiroCaso":
      this.Caso = 1;
      break;
    case "SeguntoCaso":
      this.Caso = 2;
      break;
    case "TerceiroCaso":
      this.Caso = 3;
      break;      
    }
    
  }
  
  public double Calcula(double x){
    switch(this.Caso){
      default:
      case 1:
        return this.PrimeiroCaso(x);
      case 2:
        return this.SeguntoCaso(x);
      case 3:
        return this.TerceiroCaso(x);      
    }
  }
  
  public double[] Resposta(){
    switch(this.Caso){
      default:
      case 1:
        return resposta1;
      case 2:
        return resposta2;
      case 3:
        return resposta3;      
    }
  }
  
  // http://rechneronline.de/function-graphs/

  /* funcao: (x/8)
   * max: f( 100 ) = 12.5
   */
  private double PrimeiroCaso(double x){
    this.addContador();
    return x/8.0;
  }

  /* funcao: sin(x/4)*10
   * max:~ f( 81.5 ) = 9.99
   */
  private double SeguntoCaso(double x){
    this.addContador();
    return Math.sin(x/4.0)*10;
  }

  /* funcao: sin(x/4)*(x/5)
   * max: f( 81.5 ) = 16.283
   */
  private double TerceiroCaso(double x){
    this.addContador();
    return Math.sin(x/4.0)*(x/5.0);
  }
  
  public void Zera(){
    this.Contador = 0;
  }
  
  public int getContador(){
    return this.Contador;
  }
  
  private void addContador(){
    this.Contador++;
  }

}