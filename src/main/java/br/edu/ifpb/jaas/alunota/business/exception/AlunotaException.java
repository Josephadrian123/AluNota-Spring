package br.edu.ifpb.jaas.alunota.business.exception;

public class AlunotaException extends Exception {
	private static final long serialVersionUID = 1L;

	public AlunotaException() {
		super();
	}

	public AlunotaException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public AlunotaException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public AlunotaException(String arg0) {
		super(arg0);
	}

	public AlunotaException(Throwable arg0) {
		super(arg0);
	}
	

}
