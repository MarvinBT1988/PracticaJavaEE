-- Crear la base de datos
CREATE DATABASE MABT20231608;
GO

-- Usar la base de datos recién creada
USE MABT20231608;
GO

-- Crear la tabla Task
CREATE TABLE Task (
    Id INT PRIMARY KEY IDENTITY(1,1),
    Title NVARCHAR(50) NOT NULL,
    Description NVARCHAR(MAX) NOT NULL,
    Status NVARCHAR(50) NOT NULL,
    DateCreate DATETIME NOT NULL,
    DateComplete DATETIME NOT NULL
);
GO