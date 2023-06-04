from Duree import Duree
class Ip:
                        #id: String     adresse:String  listelien: liste 
    def __init__(self,id=None,adresse=None,listeLien=None,distance=float('inf')):
        self.id=id
        self.adresse=adresse
        self.listeLien=listeLien
        self.distance=distance

    def getId(self):
        return self.id

    def getAdresse(self):
        return self.adresse
    
    def getListeLien(self):
        return self.listeLien

    def getDistance(self):
        return self.distance

    def setDistance(self,d):
        self.distance=d

    """
        ligne= 1:256.58.204.46?www.google.com,ww.canvas.com  
        *faire un split de ':' sur ligne pour avoir l'id
        **faire un split de '?' pour avoir l'adresse 
        ***faire un split de ',' pour avoir les listes de lien
    """
    def getAllIp(self):
        fichier=open('donnee.txt','r')
        tabIp=[]
        for ligne in fichier:
            #[1,256.58.204.46?www.google.com,ww.canvas.com]
            splitDeuxPoint=ligne.split(":")
            id=splitDeuxPoint[0]
            #[256.58.204.46,'www.google.com,ww.canvas.com']
            splitPointInterro=splitDeuxPoint[1].split("?")
            adresse=splitPointInterro[0]
            #[www.google.com,ww.canvas.com]
            splitVirgule=splitPointInterro[1].split(',')
            listeLien=[]
            for lien in splitVirgule:
                listeLien.append(lien.rstrip())
            #creatioin de l'instance
            ip=Ip(id,adresse,listeLien)
            #ajout dans le tableau d'objet
            tabIp.append(ip)
        return tabIp


    def getIpById(self,id):
        listeIp=self.getAllIp()
        for ip in listeIp:
            if ip.getId()==id:
                return ip
        return None

    """
        prendre le voisin d'un ip:
        **prendre la tableau de duree
        ***chercher si il y a l'ip appelant dans ip1 ou ip2
            raha misy ip=ip1 : ajouter a la liste des voisins ip2
            raha misy ip=ip2 : ajouter a la liste des voisins ip1 
    """

    def getVoisin(self):
        instanceDuree=Duree()
        listeDuree=instanceDuree.getAllDuree()
        listeVoisin=[]
        for duree in listeDuree:
            if duree.getIdIp1()==self.id:
                listeVoisin.append(self.getIpById(duree.getIdIp2()))
            if duree.getIdIp2()==self.id:
                listeVoisin.append(self.getIpById(duree.getIdIp1()))
        return listeVoisin


    """
        verification si un ip contient un lien donnee
    """
    def verificationLien(self,lienChercher):
        listeLien=self.getListeLien()
        for lien in listeLien:
            if lien==lienChercher:
                return True
        return False

    """
        prendre la distance entre deux ip
        ip appelant et l'ip en argument

        il y verification si la ligne qui relie les deux ip sont couper ou pas
        si il est couper alors on ne prends pas la distance , d lasa infinie ouazy
        mila mampiasa instance an ilay tableau mitovy 
    """
    def getDureeIp(self,ipChercher,listeDuree):
        for duree in listeDuree:
            if duree.getIdIp1()==self.id and duree.getIdIp2()==ipChercher.getId() and duree.getCouper()==False:
                return int(duree.getDuree())
            if duree.getIdIp2()==self.id and duree.getIdIp1()==ipChercher.getId() and duree.getCouper()==False:
                return int(duree.getDuree())
        return float('inf')


    #verification si un p est deja dans une liste
    def verificationIpListe(self,liste):
        for l in liste:
            if l.getId()==self.getId():
                return True
        return False

    #recherche d'un ip a l'aide de son adresse
    def rechercheIpByAdresse(self,adresse):
        listeIp=self.getAllIp()
        for ip in listeIp:
            if ip.getAdresse()==adresse:
                return ip
        return None

    #couper le lien entre deux ip
    # l'ip appelant et l'ip en argument
    def couper(self,ip,listeDuree):
        for duree in listeDuree:
            if self.getId()==duree.getIdIp1() and ip.getId()==duree.getIdIp2():
                duree.setCouper(True)
            if self.getId()==duree.getIdIp2() and ip.getId()==duree.getIdIp1():
                duree.setCouper(True)

    #verification si deux ip sont couper
    def verificationIpCouper(self,ip,listeDuree):
        for duree in listeDuree:
            if self.getId()==duree.getIdIp1() and ip.getId()==duree.getIdIp2():
                return duree.getCouper()
            if self.getId()==duree.getIdIp2() and ip.getId()==duree.getIdIp1():
                return duree.getCouper()
