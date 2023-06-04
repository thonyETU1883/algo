from Graph import Graph
from Ip import Ip
from Duree import Duree
import tkinter as tk
from tkinter import ttk
from threading import Thread
import networkx as nx
import matplotlib.pyplot as plt



instanceIp=Ip()
listeIp=instanceIp.getAllIp()

instanceDuree=Duree()
listeDuree=instanceDuree.getAllDuree()

g=Graph(listeIp,listeDuree)

fenetre = tk.Tk()

fenetre.title("ip")

fenetre.geometry("400x300")

####################
options=[ip.getAdresse() for ip in listeIp]
selected_option=tk.StringVar()
select=ttk.Combobox(fenetre,textvariable=selected_option,values=options)
label=tk.Label(fenetre,text="ip")
label.grid(row=1,column=1,pady=10)
select.grid(row=1,column=3,pady=10)

#input pour saisir le lien
valeur_saisie=tk.StringVar()
input_texte= tk.Entry(fenetre,textvariable=valeur_saisie)
label=tk.Label(fenetre,text="lien")
label.grid(row=2,column=1,pady=10)
input_texte.grid(row=2,column=3,pady=10)

####################





#######################
def recuperationValeur():
    plt.clf()
    adresseSelected=selected_option.get()
    lienChoisie=valeur_saisie.get()
    ipChoisie=instanceIp.rechercheIpByAdresse(adresseSelected)
    chemin=g.getChemin(ipChoisie,lienChoisie,g.getListeDuree())
    listeNoeud=g.getGraph().nodes()
    couleur={}
    for ip in listeIp:
        if ip.verificationIpListe(chemin):
            couleur[ip]={'color': "green"}
        else:
            couleur[ip]={'color': "blue"}
    pos = nx.spring_layout(g.getGraph())
    
    #changement du position des labels
    labels_pos={node: (x, y+0.05) for node, (x, y) in pos.items()}

    #affichage de l'adresse
    labels = {node: g.getGraph().nodes[node]['adresse'] for node in g.getGraph().nodes}
    nx.draw_networkx_labels(g.getGraph(),labels_pos,labels=labels,font_size=10)

    for node in g.getGraph().nodes:
        liste_de_lien = g.getGraph().nodes[node]['listeLien']
        annotation = "\n".join(liste_de_lien)
        plt.annotate(annotation, xy=(pos[node][0], pos[node][1]), xytext=(pos[node][0]+0.03, pos[node][1]+0.09),
        arrowprops=dict(facecolor='black', arrowstyle='->'), fontsize=8, bbox=dict(boxstyle='round,pad=0.1', fc='white', alpha=0.4))
        plt.draw()

    #affichage du duree
    edge_labels = nx.get_edge_attributes(g.getGraph(),'duree') 
    nx.draw_networkx_edge_labels(g.getGraph(),pos,edge_labels=edge_labels,font_size=8)

    nx.draw_networkx(g.getGraph(),pos,with_labels=True,node_color=[couleur[ip]["color"] for ip in listeIp])
    plt.pause(0.01)
#######################

#######################
def couperBranche():
    plt.clf()
    ip1Selected=instanceIp.rechercheIpByAdresse(selected_ip1.get())
    ip2Selected=instanceIp.rechercheIpByAdresse(selected_ip2.get())
    ip1Selected.couper(ip2Selected,g.getListeDuree())
    g.constructGraph()
    
#######################

#boutton
bouton=tk.Button(fenetre,text="chercher",command=recuperationValeur)
bouton.grid(row=4,column=3,pady=10)

#########################
#select pour couper une route
selected_ip1=tk.StringVar()
select_ip1=ttk.Combobox(fenetre,textvariable=selected_ip1,values=options)
select_ip1.grid(row=6,column=1,pady=10)

selected_ip2=tk.StringVar()
select_ip2=ttk.Combobox(fenetre,textvariable=selected_ip2,values=options)
select_ip2.grid(row=6,column=3,pady=10)

#bouton pour confirmer la coupure
bouton_coupure=tk.Button(fenetre,text="couper",command=couperBranche)
bouton_coupure.grid(row=6,column=4,pady=10)
#########################


fig, ax=plt.subplots()


tkinter_thread=Thread(g.constructGraph())
networkx_thread=Thread(fenetre.mainloop())


tkinter_thread.join()
networkx_thread.join()

