/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author ASUS
 */
public class Graph {
    Matrice Depart;
    Matrice But;
    HashMap<Matrice,Matrice> Graph;

    public Graph(Matrice Depart, Matrice But) {
        this.Depart = Depart;
        this.But = But;
        this.Graph=new HashMap<Matrice,Matrice>();
    }

    public Matrice getDepart() {
        return Depart;
    }

    public void setDepart(Matrice Depart) {
        this.Depart = Depart;
    }

    public Matrice getBut() {
        return But;
    }

    public void setBut(Matrice But) {
        this.But = But;
    }

    public HashMap<Matrice, Matrice> getGraph() {
        return Graph;
    }

    public void setGraph(HashMap<Matrice, Matrice> Graph) {
        this.Graph = Graph;
    }
    
    /*
        2)construction du graph 
           utiliser hashmap<voisin,matrice>
            *predecesseur du matrice depart est null
                                    1 2 3
                                    8 6 0
                                    7 5 4
                            
                            1 2 0      1 2 3        1 2 3 
                            8 6 3      8 6 4        8 0 6
                            7 5 4      7 5 0        7 5 4

                    1 0 2           1 2 3       1 0 3      1 2 3       1 2 3        
                    8 6 3           8 6 4       8 2 6      8 5 6       0 8 6
                    7 5 4           7 0 5       7 5 4      7 0 4       7 5 4

            1 6 2       0 1 2       1 2 3        
            8 0 3       8 6 3       8 0 4 
            7 5 4       7 5 4       7 6 5
    
    
            *mettre dans un tableau les matruces a traiter
            
            nonColorer=[1 2 3]
                        8 6 0
                        7 5 4
            prendre tous las voisin du premier element de NonColorer
            
            voisin=[1 2 0   1 2 3   1 2 3]
                    8 6 3   8 6 4   8 0 6
                    7 5 4   7 5 0   7 5 4
    
            ajouter dans le hashmap les voisins avec predecesseur le premier element de NonColorer
            <voisin,predecesseur>=<1 2 0 , 1 2 3> /// <1 2 3 , 1 2 3> //// <1 2 3 , 1 2 3>
                                   8 6 3   8 6 0       8 6 4   8 6 0        8 0 6   8 6 0
                                   7 5 4   7 5 4       7 5 0   7 5 4        7 5 4   7 5 4
    
            supprimer le predecesseur dans nonColorer et ajouter dans le tableau colorer(tableau pour les matrices dejas colorer
            NonColorer=[1 2 0   1 2 3   1 2 3]
                        8 6 3   8 6 4   8 0 6
                        7 5 4   7 5 0   7 5 4      
            
            colorer=[1 2 3]
                     8 6 0
                     7 5 4
    
            avant d'ajouter dans l'hashmap verifier si le le matrice est deja traite cad il existe deja dans colorer
            repeter en prenant l'indice 0 dans NonColorer
            fin si on trouve la matrice but ou si nonColorer est vide c'a dire qu'il n'y a pas de solution 
    */ 
    
    
    public void constructionGraph(){
       HashMap<Matrice,Matrice> graph=this.getGraph();
       //ajout du premier element
       graph.put(this.getDepart(),null);
       
       Vector<Matrice> nonColorer=new Vector<Matrice>();        //Matrice non traiter
       nonColorer.add(this.getDepart());
       
       Vector<Matrice> colorer=new Vector<Matrice>();           //matrice colorer
       
       while(nonColorer.size()!=0 && this.getBut().ifExistInVector(nonColorer)==false){
           Vector<Matrice> listeVoisin=nonColorer.get(0).getVoisin();
           for(int i=0;i<listeVoisin.size();i++){
               if(listeVoisin.get(i).ifExistInVector(colorer)==false && this.getBut().comparaison(listeVoisin.get(i))==false){
                   listeVoisin.get(i).affichageMatrice();
                   graph.put(listeVoisin.get(i), nonColorer.get(0));
                    nonColorer.add(listeVoisin.get(i));
               }
               //si on a trouver l'etat but
               //fin du graph 
               if(this.getBut().comparaison(listeVoisin.get(i))){
                   System.out.println("but");
                   this.getBut().affichageMatrice();
                   graph.put(this.getBut(), nonColorer.get(0));        //pour conserver l'adresse de but pour le retrouver dans l'hashmap
                    nonColorer.add(this.getBut());
                   break;
               }
           }
           colorer.add(nonColorer.get(0));
           nonColorer.remove(0);
       }
    }
    
    /*
       3)recherche chemin
        matrice1=<but,predecesseur>
        matruce2=<matrice1,predesseur>
        ....
    */
    
    public Vector<Matrice> rechercheChemin(){
        Vector<Matrice> chemin=new Vector<Matrice>();
        this.constructionGraph();
        HashMap<Matrice,Matrice> graph=this.getGraph();
        Matrice matrice=this.getBut();
        chemin.add(matrice);
        while(graph.get(matrice)!=null){
            chemin.add(graph.get(matrice));
            matrice=graph.get(matrice);
        }
        Collections.reverse(chemin);        //inverser l'ordre dans le vecteur
        return chemin;
    }
    
}
