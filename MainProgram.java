import java.util.Iterator;

public class MainProgram {
    public static void main(String[] args)
    {
        LinkedBinaryTree<String> D = new LinkedBinaryTree<String>("D", null, null);
        LinkedBinaryTree<String> E = new LinkedBinaryTree<String>("E", null, null);
        LinkedBinaryTree<String> B = new LinkedBinaryTree<String>("B", D, E);
        LinkedBinaryTree<String> C = new LinkedBinaryTree<String>("C", null, null);
        LinkedBinaryTree<String> A = new LinkedBinaryTree<String>("A", B, C);

        Iterator<String> it = A.iteratorLevelOrder();

        while(it.hasNext())
        {
            String result = it.next();
            System.out.println(result+" ");
        }
    }
}
