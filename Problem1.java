// Time Complexity :  O(n)
// Space Complexity :  O(n+h)
// Did this code successfully run on Leetcode :  Yes

// Make an inorder traversal of the tree and store node values to a list.
//check if the list is in ascending order.


class Solution1 {

    private List<Integer> list;

    private void inOrder(TreeNode root){
        if(root==null){
            return;
        }
        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
    }
 

    public boolean isValidBST(TreeNode root) {

        list= new ArrayList<>();
        
        inOrder(root);

        for(int i=1; i<list.size(); i++){
            if(list.get(i)<=list.get(i-1)){
                return false;
            }
        }
        return true;
    }
}


// Time Complexity :  O(n)
// Space Complexity :  O(h)
// Did this code successfully run on Leetcode :  Yes

// Make an inorder traversal of the tree.
// store the inoreder predecessor value in a global variable.
// Compare it with the current node val.
// if lesser then go further and update global variable and move to next node. else return false;



class Solution2 {

    private long preVal;
    private boolean flag;

    private void inOrder(TreeNode root){
        
        if(root==null || !flag){
            return;
        }

        inOrder(root.left);

        if(root.val<=preVal){
            flag=false;
            return;
        }

        preVal=root.val;

        inOrder(root.right);
    }
 

    public boolean isValidBST(TreeNode root) {

        preVal= Long.MIN_VALUE;
        flag=true;
        inOrder(root);
        return flag;
    }
}

// Time Complexity :  O(n)
// Space Complexity :  O(h)
// Did this code successfully run on Leetcode :  Yes

// Different from solution2 in a way that we are not maintaining a global flag boolean
// instead we will validate left if ok go for curr and then if ok go right and return true if all are true else false;
// So we will call recursivelt at each node.




class Solution3 {

    private long preVal;

    private boolean check(TreeNode root){

        //base
        if(root==null){
            return true;
        }

        //left
        boolean left= check(root.left);

        if(left){
            //node
            boolean curr= root.val>preVal;
            preVal=root.val;

            if(curr){
                //right
                boolean right= check(root.right);
                return right;
            }
        }
        return false;
    }

    public boolean isValidBST(TreeNode root) {

        preVal= Long.MIN_VALUE;
        
        return check(root);
    }
}



// Time Complexity :  O(n)
// Space Complexity :  O(h)
// Did this code successfully run on Leetcode :  Yes

// Maintian a stack and iteratively add and pop nodes to and from the stack and
// simultaneously check if the popped value is greter than previous. If not then not a valid BST



class Solution4 {
    public boolean isValidBST(TreeNode root) {
        Long preVal= Long.MIN_VALUE;
        Stack<TreeNode> myStack= new Stack<>();
        while(root.left!=null){
            myStack.push(root);
            root=root.left;
        }
        myStack.push(root);
        while(!myStack.isEmpty()){
            TreeNode popped= myStack.pop();
            if(popped.val<=preVal){
                return false;
            }
            preVal= (long)popped.val;
            TreeNode curr= popped.right;
            if(curr==null){
                continue;
            }
            while(curr.left!=null){
                myStack.push(curr);
                curr=curr.left;
            }
            myStack.push(curr);
        }
        return true;
    }
}


// Time Complexity :  O(n)
// Space Complexity :  O(h)
// Did this code successfully run on Leetcode :  Yes

// Same as above solution except that we will maintain a reference instead of a Long.Min value
// to handle cases when root is Long.Min


class Solution5 {
    public boolean isValidBST(TreeNode root) {

        TreeNode prev=null;

        Stack<TreeNode> myStack= new Stack<>();

        while(root!=null || !myStack.isEmpty()){

            while(root!=null){
                myStack.push(root);
                root=root.left;
            }

            root=myStack.pop();

            if(prev!=null && root.val<=prev.val){
                return false;
            }

            prev=root;

            root=root.right;
        }
        return true;
    }
}


// Time Complexity :  O(n)
// Space Complexity :  O(h)
// Did this code successfully run on Leetcode :  Yes

// We will specify a range of values for a each node based on it's parent's value.
// We will check each node if it is in its specified range. If not return false;
// range of left child would be (min,node.val) and right child would be (node.val,max)



class Solution6 {

    public boolean isValidBST(TreeNode root) {
        return valid(root,null,null);
    }

    private boolean valid(TreeNode root, Integer min, Integer max){

        if(root==null){
            return true;
        }
        
        if((min!=null && root.val<=min) || (max!=null && root.val>=max)){  // min and max are wraper classes so can be null
            return false;
        }

        return valid(root.left, min, root.val) && valid(root.right, root.val, max);
    }
}