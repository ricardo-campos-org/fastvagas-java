INSERT INTO state (name, acronym) VALUES ('Santa Catarina', 'SC');
--INSERT INTO state (name, acronym) VALUES ('São Paulo', 'SP');
--INSERT INTO state (name, acronym) VALUES ('Rio de Janeiro', 'RJ');
--INSERT INTO state (name, acronym) VALUES ('Distrito Federal', 'DF');
--INSERT INTO state (name, acronym) VALUES ('Bahia', 'BA');
--INSERT INTO state (name, acronym) VALUES ('Ceará', 'CE');
--INSERT INTO state (name, acronym) VALUES ('Minas Gerais', 'MG');
--INSERT INTO state (name, acronym) VALUES ('Amazonas', 'AM');
--INSERT INTO state (name, acronym) VALUES ('Paraná', 'PR');
--INSERT INTO state (name, acronym) VALUES ('Pernambuco', 'PE');
--INSERT INTO state (name, acronym) VALUES ('Goiás', 'GO');
--INSERT INTO state (name, acronym) VALUES ('Pará', 'PA');
--INSERT INTO state (name, acronym) VALUES ('Rio Grande do Sul', 'RS');
--INSERT INTO state (name, acronym) VALUES ('Maranhão', 'MA');
--INSERT INTO state (name, acronym) VALUES ('Alagoas', 'AL');
--INSERT INTO state (name, acronym) VALUES ('Mato Grosso do Sul', 'MS');
--INSERT INTO state (name, acronym) VALUES ('Rio Grande do Norte', 'RN');
--INSERT INTO state (name, acronym) VALUES ('Piauí', 'PI');
--INSERT INTO state (name, acronym) VALUES ('Paraiba', 'PB');
--INSERT INTO state (name, acronym) VALUES ('Sergipe', 'SE');
--INSERT INTO state (name, acronym) VALUES ('Mato Grosso', 'MT');
--INSERT INTO state (name, acronym) VALUES ('Rondonia', 'RO');
--INSERT INTO state (name, acronym) VALUES ('Espírito Santo', 'ES');
--INSERT INTO state (name, acronym) VALUES ('Amapá', 'AP');

INSERT INTO city (name, state_id) VALUES ('Joinville', (SELECT id FROM state WHERE acronym = 'SC'));
--INSERT INTO city (name, state_id) VALUES ('São Paulo', (SELECT id FROM state WHERE acronym = 'SP'));
--INSERT INTO city (name, state_id) VALUES ('Rio de Janeiro', (SELECT id FROM state WHERE acronym = 'RJ'));
--INSERT INTO city (name, state_id) VALUES ('Brasília', (SELECT id FROM state WHERE acronym = 'DF'));
--INSERT INTO city (name, state_id) VALUES ('Salvador', (SELECT id FROM state WHERE acronym = 'BA'));
--INSERT INTO city (name, state_id) VALUES ('Fortaleza', (SELECT id FROM state WHERE acronym = 'CE'));
--INSERT INTO city (name, state_id) VALUES ('Belo Horizonte', (SELECT id FROM state WHERE acronym = 'MG'));
--INSERT INTO city (name, state_id) VALUES ('Manaus', (SELECT id FROM state WHERE acronym = 'AM'));
--INSERT INTO city (name, state_id) VALUES ('Curitiba', (SELECT id FROM state WHERE acronym = 'PR'));
--INSERT INTO city (name, state_id) VALUES ('Recife', (SELECT id FROM state WHERE acronym = 'PE'));
--INSERT INTO city (name, state_id) VALUES ('Goiânia', (SELECT id FROM state WHERE acronym = 'GO'));
--INSERT INTO city (name, state_id) VALUES ('Belém', (SELECT id FROM state WHERE acronym = 'PA'));
--INSERT INTO city (name, state_id) VALUES ('Porto Alegre', (SELECT id FROM state WHERE acronym = 'RS'));
--INSERT INTO city (name, state_id) VALUES ('Guarulhos', (SELECT id FROM state WHERE acronym = 'SP'));
--INSERT INTO city (name, state_id) VALUES ('Campinas', (SELECT id FROM state WHERE acronym = 'SP'));
--INSERT INTO city (name, state_id) VALUES ('São Luis', (SELECT id FROM state WHERE acronym = 'MA'));
--INSERT INTO city (name, state_id) VALUES ('São Gonçalo', (SELECT id FROM state WHERE acronym = 'RJ'));
--INSERT INTO city (name, state_id) VALUES ('Maceió', (SELECT id FROM state WHERE acronym = 'AL'));
--INSERT INTO city (name, state_id) VALUES ('Duque de Caxias', (SELECT id FROM state WHERE acronym = 'RJ'));
--INSERT INTO city (name, state_id) VALUES ('Campo Grande', (SELECT id FROM state WHERE acronym = 'MS'));
--INSERT INTO city (name, state_id) VALUES ('Natal', (SELECT id FROM state WHERE acronym = 'RN'));
--INSERT INTO city (name, state_id) VALUES ('Teresina', (SELECT id FROM state WHERE acronym = 'PI'));
--INSERT INTO city (name, state_id) VALUES ('São Bernardo do Campo', (SELECT id FROM state WHERE acronym = 'SP'));
--INSERT INTO city (name, state_id) VALUES ('Nova Iguaçú', (SELECT id FROM state WHERE acronym = 'RJ'));
--INSERT INTO city (name, state_id) VALUES ('João Pessoa', (SELECT id FROM state WHERE acronym = 'PB'));
--INSERT INTO city (name, state_id) VALUES ('São José dos Campos', (SELECT id FROM state WHERE acronym = 'SP'));
--INSERT INTO city (name, state_id) VALUES ('Santo André', (SELECT id FROM state WHERE acronym = 'SP'));
--INSERT INTO city (name, state_id) VALUES ('Ribeirão Preto', (SELECT id FROM state WHERE acronym = 'SP'));
--INSERT INTO city (name, state_id) VALUES ('Jaboatão dos Guararapes', (SELECT id FROM state WHERE acronym = 'PE'));
--INSERT INTO city (name, state_id) VALUES ('Osasco', (SELECT id FROM state WHERE acronym = 'SP'));
--INSERT INTO city (name, state_id) VALUES ('Uberlândia', (SELECT id FROM state WHERE acronym = 'MG'));
--INSERT INTO city (name, state_id) VALUES ('Sorocaba', (SELECT id FROM state WHERE acronym = 'SP'));
--INSERT INTO city (name, state_id) VALUES ('Contagem', (SELECT id FROM state WHERE acronym = 'MG'));
--INSERT INTO city (name, state_id) VALUES ('Aracajú', (SELECT id FROM state WHERE acronym = 'SE'));
--INSERT INTO city (name, state_id) VALUES ('Feira de Santana', (SELECT id FROM state WHERE acronym = 'BA'));
--INSERT INTO city (name, state_id) VALUES ('Cuiabá', (SELECT id FROM state WHERE acronym = 'MT'));
--INSERT INTO city (name, state_id) VALUES ('Aparecida de Goiânia', (SELECT id FROM state WHERE acronym = 'GO'));
--INSERT INTO city (name, state_id) VALUES ('Londrina', (SELECT id FROM state WHERE acronym = 'PR'));
--INSERT INTO city (name, state_id) VALUES ('Juiz de Fora', (SELECT id FROM state WHERE acronym = 'MG'));
--INSERT INTO city (name, state_id) VALUES ('Ananindeua', (SELECT id FROM state WHERE acronym = 'PA'));
--INSERT INTO city (name, state_id) VALUES ('Porto Velho', (SELECT id FROM state WHERE acronym = 'RO'));
--INSERT INTO city (name, state_id) VALUES ('Serra', (SELECT id FROM state WHERE acronym = 'ES'));
--INSERT INTO city (name, state_id) VALUES ('Niterói', (SELECT id FROM state WHERE acronym = 'RJ'));
--INSERT INTO city (name, state_id) VALUES ('Belford Roxo', (SELECT id FROM state WHERE acronym = 'RJ'));
--INSERT INTO city (name, state_id) VALUES ('Caxias do Sul', (SELECT id FROM state WHERE acronym = 'RS'));
--INSERT INTO city (name, state_id) VALUES ('Campos dos Goytacazes', (SELECT id FROM state WHERE acronym = 'RJ'));
--INSERT INTO city (name, state_id) VALUES ('Macapá', (SELECT id FROM state WHERE acronym = 'AP'));
--INSERT INTO city (name, state_id) VALUES ('Florianópolis', (SELECT id FROM state WHERE acronym = 'SC'));
--INSERT INTO city (name, state_id) VALUES ('Vila Velha', (SELECT id FROM state WHERE acronym = 'ES'));
--INSERT INTO city (name, state_id) VALUES ('Mauá', (SELECT id FROM state WHERE acronym = 'SP'));


-- São Paulo - SP
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Trabalha Brasil', 'https://www.trabalhabrasil.com.br/vagas-empregos-em-sao-paulo-sp', (SELECT id FROM city WHERE name LIKE '% Paulo'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Info Jobs', 'https://www.infojobs.com.br/empregos-em-sao-paulo.aspx', (SELECT id FROM city WHERE name LIKE '% Paulo'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Vagas', 'https://www.vagas.com.br/vagas-em-sao-paulo', (SELECT id FROM city WHERE name LIKE '% Paulo'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('SP Vagas', 'https://spvagas.com.br/', (SELECT id FROM city WHERE name LIKE '% Paulo'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Indeed', 'https://www.indeed.com.br/empregos-em-S%C3%A3o-Paulo,-SP', (SELECT id FROM city WHERE name LIKE '% Paulo'));

-- Rio de Janeiro - RJ
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Rio Vagas', 'https://riovagas.com.br/', (SELECT id FROM city WHERE name = 'Rio de Janeiro'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Info Jobs', 'https://www.infojobs.com.br/empregos-em-rio-janeiro.aspx', (SELECT id FROM city WHERE name = 'Rio de Janeiro'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('RJ Empregos', 'https://rjempregos.net/', (SELECT id FROM city WHERE name = 'Rio de Janeiro'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Empregos RJ', 'https://www.empregosrj.com/', (SELECT id FROM city WHERE name = 'Rio de Janeiro'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Carioca Empregos', 'https://cariocaempregos.com.br/', (SELECT id FROM city WHERE name = 'Rio de Janeiro'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Trabalha Brasil', 'https://www.trabalhabrasil.com.br/vagas-empregos-em-rio-de-janeiro-rj', (SELECT id FROM city WHERE name = 'Rio de Janeiro'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Indeed', 'https://www.indeed.com.br/empregos-em-Rio-de-Janeiro,-RJ', (SELECT id FROM city WHERE name = 'Rio de Janeiro'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Vagas', 'https://www.vagas.com.br/vagas-em-rio-de-janeiro', (SELECT id FROM city WHERE name = 'Rio de Janeiro'));

-- Brasilia - DF
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Trabalha Brasil', 'https://www.trabalhabrasil.com.br/vagas-empregos-em-brasilia-df', (SELECT id FROM city WHERE name LIKE 'Bras%lia'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Indeed', 'https://www.indeed.com.br/empregos-em-Bras%C3%ADlia,-DF', (SELECT id FROM city WHERE name = 'Bras%lia'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Hora do Emprego', 'https://www.horadoempregodf.com.br/', (SELECT id FROM city WHERE name LIKE 'Bras%lia'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Info Jobs', 'https://www.infojobs.com.br/empregos-em-brasilia,-df.aspx', (SELECT id FROM city WHERE name LIKE 'Bras%lia'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('RH Emprega', 'https://rhemprega.com.br/empregos/df/brasilia', (SELECT id FROM city WHERE name LIKE 'Bras%lia'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Vagas', 'https://www.vagas.com.br/vagas-em-brasilia', (SELECT id FROM city WHERE name LIKE 'Bras%lia'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Oportunidades DF', 'https://www.oportunidadesdf.com/category/vagas/', (SELECT id FROM city WHERE name LIKE 'Bras%lia'));

-- Salvador - BA
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Indeed', 'https://www.indeed.com.br/empregos-em-Salvador,-BA', (SELECT id FROM city WHERE name = 'Salvador'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Trabalha Brasil', 'https://www.trabalhabrasil.com.br/vagas-empregos-em-salvador-ba', (SELECT id FROM city WHERE name = 'Salvador'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Info Jobs', 'https://www.infojobs.com.br/empregos-em-salvador,-ba.aspx', (SELECT id FROM city WHERE name = 'Salvador'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Salvador Empregos', 'https://salvadorempregos.com/', (SELECT id FROM city WHERE name = 'Salvador'));

-- Fortaleza - CE
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Indeed', 'https://www.indeed.com.br/empregos-em-Fortaleza,-CE', (SELECT id FROM city WHERE name = 'Fortaleza'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Info Jobs', 'https://www.infojobs.com.br/empregos-em-fortaleza,-ce.aspx', (SELECT id FROM city WHERE name = 'Fortaleza'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Trabalha Brasil', 'https://www.trabalhabrasil.com.br/vagas-empregos-em-fortaleza-ce', (SELECT id FROM city WHERE name = 'Fortaleza'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Vagas', 'https://www.vagas.com.br/vagas-em-fortaleza', (SELECT id FROM city WHERE name = 'Fortaleza'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Balcão de Empregos', 'https://www.balcaodeempregos.com.br/vagas-em-fortaleza/675', (SELECT id FROM city WHERE name = 'Fortaleza'));

-- Belo Horizonte - MG
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Indeed', 'https://www.indeed.com.br/empregos-em-Belo-Horizonte,-MG', (SELECT id FROM city WHERE name = 'Belo Horizonte'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Info Jobs', 'https://www.infojobs.com.br/empregos-em-belo-horizonte,-mg.aspx', (SELECT id FROM city WHERE name = 'Belo Horizonte'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Vagas', 'https://www.vagas.com.br/vagas-em-belo-horizonte', (SELECT id FROM city WHERE name = 'Belo Horizonte'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Trabalha Brasil', 'https://www.trabalhabrasil.com.br/vagas-empregos-em-belo-horizonte-mg', (SELECT id FROM city WHERE name = 'Belo Horizonte'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('BNE', 'https://www.bne.com.br/vagas-de-emprego-em-belo-horizonte-mg', (SELECT id FROM city WHERE name = 'Belo Horizonte'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('BH Jobs', 'https://www.bhjobs.com.br/', (SELECT id FROM city WHERE name = 'Belo Horizonte'));

-- Manaus - AM
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Indeed', 'https://www.indeed.com.br/empregos-em-Manaus,-AM', (SELECT id FROM city WHERE name = 'Manaus'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Vagas', 'https://www.vagas.com.br/vagas-de-manaus', (SELECT id FROM city WHERE name = 'Manaus'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Trabalha Brasil', 'https://www.trabalhabrasil.com.br/vagas-empregos-em-manaus-am', (SELECT id FROM city WHERE name = 'Manaus'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Info Jobs', 'https://www.infojobs.com.br/empregos-em-manaus,-am.aspx', (SELECT id FROM city WHERE name = 'Manaus'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('BNE', 'https://www.bne.com.br/vagas-de-emprego-em-manaus-am', (SELECT id FROM city WHERE name = 'Manaus'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Balcão de Empregos', 'https://www.balcaodeempregos.com.br/vagas-em-manaus/161', (SELECT id FROM city WHERE name = 'Manaus'));

-- Curitiba - PR
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Info Jobs', 'https://www.infojobs.com.br/empregos-em-curitiba,-pr.aspx', (SELECT id FROM city WHERE name = 'Curitiba'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('BNE', 'https://www.bne.com.br/vagas-de-emprego-em-curitiba-pr', (SELECT id FROM city WHERE name = 'Curitiba'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Curitiba Vagas', 'http://www.curitibarh.com.br/vagas/', (SELECT id FROM city WHERE name = 'Curitiba'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Indeed', 'https://www.indeed.com.br/empregos-em-Curitiba,-PR', (SELECT id FROM city WHERE name = 'Curitiba'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Trabalha Brasil', 'https://www.trabalhabrasil.com.br/vagas-empregos-em-curitiba-pr', (SELECT id FROM city WHERE name = 'Curitiba'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Vagas', 'https://www.vagas.com.br/vagas-em-curitiba', (SELECT id FROM city WHERE name = 'Curitiba'));

-- Recife - PE
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Indeed', 'https://www.indeed.com.br/empregos-em-Recife,-PE', (SELECT id FROM city WHERE name = 'Recife'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Info Jobs', 'https://www.infojobs.com.br/empregos-em-recife,-pe.aspx', (SELECT id FROM city WHERE name = 'Recife'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Vagas', 'https://www.vagas.com.br/vagas-em-recife', (SELECT id FROM city WHERE name = 'Recife'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Trabalha Brasil', 'https://www.trabalhabrasil.com.br/vagas-empregos-em-recife-pe', (SELECT id FROM city WHERE name = 'Recife'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Recife Vagas', 'https://recifevagas.com.br/', (SELECT id FROM city WHERE name = 'Recife'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('BNE', 'https://www.bne.com.br/vagas-de-emprego-em-recife-pe', (SELECT id FROM city WHERE name = 'Recife'));

-- Goiânia - GO
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Trabalha Brasil', 'https://www.trabalhabrasil.com.br/vagas-empregos-em-goiania-go', (SELECT id FROM city WHERE name = 'Goiânia'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Indeed', 'https://www.indeed.com.br/empregos-em-Goi%C3%A2nia,-GO', (SELECT id FROM city WHERE name = 'Goiânia'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Info Jobs', 'https://www.infojobs.com.br/empregos-em-goiania,-go.aspx', (SELECT id FROM city WHERE name = 'Goiânia'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('BNE', 'https://www.bne.com.br/vagas-de-emprego-em-goiania-go', (SELECT id FROM city WHERE name = 'Goiânia'));
--INSERT INTO portal (name, jobs_url, city_id)
--VALUES ('Vagas', 'https://www.vagas.com.br/vagas-em-goiania', (SELECT id FROM city WHERE name = 'Goiânia'));

-- Belém - PA
-- Porto Alegre - RS
-- Guarulhos - SP
-- Campinas - SP
-- São Luis - MA
-- São Gonçalo - RJ
-- Maceió - AL
-- Duque de Caxias - RJ
-- Campo Grande - MS
-- Natal - RN
-- Teresina - PI
-- São Bernardo do Campo - SP
-- Nova Iguaçú - RJ
-- João Pessoa - PB
-- São José dos Campos - SP
-- Santo André - SP
-- Ribeirão Preto - SP
-- Jaboatão dos Guararapes - PE
-- Osasco - SP
-- Uberlândia - MG
-- Sorocaba - SP
-- Contagem - MG
-- Aracajú - SE
-- Feira de Santana - BA
-- Cuiabá - MT

-- Joinville - SC
INSERT INTO portal (name, jobs_url, city_id)
VALUES ('Joinville Vagas', 'https://www.joinvillevagas.com.br/', (SELECT id FROM city WHERE name = 'Joinville'));
INSERT INTO portal (name, jobs_url, city_id)
VALUES ('Indeed', 'https://br.indeed.com/jobs?q=&l=Joinville%2C+SC&sort=date', (SELECT id FROM city WHERE name = 'Joinville'));
INSERT INTO portal (name, jobs_url, city_id)
VALUES ('Info Jobs', 'https://www.infojobs.com.br/vagas-de-emprego-joinville.aspx', (SELECT id FROM city WHERE name = 'Joinville'));
INSERT INTO portal (name, jobs_url, city_id)
VALUES ('Trabalha Brasil', 'https://www.trabalhabrasil.com.br/vagas-empregos-em-joinville-sc', (SELECT id FROM city WHERE name = 'Joinville'));
INSERT INTO portal (name, jobs_url, city_id)
VALUES ('BNE', 'https://www.bne.com.br/vagas-de-emprego-em-joinville-sc', (SELECT id FROM city WHERE name = 'Joinville'));
INSERT INTO portal (name, jobs_url, city_id)
VALUES ('Vagas', 'https://www.vagas.com.br/vagas-de-joinville,-santa-catarina', (SELECT id FROM city WHERE name = 'Joinville'));

-- Aparecida de Goiânia - GO
-- Londrina - PR
-- Juiz de Fora - MG
-- Ananindeua - PA
-- Porto Velho - RO
-- Serra - ES
-- Niterói - RJ
-- Belford Roxo - RJ
-- Caxias do Sul - RS
-- Campos dos Goytacazes - RJ
-- Macapá - AP
-- Florianópolis - SC
-- Vila Velha - ES
-- Mauá - SP

INSERT INTO person (first_name, last_name, email, password, city_id, enabled, created_at)
  VALUES ('Ricardo', 'Campos', 'ricardompcampos@gmail.com', '', 1, TRUE, '2022-07-13 18:34:11');
--INSERT INTO person (first_name, last_name, email, password, city_id, enabled, created_at)
      --VALUES ('Leandro', 'Alves', 'leandro@bussolainvestments.com.br', '', 8, TRUE, '2021-04-19 20:28:10');
--INSERT INTO person (first_name, last_name, email, password, city_id, enabled, created_at)
      --VALUES ('Jaque', 'Schmoller', 'jaquessprung@gmail.com', '', 1, TRUE, '2022-03-31 20:45:10');
INSERT INTO person (first_name, last_name, email, password, city_id, enabled, created_at)
  VALUES ('Jéssica', 'Schmoller', 'jeschmoller@gmail.com', '', 1, TRUE, '2022-07-13 18:34:11');

--INSERT INTO authorities (email, authority) --VALUES ('ricardompcampos@gmail.com', 'ROLE_USER');
--INSERT INTO authorities (email, authority) --VALUES ('leandro@bussolainvestments.com.br', 'ROLE_USER');
--INSERT INTO authorities (email, authority) --VALUES ('jaquessprung@gmail.com', 'ROLE_USER');

--INSERT INTO user_terms --VALUES ((SELECT user_id FROM users WHERE email = 'jaquessprung@gmail.com'),'RH;Administrativo');

SELECT * FROM portal_jobs WHERE seen IS NULL;
UPDATE portal_jobs SET SEEN = NOW() WHERE seen IS NULL;