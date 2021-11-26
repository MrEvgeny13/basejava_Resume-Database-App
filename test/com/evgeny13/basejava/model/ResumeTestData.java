package com.evgeny13.basejava.model;

import java.util.Map;

public class ResumeTestData {

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        ResumeData resumeData = new ResumeData();
        /*resume.addContact(ContactType.PHONE, resumeData.getPhone());
        resume.addContact(ContactType.SKYPE, resumeData.getSkype());
        resume.addSection(SectionType.OBJECTIVE, new TextSection(resumeData.getObjective()));
        resume.addSection(SectionType.PERSONAL, new TextSection(resumeData.getPersonal()));
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(resumeData.getAchievement()));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection(resumeData.getQualification()));
        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(resumeData.getExperiences()));
        resume.addSection(SectionType.EDUCATION, new OrganizationSection(resumeData.getEducation()));*/
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
        resume.addContact(ContactType.PHONE, resumeData.getPhone());
        resume.addContact(ContactType.SKYPE, resumeData.getSkype());
        resume.addSection(SectionType.OBJECTIVE, new TextSection(resumeData.getObjective()));
        resume.addSection(SectionType.PERSONAL, new TextSection(resumeData.getPersonal()));
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(resumeData.getAchievement()));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection(resumeData.getQualification()));
        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(resumeData.getExperiences()));
        resume.addSection(SectionType.EDUCATION, new OrganizationSection(resumeData.getEducation()));
        System.out.println(resume.getFullName());
        printContacts(resume.getContacts());
        printSections(resume.getSections());
    }
}