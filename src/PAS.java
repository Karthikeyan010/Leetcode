package src;


import java.util.*;

public class PAS {
    public static int romanToInt(String s) {
        char[] str =s.toCharArray();

        LinkedHashMap<String, Integer> romanValues=new  LinkedHashMap<String, Integer>();
        romanValues.put("I" , 1);
        romanValues.put("IV" , 4);
        romanValues.put("V" , 5);
        romanValues.put("IX" , 9);
        romanValues.put("X" , 10);
        romanValues.put("XL" , 40);
        romanValues.put("L" , 50);
        romanValues.put("XC" , 90);
        romanValues.put("C" , 100);
        romanValues.put("CD" , 490);
        romanValues.put("D" , 500);
        romanValues.put("CM" , 900);
        romanValues.put("M" , 1000);
        int result= 0;
        for(int i=0;i< str.length;i++){
            String k="";
            if(i<str.length-1) {
                  k = "" + str[i] + str[i + 1];
            }
            if(romanValues.containsKey(k)){
                result=result+romanValues.get(k);
                i=i+1;

            }else{
                result=result + romanValues.get(""+str[i]);
            }

        }

        return result;



    }

    public static String longestCommonPrefix(String[] strs) {
        // Edge case
        if (strs == null || strs.length == 0) {
            return "";
        }

        // Step 1: Take first string as prefix
        String prefix = strs[0];

        // Step 2: Compare with all other strings
        for (int i = 1; i < strs.length; i++) {

            // Step 3: Reduce prefix until it matches start of current string
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);

                // If prefix becomes empty → no common prefix
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }

        return prefix;
    }


    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result= new ArrayList<>();
        Arrays.sort(nums);
        for(int i =0; i< nums.length-2;i++){
            if(i>0 && nums[i]==nums[i-1] ){
                continue;
            }

            int left = i+1;
            int right = nums.length-1;



            while(left< right){
                int sum= nums[i]+ nums[left]+ nums[right];

                if(sum==0){
                    result.add(Arrays.asList(nums[i],nums[left], nums[right]));

                    while(left<right && nums[left]==nums[left+1]){
                        left++;
                    }
                    while(left<right && nums[right]== nums[right-1]){
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum<0) {
                    left++;

                } else{
                    right--;

                }
            }
        }



    return result;
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestSum=nums[0]+ nums[1]+ nums[2];

        for(int i=0;i< nums.length-2;i++){
            int left = i+1;
            int right= nums.length-1;

            while(left<right){
                int sum= nums[i]+ nums[left]+ nums[right];
                if(Math.abs(target-sum)<Math.abs(target-closestSum)){
                    closestSum= sum;
                }
                if(sum< target){
                    left++;
                } else if (sum> target) {
                    right--;

                }else{
                    return target;
                }


            }

        }
        return closestSum;




    }

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        String map[] ={
                "",     // 0
                "",     // 1
                "abc",  // 2
                "def",  // 3
                "ghi",  // 4
                "jkl",  // 5
                "mno",  // 6
                "pqrs", // 7
                "tuv" , // 8
                "wxyz"  // 9
        };
        backtrack(result, digits, map, "",0 );
        return result;

    }

    private static void backtrack(List<String> result, String digits, String[] map, String current, int index) {
        if(index==digits.length()){
            result.add(current);
            return;
        }

        String letters= map[digits.charAt(index)-'0'];

        for(char c : letters.toCharArray()){
            backtrack(result, digits, map, current+c, index+1);

        }

    }
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0; i<nums.length-3;i++){
            if(i>0 && nums[i-1]==nums[i]){
                continue;
            }
            for(int j= i+1 ; j<nums.length-2;j++){
                if(j>i+1 && nums[j-1]==nums[j]){
                    continue;
                }

                int left = j+1;
                int right= nums.length-1;

                while (left < right){
                    int sum = nums[i]+ nums[j]+nums[left]+nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(
                                nums[i], nums[j], nums[left], nums[right]
                        ));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;

                    }if(sum < target){
                        left++;
                    } else if (sum > target) {
                        right--;

                    }
                }

            }
        }

        return result;

    }


    public static boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        for(char ch: s.toCharArray()){
            if(ch== '(' || ch=='[' || ch== '{'){
                stack.push(ch);

            }else{
                if (stack.isEmpty()){
                    return false;
                }
                char top = stack.pop();

                if(ch==')' && top !='('){
                    return false;
                }
                if(ch==']' && top != '[' ){
                    return false;
                }
                if (ch == '}' && top != '{'){
                    return false;
                }

            }
        }


        return true;

    }

    public int myAtoi(String s) {
        int i = 0, n = s.length();
        int sign = 1;
        int result = 0;

        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }



    public ListNode mergeKLists(ListNode[] lists) {
        if(lists==null || lists.length==0){
            return null;
        }
        PriorityQueue<ListNode> pq= new PriorityQueue<>((a, b) -> a.val - b.val);

        for (ListNode node: lists ){
            pq.add(node);
        }

        ListNode dummy = new ListNode(1);
        ListNode tail = dummy;

        while(!pq.isEmpty()){

            ListNode min = pq.poll();

            tail.next=min;
            tail=tail.next;

            if(min.next!=null){
                pq.add(min.next);
            }
        }




        return dummy.next;
    }





    public int divide(int dividend, int divisor) {

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        long dvd=Math.abs((long) dividend);
        long dvs=Math.abs((long) divisor);

        int result =0;
        while(dvd>=dvs) {
            long temp = dvs;
            int multiple = 1;

            while (dvd >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }

            dvd -= temp;
            result = result + multiple;
        }

        if((dividend>0) ^ (divisor>0) ){
            return result * -1;
        }

        return result;

    }


    public static List<Integer> findSubstring(String s, String[] words) {
        int word= words[0].length();
        List<Integer> result = new ArrayList<>();

        System.out.println(s);



        for(int i =0;i<s.length()-word;i=i+word){
            ArrayList<String> ar = new ArrayList<>(Arrays.asList(words));
            int k=i;
            String str= s.substring(k,k+word);
            while(ar.contains(str)){
                if(ar.isEmpty()){
                    break;
                }
                ar.remove(str);
                k=k+word;
                if(k+word > s.length()){
                    break;
                }
                str=s.substring(k,k+word);


            }


            if(ar.isEmpty()){
                result.add(i);

            }







        }




        return result;
    }

    public static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // base index

        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();

                if (stack.isEmpty()) {
                    stack.push(i); // new base
                } else {
                    int length = i - stack.peek();
                    maxLength = Math.max(maxLength, length);
                }
            }
        }

        return maxLength;
    }








    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev  = dummy;


        while(true){
            ListNode kth= getKthNode(groupPrev,k);
            if(kth==null){
                break;
            }

            ListNode groupNext= kth.next;

            ListNode curr= groupPrev.next;
            ListNode prev= groupNext;
            while(curr!= groupNext){
                ListNode temp= curr.next;
                curr.next= prev;
                prev=curr;
                curr= temp;



            }


        }


        return dummy;
    }

    private ListNode getKthNode(ListNode curr, int k) {
        while(curr != null && k>0){
            curr= curr.next;
            k--;
        }
        return curr;
    }

    public int strStr(String haystack, String needle) {
        int start=0;
        int result =0;
        for(int i=0;i< haystack.length(); i++){
            int k =i;
            while(haystack.charAt(k)==needle.charAt(start) && start<needle.length()){
                k++;
                start++;

            }
            if(start==needle.length()){
                return result;
            }else{
                start=0;

            }

        }

        return -1;

    }



    public boolean isValidSudoku(char[][] board) {

        HashSet<Character>[] rows = new HashSet[9];
        HashSet<Character>[] cols = new HashSet[9];
        HashSet<Character>[] box  = new HashSet[9];

        for(int i=0;i<9;i++){
            rows[i]= new HashSet<>();
            cols[i]= new HashSet<>();
            box[i] = new HashSet<>();
        }

        for(int i =0;i<9;i++){
            for(int j=0;j<9;j++){
                char num= board[i][j];

                if(num=='.'){
                    continue;
                }

                int boxIndex= (i/3) * 3 + (j/3);

                if(rows[i].contains(num) || cols[j].contains(num) || box[boxIndex].contains(num)){
                    return false;
                }

                rows[i].add(num);
                cols[j].add(num);
                box[boxIndex].add(num);
            }
        }
        return true;




    }





    public static int[] searchRange(int[] nums, int target) {
        int first = findFirst(nums,target);
        int second= findSecond(nums, target);
        return new int[]{first, second};

    }
    private static int findFirst(int[] nums, int target){
        int result =0;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {

                    result =mid;
                    right=mid-1;


            }
            else if (nums[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return result;

    }

    private static int findSecond(int[] nums, int target){
        int result =0;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {

                    result =mid;
                    left=mid+1;


            }
            else if (nums[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return result;

    }



    public ListNode deleteDuplicates(ListNode head) {

        ListNode dummy = new ListNode(0);
        dummy.next=head;

        ListNode prev= dummy;
        ListNode curr= head;

        while(curr != null){
            if(curr.next != null && curr.val==curr.next.val){
                int duplicate = curr.val;

                while(curr !=null && curr.val==duplicate){
                    curr=curr.next;
                }
                prev.next=curr;


            }else{
                prev=curr;
                curr=curr.next;
            }
        }




        return dummy.next;
    }


    public static String countAndSay(int n) {
        String result = "1";

        for (int i = 1; i < n; i++) {
            StringBuilder next = new StringBuilder();
            int count = 1;

            for (int j = 0; j < result.length(); j++) {
                if (j + 1 < result.length() && result.charAt(j) == result.charAt(j + 1)) {
                    count++;
                } else {
                    next.append(count).append(result.charAt(j));
                    count = 1;
                }
            }

            result = next.toString();
        }

        return result;
    }

    public static String reversekStr(String s, int k) {

        char[] ch= s.toCharArray();
       for(int i=0;i< ch.length;i=i+2*k){
           int f=i;
           int l=Math.min(f+k-1,ch.length-1 );

           while(f<l){
               char temp=ch[f];
               ch[f]=ch[l];
               ch[l]=temp;
               f++;
               l--;
           }
       }


        return new String(ch);


    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private  static void backtrack(int[] candidates, int target, int start, List<Integer> temp, List<List<Integer>> res){
        if(target==0){
            res.add(temp);
            return;
        }
        for(int i = start; i< candidates.length;i++){
            if(candidates[i]>target)
                continue;

            temp.add(candidates[i]);
            backtrack(candidates,target-candidates[i],i, temp, res);
            temp.remove(temp.size()-1);
        }
    }

    public void reverse(char[] arr , int i , int j) {
        while(i <= j) {
            char t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;

            i++;
            j--;
        }
    }

    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        int n = s.length();

        int i = 0;
        while(i < n) {
            int j = Math.min(i + k - 1 , n - 1);
            reverse(arr , i , j);

            i += 2 * k;
        }

        return new String(arr);
    }

    public String multiply(String num1, String num2) {
        int m= num1.length();
        int n= num2.length();

        int[] arr= new int[m+n];
        for(int i = m-1;i>=0;i--){
            for(int j=n-1;j>=0;j++){
                int k=num1.charAt(i)-'0';
                int l=num2.charAt(j)-'0';
                int mul= k*l;

                int pos1=i+j;
                int pos2=i+j+1;
                int sum=mul+arr[pos2];

                arr[pos2]= sum%10;
                arr[pos1]= sum/10;

            }
        }

        StringBuilder res= new StringBuilder();
        for(int i: arr){
            if(!(res.length()==0 && i==0)){
                res.append(i);
            }
        }

        return res.length()==0 ? "0" : res.toString();


    }

    public void duplicateZeros(int[] arr) {
        int n= arr.length;
        int i =n-1;
        int zero=0;
        for (int k: arr){
            if(k==0)
                zero++;
        }

        int j = n-1+zero;

        while(i>=0){
            if(j<n){
                arr[j]=arr[i];
            }
            if(arr[i]==0){
                j--;
                if(j<n){
                    arr[j]= 0;
                }
            }

            j--;
            i--;
        }


    }


    public boolean isMatch(String s, String p) {
        int i = 0, j = 0;
        int starIndex = -1, match = 0;

        while (i < s.length()) {

            // Case 1: characters match OR '?'
            if (j < p.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '?')) {
                i++;
                j++;
            }

            // Case 2: '*'
            else if (j < p.length() && p.charAt(j) == '*') {
                starIndex = j;
                match = i;
                j++; // move pattern pointer
            }

            // Case 3: mismatch but we saw '*' before → backtrack
            else if (starIndex != -1) {
                j = starIndex + 1;
                match++;
                i = match;
            }

            // Case 4: mismatch and no '*'
            else {
                return false;
            }
        }

        // check remaining pattern → must be all '*'
        while (j < p.length() && p.charAt(j) == '*') {
            j++;
        }

        return j == p.length();
    }

    public static  String reverseWords(String s) {

        String[] str= s.split(" ");

        String temp= "";
        for(int i=str.length-1;i>=0;i--){
            if(str[i]==" "){
                continue;
            }
            temp+=str[i];
            if(i!=0){
                temp+=" ";
            }
        }

        return temp;

    }

    public void rotate(int[][] matrix) {
        int n= matrix[0].length;



        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int temp=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=temp;
            }
        }
        for(int i=0;i<n;i++){
            int left=0;
            int right=n-1;

            while(left<right){
                int temp=matrix[i][right];
                matrix[i][right]=matrix[i][left];
                matrix[i][right]=temp;
                left++;
                right--;
            }
        }

    }





    public boolean hasCycle(ListNode head) {


        int count =0;
        ListNode temp=head;
        while(temp!=null){
            ListNode point=temp;
            while(point!= null){
                if(point.next ==temp){
                    return true;
                }
                point=point.next;

            }
            temp=temp.next;
        }
        return false;

    }

    public static void main(String[] args) {

        String str = "the sky is blue";
        System.out.println(reverseWords(str));








    }

    public char[][] rotateTheBox(char[][] boxGrid) {
        for (int i = 0; i < boxGrid.length; i++) {
            int top = 0;

            for (int j = 0; j < boxGrid[i].length; j++) {

                if (boxGrid[i][j] == '*') {
                    // process segment before obstacle
                    gravity(boxGrid[i], top, j - 1);
                    top = j + 1; // ✅ FIX: move top after obstacle
                }
            }

            // ✅ FIX: process last segment after loop
            gravity(boxGrid[i], top, boxGrid[i].length - 1);
        }
        transpose(boxGrid);
        return boxGrid;
    }

    private void transpose(char[][] boxGrid) {
        for (int i=0;i< boxGrid.length;i++){
            for(int j=i +1;j< boxGrid[i].length;j++){
                
            }
        }
    }

    private void gravity(char[] row, int top, int end) {
        if (top > end) return;

        int write = end;

        // ✅ FIX: move stones '#' to the right
        for (int i = end; i >= top; i--) {
            if (row[i] == '#') {
                row[write] = '#';
                write--;
            }
        }

        // fill remaining with '.'
        for (int i = write; i >= top; i--) {
            row[i] = '.';
        }
    }


    public static int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }

        return water;
    }






    public static int get(int[] height){
        int left =0;
        int right =height.length-1;
        int leftMax=0;
        int rightMax=0;
        int water =0;

        while(left<right){
            if(height[left]<height[right]){
                if(height[left]>leftMax){
                    leftMax=height[left];
                }
                water+= leftMax-height[left];
                left++;
            }else {
                if(height[right]>rightMax){
                    rightMax= height[right];
                }
                water+= rightMax-height[right];
                right--;
            }
        }


        return water;
    }

    public static String reverseOnlyLetters(String s) {
        int left =0;
        int right=s.length()-1;
        char[] ch =s.toCharArray();

        while(left<right){
            while(!(((ch[left]>='A' && ch[left]<='Z') || (ch[left]>='a' && ch[left]<='z')) && (left<ch.length-1))){
                left++;
            }
            while((!((ch[right]>='A' && ch[right]<='Z') || (ch[right]>='a' && ch[right]<='z'))&& (left<ch.length-1))){
                right--;
            }
            if(left<right){
                char temp = ch[left];
                ch[left]=ch[right];
                ch[right]= temp;
                left++;
                right--;
            }
        }
        String res="";
        for(char c:ch){
            res=res+c;
        }
        return res;

    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for(int i=0;i< nums.length;i++) {
            if(set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);

            if(set.size()>k){
                set.remove(nums[i-k]);
            }
        }

        return false;


    }
}
//    Input: nums = [1,2,0]
//    Output: 3
//    Explanation: The numbers in the range [1,2] are all in the array.
//            Example 2:
//
//    Input: nums = [3,4,-1,1]
//    Output: 2
//    Explanation: 1 is in the array but 2 is missing.
//    Example 3:
//
//    Input: nums = [7,8,9,11,12]
//    Output: 1
//    Explanation: The smallest positive integer 1 is missing.





