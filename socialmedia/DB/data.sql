
INSERT INTO User (user_name, phone_number, email, password, cover_image, biography) VALUES
('Alex', '12345', 'alex@example.com', 'hashedPassword1', 'default.jpg', 'Hi I am Alex.'),
('Jamie', '67890', 'jamie@example.com', 'hashedPassword2', 'default.jpg', 'Jamie here!');


INSERT INTO Post (content, image, created_at, user_id) VALUES
('Hello world!', NULL, NOW(), 1),
('Another post here', NULL, NOW(), 2);


INSERT INTO Comment (content, created_at, user_id, post_id) VALUES
('Nice post!', NOW(), 2, 1),
('Thanks!', NOW(), 1, 1);
