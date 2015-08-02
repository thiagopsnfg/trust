/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trust.controller;

import java.io.Serializable;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Thiago
 */
public class BasicController implements java.io.Serializable {

    protected void messages(FacesMessage.Severity severity, String msg) {
        FacesMessage fm = new FacesMessage(severity, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    protected void messages(String msg) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    //Validar entidades inteiras (Não apenas campos como a especiaficação Beans Validation faz.) antes de passar para camada de persistencia JPA.
    protected Set<ConstraintViolation<Serializable>> getViolations(Serializable entidade) {
        //Cria a validação para qualquer beans validations.
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        //Aplica a validação
        Set<ConstraintViolation<Serializable>> toReturn = validator.validate(entidade);
        return toReturn;
    }

    protected boolean existViolationsForJSF(Serializable entidade) {
        //pegar todas as violações
        Set<ConstraintViolation<Serializable>> toReturn = getViolations(entidade);
        //Se não houver violações ok.
        if (toReturn.isEmpty()) {
            return false;
        }
        //senão joga na tela.
       for (ConstraintViolation<Serializable> constraintViolation : toReturn) {
            messages(constraintViolation.getMessage());
        }
        return true;
    }
}
