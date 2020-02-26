package csvToSQLite;
import com.opencsv.bean.CsvBindByName;
/**
 * Header : A, B, C, D, E, F, G, H, I, J
 * @author Shuoqiao liu (Joe)
 *
 */

public class CsvUser {

	@CsvBindByName(column = "A")
	private String A;
	
	@CsvBindByName(column = "B")
	private String B;
	
	@CsvBindByName(column = "C")
	private String C;
	
	@CsvBindByName(column = "D")
	private String D;
	
	@CsvBindByName(column = "E")
	private String E;
	
	@CsvBindByName(column = "F")
	private String F;
	
	@CsvBindByName(column = "G")
	private String G;
	
	@CsvBindByName(column = "H")
	private String H;
	
	@CsvBindByName(column = "I")
	private String I;
	
	@CsvBindByName(column = "J")
	private String J;

	public String getA() {
		return A;
	}

	public String getB() {
		return B;
	}

	public String getC() {
		return C;
	}

	public String getD() {
		return D;
	}

	public String getE() {
		return E;
	}

	public String getF() {
		return F;
	}

	public String getG() {
		return G;
	}

	public String getH() {
		return H;
	}

	public String getI() {
		return I;
	}

	public String getJ() {
		return J;
	}
	
	public boolean isBad() {
		return A==null || B==null || C==null || D==null || E==null ||
				F==null || G==null || H==null || I==null || J==null;
	}
	
	public String[] getInfo() {
		String[] result = new String[10];

		result[0] = A!=null? A : "";
		result[1] = B!=null? B : "";
		result[2] = C!=null? C : "";
		result[3] = D!=null? D : "";
		result[4] = E!=null? E : "";
		result[5] = F!=null? F : "";
		result[6] = G!=null? G : "";
		result[7] = H!=null? H : "";
		result[8] = I!=null? I : "";
		result[9] = J!=null? J : "";

		return result;
	}
	
	public boolean isEndLine() {
		return A==null && B==null && C==null && D==null && E==null &&
				F==null && G==null && H==null && I==null && J==null;
	}

}
