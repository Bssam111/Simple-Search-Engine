import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {
public static void main(String[] args) throws FileNotFoundException {
    Index i=new Index("dataset.csv");
    Invertedindex ii=new Invertedindex(i);
    InvertedindexBST iib=new InvertedindexBST(ii);
    QueryProcessing q =new QueryProcessing(ii);

   // i.print_document(1);
     q.Query("market OR sports AND warming"); //case 1
    //i.print_document(1);
   // i.print_all_document();
   // q.Query("record");//case 2
    //ii.display();
   //iib.display();
    
 
     }
}
