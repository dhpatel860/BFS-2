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
 * cousins are defined as
    1. different parent
    2. same depth
* Approach 1: 
- Go level by level to check if we have found x or y
- if x and y are found, check their depth and their parent -> maintain parentqueue and child queue, one by one mapping. 
- TC:O(n) -> iterate over all the nodes
- SC: O(n) -> queue to maintain all the nodes at that level. at max n/2 leaf nodes -> asymptotically O(n)
  */
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> cq = new LinkedList<>();
        Queue<TreeNode> pq = new LinkedList<>();
        TreeNode px = null;
        TreeNode py = null;
        boolean x_found = false;
        boolean y_found = false;

        cq.add(root);
        pq.add(null);

        while(!cq.isEmpty()){
            int size = cq.size();

            for(int i = 0; i < size; i++){
                TreeNode curr = cq.poll();
                TreeNode parent = pq.poll();

                // check if the curr.val is either x or y if not add those nodes along with their parents in the queue
                if(curr.val != x && curr.val != y){
                    if(curr.left != null){
                        cq.add(curr.left);
                        pq.add(curr);
                    }
                    if(curr.right != null){
                        cq.add(curr.right);
                        pq.add(curr);
                    }
                }

                // if either is found, update the flag and parent
                else{
                    if(curr.val == x){
                        x_found = true;
                        px = parent;
                    }
                    if(curr.val == y){
                        y_found = true;
                        py = parent;
                    }
                }
            }

            // once the level is finished we check if both x and y are found, if so check their parents and return result accordingly
            if(x_found && y_found)
                return px != py;
            // if only one either x or y is found that means they are not cousins, return false
            if(x_found || y_found)
                return false;
        }
        // we will never reach here so return either true or false doesnt matter
        return false;
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
* cousins are defined as
   1. different parent
   2. same depth
Approach 1: 
- Go level by level to check if we have found x or y
- if x and y are found, check their depth and their parent -> maintain parentqueue and child queue, one by one mapping. 

-> the above can be further optimized, as we dont need to maintain the parent queue, instead we do the check one level above
- TC:O(n) -> iterate over all the nodes
- SC: O(n) -> queue to maintain all the nodes at that level. at max n/2 leaf nodes -> asymptotically O(n)
 */
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> q = new LinkedList<>();
        boolean x_found = false;
        boolean y_found = false;

        q.add(root);

        while(!q.isEmpty()){
           
            int size = q.size();

            for(int i = 0; i < size; i++){
                TreeNode curr = q.poll();

                if(curr.left != null && curr.right != null){
                    if(curr.left.val == x && curr.right.val == y || curr.left.val == y && curr.right.val == x)
                        return false;
                }

                if(curr.val == x){
                    x_found = true;
                }
                if(curr.val == y){
                    y_found = true;
                }

                if(curr.left != null)
                    q.add(curr.left);
                if(curr.right != null)
                    q.add(curr.right);
            }
            if(x_found && y_found)
                return true;
            if(x_found || y_found)
                return false;
        }
        return false;
    }
}