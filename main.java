import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {
public static void main(String[] args) throws FileNotFoundException {
    Index i=new Index("dataset.csv");
    Invertedindex ii=new Invertedindex(i);


//    Reading r=new Reading();
//    r.load("dataset.csv", i.document);
    

   //i.DisplayDocument(0);
ii.SearchForWord("shift");
   
  
    }
}
