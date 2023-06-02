/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquin;


import affichage.Fenetre;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author ASUS
 */
public class Taquin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[][] tableau=new int[3][3];
        tableau[0][0]=1;     tableau[0][1]=2;     tableau[0][2]=3;
        tableau[1][0]=4;     tableau[1][1]=5;     tableau[1][2]=6;
        tableau[2][0]=7;     tableau[2][1]=8;     tableau[2][2]=0;
        
        Matrice depart=new Matrice(tableau);
        
        int[][] tableau2=new int[3][3];
        tableau2[0][0]=1;     tableau2[0][1]=2;     tableau2[0][2]=3;
        tableau2[1][0]=4;     tableau2[1][1]=5;     tableau2[1][2]=6;
        tableau2[2][0]=7;     tableau2[2][1]=8;     tableau2[2][2]=0;
        
        Matrice but=new Matrice(tableau2);
       
        Graph g=new Graph(depart,but);

        /*Vector<Matrice> chemin=g.rechercheChemin();
        for(int i=0;i<chemin.size();i++){
            chemin.get(i).affichageMatrice();
        }*/
        Fenetre fenetre=new Fenetre(g);
        fenetre.setVisible(true);
    }
    
}
