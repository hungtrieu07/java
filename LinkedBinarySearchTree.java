public class LinkedBinarySearchTree<T extends Comparable<T>> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T>
{
    public LinkedBinarySearchTree()
    {
        super();
    }

    public LinkedBinarySearchTree(T element)
    {
        super(element, null, null);
    }

    @Override
    public void addElement(T element) 
    {
        BinaryTreeNode<T> temp = new BinaryTreeNode<T>(element);
        if(isEmpty())
        {
            root = temp;
        }    
        else
        {
            BinaryTreeNode<T> current = root;
            boolean added = false;
            while(!added)
            {
                if(element.compareTo(current.getElement())<0)
                {
                    if(current.getLeft()==null)
                    {
                        current.setLeft(temp);
                        added = true;
                    }
                    else
                        current = current.getLeft();
                }
                else
                {
                    if(current.getRight()==null)
                    {
                        current.setRight(temp);
                        added = true;
                    }
                    else
                        current = current.getRight();
                }
            }
        }
        count++;
    }

    @Override
    public T removeElement(T element) 
    {
        T result = null;
        if(!isEmpty())
        {
            if(element.compareTo(root.getElement())==0)
            {
                result = root.getElement();
                root = replacement(root);
                count--;
            }
            else
            {
                BinaryTreeNode<T> current, parent = root;
                Boolean found = false;

                if(element.compareTo(root.getElement())<0)
                {
                    current = root.getLeft();
                }
                else
                {
                    current = root.getRight();
                } 

                while(!found && current != null)
                {
                    if(element.compareTo(current.getElement())==0)
                    {
                        result = current.getElement();
                        found = true;
                        count--;
                        if(parent.getLeft() == current)
                            parent.setLeft(replacement(current));
                        else if(parent.getRight()==current)
                            parent.setRight(replacement(current)); 
                    }
                    else
                    {
                        parent = current;
                        if(element.compareTo(current.getElement())<0)
                            current = current.getLeft();
                        else
                            current = current.getRight();
                    }
                }

                if(!found)
                    throw new ElementNotFoundException("Linked Binary Tree");
            }
        }    
        return result;
    }

    private BinaryTreeNode<T> replacement(BinaryTreeNode<T> node)
    {
        BinaryTreeNode<T> result = null;

        if(node.getLeft() != null && node.getRight() == null)
            result = null;
        else if(node.getLeft() != null && node.getRight() == null)
            result = node.getLeft();
        else if(node.getRight() != null && node.getLeft() == null)
            result = node.getRight();
        else
        {
            BinaryTreeNode<T> current = node.getRight();
            BinaryTreeNode<T> parent = node;
            while(current.getLeft() != null)
            {
                parent = current;
                current = current.getLeft();
            }

            if(node.getRight() == current)
            {
                current.setLeft(node.getLeft());
            }
            else
            {
                parent.setLeft(current.getRight());
                current.setLeft(node.getLeft());
                current.setRight(node.getRight());
            }
            result = current;
        }

        return null;
    }
}
