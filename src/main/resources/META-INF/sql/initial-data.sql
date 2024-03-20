-- Inserção de dados na tabela field_activity
insert into field_activity (id, description) values (1, 'Distribuição de alimentos');
insert into field_activity (id, description) values (2, 'Telecomunicações');
insert into field_activity (id, description) values (3, 'Vestuário');
insert into field_activity (id, description) values (4, 'Lavanderia');
insert into field_activity (id, description) values (5, 'Gráfica');
insert into field_activity (id, description) values (6, 'Mecânica');
insert into field_activity (id, description) values (7, 'Turismo');
insert into field_activity (id, description) values (8, 'Saúde');
insert into field_activity (id, description) values (9, 'Educação');
insert into field_activity (id, description) values (10, 'Lazer');

-- Inserção de dados na tabela company
insert into company (id, cnpj, trade_name, corporate_name, companyType, fundation_date, field_activity_id) values (1, '70.311.193/0001-87', 'Mercado do Foreman', 'Foreman Mercado e Alimentos Ltda', 'LTDA', '2009-03-02', 1);
insert into company (id, cnpj, trade_name, corporate_name, companyType, fundation_date, field_activity_id) values (2, '52.822.994/0001-25', 'Chase Satélite', 'Chase Telecom S.A.', 'SA', '1997-12-10', 2);
insert into company (id, cnpj, trade_name, corporate_name, companyType, fundation_date, field_activity_id) values (3, '41.952.519/0001-57', 'Allison Cameron', 'Allison Cameron', 'MEI', '2014-10-15', 3);
insert into company (id, cnpj, trade_name, corporate_name, companyType, fundation_date, field_activity_id) values (4, '16.134.777/0001-89', 'Gregory House Inovação', 'Gregory House EIRELI ME', 'EIRELI', '2009-03-02', 4);
