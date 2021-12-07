package com.evgeny13.basejava.model;

import java.util.Map;

public class ResumeTestData {

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        ResumeData resumeData = new ResumeData();

        resume.setContact(ContactType.PHONE, resumeData.getPhone());
        resume.setContact(ContactType.SKYPE, resumeData.getSkype());
        resume.setSection(SectionType.OBJECTIVE, new TextSection(resumeData.getObjective()));
        resume.setSection(SectionType.PERSONAL, new TextSection(resumeData.getPersonal()));
        resume.setSection(SectionType.ACHIEVEMENT, new ListSection(resumeData.getAchievement()));
        resume.setSection(SectionType.QUALIFICATIONS, new ListSection(resumeData.getQualification()));
        resume.setSection(SectionType.EXPERIENCE, new OrganizationSection(resumeData.getExperiences()));
        resume.setSection(SectionType.EDUCATION, new OrganizationSection(resumeData.getEducation()));
        return resume;
    }

    public static void printContacts(Map<ContactType, String> map) {
        for (Map.Entry<ContactType, String> entry : map.entrySet()) {
            System.out.println(entry.getKey().getTitle() + ": " + entry.getValue());
        }
    }

    public static void printSections(Map<SectionType, AbstractSection> map) {
        for (Map.Entry<SectionType, AbstractSection> entry : map.entrySet()) {
            System.out.println("\n" + entry.getKey().getTitle() + ": " + entry.getValue().toString());
        }
    }

    public static void main(String[] args) {
        Resume resume = createResume("uuid4", "Кислин Григорий");
        ResumeData resumeData = new ResumeData();

        resume.setContact(ContactType.PHONE, resumeData.getPhone());
        resume.setContact(ContactType.SKYPE, resumeData.getSkype());
        resume.setSection(SectionType.OBJECTIVE, new TextSection(resumeData.getObjective()));
        resume.setSection(SectionType.PERSONAL, new TextSection(resumeData.getPersonal()));
        resume.setSection(SectionType.ACHIEVEMENT, new ListSection(resumeData.getAchievement()));
        resume.setSection(SectionType.QUALIFICATIONS, new ListSection(resumeData.getQualification()));
        resume.setSection(SectionType.EXPERIENCE, new OrganizationSection(resumeData.getExperiences()));
        resume.setSection(SectionType.EDUCATION, new OrganizationSection(resumeData.getEducation()));
        System.out.println(resume.getFullName());
        printContacts(resume.getContacts());
        printSections(resume.getSections());
    }
}