package com.evgeny13.basejava.web;

import com.evgeny13.basejava.Config;
import com.evgeny13.basejava.model.*;
import com.evgeny13.basejava.storage.Storage;
import com.evgeny13.basejava.util.DateUtil;
import com.evgeny13.basejava.util.HtmlUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private Storage storage; // = Config.get().getStorage();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");

        final boolean isNewResume = (uuid == null || uuid.length() == 0);

        Resume resume;

        if (isNewResume) {
            resume = new Resume(fullName);
        } else {
            resume = storage.get(uuid);
            resume.setFullName(fullName);
        }

        for (ContactType contactType : ContactType.values()) {
            String data = request.getParameter(contactType.name());

            if (HtmlUtil.isEmpty(data)) {
                resume.getContacts().remove(contactType);
            } else {
                resume.setContact(contactType, data);
            }
        }

        for (SectionType sectionType : SectionType.values()) {
            String content = request.getParameter(sectionType.name());
            String[] values = request.getParameterValues(sectionType.name());

            if (HtmlUtil.isEmpty(content) && values.length < 2) {
                resume.getSections().remove(sectionType);
            } else {
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.setSection(sectionType, new TextSection(content));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.setSection(sectionType, new ListSection(content.split("\\n")));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> orgs = new ArrayList<>();
                        String[] urls = request.getParameterValues(sectionType.name() + "url");

                        for (int i = 0; i < values.length; i++) {
                            String name = values[i];

                            if (!HtmlUtil.isEmpty(name)) {
                                List<Organization.Position> positions = new ArrayList<>();
                                String pfx = sectionType.name() + i;
                                String[] startDates = request.getParameterValues(pfx + "startDate");
                                String[] endDates = request.getParameterValues(pfx + "endDate");
                                String[] titles = request.getParameterValues(pfx + "title");
                                String[] descriptions = request.getParameterValues(pfx + "description");

                                for (int j = 0; j < titles.length; j++) {
                                    if (!HtmlUtil.isEmpty(titles[j])) {
                                        positions.add(new Organization.Position(DateUtil.parse(startDates[j]), DateUtil.parse(endDates[j]), titles[j], descriptions[j]));
                                    }
                                }

                                orgs.add(new Organization(new Link(name, urls[i]), positions));
                            }
                        }

                        resume.setSection(sectionType, new OrganizationSection(orgs));
                        break;
                }
            }
        }

        if (isNewResume) {
            storage.save(resume);
        } else {
            storage.update(resume);
        }

        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");

        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }

        Resume resume;

        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
                resume = storage.get(uuid);
                break;
            case "add":
                resume = Resume.EMPTY;
                break;
            case "edit":
                resume = storage.get(uuid);

                for (SectionType sectionType : SectionType.values()) {
                    AbstractSection section = resume.getSection(sectionType);

                    switch (sectionType) {
                        case OBJECTIVE:
                        case PERSONAL:
                            if (section == null) {
                                section = TextSection.EMPTY;
                            }
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            if (section == null) {
                                section = ListSection.EMPTY;
                            }
                            break;
                        case EXPERIENCE:
                        case EDUCATION:
                            OrganizationSection orgSection = (OrganizationSection) section;
                            List<Organization> emptyFirstOrganizations = new ArrayList<>();
                            emptyFirstOrganizations.add(Organization.EMPTY);

                            if (orgSection != null) {
                                for (Organization org : orgSection.getOrganizations()) {
                                    List<Organization.Position> emptyFirstPositions = new ArrayList<>();
                                    emptyFirstPositions.add(Organization.Position.EMPTY);
                                    emptyFirstPositions.addAll(org.getPositions());
                                    emptyFirstOrganizations.add(new Organization(org.getHomePage(), emptyFirstPositions));
                                }
                            }

                            section = new OrganizationSection(emptyFirstOrganizations);
                            break;
                    }

                    resume.setSection(sectionType, section);
                }
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }

        request.setAttribute("resume", resume);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }
}
