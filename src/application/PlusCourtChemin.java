package application;



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.IOException;

import java.nio.file.Files;

import java.nio.file.Paths;

import java.util.ArrayList;

import java.util.Collections;

import java.util.List;

import javax.imageio.ImageIO;



public class PlusCourtChemin {
    boolean b;
	String d¨¦part;

	String arriv¨¦e;

	ArrayList<Integer> LignesInterdites;

	ArrayList<String> StationsInterdites;

	

	public PlusCourtChemin(String d¨¦part, String arriv¨¦e,ArrayList<Integer> LignesInterdites, ArrayList<String> StationsInterdites,boolean a) {
        this.b=a;
		this.d¨¦part = d¨¦part;

		this.arriv¨¦e = arriv¨¦e;

		this.LignesInterdites = LignesInterdites;

		this.StationsInterdites = StationsInterdites;

	}

	public String afficheChemin() throws IOException {



		String texte1;

		String texte2;







		ArrayList<Arr¨ºte> arr¨ºtes = new  ArrayList<Arr¨ºte>();

		List<String> arr¨ºtesString = null;

		BufferedReader bf=new BufferedReader(new FileReader("./src/application/coordonnes1.txt"));
		
		String textLine;
		
		String str2 ="";
		
		while((textLine=bf.readLine())!=null){
		
			str2+=textLine;
		}
		
		String[] numbers=str2.split("	");
		
		float []cord=new float[numbers.length]; 
		
		for (int i = 0; i < numbers.length; i++) {
		
			cord[i]=Float.parseFloat(numbers[i]);
		}
		
		bf.close();

		
		arr¨ºtesString = Files.readAllLines(Paths.get("./src/application/Arr¨ºtes.txt"));



		for (String s: arr¨ºtesString) {

			String str[] = s.split(" ");

			ArrayList<Integer> b = new ArrayList<Integer>();

			for (String t: str) {

				int n = Integer.parseInt(t);

				b.add(n);

			}

			arr¨ºtes.add(new Arr¨ºte(b.get(0), b.get(1), b.get(2)+25));

		}

        

        BufferedReader br;

        String motLu;

        ArrayList<Integer> lignesbis=new ArrayList<Integer>();

        List<String> nombis = new ArrayList<String>();

        try{

            br = new BufferedReader(new FileReader(new File("./src/application/Stations1.txt")));

            while((motLu = br.readLine()) != null)

            {

            if (motLu.charAt(motLu.length()-2) ==';'){

            int monEntier = Integer.parseInt(motLu.charAt(motLu.length()-1)+"");

            lignesbis.add(monEntier);

            nombis.add(motLu.substring(0,motLu.length()-2 ));

            

            	

            }

            else {

            	int monEntier=Integer.parseInt(motLu.charAt(motLu.length()-1)+"") + (Integer.parseInt(motLu.charAt(motLu.length()-2)+"")*10) ;

                lignesbis.add(monEntier);

                nombis.add(motLu.substring(0,motLu.length()-3 ));

            }

            

          

            }

            br.close();

        }catch(FileNotFoundException e){

            e.printStackTrace();

        }catch(IOException e){

            e.printStackTrace();

        }

        List<String>nom= nombis;

 	   

        int [] lignes= new int [lignesbis.size()];

        for (int i=0;i<lignesbis.size();i++) {

        	lignes[i]=lignesbis.get(i);

        }

    

	   



		//on cr¨¦e le graphe:

		Graphe g = new Graphe(arr¨ºtes);



		g.donnenom(nom);

		g.attribueligne(lignes);
		
		g.donnecord(cord);

		//on prend en compte les contraintes

		g.retirerligne2(LignesInterdites);

		g.retirerstation2(StationsInterdites);
		if(!this.b) {

		g.transformearr¨ºte();
		}
		int w=0;



		int z=0;

		int k=0;

		int u=100000000;



		while (w<g.nombredeligne(d¨¦part)) {



			int y=0;

			while (y<g.nombredeligne(arriv¨¦e)) {

				Graphe e = new Graphe(arr¨ºtes);


				
				e.donnenom(nom);

				e.attribueligne(lignes);

				e.retirerligne2(LignesInterdites);

				e.retirerstation2(StationsInterdites);

				if(!this.b) {

					e.transformearr¨ºte();
					}

				e.calculpluscourtedistance(d¨¦part,arriv¨¦e,w,y);

				int m=e.sommets[e.stringtoint(arriv¨¦e) + y].getdistance¨¤lasource();



				if (m<u) {

					u=m;

					k=w;

					z=y;

				}

				y=y+1;

			}

			w=w+1;

		}



		g.calculpluscourtedistance(d¨¦part,arriv¨¦e,k,z);



		g.calculerchemin();

		ArrayList<Sommet> path = g.getchemin();

		ArrayList<String> h =g.transformation();

		Collections.reverse(h);

		Collections.reverse(path);

		
		BufferedImage image = ImageIO.read(new File("./src/application/plan.png"));
		Graphics2D graphics = image.createGraphics();
		graphics.setStroke(new BasicStroke(3.0f));
		for (int i = 0; i < (cord.length+1)/2; i++) {
			Ellipse2D e = new Ellipse2D.Float(g.sommets[i].x,g.sommets[i].y,2.0f,2.0f);
			switch(g.sommets[i].ligne){
			case 1:
			graphics.setColor(Color.black);
			graphics.draw(e);
			break;
			case 2:
			graphics.setColor(Color.blue);
			graphics.draw(e);
			break;
			case 3:
			graphics.setColor(Color.cyan);
			graphics.draw(e);
			break;
			case 4:
			graphics.setColor(Color.darkGray);
			graphics.draw(e);
			break;
			case 5:
			graphics.setColor(Color.gray);
			graphics.draw(e);
			break;
			case 6:
			graphics.setColor(Color.green);
			graphics.draw(e);
			break;
			case 7:
			graphics.setColor(Color.lightGray);
			graphics.draw(e);
			break;
			case 8:
			graphics.setColor(Color.magenta);
			graphics.draw(e);
			break;
			case 9:
			graphics.setColor(Color.orange);
			graphics.draw(e);
			break;
			case 10:
			graphics.setColor(Color.pink);
			graphics.draw(e);
			break;
			case 11:
			graphics.setColor(Color.red);
			graphics.draw(e);
			break;
			case 12:
			graphics.setColor(Color.yellow);
			graphics.draw(e);
			break;
			case 13:
			graphics.setColor(Color.blue);
			graphics.draw(e);
			break;
			case 14:
			graphics.setColor(Color.cyan);
			graphics.draw(e);
			break;
			case 15:
			graphics.setColor(Color.green);
			graphics.draw(e);
			break;
			case 16:
			graphics.setColor(Color.lightGray);
			graphics.draw(e);
			break;
			}
		}	

		switch(g.sommets[g.stringtoint(d¨¦part)].ligne){
		case 1:
		graphics.setColor(Color.black);
		break;
		case 2:
		graphics.setColor(Color.blue);
		break;
		case 3:
		graphics.setColor(Color.cyan);;
		break;
		case 4:
		graphics.setColor(Color.darkGray);
		break;
		case 5:
		graphics.setColor(Color.gray);
		break;
		case 6:
		graphics.setColor(Color.green);
		break;
		case 7:
		graphics.setColor(Color.lightGray);
		break;
		case 8:
		graphics.setColor(Color.magenta);
		break;
		case 9:
		graphics.setColor(Color.orange);
		break;
		case 10:
		graphics.setColor(Color.pink);
		break;
		case 11:
		graphics.setColor(Color.red);
		break;
		case 12:
		graphics.setColor(Color.yellow);
		break;
		case 13:
		graphics.setColor(Color.blue);
		break;
		case 14:
		graphics.setColor(Color.cyan);
		break;
		case 15:
		graphics.setColor(Color.green);
		break;
		case 16:
		graphics.setColor(Color.lightGray);
		break;
		}
		graphics.drawString(g.sommets[g.stringtoint(d¨¦part)].nom, g.sommets[g.stringtoint(d¨¦part)].x, g.sommets[g.stringtoint(d¨¦part)].y);
		graphics.draw(new Line2D.Double(g.sommets[g.stringtoint(d¨¦part)].x,g.sommets[g.stringtoint(d¨¦part)].y , path.get(0).x,path.get(0).y));
		
		for (int i = 0; i < path.size()-1; i++) {
			switch(path.get(i).ligne){
			case 1:
			graphics.setColor(Color.black);
			break;
			case 2:
			graphics.setColor(Color.blue);
			break;
			case 3:
			graphics.setColor(Color.cyan);;
			break;
			case 4:
			graphics.setColor(Color.darkGray);
			break;
			case 5:
			graphics.setColor(Color.gray);
			break;
			case 6:
			graphics.setColor(Color.green);
			break;
			case 7:
			graphics.setColor(Color.lightGray);
			break;
			case 8:
			graphics.setColor(Color.magenta);
			break;
			case 9:
			graphics.setColor(Color.orange);
			break;
			case 10:
			graphics.setColor(Color.pink);
			break;
			case 11:
			graphics.setColor(Color.red);
			break;
			case 12:
			graphics.setColor(Color.yellow);
			break;
			case 13:
			graphics.setColor(Color.blue);
			break;
			case 14:
			graphics.setColor(Color.cyan);
			break;
			case 15:
			graphics.setColor(Color.green);
			break;
			case 16:
			graphics.setColor(Color.lightGray);
			break;
			}
			graphics.draw(new Line2D.Double(path.get(i).x,path.get(i).y,path.get(i+1).x,path.get(i+1).y));
			
			graphics.drawString(path.get(i).nom, path.get(i).x, path.get(i).y);
		}
		
		
		graphics.draw(new Line2D.Double(path.get(path.size()-1).x,path.get(path.size()-1).y,g.sommets[g.stringtoint(arriv¨¦e)].x ,g.sommets[g.stringtoint(arriv¨¦e)].y));
		
		graphics.drawString(g.sommets[g.stringtoint(arriv¨¦e)].nom, g.sommets[g.stringtoint(arriv¨¦e)].x, g.sommets[g.stringtoint(arriv¨¦e)].y);
		
		graphics.dispose();
		
		if (this.b){
		ImageIO.write(image, "png", new File("./bin/application/abc.png"));
		}
		else{
		ImageIO.write(image, "png", new File("./bin/application/abc2.png"));	
		}



		if (h.contains(arriv¨¦e)) {

			System.out.println(g.sommets[g.stringtoint(d¨¦part)].ligne);
            if (g.chemin.get(0+k).ligne==15) {
			h.set(0, h.get(0)+"(" + "3bis"+")");
            }
            else {
            	if(g.chemin.get(0+k).ligne==16) {
            		h.set(0, h.get(0)+"(" + "7bis"+")");
            	}
            	else {
            		h.set(0, h.get(0)+"(" + g.chemin.get(0+k).ligne+")");
            	}
            }
			for (int m=1; m<h.size()-1;m++) {

				if (h.get(m).equals(h.get(m+1))){

					h.set(m+1, h.get(m+1)+"(" + g.chemin.get(m+1).ligne +")");

				}

			}

			if ((g.sommets[g.stringtoint(arriv¨¦e)+z].getdistance¨¤lasource()-25)>3600000) {

				texte1="";

			}

			else {
            if(this.b) {
			texte1=("Le trajet le plus court est " + h+" :");
            }
            else {
            texte1=("Le trajet avec le moins de changements est " + h+" :");
            }
			}


            if (this.b) {
			texte2=g.afficherr¨¦sultat(d¨¦part, arriv¨¦e,z,1);
            }
            else {
            	texte2=g.afficherr¨¦sultat(d¨¦part, arriv¨¦e,z,2);
            }


		}

		else {

			h.add(arriv¨¦e);



			if (g.chemin.get(0+k).ligne==15) {
				h.set(0, h.get(0)+"(" + "3bis"+")");
	            }
	            else {
	            	if(g.chemin.get(0+k).ligne==16) {
	            		h.set(0, h.get(0)+"(" + "7bis"+")");
	            	}
	            	else {
	            		h.set(0, h.get(0)+"(" + g.chemin.get(0+k).ligne+")");
	            	}
	            }
			for (int m=1; m<h.size()-1;m++) {

				if (h.get(m).equals(h.get(m+1))){
					if (g.chemin.get(m+1).ligne==15) {
						h.set(m+1, h.get(m+1)+"(" + "3bis"+")");
			            }
			            else {
			            	if(g.chemin.get(m+1).ligne==16) {
			            		h.set(m+1, h.get(m+1)+"(" + "7bis"+")");
			            	}
			            	else {
			            		h.set(m+1, h.get(m+1)+"(" + g.chemin.get(m+1).ligne+")");
			            	}
					

			            }

				}
			}
			if ((g.sommets[g.stringtoint(arriv¨¦e)+z].getdistance¨¤lasource()-25)>3600000) {

				texte1="";

			}

			else {

				 if(this.b) {
						texte1=("Le trajet le plus court est " + h+" :");
			            }
			            else {
			            texte1=("Le trajet avec le moins de changements est " + h+" :");
			            }
						}



			 if (this.b) {
					texte2=g.afficherr¨¦sultat(d¨¦part, arriv¨¦e,z,1);
		            }
		            else {
		            	texte2=g.afficherr¨¦sultat(d¨¦part, arriv¨¦e,z,2);
		            }





	            }



		return texte1 + texte2;



	}
	}