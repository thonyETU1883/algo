/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package affichage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import taquin.Graph;
import taquin.Matrice;

/**
 *
 * @author ASUS
 */
public class BouttonListener implements MouseListener{
    
    int Ligne;
    int Colonne;
    Graph Graph;
    JButton[][] ListeBouton;

    public BouttonListener(int Ligne, int Colonne, Graph graph,JButton[][] ListeBouton) {
        this.Ligne = Ligne;
        this.Colonne = Colonne;
        this.Graph = graph;
        this.ListeBouton=ListeBouton;
    }
   
    public boolean canDeplaceHaut(){
        Matrice depart=Graph.getDepart();
        int[] positionVide=depart.findVide();
        int ligneVide=positionVide[0];
        int colonneVide=positionVide[1];
        if((Ligne-1)==ligneVide && Colonne==colonneVide){
            return true;
        }
        return false;
    }
    
    public boolean canDeplaceBas(){
        Matrice depart=Graph.getDepart();
        int[] positionVide=depart.findVide();
        int ligneVide=positionVide[0];
        int colonneVide=positionVide[1];
        if((Ligne+1)==ligneVide && Colonne==colonneVide){
            return true;
        }
        return false;
    }
    
    public boolean canDeplaceDroite(){
        Matrice depart=Graph.getDepart();
        int[] positionVide=depart.findVide();
        int ligneVide=positionVide[0];
        int colonneVide=positionVide[1];
        if((Colonne+1)==colonneVide && Ligne==ligneVide){
            return true;
        }
        return false;
    }
    
    public boolean canDeplaceGauche(){
        Matrice depart=Graph.getDepart();
        int[] positionVide=depart.findVide();
        int ligneVide=positionVide[0];
        int colonneVide=positionVide[1];
        if((Colonne-1)==colonneVide && Ligne==ligneVide){
            return true;
        }
        return false;
    }
   
    @Override
    public void mouseClicked(MouseEvent e) {
        Matrice depart=Graph.getDepart();
        int[] positionVide=depart.findVide();
        int ligneVide=positionVide[0];
        int colonneVide=positionVide[1];
        int[][] tableau=depart.getTableau();
        int valeurEchange=tableau[Ligne][Colonne];
         if(this.canDeplaceHaut() || this.canDeplaceBas() || this.canDeplaceDroite() || this.canDeplaceGauche()){
            ListeBouton[Ligne][Colonne].setText("");
            ListeBouton[ligneVide][colonneVide].setText(String.valueOf(valeurEchange));
            tableau[Ligne][Colonne]=0;
            tableau[ligneVide][colonneVide]=valeurEchange;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
