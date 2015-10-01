CREATE TABLE Customer
(
	customerID SERIAL PRIMARY KEY,
	mobile varchar(20)	 NOT NULL,
	email text NOT NULL,
	address varchar(50)	 NULL,
	username varchar(50)	 NOT NULL,
	password varchar(50)	 NOT NULL
)
;

insert into customer values(DEFAULT, 'm2', 'e2', 'add2', 'usr2', 'pw2');
ALTER TABLE customer ADD CONSTRAINT customer_unique_key UNIQUE (username, email);
ALTER TABLE customer drop CONSTRAINT customer_unique_key;
    ALTER TABLE customer ADD CONSTRAINT customer_username_uk UNIQUE (username);
ALTER TABLE customer ADD CONSTRAINT customer_email_uk UNIQUE (email);

CREATE TABLE PropertyAgent
(
	agentID SERIAL PRIMARY KEY ,
	username varchar(50)	 NOT NULL,
	mobile varchar(50)	 NOT NULL,
	email varchar(50)	 NOT NULL,
	gender varchar(50)	 NOT NULL,
	address varchar(100)	 NULL,
	licence varchar(15)	 NOT NULL,
	password varchar(50)	 NOT NULL
)
;

delete from propertyagent using propertyagent pag where propertyagent.username = pag.username and propertyagent.agentid < pag.agentid; 
delete from propertyagent using propertyagent pag where propertyagent.email = pag.email and propertyagent.agentid < pag.agentid; 
delete from propertyagent using propertyagent pag where propertyagent.licence = pag.licence and propertyagent.agentid < pag.agentid; 
ALTER TABLE propertyagent ADD CONSTRAINT propertyagent_unique_key UNIQUE (username, email, licence);
ALTER TABLE propertyagent drop CONSTRAINT propertyagent_unique_key;
ALTER TABLE propertyagent ADD CONSTRAINT username_unique_key UNIQUE (username);
ALTER TABLE propertyagent ADD CONSTRAINT email_unique_key UNIQUE (email);
ALTER TABLE propertyagent ADD CONSTRAINT licence_unique_key UNIQUE (licence);

CREATE TABLE Property
(
	propertyID serial PRIMARY KEY,
	name varchar(50)	 NOT NULL,
	address varchar(50)	 NOT NULL,
	rent_sale varchar(8)	 NOT NULL,
	image text NULL,
	comment varchar(100)	 NULL,
	agentID serial NOT NULL REFERENCES PropertyAgent(agentID) on delete cascade
)
;
alter table property add constraint property_agentid_fkey foreign key (agentID) REFERENCES PropertyAgent(agentID) on delete cascade;
ALTER TABLE property ADD CONSTRAINT property_unique_key UNIQUE (name, address);
ALTER TABLE property drop CONSTRAINT property_unique_key;
ALTER TABLE property ADD CONSTRAINT name_unique_key UNIQUE (name);
ALTER TABLE property ADD CONSTRAINT address_unique_key UNIQUE (address);
alter table property drop column image;
alter table property add column image oid;