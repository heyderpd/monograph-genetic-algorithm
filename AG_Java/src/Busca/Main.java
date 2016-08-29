/*
 *  autor : Heyder Pestana Dias
 *  TCC : INTELIGÊNCIA COMPUTACIONAL : APLICAÇÕES DO ALGORITMO GENÉTICO
 *  1/2015
 */

package Busca;

import AlgoritmoBuscaLinear.AlgoritmoBuscaLinear;
import AlgoritmoGenetico.AlgoritmoGenetico;
import Matematica.Funcao;
import Matematica.Relogio;

public class Main {

  public static void main(String[] args) {
    
    System.out.println("Iniciando parametros");
    
    Relogio R = new Relogio();
    double LimiteMin = 0.0;
    double LimiteMax = 100.0;
    int Precisao = 5;
    
    //Funcao F = new Funcao("PrimeiroCaso");
    //Funcao F = new Funcao("SeguntoCaso");
    Funcao F = new Funcao("TerceiroCaso");
    
    System.out.println("Executando busca...");
    
    R.zeraRelogio();
    AlgoritmoBuscaLinear BL = new AlgoritmoBuscaLinear(F, LimiteMin, LimiteMax, Precisao);
    BL.Buscar();
    R.printTempo();

    AlgoritmoGenetico AG = new AlgoritmoGenetico(F, LimiteMin, LimiteMax, Precisao);
    AG.BuscarPorMedia(500);  //AG.Buscar();
    
    System.out.println("\nFim do processo");
    
  }

}

/*
Na F1 com Precisao 3 ambos tem o mesmo tempo (chega perto nao é igual) na precisao 2 PG perde

*/