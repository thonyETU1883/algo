"""

    IP1 : 256.58.204.46          lien : www.google.com , ww.canvas.com
    IP2 : 31.13.92.36            lien : www.facebook.com , www.twitter.com
    IP3 : 216.58.204.46          lien : ww.youtube.com  , www.cartoon.com
    IP4 : 104.244.42.193         lien : www.twitter.com , www.alibaba.com
    IP5 : 176.32.103.205         lien : www.amazon.com  , www.kfc.com


    Graph:

                    IP2
           (2) /          \(3)          (5)
    IP1                         IP4   -----  IP5
           (1) \           /(6)
                    IP3

    1)mettre  les donnees dans une fichier txt
        pour les noms d'IP,IP et lien (nom de fichier donne.txt)
            1:256.58.204.46?www.google.com,ww.canvas.com
        pour les durees entres les IP  (nom de fichier duree.txt)
            1,2,2       (IP1,IP2,2s)

    2)recuperation des donnees dans chaque fichier et le mettre dans une classe
        les classes:
            Ip :  id                Duree: idIp1 
                  adresse                  idIp2
                  listeLien                duree

        fonction:
            *getAllId : recuperer tous les donnes dans le fichier donnee.txt
                     et les mettre dans une tableau de classe Ip
            *getAllDuree : recuperer tous les donnees dans le fichier duree.txt
                    et les mettre dans une tabeleau de classe Duree



    principe de l'algorithme de glouton
        **cherche le chemin le plus court aller de IP1 vers IP5
        **atao 0 ny distance an ' ilay volohany
        **predecesseur ilay entre parenthese ao amin'ny deja traite   

dejaTraite              IP1         IP2         IP3         IP4         IP5
                        0           inf         inf         inf         inf
IP1(0)(null)                        2           1           inf         inf
IP3(1)(IP1)                         2(IP1)                  7           inf
IP2(2)(IP1)                                                 5           inf
IP4(5)(IP2)                                                             10
IP5(10)(IP4)

        remonterna izany ny predecesseur mba azoana ny chemin
    IP5->IP4->IP2->IP1


    3)construction de graph
        class : Graph           attribut : graph


    4)construction d'chemin (argument: IpDebut , lienChercher)
            a)ajouter un attribut appeler distance dans la classe IP 
                cette distance sera par defaut infinie (inf) "float(inf) en python"

            b)mettre la distance de debut en 0 (depart)
            c)creer un tableau de ip deja traiter "colorer=[]"
            d)creer un hashMap<voisin,predecesseur> (c'est la qu'on va mettre le chemin)
            c)mettre dans l'hashMap<ipDebut,null>
            e) 
                ipDistanceCour=IpDebut
                hashmap<ipDistanceCour,null>
                tantQue(ipDistanceCour ne contient pas le lienChercher):
                  prendre l'ip avec la distance le plus court 
                  boucle(ip in listeIP)
                    si(ip n'est pas dans colorer et si ipDistanceCour.distance > ip.distance)
                        ipDistanceCour=ip
                    colorer.ajouter(ipDistanceCour)
                  #changer la distance de chaque ip
                  boucle(ip in listeIp)
                    d=ip.getDistanceIp(ipDistanceCour)+ipDistanceCour.distance
                    si(ip n'est pas dans colorer et d<ip.distance)
                        hashmap<ip,ipDistanceCour>
                        ip.setDistance(d)
                return hashmap

    5)couper un chemin :
        ajouter dans la classe duree un attribut afatarana we couper sa tsia
"""     