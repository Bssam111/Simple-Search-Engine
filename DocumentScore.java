public class DocumentScore{
    int docID;
    int score;

    public DocumentScore(int docID) {
        this.docID = docID;
    }
    public void incress_score(int numOfincresing){
        score+=numOfincresing;
    }
    public void insertInArrange(LinkedList<DocumentScore> list) {
        if (list.empty()) {
            list.insert(this);
            return;
        }
        DocumentScore tmp=null;
        list.findFirst();
        while (!list.last()) {
             tmp=list.retrieve();
            if (this.score>tmp.score) {
               
                list.update(this);
                list.insert(tmp);
                return;
            }
            else if(this.score==tmp.score&&this.docID<tmp.docID){
                    list.update(this);
                    list.insert(tmp);
                    return;
            }
            list.findNext();
        }
         tmp=list.retrieve();
        if (this.score>tmp.score) {
            list.update(this);
            list.insert(tmp);
        }
        else if(this.score==tmp.score){
            if(this.docID<tmp.docID){
                list.update(this);
                list.insert(tmp);
                return;
            } else list.insert(this);
        }
         
      
    }

    
    public void display() {
        String s= docID + "         ";
        if(docID<10) s=s+" ";
        s+=score;
        System.out.println(s);
    }
}

