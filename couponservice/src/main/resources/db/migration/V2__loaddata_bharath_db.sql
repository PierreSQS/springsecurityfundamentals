INSERT INTO user(first_name,last_name,email,password) values ('doug','bailey','doug@bailey.com','{bcrypt}$2a$10$U2STWqktwFbvPPsfblVeIuy11vQ1S/0LYLeXQf1ZL0cMXc9HuTEA2');
INSERT INTO user(first_name,last_name,email,password) values ('john','ferguson','john@ferguson.com','{bcrypt}$2a$10$YzcbPL.fnzbWndjEcRkDmO1E4vOvyVYP5kLsJvtZnR1f8nlXjvq/G');

INSERT INTO role values(1,'ROLE_ADMIN');
INSERT INTO role values(2,'ROLE_USER');

INSERT INTO user_role values(1,1);
INSERT INTO user_role values(2,2);