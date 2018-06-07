<%--此页面用于前端进行账号和密码验证--%>
<%@ page contentType="text/html; UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page import="bean.*,dao.*,org.json.*" %>
<%
    //获取验证页面的账号和密码
    int tmpId = Integer.parseInt(request.getParameter("checkUserId"));
    String tmpPassWord = request.getParameter("checkPassWord");
    //创建javabean
    User tmpUser = new User();
    tmpUser.setId(tmpId);
    tmpUser.setPassword(tmpPassWord);
    //创建userDAO进行账户判断
    UserDAO tmpUserDAO = new UserDAO();
    boolean checkResult = tmpUserDAO.check(tmpUser);
    response.setContentType("text/html;charset=UTF-8");
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("fireConfition",checkResult);
    out.print(jsonObject);
%>
