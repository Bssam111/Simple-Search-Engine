public class TestBST {
    public static void main(String[] args) {
        BST<LinkedList<Integer>> bst=new BST<LinkedList<Integer>>();
        LinkedList<Integer> s=new LinkedList<>();
        s.insert(1);
        bst.inseart(s, "5");
        bst.inseart(s, "6");
        bst.inseart(s, "1");
        bst.inseart(s, "2");
        bst.inseart(s, "7");
        bst.inseart(s, "8");
       System.out.println((( LinkedList<Integer>)(bst.findWord("5").data)).retrieve());
   // System.out.println(bst.findkey("7").retrieve());
    }
}
