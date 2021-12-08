<%@ page import="com.evgeny13.basejava.model.TextSection" %>
<%@ page import="com.evgeny13.basejava.model.ListSection" %>
<%@ page import="com.evgeny13.basejava.model.OrganizationSection" %>
<%@ page import="com.evgeny13.basejava.util.HtmlUtil" %>
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
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>

    <c:forEach var="contactEntry" items="${resume.contacts}">
        <jsp:useBean id="contactEntry"
                     type="java.util.Map.Entry<com.evgeny13.basejava.model.ContactType, java.lang.String>"/>
        <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
    </c:forEach>

    <c:forEach var="sectionEntry" items="${resume.sections}">
        <jsp:useBean id="sectionEntry"
                     type="java.util.Map.Entry<com.evgeny13.basejava.model.SectionType, com.evgeny13.basejava.model.AbstractSection>"/>

        <c:set var="type" value="${sectionEntry.key}"/>
        <h3>
            <%=sectionEntry.getKey().getTitle()%>
        </h3>
        <c:set var="sectionValue" value="${sectionEntry.value}"/>
        <jsp:useBean id="section" type="com.evgeny13.basejava.model.AbstractSection"/>
        <c:choose>
            <c:when test="${sectionType=='OBJECTIVE' || sectionType=='PERSONAL'}">
                <%=((TextSection) section).getContent()%>
            </c:when>
            <c:when test="${sectionType=='QUALIFICATIONS' || sectionType=='ACHIEVEMENT'}">
                <c:forEach var="item" items="<%=((ListSection) section).getItems()%>">
                    <li>${item}</li>
                </c:forEach>
            </c:when>
            <c:when test="${sectionType=='EXPERIENCE' || sectionType=='EDUCATION'}">
                <c:forEach var="experience" items="<%=((OrganizationSection) section).getOrganizations()%>">

                    <c:forEach var="position" items="${experience.positions}">
                        <jsp:useBean id="position" type="com.evgeny13.basejava.model.Organization.Position"/>
                        <div style="margin-left: 15px">
                            <h4>
                                <li>${experience.employerName}</li>
                            </h4>
                            <div style="margin-left: 15px">
                                <p>Период с: ${position.startDate} по: ${position.finishDate}</p>
                                <strong>Должность: </strong>${position.position}<br>
                                <strong>Обязанности: </strong>${position.description}
                            </div>
                        </div>
                    </c:forEach>
                </c:forEach>
            </c:when>
        </c:choose>
    </c:forEach>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>