DELETE FROM  challenge_devsu_account.movement;
DELETE FROM  challenge_devsu_account.account;

INSERT INTO challenge_devsu_account.account (id, person_id, account_type, number_account, account_balance, created_date, state) VALUES ('1061d0fb-2e29-4a07-bb40-347bbf5826d0', '0a818933-087d-47f2-ad83-2f986ed087eb', 'Ahorros', 478758, 2000, CURRENT_TIMESTAMP, 'true');
INSERT INTO challenge_devsu_account.account (id, person_id, account_type, number_account, account_balance, created_date, state) VALUES ('40fa3234-ddc5-472c-91ed-9e8edd5d4b2b', 'a712d914-61ea-4623-8bd0-32c0f6545bfd', 'Corriente', 225487, 100, CURRENT_TIMESTAMP, 'true');
INSERT INTO challenge_devsu_account.account (id, person_id, account_type, number_account, account_balance, created_date, state) VALUES ('e22af830-6202-450a-aa12-3dbf4ef40a36', '026cc3c8-3a0c-4083-a05b-e908048c1b08', 'Ahorros', 495878, 0, CURRENT_TIMESTAMP, 'true');
INSERT INTO challenge_devsu_account.account (id, person_id, account_type, number_account, account_balance, created_date, state) VALUES ('fc4f2908-2d06-4efc-b210-6005222d06c0', 'a712d914-61ea-4623-8bd0-32c0f6545bfd', 'Ahorros', 496825, 540, CURRENT_TIMESTAMP, 'true');

INSERT INTO challenge_devsu_account.movement (id, account_id, movement_type, amount, balance, created_date) VALUES ('1061d0fb-2e29-4a07-bb40-347bbf5826d0', '1061d0fb-2e29-4a07-bb40-347bbf5826d0', 'debit', 575, 2000, CURRENT_TIMESTAMP);
INSERT INTO challenge_devsu_account.movement (id, account_id, movement_type, amount, balance, created_date) VALUES ('b4b7655b-3f26-4e4b-a6be-b519def41ab9', '40fa3234-ddc5-472c-91ed-9e8edd5d4b2b', 'credit', 600, 100, CURRENT_TIMESTAMP);
INSERT INTO challenge_devsu_account.movement (id, account_id, movement_type, amount, balance, created_date) VALUES ('de98b53d-e994-4146-ac15-82a675b384ff', 'e22af830-6202-450a-aa12-3dbf4ef40a36', 'credit', 150, 0, CURRENT_TIMESTAMP);
INSERT INTO challenge_devsu_account.movement (id, account_id, movement_type, amount, balance, created_date) VALUES ('e18c1a69-5328-424e-ab8d-6437a0924dc6', 'fc4f2908-2d06-4efc-b210-6005222d06c0', 'debit', 540, 540, CURRENT_TIMESTAMP);
