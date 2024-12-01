import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {
public static void main(String[] args){
    Index i=new Index("dataset.csv");
    Invertedindex ii=new Invertedindex(i);
    InvertedindexBST iib=new InvertedindexBST(ii);
    QueryProcessing q =new QueryProcessing(i,ii,iib);
    Ranking ranking=new Ranking(i,ii,iib);
    
    //System.out.println(iib.bst.findkey("national").retrieve());
    
        //ranking.invertedindexBSTRanking("weather warming");
        q.IndexQuery("market OR sports AND warming");
      //i.findIndexes("market");
      //System.out.println(i.findIndexes("market"));
     // display(i.findIndexes("warming"));
  //System.out.println(ii.freq(3, "green"));
   // i.print_document(1);
     //q.Query("market OR sports AND warming"); //case 1
    //i.print_document(1);
    //i.print_all_document();
    //System.out.println(i.vocab);
      //display(i.findIndexes("green"));
   // q.Query("record");//case 2
    //ii.display();
   //iib.display();
    
 
     }
     public static void display(LinkedList<Integer> ll){
      ll.findFirst();
      while (!ll.last()) {
        System.out.print(ll.retrieve()+",");
        ll.findNext();
      }
      System.out.println(ll.retrieve());
     } 
}
