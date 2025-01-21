package br.com.alura.exception;

public class AgenciaNaoAtivaOuNaoEncontradaException extends RuntimeException {
    public AgenciaNaoAtivaOuNaoEncontradaException() {
        super("A agência não está ativa ou não foi encontrada.");
    }

    public AgenciaNaoAtivaOuNaoEncontradaException(String message) {
        super(message);
    }

    public AgenciaNaoAtivaOuNaoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }
}
