//******************************************************************//
//																	//
//		      PROJET DE RECHERCHE OPERATIONNELLE					//
//																	//
//       IMPLEMETATION DE L'ALGORITHME FORD-FULKERSON				//
//                    (FLOT MAXIMAL) 								//
//																	//
//       GROUPE: ELITE												//
//				1- BENDY Latortue - P22								//
//				2- MARTIN CECE Théa - P22							//
//				3- MICHAEL Lewis Kouamen - P22						//
//																	//
//                     												//
//																	//
//******************************************************************//

import java.util.Scanner;
import java.io.*;

public class Noeud {

	 
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
