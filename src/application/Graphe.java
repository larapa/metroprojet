package application;

import java.util.ArrayList;
import java.util.List;

public class Graphe {
	public Sommet[] sommets;
	public int nombreSommets;
	public ArrayList<Arrête> arrêtes;
	public int nombreArrêtes;
	public int sat;
	public int eat;
	public ArrayList<Sommet> chemin = new ArrayList<Sommet>();
	
	public int calculnombreSommets() {
		ArrayList<Arrête> a=this.arrêtes;
		int nombreSommets = 0;
		for (Arrête e : a) {	
			if (e.getdestination() > nombreSommets)
				nombreSommets = e.getdestination();
			if (e.getorigine() > nombreSommets)
				nombreSommets = e.getorigine();
		}
		nombreSommets++;
		return nombreSommets;
	}

	public Graphe(ArrayList<Arrête> t) {
		this.arrêtes = t;
		this.nombreSommets = this.calculnombreSommets();
		this.sommets = new Sommet[this.nombreSommets];
		for (int n = 0; n < this.nombreSommets; n++) {
			this.sommets[n] = new Sommet();
		}
		this.nombreArrêtes = this.arrêtes.size();
		for (int m = 0; m < this.nombreArrêtes; m++) {
			this.sommets[arrêtes.get(m).getorigine()].getarrêtes().add(arrêtes.get(m));
			this.sommets[arrêtes.get(m).getdestination()].getarrêtes().add(arrêtes.get(m));
		}
	}

	public int stringtoint(String a) {
		for (int i=0; i<this.sommets.length;i++) {
			if (this.sommets[i].nom.equals(a)) {
				return i;
			}
		}
		throw new IllegalArgumentException("nom incorrect");
	}
	
	public int getpluscourtedistance() {
		int sommetstocké = 0;
		int distancestockée = Integer.MAX_VALUE;
		for (int i = 0; i < this.sommets.length; i++) {
			int distanceactuelle = this.sommets[i].getdistanceàlasource();
			if (!this.sommets[i].estVisité() && distanceactuelle < distancestockée) {
				distancestockée = distanceactuelle;
				sommetstocké = i;
			}
		}
		return sommetstocké;
	}
	
	public void calculpluscourtedistance(String a, String b,int k,int l) {
		int débutà=this.stringtoint(a)+k;
		int finà=this.stringtoint(b)+ l;
		this.sat = débutà;
		this.eat = finà;
		this.sommets[débutà].setdistanceàlasource(0);
		int sommetsuivant = débutà;
		for (int i = 0; i < this.sommets.length; i++) {
			ArrayList<Arrête> currentSommetArcs = this.sommets[sommetsuivant].getarrêtes();
			for (int joinedArc = 0; joinedArc < currentSommetArcs.size(); joinedArc++) {
				int neighbourIndex = currentSommetArcs.get(joinedArc).getautreextrémité(sommetsuivant);
				if (!this.sommets[neighbourIndex].estVisité()) {
					int tentative = this.sommets[sommetsuivant].getdistanceàlasource() + currentSommetArcs.get(joinedArc).getpoids();
					if (tentative < sommets[neighbourIndex].getdistanceàlasource()) {
						sommets[neighbourIndex].setdistanceàlasource(tentative);
						sommets[neighbourIndex].setprédécesseur(sommetsuivant);
					}
				}
			}
			sommets[sommetsuivant].setvisité(true);
			sommetsuivant = getpluscourtedistance();
		}
	}

    public String afficherrésultat(String dep,String arr,int a,int b) {
		String phrasetype="Ce trajet entre " + dep + "et " + arr + "dure ";
		String sortie="";
		int zz=sommets[stringtoint(arr)+a].getdistanceàlasource()-25;
		if (zz>3600000) {
			return "Un tel itinéraire n'existe pas";
		}
		else {
			if(b==2) {
				while (zz>36000){
					zz=zz-(36000-120);
					}
			}
			if (zz<60) {
				sortie = phrasetype + zz + " secondes.";
			}
			else {
				if (zz<3600) {
					int u=zz/60;
					int mm=zz-u*60;
					if (u>1) {
						if (mm>1){
							sortie=phrasetype + u +" minutes "+"et "+mm + " secondes.";
						}
						else {
							if (mm==1) {
								sortie=phrasetype+u +" minutes "+"et "+mm+ " seconde.";
							}
							else {
								sortie=phrasetype+ u+ " minutes.";
							}
						}
					}
					else {
						if (mm>1) {
							sortie=phrasetype+ u+ " minute" + " et " +mm + " secondes.";	
						}
						else {
							if (mm==1) {
								sortie=phrasetype+ u+ " minute" + " et " +mm + " secondes.";	
							}
							else {
								sortie=phrasetype+ u+ " minute.";	 
							}
						}
					}
				}
				else {
					int w=zz/3600;
					int x=(zz-w*3600)/60;
					int z=(zz-w*3600)-x*60;
					if (w>1) {
						if(x>1) {
							if (z>1) {
								sortie=phrasetype+w+" heures "+ x+ " minutes" + " et " +z+ " secondes.";  
							}
							else {
								if (z==1) {
									sortie=phrasetype+w+" heures "+ x+ " minutes" + " et " +z+ " seconde.";   
								}
								else {
									sortie=phrasetype+w+" heures "+ x+ " minutes.";  
								}
							}
						}
						else {
							if (x==1) {
								if (z>1) {
									sortie=phrasetype+w+" heures "+ x+ " minute" + " et " +z+ " secondes.";  
								}
								else {
									if (z==1) {
										sortie=phrasetype+w+" heures "+ x+ " minute" + " et " +z+ " seconde.";   
									}
									else {
										sortie=phrasetype+w+" heures "+ x+ " minute.";  
									}
								}
							}
							else {
								if (z>1) {
									sortie=phrasetype+w+" heures "+ "et " +z+ " secondes.";  
								}
								else {
									if (z==1) {
										sortie=phrasetype+w+" heures "+ " et" +z+ " seconde.";   
									}
									else {
										sortie=phrasetype+w+" heures.";  
									}
								}
							}
						}
					}
					else {
						if (w==1) {
							if(x>1) {
								if (z>1) {
									sortie=phrasetype+w+" heure "+ x+ " minutes" + " et " +z+ " secondes.";  
								}
								else {
									if (z==1) {
										sortie=phrasetype+w+" heure "+ x+ " minutes" + " et " +z+ " seconde.";   
									}
									else {
										sortie=phrasetype+w+" heure "+ x+ " minutes.";  
									}
								}
							}
							else {
								if (x==1) {
									if (z>1) {
										sortie=phrasetype+w+" heure "+ x+ " minute" + " et " +z+ " secondes.";  
									}
									else {
										if (z==1) {
											sortie=phrasetype+w+" heure "+ x+ " minute" + " et " +z+ " seconde.";   
										}
										else {
											sortie=phrasetype+w+" heure "+ x+ " minute.";  
										}
									}
								}
								else {
									if (z>1) {
										sortie=phrasetype+w+" heure "+ "et " +z+ " secondes.";  
									}
									else {
										if (z==1) {
											sortie=phrasetype+w+" heure "+ " et" +z+ " seconde.";   
										}
										else {
											sortie=phrasetype+w+" heure.";  
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return sortie;
	}

    public Sommet[] getsommets() {
	   return sommets;
    }
   
    public int getnombresommets() {
	   return nombreSommets;
    }

    public ArrayList<Arrête> getarrêtes() {
	   return this.arrêtes;
    }

    public int getnombrearrêtes() {
    	return nombreArrêtes;
    }

    public void calculerchemin(){
    	int sommetactuel = eat;
    	while(sommetactuel != sat){
    		int a=sommets[sommetactuel].getprédécesseur();
    		chemin.add(sommets[a]);
    		sommetactuel = sommets[sommetactuel].getprédécesseur();
    	}
    }

    public ArrayList<Sommet> getchemin(){
    	return chemin;
    }

    public void donnenom(List<String> t) {
    	for (int i=0;i<t.size();i++) {
    		this.sommets[i].nom=t.get(i);
    	}
    }

    public int nombredeligne(String t){
    	int a=0;
    	for (int i=0; i<this.sommets.length;i++) {
    		if (this.sommets[i].nom.equals( t)) {
    			a=a+1;
    		}
    	}
    	return a;
    }

    public void attribueligne(int [] t) {
    	for (int i=0;i<t.length;i++) {
    		this.sommets[i].ligne=t[i];
    	}
    }

    public ArrayList<String> transformation() {
    	ArrayList<String> u=new ArrayList<String>();
    	for (int i=0;i<this.chemin.size();i++) {
    		u.add (this.chemin.get(i).nom);
    	}
    	return u;
    }
    
    public void retirerligne(int a) {
    	for (int i=0;i<this.arrêtes.size();i++) {
    		if (this.sommets[this.arrêtes.get(i).getdestination()].ligne==a || this.sommets[this.arrêtes.get(i).getorigine()].ligne==a ) {
    			this.arrêtes.get(i).poids=3600001;
    		}
    	}
    }

    public void retirerligne2(ArrayList<Integer> a) {
    	for (int i=0;i<a.size();i++ ) {
    		this.retirerligne(a.get(i));
    	}
    }

    public void retirerstation(String d) {
    	for (int i=0;i<this.arrêtes.size();i++) {
    		if (this.sommets[this.arrêtes.get(i).getdestination()].nom.equals(d) || this.sommets[this.arrêtes.get(i).getorigine()].nom.contentEquals(d) ) {
    			this.arrêtes.get(i).poids=3600001;
    		}
    	}
    }

    public void retirerstation2(ArrayList<String> a) {
    	for (String s: a ) {
    		this.retirerstation(s);
    	}
    }

	public void transformearrête(){
		for (int i=0;i<this.arrêtes.size();i++) {
			if ((this.sommets[this.arrêtes.get(i).origine].nom).equals(this.sommets[this.arrêtes.get(i).destination].nom)){
				this.arrêtes.get(i).poids=36000+25;
				}
			}
		}
}













