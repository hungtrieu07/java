import java.util.Iterator;

public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {
    protected int count;
    protected BinaryTreeNode<T> root;
    // protected BTNode<T> root;

    public LinkedBinaryTree()
    {
        count = 0;
        root = null;
    }

    public LinkedBinaryTree(T element, LinkedBinaryTree<T> left, LinkedBinaryTree<T> right)
    {
        count = 1;
        root = new BinaryTreeNode<T>(element);
        // root = new BTNode<T>(element);
        if(left != null)
        {
            count = count + left.size();
            root.setLeft(left.root);
        }
        else
            root.setLeft(null);

        if(right != null)
        {
            count = count + right.size();
            root.setRight(right.root);
        }
        else
            root.setRight(null);
    }

    public void removeLeftSubtree()
    {
        if(root.left != null)
            count = count - root.left.numChildren()-1;
        root.left = null;
    }

    public void removeRightSubtree()
    {
        if(root.right != null)
            count = count - root.right.numChildren()-1;
        root.right = null;
    }

    public void removeAllElements()
    {
        count = 0;
        root = null;
    }

    public int size()
    {
        return count;
    }

    public boolean isEmpty()
    {
        return (count==0);
    }

    public T getRootElement()
    {
        if (root == null)
            throw new EmptyCollectionException("LinkedBinaryTree");
        return root.element;
    }

    public BinaryTreeNode<T> getRootNode()
    {
        if (root == null)
            throw new EmptyCollectionException("LinkedBinaryTree");
        return root;
    }

    public boolean contains(T targetElement)
    {
        boolean found = false;
        try
        {
            T temp = find(targetElement);
            found = true;
        } 
        catch (ElementNotFoundException ex)
        {
            found = false;
        }

        return found;
    }

    public  T find(T targetElement)
    {
        BinaryTreeNode<T> result = findNode(targetElement, root);
        if (result == null)
            throw new ElementNotFoundException("Linked Binary Tree");
        return result.getElement();
    }

    public BinaryTreeNode<T> findNode(T targetElement, BinaryTreeNode<T> next)
    {
        if(next == null)
            return null;

        if (next.getElement().equals(targetElement))
            return next;

        BinaryTreeNode<T> left = findNode(targetElement, next.getLeft());
        if (left != null)
            return left;

        BinaryTreeNode<T> right = findNode(targetElement, next.getRight());
        if (right != null)
            return right;
        
        return null;
    }

    public String toString() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        iteratorLevelOrder();
        return templist.toString();
    }

    // public Iterator<T> iteratorInOrder()
    // {
    //     ArrayList<T> temp = new ArrayList<T>();
    //     inOrder(root, temp);
    //     return temp.iterator();
    // }


    // public void inOrder(BinaryTreeNode<T> root, ArrayList<T> temp)
    // {
    //     if (root != null)
    //     {
    //         inOrder(root.left, temp);
    //         temp.add(root.element);
    //         inOrder(root.right, temp);
    //     }
    // }

    public Iterator<T> iteratorLevelOrder()
    {
        ArrayUnorderedList<T> nodes = new ArrayUnorderedList<T>();
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        BinaryTreeNode<T> current;

        nodes.addToRear(root.element);
        while(!nodes.isEmpty())
        {
            current = (BinaryTreeNode<T>)nodes.removeFirst();
            if(current!=null)
            {
                templist.addToRear(current.element);
                nodes.addToRear(current.left.element);
                nodes.addToRear(current.right.element);
            }
            else
                templist.addToRear(null);
        }
        return templist.iterator();
        // LinkedQueue<BinaryTreeNode<T>> queue = new LinkedQueue<BinaryTreeNode<T>>();
        // ArrayIterator<T> iter = new ArrayIterator<T>();

        // if(root != null)
        // {
        //     queue.enqueue(root);
        //     while(!queue.isEmpty())
        //     {
        //         BinaryTreeNode<T> current = queue.dequeue();

        //         iter.add(current.getElement());

        //         if(current.getLeft() != null)
        //             queue.enqueue(current.getLeft());
        //         if(current.getRight() != null)
        //             queue.enqueue(current.getRight());
                
        //     }
        // }

        // return iter;
    }

    public Iterator<T> iterator()
    {
        return iteratorLevelOrder();
    }

}
