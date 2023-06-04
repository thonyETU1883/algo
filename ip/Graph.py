import networkx as nx
import matplotlib.pyplot as plt
from Ip import Ip
from Duree import Duree
from ErrorException import ErrorException
class Graph:
    def __init__(self,listeIp,listeDuree,graph=nx.Graph(),dictionnaire={}):        #graph=nx.Graph() : creation du graph
        self.listeIp=listeIp
        self.listeDuree=listeDuree
        self.graph=graph
        self.dictionnaire=dictionnaire

    def getGraph(self):
        return self.graph
    
    def getListeIp(self):
        return self.listeIp

    def getListeDuree(self):
        return self.listeDuree

    def constructGraph(self):
        instanceIp=Ip()
        # Ajout des noeuds du graph
        for ip in self.listeIp:
            self.graph.add_node(ip.getId(),adresse=ip.getAdresse(),listeLien=ip.getListeLien())

        #ajout des liens entres les ips
        for duree in self.listeDuree:
            ip1=instanceIp.getIpById(duree.getIdIp1())
            ip2=instanceIp.getIpById(duree.getIdIp2())
            if duree.getCouper()==False:
                self.graph.add_edge(ip1.getId(),ip2.getId(),duree=duree.getDuree())
            else:
                self.graph.add_edge(ip1.getId(),ip2.getId(),duree="Couper")

        # Tracer le graphe
        pos = nx.spring_layout(self.graph)
        nx.draw(self.graph,pos,with_labels=True)

        #changement du position des labels
        labels_pos={node: (x, y+0.05) for node, (x, y) in pos.items()}

        #affichage de l'adresse
        labels = {node: self.graph.nodes[node]['adresse'] for node in self.graph.nodes}
        nx.draw_networkx_labels(self.graph,labels_pos,labels=labels,font_size=10)

        for node in self.graph.nodes:
            liste_de_lien = self.graph.nodes[node]['listeLien']
            annotation = "\n".join(liste_de_lien)
            plt.annotate(annotation, xy=(pos[node][0], pos[node][1]), xytext=(pos[node][0]+0.03, pos[node][1]+0.09),
                    arrowprops=dict(facecolor='black', arrowstyle='->'), fontsize=8, bbox=dict(boxstyle='round,pad=0.1', fc='white', alpha=0.4))
            plt.draw()


        #affichage du duree
        edge_labels = nx.get_edge_attributes(self.graph,'duree') 
        nx.draw_networkx_edge_labels(self.graph,pos,edge_labels=edge_labels,font_size=8)
        plt.show()



    def constructChemin(self,ipDebut,lienChercher,listeDuree):
        try:
            ipDebut.setDistance(0)
            self.dictionnaire[ipDebut]=None
            colorer=[]      #liste pour mettre les ips dejas traites
            nonColorer=[]
            nonColorer.append(ipDebut)
            ipDistanceCour=nonColorer[0]
            while ipDistanceCour.verificationLien(lienChercher)==False:
                #verification si non colorer est vide 
                if not nonColorer:
                    raise ErrorException("pas de chemin possible")

                #prendre la distance la plus court
                ipDistanceCour=nonColorer[0]
                for ip in nonColorer:
                    if ip.getDistance()<ipDistanceCour.getDistance():
                        ipDistanceCour=ip
                colorer.append(ipDistanceCour)

                #ajout de ces voisins dans le noncolorer
                listeVoisin=ipDistanceCour.getVoisin()
                for voisin in listeVoisin:
                    if voisin.verificationIpListe(colorer)==False and voisin.verificationIpListe(nonColorer)==False and ipDistanceCour.verificationIpCouper(voisin,self.listeDuree)==False:
                        print(voisin.getId())
                        nonColorer.append(voisin)
                
                #changer la distances des autres ip par rapport a l'ip qui a la distance le plus court
                for ip in nonColorer:
                    #duree+distance
                    d=ip.getDureeIp(ipDistanceCour,listeDuree)+ipDistanceCour.getDistance()
                    if ip.verificationIpListe(colorer)==False and d<ip.getDistance():
                        self.dictionnaire[ip]=ipDistanceCour
                        ip.setDistance(d)
                
                #suppresion du ipDistance parce qu'il est deja traite
                nonColorer.remove(ipDistanceCour)

            #retablir la distance du debut pour une nouvelle recherche
            ipDebut.setDistance(float('inf'))
            return ipDistanceCour
        except ErrorException as e:
            print(e.getMessage())

    #recherche du chemin a l'aide du dictionnaire
    def getChemin(self,ipDebut,lienChercher,listeDuree):
        ipTrouver=self.constructChemin(ipDebut,lienChercher,listeDuree)        #ip qui contient le lien
        listeIp=[]
        if(ipTrouver!=None):        #si pas de chemin possible
            ip=ipTrouver
            listeIp.append(ipTrouver)
            while self.dictionnaire[ip]!=None:
                ip=self.dictionnaire[ip]
                listeIp.append(ip)
            return list(reversed(listeIp))
        return listeIp              #renvoie d'une liste vide
        
