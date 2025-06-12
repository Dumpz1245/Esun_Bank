CREATE TABLE User (
  user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_name VARCHAR(100),
  email VARCHAR(255),
  password VARCHAR(255),
  cover_image VARCHAR(255),
  biography TEXT,
  phone_number VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE Post (
  post_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  content TEXT,
  image VARCHAR(255),
  created_at DATETIME,
  user_id BIGINT,
  FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE Comment (
  comment_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  content TEXT,
  created_at DATETIME,
  user_id BIGINT,
  post_id BIGINT,
  FOREIGN KEY (user_id) REFERENCES User(user_id),
  FOREIGN KEY (post_id) REFERENCES Post(post_id)
);
