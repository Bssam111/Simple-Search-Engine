public class InvertedindexBST {
public BST<LinkedList<Integer>> bst;
Invertedindex invertedindex;
public InvertedindexBST(Invertedindex invertedindex) {
    bst=new BST<LinkedList<Integer>>();
    this.invertedindex=invertedindex;
    invert_index_BST();
}
public void invert_index_BST(){
    if(!invertedindex.words.empty()) {
        invertedindex.words.findFirst();
        while(!invertedindex.words.last()){
            bst.inseart(invertedindex.words.retrieve().indexs, invertedindex.words.retrieve().word);
            invertedindex.words.findNext();
        }
        bst.inseart(invertedindex.words.retrieve().indexs, invertedindex.words.retrieve().word);
    }
}
public int freq(int DocID,String word){
    return invertedindex.freq(DocID, word);
}
public void display(){
bst.inOrder();
}
}
