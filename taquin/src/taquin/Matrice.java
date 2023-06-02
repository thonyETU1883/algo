/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquin;

import java.util.Vector;

/**
 *
 * @author ASUS
 */
public class Matrice {
    int[][] Tableau;

    public Matrice(int[][] Tableau) {
        this.Tableau = Tableau;
    }

    public int[][] getTableau() {
        return Tableau;
    }

    public void setTableau(int[][] Tableau) {
        this.Tableau = Tableau;
    }
    
    public void affichageMatrice(){
        int[][] tableau=this.getTableau();
        for(int ligne=0;ligne<tableau.length;ligne++){
            for(int colonne=0;colonne<tableau[ligne].length;colonne++){
                System.out.print(tableau[ligne][colonne]+"     ");
            }
            System.out.println("");
        }
        System.out.println("-----------------------------");
    }
    
    /*
        1)prendre tous les voisins d'une matrice
                                    1 2 3
                                    8 6 0
                                    7 5 4
                            
                            1 2 0      1 2 3        1 2 3 
                            8 6 3      8 6 4        8 0 6
                            7 5 4      7 5 0        7 5 4
            *deplacement du 0 vers le haut
            *deplacement du 0 vers le bas
            *deplacement du 0 vers la gauche
            *deplacement du 0 vers la droite
    
    */
    
                //a)trouver le placement du 0
                //retourne un tableau qui contient la ligne et le colonne
                //indice 0 -> ligne
                //indice 1 -> colonne

                public int[] findVide(){
                    int[] enregistrement=new int[2];
                    int[][] tableau=this.getTableau();
                    for(int ligne=0;ligne<tableau.length;ligne++){
                        for(int colonne=0;colonne<tableau[ligne].length;colonne++){
                            if(tableau[ligne][colonne]==0){
                                enregistrement[0]=ligne;
                                enregistrement[1]=colonne;
                                break;
                            }
                        }
                    }
                    return enregistrement;
                }
                
                /*
                    b)deplacement du 0 vers le haut
                        0 peut se deplacer vers le haut si
                            ligne-1 superieur a 0
                
                    la fonction doit retourner une nouvelle matrice pour ne pas modifier la matrice original
                */
                public Matrice deplacementVideHaut(){
                    int[][] tableau=this.getTableau();                                  //matrice original
                    int[][] newTableau=new int[tableau.length][tableau.length];         //nouveau matrice (matrice carre)
                    int[] positionVide=this.findVide();                                 //position du vide
                    int ligneVide=positionVide[0];
                    int colonneVide=positionVide[1];
                    
                    //verification si le vide peut se depacer vers le haut
                    if((ligneVide-1)<0)
                        return null;
                                
                    int valeurDeplacer=tableau[ligneVide-1][colonneVide];               //valeur a echanger aver le 0
                    //remplir le nouveau matrice
                    for(int ligne=0;ligne<tableau.length;ligne++){
                        for(int colonne=0;colonne<tableau[ligne].length;colonne++){
                            if(ligne==(ligneVide-1) && colonne==colonneVide){
                                newTableau[ligne][colonne]=0;
                            }else if(ligne==ligneVide && colonne==colonneVide){
                                newTableau[ligne][colonne]=valeurDeplacer;
                            }else{
                                newTableau[ligne][colonne]=tableau[ligne][colonne];
                            }
                        }
                    }
                    Matrice matrice=new Matrice(newTableau);
                    return matrice;
                }
                
                /*
                c)deplacement du 0 vers le bas
                    0 peut etre deplacer vers le bas si
                        ligne+1 inferieur a la taille du matrice
                */
                
                public Matrice deplacementVideBas(){
                    int[][] tableau=this.getTableau();                                  //matrice original
                    int[][] newTableau=new int[tableau.length][tableau.length];         //nouveau matrice (matrice carre)
                    int[] positionVide=this.findVide();                                 //position du vide
                    int ligneVide=positionVide[0];
                    int colonneVide=positionVide[1];
                    
                    //verification si le vide peut se depacer vers le bas
                    if((ligneVide+1)>=tableau.length)
                        return null;
                                
                    int valeurDeplacer=tableau[ligneVide+1][colonneVide];               //valeur a echanger aver le 0
                    //remplir le nouveau matrice
                    for(int ligne=0;ligne<tableau.length;ligne++){
                        for(int colonne=0;colonne<tableau[ligne].length;colonne++){
                            if(ligne==(ligneVide+1) && colonne==colonneVide){
                                newTableau[ligne][colonne]=0;
                            }else if(ligne==ligneVide && colonne==colonneVide){
                                newTableau[ligne][colonne]=valeurDeplacer;
                            }else{
                                newTableau[ligne][colonne]=tableau[ligne][colonne];
                            }
                        }
                    }
                    Matrice matrice=new Matrice(newTableau);
                    return matrice;
                }
                
                /*
                d)deplacement du 0 vers la droite
                0 peut se deplacer vers la droite si 
                colonne+1 est inferieur a la taille du matrice
                */
                public Matrice deplacementVideDroite(){
                    int[][] tableau=this.getTableau();                                  //matrice original
                    int[][] newTableau=new int[tableau.length][tableau.length];         //nouveau matrice (matrice carre)
                    int[] positionVide=this.findVide();                                 //position du vide
                    int ligneVide=positionVide[0];
                    int colonneVide=positionVide[1];
                    
                    //verification si le vide peut se depacer vers la droite
                    if((colonneVide+1)>=tableau.length)
                        return null;
                                
                    int valeurDeplacer=tableau[ligneVide][colonneVide+1];               //valeur a echanger aver le 0
                    //remplir le nouveau matrice
                    for(int ligne=0;ligne<tableau.length;ligne++){
                        for(int colonne=0;colonne<tableau[ligne].length;colonne++){
                            if(ligne==ligneVide && colonne==(colonneVide+1)){
                                newTableau[ligne][colonne]=0;
                            }else if(ligne==ligneVide && colonne==colonneVide){
                                newTableau[ligne][colonne]=valeurDeplacer;
                            }else{
                                newTableau[ligne][colonne]=tableau[ligne][colonne];
                            }
                        }
                    }
                    Matrice matrice=new Matrice(newTableau);
                    return matrice;
                }
                
                /*
                c)deplacement du 0 vers la gauche
                    0 peut se deplacer vers la gauche si 
                        colonne-1 superieur a 0
                */
                public Matrice deplacementVideGauche(){
                    int[][] tableau=this.getTableau();                                  //matrice original
                    int[][] newTableau=new int[tableau.length][tableau.length];         //nouveau matrice (matrice carre)
                    int[] positionVide=this.findVide();                                 //position du vide
                    int ligneVide=positionVide[0];
                    int colonneVide=positionVide[1];
                    
                    //verification si le vide peut se depacer vers la gauche
                    if((colonneVide-1)<0)
                        return null;
                                
                    int valeurDeplacer=tableau[ligneVide][colonneVide-1];               //valeur a echanger aver le 0
                    //remplir le nouveau matrice
                    for(int ligne=0;ligne<tableau.length;ligne++){
                        for(int colonne=0;colonne<tableau[ligne].length;colonne++){
                            if(ligne==ligneVide && colonne==(colonneVide-1)){
                                newTableau[ligne][colonne]=0;
                            }else if(ligne==ligneVide && colonne==colonneVide){
                                newTableau[ligne][colonne]=valeurDeplacer;
                            }else{
                                newTableau[ligne][colonne]=tableau[ligne][colonne];
                            }
                        }
                    }
                    Matrice matrice=new Matrice(newTableau);
                    return matrice;
                }
                
    
    public Vector<Matrice> getVoisin(){
        Vector<Matrice> listeVoisin=new Vector<Matrice>();
        if(this.deplacementVideBas()!=null){
            listeVoisin.add(this.deplacementVideBas());
        }
        if(this.deplacementVideHaut()!=null){
            listeVoisin.add(this.deplacementVideHaut());
        }
        if(this.deplacementVideDroite()!=null){
            listeVoisin.add(this.deplacementVideDroite());
        }
        if(this.deplacementVideGauche()!=null){
            listeVoisin.add(this.deplacementVideGauche());
        }
        return listeVoisin;
    }
    
    
    
    /*comparaison entre deux matrice
       matrice appelant a comparer avec la matrce dans l'argument
        true si mitovy 
        false si tsy mitovy
    */
    public boolean comparaison(Matrice acomparer){
        boolean reponse=true;
        int[][] tableau=this.getTableau();
        int[][] tableauAcomparer=acomparer.getTableau();
        
        for(int ligne=0;ligne<tableau.length;ligne++){
            for(int colonne=0;colonne<tableau[ligne].length;colonne++){
                if(tableau[ligne][colonne]!=tableauAcomparer[ligne][colonne]){
                    reponse=false;
                    break;
                }
            }
        }
        
        return reponse;
    }
    
    /*
        verification si une matrice existe deja dans une vector<Matrice>
        true si existe
        false si n'existe pas
    */
    public boolean ifExistInVector(Vector<Matrice> listeMatrice){
        boolean reponse=false;
        for(int i=0;i<listeMatrice.size();i++){
            if(this.comparaison(listeMatrice.get(i))){
                reponse=true;
                break;
            }
        }
        return reponse;
    }
    
}
