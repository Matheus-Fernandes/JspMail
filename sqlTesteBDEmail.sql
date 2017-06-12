create table UsuarioMaligno(
email varchar(100) not null,
senha varchar(30) not null,
constraint pkEmail primary key (email)
)


create table EmailCadastrado(
idEmailCadastrado int not null identity,
emailPrincipal varchar(100) not null,
outroEmail varchar(100) not null,
senha varchar(40) not null, 
servidor varchar(50) not null,
porta int not null,
constraint pkIdEmailCadastrado primary key (idEmailCadastrado),
constraint fkEmailPrincipal foreign key (emailPrincipal) references UsuarioMaligno
)

drop table EmailCadastrado

--porta int
--servidor

select * from EmailCadastrado




--cadastrar novo usuario
insert into UsuarioMaligno values ('emailLogin', 'senha')
insert into EmailCadastrado values ('emailLogin', 'emailAdicionado', 'senha')

--alterar email do usuario
update UsuarioMaligno set email = 'emailNovo' where email = 'email'
update EmailCadastrado set emailPrincipal = 'emailPrincipalNovo' where emailPrincipal = 'emailPrincipal'

UPDATE EmailCadastrado SET emailPrincipal='emailMalignoo', outroEmail='ju@gmail.com', senha='ju', servidorRecebimento='pop.gmail.com', portaRecebimento=990, servidorEnvio='smtp.gmail.com', portaEnvio='465' WHERE emailPrincipal='emailMalignoo' AND email

--alterar senha do usuario
update UsuarioMaligno set senha = 'senhaNova' where email = 'emailAtual'

--selecionar todos os usuarios
select * from UsuarioMaligno

--selecionar usuario especifico
select email from UsuarioMaligno where email = 'email' and senha = 'senha'

--deletar conta de usuario
delete from UsuarioMaligno where email = 'emailMalignoo' and senha = 'senha'
delete from EmailCadastrado where emailPrincipal = 'emailMalignoo'


--cadastrar email em conta
insert into EmailCadastrado values ('emailLogin', 'emailAdicionado', 'senha', 'pop.gmail.com', 995)

--alterar email cadastrado na conta
update EmailCadastrado set outroEmail = 'emailNovo' where emailPrincipal = 'emailPrincipal'

--selecionar todos os emails cadastrados
select * from EmailCadastrado

--selecionar todos os emails cadastrados em conta especifica
select outroEmail from EmailCadastrado where emailPrincipal = 'emailPrincipal'

--deletar email especifico cadastrado em conta especifica
delete from EmailCadastrado where outroEmail = 'outroEmail' and emailPrincipal='emailPrincipal'