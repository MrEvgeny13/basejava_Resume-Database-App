package com.evgeny13.basejava.model;

import com.evgeny13.basejava.util.DateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class ResumeData {
    private final String fullName = "Григорий Кислин";
    private final String phone = "+7(921) 855-0482";
    private final String skype = "grigory.kislin";

    private final String personal = "Аналитический склад ума, сильная логика, " +
            "креативность, инициативность. Пурист кода и архитектуры.";
    private final String objective = "Ведущий стажировок и корпоративного обучения " +
            "по Java Web и Enterprise технологиям";

    private final List<String> achievement = Arrays.asList("С 2013 года: разработка проектов \"Разработка Web приложения\"," +
                    "\"Java Enterprise\", \"Многомодульный maven.\nМногопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                    "Удаленное взаимодействие (JMS/AKKA).\"\nОрганизация онлайн стажировок и ведение проектов. Более 1000 выпускников. ",
            "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio,\n" +
                    "DuoSecurity, Google Authenticator, Jira, Zendesk.");

    private final List<String> qualification = Arrays.asList("JEE AS: " +
            "GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2 ", "Version control: Subversion, Git, Mercury, ClearCase, Perforce");

    private final Organization javaOnlineProjects = new Organization("Java Online Projects", "http://javaops.ru/",
            new Organization.Position(DateUtil.of(2013, Month.OCTOBER), LocalDate.now(), "Автор проекта",
                    "Создание, организация и проведение Java онлайн проектов и стажировок."));
    private final Organization university = new Organization("Санкт-Петербургский национальный исследовательский университет " +
            "информационных технологий, механики и оптики", "www.ifmo.ru", new Organization.Position(DateUtil.of(1993, Month.SEPTEMBER),
            DateUtil.of(1996, Month.JULY), "Аспирантура", "(программист С, С++)"),
            new Organization.Position(DateUtil.of(1987, Month.SEPTEMBER),
                    DateUtil.of(1993, Month.JULY), "Инженер", "(программист Fortran, C)"));
    private final List<Organization> organizations = Arrays.asList(javaOnlineProjects);
    private final List<Organization> education = Arrays.asList(university);

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getSkype() {
        return skype;
    }

    public String getPersonal() {
        return personal;
    }

    public String getObjective() {
        return objective;
    }

    public List<String> getAchievement() {
        return achievement;
    }

    public List<String> getQualification() {
        return qualification;
    }

    public List<Organization> getExperiences() {
        return organizations;
    }

    public List<Organization> getEducation() {
        return education;
    }
}
