INSERT INTO teams (name, commission, balance) VALUES
('Barcelona', 5, 10000000.00),
('Real Madrid', 7, 120000000.50),
('Manchester United', 6, 95000000.75),
('Bayern Munich', 4, 110500000.00);

INSERT INTO players (name, age, experience, team_id) VALUES
('Robert Lewandowski', 35, 210, (SELECT id FROM teams WHERE name = 'Barcelona')),
('Pedri', 21, 48, (SELECT id FROM teams WHERE name = 'Barcelona')),
('Vinicius Junior', 23, 60, (SELECT id FROM teams WHERE name = 'Real Madrid')),
('Jude Bellingham', 20, 54, (SELECT id FROM teams WHERE name = 'Real Madrid')),
('Bruno Fernandes', 26, 96, (SELECT id FROM teams WHERE name = 'Manchester United')),
('Harry Maguire', 31, 180, (SELECT id FROM teams WHERE name = 'Manchester United')),
('Jamal Musiala', 21, 48, (SELECT id FROM teams WHERE name = 'Bayern Munich')),
('Harry Kane', 30, 192, (SELECT id FROM teams WHERE name = 'Bayern Munich'));