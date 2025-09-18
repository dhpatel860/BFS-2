/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

 /*
 *Approach 1: 
 - Level order traversal using queue, where add the last most element of that level in the result
 - TC: O(n) -> iterate through all the elements in the tree
 - SC: O(n) -> technically max is n/2 asymptotically O(n) as that would be the max number of leaf nodes are possible in binary tree, for skewed BT it could be O(1)
 * 
  */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        
        List<Integer> res = new ArrayList<>();
        if(root == null)
            return res;
        Queue<TreeNode> q = new LinkedList<>();
        
        q.add(root);

        while(!q.isEmpty()){
            // size variable to keep track of the level
            int size = q.size();


            // level by level processing of nodes and the last element in that level will be visible if seen the tree from the right side
            for(int i = 0; i < size; i++){
                TreeNode curr = q.poll();
                // for left side view we need to get the first element on that level as that will be visible if the tree is seen from the left side
                // if(i == 0)
                //     res.add(curr.val);
                if(i == size - 1)
                    res.add(curr.val);

                // if left and right babies are present, add them to the queue
                if(curr.left != null)
                    q.add(curr.left);
                if(curr.right != null)
                    q.add(curr.right);
            }
        }
        return res;
        
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

 /*
 * Approach2:
 - Use recursion to iterate over the nodes 
    1. left recursive call first -> when res.size == level add the node to the list else overwrite when you see the further right element as right call is done after the left is complete
    2. right recursive call first -> when res.size == level, add that node to the list
- TC: O(n) -> iterate over all the nodes in the tree
- SC: O(h) -> at max height of the tree elements in the recursive stack, worst case O(n) for skewed tree
  */
class Solution {
    List<Integer> res;
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null)
            return new ArrayList<>();
        this.res = new ArrayList<>();
        // List<Integer> res = new ArrayList<>();
        helper(root, 0);
        return res;
    }

    private void helper(TreeNode root, int level){
        //base
        if(root == null)
            return;
        //logic
        // right is called first, else part is added if left recursion is called first and the sequence of the helper function changes
        if(res.size() == level)
            res.add(root.val);
        // else
        //     res.set(level, root.val);
       
        helper(root.right, level + 1);
        helper(root.left, level + 1);
    }
}