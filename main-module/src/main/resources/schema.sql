CREATE TABLE IF NOT EXISTS users (
  user_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  username VARCHAR(250) NOT NULL,
  email VARCHAR(254) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS categories (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name VARCHAR(50) NOT NULL UNIQUE,
  CONSTRAINT pk_categories PRIMARY KEY (id),
  CONSTRAINT uq_categories UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS events (
  event_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  annotation VARCHAR(2000) NOT NULL,
  category_id BIGINT NOT NULL REFERENCES categories(id),
  created_on TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  description VARCHAR(7000) NOT NULL,
  event_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  initiator_id BIGINT NOT NULL REFERENCES users(user_id),
  lat FLOAT NOT NULL,
  lon FLOAT NOT NULL,
  paid BOOLEAN NOT NULL,
  participant_limit INTEGER NOT NULL,
  published_on TIMESTAMP WITHOUT TIME ZONE,
  request_moderation BOOLEAN NOT NULL,
  state INTEGER NOT NULL,
  title VARCHAR(120) NOT NULL,
  confirmed_requests INTEGER NOT NULL,
  views BIGINT NOT NULL,
  CONSTRAINT pk_events PRIMARY KEY (event_id),
  CONSTRAINT fk_events_cat FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE IF NOT EXISTS compilations (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  pinned BOOLEAN NOT NULL,
  title VARCHAR(50) NOT NULL,
  CONSTRAINT pk_compilations PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS compilation_events (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  compilation_id BIGINT REFERENCES compilations(id),
  event_id BIGINT REFERENCES events(event_id),
  CONSTRAINT pk_compilation_events PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS requests (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  event_id BIGINT REFERENCES events(event_id),
  requester_id BIGINT NOT NULL REFERENCES users(user_id),
  status VARCHAR(20) NOT NULL,
  CONSTRAINT pk_requests PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS comment (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  author_id BIGINT NOT NULL REFERENCES users(user_id),
  event_id BIGINT REFERENCES events(event_id),
  text VARCHAR(2000) NOT NULL,
  created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  CONSTRAINT id PRIMARY KEY (id)
);
