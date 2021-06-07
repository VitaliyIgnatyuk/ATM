DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS card;
DROP TABLE IF EXISTS client;

CREATE TABLE client(
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    number INT NOT NULL,
    fullname VARCHAR(50) NOT NULL
);

CREATE TABLE card(
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    cardnum VARCHAR(16) UNIQUE NOT NULL,
    pin VARCHAR(4) NOT NULL,
    validity DATE NOT NULL,
    attemptscount INT NOT NULL DEFAULT 0,
    client_id UUID NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client(id)
);

CREATE TABLE account(
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    accountnum VARCHAR(20) UNIQUE NOT NULL,
    currency INT NOT NULL,
    amount NUMERIC(23,4),
    main BOOLEAN NOT NULL DEFAULT false,
    card_id UUID NOT NULL,
    FOREIGN KEY (card_id) REFERENCES card(id)
);

INSERT INTO client(number, fullname) VALUES
    (1, 'Иванов Иван Иванович'),
    (2, 'Петров Пётр Петрович'),
    (3, 'Сидоров Сидор Сидорович')
;

INSERT INTO card(cardnum, pin, validity, client_id) VALUES
    ('4111111111111111', '1111', '20221011', SELECT id FROM client WHERE number = 1),
    ('5100000000000008', '2222', '20221011', SELECT id FROM client WHERE number = 2),
    ('6011000000000004', '3333', '20221011', SELECT id FROM client WHERE number = 3),
    ('6390020000000003', '4444', '20221011', SELECT id FROM client WHERE number = 1),
    ('5555555555555599', '5555', '20221011', SELECT id FROM client WHERE number = 2),
    ('4444000000001111', '6666', '20221011', SELECT id FROM client WHERE number = 3),
    ('2200000000000004', '7777', '20221011', SELECT id FROM client WHERE number = 1),
    ('2200000000000012', '8888', '20221011', SELECT id FROM client WHERE number = 2),
    ('2200000000000020', '9999', '20221011', SELECT id FROM client WHERE number = 3)
;

INSERT INTO account(accountnum, currency, amount, main, card_id) VALUES
    ('40817810099910004301', 643, 1000.00, true, SELECT id FROM card WHERE cardNum = '4111111111111111'),
    ('40817810099910004302', 643, 2000.00, true, SELECT id FROM card WHERE cardNum = '5100000000000008'),
    ('40817810099910004303', 643, 3000.00, true, SELECT id FROM card WHERE cardNum = '6011000000000004'),
    ('40817978099910004304', 978, 4000.04, false, SELECT id FROM card WHERE cardNum = '6390020000000003'),
    ('40817840099910004305', 840, 5000.05, false, SELECT id FROM card WHERE cardNum = '5555555555555599'),
    ('40817156099910004306', 156, 6000.06, false, SELECT id FROM card WHERE cardNum = '4444000000001111'),
    ('40817978099910004307', 978, 7000.07, false, SELECT id FROM card WHERE cardNum = '2200000000000004'),
    ('40817840099910004308', 840, 8000.08, false, SELECT id FROM card WHERE cardNum = '2200000000000012'),
    ('40817156099910004309', 156, 9000.09, false, SELECT id FROM card WHERE cardNum = '2200000000000020')
;