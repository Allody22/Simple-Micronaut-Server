-- Создание таблицы пользователей (user)
CREATE TABLE IF NOT EXISTS "user"
(
    id       SERIAL PRIMARY KEY,    -- Уникальный идентификатор пользователя
    username VARCHAR(255) NOT NULL, -- Имя пользователя, уникальное
    password VARCHAR(255) NOT NULL  -- Пароль (скрытый)
);

-- Создание уникального индекса для поля username в таблице "user"
CREATE UNIQUE INDEX IF NOT EXISTS username_idx ON "user" (username);

-- Создание типа ENUM для e_category
CREATE TYPE e_category AS ENUM ('GASOLINE', 'CAFE', 'SERVICE', 'UNKNOWN_CATEGORY');

-- Создание таблицы покупок на заправке (gas_station_purchase)
CREATE TABLE IF NOT EXISTS gas_station_purchase
(
    id           SERIAL PRIMARY KEY,
    product_name VARCHAR(255)   NOT NULL,
    price        DECIMAL(10, 2) NOT NULL,
    e_category   e_category     NOT NULL, -- Используем тип ENUM
    date         TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id         BIGINT REFERENCES "user" (id) ON DELETE CASCADE
);
