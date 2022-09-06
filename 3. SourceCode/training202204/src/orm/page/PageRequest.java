package orm.page;

public class PageRequest implements PageAble{
	private int index;
	private int size;
	
	private PageRequest(int index, int size) {
		this.index = index;
		this.size = size;
	}
	public static PageRequest of(int index,int size) {
		return new PageRequest(index, size);
	}
	@Override
	public int getOffset() {
		// TODO Auto-generated method stub
		return (index==1 || index == 0) ? 0 : (index - 1) * size;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public int getSize() {
		return size;
	}

}
