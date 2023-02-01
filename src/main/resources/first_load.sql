-- Joinville - SC
INSERT INTO fjobs.portal (name, search_url, city, state)
  VALUES ('Joinville Vagas', 'https://www.joinvillevagas.com.br/', 'Joinville', 'SC');
INSERT INTO fjobs.portal (name, search_url, city_id)
  VALUES ('Indeed', 'https://br.indeed.com/jobs?q=&l=Joinville%2C+SC&sort=date', 'Joinville', 'SC');
INSERT INTO fjobs.portal (name, jobs_url, city_id)
  VALUES ('Info Jobs', 'https://www.infojobs.com.br/vagas-de-emprego-joinville.aspx', 'Joinville', 'SC');
INSERT INTO fjobs.portal (name, jobs_url, city_id)
  VALUES ('Trabalha Brasil', 'https://www.trabalhabrasil.com.br/vagas-empregos-em-joinville-sc', 'Joinville', 'SC');
INSERT INTO fjobs.portal (name, jobs_url, city_id)
  VALUES ('BNE', 'https://www.bne.com.br/vagas-de-emprego-em-joinville-sc', 'Joinville', 'SC');
INSERT INTO fjobs.portal (name, jobs_url, city_id)
  VALUES ('Vagas', 'https://www.vagas.com.br/vagas-de-joinville,-santa-catarina', 'Joinville', 'SC');

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
