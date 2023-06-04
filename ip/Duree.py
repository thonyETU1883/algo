class Duree:
    def __init__(self,idIp1=None,idIp2=None,duree=None,couper=False):
        self.idIp1=idIp1
        self.idIp2=idIp2
        self.duree=duree
        self.couper=couper

    def getIdIp1(self):
        return self.idIp1

    def getIdIp2(self):
        return self.idIp2

    def getDuree(self):
        return self.duree

    def getCouper(self):
        return self.couper
    
    def setCouper(self,c):
        self.couper=c

    """
        1,2,2
        *split par ','
            ***indice 0 -> idIp1
            ***indice 1 -> idIp2
            ***indice 2 -> duree
    """
    def getAllDuree(self):
        fichier=open('duree.txt','r')
        tabDuree=[]
        for ligne in fichier:
            splitVirgule=ligne.split(",")
            idIp1=splitVirgule[0]
            idIp2=splitVirgule[1]
            duree=splitVirgule[2]
            #instance de duree
            instance=Duree(idIp1,idIp2,duree)
            #ajout dans le tableau d'objet
            tabDuree.append(instance)
        return tabDuree