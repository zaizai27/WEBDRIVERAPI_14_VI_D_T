package java_basic;
// video topic 06 https://www.youtube.com/watch?v=tSWB1J0h0hY&feature=youtu.be
public class CheckAndOr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//  2gia tri la dung or sai True False
		/*boolean : logic
		int/long nguyen
		string chuoi
		double/float thuc */
		boolean value_a = true;
		boolean value_b = true;
		boolean value_c = value_a && value_b;
		boolean value_d = value_a || value_b;
		
		//AND
		System.out.println("Gia tri C AND = " + value_c);
		//OR
		System.out.println("Gia tri D OR = " + value_d);
		
		//layxpath bang AND OR   //*[@type='email' and @id='email' and @name='login[username]']
		
		// //*[@id='selectable']/li[position()=2]
		// lay vitri cuoi cung //*[@id='selectable']/li[last()]    ( ko ho tro first)
		// lay quan he gia pha~ button ADD TO CART  //a[text()='IPhone'/parent::h2/following-sibling::div[@class='action']/button
	}

}
