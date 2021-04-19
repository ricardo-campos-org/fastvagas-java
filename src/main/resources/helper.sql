INSERT INTO states (name, sigla_uf) VALUES ('Santa Catarina', 'SC');
INSERT INTO states (name, sigla_uf) VALUES ('São Paulo', 'SP');
INSERT INTO states (name, sigla_uf) VALUES ('Rio de Janeiro', 'RJ');
INSERT INTO states (name, sigla_uf) VALUES ('Distrito Federal', 'DF');
INSERT INTO states (name, sigla_uf) VALUES ('Bahia', 'BA');
INSERT INTO states (name, sigla_uf) VALUES ('Ceará', 'CE');
INSERT INTO states (name, sigla_uf) VALUES ('Minas Gerais', 'MG');
INSERT INTO states (name, sigla_uf) VALUES ('Amazonas', 'AM');
INSERT INTO states (name, sigla_uf) VALUES ('Paraná', 'PR');
INSERT INTO states (name, sigla_uf) VALUES ('Pernambuco', 'PE');
INSERT INTO states (name, sigla_uf) VALUES ('Goiás', 'GO');
INSERT INTO states (name, sigla_uf) VALUES ('Pará', 'PA');
INSERT INTO states (name, sigla_uf) VALUES ('Rio Grande do Sul', 'RS');
INSERT INTO states (name, sigla_uf) VALUES ('Maranhão', 'MA');
INSERT INTO states (name, sigla_uf) VALUES ('Alagoas', 'AL');
INSERT INTO states (name, sigla_uf) VALUES ('Mato Grosso do Sul', 'MS');
INSERT INTO states (name, sigla_uf) VALUES ('Rio Grande do Norte', 'RN');
INSERT INTO states (name, sigla_uf) VALUES ('Piauí', 'PI');
INSERT INTO states (name, sigla_uf) VALUES ('Paraiba', 'PB');
INSERT INTO states (name, sigla_uf) VALUES ('Sergipe', 'SE');
INSERT INTO states (name, sigla_uf) VALUES ('Mato Grosso', 'MT');
INSERT INTO states (name, sigla_uf) VALUES ('Rondonia', 'RO');
INSERT INTO states (name, sigla_uf) VALUES ('Espírito Santo', 'ES');
INSERT INTO states (name, sigla_uf) VALUES ('Amapá', 'AP');

INSERT INTO cities (name, state_id) VALUES ('São Paulo', (SELECT state_id FROM states WHERE sigla_uf = 'SP'));
INSERT INTO cities (name, state_id) VALUES ('Rio de Janeiro', (SELECT state_id FROM states WHERE sigla_uf = 'RJ'));
INSERT INTO cities (name, state_id) VALUES ('Brasília', (SELECT state_id FROM states WHERE sigla_uf = 'DF'));
INSERT INTO cities (name, state_id) VALUES ('Salvador', (SELECT state_id FROM states WHERE sigla_uf = 'BA'));
INSERT INTO cities (name, state_id) VALUES ('Fortaleza', (SELECT state_id FROM states WHERE sigla_uf = 'CE'));
INSERT INTO cities (name, state_id) VALUES ('Belo Horizonte', (SELECT state_id FROM states WHERE sigla_uf = 'MG'));
INSERT INTO cities (name, state_id) VALUES ('Manaus', (SELECT state_id FROM states WHERE sigla_uf = 'AM'));
INSERT INTO cities (name, state_id) VALUES ('Curitiba', (SELECT state_id FROM states WHERE sigla_uf = 'PR'));
INSERT INTO cities (name, state_id) VALUES ('Recife', (SELECT state_id FROM states WHERE sigla_uf = 'PE'));
INSERT INTO cities (name, state_id) VALUES ('Goiânia', (SELECT state_id FROM states WHERE sigla_uf = 'GO'));
INSERT INTO cities (name, state_id) VALUES ('Belém', (SELECT state_id FROM states WHERE sigla_uf = 'PA'));
INSERT INTO cities (name, state_id) VALUES ('Porto Alegre', (SELECT state_id FROM states WHERE sigla_uf = 'RS'));
INSERT INTO cities (name, state_id) VALUES ('Guarulhos', (SELECT state_id FROM states WHERE sigla_uf = 'SP'));
INSERT INTO cities (name, state_id) VALUES ('Campinas', (SELECT state_id FROM states WHERE sigla_uf = 'SP'));
INSERT INTO cities (name, state_id) VALUES ('São Luis', (SELECT state_id FROM states WHERE sigla_uf = 'MA'));
INSERT INTO cities (name, state_id) VALUES ('São Gonçalo', (SELECT state_id FROM states WHERE sigla_uf = 'RJ'));
INSERT INTO cities (name, state_id) VALUES ('Maceió', (SELECT state_id FROM states WHERE sigla_uf = 'AL'));
INSERT INTO cities (name, state_id) VALUES ('Duque de Caxias', (SELECT state_id FROM states WHERE sigla_uf = 'RJ'));
INSERT INTO cities (name, state_id) VALUES ('Campo Grande', (SELECT state_id FROM states WHERE sigla_uf = 'MS'));
INSERT INTO cities (name, state_id) VALUES ('Natal', (SELECT state_id FROM states WHERE sigla_uf = 'RN'));
INSERT INTO cities (name, state_id) VALUES ('Teresina', (SELECT state_id FROM states WHERE sigla_uf = 'PI'));
INSERT INTO cities (name, state_id) VALUES ('São Bernardo do Campo', (SELECT state_id FROM states WHERE sigla_uf = 'SP'));
INSERT INTO cities (name, state_id) VALUES ('Nova Iguaçú', (SELECT state_id FROM states WHERE sigla_uf = 'RJ'));
INSERT INTO cities (name, state_id) VALUES ('João Pessoa', (SELECT state_id FROM states WHERE sigla_uf = 'PB'));
INSERT INTO cities (name, state_id) VALUES ('São José dos Campos', (SELECT state_id FROM states WHERE sigla_uf = 'SP'));
INSERT INTO cities (name, state_id) VALUES ('Santo André', (SELECT state_id FROM states WHERE sigla_uf = 'SP'));
INSERT INTO cities (name, state_id) VALUES ('Ribeirão Preto', (SELECT state_id FROM states WHERE sigla_uf = 'SP'));
INSERT INTO cities (name, state_id) VALUES ('Jaboatão dos Guararapes', (SELECT state_id FROM states WHERE sigla_uf = 'PE'));
INSERT INTO cities (name, state_id) VALUES ('Osasco', (SELECT state_id FROM states WHERE sigla_uf = 'SP'));
INSERT INTO cities (name, state_id) VALUES ('Uberlândia', (SELECT state_id FROM states WHERE sigla_uf = 'MG'));
INSERT INTO cities (name, state_id) VALUES ('Sorocaba', (SELECT state_id FROM states WHERE sigla_uf = 'SP'));
INSERT INTO cities (name, state_id) VALUES ('Contagem', (SELECT state_id FROM states WHERE sigla_uf = 'MG'));
INSERT INTO cities (name, state_id) VALUES ('Aracajú', (SELECT state_id FROM states WHERE sigla_uf = 'SE'));
INSERT INTO cities (name, state_id) VALUES ('Feira de Santana', (SELECT state_id FROM states WHERE sigla_uf = 'BA'));
INSERT INTO cities (name, state_id) VALUES ('Cuiabá', (SELECT state_id FROM states WHERE sigla_uf = 'MT'));
INSERT INTO cities (name, state_id) VALUES ('Aparecida de Goiânia', (SELECT state_id FROM states WHERE sigla_uf = 'GO'));
INSERT INTO cities (name, state_id) VALUES ('Londrina', (SELECT state_id FROM states WHERE sigla_uf = 'PR'));
INSERT INTO cities (name, state_id) VALUES ('Juiz de Fora', (SELECT state_id FROM states WHERE sigla_uf = 'MG'));
INSERT INTO cities (name, state_id) VALUES ('Ananindeua', (SELECT state_id FROM states WHERE sigla_uf = 'PA'));
INSERT INTO cities (name, state_id) VALUES ('Porto Velho', (SELECT state_id FROM states WHERE sigla_uf = 'RO'));
INSERT INTO cities (name, state_id) VALUES ('Serra', (SELECT state_id FROM states WHERE sigla_uf = 'ES'));
INSERT INTO cities (name, state_id) VALUES ('Niterói', (SELECT state_id FROM states WHERE sigla_uf = 'RJ'));
INSERT INTO cities (name, state_id) VALUES ('Belford Roxo', (SELECT state_id FROM states WHERE sigla_uf = 'RJ'));
INSERT INTO cities (name, state_id) VALUES ('Caxias do Sul', (SELECT state_id FROM states WHERE sigla_uf = 'RS'));
INSERT INTO cities (name, state_id) VALUES ('Campos dos Goytacazes', (SELECT state_id FROM states WHERE sigla_uf = 'RJ'));
INSERT INTO cities (name, state_id) VALUES ('Macapá', (SELECT state_id FROM states WHERE sigla_uf = 'AP'));
INSERT INTO cities (name, state_id) VALUES ('Florianópolis', (SELECT state_id FROM states WHERE sigla_uf = 'SC'));
INSERT INTO cities (name, state_id) VALUES ('Vila Velha', (SELECT state_id FROM states WHERE sigla_uf = 'ES'));
INSERT INTO cities (name, state_id) VALUES ('Mauá', (SELECT state_id FROM states WHERE sigla_uf = 'SP'));
INSERT INTO cities (name, state_id) VALUES ('Joinville', (SELECT state_id FROM states WHERE sigla_uf = 'SC'));

-- São Paulo - SP
INSERT INTO portals (name, url, city_id)
VALUES ('Trabalha Brasil', 'https://www.trabalhabrasil.com.br/vagas-empregos-em-sao-paulo-sp', (SELECT city_id FROM cities WHERE name LIKE '% Paulo'));
INSERT INTO portals (name, url, city_id)
VALUES ('Info Jobs', 'https://www.infojobs.com.br/empregos-em-sao-paulo.aspx', (SELECT city_id FROM cities WHERE name LIKE '% Paulo'));
INSERT INTO portals (name, url, city_id)
VALUES ('Vagas', 'https://www.vagas.com.br/vagas-em-sao-paulo', (SELECT city_id FROM cities WHERE name LIKE '% Paulo'));
INSERT INTO portals (name, url, city_id)
VALUES ('SP Vagas', 'https://spvagas.com.br/', (SELECT city_id FROM cities WHERE name LIKE '% Paulo'));
INSERT INTO portals (name, url, city_id)
VALUES ('Indeed', 'https://www.indeed.com.br/empregos-em-S%C3%A3o-Paulo,-SP', (SELECT city_id FROM cities WHERE name LIKE '% Paulo'));

-- Rio de Janeiro - RJ
INSERT INTO portals (name, url, city_id)
VALUES ('Rio Vagas', 'https://riovagas.com.br/', (SELECT city_id FROM cities WHERE name = 'Rio de Janeiro'));
INSERT INTO portals (name, url, city_id)
VALUES ('Info Jobs', 'https://www.infojobs.com.br/empregos-em-rio-janeiro.aspx', (SELECT city_id FROM cities WHERE name = 'Rio de Janeiro'));
INSERT INTO portals (name, url, city_id)
VALUES ('RJ Empregos', 'https://rjempregos.net/', (SELECT city_id FROM cities WHERE name = 'Rio de Janeiro'));
INSERT INTO portals (name, url, city_id)
VALUES ('Empregos RJ', 'https://www.empregosrj.com/', (SELECT city_id FROM cities WHERE name = 'Rio de Janeiro'));
INSERT INTO portals (name, url, city_id)
VALUES ('Carioca Empregos', 'https://cariocaempregos.com.br/', (SELECT city_id FROM cities WHERE name = 'Rio de Janeiro'));
INSERT INTO portals (name, url, city_id)
VALUES ('Trabalha Brasil', 'https://www.trabalhabrasil.com.br/vagas-empregos-em-rio-de-janeiro-rj', (SELECT city_id FROM cities WHERE name = 'Rio de Janeiro'));
INSERT INTO portals (name, url, city_id)
VALUES ('Indeed', 'https://www.indeed.com.br/empregos-em-Rio-de-Janeiro,-RJ', (SELECT city_id FROM cities WHERE name = 'Rio de Janeiro'));
INSERT INTO portals (name, url, city_id)
VALUES ('Vagas', 'https://www.vagas.com.br/vagas-em-rio-de-janeiro', (SELECT city_id FROM cities WHERE name = 'Rio de Janeiro'));

-- Brasília - DF
INSERT INTO portals (name, url, city_id)
VALUES ('Trabalha Brasil', 'https://www.trabalhabrasil.com.br/vagas-empregos-em-brasilia-df', (SELECT city_id FROM cities WHERE name LIKE 'Bras%lia'));
INSERT INTO portals (name, url, city_id)
VALUES ('Indeed', 'https://www.indeed.com.br/empregos-em-Bras%C3%ADlia,-DF', (SELECT city_id FROM cities WHERE name = 'Bras%lia'));
INSERT INTO portals (name, url, city_id)
VALUES ('Hora do Emprego', 'https://www.horadoempregodf.com.br/', (SELECT city_id FROM cities WHERE name LIKE 'Bras%lia'));
INSERT INTO portals (name, url, city_id)
VALUES ('Info Jobs', 'https://www.infojobs.com.br/empregos-em-brasilia,-df.aspx', (SELECT city_id FROM cities WHERE name LIKE 'Bras%lia'));
INSERT INTO portals (name, url, city_id)
VALUES ('RH Emprega', 'https://rhemprega.com.br/empregos/df/brasilia', (SELECT city_id FROM cities WHERE name LIKE 'Bras%lia'));
INSERT INTO portals (name, url, city_id)
VALUES ('Vagas', 'https://www.vagas.com.br/vagas-em-brasilia', (SELECT city_id FROM cities WHERE name LIKE 'Bras%lia'));
INSERT INTO portals (name, url, city_id)
VALUES ('Oportunidades DF', 'https://www.oportunidadesdf.com/category/vagas/', (SELECT city_id FROM cities WHERE name LIKE 'Bras%lia'));

-- Salvador - BA
INSERT INTO portals (name, url, city_id)
VALUES ('Indeed', 'https://www.indeed.com.br/empregos-em-Salvador,-BA', (SELECT city_id FROM cities WHERE name = 'Salvador'));
INSERT INTO portals (name, url, city_id)
VALUES ('Trabalha Brasil', 'https://www.trabalhabrasil.com.br/vagas-empregos-em-salvador-ba', (SELECT city_id FROM cities WHERE name = 'Salvador'));
INSERT INTO portals (name, url, city_id)
VALUES ('Info Jobs', 'https://www.infojobs.com.br/empregos-em-salvador,-ba.aspx', (SELECT city_id FROM cities WHERE name = 'Salvador'));
INSERT INTO portals (name, url, city_id)
VALUES ('Salvador Empregos', 'https://salvadorempregos.com/', (SELECT city_id FROM cities WHERE name = 'Salvador'));

-- Fortaleza - CE
INSERT INTO portals (name, url, city_id)
VALUES ('Indeed', 'https://www.indeed.com.br/empregos-em-Fortaleza,-CE', (SELECT city_id FROM cities WHERE name = 'Fortaleza'));
INSERT INTO portals (name, url, city_id)
VALUES ('Info Jobs', 'https://www.infojobs.com.br/empregos-em-fortaleza,-ce.aspx', (SELECT city_id FROM cities WHERE name = 'Fortaleza'));
INSERT INTO portals (name, url, city_id)
VALUES ('Trabalha Brasil', 'https://www.trabalhabrasil.com.br/vagas-empregos-em-fortaleza-ce', (SELECT city_id FROM cities WHERE name = 'Fortaleza'));
INSERT INTO portals (name, url, city_id)
VALUES ('Vagas', 'https://www.vagas.com.br/vagas-em-fortaleza', (SELECT city_id FROM cities WHERE name = 'Fortaleza'));
INSERT INTO portals (name, url, city_id)
VALUES ('Balcão de Empregos', 'https://www.balcaodeempregos.com.br/vagas-em-fortaleza/675', (SELECT city_id FROM cities WHERE name = 'Fortaleza'));

-- Belo Horizonte - MG
INSERT INTO portals (name, url, city_id)
VALUES ('Indeed', 'https://www.indeed.com.br/empregos-em-Belo-Horizonte,-MG', (SELECT city_id FROM cities WHERE name = 'Belo Horizonte'));
INSERT INTO portals (name, url, city_id)
VALUES ('Info Jobs', 'https://www.infojobs.com.br/empregos-em-belo-horizonte,-mg.aspx', (SELECT city_id FROM cities WHERE name = 'Belo Horizonte'));
INSERT INTO portals (name, url, city_id)
VALUES ('Vagas', 'https://www.vagas.com.br/vagas-em-belo-horizonte', (SELECT city_id FROM cities WHERE name = 'Belo Horizonte'));
INSERT INTO portals (name, url, city_id)
VALUES ('Trabalha Brasil', 'https://www.trabalhabrasil.com.br/vagas-empregos-em-belo-horizonte-mg', (SELECT city_id FROM cities WHERE name = 'Belo Horizonte'));
INSERT INTO portals (name, url, city_id)
VALUES ('BNE', 'https://www.bne.com.br/vagas-de-emprego-em-belo-horizonte-mg', (SELECT city_id FROM cities WHERE name = 'Belo Horizonte'));
INSERT INTO portals (name, url, city_id)
VALUES ('BH Jobs', 'https://www.bhjobs.com.br/', (SELECT city_id FROM cities WHERE name = 'Belo Horizonte'));

-- Manaus - AM
INSERT INTO portals (name, url, city_id)
VALUES ('Indeed', 'https://www.indeed.com.br/empregos-em-Manaus,-AM', (SELECT city_id FROM cities WHERE name = 'Manaus'));
INSERT INTO portals (name, url, city_id)
VALUES ('Vagas', 'https://www.vagas.com.br/vagas-de-manaus', (SELECT city_id FROM cities WHERE name = 'Manaus'));
INSERT INTO portals (name, url, city_id)
VALUES ('Trabalha Brasil', 'https://www.trabalhabrasil.com.br/vagas-empregos-em-manaus-am', (SELECT city_id FROM cities WHERE name = 'Manaus'));
INSERT INTO portals (name, url, city_id)
VALUES ('Info Jobs', 'https://www.infojobs.com.br/empregos-em-manaus,-am.aspx', (SELECT city_id FROM cities WHERE name = 'Manaus'));
INSERT INTO portals (name, url, city_id)
VALUES ('BNE', 'https://www.bne.com.br/vagas-de-emprego-em-manaus-am', (SELECT city_id FROM cities WHERE name = 'Manaus'));
INSERT INTO portals (name, url, city_id)
VALUES ('Balcão de Empregos', 'https://www.balcaodeempregos.com.br/vagas-em-manaus/161', (SELECT city_id FROM cities WHERE name = 'Manaus'));

-- Curitiba - PR
INSERT INTO portals (name, url, city_id)
VALUES ('Info Jobs', 'https://www.infojobs.com.br/empregos-em-curitiba,-pr.aspx', (SELECT city_id FROM cities WHERE name = 'Curitiba'));
INSERT INTO portals (name, url, city_id)
VALUES ('BNE', 'https://www.bne.com.br/vagas-de-emprego-em-curitiba-pr', (SELECT city_id FROM cities WHERE name = 'Curitiba'));
INSERT INTO portals (name, url, city_id)
VALUES ('Curitiba Vagas', 'http://www.curitibarh.com.br/vagas/', (SELECT city_id FROM cities WHERE name = 'Curitiba'));
INSERT INTO portals (name, url, city_id)
VALUES ('Indeed', 'https://www.indeed.com.br/empregos-em-Curitiba,-PR', (SELECT city_id FROM cities WHERE name = 'Curitiba'));
INSERT INTO portals (name, url, city_id)
VALUES ('Trabalha Brasil', 'https://www.trabalhabrasil.com.br/vagas-empregos-em-curitiba-pr', (SELECT city_id FROM cities WHERE name = 'Curitiba'));
INSERT INTO portals (name, url, city_id)
VALUES ('Vagas', 'https://www.vagas.com.br/vagas-em-curitiba', (SELECT city_id FROM cities WHERE name = 'Curitiba'));

-- Recife - PE
INSERT INTO portals (name, url, city_id)
VALUES ('Indeed', 'https://www.indeed.com.br/empregos-em-Recife,-PE', (SELECT city_id FROM cities WHERE name = 'Recife'));
INSERT INTO portals (name, url, city_id)
VALUES ('Info Jobs', 'https://www.infojobs.com.br/empregos-em-recife,-pe.aspx', (SELECT city_id FROM cities WHERE name = 'Recife'));
INSERT INTO portals (name, url, city_id)
VALUES ('Vagas', 'https://www.vagas.com.br/vagas-em-recife', (SELECT city_id FROM cities WHERE name = 'Recife'));
INSERT INTO portals (name, url, city_id)
VALUES ('Trabalha Brasil', 'https://www.trabalhabrasil.com.br/vagas-empregos-em-recife-pe', (SELECT city_id FROM cities WHERE name = 'Recife'));
INSERT INTO portals (name, url, city_id)
VALUES ('Recife Vagas', 'https://recifevagas.com.br/', (SELECT city_id FROM cities WHERE name = 'Recife'));
INSERT INTO portals (name, url, city_id)
VALUES ('BNE', 'https://www.bne.com.br/vagas-de-emprego-em-recife-pe', (SELECT city_id FROM cities WHERE name = 'Recife'));

-- Goiânia - GO
INSERT INTO portals (name, url, city_id)
VALUES ('Trabalha Brasil', 'https://www.trabalhabrasil.com.br/vagas-empregos-em-goiania-go', (SELECT city_id FROM cities WHERE name = 'Goiânia'));
INSERT INTO portals (name, url, city_id)
VALUES ('Indeed', 'https://www.indeed.com.br/empregos-em-Goi%C3%A2nia,-GO', (SELECT city_id FROM cities WHERE name = 'Goiânia'));
INSERT INTO portals (name, url, city_id)
VALUES ('Info Jobs', 'https://www.infojobs.com.br/empregos-em-goiania,-go.aspx', (SELECT city_id FROM cities WHERE name = 'Goiânia'));
INSERT INTO portals (name, url, city_id)
VALUES ('BNE', 'https://www.bne.com.br/vagas-de-emprego-em-goiania-go', (SELECT city_id FROM cities WHERE name = 'Goiânia'));
INSERT INTO portals (name, url, city_id)
VALUES ('Vagas', 'https://www.vagas.com.br/vagas-em-goiania', (SELECT city_id FROM cities WHERE name = 'Goiânia'));

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
INSERT INTO portals (name, url, city_id)
VALUES ('Joinville Vagas', 'https://www.joinvillevagas.com.br/', (SELECT city_id FROM cities WHERE name = 'Joinville'));
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

INSERT INTO users (first_name, last_name, email, password, city_id, enabled, created_at)
    VALUES ('Ricardo', 'Campos', 'ricardompcampos@gmail.com', '', 50, 1, '2020-01-03 14:16:10');

INSERT INTO authorities (email, authority) VALUES ('ricardompcampos@gmail.com', 'ROLE_USER');