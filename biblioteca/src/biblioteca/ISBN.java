package biblioteca;

import java.io.Serializable;



/**
 * Clase que representa el ISBN
 */
public class ISBN implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public final String value;
	
	/**
	 * Create a new instance of the ISBN class
	 * @param	value
	 * 			The string representing an ISBN.
	 */
	public ISBN(String value) {
		// elimina los guiones
		value = value.replaceAll("-", "");
		value = value.trim();
		
		this.value = value;
	}
	
	/**
	 * Devuelve el string que representa el ISBN
	 * @return	El string del ISBN.
	 */
	@Override
	public String toString() {
		return getValue();
	}

	public String getValue() {
		return this.value;
	}
	
	/**
	 * Devielve true si es valido el ISBN
	 * @param	valor
	 * 			
	 * @return	verdadero si es valido, falso en otro caso.
	 */
	public static boolean esValidoISBN(String valor) {
		if (valor.length() == 10)
			return esValidoISBN10(valor);
		else if (valor.length() == 13)
			return esValidoISBN13(valor);
		
		return false;
	}
	
	/**
	 * Check if the given value is a valid ISBN-10.
	 * @param 	value
	 * 			The <value> to check for it's ISBN validity.
	 * @return	True if the given <value> is a valid ISBN in the ISBN-10
	 * 			format. False otherwise.
	 */
	private static boolean esValidoISBN10(String value) {
		if (value.length() != 10)
			return false;
		
		int sum = 0, n;
		String s;
		
		for (int i=10; i>0; i--) {
			s = value.substring(10-i, 10-i+1);
			
			if (s.equals("X") || s.equals("x")) {
				n = 10;
			} else {
				try {
					n = Integer.parseInt(s);
				} catch (NumberFormatException e) {
					return false;
				}
			}
			sum += i*n;
		}
		
		return (sum%11 == 0);
	}
	
	/**
	 * .
	 * @param 	valor
	 * 			
	 * @return	verdadero si es valido, falso en otro caso.
	 */
	private static boolean esValidoISBN13(String valor) {
		int sum = 0, n, m;
		String s;
		
		for (int i=1; i<13; i++) {
			s = valor.substring(i-1,i);
			try {
				n = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				return false;
			}
			
			m = ((i%2 == 1) ? 1 : 3);
			sum += n*m;
		}
		
		try {
			n = Integer.parseInt(valor.substring(12,13));
		} catch (NumberFormatException e) {
			return false;
		}
		
		return (((10-sum%10)%10)-n == 0);
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ISBN))
			return false;
		
		ISBN isbn = (ISBN) o;
		return getValue().equals(isbn.getValue());
	}
}