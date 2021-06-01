import java.util.LinkedList;

public class LinkedStack implements IStackable {
	private LinkedList<Integer> ll = new LinkedList<Integer>();
	
	@Override
	public int size() {
		return this.ll.size();
	}

	@Override
	public void push(int v) {
		this.ll.addFirst(v);
	}

	@Override
	public int pop() {
		return this.ll.removeFirst();
	}
}
