<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>Desafio JAVA - Lista de Projetos</title>

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

    <style>
        a{
            color: white;
        }
        a:hover {
            color: white;
            text-decoration: none;
        }
    </style>

</head>
<body>

    <div class="container">

        <h1 class="p-3">Lista de Projetos</h1>

        <form:form>

            <table class="table table-bordered">
            	<tr>
            		<th>Id</th>
            		<th>Nome</th>
            		<th>Data de Início</th>
            		<th>Status</th>
            		<th>Risco</th>
            		<th>Gerente</th>
            		<th>Ações</th>
            	</tr>

            	<c:forEach var="projeto" items="${listaDeProjetos}">
                    <tr>
                		<td>${projeto.id}</td>
                		<td>${projeto.nome}</td>
                		<td><fmt:formatDate value="${projeto.dataInicio}" pattern="dd/MM/yyyy"/></td>
                		<td>${projeto.status}</td>
                		<td>${projeto.risco}</td>
                		<td>${projeto.gerente.nome}</td>
                		<td>
                		    <button type="button" class="btn btn-success">
                                <a href="/membrosProjeto/${projeto.id}">Membros</a>
                            </button>
                		    <button type="button" class="btn btn-secondary">
                		        <a href="/editarProjeto/${projeto.id}">Editar</a>
                		    </button>
                		    <button type="button" class="btn btn-danger">
                			    <a href="javascript:" onclick="confirmDelete(${projeto.id})">Deletar</a>
                		    </button>
                		</td>
                	</tr>

            	</c:forEach>

            </table>

        </form:form>

        <a href="/novoProjeto" class="btn btn-primary btn-block">Novo Projeto</a>

    </div>

    <script th:inline="javascript">

        function confirmDelete(id) {
            if (confirm('Deseja realmente deletar o projeto?')) location.href = '/deletarProjeto/' + id;
        }

        window.onload = function() {

            var msg = "${message}";
            if (msg == "Save Success") {
        	    Command: toastr["success"]("Projeto adicionado com sucesso!")
        	} else if (msg == "Delete Success") {
        	    Command: toastr["success"]("Projeto deletado com sucesso!")
        	} else if (msg == "Delete Failure") {
        	    Command: toastr["error"]("O Projeto não pode ser deletado!")
        	} else if (msg == "Edit Success") {
        		Command: toastr["success"]("Projeto modificado com sucesso!")
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