package com.evgeny13.basejava.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        String testUuid = "uuid";
        String testFullName = "fullName";
        Resume test = resumeCreation(testUuid, testFullName);
        System.out.println(test.getContacts());
        System.out.println(test.getSections());
    }

    public static Resume resumeCreation(String uuid, String fullName) {
        Resume test = new Resume(uuid, fullName);
        Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);

        contacts.put(ContactType.PHONE_NUMBER, "+7(921) 855-0482 \n");
        contacts.put(ContactType.SKYPE, "grigory.kislin \n");
        contacts.put(ContactType.EMAIL, "gkislin@yandex.ru \n");
        contacts.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin \n");
        contacts.put(ContactType.GITHUB, "https://github.com/gkislin \n");
        contacts.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473 \n");
        contacts.put(ContactType.HOMEPAGE, "http://gkislin.ru/ \n");

//        Map<SectionType, Section> section = new EnumMap<>(SectionType.class);
//        section.put(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного" +
//                " обучения по Java Web и Enterprise технологиям \n"));
//
//        section.put(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная" +
//                " логика, креативность, инициативность. Пурист кода и архитектуры. \n"));
//
//
//        ArrayList<String> achievements = new ArrayList<>();
//        achievements.add("Организация команды и успешная реализация Java проектов для сторонних заказчиков: " +
//                "приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей" +
//                " спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot" +
//                " + Vaadin проект для комплексных DIY смет \n");
//
//        achievements.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
//                "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное " +
//                "взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. " +
//                "Более 3500 выпускников. \n");
//
//        achievements.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike." +
//                " Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk. \n");
//
//        achievements.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция" +
//                " с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке:" +
//                " Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей," +
//                " интеграция CIFS/SMB java сервера.\n");
//
//        achievements.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, " +
//                "Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга. \n");
//
//        achievements.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов " +
//                "(SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии" +
//                " через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга" +
//                " системы по JMX (Jython/ Django). \n");
//
//        achievements.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat," +
//                " Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа. \n");
//
//        section.put(SectionType.ACHIEVEMENT, new ListSection(achievements));
//
//
//        ArrayList<String> qualification = new ArrayList<>();
//        qualification.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2 \n");
//
//        qualification.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce \n");
//
//        qualification.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL," +
//                " SQLite, MS SQL, HSQLDB \n");
//
//        qualification.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy \n");
//
//        qualification.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts \n");
//
//        qualification.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC," +
//                " Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT)," +
//                " Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements). \n");
//
//        qualification.add("Python: Django. \n");
//
//        qualification.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js \n");
//
//        qualification.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka \n");
//
//        qualification.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, " +
//                "DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2," +
//                " LDAP, OAuth1, OAuth2, JWT. \n");
//
//        qualification.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix \n");
//
//        qualification.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway," +
//                " Nagios, iReport, OpenCmis, Bonita, pgBouncer \n");
//
//        qualification.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, " +
//                "архитектурных шаблонов, UML, функционального программирования \n");
//
//        qualification.add("Родной русский, английский \"upper intermediate\" \n");
//
//        section.put(SectionType.QUALIFICATIONS, new ListSection(qualification));
//
//
//        List<Organization> experience = new ArrayList<>();
//        experience.add(new Organization("Java Online Projects", "http://javaops.ru/",
//                new Organization.Position(2013, Month.OCTOBER,
//                        "Автор проекта.", "Создание, организация проведение Java онлайн " +
//                        "проектов и стажировок. \n")));
//
//        experience.add(new Organization("Wrike", "https://www.wrike.com/", new Organization.Position(
//                2014, Month.OCTOBER, 2016, Month.JANUARY, "Старший разработчик (backend)",
//                "Проектирование и разработка онлайн платформы управления проектами Wrike " +
//                        "(Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная " +
//                        "аутентификация, авторизация по OAuth1, OAuth2, JWT SSO. \n")));
//
//        experience.add(new Organization("RIT Center", null, new Organization.Position(2012, Month.APRIL,
//                2014, Month.OCTOBER, "Java архитектор",
//                "Организация процесса разработки системы ERP для разных окружений: релизная политика," +
//                        " версионирование, ведение" +
//                        " CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), " +
//                        "AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS," +
//                        " BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция" +
//                        " Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin " +
//                        "development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis," +
//                        " Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python \n")));
//
//        experience.add(new Organization("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/",
//                new Organization.Position(2010, Month.DECEMBER, 2012, Month.APRIL, "Ведущий программист",
//                        "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, " +
//                                "SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация " +
//                                "RIA-приложения для администрирования, мониторинга и анализа результатов в области " +
//                                "алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock," +
//                                " Commet, HTML5. \n")));
//
//        experience.add(new Organization("Yota", "https://www.yota.ru/",
//                new Organization.Position(2008, Month.JUNE, 2010, Month.DECEMBER, "Ведущий специалист",
//                        "Дизайн и " + "имплементация Java EE фреймворка для отдела \"Платежные Системы\"" +
//                                " (GlassFish v2.1, v3, OC4J, EJB3," + " JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS," +
//                                " Maven2). Реализация администрирования, статистики и " + "мониторинга фреймворка. " +
//                                "Разработка online JMX клиента (Python/ Jython, Django, ExtJS) \n")));
//
//        experience.add(new Organization("Enkata", "http://enkata.com/",
//                new Organization.Position(2007, Month.MARCH, 2008, Month.JUNE, "Разработчик ПО",
//                        "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, " +
//                                "Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining). \n")));
//
//        experience.add(new Organization("Siemens AG", "https://www.siemens.com/ru/ru/home.html",
//                new Organization.Position(2005, Month.JANUARY, 2007, Month.FEBRUARY,
//                        "Разработчик ПО", "Разработка информационной модели, проектирование " +
//                        "интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix). \n")));
//
//        experience.add(new Organization("Alcatel", "http://www.alcatel.ru/",
//                new Organization.Position(1997, Month.SEPTEMBER, 2005, Month.JANUARY, "Инженер по " +
//                        "аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО " +
//                        "цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM). \n")));
//
//        section.put(SectionType.EXPERIENCE, new OrganizationSection(experience));
//
//
//        List<Organization> education = new ArrayList<>();
//        education.add(new Organization("Coursera", "https://www.coursera.org/course/progfun",
//                new Organization.Position(2013, Month.MARCH, 2013, Month.MAY, "'Functional " +
//                        "Programming Principles in Scala' by Martin Odersky", "\n")));
//
//        education.add(new Organization("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
//                new Organization.Position(2011, Month.MARCH, 2011, Month.APRIL,
//                        "Курс 'Объектно-ориентированный анализ ИС. " +
//                                "Концептуальное моделирование на UML.'", "\n")));
//
//        education.add(new Organization("Siemens AG", "http://www.siemens.ru/",
//                new Organization.Position(2005, Month.JANUARY, 2005, Month.APRIL, "3 месяца " +
//                        "обучения мобильным IN сетям (Берлин)", "\n")));
//
//        education.add(new Organization("Alcatel", "http://www.alcatel.ru/",
//                new Organization.Position(1997, Month.SEPTEMBER, 1998, Month.MARCH, "6 месяцев " +
//                        "обучения цифровым телефонным сетям (Москва)", "\n")));
//
//        education.add(new Organization("Санкт-Петербургский национальный исследовательский университет " +
//                "информационных технологий, механики и оптики", "http://www.ifmo.ru/",
//                new Organization.Position(1993, Month.SEPTEMBER, 1996, Month.JULY, "Аспирантура " +
//                        "(программист С, С++)", "\n"),
//                new Organization.Position(1987, Month.SEPTEMBER, 1993, Month.JULY, "Инженер (программист Fortran, C)",
//                        "\n")));
//
//        education.add(new Organization("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/",
//                new Organization.Position(1984, Month.SEPTEMBER, 1987, Month.JUNE, "Закончил" +
//                        " с отличием", "\n")));
//
//        section.put(SectionType.EDUCATION, new OrganizationSection(education));


        Map<SectionType, Section> section = new EnumMap<>(SectionType.class);
        section.put(SectionType.OBJECTIVE, new TextSection(""));
        section.put(SectionType.PERSONAL, new TextSection(""));


        ArrayList<String> achievements = new ArrayList<>();
        achievements.add("");
        section.put(SectionType.ACHIEVEMENT, new ListSection(achievements));


        ArrayList<String> qualification = new ArrayList<>();
        qualification.add("");
        section.put(SectionType.QUALIFICATIONS, new ListSection(qualification));


        List<Organization> experience = new ArrayList<>();
        experience.add(new Organization("", "", new Organization.Position()));
        section.put(SectionType.EXPERIENCE, new OrganizationSection(experience));


        List<Organization> education = new ArrayList<>();
        education.add(new Organization("", "", new Organization.Position()));
        section.put(SectionType.EDUCATION, new OrganizationSection(education));

        test.setContact(contacts);
        if (section == null) {
            test.setSection(section);
        }
        return test;
    }
}
