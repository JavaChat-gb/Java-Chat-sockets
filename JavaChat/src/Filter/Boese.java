package Filter;
/**
 * 
 */


import java.util.Arrays;

/**
 * @author Erik
 *
 */
public class Boese extends Filter {
	
	public Boese(Filter nextf) {
		this.setNextf(nextf);
	}

	public Boese() {
		this.setNextf(null);
	}

	@Override
	public String performFilter() {
		
		String s = getText();
		
		
		
		this.setText(s);

		if (this.getNextf() != null) {
			this.getNextf().setText(this.getText());
			return this.getNextf().performFilter();
		}
		return this.getText();
	}

}

