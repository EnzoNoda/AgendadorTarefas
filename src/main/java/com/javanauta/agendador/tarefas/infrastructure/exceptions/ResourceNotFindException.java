package com.javanauta.agendador.tarefas.infrastructure.exceptions;

public class ResourceNotFindException extends RuntimeException{

    public ResourceNotFindException(String mensagem){
        super(mensagem);
    }
    public  ResourceNotFindException(String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }
}
