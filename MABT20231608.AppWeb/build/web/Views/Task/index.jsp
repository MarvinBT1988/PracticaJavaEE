<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entidades.Task"%>
<%@page import="java.util.List"%>
<% List<Task> taskPending = (List<Task>) request.getAttribute("taskPending");
    int numPage = 1;
    int numReg = 10;
    int countReg = 0;
    if (taskPending == null) {
        taskPending =  new ArrayList<>();
    } else if (taskPending.size() > numReg) {
        double divNumPage = (double) taskPending.size() / (double) numReg;
        numPage = (int) Math.ceil(divNumPage);
    }   
%>
<% List<Task> taskComplete = (List<Task>) request.getAttribute("taskComplete");
    int numPageComplete = 1;
    int numRegComplete = 10;
    int countRegComplete = 0;
    if (taskComplete == null) {
        taskComplete =  new ArrayList<>();
    } else if (taskComplete.size() > numRegComplete) {
        double divNumPageComplete = (double) taskComplete.size() / (double) numRegComplete;
        numPageComplete = (int) Math.ceil(divNumPageComplete);
    }   
%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />    
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">  
            <div class="row">
                <div class="col l12 s12">
                    <div style="overflow: auto">
                        <table class="paginationjs">
                            <thead>
                                <tr>
                                    <th>Titulo</th> 
                                    <th>Descripcion</th> 
                                    <th>Fecha creacion</th> 
                                    <th>Estatus</th> 
                                    <th>Acciones</th>
                                </tr>
                            </thead>                       
                            <tbody>                           
                                <% for (Task taskItemPending : taskPending) {
                                        int tempNumPage = numPage;
                                        if (numPage > 1) {
                                            countReg++;
                                            double divTempNumPage = (double) countReg / (double) numReg;
                                            tempNumPage = (int) Math.ceil(divTempNumPage);
                                        }
                                %>
                                <tr data-page="<%= tempNumPage%>">
                                    <td><%=taskItemPending.getTitle()%></td>  
                                    <td><%=taskItemPending.getDescription()%></td>  
                                    <td><%=taskItemPending.getDateCreate()%></td>  
                                    <td><%=taskItemPending.getStatus()%></td>  
                                    <td>
                                        <div style="display:flex">
                                            <form action="Task" method="Put">
                                                <input type="hidden" name="id" value="<%=taskItemPending.getId()%>">
                                                <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">add</i>Complete</button>
                                            </form>                                                                                                                             
                                        </div>
                                    </td>                                   
                                </tr>
                                <%}%>                                                       
                            </tbody>
                        </table>
                    </div>                  
                </div>
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <jsp:include page="/Views/Shared/paginacion.jsp">
                        <jsp:param name="numPage" value="<%= numPage%>" />                        
                    </jsp:include>
                </div>
            </div>

            <div class="row">
                <div class="col l12 s12">
                    <div style="overflow: auto">
                        <table class="paginationjs">
                            <thead>
                                <tr>
                                    <th>Titulo</th> 
                                    <th>Descripcion</th> 
                                    <th>Fecha creacion</th> 
                                     <th>Fecha completada</th> 
                                    <th>Estatus</th> 
                                </tr>
                            </thead>                       
                            <tbody>                           
                                <% for (Task taskItemComplete : taskComplete) {
                                        int tempNumPageComplete = numPageComplete;
                                        if (numPageComplete > 1) {
                                            countRegComplete++;
                                            double divTempNumPageComplete = (double) countRegComplete / (double) numRegComplete;
                                            tempNumPageComplete = (int) Math.ceil(divTempNumPagecomplete);
                                        }
                                %>
                                <tr data-page="<%= tempNumPageComplete%>">
                                    <td><%=taskItemComplete.getTitle()%></td>  
                                    <td><%=taskItemComplete.getDescription()%></td>  
                                    <td><%=taskItemComplete.getDateCreate()%></td>  
                                    <td><%=taskItemComplete.getDateComplete()%></td>  
                                    <td><%=taskItemComplete.getStatus()%></td>                                                                      
                                </tr>
                                <%}%>                                                       
                            </tbody>
                        </table>
                    </div>                  
                </div>
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <jsp:include page="/Views/Shared/paginacion.jsp">
                        <jsp:param name="numPage" value="<%= numPageComplete%>" />                        
                    </jsp:include>
                </div>
            </div>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />        
    </body>
</html>
