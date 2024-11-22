public class Test {
public static void main(String[] args) {
    
LinkedList<DocumentScore> ll=new LinkedList<DocumentScore>();

DocumentScore documentScore=new DocumentScore(1);
DocumentScore documentScore2=new DocumentScore(2);
DocumentScore documentScore3=new DocumentScore(3);

// documentScore.incress_score(2);
// documentScore2.incress_score(4);
// documentScore3.incress_score(6);

documentScore.insertInArrange(ll);
documentScore2.insertInArrange(ll);
documentScore3.insertInArrange(ll);
// ll.insert(documentScore);ll.insert(documentScore2);ll.insert(documentScore3);
removeFromScorelLinkedList(2, ll);
ll.findFirst();
while(!ll.last()){
    System.out.println(ll.retrieve().docID);
    ll.findNext();
}   
System.out.println(ll.retrieve().docID);
}
public static void removeFromScorelLinkedList(int docID,LinkedList<DocumentScore> ll){
    if(!ll.empty()){
        ll.findFirst();
        while(!ll.last()){
            if(docID==ll.retrieve().docID)
                ll.remove();
                if(ll.last()) return;
            ll.findNext();    
        }
        if(docID==ll.retrieve().docID)
         ll.remove();
    }
}

}
