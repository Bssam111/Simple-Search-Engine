import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {
public static void main(String[] args) throws FileNotFoundException {
    Index i=new Index("dataset2.csv");
    Invertedindex ii=new Invertedindex(i);
    InvertedindexBST iib=new InvertedindexBST(ii);

    //ii.display();
    iib.display();
  
    }
}
