
# 📌 Wiki-Mais (W.I.P)

> Gerenciamento e criação de **Wikis**.

---

## 📚 Sobre o Projeto
 
Um gerenciador de wikis e artigos em Java, feito com o intuito de praticar o uso da Programação Orientada à Objetos e Banco de Dados com JDBC.

---


### Tecnologias Usadas:

- PostgreSQL 🐘
- Java 11 ☕

### Estrutura do Banco de Dados:
``` sql
create table cargo(
	id_cargo SERIAL primary key,
	nome_cargo VARCHAR(225) not null
);

create table usuario(
	id_usuario SERIAL primary key,
	id_cargo INT not null,
	nome_usuario VARCHAR(225) not null,
 senha_usuario VARCHAR(225) unique not null,
	email_usuario VARCHAR(225) unique not null,
	criacao_usuario TIMESTAMPTZ DEFAULT NOW(),
 
	foreign key (id_cargo) references cargo(id_cargo)
);

create table categoria(
	id_categoria SERIAL primary key,
	nome_categoria VARCHAR(225) not null
);

create table artigo(
	id_artigo SERIAL primary key,
	id_categoria INT not null,
	id_usuario INT not null,
	nome_artigo VARCHAR(225) not null,
	criacao_artigo TIMESTAMPTZ DEFAULT NOW(),
    wikitexto_artigo TEXT,
	
	foreign key (id_categoria) references categoria(id_categoria),
	foreign key (id_usuario) references usuario(id_usuario)
	
);

insert into cargo (nome_cargo) values ('ADMIN'),('MEMBRO'),('BANIDO');

```

#### Projeto ainda em desenvolvimento / W.I.P




