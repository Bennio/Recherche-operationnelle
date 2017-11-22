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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

//La classe ci-dessus represente le graphe avec ses attributs et ses fonctions associees
 
class Graphe
{  
	/*
	 * nombreDeSommet : Nombre de sommet du graphe
	 * graphe[][] : matrice representant le graphe et le poids des arretes associe.
	 */
    private int nombreDeSommet=0;
    private int graphe[][];
    
    //Constructeur de la classe Graphe avec 1 parametre : String nomfichier
    public Graphe(String nomfichier) throws FileNotFoundException{
    	
    	Scanner fileReader = new Scanner(new File(nomfichier)); //pour lire le fichier
    	this.nombreDeSommet=fileReader.nextInt(); //recupere le nombre de sommet dans le fichier
		graphe=new int[nombreDeSommet][nombreDeSommet]; //creation du tableau graphe de 2 dimensions nombreDeSommet*nombreDeSommet pour recevoir la matrice du graphe
    	
    	//boucle pour la lecture du fichier tant qu'il n'est pas encore lu completement et stockage des entiers lus dans le tableau graphe[][]
    	while(fileReader.hasNextInt()){
			for(int i=0;i<nombreDeSommet;i++){
    			for(int j=0;j<nombreDeSommet;j++){
    				graphe[i][j]=fileReader.nextInt();    				
    			}    			
    		}
    	}    	
    	fileReader.close();   //fin de lecture, fermeture du flux 	
    }
    
    /* Renvoie vrai si il y a un chemin de la source s au puits t dans un graphe residuel. 
     * Et aussi de remplir le tableau parent pour sauvegarder le chemin
     */ 
    
    private boolean bfs(int grapheResiduel[][], int source, int puits, int sommetPrecedents[])
    {
        
        boolean sommetVisite[] = new boolean[nombreDeSommet]; //creation d'un tableau sommetVisite[] 
        for(int i=0; i<nombreDeSommet; ++i)
            sommetVisite[i]=false; // initialisation de tous les sommets comme non visite
 
        LinkedList<Integer> fileAttente = new LinkedList<Integer>(); //creation liste chainee fileAttente
        fileAttente.add(source); //ajout de la source au file d'attente
        sommetVisite[source] = true; //initialisation de la souce a true
        sommetPrecedents[source]=-1; //consequence de tout ca
 
        //boucle de bfs
        while (fileAttente.size()!=0)
        {
            int u = fileAttente.poll();
 
            for (int v=0; v<nombreDeSommet; v++)
            {
                if (sommetVisite[v]==false && grapheResiduel[u][v] > 0)
                {
                	fileAttente.add(v);
                    sommetPrecedents[v] = u;
                    sommetVisite[v] = true;
                }
            }
        }
 
        
        return (sommetVisite[puits] == true); //renvoie true si nous avons trouve le puits en partant de la source sinon false
    }
 
    //methode fordFulkerson avec 2 parametres : source et puits
   public int fordFulkerson(int source, int puits)
    {
        int u, v;
 
        /*Creation de graphe residuel et remplissage du graphe residuel
        considerons les capacites donnees dans le graphe initial comme capacite residuel dans le graphe residuel
 
         residuelGraph[i][j] indique la capacite residuelle des arretes de i a j s'il existe une arrete
         si residuelGraph[i][j] est egal a 0, donc il n'y a pas. 
     */
        
        int residuelGraph[][] = new int[nombreDeSommet][nombreDeSommet];
 
        for (u = 0; u < nombreDeSommet; u++)
            for (v = 0; v < nombreDeSommet; v++)
                residuelGraph[u][v] = graphe[u][v];
 
        // Ce tableau est rempli avec BFS et pour stocker le chemin
        int parent[] = new int[nombreDeSommet];
 
        int flotMaximum = 0;  // initialisation du flotMaximum
 
        // Augmentation du flot maximum tant qu'il y a un chemin de la source vers le puits
        while (bfs(residuelGraph, source, puits, parent))
        {
            
        	 /* Recherche de la capacite residuelle minimale des arrets tout au long du chemin donne par BFS 
        	 * c'est a dire recherche du flot maximum a partir du chemin trouve
        	 */
            int flotChemin = Integer.MAX_VALUE; //initialisation avec le plus grand integer (entier) possible pour une bonne analyse
            for (v=puits; v!=source; v=parent[v])
            {
                u = parent[v];
                flotChemin = Math.min(flotChemin, residuelGraph[u][v]);
            }
 
            /*
             * mise a jour des capacites residuelles des arretes avec inversement des arretes tout au long du chemin
             */
            for (v=puits; v != source; v=parent[v])
            {
                u = parent[v];
                residuelGraph[u][v] -= flotChemin;
                residuelGraph[v][u] += flotChemin;
            }
 
            // flotMaximum = flotMaximum + flotChemin
            flotMaximum += flotChemin;
        }
 
        // Retourner le flot maximum
        return flotMaximum;
    }
    
   
    //methode afficherGraphe() pour afficher le graphe
    public void afficherGraphe(){    
    	System.out.println();

		for(int i=0;i<nombreDeSommet;i++){
			for(int j=0;j<nombreDeSommet;j++){
				System.out.print(graphe[i][j]);
				System.out.print(" ");
			}
			System.out.print("\n");
		}
    }
}