package Filter;
/**
 * 
 */


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
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
	public String performFilter()  {
		
		String ss,s = getText();
		ss=getText();
		ss=ss.toLowerCase();
		try{
		RandomAccessFile file = new RandomAccessFile("words.properties", "r");
		String moesewoerter = file.readUTF();
		System.out.println(moesewoerter);
		String[] zz = moesewoerter.split("=");
		
		for(int i = 1; i < zz.length;i+=2){
			if(ss.contains(zz[i])){
				ss.replace(zz[i], "!#$%*");
			}
		}//iwas geht net
		}catch(Exception e){}
		//System.out.println(ss);
		
		
		this.setText(ss);

		if (this.getNextf() != null) {
			this.getNextf().setText(this.getText());
			return this.getNextf().performFilter();
		}
		return this.getText();
	}

}

