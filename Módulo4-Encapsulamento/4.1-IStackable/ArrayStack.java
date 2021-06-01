import java.util.ArrayList;

public class ArrayStack implements IStackable {
    private ArrayList<Integer> al = new ArrayList<Integer>();

	@Override
	public int size() {
		return this.al.size();
	}   

	@Override
	public void push(int v) {
		this.al.add(v);
	}

	@Override
	public int pop() {
		return this.al.remove(this.al.size()-1);
	}
}
