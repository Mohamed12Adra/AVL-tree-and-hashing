import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
//Driver class
public class Driver extends Application{
	//implementing the abstract method start
	public void start(Stage s) {
		//border pane
		BorderPane p1 = new BorderPane();
		//setting the padding and style
		p1.setPadding(new Insets(0, 0, 25, 0));
		p1.setStyle("-fx-font-size:15px;");
		//labels
		Label l1 = new Label("Enter Country For Hashing");
		Label l5 = new Label("Search Country");
		Label l6 = new Label("Search City");
		//buttons
		Button b7 = new Button("Print Hash Tabel Size");
		Button b8 = new Button("Print Hash Tabel Function");
		//label and text field
		Label l2 = new Label("Delete country");
		TextField t1 = new TextField();
		//grid pane
		GridPane p2 = new GridPane();
		//buttons
		Button b1 = new Button("Print hash");
		Button b2 = new Button("Print AVL");
		//text area
		TextArea ta1 = new TextArea();
		//button and flow pane
		Button b3 = new Button("Enter the file");
		FlowPane h3 = new FlowPane(15, 15);
		//setting the padding for the flow pane and and the alignment and adding the buttons b1 and b2
		h3.setPadding(new Insets(25, 0, 0, 0));
		h3.getChildren().addAll(b1,b2);
		h3.setAlignment(Pos.CENTER);
		//text field and label
		TextField t4 = new TextField();
		Label l4 = new Label("Add country");
		//adding to grid pane
		p2.add(b3,0,0,2,1);
		p2.add(l4,0,1);
		p2.add(t4, 1, 1);
		//array list to store values from the input file
		ArrayList<Country> x1 = new ArrayList<>();
		ArrayList<City> x2 = new ArrayList<>();
		//avl tree
		Avl avl = new Avl();
		ArrayList<HashTable> x3 = new ArrayList<>();
		//set on action method for button b3
		b3.setOnAction(e->{
			//exception handling
			try {
			//file chooser to browse for the file
				FileChooser fc = new FileChooser();
				File file = fc.showOpenDialog(s);
				//opening the file
				Scanner in = new Scanner(file);
				while(in.hasNextLine()) {
					String str = in.nextLine();
					String[] arr = str.split("=");
					Country c = new Country(arr[2]);
					City c1 = new City(arr[1],Double.parseDouble(arr[3]),arr[2]);
					x1.add(c);
					x2.add(c1);
				}
				//adding countries to the tree
				for(int i=0; i<x1.size(); i++) {
					if(avl.search(x1.get(i))==false) {
						avl.insert(x1.get(i));
					}else {
						continue;
					}
					//adding cities to their countries
					for(int j=0; j<x2.size(); j++) {
						if(x2.get(j).getCountryName().contains(x1.get(i).getCountryName())) {
							x1.get(i).addCity(x2.get(j));
						}
					}
				}
				//empty hashtable 
				HashTable ht1 = new HashTable(x2.size());
				x3.add(ht1);
				in.close();
			}catch(FileNotFoundException x) {
			System.out.println("cant find the file");
			}
		});
		//set on action method for button b2
		b2.setOnAction(e->{
			avl.deleteX();
			avl.Tree();
			ta1.setText(avl.toString());
		});
		//set on action method for button b1
		b1.setOnAction(e->{
			try {
				PrintWriter out = new PrintWriter("result.txt");
				out.println(x3.get(0).returnHash());
				out.close();
			}catch(FileNotFoundException x) {
			System.out.println("error");
			}
		});
		//set on action method for textfield t1
		t1.setOnAction(e->{
			String str = t1.getText();
			Country c = new Country(str);
			if(avl.search(c)==true) {
				avl.delete(c);
				for(int i=0; i<x1.size(); i++) {
					if(x1.get(i).getCountryName().trim().toLowerCase().contains(c.getCountryName().trim().toLowerCase())) {
						x1.remove(i);
						break;
					}
				}
				ta1.setText(c.getCountryName()+" is deleted");
			}else {
				ta1.setText("the country "+c.getCountryName()+" is not found in the AVL tree");	
			}
		
		});
		//text field
		TextField t2 = new TextField();
		//set on action method for text field t2 
		t2.setOnAction(e->{
			String str = t2.getText().trim().toLowerCase();
			for(int i=0; i<x1.size(); i++) {
				if(x1.get(i).getCountryName().trim().toLowerCase().contains(str)) {
					for(int j=0; j<x1.get(i).getCities().getCount(); j++) {
						City o = (City)x1.get(i).getCities().Search(j).getData();
						if(x3.get(0).search(o.getCityName())==false) {
							x3.get(0).addRecord(o);
						}
					}
					ta1.setText("Successfull");
					break;
				}
			}
			
		});
		//textfield and label
		TextField t3 = new TextField();
		Label l3 = new Label("Delete city");
		//set on action method for text field t3
		t3.setOnAction(e->{
			String str = t3.getText().trim().toLowerCase();
			if(x3.get(0).search(str)==true) {
				City x = new City(str);
				x3.get(0).delete(x);
				ta1.setText(x.getCityName()+" is deleted");
			}else {
				ta1.setText("There Is No City With This Name");
			}
		});
		//set on action method for t4
		t4.setOnAction(e->{
			String[] str=t4.getText().split("=");
			Country c = new Country(str[2]);
			City c1 = new City(str[1],Double.parseDouble(str[3]),str[2]);
			c.addCity(c1);
			if(avl.search(c)==false) {
				avl.insert(c);
				x1.add(c);
				x2.add(c1);
				x3.get(0).setTableSize(x2.size());
				ta1.setText(c.getCountryName()+" is inserted");
			}else {
				ta1.setText("already inserted");
			}
		});
		//button 
		Button b4 = new Button("Calculate Height Of Tree");
		//set on action method for b4
		b4.setOnAction(e->{
			ta1.setText(avl.calculateHeightTree(avl.getRoot())+"");
		});
		//text field 
		TextField t5 = new TextField();
		//set on action method for t5
		t5.setOnAction(e->{
			Country o = new Country(t5.getText());
			if(avl.search(o)==true)
				ta1.setText(o.getCountryName()+" is found");
			else
				ta1.setText(o.getCountryName()+" is not found");
		});
		//text field
		TextField t6 = new TextField();
		//set on action method for text field t6
		t6.setOnAction(e->{
			City o = new City(t6.getText());
			if(x3.get(0).search(o.getCityName())==true)
				ta1.setText(o.getCityName()+" is found");
			else
				ta1.setText(o.getCityName()+" is not found");
		});
		//set on action method for button b7
		b7.setOnAction(e->{
			ta1.setText("the size is "+x3.get(0).returnSize());
		});
		//set on action method for button b8
		b8.setOnAction(e->{
			ta1.setText("f(x)=h(x)+i^2 , i>0 , quadratic probing, h(x)= (hashval<<5)+ascii for String.charAt(i++)");
		});
		//set on action method for button b9
		Button b9 = new Button("Print Reports");
		b9.setOnAction(e->{
			try {
				avl.deleteX();
				avl.Tree();
				PrintWriter out = new PrintWriter("report.txt");
				out.println("AVL Countries");
				out.println(avl.toString());
				out.println(avl.calculateHeightTree(avl.getRoot()));
				out.println("====================================");
				out.println("Hash Table Cities");
				out.println(x3.get(0).returnHash());
				out.println("Size="+x3.get(0).returnSize());
				out.println("hash function-> "+" f(x)=(h(x)+i^2)%tablesize , i>0 , quadratic probing");
				out.close();
			}catch(FileNotFoundException p) {
				System.out.println("file not found");
			}
		});
		//button
		Button b10 = new Button("Print Countries And Their Cities");
		//set on action method for button b10
		b10.setOnAction(e->{
			try {
				PrintWriter out = new PrintWriter("Report_Countries.txt");
				for(int i=0; i<x1.size(); i++) {
					if(x1.get(i).getCities().getCount()!=0) {
						out.println("Country: "+x1.get(i).getCountryName()+" , total number of tourists: "+x1.get(i).getTotalNumOfTourists());
						for(int j=0; j<x1.get(i).getCities().getCount(); j++) {
							City c = (City)x1.get(i).getCities().Search(j).getData();
							out.println(c.getCityName()+" "+c.getNumOfTourists());
						}
						out.println("====================================================================================");
					}
				}
				out.close();
			}catch(FileNotFoundException t) {
				System.out.println("file not found");
			}
		});
		//label and text field
		Label l9 = new Label("Add External City");
		TextField t9 = new TextField();
		//set on action method for text field t9
		t9.setOnAction(e->{
			String str = t9.getText();
			if(x3.get(0).search(str)==false) {
				City c = new City(str);
				x3.get(0).addRecord(c);
				ta1.setText(c.getCityName()+" is inserted");
			}else {
				ta1.setText("already inserted");
			}
		});
		//button
		Button b11 = new Button("Clear Tree");
		//set on action method for button b11
		b11.setOnAction(e->{
			avl.makeEmpty();
		});
		//adding button b4 and b7 and b8 to flow pane
		h3.getChildren().addAll(b4,b7,b8);
		//hbox and setting allignment for hbox and adding buttons b9 and b10 and b11
		HBox h2 = new HBox();
		h2.setAlignment(Pos.CENTER);
		h2.getChildren().addAll(b9,b10,b11);
		//adding and setting alignment and padding for grid pane
		p2.add(l2,0,2);
		p2.setAlignment(Pos.CENTER);
		p2.setPadding(new Insets(20, 20 , 20, 20));
		p2.add(t1, 1,2);
		p2.add(l1,0 ,3);
		p2.add(t2, 1, 3);
		p2.add(l3, 0, 4);
		p2.add(t3, 1, 4);
		p2.add(l5, 0, 5);
		p2.add(t5, 1, 5);
		p2.add(l6, 0, 6);
		p2.add(t6, 1, 6);
		p2.add(l9, 0, 7);
		p2.add(t9, 1, 7);
		p2.add(ta1, 0, 8,2,1);
		p2.add(h2, 0, 9,2,1);
		//setting the gap for grid pane
		p2.setVgap(10);
		//setting the center of border pane
		p1.setCenter(p2);
		//setting the bottom of the border pane
		p1.setBottom(h3);
		//scene
		Scene scene = new Scene(p1,700,700);
		s.setScene(scene);
		s.setTitle("Project 3");
		s.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
