CREATE TABLE chess_game
(
    `id`         INT         NOT NULL,
    `turn`       VARCHAR(20) NOT NULL,
    `play_state` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE pieces
(
    `id`            INT         NOT NULL AUTO_INCREMENT,
    `chess_game_id` INT         NOT NULL,
    `color`         VARCHAR(20) NOT NULL,
    `type`          VARCHAR(20) NOT NULL,
    `file`          VARCHAR(20) NOT NULL,
    `rank`          VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`chess_game_id`) REFERENCES `chess_game` (`id`)
);


DELETE
FROM pieces;
DELETE
FROM chess_game;

