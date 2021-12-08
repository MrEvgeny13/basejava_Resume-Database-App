<%@ page import="com.evgeny13.basejava.model.TextSection" %>
<%@ page import="com.evgeny13.basejava.model.ListSection" %>
<%@ page import="com.evgeny13.basejava.model.OrganizationSection" %>
<%@ page import="com.evgeny13.basejava.util.HtmlUtil" %>
<%@ page import="com.evgeny13.basejava.model.Organization" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.evgeny13.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.evgeny13.basejava.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    <table cellpadding="2">
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<com.evgeny13.basejava.model.SectionType, com.evgeny13.basejava.model.AbstractSection>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean id="section" type="com.evgeny13.basejava.model.AbstractSection"/>
            <c:choose>
                <c:when test="${type=='PERSONAL' || type=='OBJECTIVE'}">
                    <%
                        String[] str = ((TextSection) section).getContent().split("\n");
                        request.setAttribute("textSection", str);
                        request.setAttribute("size", str.length);
                    %>
                    <c:if test="${size>0 && !textSection[0].equals('')}">
                        <h3><%=sectionEntry.getKey().getTitle()%>
                        </h3>
                        <c:forEach var="text" items="${textSection}">
                            ${text}<br>
                        </c:forEach>
                    </c:if>
                </c:when>
                <c:when test="${type=='ACHIEVEMENT' || type=='QUALIFICATIONS'}">
                    <%
                        request.setAttribute("listSection", ((ListSection) section).getItems());
                        request.setAttribute("listSize", ((ListSection) section).getItems().size());
                    %>
                    <c:if test="${listSize>0 && !listSection.get(0).equals('')}">
                        <h3><%=sectionEntry.getKey().getTitle()%>
                        </h3>
                        <c:forEach var="text" items="${listSection}">
                            <li type="disc">${text}</li>
                        </c:forEach>
                    </c:if>
                </c:when>
                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <c:forEach var="org" items="<%=((OrganizationSection) section).getOrganizations()%>">
                        <tr>
                            <td colspan="2">
                                <c:choose>
                                    <c:when test="${empty org.homePage.url}">
                                        <h3>${org.homePage.name}</h3>
                                    </c:when>
                                    <c:otherwise>
                                        <h3><a href="${org.homePage.url}">${org.homePage.name}</a></h3>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                            <c:forEach var="position" items="${org.positions}">
                                <jsp:useBean id="position" type="com.evgeny13.basejava.model.Organization.Position"/>
                                <tr>
                                    <td width="15%" style="vertical-align: top"><%=HtmlUtil.formatDates(position)%>
                                    </td>
                                    <td><b>${position.title}</b><br>${position.description}</td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>
    </table>
    <br/>
    <button onclick="window.history.back()">ОК</button>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>