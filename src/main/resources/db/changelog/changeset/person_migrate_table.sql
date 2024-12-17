INSERT INTO person (name, balance)
VALUES ('artem' , 0);
INSERT INTO car (person_id, model)
VALUES (1, 'niva');
INSERT INTO passport (date, number, person_id)
VALUES ('2024-12-01', 234561,1);
INSERT INTO house(number_house, street)
VALUES(1, 'qwerty');
INSERT INTO person_house(person_id, house_id)
VALUES (1, 1);
ALTER TABLE person ADD COLUMN gender VARCHAR(10);
ALTER TABLE person ADD COLUMN surname VARCHAR(10);
INSERT INTO person (name, balance, gender, surname)
VALUES ('artem', 0, 'MALE', 'artemov')




