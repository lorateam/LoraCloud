<%@page contentType="text/html; UTF-8"  pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<section class="content-header">
    <h1>
        控制台
        <small><%
            Date dNow = new Date( );
            SimpleDateFormat ft =
                    new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
            out.print(ft.format(dNow));
        %></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li class="active">控制台</li>
    </ol>
</section>
