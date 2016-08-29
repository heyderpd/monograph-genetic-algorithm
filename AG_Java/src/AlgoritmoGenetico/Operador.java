/*
 *  autor : Heyder Pestana Dias
 *  TCC : INTELIGÊNCIA COMPUTACIONAL : APLICAÇÕES DO ALGORITMO GENÉTICO
 *  1/2015
 */

package AlgoritmoGenetico;

import java.util.Random;

public class Operador {
  
  private double chanceMutacao;
  private Random R;
  private boolean[] Mascara;
  
  public Operador (int cromossomoTamanho, double chanceMutacao, Random R){
    this.R = R;
    this.chanceMutacao = chanceMutacao;
    Mascara = new boolean[cromossomoTamanho];
    for(int i = 0; i < Mascara.length; i++){
      if(i % 3 == 0){
        Mascara[i] = true;
      }
    }
  }
  
  public String Cruza(String A, String B){
    String F = this.CrossoverMultPontos(A, B, 6);
    String Fn = this.TestaMutacao(F);
    return Fn;
  }
  
  public String CrossoverUmPonto(String A, String B){
    char[] Ac = A.toCharArray();
    char[] Bc = B.toCharArray();
    String F = "";
    int ponto = Mascara.length / 2;
    for(int i = 0; i < Mascara.length; i++){
      if(i <= ponto){
        F += Ac[i];
      } else {
        F += Bc[i];
      }
    }
    return F;
  }
  
  public String CrossoverMultPontos(String A, String B, int Pontos){
    char[] Ac = A.toCharArray();
    char[] Bc = B.toCharArray();
    String F = "";
    Pontos = Mascara.length / Pontos;
    for(int i = 0; i < Mascara.length; i++){
      if((i/Pontos)%2 == 0){
        F += Ac[i];
      } else {
        F += Bc[i];
      }
    }
    return F;
  }
  
  public String CrossoverPorMascara(String A, String B){
    char[] Ac = A.toCharArray();
    char[] Bc = B.toCharArray();
    String F = "";
    for(int i = 0; i < Mascara.length; i++){
      if(Mascara[i]){
        F += Ac[i];
      } else {
        F += Bc[i];
      }
    }
    return F;
  }
  
  private String TestaMutacao(String F){
    double teste = R.nextInt(1000)/1000.0;
    if(teste <= this.chanceMutacao){
      char[] Fc = F.toCharArray();
      String Fn = "";
      for(int i = 0; i < Mascara.length; i++){
        teste = R.nextInt(1000)/1000.0;
        if(teste <= this.chanceMutacao){
          if(Fc[i] == '0'){
            Fn += '1';
          } else {
            Fn += '0';
          }
        } else {
          Fn += Fc[i];
        }
      }
      return Fn;
    } else {
      return F;
    }
  }

}
