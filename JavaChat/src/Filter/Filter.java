/**
 * 
 */
package Filter;

/**
 * @author Erik
 *
 */
public abstract class Filter implements ChatRecFilter{
	private ChatRecFilter nextf;
	private String text;
	
	@Override
	public abstract String performFilter();
}
