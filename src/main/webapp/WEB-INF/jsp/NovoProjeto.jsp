<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>Desafio JAVA - Novo Projeto</title>

    <link rel="stylesheet"
    	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <script
    	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script
    	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script
        src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

</head>
<body>

    <div class="container">

        <h1 class="p-3">Adicionar Projeto</h1>

        <form:form action="/salvarProjeto" method="post" modelAttribute="projeto">

            <div class="row">
            	<div class="form-group col-md-12">
            		<label class="col-md-3" for="nome">Nome</label>
            		<div class="col-md-6">
            		    <form:input type="text" path="nome" id="nome"
            		        class="form-control input-sm" required="required" />
            		</div>
            	</div>
            </div>

            <div class="row">
            	<div class="form-group col-md-12">
            		<label class="col-md-3" for="dataInicio">Data de início</label>
            		<div class="col-md-6">
            		    <form:input type="date" path="dataInicio" id="dataInicio"
            		        class="form-control input-sm" required="required" />
            		</div>
            	</div>
            </div>

            <div class="row">
            	<div class="form-group col-md-12">
            		<label class="col-md-3" for="gerente">Gerente responsável</label>
            		<div class="col-md-6">
            		    <form:select path="gerente" class="form-control input-sm">
                            <form:option value="NONE">--SELECIONE--</form:option>
                            <form:options items="${listaDeGerentes}" itemLabel="nome"></form:options>
                        </form:select>
            		</div>
            	</div>
            </div>

            <div class="row">
            	<div class="form-group col-md-12">
            		<label class="col-md-3" for="dataPrevisaoFim">Previsão de término</label>
            		<div class="col-md-6">
            		    <form:input type="date" path="dataPrevisaoFim" id="dataPrevisaoFim"
            		        class="form-control input-sm" required="required" />
            		</div>
            	</div>
            </div>

            <div class="row">
            	<div class="form-group col-md-12">
            		<label class="col-md-3" for="dataFim">Data real de término</label>
            		<div class="col-md-6">
            		    <form:input type="date" path="dataFim" id="dataFim"
            		        class="form-control input-sm" />
            		</div>
            	</div>
            </div>

            <div class="row">
            	<div class="form-group col-md-12">
            		<label class="col-md-3" for="orcamento">Orçamento</label>
            		<div class="col-md-6">
            		    <form:input type="text" path="orcamento" id="orcamento"
            		        class="form-control input-sm" required="required" />
            		</div>
            	</div>
            </div>

            <div class="row">
            	<div class="form-group col-md-12">
            		<label class="col-md-3" for="descricao">Descrição</label>
            		<div class="col-md-6">
            		    <form:input type="text" path="descricao" id="descricao"
            		        class="form-control input-sm" required="required" />
            		</div>
            	</div>
            </div>

            <div class="row">
            	<div class="form-group col-md-12">
            		<label class="col-md-3" for="status">Status</label>
            		<div class="col-md-6">
            		    <form:select path="status" class="form-control input-sm">
                            <form:option value="NONE">--SELECIONE--</form:option>
                            <form:options items="${listaStatus}"></form:options>
                        </form:select>
            		</div>
            	</div>
            </div>

            <div class="row p-2">
            	<div class="col-md-2">
            		<button type="submit" value="Register" class="btn btn-success">Criar</button>
            	</div>
            </div>

        </form:form>

    </div>

    <script th:inline="javascript">
            window.onload = function() {

                var msg = "${message}";
                console.log(msg);
                if (msg == "Save Failure") {
    				Command: toastr["error"]("Something went wrong with the save.")
    			}

    			toastr.options = {
                      "closeButton": true,
                      "debug": false,
                      "newestOnTop": false,
                      "progressBar": true,
                      "positionClass": "toast-top-right",
                      "preventDuplicates": false,
                      "showDuration": "300",
                      "hideDuration": "1000",
                      "timeOut": "5000",
                      "extendedTimeOut": "1000",
                      "showEasing": "swing",
                      "hideEasing": "linear",
                      "showMethod": "fadeIn",
                      "hideMethod": "fadeOut"
                    }
    	    }
        </script>
</body></html>