CREATE DATABASE IF NOT EXISTS smartbot;

USE smartbot;

-- =========================
-- USERS TABLE
-- =========================
CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'USER'
);

-- =========================
-- MESSAGES TABLE
-- =========================
CREATE TABLE IF NOT EXISTS messages (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    user_message TEXT NOT NULL,
    bot_response TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- =========================
-- KNOWLEDGE TABLE
-- =========================
CREATE TABLE IF NOT EXISTS knowledge (
    id INT PRIMARY KEY AUTO_INCREMENT,
    question VARCHAR(255) NOT NULL,
    answer TEXT NOT NULL
);

-- =========================
-- ADMIN USER
-- =========================
INSERT IGNORE INTO users (username, password, role)
VALUES ('admin', 'admin123', 'ADMIN');

-- =========================
-- SAMPLE KNOWLEDGE
-- =========================
INSERT INTO knowledge (question, answer)
SELECT 'hello', 'Hello! How can I help you?'
WHERE NOT EXISTS (
    SELECT 1 FROM knowledge WHERE question = 'hello'
);

INSERT INTO knowledge (question, answer)
SELECT 'java', 'Java is an object-oriented programming language.'
WHERE NOT EXISTS (
    SELECT 1 FROM knowledge WHERE question = 'java'
);

INSERT INTO knowledge (question, answer)
SELECT 'ai', 'AI stands for Artificial Intelligence.'
WHERE NOT EXISTS (
    SELECT 1 FROM knowledge WHERE question = 'ai'
);

INSERT INTO knowledge (question, answer)
SELECT 'what is chatbot', 'A chatbot is a software application that can communicate with users.'
WHERE NOT EXISTS (
    SELECT 1 FROM knowledge WHERE question = 'what is chatbot'
);

INSERT INTO knowledge (question, answer)
SELECT 'what is jdbc', 'JDBC stands for Java Database Connectivity. It allows Java applications to communicate with databases.'
WHERE NOT EXISTS (
    SELECT 1 FROM knowledge WHERE question = 'what is jdbc'
);