-- ROLES
INSERT INTO roles (id, name) VALUES (1, 'ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'COMMENTATOR');
INSERT INTO roles (id, name) VALUES (3, 'AUTHOR');

-- CATEGORIES
INSERT INTO categories (id, name) VALUES (1, 'Technology');
INSERT INTO categories (id, name) VALUES (2, 'Sports');
INSERT INTO categories (id, name) VALUES (3, 'Politics');
INSERT INTO categories (id, name) VALUES (4, 'Cars');
INSERT INTO categories (id, name) VALUES (5, 'Mechanics');

-- USERS
INSERT INTO users (id, username, password, display_name, role_id) VALUES (1, 'Kosio', '$2a$10$KyIdZW94wZ2.GhGIEUZjb.gIuK87NkZ.QmpvsxCtfL65FG/DQPvWe', 'Koceto', 1);
INSERT INTO users (id, username, password, display_name, role_id) VALUES (2, 'Denislav', '$2a$10$WPn104xtro3TQBZH.Msg4.uYqY7sEhq2B/GhlYX.F5l7nNqph/xCC', 'Denkata', 2);
INSERT INTO users (id, username, password, display_name, role_id) VALUES (3, 'Daniel', '$2a$10$hxWpWCCY6/yhJG4ek5iE/u76rZypXAlNkg2FNs7q0CBKFN9TTnZIm', 'Dakata', 2);
INSERT INTO users (id, username, password, display_name, role_id) VALUES (4, 'Avram', '$2a$10$cgIdajFE1w5WwXfbj6yHR.Kw5gIB7uVxi1rQFBc3w3jLLgYjgij66', 'Acho', 2);

-- NEWS
INSERT INTO news (id, title, content, author, created_at, last_modified_at) VALUES (1, 'Tech News 1', 'Content of tech news', 'Person 1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO news (id, title, content, author, created_at, last_modified_at) VALUES (2, 'Sports News 1', 'Content of sports news', 'Person 23', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO news (id, title, content, author, created_at, last_modified_at) VALUES (3, 'Car News 1', 'Content of car news', 'Person 4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO news (id, title, content, author, created_at, last_modified_at) VALUES (4, 'Mechanic News 1', 'Content of mechanic news', 'Person 5', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- NEWS_CATEGORIES
INSERT INTO news_categories (news_id, category_id) VALUES (1, 1);
INSERT INTO news_categories (news_id, category_id) VALUES (1, 5);
INSERT INTO news_categories (news_id, category_id) VALUES (2, 2);
INSERT INTO news_categories (news_id, category_id) VALUES (3, 4);
INSERT INTO news_categories (news_id, category_id) VALUES (4, 5);
INSERT INTO news_categories (news_id, category_id) VALUES (4, 4);

-- COMMENTS
INSERT INTO comments (id, title, content, created_at, user_id, news_id) VALUES (1, 'Great Tech article', 'I really enjoyed this Tech post.', CURRENT_TIMESTAMP, 2, 1);
INSERT INTO comments (id, title, content, created_at, user_id, news_id) VALUES (2, 'Great Sports article', 'I really enjoyed this Sports post.', CURRENT_TIMESTAMP, 3, 2);
INSERT INTO comments (id, title, content, created_at, user_id, news_id) VALUES (3, 'Great Mechanic article', 'I really enjoyed this Mechanic post.', CURRENT_TIMESTAMP, 4, 4);
INSERT INTO comments (id, title, content, created_at, user_id, news_id) VALUES (4, 'Worst Mechanic article', 'I DID NOT enjoyed this Mechanic post.', CURRENT_TIMESTAMP, 2, 4);
