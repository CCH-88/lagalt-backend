INSERT INTO freelancer_history (id,applied, clicked, participated, viewed)
VALUES (nextval('freelancer_history_id_seq'),ARRAY [0],ARRAY  [0],ARRAY  [0],ARRAY  [0]);
INSERT INTO freelancer_history (id,applied, clicked, participated, viewed)
VALUES (nextval('freelancer_history_id_seq'),ARRAY [0],ARRAY  [0],ARRAY  [0],ARRAY  [0]);
INSERT INTO freelancer_history (id,applied, clicked, participated, viewed)
VALUES (nextval('freelancer_history_id_seq'),ARRAY [0],ARRAY  [0],ARRAY  [0],ARRAY  [0]);

INSERT INTO freelancer (id,description, email, google_token, portfolio, skills, username, freelancer_history_id)
VALUES(nextval('freelancer_id_seq'),'A mega awesome description', 'anders.bille.lind@gmail.com', 'abcdefg token', 'https://billelind.dev', ARRAY['spring boot', 'react'], 'billelind',1);

INSERT INTO freelancer (id, description, email, google_token, portfolio, skills, username, freelancer_history_id)
VALUES(nextval('freelancer_id_seq'),'A mega awesome description', 'email@example.com', 'abcdefg token', 'https://dev.to',ARRAY ['spring boot', 'react'],'email',2);

INSERT INTO freelancer (id,description, email, google_token, portfolio, skills, username, freelancer_history_id)
VALUES(nextval('freelancer_id_seq'),'A mega awesome description', 'levelio@example.com', 'abcdefg token', 'https://levels.io',ARRAY ['spring boot', 'react'],'levels.io',3);

INSERT INTO chat (id) values (nextval('chat_id_seq'));
INSERT INTO chat (id) values (nextval('chat_id_seq'));

INSERT INTO project (description, name,owner_id, progress, project_images, project_chat, field)
VALUES ('Project description', 'Awesome project 1',2, 'founding',ARRAY['image 1', 'image 2'],2, 'Web Development');

INSERT INTO project (description, name,owner_id, progress, project_images, project_chat, field)
VALUES ('Project description', 'Awesome project 2', 1, 'founding',ARRAY['image 1', 'image 2'], 1, 'Game Development');

INSERT INTO project_freelancer (freelancer_id, project_id, motivation)
VALUES (1, 2, 'weee please let me join this awesome project');

INSERT INTO project_freelancer (freelancer_id, project_id, motivation)
VALUES (1, 1, 'weee please let me join this awesome project');

INSERT INTO project_freelancer (freelancer_id, project_id, motivation)
VALUES (2, 1, 'weee please let me join this awesome project');

INSERT INTO project_freelancer (freelancer_id, project_id, motivation)
VALUES (2, 2, 'weee please let me join this awesome project');


INSERT INTO message (date_time, text, chat_id, freelancer_id)
VALUES ('2022-10-13T10:40:26.166486237','text message 1', 1, 1);

INSERT INTO message (date_time, text, chat_id, freelancer_id)
VALUES ('2022-10-13T10:40:26.166486237','text message 2', 1, 1);

INSERT INTO message (date_time, text, chat_id, freelancer_id)
VALUES ('2022-10-13T10:40:26.166486237','text message 3', 2, 2);