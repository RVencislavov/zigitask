
CREATE TABLE island_matrix (
                               id BIGINT IDENTITY(1,1) PRIMARY KEY,
                               rows INT NOT NULL,
                               cols INT NOT NULL,
                               matrix VARCHAR(MAX) NOT NULL
);
