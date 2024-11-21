public class DocumentScore{
    int docID;
    int score;

    public DocumentScore(int docID) {
        this.docID = docID;
    }
    public void incress_score(){
        score++;
    }
    public void insertInArrange(LinkedList<DocumentScore> list) {
        if (list.empty()) {
            return;
        }

        list.findFirst();
        while (!list.last()) {
            if (this.score > list.retrieve().score) {//اذا السكور الحالي اكبر من حق النود يضيف مباشره اذا لا يمشي لين يلقى شي اكبر منه
                list.insert(this);
                return;
            }
            list.findNext();
        }

        if (this.score > list.retrieve().score) {//خاص لاخر نود
            list.insert(this);
        } else {
            list.findNext();
            list.insert(this);
        }
    }

    
    public void display() {
        System.out.println("Document ID: " + docID + ", Score: " + score);
    }
}
}
