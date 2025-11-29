INSERT INTO ingredient (id, name)
VALUES ('e7b2c1f8-8a1b-4e3b-a0d9-1f2b3c4d5e6f', 'Mąka'),
       ('a4d1b2c3-4e5f-6789-0abc-def123456789', 'Cukier'),
       ('b1c2d3e4-5678-90ab-cdef-1234567890ab', 'Sól'),
       ('c3d4e5f6-7890-12ab-cdef-34567890abcd', 'Masło'),
       ('d5e6f7a8-9012-34bc-def0-567890abcdef', 'Mleko'),
       ('f7a8b9c0-1234-56de-f789-67890abcdef1', 'Jajko'),
       ('123e4567-e89b-12d3-a456-426614174000', 'Drożdże'),
       ('234e5678-e89b-12d3-a456-426614174001', 'Kurczak'),
       ('345e6789-e89b-12d3-a456-426614174002', 'Ryż'),
       ('456e7890-e89b-12d3-a456-426614174003', 'Czosnek');

INSERT INTO recipe (id, name, description)
VALUES ('9f1b2c3d-4e5f-6789-0abc-def123456001', 'Naleśniki', 'Klasyczne cienkie naleśniki.'),
       ('0a1b2c3d-4e5f-6789-0abc-def123456002', 'Chleb domowy', 'Prosty chleb na drożdżach.'),
       ('1b2c3d4e-5f67-890a-bcde-f12345600345', 'Kurczak z ryżem', 'Szybkie danie obiadowe.'),
       ('2c3d4e5f-6789-0abc-def1-234567890456', 'Jajecznica', 'Prosta jajecznica na maśle.'),
       ('3d4e5f67-8901-2abc-def3-456789012567', 'Ciasto kruche', 'Klasyczne kruche ciasto.');

INSERT INTO recipe_steps (recipe_id, step)
VALUES ('9f1b2c3d-4e5f-6789-0abc-def123456001', 'Wymieszaj składniki.'),
       ('9f1b2c3d-4e5f-6789-0abc-def123456001', 'Usmaż cienkie naleśniki.');

INSERT INTO recipe_steps (recipe_id, step)
VALUES ('0a1b2c3d-4e5f-6789-0abc-def123456002', 'Zagnieć ciasto.'),
       ('0a1b2c3d-4e5f-6789-0abc-def123456002', 'Odstaw do wyrośnięcia.'),
       ('0a1b2c3d-4e5f-6789-0abc-def123456002', 'Upiecz w piekarniku.');

INSERT INTO recipe_steps (recipe_id, step)
VALUES ('1b2c3d4e-5f67-890a-bcde-f12345600345', 'Ugotuj ryż.'),
       ('1b2c3d4e-5f67-890a-bcde-f12345600345', 'Usmaż kurczaka z przyprawami.');

INSERT INTO recipe_steps (recipe_id, step)
VALUES ('2c3d4e5f-6789-0abc-def1-234567890456', 'Rozbij jajka.'),
       ('2c3d4e5f-6789-0abc-def1-234567890456', 'Podsmaż na maśle.');

INSERT INTO recipe_steps (recipe_id, step)
VALUES ('3d4e5f67-8901-2abc-def3-456789012567', 'Wymieszaj składniki.'),
       ('3d4e5f67-8901-2abc-def3-456789012567', 'Włóż ciasto do lodówki.'),
       ('3d4e5f67-8901-2abc-def3-456789012567', 'Upiecz w piekarniku.');

INSERT INTO recipe_ingredient (recipe_id, ingredient_id)
VALUES ('9f1b2c3d-4e5f-6789-0abc-def123456001', 'e7b2c1f8-8a1b-4e3b-a0d9-1f2b3c4d5e6f'),
       ('9f1b2c3d-4e5f-6789-0abc-def123456001', 'd5e6f7a8-9012-34bc-def0-567890abcdef'),
       ('9f1b2c3d-4e5f-6789-0abc-def123456001', 'f7a8b9c0-1234-56de-f789-67890abcdef1'),
       ('9f1b2c3d-4e5f-6789-0abc-def123456001', 'b1c2d3e4-5678-90ab-cdef-1234567890ab');

INSERT INTO recipe_ingredient (recipe_id, ingredient_id)
VALUES ('0a1b2c3d-4e5f-6789-0abc-def123456002', 'e7b2c1f8-8a1b-4e3b-a0d9-1f2b3c4d5e6f'),
       ('0a1b2c3d-4e5f-6789-0abc-def123456002', 'b1c2d3e4-5678-90ab-cdef-1234567890ab'),
       ('0a1b2c3d-4e5f-6789-0abc-def123456002', '123e4567-e89b-12d3-a456-426614174000'),
       ('0a1b2c3d-4e5f-6789-0abc-def123456002', 'd5e6f7a8-9012-34bc-def0-567890abcdef');

INSERT INTO recipe_ingredient (recipe_id, ingredient_id)
VALUES ('1b2c3d4e-5f67-890a-bcde-f12345600345', '234e5678-e89b-12d3-a456-426614174001'),
       ('1b2c3d4e-5f67-890a-bcde-f12345600345', '345e6789-e89b-12d3-a456-426614174002'),
       ('1b2c3d4e-5f67-890a-bcde-f12345600345', '456e7890-e89b-12d3-a456-426614174003'),
       ('1b2c3d4e-5f67-890a-bcde-f12345600345', 'b1c2d3e4-5678-90ab-cdef-1234567890ab');

INSERT INTO recipe_ingredient (recipe_id, ingredient_id)
VALUES ('2c3d4e5f-6789-0abc-def1-234567890456', 'f7a8b9c0-1234-56de-f789-67890abcdef1'),
       ('2c3d4e5f-6789-0abc-def1-234567890456', 'c3d4e5f6-7890-12ab-cdef-34567890abcd'),
       ('2c3d4e5f-6789-0abc-def1-234567890456', 'b1c2d3e4-5678-90ab-cdef-1234567890ab');

INSERT INTO recipe_ingredient (recipe_id, ingredient_id)
VALUES ('3d4e5f67-8901-2abc-def3-456789012567', 'e7b2c1f8-8a1b-4e3b-a0d9-1f2b3c4d5e6f'),
       ('3d4e5f67-8901-2abc-def3-456789012567', 'c3d4e5f6-7890-12ab-cdef-34567890abcd'),
       ('3d4e5f67-8901-2abc-def3-456789012567', 'a4d1b2c3-4e5f-6789-0abc-def123456789'),
       ('3d4e5f67-8901-2abc-def3-456789012567', 'f7a8b9c0-1234-56de-f789-67890abcdef1');

