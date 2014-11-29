/**
 * 
 */
package Filter;

import java.util.Arrays;

/**
 * @author Erik
 *
 */
public class Doubleizer extends Filter {

	public Doubleizer(Filter nextf) {
		this.setNextf(nextf);
	}

	public Doubleizer() {
		this.setNextf(null);
	}

	@Override
	public String performFilter() {

		char[] ns = new char[this.getText().length() * 2];
		String s = this.getText();

		for (int i = 0; i < s.length(); i++) {
			ns[i*2] = s.charAt(i);
			ns[i*2 + 1] = s.charAt(i);
		}
		
		s="";
		for(int i=0; i< ns.length;i++){
			s+=ns[i];
		}
		this.setText(s);

		if (this.getNextf() != null) {
			this.getNextf().setText(this.getText());
			return this.getNextf().performFilter();
		}
		return this.getText();
	}

}
