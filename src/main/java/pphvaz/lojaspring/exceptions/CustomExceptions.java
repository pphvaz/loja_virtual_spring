package pphvaz.lojaspring.exceptions;

public class CustomExceptions extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomExceptions(String msgErro) {
		super(msgErro);
	}	
}