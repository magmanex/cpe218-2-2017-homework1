public class Homework1 {

	public static String word="";

	public static void main(String[] args) {
		// Begin of arguments input sample
		if (args.length > 0) {
			String input = args[0];
			if (input.equalsIgnoreCase("251-*32*+")) {
				//System.out.println("(2*(5-1))+(3*2)=14");
			}
		}
		// End of arguments input sample

		// TODO: Implement your project here
		if(args.length != 0 ) {
			word = args[0];
			Node node = null;
			node = infix(node);
			System.out.println(inorder(node).substring(1,inorder(node).length()-1) +  "=" + calculate(node));
		}
		word  ="251-*32*+";
		Node node = null;
		node = infix(node);
		System.out.println(inorder(node).substring(1,inorder(node).length()-1) +  "=" + calculate(node));
		TreeDemo tree = new TreeDemo(node);
		tree.ShowGUI(node);
	}

	public static boolean isOperator(char o){
		if(o == '+' || o == '-' || o == '*' || o =='/' || o == '^')
			return true;
		return false;
	}

	public static Node infix(Node n) {
		Node tmp = n;
		for(int i = 0;i < word.length() ; i++) {
			Node parent = new Node();
			parent.value_node = word.charAt(i);
			if(isOperator(parent.value_node)) { //node is Operator
				if(tmp == null) {//case wrong syntax

				} else {//collect Syntax
					if(isOperator(tmp.value_node)) {
						parent.left = tmp.left.left;
						tmp.left.left = null;
						parent.right = tmp;
						tmp = parent;
					} else {
						parent.left = tmp.left;
						tmp.left = null;
						parent.right = tmp;
						tmp = parent;
					}
				}
			} else { //node isn't Operator
				if(tmp != null) { //tmp is valid
					parent.left = tmp;
					tmp = parent;
				} else { //tmp is invalid
					tmp = parent;
				}
			}
		}
		return tmp;
	}

	public static String inorder (Node n) {
		String left_node ="";
		String right_node ="";
        if(n.left != null) {
            left_node = "(" + inorder(n.left);
        }
        if(n.right != null) {
            right_node = inorder(n.right) + ")";
        }
		return left_node + n.value_node + right_node;
	}

	public static int calculate(Node n) {
		int result = 0;
		if(Character.toString(n.value_node).matches("[0-9]")) { //Node is number that's mean it not have operator in next node
			return Integer.parseInt(Character.toString(n.value_node));
		}
		int left_side = calculate(n.left); //calculate on all of left side
		int right_side = calculate(n.right); //calculate on all of right side

		switch (n.value_node) {
			case '+':
				result = left_side + right_side;
				break;
			case '-':
				result = left_side - right_side;
				break;
			case '*':
				result = left_side * right_side;
				break;
			case '/':
				result = left_side / right_side;
				break;
		}
		return  result;
	}
}
