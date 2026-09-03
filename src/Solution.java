package src;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {

        Queue<TreeNode> queueP = new LinkedList<>();
        Queue<TreeNode> queueQ = new LinkedList<>();

        queueP.add(p);
        queueQ.add(q);

        while(!queueP.isEmpty() && !queueQ.isEmpty()){

            TreeNode nodeP = queueP.poll();
            TreeNode nodeQ = queueQ.poll();

            if(nodeP == null && nodeQ == null){
                continue;
            }

            if(nodeP == null || nodeQ == null){
                return false;
            }

            if(nodeP.val != nodeQ.val){
                return false;
            }

            queueP.add(nodeP.left);
            queueP.add(nodeP.right);

            queueQ.add(nodeQ.left);
            queueQ.add(nodeQ.right);
        }

        return queueP.isEmpty() && queueQ.isEmpty();




    }
}
