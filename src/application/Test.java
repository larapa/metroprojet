package application;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

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

import java.nio.file.Files;

import java.nio.file.Paths;


import java.util.Collections;



public class Test {

	public static void main(String[] args) throws IOException  {
		
		ArrayList<ArrayList<String>> itinéraires=new ArrayList<ArrayList<String>>();
		BufferedReader br;
		String préc="";
		String motLu;
		
		try{
			br = new BufferedReader(new FileReader(new File("./src/application/test_itinéraire.txt")));
			while((motLu = br.readLine()) != null)
			{
		     int a=0;
		     ArrayList<String> u=new ArrayList<String>();
			 for (int i=0;i<motLu.length();i++) {
				 if (motLu.charAt(i)==';') {
					 if (a==0) {
						 u.add(motLu.substring(0, i)+" ");
					 }
					 else {
					 u.add(motLu.substring(a+1, i)+" ");
					 
					 }
					 a=i;
				 }
			 }
		     u.add(motLu.substring(a+1)+" ");
			 itinéraires.add(u);
             

			
			
			}
			br.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
for(int i=0;i<itinéraires.size();i++) {
	String départ=itinéraires.get(i).get(0);
	String arrivée=itinéraires.get(i).get(itinéraires.get(i).size()-1);
	ArrayList<Arrête> arrêtes = new  ArrayList<Arrête>();

	List<String> arrêtesString = null;


	
	
	

	
	arrêtesString = Files.readAllLines(Paths.get("./src/application/Arrêtes.txt"));



	for (String s: arrêtesString) {

		String str[] = s.split(" ");

		ArrayList<Integer> b = new ArrayList<Integer>();

		for (String t: str) {

			int n = Integer.parseInt(t);

			b.add(n);

		}

		arrêtes.add(new Arrête(b.get(0), b.get(1), b.get(2)+25));

	}

    

    BufferedReader brr;

    String motLuu;

    ArrayList<Integer> lignesbis=new ArrayList<Integer>();

    List<String> nombis = new ArrayList<String>();

    try{

        brr = new BufferedReader(new FileReader(new File("./src/application/Stations1.txt")));

        while((motLuu = brr.readLine()) != null)

        {

        if (motLuu.charAt(motLuu.length()-2) ==';'){

        int monEntier = Integer.parseInt(motLuu.charAt(motLuu.length()-1)+"");

        lignesbis.add(monEntier);

        nombis.add(motLuu.substring(0,motLuu.length()-2 ));

        

        	

        }

        else {

        	int monEntier=Integer.parseInt(motLuu.charAt(motLuu.length()-1)+"") + (Integer.parseInt(motLuu.charAt(motLuu.length()-2)+"")*10) ;

            lignesbis.add(monEntier);

            nombis.add(motLuu.substring(0,motLuu.length()-3 ));

        }

        

      

        }

        brr.close();

    }catch(FileNotFoundException e){

        e.printStackTrace();

    }catch(IOException e){

        e.printStackTrace();

    }

    List<String>nom= nombis;

	   

    int [] lignes= new int [lignesbis.size()];

    for (int f=0;f<lignesbis.size();f++) {

    	lignes[f]=lignesbis.get(f);

    }


	Graphe g = new Graphe(arrêtes);

	g.donnenom(nom);

	g.attribueligne(lignes);
	
     
	int w=0;



	int z=0;

	int k=0;

	int u=100000000;



	while (w<g.nombredeligne(départ)) {



		int y=0;

		while (y<g.nombredeligne(arrivée)) {

			Graphe e = new Graphe(arrêtes);


			
			e.donnenom(nom);

			e.attribueligne(lignes);

			e.calculpluscourtedistance(départ,arrivée,w,y);

			int m=e.sommets[e.stringtoint(arrivée) + y].getdistanceàlasource();



			if (m<u) {

				u=m;

				k=w;

				z=y;

			}

			y=y+1;

		}

		w=w+1;

	}



	g.calculpluscourtedistance(départ,arrivée,k,z);



	g.calculerchemin();

	ArrayList<Sommet> path = g.getchemin();

	ArrayList<String> h =g.transformation();

	Collections.reverse(h);

	Collections.reverse(path);
	if(!h.contains(arrivée)) {
		h.add(arrivée);
	}
	
	System.out.println(h.equals(itinéraires.get(i)));
}

	}

}
