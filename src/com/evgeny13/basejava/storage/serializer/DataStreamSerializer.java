package com.evgeny13.basejava.storage.serializer;

import com.evgeny13.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            writeWithException(contacts.entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            Map<SectionType, Section> sections = r.getSections();
            writeWithException(sections.entrySet(), dos, entry -> {
                SectionType sectionType = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(((TextSection) section).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeWithException(((ListSection) section).getList(), dos, dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeWithException(((OrganizationSection) section).getOrganizations(), dos, organization -> {
                            Link homePage = organization.getHomePage();
                            String nameHomePage = homePage.getName();
                            String url = homePage.getUrl();
                            dos.writeUTF(nameHomePage);
                            if (url == null) {
                                dos.writeUTF("null");
                            } else {
                                dos.writeUTF(url);
                            }
                            writeWithException(organization.getPositions(), dos, position -> {
                                LocalDate startDate = position.getStartDate();
                                writeLocalDate(dos, startDate);
                                LocalDate finishDate = position.getEndDate();
                                writeLocalDate(dos, finishDate);
                                String positionName = position.getTitle();
                                dos.writeUTF(positionName);
                                String additionalInformation = position.getDescription();
                                if (additionalInformation == null) {
                                    dos.writeUTF("null");
                                } else {
                                    dos.writeUTF(additionalInformation);
                                }
                            });
                        });
                        break;
                }
            });
        }
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }

    private <T> void writeWithException(Collection<T> collection, DataOutputStream dos, ElementWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T element : collection) {
            writer.write(element);
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate date) throws IOException {
        dos.writeInt(date.getYear());
        dos.writeInt(date.getMonth().getValue());
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readCollection(dis, () -> resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            Map<SectionType, Section> section = new EnumMap<>(SectionType.class);
            readCollection(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        section.put(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        section.put(sectionType, new ListSection(readArrayList(dis, dis::readUTF)));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizationList = new ArrayList<>();
                        List<Organization.Position> positionList = new ArrayList<>();
                        readArrayList(dis, () -> {
                            Link link = readLink(dis);
                            readArrayList(dis, () -> {
                                int startYear = dis.readInt();
                                Month startMonth = Month.of(dis.readInt());
                                int finishYear = dis.readInt();
                                Month finishMonth = Month.of(dis.readInt());
                                String positionName = dis.readUTF();
                                String additionalInformation = readAdditionalInformation(dis);
                                positionList.add(new Organization.Position(startYear, startMonth, finishYear, finishMonth, positionName, additionalInformation));
                                return positionList;
                            });
                            organizationList.add(new Organization(link, positionList));
                            section.put(sectionType, new OrganizationSection(organizationList));
                            return section;
                        });
                        break;
                }
            });
            resume.setSection(section);
            return resume;
        }
    }

    private <T> List<T> readArrayList(DataInputStream dis, ElementList reader) throws IOException {
        int size = dis.readInt();
        ArrayList<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add((T) reader.add());
        }
        return list;
    }

    private interface ElementReader {
        void collect() throws IOException;
    }

    private interface ElementList<T> {
        T add() throws IOException;
    }

    private void readCollection(DataInputStream dis, ElementReader reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.collect();
        }
    }

    private Link readLink(DataInputStream dis) throws IOException {
        String name = dis.readUTF();
        String url = dis.readUTF();
        if (url.equals("null")) {
            url = null;
        }
        Link link = new Link(name, url);
        return link;
    }

    private String readAdditionalInformation(DataInputStream dis) throws IOException {
        String additionalInformation = dis.readUTF();
        if (additionalInformation == "null") {
            additionalInformation = null;
        }
        return additionalInformation;
    }
}
