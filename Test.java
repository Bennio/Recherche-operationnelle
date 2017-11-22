//******************************************************************//
//																	//
//		      PROJET DE RECHERCHE OPERATIONNELLE					//
//																	//
//       IMPLEMETATION DE L'ALGORITHME FORD-FULKERSON				//
//                    (FLOT MAXIMAL) 								//
//																	//
//       AUTEURS: 													//
//				1- BIAKOTA BOMBIA Herbert Cephas - P21				//
//				2- MILORME Rubens - P21								//
//				3- ELIODOR Ednalson Guy Mirlin - P21				//
//																	//
//                     FEVRIER 2017									//
//																	//
//******************************************************************//

import java.util.Scanner;
import java.io.*;

public class Test {

	 
    public static void main (String[] args) throws java.lang.Exception, FileNotFoundException
    {
    	String nomFichier="";
    	Scanner sc =new Scanner(System.in);

    	
    	System.out.println("\n BIENVENUE DANS NOTRE PROGRAMME DE CALCUL DE FLOT MAXIMAL DANS UN GRAPHE");
    	System.out.println("CE PROGRAMME CALCULE LE FLOT MAXIMAL AVEC L'ALGORITHME DE FORD FULKERSON\n");
    	System.out.println("ENTREZ LE CHEMIN D'ACCES DE VOTRE FICHIER GRAPHE :\n");
    	System.out.print("$ ");
    	
    	nomFichier=sc.nextLine();
    	sc.close();

    	Graphe g = new Graphe(nomFichier); 
    	g.afficherGraphe();
    	System.out.println("\nLe flot maximal est " + g.fordFulkerson(0, 1)); //0 : source - 1: puits  
    }
    
}
