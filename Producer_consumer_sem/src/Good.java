
public class Good {
	
	int id;
	String s;
	
	public Good(int id,String s) {
		
		this.id=id;
		this.s=s;
	}
	
	public int getNum() {
		
		return id;
	}
	
	public String getS() {
		
		return s;
	}
	
	public void setNum(int num) {
		
		this.id=id;
	}
	
	public void setS(String s) {
		
		this.s=s;
	}
	
	public void printGood() {
		String s = "Good " + String.valueOf(this.getNum());
		System.out.println(s);
	}
	
	
}
