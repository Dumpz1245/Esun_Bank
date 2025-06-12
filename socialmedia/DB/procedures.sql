DELIMITER //

CREATE PROCEDURE insert_comment (
  IN p_content TEXT,
  IN p_user_id BIGINT,
  IN p_post_id BIGINT,
  OUT p_comment_id BIGINT
)
BEGIN
  INSERT INTO Comment(content, user_id, post_id, created_at)
  VALUES (p_content, p_user_id, p_post_id, NOW());

  SET p_comment_id = LAST_INSERT_ID();
END //

DELIMITER ;
