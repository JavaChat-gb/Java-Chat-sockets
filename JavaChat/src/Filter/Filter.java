/**
 * 
 */
package Filter;

/**
 * @author Erik
 *
 */
public abstract class Filter implements ChatRecFilter {
	private Filter nextf;
	private String text="";

	public Filter(Filter nextf) {
		this.nextf = nextf;
	}
	public Filter(){
		this.nextf=null;
	}

	@Override
	public abstract String performFilter();

	public Filter getNextf() {
		return nextf;
	}

	public void setNextf(Filter nextf) {
		this.nextf = nextf;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
