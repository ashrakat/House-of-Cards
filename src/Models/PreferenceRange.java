package Models;

public class PreferenceRange {
	public int min;
	public long max;
	
	public PreferenceRange( int min, long max){
		this.min = min;
		this.max = max;
	}
	
	public PreferenceRange() {
	
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public long getMax() {
		return max;
	}

	public void setMax(long max) {
		this.max = max;
	}
	
}
