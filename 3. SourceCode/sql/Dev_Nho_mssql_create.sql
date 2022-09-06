
DROP Database IF EXISTS Training_DevNho
CREATE DATABASE Training_DevNho
GO
use Training_DevNho;
CREATE TABLE [Department](
	id int NOT NULL Identity(1,1) primary key,
	departName nvarchar(32),
	isDeleted bit NOT NULL,
	timeStamp timestamp NOT NULL,
)
GO
CREATE TABLE [Users] (
	id int NOT NULL Identity(1,1) primary key,
	userName nvarchar(8) NOT NULL,
	fullName nvarchar(32) NOT NULL,
	departId int NOT NULL,
	email nvarchar(128),
	password nvarchar(128) NOT NULL,
	isDeleted bit NOT NULL,
	timeStamp timestamp NOT NULL,
	)
GO
CREATE TABLE [Events] (
	id int NOT NULL Identity(1,1) primary key,
	eventName nvarchar(250) NOT NULL,
	eventType int NOT NULL,
	eventImage varchar(150),
	creator int NOT NULL,
	description nvarchar(500),
	status int NOT NULL,
	optionType int NOT NULL,
	place nvarchar(50),
	startDate datetime,
	startHour datetime,
	endDate datetime,
	endHour datetime,
	isDeleted bit NOT NULL,
	timeStamp timestamp NOT NULL,
)
GO
CREATE TABLE [VoteOption] (
	id int NOT NULL Identity(1,1) primary key,
	eventId int NOT NULL,
	startDate datetime NOT NULL,
	place nvarchar(128) NOT NULL,
	optionImage varchar(150),
	isDeleted bit NOT NULL,
	timeStamp timestamp NOT NULL,
)
GO
CREATE TABLE [RegistEvents] (
	id int NOT NULL Identity(1,1) primary key,
	userId int NOT NULL,
	isJoined bit NOT NULL,
	voteOptionId int NOT NULL,
	voted bit NOT NULL,
	attachedPersonAdult int,
	attachedPersonChild int,
	isDeleted bit NOT NULL,
	timeStamp timestamp NOT NULL,
)
GO

ALTER TABLE [Users] WITH CHECK ADD CONSTRAINT [Users_fk0] FOREIGN KEY ([departId]) REFERENCES [Department]([id])
ON UPDATE CASCADE
GO
ALTER TABLE [Users] CHECK CONSTRAINT [Users_fk0]
GO


ALTER TABLE [VoteOption] WITH CHECK ADD CONSTRAINT [VoteOption_fk0] FOREIGN KEY ([eventId]) REFERENCES [Events]([id])
ON UPDATE CASCADE
GO
ALTER TABLE [VoteOption] CHECK CONSTRAINT [VoteOption_fk0]
GO

ALTER TABLE [RegistEvents] WITH CHECK ADD CONSTRAINT [RegistEvents_fk0] FOREIGN KEY ([userId]) REFERENCES [Users]([id])
ON UPDATE CASCADE
GO
ALTER TABLE [RegistEvents] CHECK CONSTRAINT [RegistEvents_fk0]
GO
ALTER TABLE [RegistEvents] WITH CHECK ADD CONSTRAINT [RegistEvents_fk1] FOREIGN KEY ([voteOptionId]) REFERENCES [VoteOption]([id])
ON UPDATE CASCADE
GO
ALTER TABLE [RegistEvents] CHECK CONSTRAINT [RegistEvents_fk1]
GO
insert into Department(departName,isDeleted) values('pt',0);

select * from Department;
insert into Users(userName,fullName,departId,email,password,isDeleted) values('Truong','Nguyen Gia Truong',1,'truong123@gmail.com','123',0)
select * from Users

