package application;

import java.util.ArrayList;
import java.util.List;

public class Graphe {
	public Sommet[] sommets;
	public int nombreSommets;
	public ArrayList<Arr那te> arr那tes;
	public int nombreArr那tes;
	public int sat;
	public int eat;
	public ArrayList<Sommet> chemin = new ArrayList<Sommet>();
	
	public int calculnombreSommets() {
		ArrayList<Arr那te> a=this.arr那tes;
		int nombreSommets = 0;
		for (Arr那te e : a) {	
			if (e.getdestination() > nombreSommets)
				nombreSommets = e.getdestination();
			if (e.getorigine() > nombreSommets)
				nombreSommets = e.getorigine();
		}
		nombreSommets++;
		return nombreSommets;
	}

	public Graphe(ArrayList<Arr那te> t) {
		this.arr那tes = t;
		this.nombreSommets = this.calculnombreSommets();
		this.sommets = new Sommet[this.nombreSommets];
		for (int n = 0; n < this.nombreSommets; n++) {
			this.sommets[n] = new Sommet();
		}
		this.nombreArr那tes = this.arr那tes.size();
		for (int m = 0; m < this.nombreArr那tes; m++) {
			this.sommets[arr那tes.get(m).getorigine()].getarr那tes().add(arr那tes.get(m));
			this.sommets[arr那tes.get(m).getdestination()].getarr那tes().add(arr那tes.get(m));
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
		int sommetstock谷 = 0;
		int distancestock谷e = Integer.MAX_VALUE;
		for (int i = 0; i < this.sommets.length; i++) {
			int distanceactuelle = this.sommets[i].getdistance角lasource();
			if (!this.sommets[i].estVisit谷() && distanceactuelle < distancestock谷e) {
				distancestock谷e = distanceactuelle;
				sommetstock谷 = i;
			}
		}
		return sommetstock谷;
	}
	
	public void calculpluscourtedistance(String a, String b,int k,int l) {
		int d谷but角=this.stringtoint(a)+k;
		int fin角=this.stringtoint(b)+ l;
		this.sat = d谷but角;
		this.eat = fin角;
		this.sommets[d谷but角].setdistance角lasource(0);
		int sommetsuivant = d谷but角;
		for (int i = 0; i < this.sommets.length; i++) {
			ArrayList<Arr那te> currentSommetArcs = this.sommets[sommetsuivant].getarr那tes();
			for (int joinedArc = 0; joinedArc < currentSommetArcs.size(); joinedArc++) {
				int neighbourIndex = currentSommetArcs.get(joinedArc).getautreextr谷mit谷(sommetsuivant);
				if (!this.sommets[neighbourIndex].estVisit谷()) {
					int tentative = this.sommets[sommetsuivant].getdistance角lasource() + currentSommetArcs.get(joinedArc).getpoids();
					if (tentative < sommets[neighbourIndex].getdistance角lasource()) {
						sommets[neighbourIndex].setdistance角lasource(tentative);
						sommets[neighbourIndex].setpr谷d谷cesseur(sommetsuivant);
					}
				}
			}
			sommets[sommetsuivant].setvisit谷(true);
			sommetsuivant = getpluscourtedistance();
		}
	}

    public String afficherr谷sultat(String dep,String arr,int a,int b) {
		String phrasetype="Ce trajet entre " + dep + "et " + arr + "dure ";
		String sortie="";
		int zz=sommets[stringtoint(arr)+a].getdistance角lasource()-25;
		if (zz>3600000) {
			return "Un tel itin谷raire n'existe pas";
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

    public ArrayList<Arr那te> getarr那tes() {
	   return this.arr那tes;
    }

    public int getnombrearr那tes() {
    	return nombreArr那tes;
    }

    public void calculerchemin(){
    	int sommetactuel = eat;
    	while(sommetactuel != sat){
    		int a=sommets[sommetactuel].getpr谷d谷cesseur();
    		chemin.add(sommets[a]);
    		sommetactuel = sommets[sommetactuel].getpr谷d谷cesseur();
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
    
    public void donnecord(float cord[]) {
    	for (int i = 0; i < cord.length-1; i+=2) {
			this.sommets[i/2].x = cord[i];
		}
    	for (int i = 1; i < cord.length; i+=2) {
			this.sommets[(i+1)/2-1].y = cord[i];
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
    	for (int i=0;i<this.arr那tes.size();i++) {
    		if (this.sommets[this.arr那tes.get(i).getdestination()].ligne==a || this.sommets[this.arr那tes.get(i).getorigine()].ligne==a ) {
    			this.arr那tes.get(i).poids=3600001;
    		}
    	}
    }

    public void retirerligne2(ArrayList<Integer> a) {
    	for (int i=0;i<a.size();i++ ) {
    		this.retirerligne(a.get(i));
    	}
    }

    public void retirerstation(String d) {
    	for (int i=0;i<this.arr那tes.size();i++) {
    		if (this.sommets[this.arr那tes.get(i).getdestination()].nom.equals(d) || this.sommets[this.arr那tes.get(i).getorigine()].nom.contentEquals(d) ) {
    			this.arr那tes.get(i).poids=3600001;
    		}
    	}
    }

    public void retirerstation2(ArrayList<String> a) {
    	for (String s: a ) {
    		this.retirerstation(s);
    	}
    }

	public void transformearr那te(){
		for (int i=0;i<this.arr那tes.size();i++) {
			if ((this.sommets[this.arr那tes.get(i).origine].nom).equals(this.sommets[this.arr那tes.get(i).destination].nom)){
				this.arr那tes.get(i).poids=36000+25;
				}
			}
		}
}