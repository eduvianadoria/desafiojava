<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>Desafio JAVA - Membros Projeto</title>

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

        <h1 class="p-3"> Membros do Projeto </h1>

            <div class="row">
            	<div class="form-group col-md-12">
            		<label class="col-md-3" for="nome">Nome do Projeto</label>
            		<div class="col-md-6">
            		    <input type="text" id="nome" value="${projeto.nome}"
            		        class="form-control input-sm" readonly="true" />
            		</div>
            	</div>
            </div>

            <div class="row">
            	<div class="form-group col-md-4">
            		<label class="col-md-12" for="nome">Funcionários</label>
            		<div class="col-md-12">
            		    <select class="form-control" multiple="true">
                            <c:forEach items="${listaDePessoas}" var="pessoa">
                                <option value="${pessoa.id}">${pessoa.nome}</option>
                            </c:forEach>
                        </select>
            		</div>
            	</div>

                <div class="form-group col-md-4">
                    <div class="col-md-12" style="padding-top: 30px;">
                        <button type="button" value="Register" class="btn btn-primary btn-block">Adicionar Membro >></button>
                    </div>
            		<div class="col-md-12" style="padding-top: 15px;">
            		    <button type="button" value="Register" class="btn btn-danger btn-block"><< Remover Membro</button>
            		</div>
            	</div>

                <div class="form-group col-md-4">
            		<label class="col-md-12" for="nome">Membros do Projeto</label>
            		<div class="col-md-12">
            		    <select class="form-control" id="membrosSelecionados" multiple="true">
                            <c:forEach items="${listaDeMembros}" var="membro">
                                <option value="${membro.pessoa.id}">${membro.pessoa.nome}</option>
                            </c:forEach>
                        </select>
            		</div>
            	</div>
            </div>

            <div class="row p-2">
               	<div class="col-md-2">
               		<button type="button" id="btnSave" class="btn btn-success btn-block">Salvar</button>
               	</div>

               	<div class="col-md-2">
                    <button type="button" value="Register" class="btn btn-secondary btn-block" onclick="location.href='/'">Cancelar</button>
                </div>
            </div>


    </div>

    <script th:inline="javascript">

        jQuery('#btnSave').click(function(event){
            event.preventDefault();
            var newForm = jQuery('<form>', {
                'action': '/salvarMembrosProjeto',
                'method': 'post'
            }).append(jQuery('<input>', {
                'name': 'membrosSelecionados',
                'value': '5',
                'type': 'hidden'
            }))
            .append(jQuery('<input>', {
                'name': 'idProjeto',
                'value': '1',
                'type': 'hidden'
            }))
            .appendTo('body').submit().remove();
        });

        window.onload = function() {
            var msg = "${message}";
            if (msg == "Edit Failure") {
                Command: toastr["error"]("Something went wrong with the edit.")
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