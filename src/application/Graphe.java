package application;
import java.util.ArrayList;
import java.util.List;

public class Graphe {
    public Sommet[] sommets;
    public int nombreSommets;
    public ArrayList<Arr�te> arr�tes;
    private int nombreArr�tes;
    private int sat;
    private int eat;
    public ArrayList<Sommet> path = new ArrayList<Sommet>();

    public Graphe(ArrayList<Arr�te> t) {
        this.arr�tes = t;
        this.nombreSommets = calculnombreSommets(t);
        this.sommets = new Sommet[this.nombreSommets];
        for (int n = 0; n < this.nombreSommets; n++) {
        	this.sommets[n] = new Sommet();
        	}
        this.nombreArr�tes = this.arr�tes.size();
        for (int m = 0; m < this.nombreArr�tes; m++) {
            this.sommets[arr�tes.get(m).getorigine()].getArr�tes().add(arr�tes.get(m));
            this.sommets[arr�tes.get(m).getdestination()].getArr�tes().add(arr�tes.get(m));
            }
    }

    private int calculnombreSommets(ArrayList<Arr�te> a) {
    	int nombreSommets = 0;
    	for (Arr�te e : a) {
    		if (e.getdestination() > nombreSommets)
    			nombreSommets = e.getdestination();
    		if (e.getorigine() > nombreSommets)
    			nombreSommets = e.getorigine();
    	}
    	nombreSommets++;
    	return nombreSommets;
    }

    public int StringtoInt(String a) {
    	for (int i=0; i<this.sommets.length;i++) {
    		if (this.sommets[i].nom.equals(a)) {
    			return i;
    		}
    	}
    	throw new IllegalArgumentException("nom incorrect");
    }

    public void calculateShortestDistances(String a, String b,int k,int l) {
    	int startAt=this.StringtoInt(a)+k;
    	int endAt=this.StringtoInt(b)+ l;
    	this.sat = startAt;
    	this.eat = endAt;
    	this.sommets[startAt].setDistanceFromSource(0);
    	int nextNode = startAt;
    	for (int i = 0; i < this.sommets.length; i++) {
    		ArrayList<Arr�te> currentSommetArcs = this.sommets[nextNode].getArr�tes();
    		for (int joinedArc = 0; joinedArc < currentSommetArcs.size(); joinedArc++) {
    			int neighbourIndex = currentSommetArcs.get(joinedArc).getnumerovoisin(nextNode);
    			if (!this.sommets[neighbourIndex].estVisit�()) {
    				int tentative = this.sommets[nextNode].getDistance�laSource() + currentSommetArcs.get(joinedArc).getpoids();
    				if (tentative < sommets[neighbourIndex].getDistance�laSource()) {
    					sommets[neighbourIndex].setDistanceFromSource(tentative);
    					sommets[neighbourIndex].setPredecessor(nextNode);
    				}
    			}
    		}
    		sommets[nextNode].setVisit�(true);
    		nextNode = getSommetShortestDistanced();
    	}
    }

    private int getSommetShortestDistanced() {
    	int storedNodeIndex = 0;
    	int storedDist = Integer.MAX_VALUE;
    	for (int i = 0; i < this.sommets.length; i++) {
    		int currentDist = this.sommets[i].getDistance�laSource();
    		if (!this.sommets[i].estVisit�() && currentDist < storedDist) {
    			storedDist = currentDist;
    			storedNodeIndex = i;
    		}
    	}
    	return storedNodeIndex;
    }

    public String printResult(String dep,String arr,int a) {
    	String output="";
        if ((sommets[StringtoInt(arr)+a].getDistance�laSource()-25)>3600000) {
        	return "Un tel itin�raire n'existe pas";
        }
        else {
        	if ((sommets[StringtoInt(arr)+a].getDistance�laSource()-25)<60) {
        		output = ("Ce trajet entre " + dep + "et " + arr + "dure " + (sommets[StringtoInt(arr)+a].getDistance�laSource()-25) + " secondes.");
        	}
        else {
             if ((sommets[StringtoInt(arr)+a].getDistance�laSource()-25)<3600) {
                 int u=(sommets[StringtoInt(arr)+a].getDistance�laSource()-25)/60;
                 int mm=(sommets[StringtoInt(arr)+a].getDistance�laSource()-25)-u*60;
                 if (u>1) {
                   if (mm>1){
                	   output=("Ce trajet entre " + dep + "et " + arr + "dure " + u+ " minutes" + " et " +( (sommets[StringtoInt(arr)+a].getDistance�laSource()-25)-u*60) + " secondes.");
                   }
                   else {
                	   if (mm==1) {
                		   output=("Ce trajet entre " + dep + "et " + arr + "dure " + u+ " minutes" + " et " +( (sommets[StringtoInt(arr)+a].getDistance�laSource()-25)-u*60) + " seconde.");
                	   }
                	   else {
                		   output=("Ce trajet entre " + dep + "et " + arr + "dure " + u+ " minutes.");
                	   }
                   }
                 }
                 else {
                   if (mm>1) {
                	   output=("Ce trajet entre " + dep + "et " + arr + "dure " + u+ " minute" + " et " +( (sommets[StringtoInt(arr)+a].getDistance�laSource()-25)-u*60) + " secondes.");	
                   }
                   else {
                	   if (mm==1) {
                		   output=("Ce trajet entre " + dep + "et " + arr + "dure " + u+ " minute" + " et " +( (sommets[StringtoInt(arr)+a].getDistance�laSource()-25)-u*60) + " secondes.");	
                	   }
                	   else {
                		   output=("Ce trajet entre " + dep + "et " + arr + "dure " + u+ " minute ");	 
                	   }
                   }
                 }
             }
             else {
        	  int w=(sommets[StringtoInt(arr)+a].getDistance�laSource()-25)/3600;
        	  int x=((sommets[StringtoInt(arr)+a].getDistance�laSource()-25)-w*3600)/60;
        	  int z=((sommets[StringtoInt(arr)+a].getDistance�laSource()-25)-w*3600)-x*60;
        	  if (w>1) {
        		  if(x>1) {
        			  if (z>1) {
        				  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heures "+ x+ " minutes" + " et " +z+ " secondes.");  
        			  }
        			  else {
        				  if (z==1) {
        					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heures "+ x+ " minutes" + " et " +z+ " seconde.");  
        				  }
        				  else {
        					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heures "+ x+ " minutes.");  
        				  }
        			  }
        		  }
        		  else {
        			  if (x==1) {
        				  if (z>1) {
            				  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heures "+ x+ " minute" + " et " +z+ " secondes.");  
            			  }
            			  else {
            				  if (z==1) {
            					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heures "+ x+ " minute" + " et " +z+ " seconde.");  
            				  }
            				  else {
            					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heures "+ x+ " minute.");  
            				  }
            			  }
            		  
        			  }
        			  else {
        				  if (z>1) {
            				  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heures " + "et " +z+ " secondes.");  
            			  }
            			  else {
            				  if (z==1) {
            					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heures "+  "et " +z+ " seconde.");  
            				  }
            				  else {
            					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heures.");  
            				  }
            			  }
        			  }
        		  }
        	  }
        	  else {
        		  if (w==1) {
            		  if(x>1) {
            			  if (z>1) {
            				  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heure "+ x+ " minutes" + " et " +z+ " secondes.");  
            			  }
            			  else {
            				  if (z==1) {
            					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heure "+ x+ " minutes" + " et " +z+ " seconde.");  
            				  }
            				  else {
            					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heure "+ x+ " minutes.");  
            				  }
            			  }
            		  }
            		  else {
            			  if (x==1) {
            				  if (z>1) {
                				  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heure "+ x+ " minute" + " et " +z+ " secondes.");  
                			  }
                			  else {
                				  if (z==1) {
                					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heure "+ x+ " minute" + " et " +z+ " seconde.");  
                				  }
                				  else {
                					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heure "+ x+ " minute.");  
                				  }
                			  }
                		  
            			  }
            			  else {
            				  if (z>1) {
                				  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heure " + "et " +z+ " secondes.");  
                			  }
                			  else {
                				  if (z==1) {
                					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heure "+  "et " +z+ " seconde.");  
                				  }
                				  else {
                					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heure.");  
                				  }
                			  }
            			  }
            		  }
            	  }
        	  }
             }
             
        }
        }
        return output;

    }
    public String printResult2(String dep,String arr,int a) {

        int zz=sommets[StringtoInt(arr)+a].getDistance�laSource()-25;
        String output="";
        if (zz>3600000) {
        	return "Un tel itin�raire n'existe pas";
        }
        else {
        while (zz>36000){
        	zz=zz-(36000-120);
        }
        if (zz<60) {


        output = ("Ce trajet entre " + dep + "et " + arr + "dure " + (sommets[StringtoInt(arr)+a].getDistance�laSource()-25) + " secondes.");


        }
        else  {
             if (zz<3600) {
                 int u=zz/60;
                 int mm=zz-u*60;
                 if (u>1) {
                   if (mm>1){
                   output=("Ce trajet entre " + dep + "et " + arr + "dure " + u+ " minutes" + " et " +( zz-u*60) + " secondes.");
                 }
                   else {
                	   if (mm==1) {
                		   output=("Ce trajet entre " + dep + "et " + arr + "dure " + u+ " minutes" + " et " +( zz-u*60) + " seconde.");
                	   }
                	   else {
                		   output=("Ce trajet entre " + dep + "et " + arr + "dure " + u+ " minutes.");
                	   }
                   }
                 }
                 else {
                   if (mm>1) {
        	       output=("Ce trajet entre " + dep + "et " + arr + "dure " + u+ " minute" + " et " +( zz-u*60) + " secondes.");	
                  }
                   else {
                	   if (mm==1) {
                		   output=("Ce trajet entre " + dep + "et " + arr + "dure " + u+ " minute" + " et " +( zz-u*60) + " secondes.");	
                	   }
                	   else {
                		   output=("Ce trajet entre " + dep + "et " + arr + "dure " + u+ " minute ");	 
                	   }
                   }
                 }
             }
             else {
        	  int w=zz/3600;
        	  int x=zz/60;
        	  int z=(zz-w*3600)-x*60;
        	  if (w>1) {
        		  if(x>1) {
        			  if (z>1) {
        				  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heures "+ x+ " minutes" + " et " +z+ " secondes.");  
        			  }
        			  else {
        				  if (z==1) {
        					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heures "+ x+ " minutes" + " et " +z+ " seconde.");  
        				  }
        				  else {
        					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heures "+ x+ " minutes.");  
        				  }
        			  }
        		  }
        		  else {
        			  if (x==1) {
        				  if (z>1) {
            				  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heures "+ x+ " minute" + " et " +z+ " secondes.");  
            			  }
            			  else {
            				  if (z==1) {
            					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heures "+ x+ " minute" + " et " +z+ " seconde.");  
            				  }
            				  else {
            					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heures "+ x+ " minute.");  
            				  }
            			  }
            		  
        			  }
        			  else {
        				  if (z>1) {
            				  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heures " + "et " +z+ " secondes.");  
            			  }
            			  else {
            				  if (z==1) {
            					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heures "+  "et " +z+ " seconde.");  
            				  }
            				  else {
            					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heures.");  
            				  }
            			  }
        			  }
        		  }
        	  }
        	  else {
        		  if (w==1) {
            		  if(x>1) {
            			  if (z>1) {
            				  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heure "+ x+ " minutes" + " et " +z+ " secondes.");  
            			  }
            			  else {
            				  if (z==1) {
            					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heure "+ x+ " minutes" + " et " +z+ " seconde.");  
            				  }
            				  else {
            					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heure "+ x+ " minutes.");  
            				  }
            			  }
            		  }
            		  else {
            			  if (x==1) {
            				  if (z>1) {
                				  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heure "+ x+ " minute" + " et " +z+ " secondes.");  
                			  }
                			  else {
                				  if (z==1) {
                					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heure "+ x+ " minute" + " et " +z+ " seconde.");  
                				  }
                				  else {
                					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heure "+ x+ " minute.");  
                				  }
                			  }
                		  
            			  }
            			  else {
            				  if (z>1) {
                				  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heure " + "et " +z+ " secondes.");  
                			  }
                			  else {
                				  if (z==1) {
                					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heure "+  "et " +z+ " seconde.");  
                				  }
                				  else {
                					  output=("Ce trajet entre " + dep + "et " + arr + "dure " +w+" heure.");  
                				  }
                			  }
            			  }
            		  }
            	  }
        	  }
             }
             
        }
        }
        return output;

    }



    public Sommet[] getSommets() {

        return sommets;

    }



    public int getnombreSommets() {

        return nombreSommets;

    }



    public ArrayList<Arr�te> getArr�te() {

        return this.arr�tes;

    }



    public int getnombreArr�tes() {

        return nombreArr�tes;

    }

    public void calculatePath(){

        int nodeNow = eat;



        while(nodeNow != sat){

            int a=sommets[nodeNow].getPredecessor();



            path.add(sommets[a]);

            nodeNow = sommets[nodeNow].getPredecessor();



        }



    }



    public ArrayList<Sommet> getPath(){



        return path;



    }

    public void donnenom(List<String> t) {

        for (int i=0;i<t.size();i++) {

            this.sommets[i].nom=t.get(i);

        }



    }

    public int estmultiligne(String t){



        int a=0;



        for (int i=0; i<this.sommets.length;i++) {



            if (this.sommets[i].nom.equals( t)) {

                a=a+1;

            }



        }

        return a;

    }

    public void donneligne(int [] t) {

        for (int i=0;i<t.length;i++) {

            this.sommets[i].ligne=t[i];

        }



    }

    public ArrayList<String> transformation() {

        ArrayList<String> u=new ArrayList<String>();

        for (int i=0;i<this.path.size();i++) {

            u.add (this.path.get(i).nom);

        }

        return u;

    }

    public void retirerligne(int a) {





        for (int i=0;i<this.arr�tes.size();i++) {

            if (this.sommets[this.arr�tes.get(i).getdestination()].ligne==a || this.sommets[this.arr�tes.get(i).getorigine()].ligne==a ) {

                this.arr�tes.get(i).poids=3600001;

            }



        }





    }





    public void retirerligne2(ArrayList<Integer> a) {

        for (int i=0;i<a.size();i++ ) {

            this.retirerligne(a.get(i));

        }

    }





    public void retirerstation(String d) {



        for (int i=0;i<this.arr�tes.size();i++) {



            if (this.sommets[this.arr�tes.get(i).getdestination()].nom.equals(d) || this.sommets[this.arr�tes.get(i).getorigine()].nom.contentEquals(d) ) {



                this.arr�tes.get(i).poids=3600001;

            }

        }

    }

    public void retirerstation2(ArrayList<String> a) {

        for (String s: a ) {

            this.retirerstation(s);

        }

    }
    public void transformearr�te(){
    	for (int i=0;i<this.arr�tes.size();i++) {
    		if ((this.sommets[this.arr�tes.get(i).origine].nom).equals(this.sommets[this.arr�tes.get(i).destination].nom)){
    			
    			this.arr�tes.get(i).poids=36000+25;
    		}
    	
    	}
    }
}