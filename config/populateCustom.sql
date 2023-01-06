-- If after running the tests you see something like Name1, Name2, Name3 names in the list and want to change
-- it to more understandable ones, you can run this script

TRUNCATE resume, contact, section;

INSERT INTO resume (uuid, full_name)
VALUES ('id1', 'Bill Gates'),
       ('id2', 'Elon Musk'),
       ('id3', 'Test User');

INSERT INTO contact (type, value, resume_uuid)
VALUES ('PHONE_NUMBER', '(206) 709-3100', 'id1'),
       ('SKYPE', 'bill.gates', 'id1'),
       ('EMAIL', 'bill.gates@gatesfoundation.org', 'id1'),
       ('LINKEDIN', 'https://www.linkedin.com/in/williamhgates', 'id1'),
       ('STACKOVERFLOW', 'https://stackoverflow.com/users/3159424/bill-gates', 'id1'),
       ('HOMEPAGE', 'https://www.gatesnotes.com/', 'id1'),
       ('PHONE_NUMBER', '+1 800 613 8840', 'id2'),
       ('SKYPE', 'elon.musk', 'id2'),
       ('EMAIL', 'press@tesla.com', 'id2'),
       ('LINKEDIN', 'https://www.linkedin.com/company/elonmusk', 'id2'),
       ('HOMEPAGE', 'https://www.tesla.com/elon-musk', 'id2'),
       ('PHONE_NUMBER', '1234', 'id3'),
       ('SKYPE', 'test.user', 'id3'),
       ('EMAIL', 'testuser@gmail.com', 'id3'),
       ('LINKEDIN', 'https://www.linkedin.com/in/testuser', 'id3'),
       ('GITHUB', 'https://www.linkedin.com/in/testuser', 'id3');

-- Change to your own directory
INSERT INTO section (type, value, resume_uuid)
VALUES ('PERSONAL', '{"CLASSNAME":"com.evgeny13.basejava.model.TextSection","INSTANCE":{"text":"Responsibility, diligence"}}', 'id1'),
       ('OBJECTIVE', '{"CLASSNAME":"com.evgeny13.basejava.model.TextSection","INSTANCE":{"text":"CEO"}}', 'id1'),
       ('ACHIEVEMENT', '{"CLASSNAME":"com.evgeny13.basejava.model.ListSection","INSTANCE":{"list":["Billion-dollar turnover of the company"]}}', 'id1'),
       ('QUALIFICATIONS', '{"CLASSNAME":"com.evgeny13.basejava.model.ListSection","INSTANCE":{"list":["Top Manager"]}}', 'id1'),
       ('EXPERIENCE', '{"CLASSNAME":"com.evgeny13.basejava.model.OrganizationSection","INSTANCE":{"organizations":' ||
                      '[{"homePage":{"name":"Microsoft","url":"https://www.microsoft.com"},"positions":' ||
                      '[{"startDate":"1973-01-01","endDate":"2020-03-01","title":"CEO","description":"Strategic management"}]}]}}', 'id1'),
       ('EDUCATION', '{"CLASSNAME":"com.evgeny13.basejava.model.OrganizationSection","INSTANCE":{"organizations":' ||
                     '[{"homePage":{"name":"Harvard College","url":"https://college.harvard.edu/"},"positions":' ||
                     '[{"startDate":"1973-01-01","endDate":"1975-01-01","title":"Student","description":"Student"}]}]}}', 'id1'),
       ('PERSONAL', '{"CLASSNAME":"com.evgeny13.basejava.model.TextSection","INSTANCE":{"text":"Creativity, ingenuity"}}', 'id2'),
       ('OBJECTIVE', '{"CLASSNAME":"com.evgeny13.basejava.model.TextSection","INSTANCE":{"text":"CEO"}}', 'id2'),
       ('ACHIEVEMENT', '{"CLASSNAME":"com.evgeny13.basejava.model.ListSection","INSTANCE":{"list":["The richest man in the world"]}}', 'id2'),
       ('QUALIFICATIONS', '{"CLASSNAME":"com.evgeny13.basejava.model.ListSection","INSTANCE":{"list":["Top Manager"]}}', 'id2'),
       ('EXPERIENCE', '{"CLASSNAME":"com.evgeny13.basejava.model.OrganizationSection","INSTANCE":{"organizations":' ||
                      '[{"homePage":{"name":"Twitter","url":"https://twitter.com"},"positions":' ||
                      '[{"startDate":"2022-03-01","endDate":"3000-01-01","title":"CEO","description":"Strategic management"}]},' ||
                      '{"homePage":{"name":"Tesla","url":"https://www.tesla.com/"},"positions":' ||
                      '[{"startDate":"2004-01-01","endDate":"3000-01-01","title":"Founder","description":"Strategic management"}]},' ||
                      '{"homePage":{"name":"SpaceX","url":"https://www.spacex.com/"},"positions":' ||
                      '[{"startDate":"2002-01-01","endDate":"3000-01-01","title":"Founder","description":"Strategic management"}]}]}}', 'id2'),
       ('EDUCATION', '{"CLASSNAME":"com.evgeny13.basejava.model.OrganizationSection","INSTANCE":{"organizations":' ||
                     '[{"homePage":{"name":"University of Pennsylvania","url":"https://www.upenn.edu/"},"positions":' ||
                     '[{"startDate":"1991-01-01","endDate":"1997-01-01","title":"Student","description":"Student"}]}]}}', 'id2'),
       ('PERSONAL', '{"CLASSNAME":"com.evgeny13.basejava.model.TextSection","INSTANCE":{"text":"TestPersonal"}}', 'id3'),
       ('OBJECTIVE', '{"CLASSNAME":"com.evgeny13.basejava.model.TextSection","INSTANCE":{"text":"TestPosition"}}', 'id3'),
       ('ACHIEVEMENT', '{"CLASSNAME":"com.evgeny13.basejava.model.ListSection","INSTANCE":{"list":["TestAchievement"]}}', 'id3'),
       ('QUALIFICATIONS', '{"CLASSNAME":"com.evgeny13.basejava.model.ListSection","INSTANCE":{"list":["TestQualification"]}}', 'id3');