<%@ page import="com.evgeny13.basejava.model.ContactType" %>
<%@ page import="com.evgeny13.basejava.util.DateUtil" %>
<%@ page import="com.evgeny13.basejava.model.ListSection" %>
<%@ page import="com.evgeny13.basejava.model.SectionType" %>
<%@ page import="com.evgeny13.basejava.model.OrganizationSection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.evgeny13.basejava.model.Resume" scope="request"/>

    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <h2>Имя:</h2>
        <dl>
            <input type="text" name="fullName" size=55 value="${resume.fullName}">
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <hr>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(type)}"/>
            <jsp:useBean id="section" type="com.evgeny13.basejava.model.AbstractSection"/>
            <h3><a>${type.title}</a></h3>
            <c:choose>
                <c:when test="${type=='OBJECTIVE' || type=='PERSONAL'}">
                    <input type='text' name='${type}' size=100 value='<%=section%>'>
                </c:when>
                <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                    <textarea name='${type}' cols=100
                              rows=4><%=String.join("\n", ((ListSection) section).getItems())%></textarea>
                </c:when>
                <c:when test="${sectionType=='EDUCATION' || sectionType=='EXPERIENCE'}">
                    <c:forEach var="experience" items="<%=((OrganizationSection) section).getOrganizations()%>"
                               varStatus="id">
                        <p><strong>Организация:</strong></p>
                        <p><input type="text" name='${sectionType}' size=100 value="${experience.employerName}"></p>
                        <p>Сайт:</p>
                        <p><input type="text" name='${sectionType}url' size=100 value="${experience.employerSite}"></p>
                        <p>Период:</p>
                        <c:forEach var="position" items="${experience.positions}">
                            <jsp:useBean id="position" type="com.evgeny13.basejava.model.Organization.Position"/>
                            <p>с:
                                <input type="text" name="${sectionType}${id.index}startDate" size=10
                                       value="<%=(DateUtil.format(position.getStartDate()))%>">
                                по:
                                <input type="text" name="${sectionType}${id.index}finishDate" size=10
                                       value="<%=(DateUtil.format(position.getEndDate()))%>">
                            </p>
                            <p>Должность:</p>
                            <input type="text" name='${sectionType}${id.index}title' size=100
                                   value="${position.position}">
                            <p>Должностные обязанности:</p>
                            <textarea name="${sectionType}${id.index}description" rows=4
                                      cols=100>${position.description}</textarea>
                        </c:forEach>
                        </div>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>
        <p>
            <button type="submit">Сохранить</button>
            <button onclick="window.history.back()">Отменить</button>
        </p>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
