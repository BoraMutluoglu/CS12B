//Bora Mutluoglu | bmutluog | 1564633 | cs12M | March 5th, 2018 |
//Implementing a dictionary interface using BST, translated the c version
//into a Dictionary.java version
public class Dictionary implements DictionaryInterface {

	// private classes functions and variables
	private class Node {
		String key;
		String value;
		Node left;
		Node right;

		Node(String x, String y) {
			key = x;
			value = y;
			left = null;
			right = null;
		}
	}

	Node root;
	int numPairs;

	// findKey()
	// returns the Node containing key k in the subtree rooted at R, or returns
	// NULL if no such Node exists
	private Node findKey(Node N, String k) {
		if (N == null || k.equals(N.key)) {
			return N;
		} else if (k.compareTo(N.key) < 0) {
			return findKey(N.left, k);
		} else {
			return findKey(N.right, k);
		}
	}

	// findParent()
	// returns the parent of N in the subtree rooted at R, or returns NULL
	// if N is equal to R. (pre: R != NULL)
	Node findParent(Node N, Node R) {
		Node P = null;
		if (N != R) {
			P = R;
			while (P.left != N && P.right != N) {
				if (N.key.compareTo(P.key) < 0) {
					P = P.left;
				} else {
					P = P.right;
				}
			}
		}
		return P;
	}

	// findLeftmost()
	// returns the leftmost Node in the subtree rooted at R, or NULL if R is
	// NULL.
	Node findLeftmost(Node R) {
		Node L = R;
		if (L != null)
			for (; L.left != null; L = L.left)
				;
		return L;
	}

	// printInOrder()
	// prints the (key, value) pairs belonging to the subtree rooted at R in
	// order
	// of increasing keys to file pointed to by out.
	void printInOrder(Node R) {
		String out = "";
		if (R != null) {
			printInOrder(R.left);
			System.out.println(R.key + " " + R.value);
			printInOrder(R.right);
		}
	}

	// deleteAll()
	// deletes all Nodes in the subtree rooted at N.
	void deleteAll(Node N) {
		if (N != null) {
			root = null;
			numPairs = 0;
		}
	}

	// public functions from the Dictionary Interface mostly

	// constructor for the Dictionary class
	public Dictionary() {
		root = null;
		numPairs = 0;
	}
	// isEmpty()
	// returns 1 (true) if D is empty, 0 (false) otherwise
	// pre: none
	public boolean isEmpty() {
		if (numPairs == 0) {
			return true;
		} else {
			return false;
		}
	}
	// size()
	// returns the number of (key, value) pairs in D
	// pre: none
	public int size() {
		return numPairs;
	}
	// lookup()
	// returns the value v such that (k, v) is in D, or returns NULL if no 
	// such value v exists.
	// pre: none
	public String lookup(String key) {
		Node N;
		N = findKey(root, key);
		return (N == null ? null : N.value);
	}
	// insert()
	// inserts new (key,value) pair into D
	// pre: lookup(D, k)==NULL
	public void insert(String key, String value) throws DuplicateKeyException {
		if (findKey(root, key) != null) {
			throw new DuplicateKeyException("Dictionary Error: cannot insert duplicate keys");
		}
		Node N, A, B;
		N = new Node(key, value);
		B = null;
		A = root;
		while (A != null) {
			B = A;
			if (key.compareTo(A.key) < 0) {
				A = A.left;
			} else {
				A = A.right;
			}
		}
		if (B == null) {
			root = N;
		} else if (key.compareTo(B.key) < 0) {
			B.left = N;
		} else {
			B.right = N;
		}
		numPairs++;
	}
	// delete()
	// deletes pair with the key k
	// pre: lookup(D, k)!=NULL
	public void delete(String key) throws KeyNotFoundException {
		Node N, P, S;
		N = findKey(root, key);
		if (N == null) {
			throw new KeyNotFoundException("Dictionary Error: calling delete() on NULL Dictionary reference\n");
		}
		if (N.left == null && N.right == null) {
			if (N == root) {
				root = null;
			} else {
				P = findParent(N, root);
				if (P.right == N)
					P.right = null;
				else
					P.left = null;
			}
		} else if (N.right == null) {
			if (N == root) {
				root = N.left;
			} else {
				P = findParent(N, root);
				if (P.right == N)
					P.right = N.left;
				else
					P.left = N.left;
			}
		} else if (N.left == null) {
			if (N == root) {
				root = N.right;
			} else {
				P = findParent(N, root);
				if (P.right == N)
					P.right = N.right;
				else
					P.left = N.right;
			}
		} else {
			S = findLeftmost(N.right);
			N.key = S.key;
			N.value = S.value;
			P = findParent(S, N);
			if (P.right == S)
				P.right = S.right;
			else
				P.left = S.right;
		}
		numPairs--;
	}
	// makeEmpty()
	// re-sets D to the empty state.
	// pre: none
	public void makeEmpty() {
		deleteAll(root);
		root = null;
		numPairs = 0;
	}

	// toString()
	// pre: none
	// prints a text representation of root
	public String toString() {
		printInOrder(root);
		return "";
	}

}
